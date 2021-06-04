insert into role(role) values ('user');
insert into role(role) values ('admin');

insert into room(name) values ('one');
insert into room(name) values ('two');

insert into person(login, password, role_id) VALUES ('kirill', 'kirill', (select role from role where role = 'user'));
insert into person(login, password, role_id) VALUES ('nelli', 'nelli', (select role from role where role = 'user'));
insert into person(login, password, role_id) VALUES ('petr', 'petr', (select role from role where role = 'admin'));