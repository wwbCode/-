<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div>
        <p slot="header" style="height: 30px">

        </p>
        <Form ref="server" :model="server" :rules="ruleValidate" >
            <Row>
                <Col>
                    <Card >
                    <p slot="title">服务器信息</p>
                    <Form-item label="项目名：" prop="name">
                        <Input v-model="server.projectName" placeholder="项目名" type="text"/>
                    </Form-item>
                    <Form-item label="机房：" prop="code">
                        <Input v-model="server.machineryRoom" placeholder="机房" type="textarea"/>
                    </Form-item>
                    <Form-item label="内网：" prop="code">
                        <Input v-model="server.intranetIp" placeholder="内网" type="textarea"/>
                    </Form-item>
                    <Form-item label="其他Ip：" prop="code">
                        <Input v-model="server.otherIp" placeholder="其他Ip" type="textarea"/>
                    </Form-item>
                    <Form-item label="固定资产编号：" prop="code">
                        <Input v-model="server.fixedAssetsCode" placeholder="固定资产编号" type="textarea"/>
                    </Form-item>
                    </Card>
                </Col>
                <Col>
                    <Card >
                    <Form-item label="用途：" prop="code">
                        <Input v-model="server.purpose" placeholder="用途" type="textarea"/>
                    </Form-item>
                    <Form-item label="设备类型：" prop="code">
                        <Input v-model="server.equipmentType" placeholder="设备类型" type="textarea"/>
                    </Form-item>
                    <Form-item label="账户：" prop="code">
                        <Input v-model="server.account" placeholder="账户" type="textarea"/>
                    </Form-item>
                    <Form-item label="OS类型：" prop="code">
                        <Input v-model="server.osType" placeholder="OS类型" type="textarea"/>
                    </Form-item>
                    <Form-item label="登录方式：" prop="code">
                        <Input v-model="server.loginType" placeholder="登录方式" type="textarea"/>
                    </Form-item>
                    <Form-item label="备注：" prop="code">
                        <Input v-model="server.remark" placeholder="备注" type="textarea"/>
                    </Form-item>
                    </Card>
                </Col>
            </Row>
            <div class="footer-item">
                <Form-item>
                    <Button type="primary" size="large" @click="handleSubmit('server')" long>
                        提交
                    </Button>
                </Form-item>
            </div>
        </Form>
    </div>
</template>

<script>
    import serverRequest from '../../../app/api/service/server';

    export default {
        name: 'server-edit',
        props: {
            serverId: {
                type: String
            }
        },
        data() {
            return {
                currentId: this.serverId,
                editType: '0',
                server: {
                    id: '',
                    projectName: '',
                    machineryRoom: '',
                    intranetIp: '',
                    otherIp: '',
                    fixedAssetsCode: '',
                    purpose:'',
                    equipmentType:'',
                    account:'',
                    osType:'',
                    loginType:'',
                    remark:''
                },
                ruleValidate: {
                    projectName: [
                        {required: true, message: '名称不能为空', trigger: 'blur'}
                    ]
                },
            };
        },
        methods: {
            init() {
                this.load();
            },
            setCurrentId(id) {
                this.editType = id ? '1' : '0';
                if (id === this.currentId) return;
                this.currentId = id;
            },
            load() {
                let own = this;
                let id = own.currentId;
                if (id && '' != id) {
                    serverRequest.get(id, function (data) {
                        let info = data.code;
                        if (data.data) {
                            own.server = data.data;
                        }
                    });
                } else {
                    var server = {
                        id: '',
                        projectName: '',
                        machineryRoom: '',
                        intranetIp: '',
                        otherIp: '',
                        fixedAssetsCode: '',
                        purpose:'',
                        equipmentType:'',
                        account:'',
                        osType:'',
                        loginType:'',
                        remark:''
                    };
                    this.server = server;
                }
            },
            handleSubmit(projectName) {
                var own = this;
                var id = this.currentId;
                this.$refs[projectName].validate((valid) => {
                    if (valid) {
                        if (id == null){
                            serverRequest.add(own.server,function (data) {
                                var info = data.code;
                                var server = data.data;
                                own.$emit('on-done', own, info, server, own.editType);
                            });
                        }else {
                            serverRequest.update(own.server,function (data) {
                                var info = data.code;
                                var server = data.data;
                                own.$emit('on-done', own, info, server, own.editType);
                            });
                        }
                    }
                });

            }
        },
        watch: {
            serverId(val) {
                this.setCurrentId(val);
                this.init();
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
