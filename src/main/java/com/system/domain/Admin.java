package com.system.domain;

import java.io.Serializable;

public class Admin implements Serializable {
    //管理员用户名
    private String admin;
    //管理员密码
    private String adminpswd;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAdminpswd() {
        return adminpswd;
    }

    public void setAdminpswd(String adminpswd) {
        this.adminpswd = adminpswd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin='" + admin + '\'' +
                ", adminpswd='" + adminpswd + '\'' +
                '}';
    }
}
