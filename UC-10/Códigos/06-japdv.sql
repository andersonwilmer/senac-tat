/*
DATA: 22 de setembro de 2026
Este código está relacionado a...
MySQL Workbench
*/

drop database if exists japdv;

create database if not exists japdv
default character set utf8
default collate utf8_general_ci;

show databases;

use japdv;

create table fornecedores (
	idFornecedor int auto_increment primary key,
    nome varchar(50) not null,
    fone varchar(20) not null,
    email varchar(50)
);

insert into fornecedores (nome, fone, email)
values
('Kalunga', '1199999-1111', '[kalunga@kalunga.com.br](mailto:kalunga@kalunga.com.br)'),
('Tilibra', '1199999-2222', '[vendas@tilibra.com.br](mailto:vendas@tilibra.com.br)');

create table produtos (
	idProduto int auto_increment primary key,
    codigoBarras varchar(20) unique,  -- unique = Não pode repetir
    descricao varchar(100) not null,
    categoria varchar(50),
    precoCusto decimal(10,2) not null,
    precoVenda decimal(10,2) not null,
    quantidade int not null default 0,  -- default 0 = padrão 0
    estoqueMinimo int not null default 0,
    idFornecedor int not null,
    
    constraint fk_fornecedor
    foreign key (idFornecedor)
    references fornecedores(idFornecedor)
);

describe produtos;

insert into produtos (codigoBarras, descricao, categoria, precoCusto, precoVenda, quantidade, estoqueMinimo, idFornecedor)
values
('789100000001', 'Caneta BIC Azul', 'Canetas', '1.50', '3.00', '50', '10', '1'),
('789100000002', 'Caneta BIC Vermelha', 'Canetas', '1.60', '3.20', '8', '10', '1'),
('789100000003', 'Caderno Universitário', 'Cadernos', '18.00', '29.90', '0', '5', '2'),
('789100000004', 'Régua 30 cm', 'Réguas', '5.00', '10.00', '15', '5', '1');

select*from produtos;

create table vendas (
	idVenda int auto_increment primary key,
    dataVenda datetime,
    total decimal(10,2) not null
);

describe vendas;

insert into vendas (dataVenda, total)
values
(now(), 16.00),
(now(), 16.00),
(now(), 23.00);

select*from vendas;

create table itens_venda (
	idItem int auto_increment primary key,
    idVenda int not null,
    idProduto int not null,
    quantidade int not null,
    precoUnitario decimal(10,2) not null,
    
    constraint fk_venda
    foreign key (idVenda)
    references vendas(idVenda)
    ON DELETE CASCADE,
    
    constraint fk_produto
    foreign key (idProduto)
    references produtos(idProduto)
);

insert into itens_venda (idVenda, idProduto, quantidade, precoUnitario)
values
(1, 1, 2, 3.00),
(1, 4, 1, 10.00),
(2, 2, 5, 3.20),
(3, 4, 2, 10.00),
(3, 1, 1, 3.00);

select*from itens_venda;

select
    produtos.idProduto,
    produtos.descricao,
    produtos.categoria,
    produtos.precoVenda,
    produtos.quantidade,
    produtos.estoqueMinimo,
    fornecedores.nome
from produtos
join fornecedores
on produtos.idFornecedor = fornecedores.idFornecedor
order by produtos.descricao;
