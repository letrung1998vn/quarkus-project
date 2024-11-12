CREATE TABLE IF NOT EXISTS account(
userName varchar(50) PRIMARY KEY,
password varchar(50)
);
insert into account(userName,password)
values ('admin','admin'),('user','user');