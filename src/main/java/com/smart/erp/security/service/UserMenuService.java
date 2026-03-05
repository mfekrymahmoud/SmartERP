package com.smart.erp.security.service;

import com.smart.erp.security.dto.response.UserMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
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
    public List<UserMenuResponse> getMenuTree(Long userId) {
        List<UserMenuResponse> allMenus = getUserMenus(userId);
        // Advice: Filter for root level (null parent) and recurse
        return allMenus.stream()
                .filter(m -> m.getParentMenuId() == null)
                .map(m -> buildRecursiveTree(m, allMenus))
                .collect(Collectors.toList());
    }

    private UserMenuResponse buildRecursiveTree(UserMenuResponse current, List<UserMenuResponse> all) {
        List<UserMenuResponse> children = all.stream()
                .filter(m -> current.getMenuId().equals(m.getParentMenuId()))
                .map(m -> buildRecursiveTree(m, all))
                .collect(Collectors.toList());
        current.setChildren(children);
        return current;
    }

    private UserMenuResponse mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        UserMenuResponse res = new UserMenuResponse();
        res.setUserId(rs.getLong("USER_ID"));
        res.setUserName(rs.getString("USER_NAME"));
        res.setMenuId(rs.getLong("MENU_ID"));
        res.setMenuDescriptionAr(rs.getString("MENU_DESCRIPTION_AR"));
        res.setMenuDescriptionEn(rs.getString("MENU_DESCRIPTION_EN"));
        long parentId = rs.getLong("PARENT_MENU_ID");
        res.setParentMenuId(rs.wasNull() ? null : parentId);
        res.setMenuOrder(rs.getInt("MENU_ORDER"));
        res.setIcon(rs.getString("ICON"));
        res.setRoutePath(rs.getString("ROUTE_PATH"));
        res.setAccessType(rs.getString("ACCESS_TYPE"));
        res.setFunctionCode(rs.getString("FUNCTION_CODE"));
        return res;
    }
}