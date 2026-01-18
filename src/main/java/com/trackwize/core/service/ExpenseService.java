package com.trackwize.core.service;

import com.trackwize.common.constant.ErrorConst;
import com.trackwize.common.exception.TrackWizeException;
import com.trackwize.core.mapper.ExpenseMapper;
import com.trackwize.core.mapstruct.ExpenseMapStruct;
import com.trackwize.core.model.dto.ExpenseReqDTO;
import com.trackwize.core.model.entity.Expense;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {


    private final ExpenseMapper expenseMapper;
    private final ExpenseMapStruct expenseMapStruct;

    public void create(ExpenseReqDTO reqDTO) {

        Expense expense = expenseMapStruct.toEntity(reqDTO);
        expense.setCreatedBy(expense.getUserId());
        expense.setUpdatedBy(expense.getUserId());

        int createResult = expenseMapper.create(expense);
        if (createResult <= 0) {
            log.warn("[{}] due to failure when creating expense record for: [user_id] [{}]", ErrorConst.CREATE_RECORD_FAILED_CODE, expense.getUserId());
            throw new TrackWizeException(
                    ErrorConst.CREATE_RECORD_FAILED_CODE,
                    ErrorConst.CREATE_RECORD_FAILED_MSG
            );
        }

        log.info("Successfully create Expense Record for: [user_id] [{}], [expense_id] [{}]", expense.getUserId(), expense.getExpenseId());
    }
}
