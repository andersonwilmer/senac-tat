# Vetores (Array e ArrayList)

> **Data:** 30 de setembro de 2026

Durante a aula, estudamos estruturas de dados em Java, com foco na utilização de vetores estáticos (Array) e dinâmicos (ArrayList).

---

### Contextualizando

Primeiramente, fechamos o projeto "Hello Desktop" e abrimos novamente o projeto "Fundamentos".

Dentro do diretório src, foi criado um novo Package:

Nome: `array`

Nesse package, foram criadas três classes: `Array1`, `Cartas`, `Array2`

Em todas elas, foi habilitada a opção para criar o método principal: `public static void main(String[] args)`

---

## Tipos de dados em Java

| Tipo | Utilização |
| - | - |
| String | Armazena textos. |
| char | Armazena um único caractere. |
| int | Armazena números inteiros. |
| double | Armazena números com casas decimais. |

Atenção à diferença entre as aspas:

`" "` → utilizadas para representar textos (String).  
`' '` → utilizadas para representar um único caractere (char).

---

## Atalhos no Eclipse

| Atalho | Função |
| - | - |
| Ctrl + Espaço | Exibe sugestões e completa códigos. |
| Ctrl + Shift + O | Organiza os imports e importa bibliotecas necessárias. |
| Ctrl + Shift + F | Formata e organiza o código. |
| Alt + Shift + Y | Ativa ou desativa a quebra automática de linhas. |
| . (ponto) | Permite acessar sugestões de métodos e atributos disponíveis para o objeto. |

---

## Vetores estáticos (Array)

O Array é uma estrutura de dados utilizada para armazenar vários elementos do mesmo tipo. Uma de suas características é possuir um tamanho fixo, definido no momento de sua criação.

Por exemplo, podemos criar um vetor para armazenar nomes:

```java
public static void main(String[] args) {
    // Índice          [0]      [1]     [2]      [3]
    String[] nomes = {"Bill", "José", "Bruce", "Frank"};

    // Recuperando o nome Bruce
    System.out.println(nomes[2]);

    // Tamanho do array
    System.out.println(nomes.length);

    // Percorrendo o array com o uso do laço for
    for (int i = 0; i < nomes.length; i++) {
        System.out.println(nomes[i]);
    }
}
```

`String[] nomes` → declara um vetor de textos chamado nomes.  
`{}` → contém os valores armazenados no vetor.  
`Índice` → posição de cada elemento dentro do vetor, começando em 0.  
`nomes[2]` → acessa o elemento que está na posição 2, neste caso, Bruce.  
`nomes.length` → retorna a quantidade de elementos do vetor.

---

## Random

Em seguida, criamos a classe Cartas, utilizando dois vetores para representar as cartas de um baralho.

```java
import java.util.Random;

public class Cartas {

    public static void main(String[] args) {
        String[] nipes = {"♠", "♥", "♦", "♣"};
        String[] faces = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Criando um objeto para sorteio de uma carta
        Random sorteio = new Random();

        // Sorteando um índice para a face da carta
        int indiceFace = sorteio.nextInt(faces.length);

        // Sorteando um índice para o naipe da carta
        int indiceNipe = sorteio.nextInt(nipes.length);

        System.out.println(faces[indiceFace] + nipes[indiceNipe]);
    }
}
```
`nipes` → armazena os quatro naipes do baralho.  
`faces` → armazena as representações das cartas.  
`import java.util.Random` → importa a biblioteca utilizada para gerar números aleatórios.  
`Random sorteio = new Random()` → cria um objeto responsável pelo sorteio.  
`nextInt()` → gera um número inteiro aleatório dentro do limite informado.  
`faces.length` → informa a quantidade de elementos do vetor faces.  
`nipes.length` → informa a quantidade de elementos do vetor nipes.

O método `nextInt(limite)` gera um número entre zero e o limite informado, sem incluir o próprio limite.

---

## Vetores dinâmicos (ArrayList)

Diferentemente do Array, o ArrayList permite trabalhar com uma coleção cujo tamanho pode aumentar ou diminuir durante a execução do programa.

Ele é útil em situações nas quais não sabemos antecipadamente quantos elementos serão armazenados.

Por exemplo, em um cadastro de contatos, novas pessoas podem ser adicionadas ao longo do tempo.

Para utilizar o ArrayList, precisamos importar sua biblioteca: `import java.util.ArrayList;`

Criamos a classe Array2 para trabalhar com um vetor dinâmico.

```java
import java.util.ArrayList;

public class Array2 {

    public static void main(String[] args) {
        // A linha abaixo cria um vetor dinâmico
        ArrayList<String> contatos = new ArrayList<>();

        // ArrayList
        // .add (adicionar)
        // .get (recuperar)

        // Adicionando dados ao vetor
        contatos.add("Bill Gates");
        contatos.add("1199999-1234");
        contatos.add("bill@outlook.com");
        contatos.add("Linus Torvalds");
        contatos.add("1199999-4321");
        contatos.add("linus@gmail.com");

        // Recuperando os dados da lista
        for (int i = 0; i < contatos.size(); i++) {
            System.out.println(contatos.get(i));
        }
    }
}
```

`ArrayList<String>` → cria uma lista dinâmica que armazena textos.  
`contatos` → nome da lista.  
`new ArrayList<>()` → cria uma nova lista.  
`.add()` → adiciona um elemento à lista.  
`.get()` → recupera um elemento pela sua posição.  
`.size()` → retorna a quantidade de elementos armazenados.
