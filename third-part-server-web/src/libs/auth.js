import Cookies from 'js-cookie';
import {isEmpty} from './base';

let auth = {};

auth.setUser = function (user) {
    var account = user.account;
    var password = user.password;
    Cookies.set('account', account);
    Cookies.set('password', password);
};

const tokenKey = 'token_key';

auth.getToken = function () {
    return Cookies.get(tokenKey);
};

auth.setToken = function (token) {
    return Cookies.set(tokenKey, token);
};

auth.removeToken = function (token) {
    return Cookies.remove(tokenKey);
};

auth.isLogin = function () {
    var token = this.getToken();
    var isLogin = !isEmpty(token);
    return isLogin;
};

auth.isLocking = function () {
    var locking = Cookies.get('locking') === '1';
    return locking;
};

export default auth;
