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

supplier.update=function(supplier,back){

        service.post('/third/supplier/edit',supplier,back);

};
supplier.add = function (suppplier,back){

  service.post('/third/supplier/add',suppplier,back)
};
supplier.supplierNameList = function (back) {
    var body = {
    };
    service.post('/third/supplier/listAll', body, back);
};
export default supplier;


