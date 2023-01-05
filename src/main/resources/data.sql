--INSERINDO PESSOAS--
insert into pessoa (nome, data_nascimento) values ('Roberto Luis', '2020-02-09');
insert into pessoa (nome, data_nascimento) values ('Joao da Silva', '1990-02-08');
insert into pessoa (nome, data_nascimento) values ('Maria do Carmo', '1993-07-23');
insert into pessoa (nome, data_nascimento) values ('Larissa Manuela', '1995-02-03');
insert into pessoa (nome, data_nascimento) values ('Joaquim Santos', '1990-05-03');
insert into pessoa (nome, data_nascimento) values ('Fabio Milton', '1993-05-26');
insert into pessoa (nome, data_nascimento) values ('Flavia Alexandra', '1996-10-27');

--INSERINDO ENDEREÇOS--
insert into endereco (cep, cidade, logradouro, numero) values ('12598569','sao paulo','Rua Vasco da Gama','138');
insert into endereco (cep, cidade, logradouro, numero) values ('22369854','sergipe','Rua Albert Silva', '1300A');
insert into endereco (cep, cidade, logradouro, numero) values ('22369854','sergipe', 'Rua Albert Silva','1300B');
insert into endereco (cep, cidade, logradouro, numero) values ('22369877', 'santos','Rua Aviao Azul', '55');
insert into endereco (cep, cidade, logradouro, numero) values ('22369633', 'marilha','Rua Aviao Preto', '77');
insert into endereco (cep, cidade, logradouro, numero) values ('22369987', 'sao bernardo','Rua Sem nome', '135');
insert into endereco (cep, cidade, logradouro, numero) values ('22344444', 'sao bernardo','Rua das Flores', '553');

--INSERINDO PESSOAS A ENDERECOS--

insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (1,2,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (2,1,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (3,5,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (4,4,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (5,3,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (6,6,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (6,1,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (7,7,false);
insert into pessoa_endereco (endereco_id, pessoa_id, is_principal) values (7,6,false);
