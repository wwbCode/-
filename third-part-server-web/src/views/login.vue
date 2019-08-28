<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login" @keydown.enter="handleSubmit">
        <div class="login-con">
            <Card :bordered="false">
                <p slot="title">
                    <Icon type="log-in"></Icon>
                    欢迎登录
                </p>
                <div class="form-con">
                    <Form ref="loginForm" :model="form" :rules="rules">
                        <FormItem prop="userName">
                            <Input v-model="form.account" placeholder="请输入用户名">
                            <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" placeholder="请输入密码">
                            <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button @click="handleSubmit" type="primary" long>登录</Button>
                        </FormItem>
                    </Form>
                    <p class="login-tip">输入任意用户名和密码即可</p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    import auth from '@/libs/auth';
    import personal from '@/app/api/personal';

    export default {
        data () {
            return {
                form: {
                    account: 'admin',
                    password: ''
                },
                rules: {
                    account: [
                        {required: true, message: '账号不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            handleSubmit () {
                this.$refs.loginForm.validate((valid) => {
                    var own = this;
                    if (valid) {
                        var account = this.form.account;
                        var password = this.form.password;

                        personal.login(account, password, function (data) {
                            debugger;
                            var code = data.code;
                            var message = data.message;
                            if (code === '200') {
                                var user = data.data.user;
                                var token = data.data.token;
                                auth.removeToken();
                                auth.setUser(user);
                                auth.setToken(token);
                                own.$store.commit('setAvator', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3448484253,3685836170&fm=27&gp=0.jpg');
                                own.$router.push({
                                    name: 'home_index'
                                });
                            } else {
                                // console.log(own);
                                // own.$Modal.info({
                                //     title: '信息',
                                //     content: message
                                // });
                                own.$Message.error(message);
                            }
                        });
                    }
                });
            }
        },
        created() {}
    };
</script>

<style>

</style>
