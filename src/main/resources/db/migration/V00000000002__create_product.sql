create table product
(
    id bigserial not null,
    name text not null,
    description text not null,
    barcode text not null,
    id_manufacturer bigint not null,
    unit_price double precision
)
WITHOUT OIDS;

ALTER TABLE product ADD PRIMARY KEY (id);
ALTER TABLE product ADD FOREIGN KEY (id_manufacturer) REFERENCES manufacturer(id);

