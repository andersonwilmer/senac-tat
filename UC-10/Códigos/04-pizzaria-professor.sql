/*
DATA: 25 de agosto de 2026
Este código está relacionado com "05-Funções-Agregação.md"
MySQL Workbench
*/

/*
====================================================
Banco de Dados
Projeto Bella Napoli 🍕

SCHEMA OFICIAL DA TURMA
Aulas 01 a 08

Relacionamentos
CONSTRAINT
JOIN
Tabela Associativa
Normalização

Preparação para Aula 09:
Funções de Agregação

Professor: Junior Magalhães
====================================================

OBJETIVO

Este script recria o banco de dados da Bella Napoli
até o ponto em que chegamos na Aula 08.

O banco passa por uma evolução:

1. Criamos CLIENTES
2. Criamos PEDIDOS
3. Criamos PIZZAS
4. Criamos relacionamentos com FOREIGN KEY
5. Utilizamos JOIN
6. Identificamos problemas de redundância
7. Criamos ITENS_PEDIDO
8. Trabalhamos o relacionamento N:N
9. Aplicamos a normalização
10. Criamos dados para a Aula 09

A partir da Aula 06 poderemos trabalhar:

- COUNT()
- SUM()
- AVG()
- MIN()
- MAX()
- GROUP BY
- JOIN + SUM()
- ORDER BY
- HAVING

====================================================
*/


-- ============================================
-- 1. APAGAR O BANCO ANTERIOR
-- ============================================

-- ============================================
-- 2. CRIAR O BANCO
-- ============================================

CREATE DATABASE pizzaria;

USE pizzaria;


-- ============================================
-- 3. CRIAR A TABELA CLIENTES
-- ============================================

/*
A tabela CLIENTES armazena os dados
dos clientes da Bella Napoli.

PK = Primary Key
Identifica cada registro de forma única.
*/

CREATE TABLE clientes (

    id INT AUTO_INCREMENT PRIMARY KEY,

    nome VARCHAR(100),

    telefone VARCHAR(20),

    endereco VARCHAR(200)
);


-- ============================================
-- 4. CADASTRAR CLIENTES
-- ============================================

INSERT INTO clientes
(nome, telefone, endereco)
VALUES

('João',   '11999999999', 'Rua A'),

('Maria',  '11988888888', 'Rua B'),

('Pedro',  '11977777777', 'Rua C'),

('Ana',    '11966666666', 'Rua D'),

('Carlos', '11955555555', 'Rua E');


-- Conferindo os clientes

SELECT *
FROM clientes;


-- ============================================
-- 5. CRIAR A TABELA PEDIDOS
-- ============================================

/*
Neste primeiro momento estamos trabalhando
com um modelo mais simples.

PEDIDOS possui informações da pizza.

Isso é INTENCIONAL.

Durante a evolução da aula vamos perceber
que algumas dessas informações não deveriam
estar aqui.
*/

CREATE TABLE pedidos (

    id INT AUTO_INCREMENT PRIMARY KEY,

    id_cliente INT,

    sabor VARCHAR(100),

    tamanho VARCHAR(20),

    preco DECIMAL(10,2),

    data_pedido DATETIME
);


-- ============================================
-- 6. RELACIONAMENTO
--    PEDIDOS → CLIENTES
-- ============================================

/*
Cada pedido pertence a um cliente.

A CONSTRAINT cria uma regra no banco.

id_cliente de pedidos
        ↓
id de clientes
*/

ALTER TABLE pedidos

ADD CONSTRAINT fk_cliente

FOREIGN KEY (id_cliente)

REFERENCES clientes(id);


-- ============================================
-- 7. CADASTRAR PEDIDOS
--    MODELO ANTIGO
-- ============================================

/*
Ainda estamos utilizando o modelo antigo.

PEDIDOS possui:

- sabor
- tamanho
- preco

As datas são diferentes porque posteriormente
vamos utilizar esses dados para exercícios
de agregação e filtros por data.
*/

INSERT INTO pedidos
(id_cliente, sabor, tamanho, preco, data_pedido)
VALUES

-- 01
(1, 'Calabresa', 'Grande', 55.00,
 '2026-08-05 19:30:00'),

-- 02
(2, 'Portuguesa', 'Média', 48.00,
 '2026-08-05 19:45:00'),

-- 03
(3, 'Frango', 'Grande', 52.00,
 '2026-08-06 20:10:00'),

-- 04
(4, 'Margherita', 'Média', 39.90,
 '2026-08-07 18:30:00'),

-- 05
(5, 'Calabresa', 'Broto', 33.50,
 '2026-08-07 19:15:00'),

-- 06
(1, 'Portuguesa', 'Grande', 58.90,
 '2026-08-08 20:00:00'),

-- 07
(2, 'Frango', 'Média', 45.50,
 '2026-08-09 19:20:00'),

-- 08
(3, 'Margherita', 'Grande', 51.00,
 '2026-08-10 20:30:00'),

-- 09
(4, 'Calabresa', 'Média', 42.90,
 '2026-08-11 18:45:00'),

-- 10
(5, 'Portuguesa', 'Broto', 28.90,
 '2026-08-12 19:10:00'),

-- 11
(1, 'Frango', 'Grande', 62.90,
 '2026-08-13 20:15:00'),

-- 12
(2, 'Margherita', 'Broto', 31.00,
 '2026-08-14 18:30:00'),

-- 13
(3, 'Calabresa', 'Grande', 55.00,
 '2026-08-15 19:40:00'),

-- 14
(4, 'Portuguesa', 'Média', 48.00,
 '2026-08-16 20:00:00'),

-- 15
(5, 'Frango', 'Broto', 35.90,
 '2026-08-17 18:50:00'),

-- 16
(1, 'Margherita', 'Grande', 53.00,
 '2026-08-18 19:30:00'),

-- 17
(2, 'Calabresa', 'Média', 42.90,
 '2026-08-19 20:10:00'),

-- 18
(3, 'Portuguesa', 'Grande', 58.90,
 '2026-08-20 18:40:00'),

-- 19
(4, 'Frango', 'Média', 45.50,
 '2026-08-21 19:00:00'),

-- 20
(5, 'Margherita', 'Grande', 66.40,
 '2026-08-21 20:00:00');


-- Conferindo os pedidos

SELECT *
FROM pedidos;


-- ============================================
-- 8. CRIAR A TABELA PIZZAS
-- ============================================

/*
Agora criamos o CARDÁPIO.

As informações da pizza passam a existir
em uma tabela própria.

PIZZAS possui:

- id
- sabor
- tamanho
- preco
*/

CREATE TABLE pizzas (

    id INT AUTO_INCREMENT PRIMARY KEY,

    sabor VARCHAR(100),

    tamanho VARCHAR(20),

    preco DECIMAL(10,2)
);


-- ============================================
-- 9. CADASTRAR AS PIZZAS
-- ============================================

INSERT INTO pizzas
(sabor, tamanho, preco)
VALUES

('Calabresa', 'Média', 42.90),

('Margherita', 'Média', 39.90),

('Frango c/ Catupiry', 'Grande', 48.00),

('Portuguesa', 'Média', 45.00);


-- Conferindo o cardápio

SELECT *
FROM pizzas;


-- ============================================
-- 10. ADICIONAR TEMPORARIAMENTE
--     PEDIDOS → PIZZAS
-- ============================================

/*
Agora vamos experimentar uma relação direta
entre PEDIDOS e PIZZAS.

A coluna id_pizza será uma FOREIGN KEY.

Este relacionamento será posteriormente
substituído pela tabela ITENS_PEDIDO.
*/

ALTER TABLE pedidos

ADD COLUMN id_pizza INT;


-- ============================================
-- 11. CRIAR CONSTRAINT
--     PEDIDOS → PIZZAS
-- ============================================

ALTER TABLE pedidos

ADD CONSTRAINT fk_pedido_pizza

FOREIGN KEY (id_pizza)

REFERENCES pizzas(id);


-- ============================================
-- 12. RELACIONAR ALGUNS PEDIDOS ÀS PIZZAS
-- ============================================

/*
Aqui usamos o relacionamento direto
apenas para demonstrar o conceito.

Os cinco pedidos abaixo são adicionados
sem informar preço.

O preço já está em PIZZAS.
*/

INSERT INTO pedidos
(id_cliente, id_pizza, data_pedido)
VALUES

(1, 1, '2026-08-21 20:10:00'),

(2, 2, '2026-08-21 20:20:00'),

(3, 3, '2026-08-21 20:30:00'),

(4, 4, '2026-08-21 20:40:00'),

(5, 1, '2026-08-21 20:50:00');


-- Conferindo

SELECT *
FROM pedidos;


-- ============================================
-- 13. CONSULTAR AS ESTRUTURAS
-- ============================================

DESCRIBE clientes;

DESCRIBE pedidos;

DESCRIBE pizzas;


-- ============================================
-- 14. CONFERIR AS CONSTRAINTS
-- ============================================

SHOW CREATE TABLE pedidos;


-- ============================================
-- 15. JOIN
--     PEDIDOS → CLIENTES
-- ============================================

SELECT

    pedidos.id,

    clientes.nome,

    pedidos.sabor,

    pedidos.preco

FROM pedidos

JOIN clientes

ON pedidos.id_cliente = clientes.id;


-- ============================================
-- 16. JOIN
--     PEDIDOS → CLIENTES → PIZZAS
-- ============================================

SELECT

    pedidos.id,

    clientes.nome,

    pizzas.sabor,

    pizzas.tamanho,

    pizzas.preco

FROM pedidos

JOIN clientes

ON pedidos.id_cliente = clientes.id

JOIN pizzas

ON pedidos.id_pizza = pizzas.id;


-- ============================================
-- 17. OBSERVANDO O PROBLEMA
-- ============================================

/*
Temos informações de pizza em dois lugares.

PEDIDOS
- sabor
- tamanho
- preco

PIZZAS
- sabor
- tamanho
- preco

Isso gera REDUNDÂNCIA.

Além disso:

Um pedido pode possuir várias pizzas.

Uma única coluna id_pizza não é suficiente.

Precisamos de uma tabela associativa.

ITENS_PEDIDO
*/


-- ============================================
-- 18. CRIAR ITENS_PEDIDO
-- ============================================

/*
ITENS_PEDIDO será responsável por relacionar:

PEDIDOS ↔ PIZZAS

Um pedido pode possuir vários itens.

Uma pizza pode aparecer em vários pedidos.

Temos um relacionamento N:N.
*/

CREATE TABLE itens_pedido (

    id INT AUTO_INCREMENT PRIMARY KEY,

    id_pedido INT NOT NULL,

    id_pizza INT NOT NULL,

    quantidade INT NOT NULL,

    subtotal DECIMAL(10,2),

    CONSTRAINT fk_item_pedido

    FOREIGN KEY (id_pedido)

    REFERENCES pedidos(id),

    CONSTRAINT fk_item_pizza

    FOREIGN KEY (id_pizza)

    REFERENCES pizzas(id)
);


-- ============================================
-- 19. INSERIR ITENS DOS PEDIDOS
-- ============================================

/*
IMPORTANTE:

Não estamos utilizando:

id_pedido = 26

ou qualquer outro ID fixo.

O próprio banco localizará o pedido
pela data e hora.

Isso evita depender do AUTO_INCREMENT.

O subtotal representa:

preço × quantidade
*/


-- Pedido 1
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 1, 42.90
FROM pedidos
WHERE data_pedido = '2026-08-05 19:30:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 1, 39.90
FROM pedidos
WHERE data_pedido = '2026-08-05 19:30:00';


-- Pedido 2
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 2, 85.80
FROM pedidos
WHERE data_pedido = '2026-08-05 19:45:00';


-- Pedido 3
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 1, 48.00
FROM pedidos
WHERE data_pedido = '2026-08-06 20:10:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-06 20:10:00';


-- Pedido 4
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 1, 39.90
FROM pedidos
WHERE data_pedido = '2026-08-07 18:30:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 1, 48.00
FROM pedidos
WHERE data_pedido = '2026-08-07 18:30:00';


-- Pedido 5
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 1, 42.90
FROM pedidos
WHERE data_pedido = '2026-08-07 19:15:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-07 19:15:00';


-- Pedido 6
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 2, 96.00
FROM pedidos
WHERE data_pedido = '2026-08-08 20:00:00';


-- Pedido 7
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 1, 39.90
FROM pedidos
WHERE data_pedido = '2026-08-09 19:20:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-09 19:20:00';


-- Pedido 8
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 2, 85.80
FROM pedidos
WHERE data_pedido = '2026-08-10 20:30:00';


-- Pedido 9
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 1, 48.00
FROM pedidos
WHERE data_pedido = '2026-08-11 18:45:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-11 18:45:00';


-- Pedido 10
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 2, 90.00
FROM pedidos
WHERE data_pedido = '2026-08-12 19:10:00';


-- Pedido 11
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 1, 42.90
FROM pedidos
WHERE data_pedido = '2026-08-13 20:15:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 1, 39.90
FROM pedidos
WHERE data_pedido = '2026-08-13 20:15:00';


-- Pedido 12
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 1, 48.00
FROM pedidos
WHERE data_pedido = '2026-08-14 18:30:00';


-- Pedido 13
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 2, 85.80
FROM pedidos
WHERE data_pedido = '2026-08-15 19:40:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-15 19:40:00';


-- Pedido 14
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 1, 39.90
FROM pedidos
WHERE data_pedido = '2026-08-16 20:00:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 1, 48.00
FROM pedidos
WHERE data_pedido = '2026-08-16 20:00:00';


-- Pedido 15
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-17 18:50:00';


-- Pedido 16
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 2, 96.00
FROM pedidos
WHERE data_pedido = '2026-08-18 19:30:00';


-- Pedido 17
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 1, 42.90
FROM pedidos
WHERE data_pedido = '2026-08-19 20:10:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 2, 79.80
FROM pedidos
WHERE data_pedido = '2026-08-19 20:10:00';


-- Pedido 18
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-20 18:40:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 1, 48.00
FROM pedidos
WHERE data_pedido = '2026-08-20 18:40:00';


-- Pedido 19
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 3, 128.70
FROM pedidos
WHERE data_pedido = '2026-08-21 19:00:00';


-- Pedido 20
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 1, 39.90
FROM pedidos
WHERE data_pedido = '2026-08-21 20:00:00';

INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 2, 90.00
FROM pedidos
WHERE data_pedido = '2026-08-21 20:00:00';


-- Pedidos criados temporariamente
-- com id_pizza

-- Pedido 21
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 1, 42.90
FROM pedidos
WHERE data_pedido = '2026-08-21 20:10:00';


-- Pedido 22
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 2, 1, 39.90
FROM pedidos
WHERE data_pedido = '2026-08-21 20:20:00';


-- Pedido 23
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 3, 1, 48.00
FROM pedidos
WHERE data_pedido = '2026-08-21 20:30:00';


-- Pedido 24
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 4, 1, 45.00
FROM pedidos
WHERE data_pedido = '2026-08-21 20:40:00';


-- Pedido 25
INSERT INTO itens_pedido
(id_pedido, id_pizza, quantidade, subtotal)

SELECT id, 1, 2, 85.80
FROM pedidos
WHERE data_pedido = '2026-08-21 20:50:00';


-- ============================================
-- 20. CONSULTAR OS ITENS
-- ============================================

SELECT
    itens_pedido.id_pedido,
    pizzas.sabor,
    itens_pedido.quantidade,
    itens_pedido.subtotal

FROM itens_pedido

JOIN pizzas

ON itens_pedido.id_pizza = pizzas.id;


-- ============================================
-- 21. CONSULTAR UM PEDIDO
-- ============================================

SELECT *

FROM pedidos

WHERE id = 1;


-- ============================================
-- 22. CONFERIR A ESTRUTURA
-- ============================================

SHOW CREATE TABLE pedidos;


-- ============================================
-- 23. NORMALIZAÇÃO
-- ============================================

/*
Agora vamos corrigir o modelo.

ITENS_PEDIDO já é responsável por relacionar
PEDIDOS e PIZZAS.

Por isso o relacionamento direto:

PEDIDOS → PIZZAS

não será mais necessário.
*/


-- ============================================
-- 24. REMOVER A CONSTRAINT
-- ============================================

ALTER TABLE pedidos

DROP FOREIGN KEY fk_pedido_pizza;


-- ============================================
-- 25. REMOVER id_pizza
-- ============================================

ALTER TABLE pedidos

DROP COLUMN id_pizza;


-- ============================================
-- 26. REMOVER DADOS DE PIZZA DE PEDIDOS
-- ============================================

/*
Estas informações pertencem à tabela PIZZAS.

Por isso serão removidas de PEDIDOS.
*/

ALTER TABLE pedidos

DROP COLUMN sabor,

DROP COLUMN tamanho,

DROP COLUMN preco;


-- ============================================
-- 27. CONFERIR A NOVA ESTRUTURA
-- ============================================

DESCRIBE pedidos;


/*
Resultado esperado:

id
id_cliente
data_pedido
*/


-- ============================================
-- 28. CONSULTAR ITENS NOVAMENTE
-- ============================================

SELECT

    itens_pedido.id_pedido,

    pizzas.sabor,

    itens_pedido.quantidade,

    itens_pedido.subtotal

FROM itens_pedido

JOIN pizzas

ON itens_pedido.id_pizza = pizzas.id;


-- ============================================
-- 29. 🍕 UNINDO AS QUATRO TABELAS
-- ============================================

/*
Agora temos:

CLIENTES
    ↓
PEDIDOS
    ↓
ITENS_PEDIDO
    ↓
PIZZAS
*/

SELECT

    clientes.nome,

    clientes.telefone,

    clientes.endereco,

    pedidos.id,

    pedidos.data_pedido,

    pizzas.sabor,

    itens_pedido.quantidade,

    itens_pedido.subtotal

FROM pedidos

JOIN clientes

ON pedidos.id_cliente = clientes.id

JOIN itens_pedido

ON itens_pedido.id_pedido = pedidos.id

JOIN pizzas

ON itens_pedido.id_pizza = pizzas.id;


-- ============================================
-- 30. CONFERIR OS PEDIDOS
-- ============================================

SELECT *

FROM pedidos

ORDER BY data_pedido;


-- ============================================
-- 31. CONFERIR TODOS OS ITENS
-- ============================================

SELECT *

FROM itens_pedido

ORDER BY id_pedido;


-- ============================================
-- 32. CONFERÊNCIA FINAL
-- ============================================

SHOW TABLES;

DESCRIBE clientes;

DESCRIBE pedidos;

DESCRIBE itens_pedido;

DESCRIBE pizzas;


/*
====================================================
ESTRUTURA FINAL
====================================================

CLIENTES
    │
    │ 1:N
    ▼
PEDIDOS
    │
    │ 1:N
    ▼
ITENS_PEDIDO
    │
    │ N:1
    ▼
PIZZAS


PEDIDOS

- id
- id_cliente
- data_pedido


ITENS_PEDIDO

- id
- id_pedido
- id_pizza
- quantidade
- subtotal


PIZZAS

- id
- sabor
- tamanho
- preco


====================================================
DADOS PREPARADOS PARA AULA 06
====================================================

Agora temos:

✔ Vários clientes
✔ Vários pedidos
✔ Várias pizzas
✔ Várias quantidades
✔ Vários subtotais
✔ Pedidos em datas diferentes
✔ Vários pedidos no dia 21/08/2026

Isso permitirá trabalhar:

COUNT()
SUM()
AVG()
MIN()
MAX()

GROUP BY

JOIN + SUM()

ORDER BY

HAVING


====================================================
DESAFIO DA AULA 06 🍕

Don Giuseppe pergunta:

"Professor...

Quanto a Bella Napoli vendeu?"

"Quantos pedidos temos?"

"Quantas pizzas foram vendidas?"

"Qual foi o maior valor?"

"Qual foi o menor valor?"

"Qual pizza vendeu mais?"

"Quanto cada cliente gastou?"

"Quem gastou mais de R$ 100?"

Agora o banco tem dados suficientes
para responder essas perguntas.

====================================================
FIM DO SCHEMA OFICIAL
====================================================
*/

select count(*)
from pedidos;

select count(id)
from pedidos;

select sum(subtotal)
from itens_pedido;

describe clientes;
describe pedidos;
describe itens_pedido;
describe pizzas;
select*from pizzas;
select*from pedidos;
select*from clientes;

show tables;

select avg(subtotal)
from itens_pedido;

select min(subtotal)
from itens_pedido;

select max(subtotal)
from itens_pedido;

select id_pizza,
	sum(quantidade)
from itens_pedido
GROUP BY id_pizza;

select 
	itens_pedido.id_pizza,
    pizzas.sabor,
	sum(quantidade)
from itens_pedido
join pizzas on itens_pedido.id_pizza = pizzas.id
GROUP BY id_pizza;

select*from itens_pedido;

select count(*)
from itens_pedido
where subtotal > 50;

select
	itens_pedido.id,
	clientes.nome,
	itens_pedido.id_pizza,
    pizzas.sabor,
	sum(itens_pedido.quantidade)
from itens_pedido
join pizzas on itens_pedido.id_pizza = pizzas.id
join pedidos on itens_pedido.id_pedido = pedidos.id
join clientes on pedidos.id_cliente = clientes.id
where id_cliente = 1
group by id_pizza;

UPDATE clientes
SET	telefone = '11999999999'
where id = 1;

UPDATE clientes
SET nome = 'Fofis'
where id = 5;

DELETE FROM clientes
where id = 3;
-- o constraint não permitiu deletar

UPDATE clientes
SET endereco = 'Av Paulista'
where id = 5;
