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
                    新增用户
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
                width="960"
                class="form-modal"
                :mask-closable="false"
        >

            <userEdit :user-id="editId" @on-done="handleEditDone"></userEdit>
            <div slot="footer">

            </div>
        </Modal>
        <Modal
                v-model="addVisible"
                width="960"
                class="form-modal"
                :mask-closable="false"
        >
            <userAdd @on-done="handleAddDone"></userAdd>
            <div slot="footer">

            </div>
        </Modal>
        <Modal v-model="editPasswordModal" :closable='false' :mask-closable=false :width="500">
            <h3 slot="header" style="color:#2D8CF0">修改密码</h3>
            <Form ref="editPasswordForm" :model="editPasswordForm" :label-width="100" label-position="right"
                  :rules="passwordValidate">
                <FormItem label="新密码" prop="newPassword">
                    <Input v-model="editPasswordForm.newPassword" placeholder="请输入新密码，至少6位字符"></Input>
                </FormItem>
                <FormItem label="确认新密码" prop="repeatPassword">
                    <Input v-model="editPasswordForm.repeatPassword" placeholder="请再次输入新密码"></Input>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="text" @click="cancelEditPassword">取消</Button>
                <Button type="primary" :loading="savePasswordLoading" @click="saveEditPassword">保存</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    import userEdit from './user-edit';
    import userAdd from './user-add';
    import userRequest from '../../../app/api/system/user';
    import dictionary from '../../../app/common/lib/dictionary';
    import prompt from '@/libs/prompt';
    import permission from '../../../app/common/auth/permission';
    import md5 from 'js-md5';
    import auth from '../../../app/common/auth/auth';

    export default {
        name: 'user-list',
        data () {
            const valideRepeatPassword = (rule, value, callback) => {
                if (value !== this.editPasswordForm.newPassword) {
                    callback(new Error('两次输入密码不一致'));
                } else {
                    callback();
                }
            };
            return {
                editVisible: false,
                addVisible: false,
                editPasswordModal: false, // 修改密码模态框显示
                savePasswordLoading: false,
                oldPasswordError: '',
                editPasswordForm: {
                    newPassword: '',
                    repeatPassword: ''
                },
                passwordValidate: {
                    newPassword: [
                        {required: true, message: '请输入新密码', trigger: 'blur'},
                        {min: 6, message: '请至少输入6个字符', trigger: 'blur'},
                        {max: 32, message: '最多输入32个字符', trigger: 'blur'}
                    ],
                    repeatPassword: [
                        {required: true, message: '请再次输入新密码', trigger: 'blur'},
                        {validator: valideRepeatPassword, trigger: 'blur'}
                    ]
                },
                editId: '',
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
                        title: '姓名'
                    },
                    {
                        key: 'account',
                        title: '帐号'
                    },
                    {
                        key: 'flagName',
                        title: '有效标志'
                    },
                    {
                        key: 'role',
                        title: '角色',
                        render: this.roleInfoList
                    },
                    {
                        key: 'createTime',
                        title: '创建时间'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 200,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small',
                                        // disabled: !permission.has('third.system.user.addOrUpdate')
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
                                        size: 'small',
                                        // disabled: !permission.has('third.system.user.delete')
                                    },
                                    on: {
                                        click: () => {
                                            this.delete(params.row.id);
                                        }
                                    }
                                }, '删除'),
                                h('Button', {
                                    props: {
                                        type: 'dashed',
                                        size: 'small',
                                        // disabled: !permission.has('third.system.user.delete')
                                    },
                                    on: {
                                        click: () => {
                                            this.showEditPassword(params.row.id);
                                        }
                                    }
                                }, '修改密码')
                            ]);
                        }
                    }
                ],
                dataList: [],

            };
        },
        components: {userEdit, userAdd},
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

                userRequest.list(query, function (data) {
                    if (data.data.list) {
                        var list = data.data.list;
                        dictionary.setName(list, 'common', 'flag', 'flagName');
                        own.dataList = list;
                    }
                    var totalCount = data.data.total;
                    own.query.page.totalCount = totalCount;
                });
            },
            roleInfoList(h, params) {
                var user = params.row;
                var roleInfoList = user.userRoleInfoList;

                var text = "";
                text += "<ul>";
                if (roleInfoList) {
                    var l = roleInfoList.length;
                    for (var i = 0; i < l; i++) {
                        var info = roleInfoList[i];
                        var roleName = info.roleName;
                        text += "<li>";
                        text += roleName;
                        text += "</li>";
                    }
                }
                text += "</ul>";
                return h('div', {domProps: {innerHTML: text}});
            },
            openEdit(id) {
                this.editId = id;
                this.editVisible = true;
            },
            add() {
                this.addVisible = true;
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
                        userRequest.delete(id, function (data) {
                            own.search();
                        });
                    },
                    onCancel: () => {

                    }
                });
            },
            showEditPassword () {
                this.editPasswordModal = true;
            },
            cancelEditPassword () {
                this.editPasswordModal = false;
            },
            saveEditPassword () {
                var own = this;
                this.$refs['editPasswordForm'].validate((valid) => {
                    if (valid) {
                        var newPassword = own.editPasswordForm.newPassword;
                        newPassword = md5(newPassword);
                        own.savePassLoading = true;
                        userRequest.updatePassword('', newPassword, function (data) {
                            own.savePassLoading = false;
                            var info = data.code;
                            if (info == 200) {
                                own.editPasswordModal = false;
                                auth.setPassword(newPassword);
                                prompt.message(info, '修改成功。', '修改失败！');
                            }
                        });
                    }
                });
            },
            handleEditDone(edit, info, user) {
                if (info == 200) {
                    this.editVisible = false;
                    this.handleSearch();
                    prompt.message(info, '保存成功。', '保存失败！');
                }

            },
            handleAddDone(edit, info, user) {
                if (info == 200) {
                    this.addVisible = false;
                    this.search();
                    prompt.message(info, '保存成功。', '保存失败！');
                }
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
