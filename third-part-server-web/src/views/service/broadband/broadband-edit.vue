<style lang="less">
    @import '../../../styles/common.less';
</style>
<template>
    <div>
        <p slot="header" style="height: 10px"></p>
        <div>
            <Form ref="broadband" :model="broadband" :rules="ruleValidate" label-position="left">
                <row>
                    <Col span="12">
                        <Card>
                            <p slot="title">供应商信息</p>
                            <Form-item label="供应商：" prop="operator">
                                <!--<Select v-model="supplier.supplierId">
                                    <Option v-for="item in categoryList" :value="item.id" :key="item.id">{{item.name}}</Option>
                                </Select>-->
                                <Input v-model="broadband.operator" placeholder="经办人"  type="text"/>
                            </Form-item>
                            <Form-item label="电话：" prop="tel">
                                <Input v-model="broadband.tel" placeholder="电话" type="text"/>
                            </Form-item>
                            <Form-item label="地址：" prop="address">
                                <Input v-model="broadband.address" placeholder="地址" type="text"/>
                            </Form-item>
                            <Form-item label="系统：" prop="system">
                                <Input v-model="broadband.system" placeholder="系统" type="text"/>
                            </Form-item>
                            <!--<Form-item label="单位：" prop="name">
                                <Select v-model="supplier.unit">
                                    <Option v-for="item in unitList" :value="item.value" :key="item.value">
                                        {{ item.label }}
                                    </Option>
                                </Select>
                            </Form-item>-->
                            <!-- <Poptip trigger="focus" title="提示" content="数字越大越靠前，最大为10">
                                 <Form-item label="排序数字：" prop="sort">
                                     <Input v-model="supplier.sort" number placeholder="排序数字" type="text"/>
                                 </Form-item>
                             </Poptip>-->
                            <Form-item label="状态：" prop="flag">
                                <RadioGroup v-model="broadband.flag">
                                    <Radio label="0">禁用</Radio>
                                    <Radio label="1">启用</Radio>
                                </RadioGroup>
                            </Form-item>
                            <Form-item label="是否显示在首页导航栏分类下：" prop="flag">
                                <RadioGroup v-model="broadband.isTop">
                                    <Radio label="0">否</Radio>
                                    <Radio label="1">是</Radio>
                                </RadioGroup>
                            </Form-item>
                        </Card>
                    </Col>
                    <Col span="10" offset="1">
                        <Card>
                            <Form-item label="当前使用人：" prop="agent">
                                <Input v-model="broadband.agent" placeholder="使用人" type="text"/>
                            </Form-item>
                            <Form-item label="经理人：" prop="manager">
                                <Input v-model="broadband.manager" placeholder="经理人" type="text"/>
                            </Form-item>
                            <Form-item label="备注：" prop="remark">
                                <Input v-model="broadband.remark" type="textarea" placeholder="描述"/>
                            </Form-item>
                        </Card>
                    </Col>
                </row>

                <div class="footer-item">
                    <Form-item>
                        <Button type="primary" @click="handleSubmit('broadband')">
                            提交
                        </Button>
                    </Form-item>
                </div>
            </Form>
        </div>
    </div>
</template>

<script>
    import broadbandRequest from '@/app/api/service/broadband';
    export default {
        name: 'broadband-edit',
        props: {
            broadbandId: {
                type: String
            }
        },
        data () {
            return {
                currentId: this.broadbandId,
                broadband: {
                    id: '',
                    categoryId: '',
                    code: '',
                    unit: '',
                    flag: '0',
                    sort: '0',
                    isTop: '',
                    operator:'',
                    name:'',
                    tel:'',
                    address:'',
                    user:'',
                    remark:'',
                    system:'',
                    chargeMode:'',
                    monthlyFee:'',
                    fee:'',
                    manager:'',
                    managerTel:'',
                    paymentMethod:'',
                    collectionBank:'',
                    collectionbankAcc:'',
                    company:'',
                    businessLicense:'',
                },
                categoryList: [],
                ruleValidate: {
                    operator: [{required: true, message: '供应商不能为空', trigger: 'blur'}],
                    tel: [{required: true, message: '电话不能为空', trigger: 'blur'}],
                    // name: [{required: true, message: '名称不能为空', trigger: 'blur'}]
                },
                columns: [
                    {
                        prop: 'name',
                        label: '节点'
                    }
                ],
                // unitList: [
                //     {
                //         value: 'G',
                //         label: '克'
                //     },
                //     {
                //         value: 'ML',
                //         label: '毫升'
                //     }
                // ]
            };
        },
        methods: {
            init () {
                this.$refs['broadband'].resetFields();
                this.load();
            },
            setCurrentId (id) {
                if (id === this.currentId) return;
                this.currentId = id;
            },
            load () {
                let own = this;
                let id = own.currentId;
                if (id && '' !== id) {
                    broadbandRequest.getById(id, function (data) {
                        debugger;
                        let body = data.data;
                        own.broadband = body;
                        debugger;
                    });
                } else {
                    var broadband = {
                        id: '',
                        categoryId: '',
                        code: '',
                        unit: '',
                        flag: '0',
                        sort: '0',
                        isTop: '',
                        operator:'',
                        name:'',
                        tel:'',
                        address:'',
                        user:'',
                        remark:'',
                        system:'',
                        chargeMode:'',
                        monthlyFee:'',
                        fee:'',
                        manager:'',
                        managerTel:'',
                        paymentMethod:'',
                        collectionBank:'',
                        collectionbankAcc:'',
                        company:'',
                        businessLicense:'',

                    };
                    this.broadband = broadband;
                }

                // 加载类别列表
                // supplierRequest.getById(id, function (data) {
                //     let body = data.data;
                //     var tempList = [];
                //     tempList[0] = {id: '', name: ''};
                //     if (body && body.list) {
                //         for (var i = 0; i < body.list.length; i++) {
                //             var item = body.list[i];
                //             tempList.push({
                //                 id: item.id,
                //                 name: item.name
                //             });
                //         }
                //         own.categoryList = tempList;
                //     }
                // });
            },
            handleSubmit (name) {
                var own = this;
                let id =this.currentId;
                debugger;
                alert("确定提交？");
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        if(id!=null){
                            broadbandRequest.update(own.broadband, function (data) {
                                var info = data.code;
                                debugger;
                                //var supplier = data.rows.object;
                                var broadband = data.code;
                                own.$emit('on-done', own, info, broadband);
                            });
                        }
                        else {
                            debugger;
                            broadbandRequest.add(own.broadband, function (data) {
                                // debugger;
                                // var info = data.code;
                                // debugger;
                                // //var supplier = data.rows.object;
                                // var supplier = data.code;
                                // own.$emit('on-done', own, info, supplier);
                                var info = data.code;
                                var broadband = data.code;
                                own.$emit('on-done',own,info,broadband);
                            });
                        }
                    }
                });
            }
        },
        watch: {
            broadbandId (val) {
                this.setCurrentId(val);
                this.init();
            }
        },
        mounted () {
            this.init();
        }
    };
</script>

<style scoped>

</style>
