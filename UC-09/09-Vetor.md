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
// o programa


Var
   nome: vetor[1..4] de caractere
   nota1, nota2, media: vetor[1..4] de real
   i: inteiro


Inicio
      Para i de 1 ate 4 faca
           EscrevaL("Estudante N°",i)
           EscrevaL("Nome: ")
           Leia(nome[i])
           Escreva("Primeira nota do estudante: ")
           Leia(nota1[i])
           Escreva("Segunda nota do estudante: ")
           Leia(nota2[i])

           media[i] <- (nota1[i] + nota2[i]) / 2
           
           Escreval()
      FimPara
      
      Para i de 1 ate 4 faca
           EscrevaL("A média do aluno ",nome[i]," é", media[i])
      FimPara


Fimalgoritmo
```

---

## Matriz

A matriz é uma estrutura homogênea multidimensional, utilizada para organizar valores em linhas e colunas.

### Em andamento...
