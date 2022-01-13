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
 
CREATE TABLE IF NOT EXISTS big_category_master(
    category_id SERIAL,
    category_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(category_id)
);

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

