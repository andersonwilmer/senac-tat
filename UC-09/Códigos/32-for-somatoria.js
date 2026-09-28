/*
DATA: 25 de setembro de 2026
Este código está relacionado com "15-For.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

console.log('')
console.log('=== SOMATÓRIA DE 5 VALORES ===')
console.log('')

rl.question('Entre com os 5 valores separados por espaço: ', (entrada) => {
    let numeros = entrada.split(' ')
    let soma = 0
    let numero

    for (i = 0; i < 5; i++) {
        numero = Number(numeros[i])
        soma = soma + numero
    }

    console.log('')
    console.log(`A somatória dos valores é ${soma}`)

    rl.close()
})
