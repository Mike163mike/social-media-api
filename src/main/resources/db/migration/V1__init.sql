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
    read        boolean not null,
    create_time timestamp(6) with time zone,
    edit_time   timestamp(6) with time zone,
    id          uuid    not null,
    receiver_id uuid,
    sender_id   uuid,
    message     varchar(255),
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

create table users_in_requests
(
    in_requests_id uuid not null unique,
    user_id        uuid not null
);

create table users_out_requests
(
    out_requests_id uuid not null unique,
    user_id         uuid not null
);

create table users_roles
(
    roles_id integer not null unique,
    user_id  uuid    not null
);

alter table if exists friend_request
    add constraint FK2j9x9icn4n27jgwx9daltsi9a
        foreign key (receiver_id)
            references users;

alter table if exists friend_request
    add constraint FK5rji2dcs4fmykw6ovpsyv5ssw
        foreign key (sender_id)
            references users;

alter table if exists message
    add constraint FK9a25x9o5r7wguarxeon2a9tmr
        foreign key (receiver_id)
            references users;

alter table if exists message
    add constraint FKbi5avhe69aol2mb1lnm6r4o2p
        foreign key (sender_id)
            references users;

alter table if exists post
    add constraint FK7ky67sgi7k0ayf22652f7763r
        foreign key (user_id)
            references users;

alter table if exists users_in_requests
    add constraint FK66mohl7qoctqs5o5cqnj3er0n
        foreign key (in_requests_id)
            references friend_request;

alter table if exists users_in_requests
    add constraint FKpym4klt0yliwh77r0sv2lw32j
        foreign key (user_id)
            references users;

alter table if exists users_out_requests
    add constraint FKm4t7ve6c88b8jwpfc8ldabf2r
        foreign key (out_requests_id)
            references friend_request;

alter table if exists users_out_requests
    add constraint FKaf0lt1vyppykkdbivdyhhk030
        foreign key (user_id)
            references users;

alter table if exists users_roles
    add constraint FK15d410tj6juko0sq9k4km60xq
        foreign key (roles_id)
            references role;

alter table if exists users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id)
            references users;

