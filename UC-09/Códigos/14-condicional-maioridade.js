/*
DATA: 14 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})


rl.question('Digite seu nome: ', (nome) => {
rl.question('Digite seu ano de nascimento: ', (ano_nascimento) => {
rl.question('Digite o ano atual: ', (ano_atual) => {
    let idade = Number(ano_atual) - Number(ano_nascimento)

    console.log('')
    console.log(`Idade: ${idade} anos`)

    if (idade >= 18) {
        console.log(`${nome} atingiu a maioridade.`)
    } else {
        console.log(`${nome} ainda não atingiu a maioridade.`)
    }
    rl.close()
})
})
})
