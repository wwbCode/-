import Cookies from 'js-cookie';
import {isEmpty} from '../lib/base';

const tokenKey = 'token_key';

let auth = {};

auth.setUser = function (user) {
    var id = user.id;
    var type = user.type;
    var account = user.account;
    var password = user.password;
    Cookies.set('user_id', id);
    Cookies.set('user_type', type);
    Cookies.set('user_account', account);
    Cookies.set('user_password', password);
};

auth.removeUser = function () {
    Cookies.remove('user_id');
    Cookies.remove('user_type');
    Cookies.remove('user_account');
    Cookies.remove('user_password');
};

auth.getToken = function () {
    return Cookies.get(tokenKey);
};

auth.getUserId = function () {
    return Cookies.get('user_id');
};

auth.getUserType = function () {
    return Cookies.get('user_type');
};

auth.setToken = function (token) {
    return Cookies.set(tokenKey, token);
};

auth.setPassword = function (password) {
    return Cookies.set('user_password', password);
};

auth.removeToken = function () {
    return Cookies.remove(tokenKey);
};


auth.checkPassword = function (password) {
    var check = Cookies.get('user_password') == password;
    return check;
}

auth.isLogin = function () {
    var token = this.getToken();
    var isLogin = !isEmpty(token);
    return isLogin;
};

auth.isSuperAdmin = function () {
    var isSuperAdmin = Cookies.get('user_type') == '0';
    return isSuperAdmin;
}

auth.setLocking = function (locking) {
    var isLocking = locking ? '1' : '0';
    Cookies.set('locking_key', isLocking);
    return locking;
};

auth.isLocking = function () {
    var locking = Cookies.get('locking_key') === '1';
    return locking;
};

auth.logout = function () {
    auth.removeUser();
    auth.removeToken();
}

export default auth;
