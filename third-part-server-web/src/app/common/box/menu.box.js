import boxUtil from '../lib/box.util';

const menuBox = {}

const treeList = [
    {
        icon: 'md-key',
        name: 'system',
        title: '权限管理',
        children: [
            {
                icon: 'md-list',
                name: 'third.system.menu.allList',
                title: '菜单管理'
            },
            {
                icon: 'md-person',
                name: 'third.system.role.list',
                title: '角色管理'
            },
            {
                icon: 'md-people',
                name: 'third.system.admin.list',
                title: '系统用户管理'
            }
        ]
    }
]

menuBox.getMenuTreeList = function (list, type) {
    var menuList = [];
    if (0 === type || '0' === type) {
        menuList = treeList;
    } else {
        var tempList = [];
        var allMap = boxUtil.getMap('id', list, '');
        boxUtil.copyTreeList(menuList, treeList, 'name', 'children', allMap);
    }
    return menuList;
};

export default menuBox;
