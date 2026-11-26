
/*
DATA: 09, 16 e 25 de novembro de 2026
Este código está relacionado com "15-Agenda-DAO-Cadastro.md", "16-Agenda-Relatorios.md" e "17-Agenda-Busca-Edição.md".
Java
*/

package dao;

import connection.ConnectionFactory;
import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    //Este módulo trabalha com JDBC (bibliotecas responsáveis pelo acesso ao banco de dados)
    //DAO executa as instruções SQL

    //CRUD Create ============
    //Dica: Usar o mesmo nome do Controller
    public void adicionar(Contato contato) throws SQLException {
        //Comando SQL
        String sql = "insert into contato (nome, fone) values (?, ?)";

        //Instanciar o objeto fábrica (ConnectionFactory)
        ConnectionFactory fabrica = new ConnectionFactory();

        //Abrir a conexão com banco de dados
        //try (tratamento de exceção)
        //Connection JDBC (abrir a conexão com o banco)
        //PreparedStatement JDBC (preparar o comando SQL)
        try (Connection con = fabrica.conectar();
             PreparedStatement comando = con.prepareStatement(sql)) {

            //Substituir as (?, ?) pelo nome e fone do contato
            comando.setString(1, contato.getNome());
            comando.setString(2, contato.getFone());

            //Executar o comando
            //PASSO 5 do slide CRUD Create
            comando.executeUpdate();
        }
    }
    //Fim CRUD Create ========

    //CRUD Read ==============
    public Contato buscarPorNome(String nome) throws SQLException {
        //Comando SQL
        String sql = "select idContato, nome, fone from contato where nome like ? order by (nome = ?) desc, idContato limit 1";

        //Connection e PreparedStatement (JDBC)
        try (Connection con = new ConnectionFactory().conectar();
             PreparedStatement comando = con.prepareStatement(sql)) {

            //Substituir as interrogações do comando SQL
            comando.setString(1, "%" + nome + "%");
            comando.setString(2, nome);

            //ResultSet (JDBC)
            //comando.executeQuery() - Passo 3 do slide CRUD Read
            try (ResultSet resultado = comando.executeQuery()) {

                //Se houver um nome no banco que corresponda à busca
                if (resultado.next()) {
                    //Setar as variáveis do Model
                    Contato contato = new Contato();
                    contato.setIdContato(resultado.getInt("idContato"));
                    contato.setNome(resultado.getString("nome"));
                    contato.setFone(resultado.getString("fone"));

                    //PASSO 5 do slide CRUD Read
                    return contato;
                }
            }
        }

        return null;
    }
    //Fim CRUD Read ==========

    //CRUD Update ============
    //Dica: Usar o mesmo nome do Controller
    public boolean editar(Contato contato) throws SQLException {
        //Comando SQL
        String sql = "update contato set nome = ?, fone = ? where idContato = ?";

        //Instanciar o objeto fábrica (ConnectionFactory)
        ConnectionFactory fabrica = new ConnectionFactory();

        //Abrir a conexão com banco de dados
        //Connection JDBC (abrir a conexão com o banco)
        //PreparedStatement JDBC (preparar o comando SQL)
        try (Connection con = fabrica.conectar();
             PreparedStatement comando = con.prepareStatement(sql)) {

            //Substituir as (?, ?, ?) pelo nome, fone e ID do contato
            comando.setString(1, contato.getNome());
            comando.setString(2, contato.getFone());
            comando.setInt(3, contato.getIdContato());

            //Executar o comando
            //Retorna true se algum registro foi atualizado
            return comando.executeUpdate() > 0;
        }
    }
    //Fim CRUD Update ========

    //Relatório de contatos
    //Método com retorno do array (vetor) de contatos
    public List<Contato> listarTodos() throws SQLException {
        String sql = "select idContato, nome, fone from contato order by nome";

        List<Contato> contatos = new ArrayList<>();

        try (Connection con = new ConnectionFactory().conectar();
             PreparedStatement comando = con.prepareStatement(sql);
             //PASSO 3 do slide Service
             ResultSet resultado = comando.executeQuery()) {

            //Enquanto houver contatos no banco
            //PASSO 4 do slide Service (ResultSet JDBC)
            while (resultado.next()) {
                Contato contato = new Contato();
                contato.setIdContato(resultado.getInt("idContato"));
                contato.setNome(resultado.getString("nome"));
                contato.setFone(resultado.getString("fone"));

                //Adicionar
                contatos.add(contato);
            }
        }

        //PASSO 5 do slide Service (vetor contendo os contatos)
        return contatos;
    }
    //Fim relatório de contatos
}
