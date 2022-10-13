CREATE TABLE if not exists accounts (
    user_id varchar(36) NOT NULL,
    account_id varchar(36) NOT NULL PRIMARY KEY,
    create_at date DEFAULT NULL,
    update_at date DEFAULT NULL,
    balance NUMERIC(36,2) DEFAULT 0
    );