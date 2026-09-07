/*
DATA: 03 de setembro de 2026
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

SELECT
	clientes.codigo,
	clientes.nome_cliente,
	COUNT(*) AS vendas_clientes
FROM vendas
JOIN clientes ON vendas.codigo_cliente = clientes.codigo
GROUP BY clientes.codigo, clientes.nome_cliente;

SELECT SUM(vendas.quantidade) AS total_vendido
FROM vendas;
