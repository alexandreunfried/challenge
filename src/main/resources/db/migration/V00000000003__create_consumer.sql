create table consumer
(
    id bigserial not null,
    name text not null,
    phone text not null,
    email text not null
)
WITHOUT OIDS;

ALTER TABLE consumer ADD PRIMARY KEY (id);
