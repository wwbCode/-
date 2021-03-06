import Main from '@/views/Main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
    path: '/login',
    name: 'login',
    meta: {
        title: 'Login - 登录'
    },
    component: () => import('@/views/login.vue')
};

export const page404 = {
    path: '/*',
    name: 'error-404',
    meta: {
        title: '404-页面不存在'
    },
    component: () => import('@/views/error-page/404.vue')
};

export const page403 = {
    path: '/403',
    meta: {
        title: '403-权限不足'
    },
    name: 'error-403',
    component: () => import('@//views/error-page/403.vue')
};

export const page500 = {
    path: '/500',
    meta: {
        title: '500-服务端错误'
    },
    name: 'error-500',
    component: () => import('@/views/error-page/500.vue')
};

export const preview = {
    path: '/preview',
    name: 'preview',
    component: () => import('@/views/form/article-publish/preview.vue')
};

export const locking = {
    path: '/locking',
    name: 'locking',
    component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
    path: '/',
    name: 'otherRouter',
    redirect: '/home',
    component: Main,
    children: [
        { path: 'home', title: {i18n: 'home'}, name: 'home_index', component: () => import('@/views/home/home.vue') },
        { path: 'ownspace', title: '个人中心', name: 'ownspace_index', component: () => import('@/views/own-space/own-space.vue') },
        { path: 'order/:order_id', title: '订单详情', name: 'order-info', component: () => import('@/views/advanced-router/component/order-info.vue') }, // 用于展示动态路由
        { path: 'shopping', title: '购物详情', name: 'shopping', component: () => import('@/views/advanced-router/component/shopping-info.vue') }, // 用于展示带参路由
        { path: 'message', title: '消息中心', name: 'message_index', component: () => import('@/views/message/message.vue') },
        { path: 'server', title: '服务器管理', name: 'server', component: () => import('@/views/service/server/server-list.vue')},
        { path: 'device', title: '设备管理', name: 'device', component: () => import('@/views/service/device/device-list.vue')}
    ]
};

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
    {
        path: '/system',
        icon: 'key',
        name: 'system',
        title: '权限管理',
        component: Main,
        children: [
            {
                path: 'menu',
                title: '菜单管理',
                name: 'third.system.menu.allList',
                icon: 'ios-switch-outline',
                component: () => import('@/views/system/menu/tree.vue')
            },
            {
                name: 'third.system.role.list',
                path: 'role',
                title: '角色列表',
                icon: 'ios-switch-outline',
                component: () => import('@/views/system/role/role-list.vue')
            },
            {
                name: 'third.system.user.list',
                path: 'user',
                title: '系统用户列表',
                icon: 'ios-switch-outline',
                component: () => import('@/views/system/user/user-list.vue')
            }
            ]
    },
    {
        path: '/supplier',
        icon: 'key',
        name: 'supplier',
        title: '供应商',
        component: Main,
        children: [
            {
                path: 'list',
                icon: 'key',
                name: 'suppelierList',
                title: '供应商管理',
                component: () => import('@/views/supplier/supplier.vue')
            }
        ]
    },
    {
        path: '/service',
        icon: 'key',
        name: 'service',
        title: '服务管理',
        component: Main,
        children: [
            {
                path: 'services',
                icon: 'key',
                name : 'third.service.services.list',
                title: '服务列表',
                component: () => import('@/views/service/services/service-list.vue')
            },
            {
                name: 'third.service.contact.list',
                path: 'contact',
                title: '联络人管理',
                icon: 'ios-switch-outline',
                component: () => import('@/views/service/contact/contact-list.vue')
            },
            {
                name: 'third.service.broadband.list',
                path: 'broadband-list',
                title: '宽带管理',
                icon: 'ios-switch-outline',
                component: () => import('@/views/service/broadband/broadband-list.vue')
            },
            {
                name:'third.service.monitor.list',
                path:'monitor-list',
                title:'监控管理',
                icon:'ios-switch-outline',
                component:()=> import('@/views/service/monitor/monitor-list.vue')
            }
        ]
    },

    // {
    //     path: '/access-test',
    //     icon: 'lock-combination',
    //     title: '权限测试页',
    //     name: 'accesstest',
    //     access: 0,
    //     component: Main,
    //     children: [
    //         { path: 'index', title: '权限测试页', name: 'accesstest_index', access: 0, component: () => import('@/views/access/access-test.vue') }
    //     ]
    // },
];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
    loginRouter,
    otherRouter,
    preview,
    locking,
    ...appRouter,
    page500,
    page403,
    page404
];
