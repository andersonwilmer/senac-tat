/*
DATA: 22 de setembro de 2026
Este código está relacionado com "12-While.md"
JavaScript
*/

const readline = require('readline')
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
})

console.log('')
console.log('*** CAIXA ELETRÔNICO ***')
console.log('')

let saldo = 1000

function menu() {

    console.log('[1] - Consultar Saldo')
    console.log('[2] - Depositar')
    console.log('[3] - Sacar')
    console.log('[0] - Sair')
    console.log('')

    rl.question('Escolha uma opção: ', (resposta) => {

        let opcao = Number(resposta)

        switch (opcao) {

            case 1:
                console.log('')
                console.log(`Saldo atual: R$ ${saldo.toFixed(2)}`)
                console.log('')

                menu()
                break

            case 2:
                rl.question('Digite o valor do deposito: ', (valor) => {

                    valor = Number(valor)
                    saldo = saldo + valor

                    console.log('')
                    console.log('Deposito realizado com sucesso!')
                    console.log(`Novo saldo: R$ ${saldo.toFixed(2)}`)
                    console.log('')

                    menu()
                })
                break

            case 3:
                rl.question('Digite o valor do saque: ', (valor) => {

                    valor = Number(valor)

                    if (valor <= saldo) {
                        saldo = saldo - valor

                        console.log('')
                        console.log('Saque realizado com sucesso!')
                        console.log(`Novo saldo: R$ ${saldo.toFixed(2)}`)
                    } else {
                        console.log('')
                        console.log('Saldo insuficiente!')
                    }

                    console.log('')
                    menu()
                })
                break

            case 0:
                console.log('')
                console.log('Programa encerrado')
                console.log('')

                rl.close()
                break

            default:
                console.log('')
                console.log('Opção inválida')
                console.log('')

                menu()
                break
        }
    })
}

menu()
