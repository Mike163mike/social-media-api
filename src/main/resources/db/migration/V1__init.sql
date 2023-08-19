create table users
(
    id          uuid  not null,
    create_time timestamp(6) with time zone,
    edit_time   timestamp(6) with time zone,
    email       varchar(255),
    password    varchar(255),
    username    varchar(255),
    my_subscribers uuid[],
    i_subscribe uuid[],
    primary key (id),
    constraint unique_email unique (email),
    constraint unique_username unique (username)
);

create table message
(
    id          uuid not null,
    create_time timestamp(6) with time zone,
    edit_time   timestamp(6) with time zone,
    title       varchar(255),
    message     text,
    image       varchar(255),
    user_id     uuid,
    primary key (id),
    constraint fk_users
        foreign key (user_id)
            references users
);

create table role
(
    id   serial not null,
    name varchar(255),
    primary key (id)
);

create table users_roles
(
    user_id  uuid not null,
    roles_id int not null,
--     constraint unique_roles_id unique (roles_id),
    constraint fk_roles_id
        foreign key (roles_id)
            references role,
    constraint fk_user_id
        foreign key (user_id)
            references users
);



