import service from '@/libs/service';

let menu = {};

menu.allList = function (back) {
    var body = {};
    var m = {'body': body};
    service.post('/third/system/menu/allList', m, back);
}

menu.refresh = function (back) {
    var body = {};
    var m = {'body': body};
    service.post('/third/system/menu/refresh', m, back);
}

menu.updateFlag = function (id, flag, back) {
    var m = {
        'id': id,
        'flag': flag
    };
    service.post('/third/system/menu/updateFlag', m, back);
}

export default menu;
