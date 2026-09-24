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

console.log('')
console.log('=== TABUADA ===')
console.log('')

rl.question('Digite um número: ', (valor) => {
valor = Number(valor)
console.log('')

    let i = 1

    do {
        let tabuada = valor * i
        console.log(`${valor} x ${i} = ${tabuada}`)
        i = i + 1
    } while (i <= 10)

    console.log('')
    console.log(`Tabuada do ${valor} concluída.`)

    rl.close()
})
