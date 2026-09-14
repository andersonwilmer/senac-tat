/*
DATA: 11 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

/* 
Conversão de reais para dólares

Crie um programa em JavaScript que permita ao usuário informar uma quantia em reais (R$) e a cotação atual do dólar (US$).

O programa deverá calcular quantos dólares o usuário poderá comprar com a quantia informada.

Ao final, exiba o resultado com duas casas decimais
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite um valor em reais: ', (valor) => {
rl.question('Quanto vale o dólar atualmente: ', (cotacao) => {
    let resultado = Number(valor) / Number(cotacao)

    console.log('')
    console.log(`A cotação do dólar é ${resultado.toFixed(2)}`)

    rl.close()
})
})
