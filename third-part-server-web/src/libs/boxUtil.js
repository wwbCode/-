import * as base from './base';
import {Map} from './base';

let boxUtil = {};

boxUtil.getMap = function (keyName, list, children) {
    var map = new base.Map();
    if (list) {
        boxUtil.putInMap(map, keyName, list, children);
    }
    return map;
};

boxUtil.putInMap = function (map, keyName, list, children) {
    if (list) {
        var length = list.length;
        for (var i = 0; i < length; i++) {
            // if (list[i].sort === 0) {
            //     list[i].sort = null;
            // }
            var item = list[i];
            var keyValue = item[keyName];
            map.put(keyValue, item);

            if (children && children !== '') {
                var nodes = item[children];
                if (nodes) {
                    boxUtil.putInMap(map, keyName, nodes, children);
                }
            }
        }
    }
};

boxUtil.sortList = function (keyName, list, children) {
    testSort(list);
    return list;
}

function sortItem (a, b) {
    // return (a.sort - b.sort) * -1;
    if (a.sort === 0 || b.sort === 0) {
        return (a.sort - b.sort) * -1;
    } else {
        return (a.sort - b.sort);
    }
};

function testSort (list) {
    list.sort(sortItem);
    for (var i = 0; i < list.length; i++) {
        if (list[i].children !== null && list[i].children.length > 0) {
            testSort(list[i].children);
        }
    }
}

export default boxUtil;
