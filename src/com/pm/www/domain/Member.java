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
public class Member implements Serializable {
    private int mid;
    private int mrole;
    private String mpassword;
    private String mAccount;
    private String mbrithday;
    private String mgender;
    private String memail;
    private int userlevel;
    private String mcreatetime;
    private String mphone;

    public Member(String mpassword, String mAccount, String mbrithday, String mgender, String memail, String mphone) {
        this.mpassword = mpassword;
        this.mAccount = mAccount;
        this.mbrithday = mbrithday;
        this.mgender = mgender;
        this.memail = memail;
        this.mphone = mphone;
    }
}
