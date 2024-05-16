CREATE TYPE LOAN_STATUS AS ENUM ('solicitado', 'emprestado', 'devolvido', 'atrasado', 'perdido');

CREATE TABLE tb_loan
(
    id          UUID PRIMARY KEY,
    book_id     UUID REFERENCES tb_book (id),
    user_id     UUID REFERENCES tb_user (id),
    loan_date   DATE        DEFAULT CURRENT_DATE,
    return_date DATE        DEFAULT CURRENT_DATE,
    status      LOAN_STATUS DEFAULT 'solicitado' NOT NULL,
    created_at  DATE        DEFAULT CURRENT_DATE
);