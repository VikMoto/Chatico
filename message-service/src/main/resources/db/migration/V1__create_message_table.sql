
create table group_chat
(
    id          bigserial   primary key,
    name        varchar(255) not null,
    userchat_id bigint
);

alter table group_chat
    owner to postgres;

create table privat_chat
(
    id          bigserial    primary key,
    userchat_id bigint
);

alter table privat_chat
    owner to postgres;

create table message
(
    id             bigserial  primary key,
    creation_date  timestamp(6),
    text           varchar(255) not null,
    userchat_id    bigint       not null,
    group_chat_id  bigint references group_chat,
    privat_chat_id bigint references privat_chat
);

