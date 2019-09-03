import * as base from '../lib/base';
import boxUtil from '../lib/box.util';
import auth from './auth';

let permission = {};
let map = new base.Map();

permission.setPermission = function (list) {
    map = boxUtil.getMap('id', list, '');
};

permission.has = function (id) {
    var isSuperAdmin = auth.isSuperAdmin();
    var has = map.containsKey(id);
    return has || isSuperAdmin;
};

export default permission;
