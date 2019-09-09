import service from '@/libs/service';

let supplier = {};

supplier.supplierNameList = function (back) {
    var body = {
    };
    service.post('/third/supplier/listAll', body, back);
};

export default supplier;