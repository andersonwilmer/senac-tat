/*
DATA: 09 de novembro de 2026
Este código está relacionado com "15-Agenda-DAO-Cadastro.md"
Java
*/

package model;

public class Contato {
    //No MVC Model é uma estrutura de dados
    //encapsular variáveis relacionadas a tabela
    private int idContato;
    private String nome;
    private String fone;

    //Generate getter and setter

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
}
