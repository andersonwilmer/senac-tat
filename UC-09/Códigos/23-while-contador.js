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
console.log('CONTADOR')
console.log('')

rl.question('Contar até: ', (valor) => {

    let contador = 0

    while(cont <= valor){
        console.log(contador)
        contador = contador + 1
    }

    console.log('')
    console.log('Fim da contagem...')

    rl.close()
})
