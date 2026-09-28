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

let i
let j

console.log('')
console.log('=== TABUADAS DO 1 AO 10 ===')
console.log('')

for (i = 1; i <= 10; i++) {
    console.log(`Tabuada do ${i}`)
    console.log('')

    for (j = 1; j <= 10; j++) {
        tabuada = i * j
        console.log(`${i} x ${j} = ${tabuada}`)
    }

    console.log('')
}

rl.close()
