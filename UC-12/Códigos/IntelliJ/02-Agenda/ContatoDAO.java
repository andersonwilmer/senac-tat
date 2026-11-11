/*
DATA: 09 de novembro de 2026
Este código está relacionado com "15-Agenda-DAO-Cadastro"
Java
*/

package dao;

import connection.ConnectionFactory;
import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            comando.executeUpdate();
        }
    }
    //Fim CRUD Create ========
}
