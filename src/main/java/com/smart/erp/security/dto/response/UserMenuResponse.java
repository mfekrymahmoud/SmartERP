package com.smart.erp.security.dto.response;

import lombok.Data;

@Data
public class UserMenuResponse {
    private Long userId;
    private String userName;
    private Long menuId;
    private String menuDescriptionAr;
    private String menuDescriptionEn;
    private Long parentMenuId;
    private Integer menuOrder;
    private String icon;
    private String routePath;
    private String accessType;
    private Long functionId;
    private String functionCode;
    private String functionDescriptionAr;
    private String functionDescriptionEn;

    // Manual setters if Lombok fails
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setMenuDescriptionAr(String menuDescriptionAr) {
        this.menuDescriptionAr = menuDescriptionAr;
    }

    public void setMenuDescriptionEn(String menuDescriptionEn) {
        this.menuDescriptionEn = menuDescriptionEn;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setFunctionDescriptionAr(String functionDescriptionAr) {
        this.functionDescriptionAr = functionDescriptionAr;
    }

    public void setFunctionDescriptionEn(String functionDescriptionEn) {
        this.functionDescriptionEn = functionDescriptionEn;
    }
}