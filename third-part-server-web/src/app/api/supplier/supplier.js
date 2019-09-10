import service from '@/libs/service';
let supplier = {};

supplier.list = function (page, back) {
    var body = {
        'page': page
    };
    service.post('/third/supplier/listAll', page, back);
};
supplier.getByName = function (name,back) {

    var body={
        'name':name
    };
    var m=body;
    service.post('/third/supplier/getByName',m,back);

};

supplier.getById = function (id,back) {

    var body={
        'id':id
    };
    service.post('/third/supplier/getById',body,back);

};
supplier.delete = function (id,back) {
    var body ={
        'id':id
    };
    var m=body;
    service.post('/third/supplier/delete',m,back)
};

supplier.addOrUpdate=function(supplier,back){
    var body = {
        'object': supplier
    };
    var m ={'body':body};
    service.post('/manage/chemical/product/edit',m,back);

};
supplier.supplierNameList = function (back) {
    var body = {
    };
    service.post('/third/supplier/listAll', body, back);
};
export default supplier;


