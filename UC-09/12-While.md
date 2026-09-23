# JavaScript - Estrutura de Repetição `while`

> **Data:** 23 de setembro de 2026

O `while` permite repetir um determinado bloco de código enquanto uma condição for verdadeira.

---

## Estrutura Básica

A estrutura básica é:

```js
while (condição) {
    // código que será repetido
}
```

A condição é verificada antes de cada repetição.

Enquanto ela for verdadeira, o código dentro do while continuará sendo executado.

---

## Variável "contador" na estrutura

Nos exercícios, utilizamos uma variável para controlar a quantidade de repetições.

```js
let cont = 1

while (cont <= 10) {
    console.log(cont)
    cont = cont + 1
}
```

`cont` → variável utilizada como contador.  
`cont <= 10` → condição que determina até quando o while será executado.  
`cont = cont + 1` → aumenta o contador em 1 a cada repetição.

Essa alteração do contador é importante para que a condição eventualmente se torne falsa e o while seja encerrado.

### Utilizando valores informados pelo usuário

O limite da repetição também pode ser definido por um valor recebido pelo `rl.question()`.

```js
rl.question('Contar até: ', (valor) => {

    let cont = 0

    while (cont <= valor) {
        console.log(cont)
        cont = cont + 1
    }

})
```
Dessa forma, o número de repetições depende da informação fornecida pelo usuário.

### Utilizando while em cálculos

O while também pode ser utilizado para repetir cálculos.

Nos exercícios, utilizamos essa estrutura para criar uma tabuada:

```js
let cont = 1

while (cont <= 10) {
    let tabuada = valor * cont

    console.log(valor, ' x ', cont, ' = ', tabuada)

    cont = cont + 1
}
```

O contador é utilizado para realizar as multiplicações de 1 até 10.

---

## ♾️ Loop infinito

É necessário tomar cuidado ao utilizar o while.

Se a variável utilizada na condição não for alterada, a condição pode permanecer verdadeira indefinidamente.

Por exemplo:

```js
let cont = 0

while (cont <= 10) {
    console.log(cont)
}
```

Nesse caso, `cont` nunca aumenta e continua valendo 0. Portanto, `cont <= 10` continuará sendo verdadeiro.

Isso faz com que o programa entre em um loop infinito.
