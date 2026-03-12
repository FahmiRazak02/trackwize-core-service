package com.trackwize.core.service;

import com.trackwize.common.constant.ErrorConst;
import com.trackwize.common.exception.TrackWizeException;
import com.trackwize.core.mapper.ExpenseMapper;
import com.trackwize.core.mapstruct.ExpenseMapStruct;
import com.trackwize.core.model.dto.ExpenseListReqDTO;
import com.trackwize.core.model.dto.ExpenseReqDTO;
import com.trackwize.core.model.dto.ExpenseResDTO;
import com.trackwize.core.model.entity.Expense;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

        int result = expenseMapper.create(expense);
        if (result <= 0) {
            log.warn(
                    "[{}] Failed to CREATE Expense Record: [user_id] [{}]",
                    ErrorConst.CREATE_RECORD_FAILED_CODE,
                    expense.getUserId()
            );
            throw new TrackWizeException(
                    ErrorConst.CREATE_RECORD_FAILED_CODE,
                    ErrorConst.CREATE_RECORD_FAILED_MSG
            );
        }

        log.info(
                "Expense Record CREATED : [expense_id] [{}]",
                expense.getExpenseId()
        );
    }

    public void update(@Valid ExpenseReqDTO reqDTO) {
        Expense expense =  expenseMapStruct.toEntity(reqDTO);
        expense.setUpdatedBy(expense.getUserId());

        int result = expenseMapper.update(expense);
        if (result <= 0) {
            log.warn(
                    "[{}] Failed to UPDATE Expense Record: [expense_id] [{}]"
                    , ErrorConst.UPDATE_RECORD_FAILED_CODE,
                    expense.getExpenseId()
            );
            throw new TrackWizeException(
                    ErrorConst.UPDATE_RECORD_FAILED_CODE,
                    ErrorConst.UPDATE_RECORD_FAILED_MSG
            );
        }

        log.info(
                "Expense Record UPDATED: [expense_id] [{}]",
                expense.getExpenseId()
        );
    }

    public void deleteById(Long expenseId) {
        int result = expenseMapper.deleteById(expenseId);
        if (result <= 0) {
            log.warn(
                    "[{}] Failed to DELETE Expense Record: [expense_id] [{}]"
                    , ErrorConst.DELETE_RECORD_FAILED_CODE,
                    expenseId
            );
            throw new TrackWizeException(
                    ErrorConst.DELETE_RECORD_FAILED_CODE,
                    ErrorConst.DELETE_RECORD_FAILED_MSG
            );
        }
    }

    public ExpenseResDTO get(Long expenseId) {
        Expense expense = expenseMapper.findById(expenseId);
        if(expense == null) {
            log.warn(
                    "[{}] Failed to FETCH Expense Record: [expense_id] [{}]"
                    , ErrorConst.NO_RECORD_FOUND_CODE,
                    expenseId
            );
            throw new TrackWizeException(
                    ErrorConst.NO_RECORD_FOUND_CODE,
                    ErrorConst.NO_RECORD_FOUND_MSG
            );
        }

        return expenseMapStruct.toResDTO(expense);
    }

    public List<ExpenseResDTO> getExpenses(ExpenseListReqDTO reqDTO) {
        List<Expense> expenses = expenseMapper.findAllWithFilter(reqDTO);

        return expenses.stream()
                .map(expenseMapStruct::toResDTO)
                .toList();
    }
}
