/*
DATA: 18 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

console.log('=========================')
console.log('=== CRIANÇA ESPERANÇA ===')
console.log('=========================')
console.log('[1] para doar R$ 15,00')
console.log('[2] para doar R$ 30,00')
console.log('[3] para doar R$ 50,00')
console.log('[4] para doar outro valor')
console.log('[5] para cancelar')
console.log('')

rl.question('Escolha uma das opções acima: ', (doar) => {
    doar = Number(doar)
    let valor = 0

    switch (doar) {
        case 1:
            valor = 15
        break
        case 2:
            valor = 30
        break
        case 3:
            valor = 50
        break
        case 4:
            console.log('')
            rl.question('Escolha um valor para doar: R$ ', (doacao) => {
                valor = Number(doacao)
                console.log('')
                console.log(`Sua doação foi de R$ ${valor.toFixed(2)}.`)

                rl.close()
            })
        return
        case 5:
            console.log('')
            console.log('Sua doação foi cancelada.')

            rl.close()
        return
    }
    console.log('')
    console.log(`Sua doação foi de R$ ${valor.toFixed(2)}.`)

    rl.close()
})
