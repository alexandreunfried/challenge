create table delivery
(
    mode text not null
)
WITHOUT OIDS;

ALTER TABLE delivery ADD PRIMARY KEY (mode);
