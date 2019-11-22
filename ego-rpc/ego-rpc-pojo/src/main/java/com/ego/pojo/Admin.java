package com.ego.pojo;

import java.io.Serializable;

public class Admin implements Serializable {
    private Short adminId;

    private String userName;

    private String email;

    private String password;

    private String ecSalt;

    private Integer addTime;

    private Integer lastLogin;

    private String lastIp;

    private String langType;

    private Short agencyId;

    private Short suppliersId;

    private Short roleId;

    private static final long serialVersionUID = 1L;

    public Short getAdminId() {
        return adminId;
    }

    public void setAdminId(Short adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEcSalt() {
        return ecSalt;
    }

    public void setEcSalt(String ecSalt) {
        this.ecSalt = ecSalt == null ? null : ecSalt.trim();
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Integer lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public String getLangType() {
        return langType;
    }

    public void setLangType(String langType) {
        this.langType = langType == null ? null : langType.trim();
    }

    public Short getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Short agencyId) {
        this.agencyId = agencyId;
    }

    public Short getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(Short suppliersId) {
        this.suppliersId = suppliersId;
    }

    public Short getRoleId() {
        return roleId;
    }

    public void setRoleId(Short roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminId=").append(adminId);
        sb.append(", userName=").append(userName);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", ecSalt=").append(ecSalt);
        sb.append(", addTime=").append(addTime);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append(", lastIp=").append(lastIp);
        sb.append(", langType=").append(langType);
        sb.append(", agencyId=").append(agencyId);
        sb.append(", suppliersId=").append(suppliersId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}