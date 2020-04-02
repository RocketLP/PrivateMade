package com.pm.www.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Goods implements Serializable {
    private int goodId;
    private String goodName;
    private int goodType;
    private String goodDescribe;
    private double goodPrice;
    private String goodPhoto;
    private String goodCreateTime;
    private int merchantsId;
}
