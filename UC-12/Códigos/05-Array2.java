/*
DATA: 30 de setembro de 2026
Este código está relacionado com "03-Array.md"
Java
Descrição: Exemplo de utilização do ArrayList (vetor dinâmico), demonstrando a adição, recuperação e percurso de elementos em uma lista de contatos.
*/

package array;

import java.util.ArrayList;

public class Array2 {

	public static void main(String[] args) {
		// a linha abaixo cria um vetor dinâmico
		ArrayList<String> contatos = new ArrayList<>();
		
		// ArrayList
		// .add (adicionar)
		// .get (recuperar)
		
		// adicionando dados ao vetor
		contatos.add("Bill Gates");
		contatos.add("1199999-1234");
		contatos.add("bill@outlook.com");
		contatos.add("Linus Torvalds");
		contatos.add("1199999-4321");
		contatos.add("linus@gmail.com");
		
		// recuperando os dados da lista
		for (int i = 0; i < contatos.size(); i++) {
			System.out.println(contatos.get(i));
		};

	}

}
