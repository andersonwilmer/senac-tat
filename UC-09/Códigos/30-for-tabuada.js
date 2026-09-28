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
console.log('=== TABUADA - "FOR" ===')
console.log('')

let i

rl.question('Digite um valor: ', (valor) => {
    valor = Number(valor)

    console.log('')

    for (i = 1; i <= 10; i++) {
        tabuada = valor * i
        console.log(`${valor} x ${i} = ${tabuada}`)
    }

    console.log('')
    console.log(`Tabuada do ${valor} concluída.`)

    rl.close()
})
