var resptext;
var xmlhttp;
if (window.XMLHttpRequest) {
    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp = new XMLHttpRequest();
} else {
    // IE6, IE5 浏览器执行代码
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange = function () {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        resptext=xmlhttp.responseText;
        if (resptext != -1) {
            // document.getElementById("all").style.visibility = "visible";
                document.getElementById("logInfo").innerHTML = resptext;
        }else {
            document.getElementById("login-form").style.visibility="visible";

        }
    }
};
xmlhttp.open("get", "/chksid", true);
xmlhttp.send();


var info = document.getElementById("logInfo");

function check() {
    var userName = document.getElementById("username");
    var passwd = document.getElementById("password");
    var isError = false;
    if (userName.value.length > 15) {
        info.innerHTML = "用户名长度必须在6~20位之间";
        isError = true;
        return;

    } else if (userName.value.charCodeAt(0) >= 48 && passwd.value.charCodeAt(0) <= 57) {
        info.innerHTML = "用户名开头不能为数字";
        isError = true;
        return;
    } else {
        for (var i = 0; i < passwd.value.length; i++) {
            if ((userName.value.charCodeAt(i) > 122 || userName.value.charCodeAt(i) < 97) && (userName.value.charCodeAt(i) > 57 || userName.value.charCodeAt(i) < 48)) {
                info.innerHTML = "用户名只能包含小写字母和数字";
                isError = true;
                return;
            }
        }
    }
    if (passwd.value.length > 20 || passwd.value.length < 6) {
        passwd.innerHTML = "密码长度必须在6~20位之间";
        isError = true;
        return;
    }
    info.innerHTML = "格式正确";
}

$(document).ready(function () {
    $("#loginForm").ajaxForm(function (data) {
        alert(data);
        info.innerHTML = "密码错误";
    });
});
