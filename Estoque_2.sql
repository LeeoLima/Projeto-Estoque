/**
* Projeto 2: Controle de estoque
* Versão 1.0
* @author Leonardo Lima
*/ 
show databases;
create database dbconest;
use dbconest;
show tables;
drop table estoque;

/*

 unique -> Impede valores duplicados no campo da tabela
 timestamp default current_timestamp -> data/hora automático
 date -> data no formato (YYYYMMDD)
 decimal (10,2)(digitos, virgulas casas decimais) -> tipo numérico real
*/

create table estoque (
	codigo int primary key auto_increment,
    barcode varchar(50) unique,
    produto varchar (100) not null,
    fabricante varchar(100) not null,
    datacad timestamp default current_timestamp,
    dataval date not null,
    quantidade int not null,
    estoquemin int not null,               
    medida varchar(50) not null,
    valor decimal(10,2),
    localizacao varchar(100)
);

describe estoque;

insert into estoque
(barcode, produto, fabricante, dataval, quantidade, estoquemin, medida, valor, localizacao)
values
('11111', 'Regua 30 cm', 'Faber', 20230531, 50, 20, 'Unidade', 2.35, 'Setor A'); 

insert into estoque
(barcode, produto, fabricante, dataval, quantidade, estoquemin, medida, valor, localizacao)
values
('22222', 'Caneta Azul caixa c/50', 'BIC', 20220531, 15, 30, 'caixa', 17.00, 'Setor B');

insert into estoque
(barcode, produto, fabricante, dataval, quantidade, estoquemin, medida, valor, localizacao)
values
('33333', 'Folha sulfite branca', 'Xamex', 20230225, 200, 50, 'Unidade', 17.50, 'Setor C');

insert into estoque
(barcode, produto, fabricante, dataval, quantidade, estoquemin, medida, valor, localizacao)
values
('44444', 'Lapis preto caixa c/50', 'Compacto', 20230122, 100, 20, 'Unidade', 15.00, 'Setor B');

insert into estoque
(barcode, produto, fabricante, dataval, quantidade, estoquemin, medida, valor, localizacao)
values
('55555', 'Cola bastão', 'Pritt', 20210721, 10, 20, 'Unidade', 12.50, 'Setor B');
 
/* CRUD update */
update estoque set  produto='Cola bastão', medida ='caixa c/10' where barcode='22222222';

delete from  estoque where barcode='88888888';

select * from estoque;

-- executando configurações matemáticas no select (inventário)
select sum(valor * quantidade) as Total from estoque;

-- relatorio de reposição de estoque 1

select * from estoque where quantidade < estoquemin;

-- relatorio de reposição de estoque 2
select codigo as Código, produto, date_format(dataval, '%d/%m/%Y') as data_validade, quantidade, estoquemin as estoque_mínimo
from estoque where quantidade <estoquemin;

-- relatório de controle de qualidade 1
select codigo, produto,  date_format(dataval, '%d/%m/%Y') as validade from estoque;

-- relatório de controle de qualidade 2
select codigo, produto,  date_format(dataval, '%d/%m/%Y') as validade, datediff(dataval, curdate()) as dias_restantes from estoque;

-- 
/*
	formatando e realizando operações no banco de dado
	Para formatar uma data usamos a função date_format
    date_format(campo, formato)
    Formato '%d%m%y' (dia, mês e ano com 2 digítos)
    Formato '%d%m%Y' (dia, mês e ano com 4 digítos)
    sum() -> função para somar valores numéricos
    datediff(data1,data2) -> função usada para obter a diferença em dias entre datas
    curdate () -> função usada para obter a data atual
*/

select codigo as código, barcode, produto, fabricante, 
date_format(datacad, '%d%m%Y') as data_cadastro, 
dataval as data_validade, quantidade, estoquemin as estoque_mínimo, medida, valor,
localizacao as localização from estoque order by produto; 



