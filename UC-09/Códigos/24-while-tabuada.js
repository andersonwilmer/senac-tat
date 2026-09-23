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
console.log('=== TABUADA ===')
console.log('')

rl.question('Digite um número: ', (valor) => {
console.log('')

    let contador = 1
   
    while(contador <= 10){

        tabuada = valor * contador 

        console.log(`${valor} x ${contador} = ${tabuada}`)
        contador = contador + 1
    }

    console.log('')
    console.log(`Tabuada do ${valor} concluída.`)

    rl.close()

})
