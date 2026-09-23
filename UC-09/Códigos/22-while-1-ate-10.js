/*
DATA: 22 de setembro de 2026
Este código está relacionado com "12-While.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input: process.stdin, 
    output: process.stdout
})

console.log('')
console.log('CONTADOR DE 1 ATÉ 10')
console.log('')

let contador = 1

while (cont <= 10) {
    console.log(cont)
    contador = contador + 1
}

console.log('')
console.log('Fim da contagem...')

rl.close()
