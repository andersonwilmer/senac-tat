/*
DATA: 10 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite o seu nome: ', (nome) => {
rl.question('Digite o primeiro valor: ', (valor1) => {
rl.question('Digite o segundo valor: ', (valor2) => {
rl.question('Digite o terceiro valor: ', (valor3) => {

    const media = (Number(valor1) + Number(valor2) + Number(valor3)) / 3

    console.log('=== RESULTADO ===')
    console.log(`Nome: ${nome}`)
    console.log(`A media dos valores é: ${media}`)

    rl.close()
})
})
})
})
