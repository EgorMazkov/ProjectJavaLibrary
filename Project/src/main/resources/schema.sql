drop schema if exists library cascade;

create schema if not exists library;

CREATE table if not exists library.books
(
    Номер_книги serial primary key,
    Название_книги varchar,
    Количество_книг int,
    Автор varchar(20),
    Год_издания varchar(4)
);

CREATE table if not exists library.treaty
(
    Номер_договора serial primary key,
    Номер_сотрудника integer,
    Пароль varchar,
    Должность varchar(40),
    Номер_телефона varchar(11),
    Фамилия varchar (40),
    Имя varchar (40),
    Отчество varchar (40),
    ИНН integer,
    СНИЛС varchar (11),
    Дата_рождения date,
    Дата_начала_работы varchar
);

CREATE table if not exists library.ticket
(
    Читательский_билет serial primary key,
    Фамилия varchar (40),
    Имя varchar (40),
    Отчество varchar (40),
    Адрес varchar (100),
    Номер_телефона varchar(11)
);

CREATE table if not exists library.delivery
(
    Код_выдачи serial primary key,
    Чит_билет bigint,
    Код_книги bigint,
    Дата_выдачи date,
    Дата_возврата  date,
    foreign key (Код_книги) references library.books,
    foreign key (Чит_билет) references library.ticket
);
