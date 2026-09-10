/*
DATA: 10 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

// Configurando a entrada de dados pelo usuário

// Criando interface para o usuário
const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite o seu nome: ', (nome) => {
rl.question('Digite o primeiro valor: ', (valor1) => {
rl.question('Digite o segundo valor: ', (valor2) => {

    const soma = Number(valor1) + Number(valor2)

    console.log('=== RESULTADO ===')
    console.log(`Nome: ${nome}`)
    console.log(`A soma dos valores é: ${soma}`)

    rl.close()
})
})
})
