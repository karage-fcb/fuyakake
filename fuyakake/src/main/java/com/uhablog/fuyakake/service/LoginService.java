package com.uhablog.fuyakake.service;

import java.sql.Timestamp;
import java.util.Optional;

import javax.transaction.Transactional;

import com.uhablog.fuyakake.entity.UserMaster;
import com.uhablog.fuyakake.repository.LoginRepository;
import com.uhablog.fuyakake.service.interfaceClass.ILoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    
    /**
     * レポジトリクラス
     */
    @Autowired
    private LoginRepository repository;

    /**
     * ユーザー登録
     */
    @Transactional
    @Override
    public boolean signup(UserMaster user) throws DataAccessException {
        // 存在チェック
        boolean exists = repository.existsById(user.getUserId());

        // ユーザーがすでに存在する場合
        if(exists) {
            // throw new DataAccessException("ユーザーがすでに存在");
            return false;
        }

        // パスワード暗号化
        // String rawPassword = user.getPassword();
        // user.setPassword(encoder.encode(rawPassword));

        // 登録情報追加
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setCreateDate(now);
        user.setCreateUser(user.getUserId());
        user.setUpdateDate(now);
        user.setUpdateUser(user.getUserId());
        user.setVersion(1);
        user.setDeleteFlag(false);;

        // DBにユーザーを追加する
        repository.save(user);
        return true;
    }

    /**
     * ログインユーザー取得
     */
    @Override
    public UserMaster getLoginUser(String userId) {
        Optional<UserMaster> option = repository.findById(userId);
        UserMaster user = option.orElse(null);
        return user;
    }
}
