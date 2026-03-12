package com.trackwize.core.mapstruct;

import com.trackwize.common.config.MapStructConfig;
import com.trackwize.core.model.dto.ExpenseReqDTO;
import com.trackwize.core.model.dto.ExpenseResDTO;
import com.trackwize.core.model.entity.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface ExpenseMapStruct {

    Expense toEntity(ExpenseReqDTO reqDTO);

    ExpenseResDTO toResDTO(Expense expense);
}
