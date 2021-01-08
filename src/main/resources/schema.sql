drop table if exists books;
drop table if exists authors;

create table authors(
  id int primary key,
  name varchar(250) not null
);

create table  books(
  id int auto_increment primary key,
  author_id int not null,
  title varchar(250) not null,
  priceOld  varchar(250) default null,
  price varchar(250) default null,
  foreign key (author_id) references authors(id)
);
