# JavaScript - Funções

> **Data:** 23 de setembro de 2026

As funções permitem organizar um conjunto de comandos em um bloco que pode ser executado quando for necessário.

---

Em JavaScript, utilizamos a palavra `function` para criar uma função.

## Estrutura Básica

A estrutura básica de uma função é:

```js
function nomeDaFuncao() {
    // código da função
}
```
`function` → indica que uma função será criada.  
`nomeDaFuncao` → nome utilizado para identificar a função.  
`()` → indica os elementos utilizados pela função.  
`{ }` → contém os comandos que serão executados.

---

## Chamando uma função

Depois de criar uma função, podemos executá-la utilizando o seu nome seguido de ().

```js
function mostrarMensagem() {
    console.log('Olá!')
}

mostrarMensagem()
```

Nesse exemplo, a função `mostrarMensagem()` é criada e depois chamada para executar o `console.log()`.

### Chamando a função novamente

No exercício do caixa eletrônico, a função menu() é chamada novamente depois que uma operação é realizada.

```js
function menu() {
    // comandos do menu

    menu()
}
```

Isso faz com que o menu seja apresentado novamente para que o usuário possa escolher outra opção.

Nesse exercício, essa repetição foi feita através da chamada da própria função.
