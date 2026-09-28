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
console.log('CONTAGEM DO 1 AO 10 - "FOR"')
console.log('')

let i

for (i = 1; i <= 10; i++) {
    console.log(`${i}`)
}

rl.close()
