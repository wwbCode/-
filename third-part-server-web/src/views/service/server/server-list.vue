<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div class="user-list">
        <Card>
            <p slot="title">
                <Icon type="md-search" />
                服务器详情
            </p>
            <row>
                <span style="float: left">
                    <Button  @click="add" type="primary" shape="circle" icon="md-add">
                        新增服务器
                    </Button>
                </span>
            </row>
        </Card>
        <Card>
            <Row>
                <Table :columns="columns" :data="dataList" border></Table>
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
                    serviceId: ''
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
