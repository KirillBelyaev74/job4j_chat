create table role
(
    role varchar(10) primary key
);

create table room
(
    id   serial primary key,
    name varchar(20) unique not null
);

create table person
(
    id       serial primary key,
    login    varchar(50) not null unique,
    password varchar(100),
    role_id  varchar(10) references role (role)
);