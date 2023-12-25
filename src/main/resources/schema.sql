create table user
(
    id           int         not null auto_increment,
    phone_number varchar(20),
    role         int         not null,
    email        varchar(50) not null unique,
    password     varchar(50) not null,
    first_name   varchar(50) not null,
    last_name    varchar(50) not null,
    gender       int         not null,
    primary key (id)
);

create table adopter
(
    id int not null,
    primary key (id),
    foreign key (id) references user (id)
);

create table staff
(
    id int not null,
    primary key (id),
    foreign key (id) references user (id)
);

create table manager
(
    id int not null,
    primary key (id),
    foreign key (id) references user (id)
);