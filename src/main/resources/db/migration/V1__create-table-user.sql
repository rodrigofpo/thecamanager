CREATE TABLE tb_user
(
    id         UUID PRIMARY KEY,
    name       TEXT        NOT NULL,
    email      TEXT        NULL,
    cpf        VARCHAR(11) NOT NULL UNIQUE,
    pwd        TEXT        NOT NULL,
    profile    TEXT        NOT NULL,
    created_at DATE DEFAULT CURRENT_DATE
);