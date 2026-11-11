/*
DATA: 09 de novembro de 2026
Este código está relacionado com "15-Agenda-DAO-Cadastro"
Java
*/

package controller;

//importação do modelo de dados

import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;

public class ContatoController {
    //No MVC Controller faz as validações (regras de negócio)

    //Instanciar o DAO
    //final protege o objeto evitando a sua substituição no processo
    private final ContatoDAO contatoDAO = new ContatoDAO();

    //CRUD Create ============
    public void adicionar(Contato contato) throws SQLException {
        validarContato(contato);
        //executando o DAO
        contatoDAO.adicionar(contato);
    }
    //Fim CRUD Create ========

    //Função para validar os dados do contato (adicionar e editar) OBS: contato é o objeto
    private void validarContato(Contato contato) {
        //getNome() == null (verificar se o nome foi preenchido)
        //.getNome().trim().isEmpty() remove espaços em branco invisíveis no início e fim (ex: "  " vira "")
        if (contato.getNome() == null || contato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do contato.");
        }

        //remove espaços inúteis antes de salvar
        //Ex: Se o usuário digitou " João Silva  ", ele limpa e ajusta para ("João Silva")
        contato.setNome(contato.getNome().trim());
    }
}
