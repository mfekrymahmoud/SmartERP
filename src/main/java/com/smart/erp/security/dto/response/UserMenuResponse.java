package com.smart.erp.security.dto.response;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;

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

    // Advice: Add children list for tree structure support
    private List<UserMenuResponse> children = new ArrayList<>();
}