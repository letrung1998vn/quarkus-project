CREATE TABLE IF NOT EXISTS account(
userName varchar(250) PRIMARY KEY,
password varchar(250)
);
delete from account;
insert into account(userName,password)
values ('admin','$2a$10$ABC3nO07.UbZIVY7dgrYde3rShXkjqc05sLASRylq2tsd2.0SY.JG'),('user','$2a$10$A7n31wFSJbTd.0vMNao8je1L2lFFJZ4p/EfWagjKB7Xa6oph2RZ9q');