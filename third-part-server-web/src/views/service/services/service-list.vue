<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div>
        <card>
            <Row>
                <div>
                    <Col span="12" offset="12">
                        <div align="right">
                            <Checkbox v-model="expand">展开节点</Checkbox>
                            <Input v-model="query.name" placeholder="请输入名称搜搜..." style="width: 200px"/>
                            <Select v-model="query.supplierId" placeholder="供应商" style="width:120px">
                                <Option v-for="item in supplierList" :value="item.id" :key="item.value">
                                    {{ item.name }}
                                </Option>
                            </Select>
                            <span @click="handleSearch" style="margin: 0 10px;">
                                <Button type="primary" icon="ios-search">搜索</Button>
                            </span>
                            <span @click="handleCancel" style="margin: 0 10px;">
                                <Button type="primary">重置</Button>
                            </span>
                            <span>
                                <Button
                                        @click="add"
                                        type="primary" shape="circle" icon="plus">
                                    新增服务根目录
                                </Button>
                            </span>
                        </div>
                    </Col>
                </div>
            </Row>
        </Card>
        <Card>
            <Row>
                <zk-table
                        ref="table"
                        sum-text="sum"
                        index-text="#"
                        :data="dataList"
                        :columns="columns"
                        :stripe="false"
                        :border="true"
                        :show-header="true"
                        :show-summary="false"
                        :show-row-hover="true"
                        :show-index="true"
                        :tree-type="true"
                        :is-fold="!expand"
                        :expand-type="false"
                        :selection-type="false">
                    <template slot="operate" slot-scope="scope">
                        <div>
                            <Button @click="toDetail(scope.row)" type="warning" shape="circle" size="small">
                                详情
                            </Button>
                            <Button @click="addNodeService(scope.row.id)" type="success" shape="circle" icon="plus"
                                    size="small">
                                添加子服务
                            </Button>
                            <Button @click="edit(scope.row.id)" type="info" shape="circle" size="small">
                                编辑
                            </Button>
                            <Button @click="deleteService(scope.row.id)" type="error" shape="circle" size="small">
                                删除
                            </Button>
                        </div>
                    </template>
                </zk-table>
            </Row>
        </Card>
        <!--<Card>-->
            <!--<Row>-->
                <!--<Page :current.sync="query.page.pageNum"-->
                      <!--:page-size="query.page.pageSize"-->
                      <!--:total.sync="query.page.totalCount"-->
                      <!--@on-change="handlePage"-->
                      <!--@on-page-size-change='handlePageSize'-->
                      <!--show-elevator show-sizer-->
                      <!--align="right">-->
                <!--</Page>-->
            <!--</Row>-->
        <!--</Card>-->
        <Modal
                v-model="editVisible"
                width="460"
                class="form-modal"
                :mask-closable="false"
        >

            <servicesEdit :services-id="editServiceId" @on-done="handleEditDone"></servicesEdit>
            <div slot="footer">

            </div>
        </Modal>
        <Modal
                v-model="addNodeVisible"
                width="460"
                class="form-modal"
                :mask-closable="false"
        >

            <servicesAddNode :services-id="nodeSuperId" @on-done="handleEditDone"></servicesAddNode>
            <div slot="footer">

            </div>
        </Modal>
    </div>
</template>

<script>

    import servicesEdit from './services-edit';
    import servicesAddNode from './services-addNode';
    import servicesRequest from '../../../app/api/service/services';
    import supplierRequest from '../../../app/api/supplier/supplier';
    import prompt from '@/libs/prompt';
    import boxUtil from '../../../libs/boxUtil';

    export default {
        name: 'service-list',
        data () {
            return {
                expand: true,
                root: false,
                editVisible: false,
                addNodeVisible:false,
                editServiceId: '',
                nodeSuperId: '',
                query: {
                    name: '',
                    supplierId: ''
                },
                supplierList:[
                    {
                        id: '',
                        name: ''
                    }
                ],
                columns: [
                    {
                        prop: 'name',
                        label: '服务名称'
                    },
                    {
                        prop: 'supplierName',
                        label: '供应商'
                    },
                    {
                        prop: 'user',
                        label: '使用者'
                    },
                    {
                        prop: 'operator',
                        label: '经办人'
                    },
                    {
                        prop: 'status',
                        label: '服务状态'
                    },
                    {
                        prop: 'type',
                        label: '服务类型'
                    },
                    {
                        prop: 'startTime',
                        label: '开始使用时间'
                    },
                    {
                        prop: 'endTime',
                        label: '服务截至时间'
                    },
                    {
                        label: '操作',
                        type: 'template',
                        template: 'operate',
                        width: '250',
                        headerAlign: 'center'
                    }],
                dataList: [],
                dataMap: {},
                dataMap2: {}
            };
        },
        watch: {
            '$route' (to, from) { // 路由监听，重新获取数据

            }
        },
        components: {
            servicesEdit,
            servicesAddNode
        },
        methods: {
            init () {
                this.querySupplier();
                this.search();
            },
            handleSearch: function () {
                this.search();
            },
            handleCancel() {
                this.query.name = '';
                this.query.supplierId = '';
                this.search();
            },
            // handlePage (value) {
            //     var own = this;
            //     own.query.page.pageNumber = value;
            //     this.search();
            // },
            // handlePageSize (value) {
            //     var own = this;
            //     own.query.page.pageSize = value;
            //     this.search();
            // },
            querySupplier(){
                var own = this;
                var supplier = {
                    id : '',
                    name : '全部'
                }
                supplierRequest.supplierNameList(function (data) {
                    if (data.rows){
                        var list = data.rows;
                        own.supplierList = list;
                        own.supplierList.push(supplier);

                    }
                });

            },
            search () {
                var own = this;
                var query = own.query;
                servicesRequest.queryList(query, function (data) {
                    if (data.data) {
                        var list = data.data;
                        own.dataList = list;
                        own.dataMap = boxUtil.sortList('id', list, 'children');
                        own.dataMap2 = boxUtil.getMap('id', list, 'children');
                    }
                });
            },
            deleteService (id) {
                var own = this;
                this.$Modal.confirm({
                    title: '确定',
                    content: '<p>确定删除此服务？</p><p>如果此服务别有子服务，将会上移到此服务父服务的子服务。</p>',
                    onOk: () => {
                        servicesRequest.delete(id, function (data) {
                            own.search();
                        });
                    },
                    onCancel: () => {

                    }
                });
            },
            openEdit(id) {
                this.editServiceId = id;
                this.editVisible = true;
            },
            openNodeEdit(id){
                this.nodeSuperId = id;
                this.addNodeVisible = true;
            },
            add() {
                this.openEdit();
            },
            edit(id) {
                this.openEdit(id);
            },
            addNodeService(id){
                this.openNodeEdit(id);
            },
            handleEditDone(edit, info, role,editType) {
                if (info == 200) {
                    this.editVisible = false;
                    this.addNodeVisible = false;
                    if('0'==editType){
                        this.handleSearch();
                    }else{

                        this.search();
                    }
                }
                prompt.message(info, '保存成功。', '保存失败！');
            },
            toDetail (services) {
                var own = this;
                var type = services.type
                var query = {'serviceId': services.id};
                if (type === 1) {
                    own.$router.push({
                        name: 'server',
                        query: query
                    });
                }
                if (type === 2) {
                    own.$router.push({
                        name: 'device',
                        query: ss
                    });
                }
            },
        },
        mounted () {
            this.init();
        }
    };
</script>
