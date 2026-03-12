package com.trackwize.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    private Long expenseId;
    private Long userId;
    private BigDecimal amount;
    private String category;
    private String description;
    private Instant expenseDate;

    private String status;
    private Long createdBy = 0L;
    private Instant createdDate;
    private Long updatedBy;
    private Instant updatedDate;
}
