package com.ego.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ego.config.AlipayConfig;
import com.ego.pojo.Admin;
import com.ego.pojo.Order;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;
import com.ego.service.CartServiceI;
import com.ego.service.OrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartServiceI cartService;

    @Autowired
    private OrderServiceI orderService;

    /**
     * 跳转订单页面
     *
     * @return
     */
    @RequestMapping("/preOrder")
    public String preOrder(Model model, HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        model.addAttribute("cartResult", cartService.getCartList(admin));
        return "order/preOrder";
    }

    /**
     * 订单提交成功页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/submitOrder")
    public String submitOrder(Model model, HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        CartResult cartResult = cartService.getCartList(admin);
        // 生成订单
        BaseResult baseResult = orderService.orderSave(cartResult, Integer.valueOf(admin.getAdminId()));
        // 设置页面显示数据
        model.addAttribute("goodsSn", baseResult.getMessage());
        model.addAttribute("totalPrice", cartResult.getTotalPrice());
        // 清空购物车信息
        cartService.clearCart(admin);
        return "order/submitOrder";
    }

    /**
     * 去付款
     */
    @RequestMapping("/payment")
    public String payment(HttpServletRequest request, String orderSn, Model model) {
        try{
            //根据订单编号查询订单信息
            Order order = orderService.selectOrderByOrderSn(orderSn);
            if (null == order)
                return null;

            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = orderSn;

            //付款金额，必填
            String total_amount = String.valueOf(order.getTotalAmount());
            //订单名称，必填
            String subject = "编号为：" + order.getUserId() + "的用户订单";
            //商品描述，可空
            String body = "";

            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
            //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
            //		+ "\"total_amount\":\""+ total_amount +"\","
            //		+ "\"subject\":\""+ subject +"\","
            //		+ "\"body\":\""+ body +"\","
            //		+ "\"timeout_express\":\"10m\","
            //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println(result);
            model.addAttribute("result", result);

            //输出
            return "order/result";
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
            return "error";
    }

    /**
     * 跳转我的订单页面
     */
    public String myOrder(){
        //关于订单的各种转态修改
        System.out.println("订单支付成功， 同步通知回调！");
        return "order/myOrder";
    }

}
