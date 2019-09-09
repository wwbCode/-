import service from '@/libs/service';

let device = {};

device.pageList = function (query,back) {
    var body = {
        'machineryRoom': query.name,
        'page':query.page

    };
    service.post('/third/service/device/pageList', body, back);
};

device.add = function (bean, back) {
    var body = {'device': bean};
    service.post('/third/service/device/add', body, back);
};

device.update = function (bean, back) {
    var body = {'device': bean};
    service.post('/third/service/device/update', body, back);
}

device.get = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/device/get', m, back);
};

device.delete = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/device/delete', m, back);
};

device.list = function (back) {
    var body = {};
    service.post('/third/service/device/list', body, back);
};

export default device;
