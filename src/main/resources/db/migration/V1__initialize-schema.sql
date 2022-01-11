create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists menu_items
(
    id        uuid not null
        constraint menu_items_pkey
            primary key,
    depth     integer,
    node      boolean,
    value     text,
    parent_id uuid
        constraint fk_treeable_parent_id
            references menu_items
);
