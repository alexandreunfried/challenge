create table payment
(
    id bigserial not null,
    mode text not null,
    amount double precision not null,
    installments integer not null,
    installment_value double precision not null
)
WITHOUT OIDS;

ALTER TABLE payment ADD PRIMARY KEY (id);
