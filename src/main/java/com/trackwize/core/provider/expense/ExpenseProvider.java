package com.trackwize.core.provider.expense;

import com.trackwize.common.constant.DBConst;
import com.trackwize.core.model.entity.Expense;
import org.apache.ibatis.jdbc.SQL;

public class ExpenseProvider {

    public String create(Expense expense) {
        return new SQL()
                .INSERT_INTO(DBConst.EXPENSE_TABLE)
                .VALUES("user_id", "#{userId}")
                .VALUES("amount", "#{amount}")
                .VALUES("category", "#{category}")
                .VALUES("description", "#{description}")
                .VALUES("expense_date", "#{expenseDate}")
                .VALUES("created_by", "#{createdBy}")
                .VALUES("updated_by", "#{updatedBy}")
                .toString();
    }

    public String update(Expense expense) {
        return new SQL()
                .UPDATE(DBConst.EXPENSE_TABLE)
                .SET("user_id = #{userId}")
                .SET("amount = #{amount}")
                .SET("category = #{category}")
                .SET("description = #{description}")
                .SET("expense_date = #{expenseDate}")
                .SET("updated_by = #{updatedBy}")
                .WHERE("expense_id = #{expenseId}")
                .toString();
    }

    public String delete(Long expenseId) {
        return new SQL()
                .DELETE_FROM(DBConst.EXPENSE_TABLE)
                .WHERE("expense_id = #{expenseId}")
                .WHERE("status <> 'INACTIVE'")
                .toString();
    }

    public String findById(Long expenseId) {
        return new SQL()
                .SELECT("*")
                .FROM(DBConst.EXPENSE_TABLE)
                .WHERE("expense_id = #{expenseId}")
                .WHERE("status <> 'INACTIVE'")
                .toString();
    }
}