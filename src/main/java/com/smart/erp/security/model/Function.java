package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "FND_FUNCTIONS")
@EqualsAndHashCode(callSuper = true)
public class Function extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "func_seq")
    @SequenceGenerator(name = "func_seq", sequenceName = "FND_FUNCTIONS_SEQ", allocationSize = 1)
    @Column(name = "FUNCTION_ID")
    private Long functionId;

    @Column(name = "FUNCTION_DESCRIPTION_AR", length = 200)
    private String functionDescriptionAr;

    @Column(name = "FUNCTION_DESCRIPTION_EN", length = 200)
    private String functionDescriptionEn;

    @Column(name = "FUNCTION_CODE", unique = true, length = 50)
    private String functionCode;
}