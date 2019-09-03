import * as base from './base';
let dictionary = {};

const map = new base.Map();

function put (category, property, value, name) {
    var propertyMap = getMap(category, property);
    propertyMap.put(value, name);
}

function getMap (category, property){

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
            if (children && '' !== children) {
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
    put('article', 'status', '0', '草稿');
    put('article', 'status', '1', '已发表');
    put('article', 'status', '2', '回收站');

    put('articleCategory', 'type', '0', '未分类');
    put('articleCategory', 'type', '1', '广告');
    put('articleCategory', 'type', '2', '公告');
    put('articleCategory', 'type', '3', '资讯');

    put('common', 'flag', '0', '禁用');
    put('common', 'flag', '1', '启用');

    put('common', 'status', '0', '无效');
    put('common', 'status', '1', '有效');

    put('common', 'isTop', '0', '否');
    put('common', 'isTop', '1', '是');

    put('supply', 'deliveryMode', '1', '卖家包送');
    put('supply', 'deliveryMode', '2', '仓库自提');

    put('user', 'status', '0', '注册');
    put('user', 'status', '1', '审批中');
    put('user', 'status', '2', '有效');
    put('user', 'status', '3', '禁用');

    // 用户性别
    put('user', 'gender', '1', '男');
    put('user', 'gender', '2', '女');
    put('user', 'gender', '3', '保密');

    put('org', 'status', '0', '未认证');
    put('org', 'status', '1', '待审核');
    put('org', 'status', '2', '已认证');
    put('org', 'status', '3', '解约');

    // 企业类型
    put('org', 'nature', '0', '国有');
    put('org', 'nature', '1', '私企');
    put('org', 'nature', '2', '外资');
    put('org', 'nature', '3', '合资');

    // 企业认证
    put('org', 'isAuthorize', '0', '未认证');
    put('org', 'isAuthorize', '1', '待审核');
    put('org', 'isAuthorize', '2', '认证成功');
    put('org', 'isAuthorize', '3', '认证失败');

    // 库存状态
    put('storage', 'status', '0', '新申请');
    put('storage', 'status', '1', '拒绝申请');
    put('storage', 'status', '2', '客户取消');
    put('storage', 'status', '3', '批准入库');
    put('storage', 'status', '4', '保管中');
    put('storage', 'status', '5', '已出库');

    // 求购单状态
    put('delivery', 'status', '0', '新申请');
    put('delivery', 'status', '1', '已签收');
    put('delivery', 'status', '2', '拒绝申请');
    put('delivery', 'status', '3', '客户取消');
    put('delivery', 'status', '4', '待发货');
    put('delivery', 'status', '5', '物流接单');
    put('delivery', 'status', '6', '运输中');

    // 配送方式
    put('supply', 'deliveryMode', '1', '卖家包送');
    put('supply', 'deliveryMode', '2', '仓库自提');
    put('supply', 'deliveryMode', '3', '第三方配送');

    // 供应单状态
    put('supply', 'status', '-1', '拒绝');
    put('supply', 'status', '0', '待审核');
    put('supply', 'status', '1', '发布中');
    put('supply', 'status', '2', '部分交易中');
    put('supply', 'status', '3', '交易中');
    put('supply', 'status', '4', '完成');
    put('supply', 'status', '5', '取消');

    // 用户状态
    put('user', 'status', '0', '注册');
    put('user', 'status', '1', '审批中');
    put('user', 'status', '2', '有效');
    put('user', 'status', '3', '禁用');

    // 立方体积
    put('common', 'unit', 'M³', '立方米');
    put('common', 'unit', 'DM³', '立方分米');
    put('common', 'unit', 'CM³', '立方厘米');
    put('common', 'unit', 'MM³', '立方毫米');
    // 升体积
    put('common', 'unit', 'L', '升');
    put('common', 'unit', 'DL', '分升');
    put('common', 'unit', 'CL', '厘升');
    put('common', 'unit', 'ML', '毫升');
    // 重量
    put('common', 'unit', 'T', '吨');
    put('common', 'unit', 'KG', '千克');
    put('common', 'unit', 'G', '克');

    // 规格
    put('common', 'standard', '1', '桶装');
    put('common', 'standard', '2', '散水');
    put('common', 'standard', '3', '瓶装');
    put('common', 'standard', '99', '其他');

    // 产地
    put('common', 'placeOrigin', '1', '国产');
    put('common', 'placeOrigin', '2', '进口');
    // 提货地
    put('common', 'warehouse', '1', '江苏太仓');
    put('common', 'warehouse', '2', '江苏张家港');
    put('common', 'warehouse', '3', '江苏江阴');

    // 订单状态
    put('order', 'status', '0', '待供应商确认');
    put('order', 'status', '9', '已完成');
    put('order', 'status', '11', '已取消');

    // 报价形式
    put('order', 'quoteType', '1', '含税');
    put('order', 'quoteType', '2', '不含税');

    // 创建方式
    put('order', 'createType', '1', '采购创建');
    put('order', 'createType', '2', '供应创建');
    put('order', 'createType', '3', '管理员创建');
    put('order', 'createType', '4', '系统创建');

    // 留言状态
    put('content', 'status', '0', '未处理');
    put('content', 'status', '1', '已处理');

    // 留言类型
    put('content', 'type', '2', '求购单');
    put('content', 'type', '1', '供应单');

    // 认证处理结果
    put('common', 'isAuthorize', '1', '待审核');
    put('common', 'isAuthorize', '2', '已通过');
    put('common', 'isAuthorize', '3', '已拒绝');

    // 供求单列表类型
    put('bill', 'queryType', '1', '供应单');
    put('bill', 'queryType', '2', '求购单');

    // 撤销类型
    put('bill', 'revokeType', '0', '其他');
    put('bill', 'revokeType', '1', '信息填写有误');
    put('bill', 'revokeType', '2', '未收到合适的采购');
    put('bill', 'revokeType', '3', '已通过其他渠道找到采购商');
    put('bill', 'revokeType', '4', '已无商品库存');
}

export default dictionary;
