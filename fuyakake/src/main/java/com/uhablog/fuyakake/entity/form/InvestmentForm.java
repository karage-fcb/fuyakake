package com.uhablog.fuyakake.entity.form;

import lombok.Data;

@Data
public class InvestmentForm extends BaseForm {

    /**
     * 投資先口座
     */
    private int toAccountId;

}
