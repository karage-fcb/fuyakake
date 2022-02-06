package com.uhablog.fuyakake.common;

import lombok.Data;

/**
 * 自作例外クラス
 */
public class FuyakakeException extends Exception{

    /**
     * コンストラクタ
     * @param msg
     */
    public FuyakakeException(String msg) {
        super(msg);
    }
}
