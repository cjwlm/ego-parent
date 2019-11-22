package com.ego.service.impl;

import com.ego.enums.*;
import com.ego.mapper.OrderGoodsMapper;
import com.ego.mapper.OrderMapper;
import com.ego.pojo.Order;
import com.ego.pojo.OrderExample;
import com.ego.pojo.OrderGoods;
import com.ego.result.BaseResult;
import com.ego.result.CartResult;
import com.ego.service.OrderServiceI;
import com.ego.util.DateUtil;
import com.ego.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单Service
 */
@Service
public class OrderServiceImpl implements OrderServiceI {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${order.increment}")
    private String orderIncrement;

    /**
     * 生成订单
     *
     * @param cartResult
     * @param userId
     * @return
     */
    @Override
    public BaseResult orderSave(CartResult cartResult, Integer userId) {
        Order order = new Order();
        // 订单编号 ego_年月日时分秒_自增key
        String orderSn = "ego_" +
                DateUtil.getDateStr(LocalDateTime.now(), "yyyyMMddHHmmss") +
                "_" + getIncrement();
        order.setOrderSn(orderSn);
        // 用户id
        order.setUserId(userId);
        // 订单状态
        order.setOrderStatus(OrderStatus.no_confirm.getStatus());
        // 发货状态
        order.setShippingStatus(ShipStatus.no_send.getStatus());
        // 支付状态
        order.setPayStatus(PayStatus.no_pay.getStatus());
        // 商品总价
        order.setGoodsPrice(cartResult.getTotalPrice());
        // 应付款金额
        order.setOrderAmount(cartResult.getTotalPrice());
        // 订单总价
        order.setTotalAmount(cartResult.getTotalPrice());
        // 下单时间
        Long time = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        order.setAddTime(time.intValue());
        // 保存订单
        int result = orderMapper.insertSelective(order);
        if (result < 0)
            return BaseResult.error();
        // 保存订单相关商品
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        for (CartVo cartVo : cartResult.getCartVoList()) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(order.getOrderId());
            orderGoods.setGoodsId(cartVo.getGoodsId());
            orderGoods.setGoodsName(cartVo.getGoodsName());
            orderGoods.setMarketPrice(cartVo.getMarketPrice());
            orderGoods.setGoodsNum(cartVo.getGoodsNum().shortValue());
            orderGoods.setPromType(PromTypeStatus.normal.getStatus());
            orderGoods.setIsSend(SendStatus.no_pay.getStatus());
            orderGoodsList.add(orderGoods);
        }
        result = orderGoodsMapper.insertOrderGoodsBatch(orderGoodsList);
        if (result != orderGoodsList.size())
            return BaseResult.error();
        BaseResult baseResult = BaseResult.success();
        baseResult.setMessage(orderSn);
        return baseResult;
    }

    /**
     * 根据订单编号查询订单信息
     * @param orderSn
     * @return
     */
    @Override
    public Order selectOrderByOrderSn(String orderSn) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderSnEqualTo(orderSn);
        List<Order> orderList = orderMapper.selectByExample(example);
        return (CollectionUtils.isEmpty(orderList) || orderList.size() <= 0) ? null : orderList.get(0);
    }

    /**
     * 获取 redis 自增的值
     *
     * @return
     */
    private Long getIncrement() {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(orderIncrement, redisTemplate.getConnectionFactory());
        return entityIdCounter.getAndIncrement();
    }

}
