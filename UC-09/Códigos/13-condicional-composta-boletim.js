/*
DATA: 14 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
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
        if (media >= 5 && media < 7) {
            console.log(`Então o estudante está de recuperação.`)
        } else {
            console.log(`Então o estudante foi reprovado.`)
        }
    }
    rl.close()
})
})
})
