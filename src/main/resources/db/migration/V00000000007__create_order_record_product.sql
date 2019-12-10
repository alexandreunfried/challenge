create table order_record_product
(
    id_order_record bigint not null,
    id_product bigint not null,
    units double precision not null,
    unit_price double precision not null,
    amount double precision not null,
    name text not null
)
WITHOUT OIDS;

ALTER TABLE order_record_product ADD PRIMARY KEY (id_order_record, id_product);
ALTER TABLE order_record_product ADD FOREIGN KEY (id_order_record) REFERENCES order_record(id);
ALTER TABLE order_record_product ADD FOREIGN KEY (id_product) REFERENCES product(id);

