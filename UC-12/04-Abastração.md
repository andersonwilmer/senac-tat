# Abstração

> **Data:** 02 de outubro de 2026

---

### Programação Orientada a Objetos


Nesta aula, iniciamos os estudos de Programação Orientada a Objetos (POO) utilizando Java.

Java é uma linguagem que utiliza a Programação Orientada a Objetos como base para a criação e organização dos programas.

---

## Abstração

A abstração consiste em criar um modelo que representa as características e comportamentos de algo.

Uma forma de entender é pensar em um modelo que descreve as peças de um conjunto de LEGO antes de construí-lo.

Na programação, esse modelo pode ser representado por uma classe.

---

## Classes e objetos

Para praticar os conceitos de POO, fechamos o projeto `Fundamentos` e criamos um novo Java Project chamado: `POO Minecraft`

Dentro do projeto, foi criado o package: `minecraft`

E dentro dele foram criadas as classes:

- `Bloco.java`
- `Mundo.java`

### Classe

A classe funciona como um modelo para os objetos.

Na classe `Bloco`, foram definidos atributos e métodos:

```java
int resistencia;
String textura;

void construir() {
    System.out.println("Bloco colocado");
}

void minerar() {
    System.out.println("Recursos obtidos");
}

void craftar() {
    System.out.println("Item criado");
}
```

Os **atributos** representam características do objeto, enquanto os **métodos** representam ações que o objeto pode realizar.

### Criando objetos

Depois de criar a classe `Bloco`, utilizamos a classe `Mundo` para criar objetos a partir desse modelo.

```java
Bloco blocoTerra = new Bloco();
```

Nesse caso, `blocoTerra` é um objeto criado a partir da classe Bloco.

Podemos então definir seus atributos:

```java
blocoTerra.resistencia = 1;
blocoTerra.textura = "Terra";
```

E utilizar os métodos definidos na classe:

```java
blocoTerra.minerar();
blocoTerra.construir();
```

O mesmo modelo pode ser utilizado para criar vários objetos com características diferentes.

Por exemplo, também foi criado um bloco de madeira:

```java
Bloco blocoMadeira = new Bloco();

blocoMadeira.resistencia = 2;
blocoMadeira.textura = "Madeira";
```

Assim, uma única classe pode servir como modelo para diferentes objetos.

---

## Construtor

Depois, adicionamos um construtor à classe `Bloco`.

O construtor possui o mesmo nome da classe e é executado quando um novo objeto é criado.

```java
public Bloco() {
    System.out.println(" ____ ");
    System.out.println("|    |");
    System.out.println("|____|");
}
```

Quando utilizamos:

```java
Bloco blocoTerra = new Bloco();
```

o construtor é executado automaticamente durante a criação do objeto.

No exemplo da aula, ele foi utilizado para exibir um desenho no console sempre que um novo bloco era criado.
