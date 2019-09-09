<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div>
        <p slot="header" style="height: 30px">

        </p>
        <Form ref="device" :model="device" :rules="ruleValidate" >
            <Row>
                <Col>
                    <Card >
                        <p slot="title">服务器信息</p>
                        <Form-item label="项目名：" prop="name">
                            <Input v-model="device.machineryRoom" placeholder="机房" type="text"/>
                        </Form-item>
                        <Form-item label="机房：" prop="code">
                            <Input v-model="device.deviceAddress" placeholder="设备地址" type="textarea"/>
                        </Form-item>
                        <Form-item label="内网：" prop="code">
                            <Input v-model="device.managementAddress" placeholder="管理地址" type="textarea"/>
                        </Form-item>
                        <Form-item label="联系方式：" prop="code">
                            <Input v-model="device.deviceType" placeholder="联系方式" type="textarea"/>
                        </Form-item>
                        <Form-item label="型号：" prop="code">
                            <Input v-model="device.type" placeholder="型号" type="textarea"/>
                        </Form-item>
                    </Card>
                </Col>
                <Col>
                    <Card >
                        <Form-item label="联系人：" prop="code">
                            <Input v-model="device.contact" placeholder="联系人" type="textarea"/>
                        </Form-item>
                        <Form-item label="联系方式：" prop="code">
                            <Input v-model="device.contactType" placeholder="联系方式" type="textarea"/>
                        </Form-item>
                        <Form-item label="具体地址：" prop="code">
                            <Input v-model="device.specificAddress" placeholder="具体地址" type="textarea"/>
                        </Form-item>
                        <Form-item label="备注：" prop="code">
                            <Input v-model="device.remark" placeholder="备注" type="textarea"/>
                        </Form-item>
                    </Card>
                </Col>
            </Row>
            <div class="footer-item">
                <Form-item>
                    <Button type="primary" size="large" @click="handleSubmit('device')" long>
                        提交
                    </Button>
                </Form-item>
            </div>
        </Form>
    </div>
</template>

<script>
    import deviceRequest from '../../../app/api/service/device';

    export default {
        name: 'device-edit',
        props: {
            deviceId: {
                type: String
            }
        },
        data() {
            return {
                currentId: this.deviceId,
                editType: '0',
                device: {
                    id: '',
                    machineryRoom: '',
                    deviceAddress: '',
                    managementAddress: '',
                    deviceType: '',
                    type: '',
                    contact:'',
                    contactType:'',
                    specificAddress:'',
                    remark:''
                },
                ruleValidate: {
                    machineryRoom: [
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
                    deviceRequest.get(id, function (data) {
                        let info = data.code;
                        if (data.data) {
                            own.device = data.data;
                        }
                    });
                } else {
                    var device = {
                        id: '',
                        machineryRoom: '',
                        deviceAddress: '',
                        managementAddress: '',
                        deviceType: '',
                        type: '',
                        contact:'',
                        contactType:'',
                        specificAddress:'',
                        remark:''
                    };
                    this.device = device;
                }
            },
            handleSubmit(machineryRoom) {
                var own = this;
                var id = this.currentId;
                this.$refs[machineryRoom].validate((valid) => {
                    if (valid) {
                        if (id == null){
                            deviceRequest.add(own.device,function (data) {
                                var info = data.code;
                                var device = data.data;
                                own.$emit('on-done', own, info, device, own.editType);
                            });
                        }else {
                            deviceRequest.update(own.device,function (data) {
                                var info = data.code;
                                var device = data.data;
                                own.$emit('on-done', own, info, device, own.editType);
                            });
                        }
                    }
                });

            }
        },
        watch: {
            deviceId(val) {
                this.setCurrentId(val);
                this.init();
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
