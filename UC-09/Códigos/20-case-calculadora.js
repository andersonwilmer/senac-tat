/*
DATA: 18 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

/*
Faça um algortimo que leia dois números e uma opção:

1 > Somar
2 > Subtrair
3 > Multiplicar
4 > Dividir

Utilize CASO para realizar a operação escolhida e mostrar o resultado.
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite um número: ', (n1) => {
rl.question('Digite outro número: ', (n2) => {
    n1 = Number(n1)
    n2 = Number(n2)

    console.log('')
    console.log('= Operações =')
    console.log('[1] Soma')
    console.log('[2] Subtração')
    console.log('[3] Multiplicação')
    console.log('[4] Divisão')
    console.log('')

    rl.question('Escolha uma dessas operações acima: ', (opcoes) => {
        opcoes = Number(opcoes)
        let resultado = 0

        switch (opcoes) {
            case 1:
                resultado = n1 + n2
            break
            case 2:
                resultado = n1 - n2
            break
            case 3:
                resultado = n1 * n2
            break
            case 4:
                if (n2 != 0) {
                    resultado = n1 /n2
                    console.log('')
                    console.log(`O resultado da operação é ${resultado}.`)
                } else {
                    console.log('')
                    console.log('Não foi possível concluir essa operação.')
                }
                rl.close()
            return
        }

    console.log('')
    console.log(`O resultado da operação é ${resultado}.`)
    rl.close()
    })
})
})
