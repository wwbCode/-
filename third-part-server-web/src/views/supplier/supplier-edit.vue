<style lang="less">
    @import '../../styles/common.less';
</style>
<template>
    <div>
        <p slot="header" style="height: 10px"></p>
        <div>
            <Form ref="supplier" :model="supplier" :rules="ruleValidate" label-position="left">

                <row>
                    <Col span="12">
                        <Card>
                        <p slot="title">供应商信息</p>
                            <Form-item label="供应商：" prop="name">
                                <!--<Select v-model="supplier.supplierId">
                                    <Option v-for="item in categoryList" :value="item.id" :key="item.id">{{item.name}}</Option>
                                </Select>-->
                                <Input v-model="supplier.name"  type="text"/>
                            </Form-item>
                            <Form-item label="电话：" prop="tel">
                                <Input v-model="supplier.tel" placeholder="电话" type="text"/>
                            </Form-item>
                            <Form-item label="地址：" prop="address">
                                <Input v-model="supplier.address" placeholder="地址" type="text"/>
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
                                <RadioGroup v-model="supplier.flag">
                                    <Radio label="0">禁用</Radio>
                                    <Radio label="1">启用</Radio>
                                </RadioGroup>
                            </Form-item>
                            <Form-item label="是否显示在首页导航栏分类下：" prop="flag">
                                <RadioGroup v-model="supplier.isTop">
                                    <Radio label="0">否</Radio>
                                    <Radio label="1">是</Radio>
                                </RadioGroup>
                            </Form-item>
                        </Card>
                    </Col>
                    <Col span="10" offset="1">
                        <Card>
                            <Form-item label="当前使用人：" prop="user">
                                <Input v-model="supplier.user" placeholder="英文名" type="text"/>
                            </Form-item>
                            <Form-item label="经办人：" prop="operator">
                                <Input v-model="supplier.operator" placeholder="化学名,化学表达式" type="text"/>
                            </Form-item>
                            <Form-item label="备注：" prop="remark">
                                <Input v-model="supplier.remark" type="textarea" placeholder="描述"></Input>
                            </Form-item>
                        </Card>
                    </Col>
                </row>

                <div class="footer-item">
                    <Form-item>
                        <Button type="primary" @click="handleSubmit('supplier')">
                            提交
                        </Button>
                    </Form-item>
                </div>
            </Form>
        </div>
    </div>
</template>

<script>
    import supplierRequest from '@/app/api/supplier/supplier';
    export default {
        name: 'supplier-edit',
        props: {
            supplierId: {
                type: String
            }


        },
        data () {
            return {
                currentId: this.supplierId,
                supplier: {
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
                    remark:''
                },
                categoryList: [],
                ruleValidate: {
                    name: [{required: true, message: '供应商不能为空', trigger: 'blur'}],
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
                //         value: 'T',
                //         label: '吨'
                //     },
                //     {
                //         value: 'KG',
                //         label: '千克'
                //     },
                //     {
                //         value: 'G',
                //         label: '克'
                //     },
                //     {
                //         value: 'M³',
                //         label: '立方米'
                //     },
                //     {
                //         value: 'DM³',
                //         label: '立方分米'
                //     },
                //     {
                //         value: 'CM³',
                //         label: '立方厘米'
                //     },
                //     {
                //         value: 'MM³',
                //         label: '立方毫米'
                //     },
                //     {
                //         value: 'L',
                //         label: '升'
                //     },
                //     {
                //         value: 'DL',
                //         label: '分升'
                //     },
                //     {
                //         value: 'CL',
                //         label: '厘升'
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
                this.$refs['supplier'].resetFields();
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
                    supplierRequest.getById(id, function (data) {
                             let body = data.data
                            own.supplier = body;
                            debugger;
                    });
                } else {
                    var supplier = {
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
                        remark:''
                    };
                    this.supplier = supplier;
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
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        supplierRequest.addOrUpdate(own.supplier, function (data) {
                            var info = data.info;
                            var supplier = data.rows.object;
                            var supplier = data;
                            own.$emit('on-done', own, info, supplier);
                        });
                    }
                });
            }
        },
        watch: {
            supplierId (val) {
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
