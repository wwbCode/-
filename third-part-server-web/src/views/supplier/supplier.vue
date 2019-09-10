<style lang="less">
    @import '../../styles/common.less';
</style>

<template>
    <div class="supplier">
        <Card>
            <p slot="title">
                <Icon type="mouse"></Icon>
                点击搜索进行搜索
            </p>

            <Row>
                <Input v-model="searchConName" placeholder="请输入姓名搜搜..." style="width: 200px"/>
                <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                <Button @click="handleCancel" type="ghost">重置</Button>

                <!--<Input v-model="searchConName" placeholder="请输入电话搜搜..." style="width: 200px"/>-->
                <!--<span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>-->
                <!--<Button @click="handleCancel" type="ghost">重置</Button>-->
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

            <SupplierEdit :supplier-id="editSupplierId" @on-done="handleEditDone"></SupplierEdit>
            <div slot="footer">

            </div>
        </Modal>

    </div>
</template>

<script>

    import supplierRequest from '@/app/api/supplier/supplier';
    import SupplierEdit from "./supplier-edit";
    import prompt from '@/libs/prompt';

    export default {
        name: 'list',
        components: {SupplierEdit},
        data() {
            return {
                editVisible: false,
                editSupplierId: '',
                query: {nameSearch: ''},
                searchConName: '',
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
                        key: 'name',
                        title: '供应商'
                    },
                    {
                        key: 'address',
                        title: '地址'
                    },
                    {
                        key: 'tel',
                        title: '联系方式'
                    },
                    {
                        key: 'remark',
                        title: '简略信息'
                    },
                    {
                        key: 'startTime',
                        title: '合作时间'
                    },
                    {
                        key: 'operator',
                        title: '经办人'
                    },
                    {   key: 'user',
                        title: '使用人'},
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
                    pageSize: 2,
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
                supplierRequest.getByName(name,function (data) {
                    var list = data.data;
                    own.dataList=list;
                })

            },
            search() {
                var own = this;
                var query = own.query;
                var page = own.page;

                supplierRequest.list(page, function (data) {
                    debugger;
                    if (data.rows) {
                        debugger;
                        var list =data.rows;
                        debugger;
                        // dictionary.setName(list, 'common', 'flag', 'flagName');
                        // dictionary.setName(list, 'common', 'unit', 'unitName');
                        // dictionary.setName(list, 'common', 'isTop', 'isTopName');
                        own.dataList = list;
                    }
                    if (data.rows && data.page) {
                        var body = data.rows;
                        var page = data.page;
                        var totalCount =page;
                        own.page.totalCount = totalCount;
                    }
                });
            },
            openEdit(id) {
                this.editSupplierId = id;
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
                       supplierRequest.delete(id, function () {
                           own.search();//刷新页面 调用listAll方法
                        });
                    },
                    onCancel: () => {

                    }
                });
            },
            handleEditDone(edit, info, supplier,editType) {
                if (info.success) {
                    this.editVisible = false;
                    if('0'==editType){
                        this.handleSearch();
                    }else{
                        this.search();
                    }
                }
                prompt.message(info, '保存成功。', '保存失败！');
            }
        },
        mounted() {
            this.init();
        }

    };
</script>
