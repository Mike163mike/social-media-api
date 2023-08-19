create table friend_request
(
    create_time timestamp(6) with time zone,
    id          uuid not null,
    receiver_id uuid,
    sender_id   uuid,
    primary key (id)
);

create table message
(
    create_time timestamp(6) with time zone,
    edit_time   timestamp(6) with time zone,
    id          uuid not null,
    receiver_id uuid,
    sender_id   uuid,
    message     varchar(255),
    title       varchar(255),
    primary key (id)
);

create table post
(
    create_time timestamp(6) with time zone,
    edit_time   timestamp(6) with time zone,
    id          uuid not null,
    user_id     uuid,
    image       varchar(255),
    message     varchar(255),
    title       varchar(255),
    primary key (id)
);

create table role
(
    id   serial not null,
    name varchar(255),
    primary key (id)
);

create table users
(
    create_time timestamp(6) with time zone,
    edit_time   timestamp(6) with time zone,
    id          uuid not null,
    email       varchar(255) unique,
    password    varchar(255),
    username    varchar(255) unique,
    follow      uuid[],
    followers   uuid[],
    primary key (id)
);

create table users_roles
(
    roles_id integer not null unique,
    user_id  uuid    not null
);

alter table if exists friend_request
    add constraint receiver_id_fk
        foreign key (receiver_id)
            references users;

alter table if exists friend_request
    add constraint sender_id_fk
        foreign key (sender_id)
            references users;


alter table if exists message
    add constraint receiver_fk
        foreign key (receiver_id)
            references users;

alter table if exists message
    add constraint sender_fk
        foreign key (sender_id)
            references users;

alter table if exists post
    add constraint post_user_fk
        foreign key (user_id)
            references users;

alter table if exists users_roles
    add constraint users_roles_role_fk
        foreign key (roles_id)
            references role;

alter table if exists users_roles
    add constraint users_roles_user_fk
        foreign key (user_id)
            references users;




