package com.uhablog.fuyakake.service.interfaceClass;

import com.uhablog.fuyakake.entity.UserMaster;

public interface ILoginService {

    // ユーザー登録
    public boolean signup(UserMaster user);

    // ログインユーザー取得
    public UserMaster getLoginUser(String userId);
}
