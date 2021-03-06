-- 大カテゴリマスタデータ
INSERT INTO big_category_master(
    category_name
)VALUES(
    '収入'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '投資先'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '自己投資'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '食費'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '日用品'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '趣味・娯楽'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '交際費'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '交通費'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '衣服・美容'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '健康・医療'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '自動車'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '教養・教育'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '特別な支出'
);

INSERT INTO big_category_master(
    category_name
)VALUES(
    '現金・カード'
);
INSERT INTO big_category_master(
    category_name
)VALUES(
    '水道・光熱費'
);
INSERT INTO big_category_master(
    category_name
)VALUES(
    '通信費'
);
INSERT INTO big_category_master(
    category_name
)VALUES(
    '住宅'
);
INSERT INTO big_category_master(
    category_name
)VALUES(
    '税・社会保障'
);
INSERT INTO big_category_master(
    category_name
)VALUES(
    '保険'
);
INSERT INTO big_category_master(
    category_name
)VALUES(
    'その他'
);

-- 中カテゴリマスタデータ
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    1,
    '給与',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    1,
    '副業収入',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    1,
    '不動産',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    1,
    '配当',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    1,
    'その他収入',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 投資先
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    2,
    '株式',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    2,
    '投資信託',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 自己投資
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    3,
    '書籍',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    3,
    'スクール代',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 食費
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    '食費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    '食料品',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    '外食',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    '朝ご飯',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    '昼ごはん',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    '夜ご飯',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    'カフェ',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    4,
    'その他食費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 日用品
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    5,
    '日用品',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    5,
    '子育て用品',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    5,
    'ドラッグストア',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    5,
    'お小遣い',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    5,
    'ペット用品',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    5,
    'タバコ',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    5,
    'その他日用品',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);


-- 趣味・娯楽
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    'アウトドア',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    'ゴルフ',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    'スポーツ',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    '映画・音楽・ゲーム',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    '本',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    '旅行',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    '秘密の趣味',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    6,
    'その他趣味・娯楽',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 交際費
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    7,
    '交際費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    7,
    '飲み会',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    7,
    'プレゼント代',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    7,
    '冠婚葬祭',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    7,
    'その他交際費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 交通費
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    8,
    '交通費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    8,
    '電車',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    8,
    'バス',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    8,
    'タクシー',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    8,
    '飛行機',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    8,
    'その他交通費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 衣服・美容
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    9,
    '衣服',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    9,
    'クリーニング',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    9,
    '美容院・理髪',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    9,
    '化粧品',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    9,
    'アクセサリー',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    9,
    'その他衣服・美容',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 健康・医療
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    10,
    'フィットネス',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    10,
    'ボディケア',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    10,
    '医療費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    10,
    '薬',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    10,
    'その他健康・医療',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);


-- 自動車
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    '自動車ローン',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    '道路料金',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    'ガソリン',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    '駐車場',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    '車両',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    '車検・整備',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    '自動車ほけん',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    11,
    'その他自動車',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 教養・教育
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    12,
    '書籍',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    12,
    '新聞・雑誌',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    12,
    '習いごと',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    12,
    '学費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    12,
    '塾',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    12,
    'その他教養',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 特別な消費
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    13,
    '家具・家電',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    13,
    '住宅・リフォーム',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    13,
    'その他特別な消費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 現金・カード
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    14,
    'ATM引き出し',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    14,
    'カード引き落とし',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    14,
    '電子マネー',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    14,
    '使途不明金',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    14,
    'その他現金',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 水道・光熱費
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    15,
    '光熱費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    15,
    '電気代',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    15,
    'ガス・灯油代',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    15,
    '水道代',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    15,
    'その他水道',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 通信費
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    16,
    '携帯電話',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    16,
    '固定電話',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    16,
    'インターネット',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    16,
    '放送視聴料',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    16,
    '情報サービス',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    16,
    '宅配便・運送',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    16,
    'その他通信費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 住宅
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    17,
    '住宅',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    17,
    'ローン返済',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    17,
    '管理費・積立金',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    17,
    '地震・火災保険',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    17,
    'その他住宅',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 税・社会保険
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    18,
    '所得税・住民税',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    18,
    '年金保険',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    18,
    '健康保険',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    18,
    'その他税',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- 保険
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    19,
    '生命保険',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    19,
    '医療保険',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    19,
    'その他保険',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

-- その他
INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    20,
    '仕送り',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    20,
    '事業経費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    20,
    '事業原価',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    20,
    '事業投資',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    20,
    '寄付金',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);

INSERT INTO middle_category_master(
    big_category_id,
    category_name,
    user_id,
    create_date,
    create_user,
    update_date,
    update_user,
    version,
    delete_flag
)VALUES(
    20,
    '雑費',
    'guests',
    current_timestamp,
    'guests',
    current_timestamp,
    'guests',
    1,
    false
);


