/*
DATA: 03 e 08 de setembro de 2026
Este código está relacionado a uma atividade feita pelo professor.
MySQL Workbench
*/

create database street_shoes;
use street_shoes;

create table clientes (
	codigo INT AUTO_INCREMENT PRIMARY KEY,
	nome_cliente VARCHAR(50) not null,
	telefone VARCHAR(20) not null,
	cidade VARCHAR(100)
);

describe clientes;

insert into clientes (nome_cliente, telefone, cidade)
values
('Anderson', '11999999999', 'São Paulo'),
('Alex', '11988888888', 'Rio de Janeiro'),
('Andres', '11977777777', 'Belo Horizonte'),
('Cristian', '11966666666', 'Comrumbá'),
('João', '11955555555', 'Cuiabá');

select*from clientes;

create table produtos (
	codigo int auto_increment primary key,
	nome_produto varchar(50) not null,
	marca varchar(50) not null,
	tamanho int,
	preco decimal(10,2) not null
);

describe produtos;

insert into produtos (nome_produto, marca, tamanho, preco)
values
('Air Force 1''07', 'Nike', '42', 799.90),
('Air Jordan 1 Retro', 'Nike', '41', 1299.90),
('Ultraboost Light', 'Adidas', '40', 999.99),
('Forum Low', 'Adidas', '39', 599.90),
('Old Skool Classic', 'Vans', '42', 399.90),
('All Star Chuck Taylor', 'Converse', '40', 299.90);

select*from produtos;

create table vendas (
	codigo int auto_increment primary key,
	codigo_cliente int not null,
	codigo_produto int not null,
	data_venda datetime,
	quantidade int not null,
    
	constraint fk_cliente
	foreign key (codigo_cliente)
	references clientes(codigo),
    
	constraint fk_produto
	foreign key (codigo_produto)
	references produtos(codigo)
);

describe vendas;

insert into vendas (codigo_cliente, codigo_produto, data_venda, quantidade)
values
(99, 1, now(), 1);

select*from vendas;

insert into vendas (codigo_cliente, codigo_produto, data_venda, quantidade)
values
(2, 1, now(), 1),
(1, 4, now(), 3),
(4, 3, now(), 2),
(3, 2, now(), 2),
(5, 5, now(), 1),
(1, 6, now(), 2),
(2, 1, now(), 2),
(3, 2, now(), 1);

select*from produtos
where preco > 400;

select*from produtos
where preco < 400;

select*from vendas
where quantidade > 1;

select*from produtos
order by preco asc;

select*from produtos
order by preco desc;

select
	clientes.codigo,
	clientes.nome_cliente,
	count(*) as vendas_clientes
from vendas
join clientes on vendas.codigo_cliente = clientes.codigo
group by clientes.codigo, clientes.nome_cliente;

select SUM(vendas.quantidade) as total_vendido
from vendas;

select
	vendas.codigo,
    clientes.nome_cliente,
    vendas.quantidade
from vendas
join clientes on vendas.codigo_cliente = clientes.codigo;

select
	vendas.codigo,
    produtos.nome_produto,
    produtos.marca,
    vendas.quantidade
from vendas
join produtos on vendas.codigo_produto = produtos.codigo;

select
	clientes.nome_cliente,
    produtos.nome_produto,
    produtos.marca,
    vendas.quantidade
from vendas
join clientes on vendas.codigo_cliente = clientes.codigo
join produtos on vendas.codigo_produto = produtos.codigo;

select
	vendas.codigo,
    clientes.nome_cliente,
	produtos.nome_produto,
	produtos.preco,
    vendas.quantidade
from vendas
join clientes on vendas.codigo_cliente = clientes.codigo
join produtos on vendas.codigo_produto = produtos.codigo
where preco > 400 and codigo_cliente = 1
order by preco desc;

-- NOVO DESAFIO

insert into clientes (nome_cliente, telefone, cidade)
values
('Gabriel', '11944444444', 'Salvador'),
('Lucas', '11933333333', 'Curitiba'),
('Maria', '11922222222', 'Campo Grande'),
('Mateus', '11911111111', 'Natal'),
('Pedro', '11900000000', 'Fortaleza');

select*from clientes;

insert into produtos (nome_produto, marca, tamanho, preco)
values
('Stan Smith', 'Adidas', 41, 499.90),
('Air Max 90', 'Nike', 42, 899.90),
('Suede Classic', 'Puma', 40, 379.90),
('Sk8-Hi', 'Vans', 39, 449.90),
('Gel-Lyte III', 'Asics', 43, 649.90),
('Classic Leather', 'Reebok', 41, 419.90);

select*from produtos;

insert into vendas (codigo_cliente, codigo_produto, data_venda, quantidade)
values
(9, 9, now(), 2),
(6, 12, now(), 1),
(7, 8, now(), 1),
(10, 10, now(), 2),
(8, 9, now(), 3),
(6, 11, now(), 1),
(7, 12, now(), 1),
(9, 10, now(), 2),
(6, 7, now(), 2),
(7, 7, now(), 3),
(10, 11, now(), 2),
(8, 8, now(), 1);

select*from vendas;

select
	nome_produto,
    marca,
    preco
from produtos
where preco > 600
order by preco desc;

select
	vendas.codigo,
    clientes.nome_cliente,
    produtos.nome_produto,
    vendas.quantidade
from vendas
join clientes on vendas.codigo_cliente = clientes.codigo
join produtos on vendas.codigo_produto = produtos.codigo
where quantidade > 1;

select
    clientes.nome_cliente,
    produtos.nome_produto,
    produtos.preco,
    vendas.quantidade,
    produtos.preco * vendas.quantidade as valor_total
from vendas
join clientes on vendas.codigo_cliente = clientes.codigo
join produtos on vendas.codigo_produto = produtos.codigo;

select
    clientes.nome_cliente,
    produtos.nome_produto,
    produtos.marca,
    vendas.quantidade,
    vendas.data_venda
from vendas
join clientes on vendas.codigo_cliente = clientes.codigo
join produtos on vendas.codigo_produto = produtos.codigo
where clientes.codigo = 6;
