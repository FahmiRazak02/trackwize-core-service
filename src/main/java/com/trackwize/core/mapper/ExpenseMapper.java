package com.trackwize.core.mapper;

import com.trackwize.core.model.dto.ExpenseListReqDTO;
import com.trackwize.core.model.entity.Expense;
import com.trackwize.core.provider.expense.ExpenseProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpenseMapper {

    @SelectProvider(type = ExpenseProvider.class, method = "findAll")
    @Results(id = "expenseMap",value = {
            @Result(property = "expenseId", column = "expense_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "category", column = "category"),
            @Result(property = "description", column = "description"),
            @Result(property = "expenseDate", column = "expense_date"),
            @Result(property = "createdBy", column = "created_by"),
            @Result(property = "createdDate", column = "created_date"),
            @Result(property = "updatedBy", column = "updated_by"),
            @Result(property = "updatedDate", column = "updated_date")
    })
    List<Expense> findAll();

    @InsertProvider(type = ExpenseProvider.class, method = "create")
    @Options(useGeneratedKeys = true, keyProperty = "expenseId")
    int create(Expense expense);

    @UpdateProvider(type = ExpenseProvider.class, method = "update")
    int update(Expense expense);

    @DeleteProvider(type = ExpenseProvider.class, method = "delete")
    int deleteById(Long expenseId);

    @SelectProvider(type = ExpenseProvider.class, method = "findById")
    @ResultMap("expenseMap")
    Expense findById(Long expenseId);

    List<Expense> findAllWithFilter(ExpenseListReqDTO reqDTO);
}
