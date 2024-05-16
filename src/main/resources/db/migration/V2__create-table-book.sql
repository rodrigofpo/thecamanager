CREATE TABLE tb_book
(
    id         UUID PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    author     VARCHAR(255) NOT NULL,
    user_id    UUID REFERENCES tb_user (id),
    created_at DATE DEFAULT CURRENT_DATE
);