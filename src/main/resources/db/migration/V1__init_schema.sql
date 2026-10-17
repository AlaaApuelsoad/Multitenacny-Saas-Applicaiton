create schema tenant_1;
create schema tenant_2;

create table tenant_1.employees(
id UUID primary key ,
name VARCHAR(255) NOT NULL
);

create table tenant_2.employees(
id UUID primary key ,
name VARCHAR(255) NOT NULL
);