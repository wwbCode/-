import * as base from './base';

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
            var item = list[i];
            var keyValue = item[keyName];
            map.put(keyValue, item);

            if (children && '' != children) {
                var nodes = item[children];
                if (nodes) {
                    boxUtil.putInMap(map, keyName, nodes, children);
                }
            }
        }
    }
};

boxUtil.getTreeList = function (key, superKey, rootSuperKeyValue, list, children) {
    var baseTreeList = [];
    var map = boxUtil.getMap(key, list, '');
    if (list) {
        var length = list.length;
        for (var i = 0; i < length; i++) {
            var item = list[i];
            var id = item[key];
            var superId = item[superKey];

            var superItem = map.get(superId);
            if (superItem) {
                var superItemChildren = item[children];
                if (superItemChildren) {
                    superItemChildren = [];
                    item[children] = superItemChildren;
                }
                superItemChildren.push(item);
            } else if (rootSuperKeyValue == superId) {
                baseTreeList.push(item);
            }
        }
    }
    return baseTreeList;
};

boxUtil.copyTreeList = function (toTreeList, formTreeList, key, children, keyMap) {
    if (toTreeList && formTreeList) {
        var length = formTreeList.length;
        for (var i = 0; i < length; i++) {
            var fromItem = formTreeList[i];

            var id = fromItem[key];
            var copyKey = keyMap.get(id);
            if (copyKey) {
                var toItem = Object.assign({}, fromItem);
                toTreeList.push(toItem);
                if (children && '' != children) {
                    var fromNodes = fromItem[children];
                    if (fromNodes) {
                        toItem[children] = [];
                        var toNodes = toItem[children];
                        boxUtil.copyTreeList(toNodes, fromNodes, key, children, keyMap);
                    }
                }
            }
        }
    }
};

export default boxUtil;
