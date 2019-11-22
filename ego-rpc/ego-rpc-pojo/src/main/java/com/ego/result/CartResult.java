package com.ego.result;

import com.ego.vo.CartVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车列表返回对象
 */
public class CartResult implements Serializable {

    private List<CartVo> cartVoList;

    private BigDecimal totalPrice;

    public List<CartVo> getCartVoList() {
        return cartVoList;
    }

    public void setCartVoList(List<CartVo> cartVoList) {
        this.cartVoList = cartVoList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
