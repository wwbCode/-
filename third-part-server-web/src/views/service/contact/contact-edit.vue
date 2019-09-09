<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div>
        <p slot="header" style="height: 30px">

        </p>
        <Form ref="contact" :model="contact" :rules="ruleValidate" >
            <Row>
                <Col>
                    <Card >
                        <p slot="title">服务器信息</p>
                        <Form-item label="供应商：" prop="name">
                            <Input v-model="contact.supplier" placeholder="供应商" type="text"/>
                        </Form-item>
                        <Form-item label="姓名：" prop="code">
                            <Input v-model="contact.name" placeholder="姓名" type="textarea"/>
                        </Form-item>
                        <Form-item label="岗位：" prop="code">
                            <Input v-model="contact.position" placeholder="岗位" type="textarea"/>
                        </Form-item>
                        <Form-item label="手机：" prop="code">
                            <Input v-model="contact.phone" placeholder="手机" type="textarea"/>
                        </Form-item>
                        <Form-item label="固话：" prop="code">
                            <Input v-model="contact.fixedLine" placeholder="固话" type="textarea"/>
                        </Form-item>
                    </Card>
                </Col>
                <Col>
                    <Card >
                        <Form-item label="邮箱：" prop="code">
                            <Input v-model="contact.mail" placeholder="邮箱" type="textarea"/>
                        </Form-item>
                        <Form-item label="微信：" prop="code">
                            <Input v-model="contact.weChat" placeholder="微信" type="textarea"/>
                        </Form-item>
                        <Form-item label="QQ：" prop="code">
                            <Input v-model="contact.qq" placeholder="QQ" type="textarea"/>
                        </Form-item>
                        <Form-item label="办公地址：" prop="code">
                            <Input v-model="contact.address" placeholder="办公地址" type="textarea"/>
                        </Form-item>
                        <Form-item label="备注：" prop="code">
                            <Input v-model="contact.remark" placeholder="备注" type="textarea"/>
                        </Form-item>
                    </Card>
                </Col>
            </Row>
            <div class="footer-item">
                <Form-item>
                    <Button type="primary" size="large" @click="handleSubmit('contact')" long>
                        提交
                    </Button>
                </Form-item>
            </div>
        </Form>
    </div>
</template>

<script>
    import contactRequest from '../../../app/api/service/contact';

    export default {
        name: 'contact-edit',
        props: {
            contactId: {
                type: String
            }
        },
        data() {
            return {
                currentId: this.contactId,
                editType: '0',
                contact: {
                    id: '',
                    supplier: '',
                    name: '',
                    position: '',
                    phone: '',
                    fixedLine: '',
                    mail:'',
                    weChat:'',
                    qq:'',
                    address:'',
                    remark:''
                },
                ruleValidate: {
                    name: [
                        {required: true, message: '名称不能为空', trigger: 'blur'}
                    ]
                },
                // columns: [
                //     {
                //         prop: 'name',
                //         label: '节点'
                //     },
                //     {
                //         label: '选者',
                //         type: "template",
                //         template: "select",
                //     }],
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
                    contactRequest.get(id, function (data) {
                        let info = data.code;
                        if (data.data) {
                            own.contact = data.data;
                        }
                    });
                } else {
                    var contact = {
                        id: '',
                        supplier: '',
                        name: '',
                        position: '',
                        phone: '',
                        fixedLine: '',
                        mail:'',
                        weChat:'',
                        qq:'',
                        address:'',
                        remark:''
                    };
                    this.contact = contact;
                }
            },
            handleSubmit(projectName) {
                var own = this;
                var id = this.currentId;
                this.$refs[projectName].validate((valid) => {
                    if (valid) {
                        if (id == null){
                            contactRequest.add(own.contact,function (data) {
                                var info = data.code;
                                var contact = data.data;
                                own.$emit('on-done', own, info, contact, own.editType);
                            });
                        }else {
                            contactRequest.update(own.contact,function (data) {
                                var info = data.code;
                                var contact = data.data;
                                own.$emit('on-done', own, info, contact, own.editType);
                            });
                        }
                    }
                });

            }
        },
        watch: {
            contactId(val) {
                this.setCurrentId(val);
                this.init();
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
