<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div class="user-list">
        <Card>
            <p slot="title">
                <Icon type="md-search" />
                点击搜索进行搜索
            </p>
            <Row>
                <Input v-model="query.name" placeholder="请输入名称搜搜..." style="width: 200px"/>
                <span @click="handleSearch" style="margin: 0 10px;">
                    <Button type="primary" icon="ios-search">搜索</Button>
                </span>
                <span @click="handleCancel" style="margin: 0 10px;">
                    <Button type="primary">重置</Button>
                </span>
                <span style="float: right">
                    <Button  @click="add" type="primary" shape="circle" icon="md-add">
                        新增服务器
                    </Button>
                </span>
            </Row>
        </Card>
        <Card>
            <Row>
                <Table :columns="columns" :data="dataList" border></Table>
            </Row>
        </Card>
        <Card>
            <Row>
                <Page :current.sync="query.page.pageNum"
                      :page-size="query.page.pageSize"
                      :total.sync="query.page.totalCount"
                      @on-change="handlePage"
                      @on-page-size-change='handlePageSize'
                      show-elevator show-sizer>
                </Page>
            </Row>
        </Card>
        <Modal
                v-model="editVisible"
                width="860"
                class="form-modal"
                :mask-closable="false"
        >

            <serverEdit :server-id="editServerId" @on-done="handleEditDone"></serverEdit>
            <div slot="footer">

            </div>
        </Modal>
    </div>
</template>

<script>
    import serverEdit from './server-edit';
    import serverRequest from '../../../app/api/service/server';
    import dictionary from '../../../app/common/lib/dictionary';
    import prompt from '@/libs/prompt';

    export default {
        name: 'server-list',
        data() {
            return {
                editVisible: false,
                editServerId: '',
                query: {
                    name: '',
                    serviceId: '',
                    page: {
                        pageSize: 10,
                        pageNum: 1,
                        totalCount: 0
                    }
                },
                columns: [
                    {
                        key: 'index',
                        title: '序号',
                        width: 70,
                        render: (h, params) => {
                            var index = params.index;
                            return h('div', [
                                h('label', {}, (index + 1))
                            ]);
                        }
                    },
                    {
                        key: 'projectName',
                        title: '项目名'
                    },
                    {
                        key: 'machineryRoom',
                        title: '机房'
                    },
                    {
                        key: 'intranetIp',
                        title: '内网'
                    },
                    {
                        key: 'otherIp',
                        title: '其他Ip'
                    },
                    {
                        key: 'fixedAssetsCode',
                        title: '固定资产编号'
                    },
                    {
                        key: 'purpose',
                        title: '用途'
                    },
                    {
                        key: 'equipmentType',
                        title: '设备类型'
                    },
                    {
                        key: 'account',
                        title: '账户'
                    },
                    {
                        key: 'osType',
                        title: 'OS类型'
                    },
                    {
                        key: 'loginType',
                        title: '登录方式'
                    },
                    {
                        key: 'remark',
                        title: '备注'
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
                dataList: []
            };
        },
        components: {serverEdit},
        methods: {
            init() {
                this.search();
            },
            handleSearch: function () {
                this.query.page.pageNum = 1;
                this.search();
            },
            handleCancel() {
                this.query.page.pageNum = 1;
                this.query.name = '';
                this.search();
            },
            handlePage(value) {
                var own = this;
                own.query.page.pageNum = value;
                this.search();
            },
            handlePageSize(value) {
                var own = this;
                own.query.page.pageSize = value;
                this.search();
            },
            search() {
                var own = this;
                var query = own.query;
                query.serviceId = this.$route.query.serviceId;
                serverRequest.pageList(query,function (data) {
                    if (data.data.list) {
                        var list = data.data.list;
                        dictionary.setName(list, 'common', 'flag', 'flagName');
                        own.dataList = list;
                    }
                    var totalCount = data.data.total;
                    own.query.page.totalCount = totalCount;
                });

            },
            openEdit(id) {
                this.editServerId = id;
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
                this.$Modal.confirm({
                    title: '确定',
                    content: '<p>确定删除？</p></p>',
                    onOk: () => {
                        serverRequest.delete(id, function (data) {
                            own.search();
                        });
                    },
                    onCancel: () => {

                    }
                });
            },
            handleEditDone(edit, info, role,editType) {
                if (info == 200) {
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
