# Estrutura de Repetição Para

> **Data:** 24 de agosto de 2026

Nova estrutura de laço de repetição.

---

## Para

Estrutura com variável de controle.

Utilizada quando sabemos previamente o número de vezes que os comandos deverão ser repetidos.

---

## Atividades feitas no VisualG

### Conte de 1 à 10
```
Algoritmo "PARA"
// Contar 1 até 10


Var
   cont: inteiro


Inicio
      EscrevaL("Contagem de 1 até 10:")
      EscrevaL()
      
      PARA cont <- 1 ATE 10 PASSO 1 FACA
           EscrevaL(cont)
      FIMPARA


Fimalgoritmo
```

### Soma de 5 números
```
Algoritmo "Soma de 5 números"
// Faça um programa que receba 5 números inteiros
// e imprima a somatória desses 5 números


Var
   cont, numero, soma: inteiro


Inicio
      soma <- 0
      
      PARA cont DE 1 ATE 5 FACA
           Escreva("Entre com um valor: ")
           Leia(numero)
           
      soma <- soma + numero
      
      FIMPARA
      
      EscrevaL()
      EscrevaL("A somatória dos valores é",soma)


Fimalgoritmo
```

### Tabuada do 1 ao 5
```
Algoritmo "Tabuada 1 até 5"
// Faça um programa que imprima na tela a tabuada de 1 ao 5


Var
   cont, numero: inteiro


Inicio
      PARA cont DE 1 ATE 5 FACA
           PARA numero DE 1 ATE 10 FACA
                EscrevaL(cont," x",numero," =",cont * numero)
           FIMPARA
           EscrevaL()
      FIMPARA


Fimalgoritmo
```

### Contagem regressiva de números pares
```
Algoritmo "Contagem regressiva de números pares"
// Escreva um algoritmo que mostre os números pares
// em contagem regressiva.

Var
   numero, cont: inteiro

Inicio
      Escreva("Escreva um número: ")
      Leia(numero)
      EscrevaL()

      SE numero % 2 = 0 ENTAO
         PARA cont <- numero ATE 0 PASSO -2 FACA
              EscrevaL(cont)
         FIMPARA
      SENAO
           EscrevaL()
           EscrevaL("Erro...")
      FIMSE

Fimalgoritmo
```

### Leitura de 10 valores - quantidade e soma dos ímpares
```
Algoritmo "Leitura de 10 valores - quantidade e soma dos ímpares"
// Escreva um algoritmo que le 10 valores
// a) mostra quantos números estão entre 0 e 10.
// b) a soma entre os números ímpares digitados entre 0 e 10.


Var
   cont, numero, soma, quantidade: inteiro

Inicio
      soma <- 0
      quantidade <- 0

      PARA cont DE 1 ATE 10 FACA
           Escreva("Entre com um valor: ")
           Leia(numero)

           SE (numero >= 0) E (numero <= 10) ENTAO
              quantidade <- quantidade + 1
              SE numero % 2 = 1 ENTAO
                 soma <- soma + numero
              FIMSE
           FIMSE
      FIMPARA

      EscrevaL()
      EscrevaL("Existem",quantidade," números entre 0 e 10")
      EscrevaL()
      EscrevaL("A soma dos números ímpares entre 0 e 10 é ",soma)

Fimalgoritmo
```

### Soma de vendas por período
```
Algoritmo "Soma de vendas por período"
// Ler 10 pessoas e somar suas vendas. Somar vendas apenas de 2020
// à 2025. Se não estiver nas condições acima exiba na tela ano invalído!


Var
   cont, ano: inteiro
   venda, total_venda: real


Inicio
      total_venda <- 0

      PARA cont DE 1 ATE 10 FACA
           EscrevaL("Vendedor número",cont)

           Escreva("Digite o valor da venda: R$ ")
           Leia(venda)

           Escreva("Digite o ano da venda: ")
           Leia(ano)

         Se (ano >= 2020) e (ano <= 2025) entao
            total_venda <- total_venda + venda
         Senao
              EscrevaL("Ano inválido!")
         Fimse
           EscrevaL()
      FIMPARA
      
      EscrevaL()
      EscrevaL("O total das vendas é: R$",total_venda)


Fimalgoritmo
```
