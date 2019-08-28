import service from '@/libs/service';

let user = {};

user.list = function (userQuery, page, back) {
    var body = {
        'userQuery': userQuery,
        'page': page
    };

    var m = {'body': body};
    service.post('/manage/user/list', m, back);
}

export default user;
