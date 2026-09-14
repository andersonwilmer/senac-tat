/*
DATA: 11 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

/*
Crie um programa em JavaScript que solicite ao usuário a entrada de um número.

Em seguida, o programa deverá calcular e exibir o dobro do número informado.
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite um número: ', (valor) => {
    let dobro = 2*valor

    console.log(`O dobro do valor "${valor}" é ${dobro}`)

    rl.close()
})
