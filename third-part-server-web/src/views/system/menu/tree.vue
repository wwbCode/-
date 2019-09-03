<style lang="less">

</style>

<template>
    <div>
        <Row :gutter="10">
            <Col span="6">
                <Card>
                    <p slot="title">
                        <Icon type="ios-switch" />
                        点击查看详情
                    </p>
                    <slot name="top">
                        <Button @click="refreshAllMenu" type="default">刷新整个菜单</Button>
                    </slot>
                    <Card>
                        <Scroll>
                            <Tree :data="menuList" @on-select-change="onSelectChange"></Tree>
                        </Scroll>
                    </Card>
                </Card>
            </Col>
            <Col span="18">
                <Card>
                    <p slot="title">
                        <Icon type="ios-switch" />
                        菜单详情
                    </p>
                    <div style="margin-top: 25px;">
                        <p><span>名称：</span>{{menu.name}}</p>
                    </div>
                    <div style="margin-top: 25px;">
                        <p><span>编号：</span>{{menu.id}}</p>
                    </div>
                    <div style="margin-top: 25px;">
                        <p>
                            <span>是否启用：</span>
                            <RadioGroup v-model="menu.flag" @on-change="flagChange">
                                <Radio label="1">启用</Radio>
                                <Radio label="0">停用</Radio>
                            </RadioGroup>
                        </p>
                    </div>
                    <div style="margin-top: 25px;">
                        <p><span>创建时间：</span>{{menu.createTime}}</p>
                    </div>
                    <div style="margin-top: 25px;">
                        <p><span>类别：</span>{{menu.type==0?'菜单':'按钮'}}</p>
                    </div>
                </Card>
            </Col>
        </Row>
    </div>
</template>

<script>
    import prompt from '@/libs/prompt';
    import * as base from '../../../app/common/lib/base';
    import menuRequest from '../../../app/api/system/menu';

    export default {
        components: {},
        data() {
            return {
                shrink: false,
                menuTheme: 'dark',
                openNames: [],
                menuList: [],
                menu: {
                    id: '',// 菜单id
                    superId: '',// 上级菜单id
                    name: '',// 菜单名称
                    flag: '1',// 是否启用 1：启用 0：停用
                    introduce: '',// 功能描述
                    createTime: '',// 创建时间
                    rank: '',// 菜单排序
                    type: ''// 菜单类别 0：菜单 1：按钮
                }
            };
        },
        methods: {
            init() {
                this.load();
            },
            load() {
                var own = this;
                menuRequest.allList(function (data) {
                    var info = data.info;
                    if (data.data) {
                        var list = data.data;
                        own.setMenuTree(list);
                    }
                });
            },
            setMenuTree(list) {
                var baseList = [];
                if (list) {
                    var map = new base.Map();
                    var length = list.length;
                    for (var i = 0; i < length; i++) {
                        var menu = list[i];
                        menu.title = menu.name;
                        map.put(menu.id, menu);
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
                }
                this.menuList = baseList;
            },
            refreshAllMenu() {
                var own = this;
                menuRequest.refresh(function (data) {
                    var info = data.code;
                    prompt.message(info, "刷新成功");
                    own.load();
                });
            },
            onSelectChange(menus) {
                if (menus && menus.length > 0) {
                    this.menu = menus[0];
                }
            },
            flagChange(flag) {
                var own = this;
                let id = own.menu.id;
                if (id && '' != id) {
                    menuRequest.updateFlag(id, flag, function (data) {
                        var info = data.code;
                        if (info && info == 200) {
                            own.menu.flag = flag;
                        }
                    });
                }
            }
        },
        mounted() {
            this.init();
        }
    };
</script>
