package com.trackwize.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseListReqDTO {

    private String category;

    private Date expenseDateStart;
    private Date expenseDateEnd;
}
