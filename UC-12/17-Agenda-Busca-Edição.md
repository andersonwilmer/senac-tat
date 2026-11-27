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

---

## Implementando a edição de contatos (CRUD Update)

Depois da implementação da busca e do acesso ao WhatsApp Web, foi iniciado o CRUD Update, responsável por atualizar os dados de um contato já cadastrado.

### Método editar() - ContatoController

Na classe `ContatoController`, foi criado o método `editar()` junto aos demais métodos CRUD, logo após o método `buscarPorNome()`.

```java
// CRUD Update
public boolean editar(Contato contato) throws SQLException {
    if(contato.getIdContato() <= 0) {
        throw new IllegalArgumentException("Busque um contato antes de editar");
    }
    validarContato(contato);
    return contatoDAO.editar(contato);
}
```

**Entendendo o método:**

- `public boolean editar(Contato contato)`: recebe um objeto `Contato` e retorna um valor booleano, indicando se a atualização foi realizada.
- `contato.getIdContato() <= 0`: verifica se o contato possui um ID válido.
- `throw new IllegalArgumentException(...)`: interrompe a execução caso não exista um ID válido, informando que é necessário buscar um contato antes de editar.
- `validarContato(contato)`: utiliza o método de validação já existente no Controller para verificar o nome e o telefone.
- `return contatoDAO.editar(contato)`: encaminha o contato validado para o DAO e retorna o resultado da atualização.

O método `editar()` utiliza o ID para identificar qual contato será atualizado, evitando alterar um registro sem identificação.

### Método editar() - ContatoDAO

Na classe `ContatoDAO`, foi criado o método `editar()` logo depois do método `buscarPorNome()`, responsável por executar o comando SQL de atualização dos dados do contato.

```java
// CRUD Update ============
public boolean editar(Contato contato) throws SQLException {
    String sql = "update contato set nome = ?, fone = ? where idContato = ?";
    ConnectionFactory fabrica = new ConnectionFactory();

    try (Connection con = fabrica.conectar();
         PreparedStatement comando = con.prepareStatement(sql)) {

        comando.setString(1, contato.getNome());
        comando.setString(2, contato.getFone());
        comando.setInt(3, contato.getIdContato());

        return comando.executeUpdate() > 0;
    }
}
// Fim CRUD Update ========
```

**Entendendo o método:**

- `String sql`: armazena o comando SQL `UPDATE`, utilizado para atualizar os dados de um contato.
- `ConnectionFactory fabrica`: instancia a classe responsável por fornecer a conexão com o banco de dados.
- `PreparedStatement`: prepara o comando SQL para receber os valores do contato.
- `comando.setString(1, contato.getNome())`: preenche o primeiro parâmetro com o novo nome.
- `comando.setString(2, contato.getFone())`: preenche o segundo parâmetro com o novo telefone.
- `comando.setInt(3, contato.getIdContato())`: preenche o terceiro parâmetro com o ID do contato que será atualizado.
- `comando.executeUpdate()`: executa o comando de atualização no banco de dados.
- `> 0`: verifica se pelo menos um registro foi afetado. Retorna `true` quando a atualização ocorre e `false` caso contrário.

O ID é utilizado na cláusula `WHERE` para identificar exatamente qual contato deve receber as alterações.

### Integrando a edição à tela Agenda

Na classe `Agenda`, dentro do construtor, foi adicionado um `ActionListener` ao botão `btnEditar`, chamando o método `editarContato()` quando o usuário clicar nele.

```java
btnEditar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        editarContato();
    }
});
```

### Método editarContato() — Agenda

Fora do construtor, na classe `Agenda`, foi criado o método `editarContato()`, responsável por recuperar os dados preenchidos na tela e encaminhá-los ao Controller para realizar a atualização.

```java
private void editarContato() {
    try {
        if (txtId.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Busque um contato antes de editar.");
        }

        Contato contato = new Contato();
        contato.setIdContato(Integer.parseInt(txtId.getText()));
        contato.setNome(txtNome.getText());
        contato.setFone(txtFone.getText());

        boolean atualizado = contatoController.editar(contato);

        if (atualizado == true) {
            JOptionPane.showMessageDialog(
                    this,
                    "Contato atualizado com sucesso!",
                    "Agenda de contatos",
                    JOptionPane.INFORMATION_MESSAGE);
            limpar();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Contato não encontrado para atualização!",
                    "Agenda de contatos",
                    JOptionPane.WARNING_MESSAGE);
        }
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(
                this, e.getMessage(),
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "Não foi possível editar o contato.\n" + e.getMessage(),
                "Erro no banco de dados",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
```

**Entendendo o método:**

- `txtId.getText().trim().isEmpty()`: verifica se o campo de ID está vazio, impedindo a edição sem antes buscar um contato.
- `throw new IllegalArgumentException(...)`: interrompe a execução e apresenta um aviso caso nenhum contato tenha sido buscado.
- `new Contato()`: cria um objeto para armazenar os dados que serão atualizados.
- `Integer.parseInt(txtId.getText())`: converte o ID, que está em formato de texto, para inteiro.
- `contato.setIdContato()`, `setNome()` e `setFone()`: preenchem o objeto com os dados atuais dos campos da tela.
- `contatoController.editar(contato)`: encaminha o objeto ao Controller para validar e realizar a atualização.
- `boolean atualizado`: armazena o resultado da operação, indicando se algum registro foi atualizado.
- `if (atualizado == true)`: verifica se a atualização foi realizada. Em caso positivo, exibe uma mensagem de sucesso e chama `limpar()`.
- `else`: caso nenhum registro seja atualizado, apresenta uma mensagem informando que o contato não foi encontrado.
- `catch (IllegalArgumentException e)`: trata erros de validação, como tentar editar sem buscar um contato.
- `catch (SQLException e)`: trata possíveis erros durante a operação no banco de dados.
- `e.getMessage()`: acrescenta a descrição do erro do banco à mensagem exibida.
