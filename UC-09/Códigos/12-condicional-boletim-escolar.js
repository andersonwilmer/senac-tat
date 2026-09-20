/*
DATA: 14 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

/*
Faça um programa que receba 3 notas de um estudante, calcula e imprima a média aritmética das notas e a mensagem de APROVADO para média superior ou igual
a 7,0 ou a mensagem de REPROVADO para média inferior a 7,0.
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('1º nota do estudante: ', (nota1) => {
rl.question('2º nota do estudante: ', (nota2) => {
rl.question('3º nota do estudante: ', (nota3) => {
    let media = (Number(nota1) + Number(nota2) + Number(nota3)) / 3

    console.log('')
    console.log(`A média do estudante é ${media.toFixed(1)}`)

    if (media >= 7) {
        console.log(`Então o estudante foi aprovado.`)
    } else {
        console.log(`Então o estudante foi reprovado.`)
    }
    rl.close()
})
})
})
