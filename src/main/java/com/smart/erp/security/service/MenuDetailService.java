package com.smart.erp.security.service;

import com.smart.erp.security.dto.request.MenuDetailRequest;
import com.smart.erp.security.dto.response.MenuDetailResponse;
import com.smart.erp.security.model.MenuDetail;
import com.smart.erp.security.repository.MenuDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuDetailService {

    private final MenuDetailRepository menuDetailRepository;

    @Transactional(readOnly = true)
    public List<MenuDetailResponse> getAllMenuDetails() {
        return menuDetailRepository.findAllView().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuDetailResponse getMenuDetailById(Long id) {
        MenuDetail menuDetail = menuDetailRepository.findByIdView(id)
                .orElseThrow(() -> new RuntimeException("Menu detail not found"));
        return mapToResponse(menuDetail);
    }

    @Transactional
    public MenuDetailResponse createMenuDetail(MenuDetailRequest request) {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setMenuId(request.getMenuId());
        menuDetail.setFunctionId(request.getFunctionId());
        menuDetail.setSubMenuId(request.getSubMenuId());
        menuDetail.setAccessType(request.getAccessType());

        MenuDetail saved = menuDetailRepository.save(menuDetail);
        return mapToResponse(saved);
    }

    @Transactional
    public MenuDetailResponse updateMenuDetail(Long id, MenuDetailRequest request) {
        MenuDetail menuDetail = menuDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu detail not found"));

        menuDetail.setMenuId(request.getMenuId());
        menuDetail.setFunctionId(request.getFunctionId());
        menuDetail.setSubMenuId(request.getSubMenuId());
        menuDetail.setAccessType(request.getAccessType());

        MenuDetail saved = menuDetailRepository.save(menuDetail);
        return mapToResponse(saved);
    }

    @Transactional
    public void deleteMenuDetail(Long id) {
        MenuDetail menuDetail = menuDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu detail not found"));
        menuDetailRepository.delete(menuDetail);
    }

    private MenuDetailResponse mapToResponse(MenuDetail menuDetail) {
        MenuDetailResponse response = new MenuDetailResponse();
        response.setMenuDetailId(menuDetail.getMenuDetailId());
        response.setMenuId(menuDetail.getMenuId());
        response.setFunctionId(menuDetail.getFunctionId());
        response.setSubMenuId(menuDetail.getSubMenuId());
        response.setAccessType(menuDetail.getAccessType());
        response.setCreatedBy(menuDetail.getCreatedBy());
        response.setCreationDate(menuDetail.getCreationDate());
        response.setLastUpdatedBy(menuDetail.getLastUpdatedBy());
        response.setLastUpdateDate(menuDetail.getLastUpdateDate());
        return response;
    }
}