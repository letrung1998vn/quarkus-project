CREATE TABLE IF NOT EXISTS account(
userName varchar(250) PRIMARY KEY,
password varchar(250)
);
delete from account;
insert into account(userName,password)
values ('admin','QWRtaW4xMjM0NQ=='),('user','VXNlcjEyMzQ1');