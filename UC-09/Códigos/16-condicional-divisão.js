/*
DATA: 16 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

rl.question('Digite um número: ', (n1) => {
rl.question('Digite outro número: ', (n2) => {
    n1 = Number(n1)
    n2 = Number(n2)
    console.log('')

    if (n2 != 0) {
        let divisao = n1 / n2
        console.log(`O resultado da divisão é ${divisao}.`)
    } else {
        console.log(`Não é possível dividir por zero.`)
    }
    rl.close()
})
})
