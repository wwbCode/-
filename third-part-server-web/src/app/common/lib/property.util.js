let propertyUtil = {};

/**
 * 增加列表中的属性，从另一属性赋值。
 * @param list：列表
 * @param children：子列表的属性名
 * @param fromProperty：来源属性
 * @param toProperty：被赋值的属性
 */
propertyUtil.fromProperty = function (list, children, fromProperty, toProperty) {
    if (list) {
        var length = list.length;
        for (var i = 0; i < length; i++) {
            var item = list[i];
            item[toProperty] = item[fromProperty];
            if (children && '' != children) {
                var node = item[children];
                if (node) {
                    propertyUtil.fromProperty(node, children, fromProperty, toProperty);
                }
            }
        }
    }
};

propertyUtil.setProperty = function (list, children, property, value) {
    if (list) {
        var length = list.length;
        for (var i = 0; i < length; i++) {
            var item = list[i];
            item[property] = value;
            if (children && '' != children) {
                var node = item[children];
                if (node) {
                    propertyUtil.setProperty(node, children, property, value);
                }
            }
        }
    }
};


propertyUtil.forEach = function (list, handler, children) {
    if (list) {
        var length = list.length;
        for (var i = 0; i < length; i++) {
            var item = list[i];

            if (typeof(handler) == 'function') {
                handler(i, item);
            }
            if (children && '' != children) {
                var node = item[children];
                if (node) {
                    propertyUtil.forEach(node, handler, children);
                }
            }
        }
    }
};

propertyUtil.substring = function (text, start, end) {
    var value
    if (text) {
        var length = text.length;
        if (start <= end && end <= length) {
            value = text.substring(start, end);
        } else {
            value = text;
        }
    }
    return value;
};

propertyUtil.substringList = function (list, children, start, end, fromProperty, toProperty) {
    if (list) {
        var length = list.length;
        for (var i = 0; i < length; i++) {
            var item = list[i];
            var to = (toProperty && '' != toProperty) ? toProperty : fromProperty;
            var text = item[fromProperty];
            item[to] = propertyUtil.substring(text, start, end);

            if (children && '' != children) {
                var node = item[children];
                if (node) {
                    propertyUtil.substringList(node, children, start, end, fromProperty, toProperty);
                }
            }
        }
    }
};

export default propertyUtil;
