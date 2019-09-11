import service from '@/libs/service';

let monitor = {};

monitor.pageList = function (query, back) {
    var body = {
        'name': query.name,
        'page': query.page
    };
    service.post('/third/service/monitor/pageList', body, back);
};

monitor.addOrupdate = function (monitor, back) {
    //var body = {'broadband': broadband};
    service.post('/third/service/monitor/addOrUpdate', monitor, back);
}

monitor.getById = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/monitor/getById', m, back);
};

monitor.delete = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/monitor/delete', m, back);
};

monitor.list = function (page, back) {
    service.post('/third/service/monitor/listAll', page, back);
};

monitor.getByName = function (name, back) {
    var body = {
        'name': name
    };
    service.post('/third/service/monitor/getByName', body, back);
};

export default monitor;