<style lang="less">
    @import '../../styles/common.less';
    @import '../tables/components/table.less';
</style>

<template>
    <div class="user-list">
        <Card>
            <p slot="title">
                <Icon type="mouse"></Icon>
                点击搜索进行搜索
            </p>

            <Row>
                <Input v-model="searchConName" placeholder="请输入姓名搜搜..." style="width: 200px"/>
                <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary"
                                                                            icon="search">搜索</Button></span>
                <Button @click="handleCancel" type="ghost">取消</Button>
            </Row>
        </Card>
        <Card>
            <Row>
                <Table :columns="columns" :data="tableList"></Table>
            </Row>
        </Card>
        <Card>
            <Row>
                <Page :current.sync="page.pageNumber"
                      :page-size="page.pageSize"
                      :total.sync="page.totalCount"
                      @on-change="handlePage"
                      @on-page-size-change='handlePageSize'

                      show-elevator show-sizer></Page>
            </Row>
        </Card>

    </div>
</template>

<script>

    import userRequest from '@/app/api/user';

    export default {
        name: 'user-list',
        data() {
            return {
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
                        title: '姓名'
                    },
                    {
                        key: 'account',
                        title: '帐号'
                    },
                    {
                        key: 'mobile',
                        title: '手机'
                    },
                    {
                        key: 'createTime',
                        title: '创建时间'
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
                                            this.show(params.index);
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
                                            this.remove(params.index);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],
                tableList: [],
                initTable: [],
                page: {
                    pageSize: 10,
                    pageNumber: 1,
                    totalCount: 0
                }
            };
        },
        methods: {
            init() {
                this.search();
            },
            search() {
                var own = this;
                var queryText = this.searchConName;
                var userQuery = {'queryText': queryText};
                var page = {
                    'pageNumber': this.page.pageNumber,
                    'pageSize': this.page.pageSize
                };

                userRequest.list(userQuery, page, function (data) {
                    if (data.body && data.body.list) {
                        var body = data.body;

                        var list = body.list;

                        own.tableList = list;
                    }
                    if (data.body && data.body.page) {
                        var body = data.body;
                        var page = body.page;

                        // var pageNumber = page.pageNumber;
                        var totalCount = page.totalCount;

                        // own.page.pageNumber = pageNumber;
                        own.page.totalCount = totalCount;
                    }
                });
                // var own = this;
                // var text = this.searchConName;
                // var employee = {"name": text};
                // var page = {
                //     "pageNumber": this.page.pageNumber,
                //     "pageSize": this.page.pageSize
                // };
                //
                // var body = {
                //     "employee": employee,
                //     "page": page
                // };
                //
                //
                // var m = {"body": body};
                // var d = {data: base.objectToJson(m)};
                //
                //
                // service.post('/employee/list', m, function (data) {
                //     if (data.body && data.body.list) {
                //         var body = data.body;
                //
                //         var list = body.list;
                //
                //         own.tableList = list;
                //     }
                //     if (data.body && data.body.page) {
                //         var body = data.body;
                //
                //         var page = body.page;
                //
                //         var pageNumber = page.pageNumber;
                //         var totalCount = page.totalCount;
                //
                //         //own.page.pageNumber = pageNumber;
                //         own.page.totalCount = totalCount;
                //     }
                // });
            },
            handleSearch: function () {
                this.page.pageNumber = 1;
                this.search();
            },
            handleCancel() {
                this.data = this.initTable;
            },
            handlePage(value) {
                var own = this;
                own.page.pageNumber = value;
                this.search();
            },
            handlePageSize(value) {
                var own = this;
                own.page.pageSize = value;
                this.search();
            },
            show(index) {
                this.$Modal.info({
                    title: 'User Info',
                    content: 'Name：'
                });
            },
            remove(index) {

            }
        },
        mounted() {
            this.init();
        }
    };
</script>
