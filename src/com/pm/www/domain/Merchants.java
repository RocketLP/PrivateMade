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
public class Merchants implements Serializable {
    private int mcid;
    private String mcname;
    private String mcpassword;
    private String mclicense;
    private String mccreatetime;
    private String mcphone;
    private int mcstate;

    public Merchants(String mcname, String mcpassword, String mclicense, String mcphone) {
        this.mcname = mcname;
        this.mcpassword = mcpassword;
        this.mclicense = mclicense;
        this.mcphone = mcphone;
    }
}
