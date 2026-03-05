package com.smart.erp.security.service;

import com.smart.erp.security.dto.request.FunctionRequest;
import com.smart.erp.security.dto.response.FunctionResponse;
import com.smart.erp.security.model.Function;
import com.smart.erp.security.repository.FunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FunctionService {

    private final FunctionRepository functionRepository;

    @Transactional(readOnly = true)
    public List<FunctionResponse> getAllFunctions() {
        return functionRepository.findAllView().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FunctionResponse getFunctionById(Long id) {
        Function function = functionRepository.findByIdView(id)
                .orElseThrow(() -> new RuntimeException("Function not found"));
        return mapToResponse(function);
    }

    @Transactional
    public FunctionResponse createFunction(FunctionRequest request) {
        Function function = new Function();
        function.setFunctionCode(request.getFunctionCode());
        function.setFunctionDescriptionAr(request.getFunctionDescriptionAr());
        function.setFunctionDescriptionEn(request.getFunctionDescriptionEn());

        Function saved = functionRepository.save(function);
        return mapToResponse(saved);
    }

    @Transactional
    public FunctionResponse updateFunction(Long id, FunctionRequest request) {
        Function function = functionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Function not found"));

        function.setFunctionCode(request.getFunctionCode());
        function.setFunctionDescriptionAr(request.getFunctionDescriptionAr());
        function.setFunctionDescriptionEn(request.getFunctionDescriptionEn());

        Function saved = functionRepository.save(function);
        return mapToResponse(saved);
    }

    @Transactional
    public void deleteFunction(Long id) {
        Function function = functionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Function not found"));
        functionRepository.delete(function);
    }

    private FunctionResponse mapToResponse(Function function) {
        FunctionResponse response = new FunctionResponse();
        response.setFunctionId(function.getFunctionId());
        response.setFunctionCode(function.getFunctionCode());
        response.setFunctionDescriptionAr(function.getFunctionDescriptionAr());
        response.setFunctionDescriptionEn(function.getFunctionDescriptionEn());
        response.setCreatedBy(function.getCreatedBy());
        response.setCreationDate(function.getCreationDate());
        response.setLastUpdatedBy(function.getLastUpdatedBy());
        response.setLastUpdateDate(function.getLastUpdateDate());
        return response;
    }
}