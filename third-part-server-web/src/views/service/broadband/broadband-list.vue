<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div class="broadband-list">
        <Card>
            <p slot="title">
                <Icon type="mouse"></Icon>
                点击搜索进行搜索
            </p>

            <Row>
                <Input v-model="searchConName" placeholder="请输入姓名搜搜..." style="width: 200px"/>
                <DatePicker  v-model="startTime" :start-date="new Date()" type="date" show-week-numbers placeholder="开始时间" style="width: 150px"></DatePicker> ~
                <DatePicker v-model="endTime" :start-date="new Date()" type="date" show-week-numbers placeholder="结束时间" style="width: 150px"></DatePicker>
                <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                <Button @click="handleCancel" type="ghost">重置</Button>
                <span style="float: right">
                    <Button  @click="add" type="primary" shape="circle" icon="md-add">
                        新增宽带记录
                    </Button>
                </span>
            </Row>
        </Card>
        <Card>
            <Row>
                <Table :columns="columns" :data="dataList"></Table>
            </Row>
        </Card>
        <Card>
            <Row>
                <Page :current.sync="page.pageNum"
                      :page-size="page.pageSize"
                      :total.sync="page.totalCount"
                      @on-change="handlePage"
                      @on-page-size-change='handlePageSize'
                      show-elevator show-sizer></Page>
            </Row>
        </Card>

        <Modal
                v-model="editVisible"
                width="860"
                class="form-modal"
                :mask-closable="false"
        >

            <BroadbandEdit :broadband-id="editBroadbandId" @on-done="handleEditDone"></BroadbandEdit>
            <div slot="footer">

            </div>
        </Modal>

    </div>
</template>

<script>
    import broadbandRequest from '@/app/api/service/broadband';
    import dictionary from '../../../app/common/lib/dictionary';
    import BroadbandEdit from'./broadband-edit'
    import prompt from '@/libs/prompt';

    export default {
        name: 'broadband-list',
        components: {BroadbandEdit},
        data() {
            return {
                editVisible: false,
                editBroadbandId: '',
                query: {nameSearch: ''},
                searchConName: '',
                startTime:null,
                endTime:null,
                columns: [
                    {
                        key: 'index',
                        title: '序号',
                        width: 80,
                        render: (h, params) => {
                            // var column = params.column;
                            // var row = params.row;
                            var index = params.index;
                            return h('div', [
                                h('label', {}, (index + 1))
                            ]);
                        }
                    },

                    {
                        key: 'tel',
                        title: '联系方式'
                    },
                    {
                        key: 'operator',
                        title: '经办人'
                    },
                    {
                        key: 'system',
                        title: '系统'
                    },
                    {
                        key: 'chargeMode',
                        title: '计费方式'
                    },
                    {
                        key: 'monthlyFee',
                        title: '月费'
                    },
                    {
                        key: 'openTime',
                        title: '开始时间'
                    },
                    {
                        key: 'expirationTime',
                        title: '结束时间'
                    },
                    {
                        key: 'businessLicense',
                        title: '营业编码'
                    },
                    {
                        key: 'address',
                        title: '地址'
                    },
                    {   key: 'agent',
                        title: '使用人'
                    },
                    {   key: 'manager',
                        title: '经理人'
                    },
                    {   key: 'managerTel',
                        title: '经理人电话'
                    },
                    {   key: 'paymentMethod',
                        title: '支付方式'
                    },
                    {   key: 'collectionBank',
                        title: '托收银行'
                    },
                    {   key: 'collectionbankAcc',
                        title: '托收银行账号'
                    },
                    {
                        key:'businessLicense',
                        title:'营业编号'
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
                dataList: [],
                tableList: [],
                initTable: [],
                page: {
                    pageSize: 1,
                    pageNum: 1,
                    totalCount: 0
                }
            };
        },
        methods: {

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
                broadbandRequest.getByName(name,function (data) {
                    var list = data.data;
                    own.dataList=list;
                })
            },
            search() {
                var own = this;
                var query = own.query;
                var page = own.page;

                broadbandRequest.list(page, function (data) {
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
                    if (data.data && data.data.page) {
                        let page = data.data.page;
                        var totalCount =page.totalCount;
                        own.page.totalCount = totalCount;
                    }
                });
            },
            openEdit(id) {
                this.editBroadbandId= id;
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
                        broadbandRequest.delete(id, function () {
                            own.search();//刷新页面 调用listAll方法
                        });
                    },
                    onCancel: () => {

                    }
                });
            },
            handleEditDone(edit, info, supplier,editType) {
                // if (info.success) {
                //     this.editVisible = false;
                //     if('0'==editType){
                //         this.handleSearch();
                //     }else{
                //         this.search();
                //     }
                // }
                this.editVisible = false;
                this.search();
                prompt.message(info, '保存成功。', '保存失败！');
            }
        },
        mounted() {
            this.init();
        }

    };
</script>
