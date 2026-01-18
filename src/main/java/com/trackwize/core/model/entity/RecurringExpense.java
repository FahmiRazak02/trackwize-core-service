package com.trackwize.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecurringExpense {

    private Long recurringId;
    private Long userId;
    private BigDecimal amount;
    private String category;
    private String description;
    private Instant startDate;
    private String frequency;
    private Boolean active;
    private Instant lastGeneratedAt;

    private Long createdBy;
    private Instant createdDate;
    private Long updatedBy;
    private Instant updatedDate;
}
