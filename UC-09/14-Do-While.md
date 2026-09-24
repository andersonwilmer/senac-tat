# JavaScript - Estrutura de Repetição `do...while`

> **Data:** 24 de setembro de 2026

O `do...while` é uma estrutura de repetição utilizada para executar um bloco de código enquanto uma condição for verdadeira.

A principal diferença em relação ao `while` é que o `do...while` **executa o código pelo menos uma vez antes de verificar a condição.**

---

## Estrutura do do...while

```js
do {
    // código que será repetido
} while (condição)
```
↳ `do` → inicia o bloco de código que será executado.  
↳ `while` → verifica a condição depois da execução.

### Contador com do...while

```js
let i = 1

do {
    console.log(i)
    i = i + 1
} while (i <= 10)
```

Nesse exemplo, o contador começa em 1 e aumenta de um em um até chegar a 10.

↳ `i = i + 1` → aumenta o valor do contador.  
↳ `i <= 10` → define até quando a repetição continuará.

### do...while com entrada de dados

O do...while também pode ser utilizado junto com rl.question().

```js
rl.question('Digite um número: ', (valor) => {

    valor = Number(valor)

    let i = 1

    do {
        let tabuada = valor * i
        console.log(`${valor} x ${i} = ${tabuada}`)
        i = i + 1
    } while (i <= 10)

    rl.close()
})
```

Nesse exemplo, o número informado pelo usuário é utilizado para calcular sua tabuada de 1 até 10.

↳ `Number(valor)` → converte o valor recebido para número.

### do...while com decremento

O do...while também pode ser utilizado quando o valor precisa diminuir a cada repetição.

Um exemplo é o cálculo de fatorial:

```js
let i = Number(valor)
let resultado = 1

do {
    resultado = resultado * i
    i = i - 1
} while (i > 1)
```

Nesse caso:

↳ `resultado = resultado * i` → realiza o cálculo do fatorial.  
↳ `i = i - 1` → diminui o contador.  
↳ `i > 1` → mantém a repetição enquanto i for maior que 1.

---

## toUpperCase()

Durante o exercício do fatorial, foi utilizado:

```js
resposta = resposta.toUpperCase()
```

↳ `toUpperCase()` → transforma as letras de um texto em letras maiúsculas.

Por exemplo:  
- sim → SIM  
- Sim → SIM  
- SIM → SIM

Por isso, depois de utilizar `toUpperCase()`, a comparação deve ser feita com letras maiúsculas:

```js
if (resposta === 'SIM') {
    fatorial()
}
```
