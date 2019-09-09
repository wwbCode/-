<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div>
        <p slot="header" style="height: 30px">

        </p>
        <Form ref="services" :model="services" :rules="ruleValidate" >
            <Row>
                <Col>
                    <Card >
                        <p slot="title">服务器信息</p>
                        <Form-item label="服务名称：" prop="name">
                            <Input v-model="services.name" placeholder="服务名称" type="text"/>
                        </Form-item>
                        <Form-item label="父服务：" prop="code">
                            <Select v-model="services.superId" placeholder="父服务" style="width:120px">
                                <Option v-for="item in servicesNameList" :value="item.id" :key="item.value">
                                    {{ item.name }}
                                </Option>

                            </Select>
                        </Form-item>
                        <Form-item label="供应商：" prop="code">
                            <Select v-model="services.supplierId" placeholder="供应商" style="width:120px">
                                <Option v-for="item in supplierList" :value="item.id" :key="item.value">
                                    {{ item.name }}
                                </Option>
                            </Select>
                        </Form-item>
                        <Form-item label="使用者：" prop="code">
                            <Input v-model="services.user" placeholder="使用者" type="textarea"/>
                        </Form-item>
                        <Form-item label="服务状态：" prop="code">
                            <Input v-model="services.status" placeholder="服务状态" type="textarea"/>
                        </Form-item>
                        <Form-item label="服务类型：" prop="code">
                            <Input v-model="services.type" placeholder="服务类型" type="textarea"/>
                        </Form-item>
                        <Form-item label="开始使用时间：" prop="code">
                            <DatePicker v-model="services.startTime" type="date" :start-date="new Date()" format="yyyy-MM-dd"
                                        placeholder="选择日期"></DatePicker>
                        </Form-item>
                        <Form-item label="服务截至时间：" prop="code">
                            <DatePicker v-model="services.endTime" type="date" :start-date="new Date()" format="yyyy-MM-dd"
                                        placeholder="选择日期"></DatePicker>
                        </Form-item>
                    </Card>
                </Col>
            </Row>
            <div class="footer-item">
                <Form-item>
                    <Button type="primary" size="large" @click="handleSubmit('services')" long>
                        提交
                    </Button>
                </Form-item>
            </div>
        </Form>
    </div>
</template>

<script>
    import servicesRequest from '../../../app/api/service/services';
    import supplierRequest from '../../../app/api/supplier/supplier';
    import dictionary from '../../../app/common/lib/dictionary';
    export default {
        name: 'services-edit',
        props: {
            servicesId: {
                type: String
            }
        },
        data() {
            return {
                currentId: this.servicesId,
                editType: '0',
                services: {
                    id: '',
                    name: '',
                    superId:"",
                    supplierId: '',
                    user: '',
                    operator: '',
                    status: '',
                    type:'',
                    startTime:'',
                    endTime:''
                },
                supplierList:[
                    {
                        id: '',
                        name: ''
                    }
                ],
                servicesNameList:[
                    {
                        id: '',
                        name: ''
                    }
                ],
                ruleValidate: {
                    name: [
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
                    servicesRequest.get(id, function (data) {
                        let info = data.code;
                        if (data.data) {
                            own.services = data.data;
                        }
                    });
                } else {
                    var services = {
                        id: '',
                        name: '',
                        superId:"",
                        supplierId: '',
                        user: '',
                        operator: '',
                        status: '',
                        type:'',
                        startTime:'',
                        endTime:''
                    };
                    this.services = services;
                }
                supplierRequest.supplierNameList(function (data) {
                    if (data.rows){
                        var list = data.rows;
                            dictionary.setName(list, 'common', 'flag', 'flagName');
                            own.supplierList = list;
                    }
                });
                servicesRequest.list(function (data) {
                    if (data.data){
                        var list = data.data;
                        dictionary.setName(list, 'common', 'flag', 'flagName');
                        own.servicesNameList = list;
                    }
                });
            },
            handleSubmit(name) {
                var own = this;
                var id = this.currentId;
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        if (id == null){
                            servicesRequest.add(own.services,function (data) {
                                var info = data.code;
                                var services = data.data;
                                own.$emit('on-done', own, info, services, own.editType);
                            });
                        }else {
                            servicesRequest.update(own.services,function (data) {
                                var info = data.code;
                                var services = data.data;
                                own.$emit('on-done', own, info, services, own.editType);
                            });
                        }
                    }
                });

            }
        },
        watch: {
            servicesId(val) {
                this.setCurrentId(val);
                this.init();
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
