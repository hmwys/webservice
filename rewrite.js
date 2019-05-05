// document.write("test");
// document.getElementsByClassName("text").item(0).innerHTML="<p id=\"art1\"  class=\"art1\" style=\"text-indent: 2em;\">网页登录大多是通过向服务器提交表单来完成的，因此我们要做的第一件事就是获取它的表单（&ltform&gt表单内容&lt/form&gt），就是登录界面的输入框。 首先是进入控制台，查看输入框所在的元素，但是由于网页一般都是被渲染过的，此时应当右键，查看源代码（和控制台的审查元素是不一样的）</p>\n";
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
        document.getElementById("textArea").innerHTML = xmlhttp.responseText;
    }
};
xmlhttp.open("GET", "/getText", true);
xmlhttp.send();

