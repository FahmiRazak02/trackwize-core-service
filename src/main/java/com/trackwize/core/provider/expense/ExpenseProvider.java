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
}