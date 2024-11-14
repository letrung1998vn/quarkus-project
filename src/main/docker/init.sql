CREATE TABLE IF NOT EXISTS account(
userName varchar(250) PRIMARY KEY,
password varchar(250)
);
delete from account;
insert into account(userName,password)
values ('admin','Admin12345'),('user','User12345');