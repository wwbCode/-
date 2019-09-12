<template><!--模板文件-->
    <div class="monitor">
        <card>
            <p slot="title">
                <Icon type="mouse"></Icon>
                点击搜索进行搜索
            </p>

            <Row>
                <Input v-model="searchConName" placeholder="请输入一些东西搜搜..." style="width: 200px"/>
                <DatePicker  v-model="startTime" :start-date="new Date()" type="date" show-week-numbers placeholder="开始时间" style="width: 150px"></DatePicker> ~
                <DatePicker v-model="endTime" :start-date="new Date()" type="date" show-week-numbers placeholder="结束时间" style="width: 150px"></DatePicker>
                <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                <Button @click="handleCancel"type="ghost">重置</Button>

                <span style="float: right">
                    <Button  @click="add" type="primary" shape="circle" icon="md-add">
                        新增宽带
                    </Button>
                </span>
            </Row>
        </card>
        <card>
            <Row>
                <Table :columns="columns" :data="dataList"></Table>
            </Row>
        </card>

        <card>
            <Row>
                <Page :current.sync="page.pageNum"
                      :page-size="page.pageSize"
                      :total.sync="page.totalCount"
                      @on-change="handlePage"
                      @on-page-size-change="handlePageSize"
                      show-elevator show-sizer></Page>
            </Row>
        </card>
        <modal
           v-model="editVisible"
           width="860"
           class="form-modal"
           :mask-closable="false"
          >
             <monitorEdit :monitor-id="editMonitorId"@on-done="handleEditDone"></monitorEdit>

            <div slot="footer">

              </div>
       </modal>

    </div>

</template>

<script>
    import monitorRequest from '@/app/api/service/monitor';
    import monitorEdit from './monitor-edit';
    export default {
        name: "monitor-list",
        components:{monitorEdit},
        data(){
            return{
                editVisible: false,
                editMonitorId:'',
                startTime:null,
                endTime:null,
                query: {nameSearch: ''},
                searchConName: '',
                dataList:[],
                tableList: [],
                initTable: [],
                page: {
                    pageSize: 2,
                    pageNum: 1,
                    totalCount: 0
                },

                columns:[
                    {
                        key:'index',
                        title:'序号',
                        width:80,
                        render:(h,params)=>{
                            var index = params.index;
                            return h('div',[
                                h('label',{},(index+1))
                            ]);
                        }
                    },
                    {
                        key:'company',
                        title:'所属公司'

                    },
                    {
                        key:'place',
                        title:'场地'

                    },
                    {
                        key:'leader',
                        title:'负责人'
                    },
                    {
                        key:'monitorType',
                        title:'监控类型'
                    },
                    {
                        key:'loginIp',
                        title:'登入ip'
                    },
                    {
                        key:'memory',
                        title:'存贮空间'
                    },
                    {
                        key:'storageDays',
                        title:'标准存储天数'
                    },
                    {
                        key:'acc',
                        title:'账户'
                    },
                    {
                        key:'password',
                        title:'密码'
                    },
                    {
                        key:'cameraType',
                        title:'摄像机类型'
                    },
                    {
                        key:'cameraNum',
                        title:'摄像机数量'

                    },
                    {
                        key:'purchasingSupplier',
                        title:'购买供应商'
                    },
                    {
                        key:'purchasingTel',
                        title:'供应商tel'

                    },

                    {
                        key:'deliveryDate',
                        title:'到货日期'

                    },

                    {
                        key:'warrantyPeriod',
                        title:'保修时长'

                    },

                    {
                        key:'implementationSupplier',
                        title:'实施供应商'

                    },

                    {
                        key:'implementationTel',
                        title:'实施供应商tel'

                    },

                    {
                        key:'installTel',
                        title:'安装人员tel'

                    },
                    {
                       key:'implementationWarrantyPeriod',
                       title:'保修时长'
                    },
                    {
                        key:'serviceType',
                        title:'服务类型'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.edit(params.row.id);
                                        }
                                    }
                                }, '编辑'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.delete(params.row.id);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],

            }
        },
        methods:{
            init () {
                this.search();
            },
            handleSearch: function () {
                this.page.pageNum = 1;
                var m= this.searchConName;
                this.searchx(m);
            },
            handleCancel () {
                this.page.pageNum = 1;
                this.query = {};
                this.search();
                this.searchConName='';
            },
            handlePage (value) {
                var own = this;
                own.page.pageNum = value;
                this.search();
            },
            handlePageSize (value) {
                var own = this;
                own.page.pageSize = value;
                this.search();
            },

            searchx(name){
                debugger;
                var own = this;
                var page = own.page;
               monitorRequest.getByName(name,function (data) {
                    var list = data.data.list;
                    own.dataList=list;
                })

            },
            search() {
                var own = this;
                var query = own.query;
                var page = own.page;
                debugger;
                monitorRequest.list(page, function (data) {
                    debugger;
                    if (data.data.list) {
                        debugger;
                        var list =data.data.list;
                        debugger;
                        // dictionary.setName(list, 'common', 'flag', 'flagName');
                        // dictionary.setName(list, 'common', 'unit', 'unitName');
                        // dictionary.setName(list, 'common', 'isTop', 'isTopName');
                        own.dataList = list;
                    }
                    if (data.data && data) {
                        var page = data.data;
                        var totalCount =page;
                        own.page = totalCount;
                    }
                });
            },
            openEdit(id) {
                this.editMonitorId = id;
                this.editVisible = true;
            },
            add() {
                this.openEdit();
            },
            edit(id) {
                this.openEdit(id);
            },
            delete(id) {
                var own = this;
                debugger;
                this.$Modal.confirm({
                    title: '确定',
                    content: '<p>确定删除？</p></p>',
                    onOk: () => {
                        debugger;
                        monitorRequest.delete(id, function () {
                            own.search();//刷新页面 调用listAll方法
                        });
                    },
                    onCancel: () => {
                    }
                });
            },
            handleEditDone(edit, info, supplier,editType) {
                debugger;
                this.editVisible = false;
                this.search();
                // if('0'==editType){
                //     this.handleSearch();
                // }else{
                //     this.search();
                // }
                prompt.message(info, '保存成功。', '保存失败！');
            }
        },
        mounted(){//狗子函数
            this.init();
        }
    };
</script>

<style scoped>
    @import '../../../styles/common.less';/*样式*/
</style>