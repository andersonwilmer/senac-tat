/*
DATA: 11 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite o primeiro valor: ', (valor1) => {
rl.question('Digite o segundo valor: ', (valor2) => {
    let soma = Number(valor1) + Number(valor2)
    let subtracao = Number(valor1) - Number(valor2)
    let multiplicacao = Number(valor1) * Number(valor2)
    let divisao = Number(valor1) / Number(valor2)

    console.log('')
    console.log('=== RESULTADOS ===')
    console.log(`Soma dos valores: ${soma}`)
    console.log(`Subtração dos valores: ${subtracao}`)
    console.log(`Multiplicação dos valores: ${multiplicacao}`)
    console.log(`Divisão dos valores: ${divisao}`)

    rl.close()
})
})
