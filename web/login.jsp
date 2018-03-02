<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/28
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登陆</title>
    <script type="text/javascript" src="//upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.3.min.js"></script>
    <script>
        $(function () {
            $("#login").click(function () {
                var name = $('#name').val();
                var password = $("#password").val();
                var user = {"name":name,"password":password};
                var url = "/login";
                $.post(
                    url,{"data":JSON.stringify(user)},function(data){

                        if(data=='right'){
                            window.location="/listProduct";
                        }else{
                            alert("用户名或密码错误！");
                        }
                    }
                )
            })
        })
    </script>
</head>
<body>

    账号：<input id="name" type="text" name="name"><br>
    密码：<input id="password" type="password" name="password"><br>
    <button id="login" >登陆</button>

</body>
</html>
