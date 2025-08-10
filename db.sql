DROP SCHEMA IF EXISTS public CASCADE; 
CREATE SCHEMA public


create table users(
	id smallserial primary key ,
    creation_date date,
    first_name varchar(100) not null ,
    middle_name varchar(100) default null,
    last_name varchar(100) not null
);
create table user_login_data(
	id smallserial primary key,
    user_id smallint not null,
    nickname varchar(100) unique not null,
    session_token char(254) unique,
    password_salt char(43) not null,
    password_hash char(64) not null,
    email_address varchar(254) not null unique,
    confirmation_token char(86) null default null,
    token_generation_time timestamp null default null,
    password_recovery_time timestamp null default null,
    recovery_token_time date null default null,
    foreign key (user_id) references users(id)
);

create table role(
	id smallserial primary key,
    name varchar(64) unique not null,
	description varchar(128)	
);
create table permission(
	id smallserial primary key,
    name varchar(64) unique not null,
	description varchar(128)
);

create table user_has_role(
	id smallserial primary key,
	user_id smallint not null,
    role_id smallint not null,
	foreign key(user_id) references users(id),
    foreign key(role_id) references role(id)
);

create table role_granted_permission(
	id smallserial primary key ,
	permission_id smallint not null,
    role_id smallint not null,
	foreign key(permission_id) references permission(id),
    foreign key(role_id) references role(id)
);
create type status as enum ('pending', 'in_progress', 'completed', 'cancelled');

create table task(
	id smallserial primary key,
    user_id smallint not null,
    eliminated boolean default false,
    tittle varchar(64) not null,
    description varchar(256) null default null,
    expiration_date date default (current_date + interval '2 month'),
    creation_date date default current_timestamp,
    status status default 'pending',
    foreign key(user_id) references users(id)
);

create table comment(
	id smallserial primary key,
    task_id smallint not null,
    description varchar(256),
    foreign key(task_id) references task(id)
);