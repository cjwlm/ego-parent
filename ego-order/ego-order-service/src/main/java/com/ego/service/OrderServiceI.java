package com.ego.service;

import com.ego.pojo.Order;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;

/**
 * 订单Service
 */
public interface OrderServiceI {

    /**
     * 生成订单
     *
     * @param cartResult
     * @param userId
     * @return
     */
    BaseResult orderSave(CartResult cartResult, Integer userId);

    Order selectOrderByOrderSn(String orderSn);
}
