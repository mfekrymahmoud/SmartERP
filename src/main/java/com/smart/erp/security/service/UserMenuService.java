package com.smart.erp.security.service;

import com.smart.erp.security.dto.response.UserMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMenuService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<UserMenuResponse> getUserMenus(Long userId) {
        String sql = "SELECT * FROM FND_USER_MENUS_V WHERE USER_ID = ? ORDER BY MENU_ORDER, MENU_ID";
        return jdbcTemplate.query(sql, this::mapRow, userId);
    }

    @Transactional(readOnly = true)
    public List<UserMenuResponse> getRootMenus(Long userId) {
        String sql = "SELECT * FROM FND_USER_MENUS_V WHERE USER_ID = ? AND PARENT_MENU_ID IS NULL ORDER BY MENU_ORDER";
        return jdbcTemplate.query(sql, this::mapRow, userId);
    }

    @Transactional(readOnly = true)
    public List<UserMenuResponse> getSubMenus(Long userId, Long parentId) {
        String sql = "SELECT * FROM FND_USER_MENUS_V WHERE USER_ID = ? AND PARENT_MENU_ID = ? ORDER BY MENU_ORDER";
        return jdbcTemplate.query(sql, this::mapRow, userId, parentId);
    }

    @Transactional(readOnly = true)
    public List<UserMenuResponse> getMenusByAccessType(Long userId, String accessType) {
        String sql = "SELECT * FROM FND_USER_MENUS_V WHERE USER_ID = ? AND ACCESS_TYPE = ? ORDER BY MENU_ORDER";
        return jdbcTemplate.query(sql, this::mapRow, userId, accessType);
    }

    @Transactional(readOnly = true)
    public List<UserMenuResponse> getMenuTree(Long userId) {
        List<UserMenuResponse> allMenus = getUserMenus(userId);
        return buildMenuTree(allMenus, null);
    }

    @Transactional(readOnly = true)
    public List<UserMenuResponse> getUserMenusByLanguage(Long userId, String language) {
        String sql = "SELECT * FROM FND_USER_MENUS_V WHERE USER_ID = ? AND LANGUAGE_PREFERENCE = ? ORDER BY MENU_ORDER";
        return jdbcTemplate.query(sql, this::mapRow, userId, language);
    }

    @Transactional(readOnly = true)
    public boolean hasFunctionAccess(Long userId, String functionCode) {
        String sql = "SELECT COUNT(*) FROM FND_USER_MENUS_V WHERE USER_ID = ? AND FUNCTION_CODE = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, functionCode);
        return count != null && count > 0;
    }

    private List<UserMenuResponse> buildMenuTree(List<UserMenuResponse> menus, Long parentId) {
        List<UserMenuResponse> result = new ArrayList<>();

        for (UserMenuResponse menu : menus) {
            if ((parentId == null && menu.getParentMenuId() == null) ||
                    (parentId != null && parentId.equals(menu.getParentMenuId()))) {

                UserMenuResponse menuItem = new UserMenuResponse();
                menuItem.setUserId(menu.getUserId());
                menuItem.setUserName(menu.getUserName());
                menuItem.setMenuId(menu.getMenuId());
                menuItem.setMenuDescriptionAr(menu.getMenuDescriptionAr());
                menuItem.setMenuDescriptionEn(menu.getMenuDescriptionEn());
                menuItem.setParentMenuId(menu.getParentMenuId());
                menuItem.setMenuOrder(menu.getMenuOrder());
                menuItem.setIcon(menu.getIcon());
                menuItem.setRoutePath(menu.getRoutePath());
                menuItem.setAccessType(menu.getAccessType());
                menuItem.setFunctionId(menu.getFunctionId());
                menuItem.setFunctionCode(menu.getFunctionCode());
                menuItem.setFunctionDescriptionAr(menu.getFunctionDescriptionAr());
                menuItem.setFunctionDescriptionEn(menu.getFunctionDescriptionEn());

                // Recursively build children
                List<UserMenuResponse> children = buildMenuTree(menus, menu.getMenuId());
                // Note: If you want to include children in the response,
                // add a children field to UserMenuResponse

                result.add(menuItem);
            }
        }

        return result;
    }

    private UserMenuResponse mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        UserMenuResponse response = new UserMenuResponse();
        response.setUserId(rs.getLong("USER_ID"));
        response.setUserName(rs.getString("USER_NAME"));
        response.setMenuId(rs.getLong("MENU_ID"));
        response.setMenuDescriptionAr(rs.getString("MENU_DESCRIPTION_AR"));
        response.setMenuDescriptionEn(rs.getString("MENU_DESCRIPTION_EN"));

        long parentMenuId = rs.getLong("PARENT_MENU_ID");
        response.setParentMenuId(rs.wasNull() ? null : parentMenuId);

        response.setMenuOrder(rs.getInt("MENU_ORDER"));
        response.setIcon(rs.getString("ICON"));
        response.setRoutePath(rs.getString("ROUTE_PATH"));
        response.setAccessType(rs.getString("ACCESS_TYPE"));

        long functionId = rs.getLong("FUNCTION_ID");
        response.setFunctionId(rs.wasNull() ? null : functionId);

        response.setFunctionCode(rs.getString("FUNCTION_CODE"));
        response.setFunctionDescriptionAr(rs.getString("FUNCTION_DESCRIPTION_AR"));
        response.setFunctionDescriptionEn(rs.getString("FUNCTION_DESCRIPTION_EN"));

        return response;
    }
}