create schema if not exists application;

create table if not exists application.company(
    id serial not null primary key,
    name varchar(255) not null,
    address varchar(255) not null
);

create table if not exists application.app_user(
    id serial not null primary key,
    email varchar(255) not null unique,
    name varchar(255) not null,
    age int not null,
    company_id bigint not null references application.company(id) on delete cascade
);

insert into application.company(name, address) values
('Company - 1', 'Address - 1'),
('Company - 2', 'Address - 2'),
('Company - 3', 'Address - 3'),
('Company - 4', 'Address - 4');

insert into application.app_user(email, name, age, company_id) values
('email-1@email.com', 'Felippe', 100, 1),
('email-2@email.com', 'Julia', 80, 1),
('email-3@email.com', 'Ednaldo', 59, 2),
('email-4@email.com', 'Cleunir', 82, 2),
('email-5@email.com', 'Juliana', 32, 3),
('email-6@email.com', 'Lavinia', 8, 3),
('email-7@email.com', 'Helena', 2, 4);