/*
DATA: 14 de setembro de 2026
Este código está relacionado com "10-JavaScript.md"
JavaScript
*/

/*
Verificar se o valor é PAR ou IMPAR.
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Informe um valor: ', (valor) => {
    valor = Number(valor)

    if (valor % 2 === 0) {
        console.log('')
        console.log(`O valor inserido "${valor}" é PAR`)
    } else {
        console.log('')
        console.log(`O valor inserido "${valor}" é IMPAR`)
    }

    rl.close()
})
