/*
DATA: 11 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

/*
Crie um programa em JavaScript que solicite ao usuário: nome, ano de nascimento e ano atual.

O programa deverá calcular a idade da pessoa em anos e em semanas.

Para realizar o cálculo das semanas, considere que:
1 ano possui 12 meses
1 mês possui 4 semanas

Ao final, exiba o nome da pessoa, sua idade em anos e a quantidade de semanas de vida.
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
    let idade_semanas = idade * (4*12)

    console.log('')
    console.log(`${nome}, você tem ${idade} anos`)
    console.log(`Sua idade em semanas é ${idade_semanas} semanas`)

    rl.close()
})
})
})
