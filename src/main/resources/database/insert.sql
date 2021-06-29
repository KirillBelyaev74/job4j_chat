insert into role(role) values ('user');
insert into role(role) values ('admin');

insert into room(name) values ('one');
insert into room(name) values ('two');

insert into person(login, password, role_id) VALUES ('user', '$2a$10$Q04sKc5FjEOw89xbBNxB4OUp0bUx8Do2EcAF.73YbqBxiY1uCIWzG', (select role from role where role = 'user'));
insert into person(login, password, role_id) VALUES ('kirill', '$2a$10$V9165O5r0DmY.OXoaEyYCeokj.EKIe4Xu/xNnNUMD9KvrDLE874k.', (select role from role where role = 'admin'));