/*
DATA: 23 de setembro de 2026
Este código está relacionado com "14-Do-While.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

function fatorial() {

rl.question('Digite um valor: ', (valor) => {
    let i = Number(valor)
    let resultado = 1

    do {
        resultado = resultado * i
        i = i - 1
    } while (i > 1)

    console.log('')
    console.log(`O fatorial de ${valor} é ${resultado}.`)

    rl.question('Deseja continuar ', (resposta) => {
        resposta = resposta.toUpperCase()

        if (resposta === 'SIM') {
            fatorial()
        } else {
            rl.close()
        }
    })
})
} fatorial()
