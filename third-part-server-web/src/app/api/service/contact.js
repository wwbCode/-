import service from '@/libs/service';

let contact = {};

contact.pageList = function (query,back) {
    var body = {
        'name': query.name,
        'page':query.page

    };
    service.post('/third/service/contact/pageList', body, back);
};

contact.add = function (bean, back) {
    var body = {'contact': bean};
    service.post('/third/service/contact/add', body, back);
};

contact.update = function (bean, back) {
    var body = {'contact': bean};
    service.post('/third/service/contact/update', body, back);
}

contact.get = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/contact/get', m, back);
};

contact.delete = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/contact/delete', m, back);
};

contact.list = function (back) {
    var body = {};
    service.post('/third/service/contact/list', body, back);
};

export default contact;
