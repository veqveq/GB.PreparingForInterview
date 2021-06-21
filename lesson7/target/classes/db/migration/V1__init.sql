create table students_tbl
(
    id_fld   bigint       not null auto_increment,
    name_fld varchar(255) not null,
    age_fld  int          not null,
    primary key (id_fld)
);

insert into students_tbl (name_fld, age_fld)
VALUES ('Bob', 20),
       ('Pit', 18),
       ('Mario', 21);