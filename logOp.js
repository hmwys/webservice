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
        resptext = xmlhttp.responseText;
        if (resptext != -1) {
            window.location.href = "admin.html";
        }
    }
}
;
xmlhttp.open("get", "/chksid", true);
xmlhttp.send();

$(document).ready(function () {
    $("#loginForm").ajaxForm(function (data) {
        // alert(data);
        if ("success" == data) {
            window.location.href = "admin.html";
        } else {
            alert(data)
        }
    });
});
