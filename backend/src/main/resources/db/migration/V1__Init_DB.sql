create table role
(
    id   serial not null,
    name varchar(50),
    primary key (id)
);

create table users
(
    id         bigserial not null,
    email      varchar(150),
    first_name varchar(100),
    last_name  varchar(100),
    password   varchar(255),
    role_id    integer,
    created_at timestamp,
    reset_code varchar(255),
    primary key (id)
);

create table test
(
    id          bigserial            not null,
    title       varchar(255)         not null,
    description varchar(1024),
    user_id     bigint,
    created_at  timestamp,
    settings_id bigint               not null,
    available boolean default true not null,
    primary key (id)
);

create table test_settings
(
    id             bigserial not null,
    available_from timestamp,
    available_to   timestamp,
    chat_enabled   boolean default false,
    time_limit     integer,
    max_attempts   integer default 1,
    primary key (id)
);

create table user_test
(
    id          bigserial not null,
    user_id     bigint    not null,
    test_id     bigint    not null,
    start_date  timestamp,
    submit_date timestamp,
    primary key (id)
);

create table question
(
    id                  bigserial not null,
    test_id             bigserial not null,
    value               text      not null,
    sequence            integer   not null,
    time_limit          integer,
    wait_for_time_limit boolean default false,
    score               integer,
    answer_type_id      integer   not null,
    primary key (id)
);

create table answer_type
(
    id   serial       not null,
    name varchar(100) not null,
    primary key (id)
);

create table answer
(
    id          bigserial not null,
    question_id bigint    not null,
    value       text      not null,
    correct     boolean,
    score       integer,
    primary key (id)
);

create table user_response
(
    id        bigserial not null,
    user_id   bigint    not null,
    answer_id bigint    not null,
    value     text,
    score     integer,
    primary key (id)
);

create table chat_message
(
    id           bigserial     not null,
    user_test_id bigint        not null,
    message      varchar(1024) not null,
    teacher      boolean       not null,
    date         timestamp     not null,
    primary key (id)
);

create table groups
(
    id    serial       not null,
    title varchar(150) not null,
    primary key (id)
);

create table test_group
(
    id       serial  not null,
    group_id integer not null,
    test_id  bigint  not null
);

create table user_group
(
    id       serial  not null,
    group_id integer not null,
    user_id  bigint  not null,
    primary key (id)
);


alter table if exists users
    add constraint user_role_fk foreign key (role_id) references role;

alter table if exists test
    add constraint user_test_fk foreign key (user_id) references users;

alter table if exists test
    add constraint test_settings_fk foreign key (settings_id) references test_settings;

alter table if exists user_test
    add constraint user_test_test_fk foreign key (test_id) references test,
    add constraint user_test_user_fk foreign key (user_id) references users;

alter table if exists question
    add constraint question_test_fk foreign key (test_id) references test;

alter table if exists question
    add constraint question_answerType_fk foreign key (answer_type_id) references answer_type;

alter table if exists answer
    add constraint question_fk foreign key (question_id) references question;

alter table if exists user_response
    add constraint user_response_user_fk foreign key (user_id) references users,
    add constraint user_answer_fk foreign key (answer_id) references answer;

alter table if exists chat_message
    add constraint chat_message_user_test_fk foreign key (user_test_id) references user_test;

alter table if exists test_group
    add constraint test_group_fk foreign key (group_id) references groups;

alter table if exists test_group
    add constraint test_group_test_fk foreign key (test_id) references test;

alter table if exists user_group
    add constraint group_fk foreign key (group_id) references groups;


insert into role(name)
values ('ROLE_admin'),
       ('ROLE_user');

insert into users(created_at, email, first_name, last_name, password, role_id)
values (current_timestamp, 'ciolacu.ion07@gmail.com', 'Ciolacu', 'Ion',
        '$2a$10$YjuCkqpugtkzUD.Lfh0J3.qYwBxqgI1XAT3WzKoYn7fn/IzA4GWT6', 1);