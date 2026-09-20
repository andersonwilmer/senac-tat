/*
DATA: 16 de setembro de 2026
Este código está relacionado com "11-Operadores-Condicionais.md"
JavaScript
*/

/*
Solicitar o peso e a altura do usuário e calcular o seu Índice de Massa Corporal (IMC)
Abaixo do 17 = Muito abaixo do peso
Entre 17 e 18,5 = Abaixo do peso
De 18,5 a 25 = Peso ideal
De 25 a 30 = Sobrepeso
De 30 a 35 = Obesidade
De 35 a 40 = Obesidade severa
40 ou mais = Obesidade mórbida
*/

const readline = require('readline')
const rl = readline.createInterface({
    input:process.stdin,
    output:process.stdout
})

rl.question('Digite seu peso: ', (peso) => {
rl.question('Digite sua altura: ', (altura) => {
    peso = Number(peso)
    altura = Number(altura)
    let imc = peso / (altura ** 2)

    console.log('')
    console.log(`Seu IMC é ${imc.toFixed(2)}`)

    if (imc < 17) {
        console.log(`"Muito abaixo do peso"`)
    } else {
        if (imc >= 17 && imc < 18.5) {
            console.log(`Abaixo do peso`)
        } else { 
            if (imc >= 18.5 && imc < 25) {
                console.log(`Peso ideal`)
            } else {
                if (imc >= 25 && imc < 30) {
                    console.log(`Sobrepeso`)
                } else {
                    if (imc >= 30 && imc < 35) {
                        console.log(`Obesidade`)
                    } else {
                        if (imc >= 35 && imc < 40) {
                            console.log(`Obesidade severa`)
                        } else {
                            console.log(`Obesidade mórbida`)
                        }
                    }
                }
            }
        }    
    }
    rl.close()
})
})
