import service from '@/libs/service';

let role = {};

role.list = function (query,back) {
    var body = {
        'name': query.name,
        'page':query.page

    };
    service.post('/third/system/role/pageList', body, back);
};

role.addOrUpdate = function (bean, menuIds, back) {
    var body = {'role': bean, 'menuIds': menuIds};
    service.post('/third/system/role/addOrUpdate', body, back);
};

role.get = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/system/role/get', m, back);
};

role.delete = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/system/role/delete', m, back);
};

role.roleMenuList = function (id, back) {
    var m = {
        'id': id
    };
    service.post('/third/system/role/roleMenuList', m, back);
};

role.allList = function (back) {
    var body = {};
    service.post('/third/system/role/allList', body, back);
};

export default role;
