/*
DATA: 18 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

/*
Faça um algoritmo utilizando CASO que de reajuste de salário para os funcionários de acordo com o número de dependentes, considerando a tabela abaixo:

Dependentes | Reajustes
0           | 5%
1, 2, 3     | 10%
4, 5, 6     | 15%
Acima de 6  | 18%

Imprimir o nome do funcionário e o valor do salário com reajuste.
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Qual nome do funcionário: ', (nome) => {
rl.question('Qual o salário: R$ ', (salario) => {
rl.question('Qual o número de dependentes: ', (dependentes) => {
    salario = Number(salario)
    dependentes = Number(dependentes)
    let reajuste = 0/100

    switch (dependentes) {
        case 0:
            reajuste = salario + (salario * 5/100) // ou ... + 1.05
        break
        case 1:
        case 2:
        case 3:
            reajuste = salario + (salario * 10/100)
        break
        case 4:
        case 5:
        case 6:
            reajuste = salario + (salario * 15/100)
        break
        default:
            reajuste = salario + (salario * 18/100)
        break
    }
    console.log('')
    console.log(`${nome}, o seu salário com o reajuste é R$ ${reajuste.toFixed(2)}.`)
    rl.close()
})
})
})
