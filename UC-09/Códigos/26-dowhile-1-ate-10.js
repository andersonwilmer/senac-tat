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
console.log('CONTADOR DE 1 ATÉ 10 - DO...WHILE')
console.log('')

let i = 1

do {
    console.log(i)
    i = i + 1
} while (i <= 10)

console.log('')
console.log(`Fim da contagem.`)

rl.close()
