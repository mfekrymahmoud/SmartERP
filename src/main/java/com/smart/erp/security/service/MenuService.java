package com.smart.erp.security.service;

import com.smart.erp.security.dto.request.MenuRequest;
import com.smart.erp.security.dto.response.MenuResponse;
import com.smart.erp.security.model.Menu;
import com.smart.erp.security.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<MenuResponse> getAllMenus() {
        return menuRepository.findAllView().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuResponse getMenuById(Long id) {
        Menu menu = menuRepository.findByIdView(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        return mapToResponse(menu);
    }

    @Transactional
    public MenuResponse createMenu(MenuRequest request) {
        Menu menu = new Menu();
        menu.setMenuCode(request.getMenuCode());
        menu.setMenuDescriptionAr(request.getMenuDescriptionAr());
        menu.setMenuDescriptionEn(request.getMenuDescriptionEn());
        menu.setParentMenuId(request.getParentMenuId());
        menu.setMenuOrder(request.getMenuOrder());
        menu.setIcon(request.getIcon());
        menu.setRoutePath(request.getRoutePath());

        Menu saved = menuRepository.save(menu);
        return mapToResponse(saved);
    }

    @Transactional
    public MenuResponse updateMenu(Long id, MenuRequest request) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menu.setMenuCode(request.getMenuCode());
        menu.setMenuDescriptionAr(request.getMenuDescriptionAr());
        menu.setMenuDescriptionEn(request.getMenuDescriptionEn());
        menu.setParentMenuId(request.getParentMenuId());
        menu.setMenuOrder(request.getMenuOrder());
        menu.setIcon(request.getIcon());
        menu.setRoutePath(request.getRoutePath());

        Menu saved = menuRepository.save(menu);
        return mapToResponse(saved);
    }

    @Transactional
    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menuRepository.delete(menu);
    }

    private MenuResponse mapToResponse(Menu menu) {
        MenuResponse response = new MenuResponse();
        response.setMenuId(menu.getMenuId());
        response.setMenuCode(menu.getMenuCode());
        response.setMenuDescriptionAr(menu.getMenuDescriptionAr());
        response.setMenuDescriptionEn(menu.getMenuDescriptionEn());
        response.setParentMenuId(menu.getParentMenuId());
        response.setMenuOrder(menu.getMenuOrder());
        response.setIcon(menu.getIcon());
        response.setRoutePath(menu.getRoutePath());
        response.setCreatedBy(menu.getCreatedBy());
        response.setCreationDate(menu.getCreationDate());
        response.setLastUpdatedBy(menu.getLastUpdatedBy());
        response.setLastUpdateDate(menu.getLastUpdateDate());
        return response;
    }
}