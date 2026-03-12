package com.trackwize.core.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ExpenseResDTO {

    private BigDecimal amount;

    private String category;

    private String description;

    private Instant expenseDate;
}
