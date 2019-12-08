create table order_record
(
    id bigserial not null,
    status text not null,
    id_consumer bigint not null,
    id_payment bigint not null,
    id_delivery text not null
)
WITHOUT OIDS;

ALTER TABLE order_record ADD PRIMARY KEY (id);
ALTER TABLE order_record ADD FOREIGN KEY (id_consumer) REFERENCES consumer(id);
ALTER TABLE order_record ADD FOREIGN KEY (id_payment) REFERENCES payment(id);
ALTER TABLE order_record ADD FOREIGN KEY (id_delivery) REFERENCES delivery(mode);
