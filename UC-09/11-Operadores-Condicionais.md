# JavaScript - Operadores e Estruturas Condicionais

> **Data:** 14, 16 e 18 de setembro de 2026

Continuação dos estudos de JavaScript, com operadores de comparação, operadores lógicos e estruturas condicionais.

---

## Comandos

### Operadores de comparação

- `=` → atribuição de valor.
- `==` → compara apenas o valor.
- `===` → compara o valor e o tipo, sendo uma igualdade estrita.

Exemplo:

```js
10 == "10"   // true
10 === "10"  // false
```

### Estrutura condicional

O `if` permite executar um bloco de código quando uma condição é verdadeira.

```js
if (idade >= 18) {
    console.log('Maior de idade')
}
```

O `else` permite executar outro bloco quando a condição é falsa.

```js
if (idade >= 18) {
    console.log('Maior de idade')
} else {
    console.log('Menor de idade')
}
```

### Operador lógico `&&`

O operador `&&` representa **E**.

As duas condições precisam ser verdadeiras.

```js
if (media >= 5 && media < 7) {
    console.log('Recuperação')
}
```

### Condicional aninhada

É possível utilizar um `if` dentro de outro `if` ou `else` para verificar condições adicionais.

```js
if (media >= 7) {
    console.log('Aprovado')
} else {
    if (media >= 5) {
        console.log('Recuperação')
    } else {
        console.log('Reprovado')
    }
}
```

### Operadores de comparação adicionais

- `!=` → verifica se dois valores são diferentes.
- `!==` → verifica se dois valores são diferentes em valor ou tipo.

```js
10 != 5    // true
10 !== "10" // true
```

O `!==` é chamado de desigualdade estrita.

### Estrutura `switch/case`

O `switch` é uma estrutura condicional semelhante ao **Escolha/Caso** do VisualG. Ele permite executar diferentes blocos de código de acordo com o valor de uma expressão.

```js
switch (opcao) {
    case 1:
        console.log('Opção 1')
        break
    case 2:
        console.log('Opção 2')
        break
    default:
        console.log('Opção inválida')
        break
}
```

* `switch` → avalia o valor informado.
* `case` → define um valor possível para comparação.
* `break` → encerra o `switch`, evitando que a execução continue nos próximos casos.
* `default` → executa quando nenhum `case` corresponde ao valor informado.

### Utilizando vários `case`

É possível agrupar vários `case` para executar o mesmo bloco de código.

```js
switch (dependentes) {
    case 1:
    case 2:
    case 3:
        console.log('Reajuste de 10%')
        break
}
```

Nesse exemplo, os valores `1`, `2` e `3` executam o mesmo bloco.

### Utilizando `return`

O `return` encerra a execução da função ou callback atual.

Nos exercícios, foi utilizado dentro de um `switch` para encerrar o callback e impedir que o código abaixo do `switch` fosse executado.

```js
case 5:
    console.log('Operação cancelada.')
    rl.close()
    return
```
