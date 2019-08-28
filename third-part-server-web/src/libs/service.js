import axios from 'axios';
import * as base from '@/libs/base';
import auth from '@/libs/auth';
import env from '../../build/env';
import iView from 'iview';

let service = {};

const ajaxUrl = getBaseUrl();

function getBaseUrl () {
    var url = '';
    if (env === 'development') {
        url = 'http://127.0.0.1:6603';
    }
    if (env === 'development') {

    }
    return url;
}

service.ajax = axios.create({
    baseURL: ajaxUrl,
    timeout: 30000
});

service.baseURL = ajaxUrl;

// request interceptor
service.ajax.interceptors.request.use(config => {
    // Do something before request is sent
    // if (store.getters.token) {
    config.headers['X-Token'] = auth.getToken(); // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
    // }
    return config;
}, error => {
    // Do something with request error
    console.log(error); // for debug
    Promise.reject(error);
});

// respone interceptor
service.ajax.interceptors.response.use(
    response => {

        var status = response.status;
        var headers = response.headers;
        if (200 == status) {
            var result = response.data;

            if (!base.isEmpty(result)) {
                var head = result.head;
                var info = result.info;
                if (info) {
                    if (info.success) {
                    } else {
                        var error = service.getDefaultErrorText(info);
                        iView.Notice.warning({
                            title: '错误',
                            desc: error
                        });
                    }
                }
                if (head) {
                    var resultCode = head.resultCode;
                    var resultMessage = head.resultMessage;

                    if ('101' == resultCode) {
                        if (typeof (service.onExpire) == 'function') {
                            service.onExpire();
                        }
                    } else if ('0' == resultCode) {
                        iView.Notice.warning({
                            title: '错误',
                            desc: resultMessage
                        });
                    }
                }
            }
        }

        // var resultCode = headers.resultCode;
        // if ('101' == resultCode) {
        //     if (typeof (service.onExpire) == "function") {
        //         service.onExpire();
        //     }
        // }
        // if (100 == status) {
        //     if (typeof (service.onExpire) == "function") {
        //         service.onExpire();
        //     }
        // }
        return response;//Promise.reject(response)
    },
    /**
     * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
     * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
     */
        //  const res = response.data;
        //     if (res.code !== 20000) {
        //       Message({
        //         message: res.message,
        //         type: 'error',
        //         duration: 5 * 1000
        //       });
        //       // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
        //       if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        //         MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
        //           confirmButtonText: '重新登录',
        //           cancelButtonText: '取消',
        //           type: 'warning'
        //         }).then(() => {
        //           store.dispatch('FedLogOut').then(() => {
        //             location.reload();// 为了重新实例化vue-router对象 避免bug
        //           });
        //         })
        //       }
        //       return Promise.reject('error');
        //     } else {
        //       return response.data;
        //     }
    error => {
        //console.log('err' + error)// for debug
        var message = error.message;
        if (message == 'Network Error') {
            message = '网络错误！';
        }
        iView.Notice.warning({
            title: '错误',
            desc: message
        });
        return Promise.reject(error);
    });

service.post = function (url, data, back) {
    service.ajax.post(url, data).then(function (response) {
        var data = response.data;
        if (typeof (back) == 'function') {
            back(data);
        }
    }).catch(function (error) {
        var message = error.message;
        if (message == 'Network Error') {
            message = '网络错误！';
        }
        iView.Notice.warning({
            title: '错误',
            desc: message
        });
    });
};

service.postBody = function (url, body, back) {
    var request = {'body': body};
    service.post(url, request, back);
};

service.getDefaultErrorText = function (info) {
    var text = '';
    if (!base.isEmpty(info)) {
        var warnings = info.warnings;
        var errors = info.errors;
        if (warnings && warnings.length > 0) {
            for (var i = 0; i < warnings.length; i++) {
                text = text + warnings[i].text + '\n';
            }
        } else if (errors && errors.length > 0) {
            for (var i = 0; i < errors.length; i++) {
                text = text + errors[i].text + '\n';
            }
        }
    }
    return text;
};

service.onExpire - function () {

};
export default service;
