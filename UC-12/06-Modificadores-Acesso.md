# Modificadores de Acesso e Operações Bancárias em Java

> **Data:** 07 de outubro de 2026

---

## Modificadores de acesso

Durante a aula, foi apresentado o tema Encapsulamento, último pilar da Programação Orientada a Objetos (POO). Entretanto, nesta etapa, trabalhamos principalmente os modificadores de acesso e a utilização de métodos em um projeto Java.

Os modificadores de acesso definem quais partes do programa podem acessar os atributos e métodos de uma classe.

### Os quatro níveis de acesso

Do mais restritivo ao menos restritivo:

1. 🟥 `private` - permite acesso somente dentro da própria classe.
2. **Padrão** - quando nenhum modificador é declarado, permite acesso dentro do mesmo pacote.
3. 🔶 `protected` - permite acesso dentro do mesmo pacote e também por subclasses, respeitando as regras de herança.
4. 🟢 `public` - permite acesso de qualquer classe que consiga acessar o elemento.

---

## Projeto `Agência Bancária`

Após finalizar o projeto `POO Carros`, foi criado um novo projeto Java chamado `Agência Bancária`.

Foram criados dois pacotes:

- `contas` - contém as classes `Conta.java` (classe modelo) e `PessoaFisica.java`.
- `seguro` - contém a classe `SeguroPessoaFisica.java`.

### Classe modelo `Conta`

A classe `Conta` foi criada para representar uma conta bancária, contendo atributos, um construtor e um método.

```java
// Atributos
double saldo;
String cliente;

// Construtor
public Conta() {
    System.out.println("Agência 0261");
}

// Método
void exibirSaldo() {
    System.out.println("Saldo: R$ " + saldo);
}
```
`saldo` - armazena o saldo da conta.  
`cliente` - armazena o nome do cliente.  
`Conta()` - construtor executado quando um objeto da classe é criado.  
`exibirSaldo()` - exibe o saldo armazenado.

### Criando objetos em `PessoaFisica`

Na classe `PessoaFisica`, foram criados dois objetos da classe `Conta`:

```java
Conta cc1 = new Conta();
cc1.cliente = "Leandro Ramos";
cc1.saldo = 1000.00;

Conta cc2 = new Conta();
cc2.cliente = "Sirlene Sanches";
cc2.saldo = 800.00;
```

Cada objeto possui seus próprios valores de cliente e saldo.

O operador `.` é utilizado para acessar atributos e métodos de um objeto, desde que as regras de acesso permitam.

---

## Acesso entre pacotes

### Acesso com `public`

Em seguida, foi necessário utilizar a classe `Conta` no pacote `seguro`, que é diferente do pacote `contas`.

Para isso, foi utilizada a importação:

```java
import contas.Conta;
```

A importação permite utilizar a classe pelo seu nome, mas não libera automaticamente o acesso aos seus membros.

Como os atributos e o método estavam **sem modificadores de acesso**, não podiam ser acessados diretamente por uma classe de outro pacote.

Por isso, na classe `Conta`, eles foram alterados para `public`:

```java
public double saldo;
public String cliente;

public void exibirSaldo() {
    System.out.println("Saldo: R$ " + saldo);
}
```

Com essa alteração, foi possível criar um objeto em `SeguroPessoaFisica` e acessar seus atributos e métodos:

```java
Conta cc3 = new Conta();
cc3.cliente = "Junior Magalhães";
cc3.saldo = 9000;

cc3.exibirSaldo();
```

### Utilizando `protected` com herança

Os atributos e o método da classe `Conta` foram alterados para `protected`:

```java
protected double saldo;
protected String cliente;

protected void exibirSaldo() {
    System.out.println("Saldo: R$ " + saldo);
}
```

Como `protected` possui regras específicas de acesso, foi utilizada a herança na classe `SeguroPessoaFisica`:

```java
public class SeguroPessoaFisica extends Conta {
```

Assim, a classe passou a herdar de `Conta`, podendo utilizar os membros protegidos por meio da própria instância da subclasse, respeitando as regras de acesso do Java.

Esses exercícios demonstrou como os **modificadores de acesso** interferem na utilização de classes de pacotes diferentes.

---

## Métodos para operações bancárias

Na classe `Conta`, foram criados métodos para realizar operações sobre o saldo.

### Sacar

O método `sacar()` recebe um valor e o subtrai do saldo:

```java
void sacar(double valor) {
    saldo -= valor;
    System.out.println("Débito: R$ " + valor);
}
```

O operador `-=` **subtrai o valor** informado do saldo atual.

Exemplo de utilização:

```java
cc1.exibirSaldo();
cc1.sacar(1000);
cc1.exibirSaldo();
```

### Depositar

O método `depositar()` recebe um valor e o adiciona ao saldo:

```java
void depositar(double valor) {
    saldo += valor;
    System.out.println("Crédito: R$ " + valor);
}
```

O operador `+=` **soma o valor** informado ao saldo atual.

Exemplo de utilização:

```java
cc2.exibirSaldo();
cc2.depositar(1000);
cc2.exibirSaldo();
```

Esses métodos demonstram como uma classe pode reunir comportamentos relacionados aos seus próprios dados.

### Transferências entre contas

Também foi criado um método para transferir dinheiro de uma conta para outra:

```java
void transferir(Conta destino, double valor) {
    this.sacar(valor);
    destino.depositar(valor);
    System.out.println("Transferência: " + valor);
}
```

O método recebe dois parâmetros:

- `Conta destino` - representa a conta que receberá o dinheiro.
- `double valor` - representa o valor da transferência.

O funcionamento ocorre em duas etapas:

1. `this.sacar(valor)` — retira o valor da conta que iniciou a transferência.
2. `destino.depositar(valor)` — adiciona o mesmo valor à conta de destino.

A palavra `this` referencia o objeto atual. Já `destino` representa o objeto recebido como parâmetro.

Exemplo:

```java
cc1.transferir(cc2, 2000);
```

Nesse caso, `cc1` é a conta de origem, `cc2` é a conta de destino e 2000 é o valor transferido.
