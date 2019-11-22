<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div id="s_cart">
    <ul>
        <li class="nums">
            <a href="javascript:void(0);">购物车： <span id="s_cart_nums1">0</span> 件</a>
            <a href="javascript:void(0);" class="btn" id="s_cart_nums2"></a>
        </li>
        <li class="checkout"><a href="javascript:void(0);">去结算&gt;&gt;</a></li>
    </ul>
</div>
<input type="hidden" id="userName" value="${user.userName}"/>
<script type="text/javascript">
    $(function () {
        getCartNum();
    });

    // 获取购物车数量
    function getCartNum() {
        // 如果用户存在，获取购物车数量
        if ($("#userName").val()) {
            $.ajax({
                url: "${ctx}/cart/getCartNum",
                type: "GET",
                dataType: "JSON",
                success: function (result) {
                    $("#s_cart_nums1").text(result);
                },
                error: function (result) {
                    console.log(result);
                    layer.msg("亲，系统正在升级中，请稍后再试！");
                }
            });
        }
    }
</script>
