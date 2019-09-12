import * as base from './base';
import env from '../../build/env';
let dictionary = {};

const map = new base.Map();

function put (category, property, value, name) {
    var propertyMap = getMap(category, property);
    propertyMap.put(value, name);
}

function getMap (category, property) {
    var categoryMap = map.get(category);
    if (!categoryMap) {
        categoryMap = new base.Map();
        map.put(category, categoryMap);
    }

    var propertyMap = categoryMap.get(property);
    if (!propertyMap) {
        propertyMap = new base.Map();

        categoryMap.put(property, propertyMap);
    }
    return propertyMap;
}

dictionary.getName = function (category, property, value) {
    var categoryMap = map.get(category);
    var propertyMap = (categoryMap) ? categoryMap.get(property) : null;
    var name = (propertyMap) ? propertyMap.get(value) : '';
    return name;
};

dictionary.setName = function (list, category, valueProperty, nameProperty, children) {
    if (list) {
        var categoryMap = map.get(category);
        var propertyMap = (categoryMap) ? categoryMap.get(valueProperty) : null;

        var length = list.length;
        for (var i = 0; i < length; i++) {
            var item = list[i];
            var value = item[valueProperty];
            var name = (propertyMap) ? propertyMap.get(value) : '';
            item[nameProperty] = name;
            if (children && children != '') {
                var nodes = item[children];
                if (nodes) {
                    dictionary.setName(nodes, category, valueProperty, nameProperty, children);
                }
            }
        }
    }
};

initMap();

function initMap () {

    put('serviceType', 'type', '1', '服务器');
    put('serviceType', 'type', '2', '设备');
    put('serviceType', 'type', '3', '联络人');

    put('articleCategory', 'type', '0', '未分类');
    put('articleCategory', 'type', '1', '广告');
    put('articleCategory', 'type', '2', '公告');
    put('articleCategory', 'type', '3', '资讯');
    put('articleCategory', 'type', '4', '视频');

    put('common', 'flag', '0', '禁用');
    put('common', 'flag', '1', '启用');

    // 留言状态
    put('option', 'status', '0', '未处理');
    put('option', 'status', '1', '已处理');
    // 状态
    put('common', 'status', '0', '禁用');
    put('common', 'status', '1', '启用');
}

dictionary.obsUrl = getObsUrl();

function getObsUrl () {
    var url = '';
    if (env === 'development') {
        url = 'http://jyfresource.obs.cn-east-2.myhuaweicloud.com';
    }
    if (env === 'test') {
        url = '/server/resource';
    }
    if (env === 'production') {
        url = '/server/resource';
    }
    return url;
}

export default dictionary;
