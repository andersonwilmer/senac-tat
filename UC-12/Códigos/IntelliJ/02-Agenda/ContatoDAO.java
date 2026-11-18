/*
DATA: 09 e 16 de novembro de 2026
Este código está relacionado com "15-Agenda-DAO-Cadastro.md" e "16-Agenda-Relatorios.md"
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
    //DAO executa as instruções sql

    //CRUD Create ============
    //Dica: Usar o mesmo nome do controller
    public void adicionar(Contato contato) throws SQLException {
        //Comando sql
        String sql = "insert into contato (nome, fone) values (?, ?)";

        //Instanciar o objeto fábrica (ConnectionFactory)
        ConnectionFactory fabrica = new ConnectionFactory();

        //Abrir a conexão com banco de dados
        //try (tratamento de exceção)
        //Connection JDBC (abrir a conexão com o banco)
        //PreparedStatement JDBC (preparar o comando sql)
        try (Connection con = fabrica.conectar();
             PreparedStatement comando = con.prepareStatement(sql)
        ){
            //substituit as (?, ?) pelo nome e fone do contato
            comando.setString(1, contato.getNome());
            comando.setString(2, contato.getFone());
            //executar o comando
            //PASSO 5 do slide CRUD Create
            comando.executeUpdate();
        }
    }
    //Fim CRUD Create ========

    //Relatório de contatos
    //método com retorno do array(vetor) de contatos
    public List<Contato> listarTodos() throws SQLException {
        String sql = "select idContato, nome, fone from contato order by nome";

        List<Contato> contatos = new ArrayList<>();
        try (Connection con = new ConnectionFactory().conectar();
             PreparedStatement comando = con.prepareStatement(sql);
             //PASSO 3 do slide Service
             ResultSet resultado = comando.executeQuery()) {

            //enquanto houver contatos no banco
            //PASSO 4 do slide Service (ResultSet JDBC)
            while (resultado.next()) {
                Contato contato = new Contato();
                contato.setIdContato(resultado.getInt("idContato"));
                contato.setNome(resultado.getString("nome"));
                contato.setFone(resultado.getString("fone"));
                //adicionar
                contatos.add(contato);
            }
        }
        //PASSO 5 do slide Service (vetor contendo os contatos)
        return contatos;
    }
    //Fim relatório de contatos
}
