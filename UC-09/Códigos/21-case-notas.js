/*
DATA: 18 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

/*
Faça um algortimo que leia uma nota de 0 a 10 e utilize CASO para classificar o estudante:

0 a 4 > "Reprovado"
5 a 6 > "Recuperação"
7 a 8 > "Aprovado"
9 a 10 > "Aprovado com destaque"
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Qual a nota: ', (nota) => {
    nota = Number(nota)

    switch (nota) {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
            console.log('')
            console.log('Reprovado.')
            break
        case 5:
        case 6:
            console.log('')
            console.log('Recuperação.')
            break
        case 7:
        case 8:
            console.log('')
            console.log('Aprovado.')
            break
        case 9:
        case 10:
            console.log('')
            console.log('Aprovado com destaque.')
            break
        default:
            console.log('')
            console.log('Valor mal inserido.')
            break
    }
    rl.close()
})
