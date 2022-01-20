create table filmek
(
    id        INTEGER not null
        constraint filmek_pk
            primary key autoincrement,
    cim       text    not null,
    hossz     int     not null,
    korhatar  int     not null,
    rendezo   text    not null,
    szereplok text    not null,
    leiras    text    not null,
    boritokep text    not null
);

