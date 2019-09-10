import service from '@/libs/service';

let broadband = {};

broadband.pageList = function (query,back) {
    var body = {
        'name': query.name,
        'page':query.page

    };
    service.post('/third/service/broadband/pageList', body, back);
};

broadband.add = function (broadband, back) {
   // var body = {'broadband': broadband};
    service.post('/third/service/broadband/add', broadband, back);
};

broadband.update = function (broadband, back) {
    //var body = {'broadband': broadband};
    service.post('/third/service/broadband/edit', broadband, back);
}

broadband.getById = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/broadband/getById', m, back);
};

broadband.delete = function (id, back) {
    var m = {
        "id": id
    };
    service.post('/third/service/broadband/delete', m, back);
};

broadband.list = function (page,back) {
    service.post('/third/service/broadband/listAll', page, back);
};
broadband.getByName = function (name, back) {
    var body = {
        'name':name
    };
    service.post('/third/service/broadband/getByName', body, back);
};


export default broadband;