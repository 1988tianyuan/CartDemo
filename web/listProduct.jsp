<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/28
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>购物</title>
    <script type="text/javascript" src="//upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.3.min.js"></script>
    <script>
        $(function () {
                $(".add").click(function () {
                        var pid = $(this).parent().find(".pid");
                        var num = $(this).parent().find(".num");
                        var product = {"pid":pid.val(), "num":num.val()};
                        var url = "/addOrderItem";
                        $.post(
                            url,{"data":JSON.stringify(product)},function () {
                                alert("加入成功！");
                            }
                        )
                        // $(this).parent().find(".add").attr("disabled",true);
                    }
                )
            }
        )
    </script>
</head>
<body>
<div align="center">当前用户：${user.name}</div><hr>
<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>名称</td>
        <td>价格</td>
        <td>购买</td>
    </tr>
    <c:forEach items="${productList}" var="product" varStatus="st">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                    数量<input class="num" type="text" value="1" name="num">
                    <input class="pid" type="hidden" name="pid" value="${product.id}">
                    <button class="add" id="add"+${product.id}>
                        加入购物车
                    </button>
            </td>
        </tr>
    </c:forEach>
</table>
<div align="center"><a href="listOrderItem">进入购物车</a></div>

</body>
</html>
