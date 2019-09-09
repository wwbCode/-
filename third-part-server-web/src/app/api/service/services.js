import service from '@/libs/service';

let services = {};

services.pageList = function (query,back) {
    var body = {
        'name': query.name,
        'supplierId': query.supplierId,
        'page':query.page

    };
    service.post('/third/service/pageList', body, back);
};

services.add = function (bean, back) {
    var body = {
        "id": bean.id,
        "name": bean.name,
        "superId": bean.superId,
        "supplierId": bean.supplierId,
        "user": bean.user,
        "operator": bean.operator,
        "status": bean.status,
        "type": bean.type,
        "startTime": bean.startTime,
        "endTime": bean.endTime
    };
    service.post('/third/service/add', body, back);
};

services.update = function (bean, back) {
    var body = {
        "id": bean.id,
        "name": bean.name,
        "superId": bean.superId,
        "supplierId": bean.supplierId,
        "user": bean.user,
        "operator": bean.operator,
        "status": bean.status,
        "type": bean.type,
        "startTime": bean.startTime,
        "endTime": bean.endTime
    };
    service.post('/third/service/update', body, back);
}

services.get = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/get', m, back);
};

services.delete = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/delete', m, back);
};

services.list = function (back) {
    var body = {};
    service.post('/third/service/list', body, back);
};

export default services;
