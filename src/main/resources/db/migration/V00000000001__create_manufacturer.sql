create table manufaturer
(
    id bigserial not null,
    nome text not null
)
WITHOUT OIDS;
ALTER TABLE manufaturer ADD PRIMARY KEY (id);
