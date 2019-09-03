<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div>
        <p slot="header" style="height: 30px">

        </p>
        <Form ref="user" :model="user" :rules="ruleValidate" label-position="left">
            <Row>
                <Col span="10">
                    <Card >
                        <p slot="title">用户信息</p>
                        <Form-item label="姓名：" prop="name">
                            <Input v-model="user.name" placeholder="姓名" type="text"/>
                        </Form-item>
                        <Form-item label="账号：" prop="account">
                            <Input v-model="user.account" placeholder="账号" type="text"/>
                        </Form-item>
                        <Form-item label="密码：" prop="password">
                            <Input v-model="user.password" placeholder="密码" type="text"/>
                        </Form-item>
                        <Form-item label="确认密码：" prop="repeatPassword">
                            <Input v-model="user.repeatPassword" placeholder="密码" type="text"/>
                        </Form-item>
                        <Form-item label="手机：" prop="mobile">
                            <Input v-model="user.mobile" placeholder="手机" type="text"/>
                        </Form-item>
                        <Form-item label="工号：" prop="employeeNumber">
                            <Input v-model="user.employeeNumber" placeholder="工号" type="text"/>
                        </Form-item>
                        <Form-item label="简介：" prop="introduce">
                            <Input v-model="user.introduce" placeholder="介绍" type="textarea"/>
                        </Form-item>
                        <Form-item label="状态：" prop="flag">
                            <RadioGroup v-model="user.flag">
                                <Radio label="1">启用</Radio>
                                <Radio label="0">禁用</Radio>
                            </RadioGroup>
                        </Form-item>
                    </Card>
                </Col>
                <Col span="13" offset="1">
                    <Card dis-hover style="height: 380px">
                        <p slot="title">角色信息</p>
                        <Scroll>
                            <Transfer
                                    :titles="['所有角色', '已选角色']"
                                    :data="dataList"
                                    :render-format="roleRender"
                                    :target-keys="targetKeys"
                                    @on-change="handleChange"
                            >

                            </Transfer>
                        </Scroll>
                    </Card>
                </Col>
            </Row>
            <div class="footer-item">
                <Form-item>
                    <Button type="primary" size="large" @click="handleSubmit" long>
                        提交
                    </Button>
                </Form-item>
            </div>
        </Form>
    </div>
</template>

<script>
    import roleRequest from '../../../app/api/system/role';
    import userRequest from '../../../app/api/system/user';
    import prompt from '@/libs/prompt';
    import * as base from '../../../app/common/lib/base';
    import boxUtil from '../../../app/common/lib/box.util';
    import propertyUtil from '../../../app/common/lib/property.util';

    export default {
        name: 'user-add',
        props: {
            userId: {
                type: String
            }
        },

        data() {
            const valideRepeatPassword = (rule, value, callback) => {
                if (value !== this.user.password) {
                    callback(new Error('两次输入密码不一致'));
                } else {
                    callback();
                }
            };
            const isExit = (rule, value, callback) => {
                userRequest.isExist(this.user.id, this.user.account, function (data) {
                    if (data.data == true) {
                        return callback(new Error('账号已存在！'));
                    } else {
                        callback();
                    }
                });
            };
            return {
                currentId: this.userId,
                user: this.getData(),
                repeatPassword: '',
                ruleValidate: {
                    name: [
                        {required: true, message: '姓名不能为空', trigger: 'blur'}
                    ],
                    account: [
                        {required: true, message: '账号不能为空', trigger: 'blur'},
                        {validator: isExit, trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 6, message: '请至少输入6个字符', trigger: 'blur'},
                        {max: 32, message: '最多输入32个字符', trigger: 'blur'}
                    ],
                    repeatPassword: [
                        {required: true, message: '请再次输入密码', trigger: 'blur'},
                        {validator: valideRepeatPassword, trigger: 'blur'}
                    ]
                },

                dataList: [],
                targetKeys: []
            };
        },
        methods: {
            init() {
                this.load();
                this.loadRoleList();
            },
            setCurrentId(id) {
                this.editType = id ? '1' : '0';
                if (id === this.currentId) return;
                this.currentId = id;
            },
            roleRender(item) {
                return item.name;
            },
            handleChange(newTargetKeys) {
                this.targetKeys = newTargetKeys;
            },
            load() {

            },
            getData() {
                var data = {
                    id: '',// 用户Id
                    account: '',// 帐号
                    password: '',// 密码
                    // createTime:'',// 建立时间
                    // updateTime:'',//
                    flag: '1',// 有效标志 1：启用 0：停用
                    employeeNumber: '',
                    name: '',// 姓名
                    gender: '',// 性别,1:男 2:女 3:保密
                    // /基本信息
                    avatar: '',// 自定义照片
                    mobile: '',// 手机
                    email: '',// 电子信箱
                    qq: '',// qq
                    identityCard: '',// 身份证号码
                    remark: '',// 备注
                    introduce: '',// 个人简介
                };
                return data;
            },
            loadRoleList() {
                var own = this;
                roleRequest.allList(function (data) {
                    var info = data.code;
                    if (data && data.data) {
                        var list = data.data;
                        propertyUtil.fromProperty(list, '', 'id', 'key');
                        own.dataList = list;
                    }
                });
            },
            getSelectedRoleIds() {
                var own = this;
                var ids = own.targetKeys;
                return ids;
            },
            handleSubmit() {
                var own = this;
                var roleIds = this.getSelectedRoleIds();
                this.$refs['user'].validate((valid) => {
                    if (valid) {
                        userRequest.addOrUpdate(own.user, roleIds, function (data) {
                            var info = data.code;
                            var user = data.data;
                            own.$emit('on-done', own, info, user);
                        });
                    }
                });
            }
        },
        watch: {
            userId(val) {
                this.setCurrentId(val);
                this.init();
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
