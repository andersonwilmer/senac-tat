# Estruturas de Dados Homogêneas

> **Data:** 31 de agosto de 2026

---

Estruturas de dados homogêneas permitem armazenar vários valores do mesmo tipo de dado dentro de uma mesma estrutura.

- **Vetor** → estrutura homogênea unidimensional.
- **Matriz** → estrutura homogênea multidimensional.

---

## Vetor

O vetor permite armazenar vários valores do mesmo tipo utilizando uma única variável.

Cada valor armazenado é chamado de **elemento** e ocupa uma determinada **posição** dentro do vetor.

- **Posição** → local onde o elemento está armazenado.
- **Elemento** → valor armazenado naquela posição.

---

## Atividades feitas no VisualG - Vetor

### Armazenamento e exibição de 5 valores
```
Algoritmo "Vetor"
// Digitar 5 números


Var
   n: vetor[1..5] de inteiro
   i: inteiro  // cont ou i


Inicio
      Para i de 1 ate 5 faca
           Escreva("Digite o",i,"° valor: ")
           Leia(n[i])
      FimPara
      
      Para i de 1 ate 5 faca
           Escreva("(",n[i]," )")
      FimPara
      

Fimalgoritmo
```

### Contagem de números pares
```
Algoritmo "Pares"
// Ler 10 valores, mostrar as posições dos pares e contar quantos são


Var
   valores: vetor[1..10] de inteiro
   i, pares: inteiro


Inicio
      pares <- 0
      
      Para i de 1 ate 10 faca
           Escreva("Informe o",i,"º valor: ")
           Leia(valores[i])
      FimPara
      
      Para i de 1 ate 10 faca
           Se (valores[i] % 2 = 0) entao
              pares <- pares + 1
              
              EscrevaL()
              EscrevaL("O valor PAR na posição:",i)
           Fimse
      FimPara
      
      EscrevaL
      EscrevaL("O total de valores pares foi",pares)


Fimalgoritmo
```

### Listagem de alunos
```
Algoritmo "Listagem de Alunos"
// Criar um algoritmo que mostre 4 nomes de alunos calcula
// a media para os 4 alunos
//
// - A medida deve ser calculada da seguinte forma:
// (nota1 + nota2) / 2
//
// Após cadastrar todos os alunos e suas notas (nota1 e nota2)
// o programa, além de dizer se o estudante está aprovado
// ou reprovado.


Var
   nome: vetor[1..4] de caractere
   nota1, nota2, media: vetor[1..4] de real
   i: inteiro


Inicio
      Para i de 1 ate 4 faca
           EscrevaL("Estudante N°",i)
           Escreva("Nome: ")
           Leia(nome[i])
           Escreva("Primeira nota do estudante: ")
           Leia(nota1[i])
           Escreva("Segunda nota do estudante: ")
           Leia(nota2[i])

           media[i] <- (nota1[i] + nota2[i]) / 2

           Escreval()
      FimPara
      LimpaTela

      Para i de 1 ate 4 faca
           EscrevaL("A média do aluno ",nome[i]," é", media[i])
           Se media[i] >= 7 entao
              EscrevaL("Estudante aprovado.")
              EscrevaL()
              Senao
                   EscrevaL("Estudante reprovado.")
                   EscrevaL()
           Fimse
      FimPara


Fimalgoritmo
```

### Soma e média dos valores
```
Algoritmo "Soma e Média"
// Faça um algoritmo que armazena 10 números inteiros em um vetor.
//
// O programa deverá:
// 1. Solicitar ao usuário que digite 10 números inteiros;
// 2. Armazenar cada número em uma posição de um vetor;
// 3. Calcular a soma de todos os números digitados;
// 4. Calcular a média aritmética dos números;
// 5. Ao final, mostrar na tela:
//  a) todos os números que foram digitados;
//  b) a soma dos valores;
//  c) a média dos valores, apresentada com duas casas decimais.
//
// OBS: utilize um vetor para armazenar os 10 números e
// estruturas de repetição (para) para realizar a leitura e
// a exibição dos valores.


Var
   i: inteiro
   numero: vetor[1..10] de real
   soma: real
   media: real


Inicio
      Para i de 1 ate 10 faca
           EscrevaL("N°",i)
           Escreva("Digite um número: ")
           Leia(numero[i])
           
           soma <- soma + numero[i]
           media <- soma / i
           
           EscrevaL()
      FimPara
      
      LimpaTela
      
      Para i de 1 ate 10 faca
           EscrevaL("N°",i," é:",numero[i])
      FimPara
      
      EscrevaL()
      EscrevaL("A soma de todos os valores é:",soma)
      EscrevaL("A média dos valores é: ",media:4:2)
           

Fimalgoritmo
```

### Cadastro e Listagem de Estudantes
```
Algoritmo "Cadastro de Estudantes"
// Uma escola deseja criar um sistema simples para realizar o
// cadastro e a pesquisa de estudantes.
// Você deverá desenvolver um algoritmo capaz de armazenar os nomes
// de 6 estudantes em um vetor e, posteriormente, permitir que o
// usuário pesquise pelo nome de um estudante cadastrado.
//
// O programa deverá realizar as seguintes etapas:
// 1. Solicitar ao usuário o nome de 6 estudantes, armazenando cada
// nome em uma posição de um vetor.
// 2. Após finalizar o cadastro, apresentar na tela a lista dos estudantes
// cadastrados, mostrando também a posição em que cada estudante foi armazenado.


Var
   i: inteiro
   estudante: vetor[1..6] de caractere


Inicio
      Para i de 1 ate 6 faca
           EscrevaL("Cadastro de estudante n°",i)
           Escreva("Estudante: ")
           Leia(estudante[i])
           EscrevaL()
      FimPara
      LimpaTela
      
      Para i de 1 ate 6 faca
           EscrevaL("Estudante Nº",i," cadastrado: ",estudante[i])
      FimPara


Fimalgoritmo
```

---

## Matriz

A matriz é uma estrutura homogênea multidimensional, utilizada para organizar valores em linhas e colunas.

### Em andamento...
