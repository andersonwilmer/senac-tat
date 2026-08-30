# Funções de Agregação, GROUP BY e CRUD

> **Data:** 25 de agosto de 2026

Agregação, agrupamento, alteração e exclusão de dados.

---

## Funções de agregação

As funções de agregação permitem realizar cálculos sobre vários registros de uma tabela.

|Função|Função|
|-|-|
|`COUNT`|Conta registros|
|`SUM`|Soma valores|
|`AVG`|Calcula a média|
|`MIN`|Retorna o menor valor|
|`MAX`|Retorna o maior valor|

Exemplos:

```sql
SELECT COUNT(*)
FROM pedidos;
-- Conta a quantidade de registros da tabela pedidos.
```

```sql
SELECT SUM(subtotal)
FROM itens_pedido;
-- Soma os valores da coluna subtotal.
```

---

## GROUP BY

O GROUP BY é utilizado para agrupar registros que possuem o mesmo valor em determinada coluna.

Exemplo:

```sql
SELECT
    id_pizza,
    SUM(quantidade)
FROM itens_pedido
GROUP BY id_pizza;
```

Nesse caso, os itens são agrupados pelo id_pizza e a quantidade é somada para cada pizza.

Também podemos relacionar o agrupamento com a tabela pizzas:

```sql
SELECT
    itens_pedido.id_pizza,
    pizzas.sabor,
    SUM(quantidade)
FROM itens_pedido
JOIN pizzas
    ON itens_pedido.id_pizza = pizzas.id
GROUP BY id_pizza;
```

Assim conseguimos visualizar o sabor da pizza junto com a quantidade total vendida.


### COUNT + WHERE

As funções de agregação também podem ser utilizadas junto com filtros.

```sql
SELECT COUNT(*)
FROM itens_pedido
WHERE subtotal > 50;
```

Nesse caso, contamos quantos itens possuem subtotal maior que 50.

### GROUP BY + JOIN + WHERE

Também utilizamos várias estruturas que já havíamos aprendido em uma mesma consulta:

```sql
SELECT
    itens_pedido.id,
    clientes.nome,
    itens_pedido.id_pizza,
    pizzas.sabor,
    SUM(itens_pedido.quantidade)
FROM itens_pedido
JOIN pizzas
    ON itens_pedido.id_pizza = pizzas.id
JOIN pedidos
    ON itens_pedido.id_pedido = pedidos.id
JOIN clientes
    ON pedidos.id_cliente = clientes.id
WHERE id_cliente = 1
GROUP BY id_pizza;
```

Essa consulta agrupa as pizzas pertencentes aos pedidos de um determinado cliente e soma suas quantidades.

---

## CRUD

CRUD representa as quatro operações básicas realizadas sobre os dados:

- C - Create → criar
- R - Read → consultar
- U - Update → atualizar
- D - Delete → excluir

Nesta aula, praticamos principalmente UPDATE e DELETE.

### UPDATE

O UPDATE é utilizado para alterar dados existentes.

```sql
UPDATE clientes
SET endereco = 'Av Paulista'
WHERE id = 5;
```

O WHERE é importante para indicar qual registro deverá ser alterado.

### DELETE

O DELETE é utilizado para excluir registros.

```sql
DELETE FROM clientes
WHERE id = 3;
```

Nesse caso, tentamos excluir o cliente de id = 3, mas o banco não permitiu a exclusão devido a uma restrição de relacionamento (constraint).

Isso mostra que as chaves estrangeiras podem impedir a exclusão de um registro que ainda está sendo utilizado por outra tabela.
