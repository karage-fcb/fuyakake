INSERT INTO investment(
    investment_money,
    account_id,
    to_account_id,
    user_id,
    category_id,
    memo,
    date,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    33333,
    1,
    2,
    'uhablog',
    7,
    'S&P500',
    now(),
    current_timestamp,
    'uhablog',
    current_timestamp,
    'uhablog',
    1,
    'false'
);