create table user_chat
(
    id         bigint       not null
        primary key,
    email      varchar(255) not null
            unique,
    gender     varchar(255),
    last_visit timestamp(6),
    locale     varchar(255),
    name       varchar(255) not null
            unique,
    user_pic   varchar(255)
);