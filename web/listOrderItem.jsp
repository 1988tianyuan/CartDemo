<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/28
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>购物车</title>
    <script type="text/javascript" src="//upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.3.min.js"></script>

</head>
<body>
<div align="center">购物车</div><hr>
<table align="center" style="text-align: center;border: solid">
    <tr>
        <td>商品名称</td><td>单价</td><td>数量</td><td>总价</td><td>删除</td>
    </tr>
    <c:forEach items="${orderItemList}" var="orderItem" varStatus="st">
        <tr id="tr">
            <td>${orderItem.product.name}</td>
            <td>${orderItem.product.price}</td>
            <td>${orderItem.num}</td>
            <td>${orderItem.num*orderItem.product.price}</td>
            <td><a href="deleteOrderItem?id=${orderItem.product.id}" class="delete">删除</a></td>
        </tr>
    </c:forEach>
    <%--<c:if test="${!empty orderItemList}">--%>
        <%--<tr>--%>
            <%--<td colspan="4" style="text-align: center"><a href="createOrder">创建订单</a></td>--%>
        <%--</tr>--%>
    <%--</c:if>--%>
</table>
</body>
</html>
