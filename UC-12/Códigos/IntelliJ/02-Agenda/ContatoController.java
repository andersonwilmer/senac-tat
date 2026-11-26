
/*
DATA: 09, 16 e 25 de novembro de 2026
Este código está relacionado com "15-Agenda-DAO-Cadastro.md", "16-Agenda-Relatorios.md" e "17-Agenda-Busca-Edição.md"
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
        //(contato) PASSO 3 do slide CRUD Create
        validarContato(contato);

        //executando o DAO
        //PASSO 4 do slide CRUD Create
        contatoDAO.adicionar(contato);
    }
    //Fim CRUD Create ========

    //CRUD Read ==============
    public Contato buscarPorNome(String nome) throws SQLException {
        //Validar se o nome foi preenchido
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome para a busca.");
        }

        //Passo 2 do slide CRUD Read
        return contatoDAO.buscarPorNome(nome.trim());
    }
    //Fim CRUD Read ==========

    //CRUD Update ============
    public boolean editar(Contato contato) throws SQLException {
        //Verificar se um contato foi buscado antes da edição
        if (contato.getIdContato() <= 0) {
            throw new IllegalArgumentException("Busque um contato antes de editar.");
        }

        //(contato) PASSO 3 do slide CRUD Update
        validarContato(contato);

        //executando o DAO
        //PASSO 4 do slide CRUD Update
        return contatoDAO.editar(contato);
    }
    //Fim CRUD Update ========

    //Função para validar os dados do contato (adicionar e editar)
    //OBS: contato é o objeto
    private void validarContato(Contato contato) {
        //getNome() == null (verificar se o nome foi preenchido)
        //.getNome().trim().isEmpty() remove espaços em branco invisíveis no início e fim
        //Ex: "  " vira ""
        if (contato.getNome() == null || contato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do contato.");
        }

        //regex (expressão regular)
        //d{2} obriga o preenchimento com 2 dígitos
        //! not (se não corresponder com o telefone)
        if (contato.getFone() == null || !contato.getFone().matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
            throw new IllegalArgumentException("Informe o telefone completo.");
        }

        //remove espaços inúteis antes de salvar
        //Ex: Se o usuário digitou " João Silva  ", ele limpa e ajusta para "João Silva"
        contato.setNome(contato.getNome().trim());
    }
}
