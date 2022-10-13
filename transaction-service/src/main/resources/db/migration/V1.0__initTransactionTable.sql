create table if not exists transaction
(
    transaction_id        varchar(36) not null primary key,
    amount                NUMERIC(36,2) DEFAULT 0,
    create_at             timestamp,
    currency              varchar(7),
    description           varchar(255),
    transaction_direction varchar(7),
    update_at             timestamp,
    account_id            varchar(36) not null
);

