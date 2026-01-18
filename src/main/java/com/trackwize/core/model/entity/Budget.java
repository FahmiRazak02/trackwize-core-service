package com.trackwize.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    private Long budgetId;
    private Long userId;
    private Integer month;
    private Integer year;
    private BigDecimal totalBudget;
    private Boolean alertSent;

    private Long createdByL;
    private Instant createdDate;
    private Long updatedBy;
    private Instant updatedDate;
}
