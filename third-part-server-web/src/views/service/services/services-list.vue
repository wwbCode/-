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
                <span style="float: right">
                    <Button  @click="add" type="primary" shape="circle" icon="md-add">
                        新增服务
                    </Button>
                </span>
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
                width="860"
                class="form-modal"
                :mask-closable="false"
        >

            <servicesEdit :services-id="editServiceId" @on-done="handleEditDone"></servicesEdit>
            <div slot="footer">

            </div>
        </Modal>
    </div>
</template>

<script>
    import servicesEdit from './services-edit';
    import servicesRequest from '../../../app/api/service/services';
    import supplierRequest from '../../../app/api/supplier/supplier';
    import dictionary from '../../../app/common/lib/dictionary';
    import prompt from '@/libs/prompt';

    export default {
        name: 'services-list',
        data() {
            return {
                editVisible: false,
                editServiceId: '',
                query: {
                    name: '',
                    supplierId: '',
                    page: {
                        pageSize: 10,
                        pageNum: 1,
                        totalCount: 0
                    }
                },
                supplierList:[
                    {
                        id: '',
                        name: ''
                    }
                ],
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
                        title: '服务名称',
                        render: (h, params) => {
                            return h('a', {
                                attrs: {
                                    title: params.row.name
                                },
                                on: {
                                    click: () => {
                                        this.toDetail(params.row.id, params.row.type);
                                    }
                                }
                            }, params.row.name);
                        }

                    },
                    {
                        key: 'supplierName',
                        title: '供应商'
                    },
                    {
                        key: 'user',
                        title: '使用者'
                    },
                    {
                        key: 'operator',
                        title: '经办人'
                    },
                    {
                        key: 'status',
                        title: '服务状态'
                    },
                    {
                        key: 'type',
                        title: '服务类型'
                    },
                    {
                        key: 'startTime',
                        title: '开始使用时间'
                    },
                    {
                        key: 'endTime',
                        title: '服务截至时间'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
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
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.delete(params.row.id);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                    }
                ],
                dataList: []
            };
        },
        components: {servicesEdit},
        methods: {
            init() {
                this.querySupplier();
                this.search();
            },
            handleSearch: function () {
                this.query.page.pageNum = 1;
                this.search();
            },
            handleCancel() {
                this.query.page.pageNum = 1;
                this.query.name = '';
                this.search();
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
            querySupplier(){
                var own = this;
                var supplier = {
                    id : '',
                    name : '全部'
                }
                supplierRequest.supplierNameList(function (data) {
                    if (data.rows){
                        var list = data.rows;
                        dictionary.setName(list, 'common', 'flag', 'flagName');
                        own.supplierList = list;
                        own.supplierList.push(supplier);

                    }
                });

            },
            search() {
                var own = this;
                var query = own.query;

                servicesRequest.pageList(query,function (data) {
                    if (data.data.list) {
                        var list = data.data.list;
                        dictionary.setName(list, 'common', 'flag', 'flagName');
                        own.dataList = list;
                    }
                    var totalCount = data.data.total;
                    own.query.page.totalCount = totalCount;
                });

            },
            openEdit(id) {
                this.editServiceId = id;
                this.editVisible = true;
            },
            add() {
                this.openEdit();
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
                        servicesRequest.delete(id, function (data) {
                            own.search();
                        });
                    },
                    onCancel: () => {

                    }
                });
            },
            handleEditDone(edit, info, role,editType) {
                if (info == 200) {
                    this.editVisible = false;
                    if('0'==editType){
                        this.handleSearch();
                    }else{
                        this.search();
                    }
                }
                prompt.message(info, '保存成功。', '保存失败！');
            },
            toDetail (id, type) {
                var own = this;
                var query = {'id': ''};
                if (type === 1) {
                    own.$router.push({
                        name: 'server'
                        // query: query
                    });
                }
                if (type === 2) {
                    own.$router.push({
                        name: 'device',
                        query: query
                    });
                }
            },
        },
        mounted() {
            this.init();
        }
    };
</script>
