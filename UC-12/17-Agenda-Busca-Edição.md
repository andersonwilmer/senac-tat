# Java - Agenda: Busca e Edição

> **Data:** 25 de novembro de 2026

Implementando a busca, edição e acesso ao WhatsApp na Agenda.

---

## Implementando a busca de contatos (CRUD Read)

A primeira etapa foi implementar a busca de contatos pelo nome, utilizando uma consulta SQL no banco de dados.

### Consulta SQL

Antes de implementar o método no Java, foi utilizada a seguinte consulta para testar a busca diretamente no MySQL:

```sql
-- CRUD Read ================================
select idContato, nome, fone
from contato
where nome like "%Bill gates%"
order by (nome = "Bill gates") desc, idContato
limit 1;
-- Fim CRUD Read ============================
```

- `LIKE`: permite buscar nomes que contenham o texto informado.
- `ORDER BY (nome = "Bill gates") DESC`: prioriza o resultado cujo nome seja exatamente igual ao informado.
- `LIMIT 1`: limita o resultado a apenas um contato.

### Método buscarPorNome() - ContatoController

Na classe `ContatoController`, foi criado o método `buscarPorNome()` depois do CRUD Create, responsável por validar o texto informado e encaminhar a busca para o DAO.

```java
// CRUD read ============
public Contato buscarPorNome(String nome) throws SQLException {
    if (nome == null || nome.trim().isEmpty()) {
        throw new IllegalArgumentException("Informe o nome para a busca.");
    }
    return contatoDAO.buscarPorNome(nome.trim());
}
// FIM CRUD ===============
```

- `nome == null`: verifica se nenhum valor foi informado.
- `nome.trim().isEmpty()`: verifica se o texto está vazio ou contém apenas espaços.
- `throw new IllegalArgumentException(...)`: interrompe a execução e informa que é necessário preencher o nome.
- `nome.trim()`: remove os espaços em branco no início e no final do texto.
- `contatoDAO.buscarPorNome(nome.trim())`: encaminha o nome validado para o método de busca na classe `ContatoDAO`.

O método retorna um objeto `Contato` quando encontra um registro correspondente. Caso contrário, o DAO retorna `null`.

### Método buscarPorNome() - ContatoDAO

Na classe `ContatoDAO`, foi implementado o método responsável por executar a consulta SQL e recuperar os dados do contato encontrado, também logo depois do CRUD Create.

```java
// CRUD read ============
public Contato buscarPorNome(String nome) throws SQLException {
    String sql = "select idContato, nome, fone from contato where nome like ? order by (nome = ?) desc, idContato limit 1";

    //Connection e PreparedStatement (JDBC)
    try (Connection con = new ConnectionFactory().conectar();
         PreparedStatement comando = con.prepareStatement(sql)) {

        comando.setString(1, "%" + nome + "%");
        comando.setString(2, nome);

        //ResultSet (JDBC)
        try (ResultSet resultado = comando.executeQuery()) {
            if (resultado.next()) {
                Contato contato = new Contato();
                contato.setIdContato(resultado.getInt("idContato"));
                contato.setNome(resultado.getString("nome"));
                contato.setFone(resultado.getString("fone"));
                return contato;
            }
        }
    }
    return null;
}
// FIM CRUD Read ===============
```

**Entendendo o método:**

- `String sql`: armazena a consulta SQL que será executada no banco de dados.
- `Connection`: estabelece a conexão com o banco através da `ConnectionFactory`.
- `PreparedStatement`: prepara a consulta SQL e permite preencher os parâmetros representados pelos `?`.
- `comando.setString(1, "%" + nome + "%")`: preenche o primeiro parâmetro, permitindo buscar nomes que contenham o texto informado.
- `comando.setString(2, nome)`: preenche o segundo parâmetro, utilizado para priorizar uma correspondência exata.
- `ResultSet`: armazena o resultado retornado pela consulta.
- `resultado.next()`: verifica se existe algum registro encontrado.
- `new Contato()`: cria um objeto para armazenar os dados recuperados do banco.
- `setIdContato()`, `setNome()` e `setFone()`: preenchem os atributos do objeto com os valores retornados pela consulta.
- `return contato`: devolve o contato encontrado.
- `return null`: caso nenhum registro seja encontrado, retorna `null`.

O `try-with-resources` permite que os recursos utilizados na conexão e na consulta sejam fechados automaticamente ao final da execução.

### Integrando a busca à tela Agenda

Na classe `Agenda`, foi adicionado um `ActionListener` ao botão `btnBuscar`, chamando o método `buscarContato()` quando o usuário clicar nele.

```java
btnBuscar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        buscarContato();
    }
});
```

Fora do construtor, o método `buscarContato()` recebe o texto digitado no campo `txtNome` e encaminha a busca para o Controller.

```java
private void buscarContato() {
    try {
        Contato contato = contatoController.buscarPorNome(txtNome.getText());

        if (contato != null) {
            txtId.setText(String.valueOf(contato.getIdContato()));
            txtNome.setText(contato.getNome());
            txtFone.setText(contato.getFone());
            btnAdicionar.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum contato encontrado",
                    "Agenda de contatos",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "Erro ao buscar contato.\n"
        );
    }
}
```

**Entendendo o método:**

- `contatoController.buscarPorNome(txtNome.getText())`: envia o texto digitado no campo de nome para realizar a busca.
- `contato != null`: verifica se um contato foi encontrado.
- `txtId`, `txtNome` e `txtFone`: recebem os dados do contato encontrado e preenchem os campos da tela.
- `btnAdicionar.setEnabled(false)`: desabilita o botão Adicionar quando um contato é encontrado, evitando um novo cadastro acidental.
- `JOptionPane.showMessageDialog()`: exibe mensagens na tela, como quando nenhum contato é encontrado ou quando ocorre algum erro.
- `catch (IllegalArgumentException e)`: trata erros de validação, como tentar buscar com o campo de nome vazio.
- `catch (SQLException e)`: trata possíveis erros relacionados ao banco de dados.

Dentro do construtor também foi configurado o botão Buscar como botão padrão da janela, antes da configuração da mãozinha:

```java
getRootPane().setDefaultButton(btnBuscar);
```

Assim, ao pressionar **Enter**, a busca é executada sem precisar clicar diretamente no botão.

### Ajuste no botão Adicionar

Como o botão `btnAdicionar` é desabilitado quando um contato é encontrado, foi necessário ajustar o método `limpar()` para habilitá-lo novamente ao limpar os campos da tela.

```java
private void limpar() {
    txtId.setText(null);
    txtNome.setText(null);
    txtFone.setValue(null);
    txtNome.requestFocusInWindow();
    btnAdicionar.setEnabled(true);
}
```

`btnAdicionar.setEnabled(true)`: habilita novamente o botão Adicionar após limpar os campos, permitindo cadastrar um novo contato.

---

## Acessando o WhatsApp Web

O professor disponibilizou o código para integrar a Agenda ao WhatsApp Web, permitindo abrir uma conversa utilizando o telefone informado no campo `txtFone`.

### Imports utilizados

Na classe `Agenda`, foram adicionados os seguintes imports:

```java
import java.net.URI;
import java.awt.*;
```

- `URI`: permite representar e manipular o endereço que será aberto.
- `Desktop`: disponibiliza recursos para interagir com a área de trabalho, como abrir um endereço no navegador.

### ActionListener do botão WhatsApp

Dentro do construtor da classe `Agenda`, foi adicionado um `ActionListener` ao botão `btnWhatsapp`, chamando o método `acessarWhatsapp()`.

```java
btnWhatsapp.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        acessarWhatsapp();
    }
});
```

### Método acessarWhatsapp()

Fora do construtor, na classe `Agenda`, foi implementado o método `acessarWhatsapp()`, responsável por validar o telefone e abrir o WhatsApp Web no navegador.

```java
private void acessarWhatsapp() {
    String fone = txtFone.getText();

    if (fone == null || !fone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
        JOptionPane.showMessageDialog(
                this,
                "Informe um telefone válido antes de abrir o WhatsApp.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );
        txtFone.requestFocusInWindow();
        return;
    }

    fone = fone.replaceAll("\\D", "");
    String url = "https://web.whatsapp.com/send?phone=55" + fone;

    try {
        Desktop desktop = Desktop.getDesktop();
        URI uri = new URI(url);
        desktop.browse(uri);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Não foi possível abrir o WhatsApp Web.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
        System.err.println(e.getMessage());
    }
}
```

**Entendendo o método:**

- `txtFone.getText()`: recupera o telefone informado no campo da tela.
- `fone.matches(...)`: verifica se o telefone segue o formato `(##) #####-####`.
- `JOptionPane.showMessageDialog()`: exibe um aviso caso o telefone esteja vazio ou inválido.
- `txtFone.requestFocusInWindow()`: posiciona o cursor no campo de telefone para facilitar a correção.
- `return`: interrompe o método caso o telefone não seja válido.
- `fone.replaceAll("\\D", "")`: remove todos os caracteres que não sejam números, como parênteses, espaços e hífen.
- `String url`: monta o endereço do WhatsApp Web, adicionando o código do país `55` antes do telefone.
- `Desktop.getDesktop()`: permite acessar os recursos da área de trabalho.
- `new URI(url)`: cria um objeto `URI` a partir do endereço montado.
- `desktop.browse(uri)`: abre o endereço no navegador padrão.
- `catch (Exception e)`: trata possíveis erros ao tentar abrir o WhatsApp Web.
- `System.err.println(e.getMessage())`: exibe a mensagem do erro no console.
