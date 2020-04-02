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
public class GoodType implements Serializable {
    private int goodType;
    private int goodTypeName;
}
