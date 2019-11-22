<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div id="s_cart">
    <ul>
        <li class="nums">
            <a href="${ctx}/cart/getCartList">购物车： <span id="s_cart_nums1">0</span> 件</a>
            <a href="${ctx}/cart/getCartList" class="btn" id="s_cart_nums2"></a>
        </li>
        <li class="checkout"><a href="${ctx}/cart/getCartList">去结算&gt;&gt;</a></li>
    </ul>
</div>
<input type="hidden" id="userName" value="${user.userName}"/>
<script type="text/javascript">
    $(function () {
        getCartNum();
    });

    // 添加至购物车
    function addToCart(goodsId, goodsName, marketPrice, originalImg, goodsNum=1) {
        $.ajax({
            url: "${ctx}/cart/addToCart",
            type: "POST",
            data: {
                goodsId: goodsId,
                goodsName: goodsName,
                marketPrice: marketPrice,
                originalImg: originalImg,
                goodsNum: goodsNum
            },
            dataType: "JSON",
            success: function (result) {
                if (200 == result.code) {
                    layer.msg("添加购物车成功");
                    var num = parseInt($("#s_cart_nums1").text());
                    $("#s_cart_nums1").text(num + 1);
                } else {
                    layer.msg("亲，系统正在升级中，请稍后再试！");
                }
            },
            error: function (result) {
                if (200 == result.status && "OK" == result.statusText) {
                    location.href = "${ctx}/login";
                } else {
                    layer.msg("亲，系统正在升级中，请稍后再试！");
                }
            }
        });
    }

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
