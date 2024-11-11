CREATE TABLE IF NOT EXISTS account(
username varchar(50) PRIMARY KEY,
password varchar(50)
);
insert into account(UserName,Password)
values ('admin','admin'),('user','user');