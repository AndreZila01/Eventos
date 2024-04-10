
Create DataBase CASA;

Create table tblLogin(
	idLogin int not null primary key IDENTITY(1,1),
	usern varchar(max) not null,
	passw varchar(max) not null,
);

Create table tblEstado(
	idEstado int not null primary key IDENTITY(1,1),
	Estado varchar(250) not null,
);

Create table tblRoles(
	idRole int not null primary key identity(1,1),
	Roles varchar(150) not null,
);

Create table tblUser(
	idUser int not null primary key IDENTITY(1,1),
	idRole int not null foreign key references tblRoles(idRole), 
	Nome varchar(100) not null,
	Apelido varchar(100) not null,
	Email varchar(150),
	Nascimento DATE not null,
	ContaCriada datetime not null,
	UltimoAcesso datetime,
);

create table tblEvento(
	idEvento int not null primary key IDENTITY(1,1),
	Titulo varchar(100) not null,
	DetalhesExtras varchar(350) not null,
	NomeRua varchar(400) not null,
	Coordenadas varchar(350),
	IdOrganizador int not null,
	DataEvento DateTime not null,
	Requesitos varchar(max) not null,
);

Create table tblDetalhesEventos(
	idDetalhesEventos int not null primary key IDENTITY(1,1),
	idEventos int not null foreign key references tblEvento(idEvento),
	idUser int not null foreign key references tblUser(idUser),
	idEstado int not null foreign key references tblEstado(idEstado),
);

insert into tblRoles(Roles) values ('Admin');
insert into tblRoles(Roles) values ('CEO');
insert into tblRoles(Roles) values ('Organizador');
insert into tblRoles(Roles) values ('Voluntario');
insert into tblRoles(Roles) values ('N Voluntario');

insert into tblEstado(Estado) values ('Por aceitar');
insert into tblEstado(Estado) values ('Rejeitado');
insert into tblEstado(Estado) values ('Aceite');

insert into tblLogin(usern, passw) values ('admin', 'admin');
insert into tblUser(Nome, Apelido, Email, Nascimento, ContaCriada, idRole) values ('admin', 'admin', 'admin@gmail.com', '2024-04-07', '2023-04-07 14:00:00', 1);

insert into tblEvento(Titulo, NomeRua, Requesitos, IdOrganizador, DataEvento, DetalhesExtras) values ('T4G', 'Av. Dom Carlos I 4, 1200-649 Lisboa', 'Ter alguma disponibilidade', 1, '2024-04-07 17:00:00', '{"\"Transporte\":\"Pelo menos 3 Carro\", \"Voluntarios\":20"}'); 
insert into tblDetalhesEventos(idEstado, idEventos, idUser) values(3, 1, 1);