# Impressora Térmica com Java

> **Data:** 13 e 14 de outubro de 2026

Utilizamos a impressora térmica Elgin i8, um equipamento utilizado para imprimir recibos e comprovantes.

---

### Introdução

A atividade consistiu em criar um programa Java capaz de enviar comandos de impressão diretamente para a impressora pela rede.

A comunicação foi realizada utilizando o protocolo de impressão RAW, pela porta 9100, com comandos ESC/POS.

No Eclipse, criamos o projeto Java `Impressora Térmica`, o pacote `impressora` e a classe `Impressora`.

Na classe, utilizamos o método main(), que é o ponto de entrada da execução do programa.

Também foi utilizado `try/catch` para tratar exceções que podem ocorrer durante a execução, como falhas na conexão ou no envio dos dados:

```java
try {
  // códigos...
} catch (Exception e) {
  System.out.println(e);
}
```

---

## Conexão com a impressora

Para realizar a comunicação pela rede, **importamos** as classes `Socket` e `OutputStream`.

```java
import java.net.Socket;
import java.io.OutputStream;
```

`Socket` → estabelece uma conexão TCP com um endereço IP e uma porta.  
`OutputStream` → permite enviar dados pela conexão estabelecida.

A conexão foi criada com:

```java
Socket impressora = new Socket("10.26.45.59", 9100);
OutputStream saida = impressora.getOutputStream();
```

O endereço IP utilizado pode variar conforme a configuração da rede e da impressora. Durante os testes, foram utilizados diferentes endereços.

---

## Impressão

Inicialmente, aprendemos os comandos necessários para controlar a saída da impressão.

Foram utilizados comandos ESC/POS para avançar o papel e realizar o corte.

### Avanço do papel

```java
saida.write(new byte[] {0x1B, 0x64, 0x05});
```

O último byte define a quantidade de linhas de avanço, conforme a interpretação do comando pela impressora.

### Corte do papel

```java
saida.write(new byte[] {0x1D, 0x56, 0x00});
```

Envia o comando de corte de papel.

### Enviando os dados e encerrando a conexão

Ao final do programa, utilizamos:

```java
saida.flush();
impressora.close();
```

`flush()` → solicita o envio dos dados que ainda estão pendentes no fluxo de saída.  
`close()` → encerra a conexão com a impressora.

---

## Texto inicial

Inicialmente, enviamos um texto simples para a impressora:

```java
saida.write("Anderson Wilmer\n".getBytes("CP850"));
```

`write()` → envia os dados para o fluxo de saída.  
`getBytes("CP850")` → converte o texto para uma sequência de bytes utilizando a codificação CP850.  
`\n` → representa uma quebra de linha.

A codificação CP850 é utilizada para representar caracteres em uma tabela de caracteres compatível com o equipamento. Ela não é a mesma coisa que UTF-8.

---

## Formatando o texto com comandos ESC/POS

Depois da impressão simples, foram adicionados comandos para modificar a aparência do texto.

### Negrito

```java
saida.write(new byte[] {0x1B, 0x45, 0x01});  // ativa o negrito

saida.write(new byte[] {0x1B, 0x45, 0x00});  // texto com aparência normal e também serve para desativar o negrito
```

### Alinhamento

```java
saida.write(new byte[] {0x1B, 0x61, 0x01});  // centraliza

saida.write(new byte[] {0x1B, 0x61, 0x00});  // alinha à esquerda

saida.write(new byte[] {0x1B, 0x61, 0x02});  // alinha à direita
```

### Tamanho da fonte

```java
saida.write(new byte[] {0x1D, 0x21, 0x00});  // normal

saida.write(new byte[] {0x1D, 0x21, 0x11});  // 2x

saida.write(new byte[] {0x1D, 0x21, 0x22});  // 3x

saida.write(new byte[] {0x1D, 0x21, 0x33});  // 4x
```

**Atenção:** o último byte foi utilizado para selecionar o tamanho desejado.

---

## Data e hora

Acrescentamos a impressão automática da data e da hora atuais.

Para isso, importamos duas classes:

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
```

`LocalDateTime` → permite obter a data e a hora atuais.  
`DateTimeFormatter` → permite definir como a data e a hora serão apresentadas.

### Obtendo a data e a hora

```java
LocalDateTime agora = LocalDateTime.now();
```

O método `now()` obtém a data e a hora atuais do sistema no momento da execução.

### Definindo o formato

```java
DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
```

### Preparando e imprimindo o conteúdo

```java
String dataHora = "Data: " + agora.format(formato) + "\n\n";

saida.write(dataHora.getBytes("CP850"));
```

A variável `dataHora` armazena o texto com a data e a hora formatadas. Em seguida, o conteúdo é convertido para bytes e enviado à impressora.

Dessa forma, a data e a hora são atualizadas automaticamente a cada execução do programa.
