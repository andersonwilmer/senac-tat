/*
DATA: 11 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

/*
Crie um programa em JavaScript que solicite ao usuário: nome, peso e altura

O programa deverá o IMC.

IMC: peso / altura²
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite seu nome: ', (nome) => {
rl.question('Digite seu peso: ', (peso) => {
rl.question('Digite sua altura: ', (altura) => {
    let imc = Number(peso) / Number(altura**2)

    console.log('')
    console.log('=== ÍNDICE DE MASSA CORPORAL ===')
    console.log(`${nome}, seu IMC é ${imc.toFixed(2)}`)

    rl.close()
})
})
})
