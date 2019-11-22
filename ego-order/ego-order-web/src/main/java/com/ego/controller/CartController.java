package com.ego.controller;

import com.ego.pojo.Admin;
import com.ego.result.BaseResult;
import com.ego.service.CartServiceI;
import com.ego.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceI cartService;

    /**
     * 添加至购物车
     */
    @RequestMapping("/addToCart")
    @ResponseBody
    public BaseResult addToCart(CartVo cartVo, HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        return cartService.addToCart(cartVo,admin);
    }

    /**
     * 获取购物车数量
     */
    @RequestMapping("/getCartNum")
    @ResponseBody
    public Integer getCartNum(HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        return cartService.getCartNum(admin);
    }

    /**
     * 查询购物车信息
     */
    @RequestMapping("/getCartList")
    public String getCartList(HttpServletRequest request, Model model) {
        Admin admin = (Admin) request.getSession().getAttribute("user");
        model.addAttribute("cartResult", cartService.getCartList(admin));
        return "cart/cart-index";
    }
}
