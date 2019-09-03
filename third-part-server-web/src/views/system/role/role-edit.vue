<style lang="less">
    @import '../../../styles/common.less';
</style>

<template>
    <div>
        <p slot="header" style="height: 30px">

        </p>
        <Form ref="role" :model="role" :rules="ruleValidate" label-position="left">
            <Row>
                <Col span="11">
                    <Card style="height: 380px">
                        <p slot="title">角色信息</p>
                        <Form-item label="角色名称：" prop="name">
                            <Input v-model="role.name" placeholder="角色名称" type="text"/>
                        </Form-item>
                        <Form-item label="简介：" prop="code">
                            <Input v-model="role.introduce" placeholder="介绍" type="textarea"/>
                        </Form-item>
                        <Form-item label="状态：" prop="flag">
                            <RadioGroup v-model="role.flag">
                                <Radio label="1">启用</Radio>
                                <Radio label="0">禁用</Radio>
                            </RadioGroup>
                        </Form-item>
                    </Card>
                </Col>
                <Col span="12" offset="1">
                    <Card dis-hover style="height: 380px">
                        <p slot="title">角色权限</p>
                        <Scroll>
                            <Tree :data="menuTreeList" show-checkbox multiple></Tree>
                        </Scroll>
                    </Card>
                </Col>
            </Row>
            <div class="footer-item">
                <Form-item>
                    <Button type="primary" size="large" @click="handleSubmit('role')" long>
                        提交
                    </Button>
                </Form-item>
            </div>
        </Form>
    </div>
</template>

<script>
    import roleRequest from '../../../app/api/system/role';
    import menuRequest from '../../../app/api/system/menu';
    import * as base from '../../../app/common/lib/base';
    import boxUtil from '../../../app/common/lib/box.util';

    export default {
        name: 'role-edit',
        props: {
            roleId: {
                type: String
            }
        },
        data() {
            return {
                currentId: this.roleId,
                editType: '0',
                role: {
                    id: '',
                    name: '',
                    introduce: '',
                    flag: '1',
                    grade: 1,
                    rank: 0
                },
                ruleValidate: {
                    name: [
                        {required: true, message: '名称不能为空', trigger: 'blur'}
                    ]
                },
                columns: [
                    {
                        prop: 'name',
                        label: '节点'
                    },
                    {
                        label: '选者',
                        type: "template",
                        template: "select",
                    }],
                menuTreeList: [],
                roleMenuList: []
            };
        },
        methods: {
            init() {
                this.load();
                this.loadRoleMenuList();
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
                    roleRequest.get(id, function (data) {
                        let info = data.code;
                        if (data.data) {
                            own.role = data.data;
                        }
                    });
                } else {
                    var role = {
                        id: '',
                        name: '',
                        introduce: '',
                        flag: '1',
                        grade: 1,
                        rank: 0
                    };
                    this.role = role;
                }
            },
            loadMenuTree() {
                var own = this;
                menuRequest.allList(function (data) {
                    var info = data.info;
                    if (data.data) {
                        var list = data.data;
                        own.setMenuTree(list);

                    }
                });
            },
            loadRoleMenuList() {
                var own = this;
                let id = own.currentId;
                var treeList = this.menuTreeList;
                var map = boxUtil.getMap('id', treeList, 'children');

                if (id && '' != id) {
                    roleRequest.roleMenuList(id, function (data) {
                        let info = data.info;
                        if (data.data) {
                            var list = data.data;
                            own.roleMenuList = list;

                            //
                            // var length = list.length;
                            // for (var i = 0; i < length; i++) {
                            //     var roleMenu = list[i];
                            //     var menuId = roleMenu.menuId;
                            //     var m = map.get(menuId);
                            //
                            //     if (m && !m.children) {
                            //         m['checked'] = true;
                            //     }
                            // }

                        }
                        own.loadMenuTree();
                    });
                } else {
                    own.loadMenuTree();
                }

            },
            setMenuTree(list) {
                var own = this;
                var roleMenuList = own.roleMenuList;
                var roleMenuMap = boxUtil.getMap('menuId', roleMenuList);

                var baseList = [];
                if (list) {
                    var map = new base.Map();
                    var length = list.length;
                    for (var i = 0; i < length; i++) {
                        var menu = list[i];
                        var id = menu.id;
                        menu.title = menu.name;
                        map.put(id, menu);
                        if ('0' == menu.superId) {
                            baseList.push(menu);
                        }
                    }
                    for (var i = 0; i < length; i++) {
                        var menu = list[i];
                        var superId = menu.superId;
                        var superMenu = map.get(superId);
                        if (superMenu) {
                            superMenu.expand = true;
                            if (!superMenu.children) {
                                superMenu.children = [];
                            }
                            superMenu.children.push(menu);
                        }
                    }


                    for (var i = 0; i < length; i++) {
                        var menu = list[i];
                        var id = menu.id;
                        var roleMenu = roleMenuMap.get(id);
                        if (roleMenu && !menu.children) {
                            menu.checked = true;
                        }
                    }
                }
                this.menuTreeList = baseList;
            },
            getSelectedMenuIds() {
                var own = this;

                var treeList = this.menuTreeList;
                var map = boxUtil.getMap('id', treeList, 'children');

                var list = map.values();

                var idMap = new base.Map();
                var ids = [];
                var length = list.length;
                for (var i = 0; i < length; i++) {
                    var menu = list[i];
                    if (menu.checked) {
                        idMap.put(menu.id, menu.id);
                        own.putSuperId(idMap, map, menu);
                    }
                }
                ids = idMap.values();
                return ids;
            },
            putSuperId(idMap, allMap, menu) {
                var superId = menu.superId;
                var superMenu = allMap.get(superId);
                if (superMenu) {
                    idMap.put(superId, superId);
                    this.putSuperId(idMap, allMap, superMenu);
                }
            },
            handleSelect(menu) {

            },
            handleSubmit(name) {
                var own = this;
                var menuIds = this.getSelectedMenuIds();
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        roleRequest.addOrUpdate(own.role, menuIds, function (data) {
                            //own.setVisible(false);
                            var info = data.code;
                            var c = data.data;
                            own.$emit('on-done', own, info, c, own.editType);
                        });
                    }
                });

            }
        },
        watch: {
            roleId(val) {
                this.setCurrentId(val);
                this.init();
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
