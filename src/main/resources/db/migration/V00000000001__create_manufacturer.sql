create table manufacturer
(
    id bigserial not null,
    name text not null
)
WITHOUT OIDS;

ALTER TABLE manufacturer ADD PRIMARY KEY (id);

