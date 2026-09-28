# JavaScript - Estrutura de Repetição `for`

> **Data:** 25 de setembro de 2026

O for é uma estrutura de repetição utilizada quando sabemos quantas vezes queremos repetir um bloco de código.

---

## Estrutura Básica

```js
for (inicialização; condição; incremento) {
    // código que será repetido
}
```

### Contagem

```js
let i

for (i = 1; i <= 10; i++) {
    console.log(`${i}`)
}
```

`i = 1` → define o valor inicial do contador.  
`i <= 10` → enquanto essa condição for verdadeira, o código continua repetindo.  
`i++` → aumenta `i` em 1 a cada repetição (contrário dele é o `i--`).

### `for` aninhado

Um for pode ser colocado dentro de outro for.

O for de fora controla uma repetição, enquanto o for de dentro é executado novamente a cada repetição do for externo.

Exemplo:

```js
for (i = 1; i <= 10; i++) {
    console.log(`Tabuada do ${i}`)

    for (j = 1; j <= 10; j++) {
        tabuada = i * j
        console.log(`${i} x ${j} = ${tabuada}`)
    }
}
```

Nesse exemplo:

- O `for` externo percorre os números de 1 a 10.
- Para cada número, o `for` interno também percorre de 1 a 10.
- Assim, são feitas as tabuadas do 1 ao 10.

### `split()`

O `split()` não é específico do for, mas foi utilizado junto com ele no exercício da somatória.

Ele é utilizado para separar uma string em partes, utilizando um separador definido.

Exemplo:

```js
let entrada = '10 20 30'
let numeros = entrada.split(' ')
```

O `' '` representa um espaço. Dessa forma, o texto é separado em partes: `10 | 20 | 30`

Cada parte pode ser acessada por sua posição:

```js
numeros[0] // 10
numeros[1] // 20
numeros[2] // 30
```

Os valores obtidos pelo `split()` ainda são textos. Para utilizá-los como números, é necessário fazer a conversão com `Number()`.
