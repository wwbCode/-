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
                <Button
                        @click="add"
                        type="primary" shape="circle" icon="md-add">
                    新增角色
                </Button>
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

            <roleEdit :role-id="editRoleId" @on-done="handleEditDone"></roleEdit>
            <div slot="footer">

            </div>
        </Modal>
    </div>
</template>

<script>
    import roleEdit from './role-edit';
    import roleRequest from '../../../app/api/system/role';
    import dictionary from '../../../app/common/lib/dictionary';
    import prompt from '@/libs/prompt';

    export default {
        name: 'role-list',
        data() {
            return {
                editVisible: false,
                editRoleId: '',
                query: {
                    name: '',
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
                        key: 'name',
                        title: '角色名'
                    },
                    {
                        key: 'introduce',
                        title: '介绍'
                    },
                    {
                        key: 'flagName',
                        title: '有效标志'
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
        components: {roleEdit},
        methods: {
            init() {
                this.search();
            },
            handleSearch: function () {
                this.query.page.pageNum = 1;
                this.search();
            },
            handleCancel() {
                this.data = this.initTable;
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

                roleRequest.list(query,function (data) {
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
                this.editRoleId = id;
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
                        roleRequest.delete(id, function (data) {
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
