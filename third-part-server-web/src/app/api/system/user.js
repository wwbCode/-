import service from '@/libs/service';

let user = {};

user.list = function (query, back) {
    var body = {
        'name': query.name,
        'page': query.page
    };
    service.post('/third/system/user/pagelist', body, back);
};

user.addOrUpdate = function (bean, roleIds, back) {
    var body = {'user': bean, 'roleIds': roleIds};
    service.post('/third/system/user/addOrUpdate', body, back);
};

user.get = function (id, back) {
    var body = {
        'id': id
    };
    service.post('/third/system/user/get', body, back);
};

user.delete = function (id, back) {
    var body = {
        'id': id
    };
    service.post('/third/system/user/delete', body, back);
};

user.isExist = function (id, account, back) {
    var body = {
        'id': id,
        'account': account
    };
    service.post('/third/system/user/isExist', body, back);
};

user.getInfo = function (id, back) {
    var body = {
        'id': id
    };
    service.post('/third/system/user/getInfo', body, back);
};

user.updatePassword = function (id, password, back) {
    var body = {
        'id': id,
        'password': password
    };
    service.post('/third/system/user/updatePassword', body, back);
};

export default user;
