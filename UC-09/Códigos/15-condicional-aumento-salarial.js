/*
DATA: 16 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

/*
Inclua nome e o salário de um funcionário. Verifique se o salário é menor ou igual a R$ 1.000,00. Caso seja, dê um aumento de R$ 200,00 senão apenas R$ 50,00. 
Imprima o nome e o salário corrigido.
*/

const readline = require('readline')
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

rl.question('Digite o nome: ', (nome) => {
rl.question('Digite o salário: R$ ', (salario) => {
    salario = Number(salario)
    console.log('')

    if (salario <= 1000) {
        let salario_corrigido = salario + 200
        console.log(`O salário do ${nome} teve um aumento de R$ 200.00`)
        console.log(`Agora o salário reajustado é R$ ${salario_corrigido.toFixed(2)}`)
    } else {
        let salario_corrigido = salario + 50
        console.log(`O salário do ${nome} teve um aumento de R$ 50.00`)
        console.log(`Agora o salário reajustado é R$ ${salario_corrigido.toFixed(2)}`)
    }
    rl.close()
})
})
