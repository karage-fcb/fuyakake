-- ユーザーマスタ
CREATE TABLE IF NOT EXISTS user_master(
    user_id VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) DEFAULT ' ' NOT NULL,
    user_name VARCHAR(255) DEFAULT ' ',
    create_date TIMESTAMP DEFAULT '1998-07-05' NOT NULL,
    create_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    update_date TIMESTAMP DEFAULT '1998-07-05' NOT NULL,
    update_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    delete_flag BOOLEAN DEFAULT 'FALSE' NOT NULL
);
 
 -- 大カテゴリーマスタ
CREATE TABLE IF NOT EXISTS big_category_master(
    category_id SERIAL,
    category_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(category_id)
);

-- 中カテゴリーマスタ
CREATE TABLE IF NOT EXISTS middle_category_master(
    category_id SERIAL PRIMARY KEY,
    big_category_id INTEGER NOT NULL REFERENCES big_category_master(category_id),
    category_name VARCHAR(255) DEFAULT ' ' NOT NULL,
    user_id VARCHAR(255) DEFAULT ' ' NOT NULL REFERENCES user_master(user_id),
    create_date TIMESTAMP DEFAULT '1998-07-05' NOT NULL,
    create_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    update_date TIMESTAMP DEFAULT '1998-07-05' NOT NULL,
    update_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    delete_flag BOOLEAN DEFAULT 'FALSE' NOT NULL
);

-- 口座テーブル
CREATE TABLE IF NOT EXISTS accounts(
    account_id SERIAL PRIMARY KEY,
    account_name VARCHAR(255) NOT NULL DEFAULT ' ',
    asset_amount INTEGER NOT NULL DEFAULT 0,
    category_type VARCHAR(1) NOT NULL DEFAULT ' ',
    user_id VARCHAR(255) DEFAULT ' ' NOT NULL REFERENCES user_master(user_id),
    create_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    create_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    update_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    update_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    delete_flag BOOLEAN DEFAULT 'FALSE' NOT NULL
);

-- 消費テーブル
CREATE TABLE IF NOT EXISTS consumption (
    consumption_id SERIAL PRIMARY KEY,
    consumption_money INTEGER NOT NULL DEFAULT 0,
    account_id INTEGER NOT NULL REFERENCES accounts(account_id),
    user_id VARCHAR(255) NOT NULL DEFAULT ' ' REFERENCES user_master(user_id),
    category_id INTEGER NOT NULL REFERENCES middle_category_master(category_id),
    memo VARCHAR(1024) NOT NULL DEFAULT ' ',
    date DATE NOT NULL DEFAULT '1998-07-05',
    create_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    create_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    update_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    update_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    delete_flag BOOLEAN DEFAULT 'FALSE' NOT NULL
);

-- 収入テーブル
CREATE TABLE IF NOT EXISTS incom (
    incom_id SERIAL PRIMARY KEY,
    incom_money INTEGER NOT NULL DEFAULT 0,
    account_id INTEGER NOT NULL REFERENCES accounts(account_id),
    user_id VARCHAR(255) NOT NULL DEFAULT ' ' REFERENCES user_master(user_id),
    category_id INTEGER NOT NULL REFERENCES middle_category_master(category_id),
    memo VARCHAR(1024) NOT NULL DEFAULT ' ',
    date DATE NOT NULL DEFAULT '1998-07-05',
    create_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    create_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    update_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    update_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    delete_flag BOOLEAN DEFAULT 'FALSE' NOT NULL
);

-- 投資テーブル
-- 自己投資と株・債権などを分けるフラグが必要？
-- それかやっぱりテーブル分けるべき？
CREATE TABLE IF NOT EXISTS investment (
    investment_id SERIAL PRIMARY KEY,
    investment_money INTEGER NOT NULL DEFAULT 0,
    account_id INTEGER NOT NULL REFERENCES accounts(account_id),
    to_account_id INTEGER NOT NULL REFERENCES accounts(account_id),
    user_id VARCHAR(255) NOT NULL DEFAULT ' ' REFERENCES user_master(user_id),
    category_id INTEGER NOT NULL REFERENCES middle_category_master(category_id),
    memo VARCHAR(1024) NOT NULL DEFAULT ' ',
    date DATE NOT NULL DEFAULT '1998-07-05',
    create_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    create_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    update_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    update_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    delete_flag BOOLEAN DEFAULT 'FALSE' NOT NULL
);

-- 自己投資テーブル
CREATE TABLE IF NOT EXISTS self_investment (
    investment_id SERIAL PRIMARY KEY,
    investment_money INTEGER NOT NULL DEFAULT 0,
    account_id INTEGER NOT NULL REFERENCES accounts(account_id),
    user_id VARCHAR(255) NOT NULL DEFAULT ' ' REFERENCES user_master(user_id),
    category_id INTEGER NOT NULL REFERENCES middle_category_master(category_id),
    memo VARCHAR(1024) NOT NULL DEFAULT ' ',
    date DATE NOT NULL DEFAULT '1998-07-05',
    create_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    create_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    update_date TIMESTAMP DEFAULT '1998-07-05 00:00:00' NOT NULL,
    update_user VARCHAR(255) DEFAULT ' ' NOT NULL,
    version INTEGER DEFAULT 1 NOT NULL,
    delete_flag BOOLEAN DEFAULT 'FALSE' NOT NULL
);
