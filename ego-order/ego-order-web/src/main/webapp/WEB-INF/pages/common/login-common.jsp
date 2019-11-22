<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="tbar_lft">
    <c:if test="${empty user}">
        您好，欢迎来到EGO商城！<a href="${ctx}/login">请登录</a> | <a href="#">免费注册</a>
    </c:if>
    <c:if test="${!empty user}">
        ${user.userName}您好，欢迎来到EGO商城！<a href="${ctx}/user/logout">安全退出</a>
    </c:if>
</div>
