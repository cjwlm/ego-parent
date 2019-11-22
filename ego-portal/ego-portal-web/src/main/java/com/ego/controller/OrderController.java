package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    /**
     * 重定向至订单系统生成预订单信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/toPreOrder")
    public String toPreOrder(HttpServletRequest request) {
        return "redirect:" +
                request.getSession().getServletContext().getAttribute("orderUrl") +
                "order/preOrder";
    }

}
