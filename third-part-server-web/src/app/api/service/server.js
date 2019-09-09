import service from '@/libs/service';

let server = {};

server.pageList = function (query,back) {
    var body = {
        'projectName': query.name,
        'serviceId': query.serviceId,
        'page':query.page

    };
    service.post('/third/service/server/pageList', body, back);
};

server.add = function (bean, back) {
    var body = {'server': bean};
    service.post('/third/service/server/add', body, back);
};

server.update = function (bean, back) {
    var body = {'server': bean};
    service.post('/third/service/server/update', body, back);
}

server.get = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/server/get', m, back);
};

server.delete = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/server/delete', m, back);
};

server.list = function (back) {
    var body = {};
    service.post('/third/service/server/list', body, back);
};

export default server;
