create table resume
(
    id         bigint auto_increment
        primary key,
    first_name varchar(45)  not null,
    last_name  varchar(45)  not null,
    headline   varchar(45)  not null,
    about      varchar(255) not null
);

create table education
(
    id             bigint auto_increment
        primary key,
    degree         varchar(45) not null,
    school         varchar(45) not null,
    academic_field varchar(45) not null,
    start_date     date        not null,
    end_date       date        null,
    resume_id      bigint      not null,
    constraint education_resume_id_fk
        foreign key (resume_id) references resume (id)
);

create table position
(
    id           bigint auto_increment
        primary key,
    role         varchar(45) not null,
    company_name varchar(45) not null,
    start_date   date        not null,
    end_date     date        null,
    resume_id    bigint      not null,
    constraint position_resume_id_fk
        foreign key (resume_id) references resume (id)
);

create table position_responsibility
(
    id          bigint auto_increment
        primary key,
    description varchar(255) not null,
    position_id bigint       not null,
    constraint position_responsibility_position_id_fk
        foreign key (position_id) references position (id)
);

create table project
(
    id                bigint auto_increment
        primary key,
    title             varchar(45)  not null,
    description       varchar(255) not null,
    start_date        date         null,
    website           varchar(255) null,
    github_repository varchar(255) null,
    preview_image     varchar(255) null,
    resume_id         bigint       not null,
    constraint project_resume_id_fk
        foreign key (resume_id) references resume (id)
);

create table skill_type
(
    id   bigint auto_increment
        primary key,
    name varchar(45) not null
);

create table skill
(
    id            bigint auto_increment
        primary key,
    name          varchar(45) null,
    resume_id     bigint      not null,
    skill_type_id bigint      not null,
    constraint skill_resume_id_fk
        foreign key (resume_id) references resume (id),
    constraint skill_skill_type_id_fk
        foreign key (skill_type_id) references skill_type (id)
);

create table contact
(
    email     varchar(45)  not null,
    github    varchar(255) not null,
    linkedin  varchar(255) null,
    resume_id bigint       not null
        primary key,
    constraint contact_resume_id_fk
        foreign key (resume_id) references resume (id)
);