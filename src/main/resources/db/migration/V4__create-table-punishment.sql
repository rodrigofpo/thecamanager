CREATE TABLE tb_punishment
(
    id                  UUID PRIMARY KEY,
    punishment_type     TEXT           NOT NULL,
    punishment_init     DATE DEFAULT CURRENT_DATE,
    punishment_final    DATE           NULL,
    punishment_duration INT  DEFAULT 2 NOT NULL,
    observation         TEXT           NULL,
    created_at          DATE DEFAULT CURRENT_DATE
);

ALTER TABLE tb_user
    ADD punishment_id UUID;

ALTER TABLE tb_user
    ADD CONSTRAINT tb_user_tb_punishment_id_fk
        FOREIGN KEY (punishment_id) REFERENCES tb_punishment;