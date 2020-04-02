package com.pm.www.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Order implements Serializable {
    private int orderId;
    private int memberId;
    private int goodId;
    private int goodQuantity;
    private int orderTatolMoney;
    private String orderCreateTime;
    private int orderState;

    public Order(int memberId, int goodId, int goodQuantity, int orderTatolMoney) {
        this.memberId = memberId;
        this.goodId = goodId;
        this.goodQuantity = goodQuantity;
        this.orderTatolMoney = orderTatolMoney;
    }
}
