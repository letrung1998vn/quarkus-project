CREATE TABLE IF NOT EXISTS account(
userName varchar(250) PRIMARY KEY,
password varchar(250)
);
insert into account(userName,password)
values ('admin','admin'),('user','user');