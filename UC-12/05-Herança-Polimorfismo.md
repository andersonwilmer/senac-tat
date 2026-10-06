# Herança e Polimorfismo

> **Data:** 05 de outubro de 2026

Continuação de POO após o entendimento sobre **abstração**.

---

## Java Project: POO Carros

Antes de iniciar o novo conteúdo, continuamos o projeto `POO Carros` da aula anterior.

Na classe modelo Carro, foi criado um construtor responsável por gerar uma identificação de chassi aleatória ao criar um novo objeto.

### Gerador de caracteres aleatórios

```java
String chassi = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
```

A String funciona como uma sequência de caracteres que será utilizada como base para o sorteio.

Também foi utilizado o Random, da biblioteca do Java, para gerar valores aleatórios:

```java
Random gerador = new Random();
```

### Gerando o código do chassi

Dentro do construtor da classe Carro, foi criado um código para gerar 10 caracteres aleatórios:

```java
String chassi = new String("123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
Random gerador = new Random();

System.out.print("Chassi: * ");

for (int i = 1; i < 11; i++) {
    char numeracao = (char) gerador.nextInt(chassi.length());
    System.out.print(chassi.charAt(numeracao));
}

System.out.println(" *");
```

O funcionamento utilizado na aula foi:

- `char numeracao` → cria uma variável capaz de armazenar um caractere.  
- `(char)` → realiza um casting, convertendo o valor para o tipo char.  
- `gerador.nextInt(chassi.length())` → sorteia um número inteiro dentro da quantidade de posições existentes na String.  
- `chassi.charAt(numeracao)` → utiliza o número sorteado como posição e retorna o caractere daquela posição.  
- O `for` repete o processo 10 vezes, formando o código do chassi.

Dessa forma, o programa sorteia uma posição da String, pega o caractere daquela posição e repete o processo até formar um código com 10 caracteres.

Também foram adicionados mais dois objetos à classe Garagem:
- Camaro
- Uno

---

## Pilares da POO

Depois da abstração estudada na aula anterior, iniciamos o estudo de novos pilares da Programação Orientada a Objetos:

- Herança
- Polimorfismo

---

## Herança

A **herança** permite criar uma nova classe a partir de outra classe existente.

A nova classe, chamada de subclasse, pode reutilizar atributos e métodos da classe original, chamada de superclasse ou classe pai.

Dessa forma, podemos aproveitar características já existentes e adicionar novos comportamentos quando necessário.

### Exemplo com Minecraft

Voltamos ao projeto POO Minecraft e criamos uma nova classe chamada Enxada.

A classe modelo `Enxada` herdou características da classe `Bloco` utilizando **extends**:

```java
public class Enxada extends Bloco {
```

Isso permite que objetos de `Enxada` utilizem atributos e métodos que foram definidos anteriormente em `Bloco`.

Além disso, a classe Enxada possui seus próprios atributos e métodos:

```java
boolean conquista;

void arar() {
    System.out.println("Terra preparada para o plantio!");
    conquista = true;
}
```

Assim, a `Enxada` possui características herdadas de `Bloco` e também possui um comportamento próprio, representado pelo método `arar()`.

### Utilizando a classe herdada

Depois, foi criada a classe Itens, utilizada para criar objetos do tipo `Enxada`.

Foram criadas duas enxadas com características diferentes:

- Enxada de madeira
- Enxada de diamante

Mesmo sendo objetos da classe Enxada, eles podem utilizar atributos herdados de Bloco, como textura e resistencia.

Exemplo:

```java
Enxada enxadaMadeira = new Enxada();

enxadaMadeira.textura = "Madeira";
enxadaMadeira.resistencia = 2;
```

Também utilizamos o método próprio da classe:

```java
enxadaMadeira.arar();
```

---

## Polimorfismo

O polimorfismo permite que um método herdado seja reescrito na classe filha, fazendo com que ele tenha um comportamento diferente.

Na classe **`Bloco`**, já existia o método:

```java
void minerar() {
    System.out.println("Recursos obtidos");
}
```

Na classe **`Enxada`**, o método `minerar()` foi criado novamente com o mesmo nome:

```java
void minerar() {
    System.out.println("Dano atribuído!");
}
```

Dessa forma, quando o método `minerar()` é utilizado por um objeto de Enxada, ele executa o comportamento definido na própria classe Enxada.

A classe filha pode, portanto, modificar o comportamento de um método que já existia na classe pai.
