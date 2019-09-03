import iView from 'iview';

let prompt = {};

prompt.message = function (info, successText, warningText, duration) {
    var message = "";
    if (info) {
        if (!duration) {
            duration = 4.5;
        }
        if (info == 200) {
            message = prompt.getDefaultPromptText(info);
            if (!message || '' == message) {
                message = successText;
            }
            iView.Notice.success({
                title: '成功',
                desc: message,
                duration: duration
            });
        } else {
            message = prompt.getDefaultErrorText(info);
            if (!message || '' == message) {
                message = warningText;
            }
            iView.Notice.warning({
                title: '警告',
                desc: message,
                duration: duration
            });
        }
    } else {
        iView.Notice.error({
            title: '错误',
            desc: warningText,
            duration: duration
        });
    }
};

prompt.noticee = function (message, title, type) {
    type = type ? type : "info";

    if ("info" == type) {
        iView.Notice.info({
            title: title | '信息',
            desc: message
        });
    }
    if ("success" == type) {
        iView.Notice.success({
            title: title | '成功',
            desc: message
        });
    }
    if ("warning" == type) {

        iView.Notice.warning({
            title: title | '警告',
            desc: message
        });
    }
    if ("error" == type) {
        iView.Notice.error({
            title: title | '错误',
            desc: message
        });
    }
};
prompt.getDefaultErrorText = function (info) {
    var text = '';
    if (info) {
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
prompt.getDefaultPromptText = function (info) {
    var text = '';
    if (info) {
        var prompts = info.prompts;
        if (prompts && prompts.length > 0) {
            for (var i = 0; i < prompts.length; i++) {
                text = text + prompts[i].text + '\n';
            }
        }
    }
    return text;
};
export default prompt;
