/*
DATA: 30 de setembro de 2026
Este código está relacionado com "03-Array.md"
Java
Descrição: Exemplo de utilização de vetores (Array) para representar cartas de um baralho e realizar sorteios aleatórios utilizando a classe Random.
*/

package array;

import java.util.Random;

public class Cartas {

	public static void main(String[] args) {
		String[] nipes = {"♠", "♥", "♦", "♣"};
		String[] faces = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		// recuperar o Az de ouros
		// System.out.println(faces[0] + nipes[2]);
		
		// recuperar o Dama de copas
		// System.out.println(faces[12] + nipes[1]);
		
		// Criando um objeto para sorteio de uma carta
		Random sorteio = new Random();
		
		// a linha abaixo sorteia um número aleatório entre 0 e 12
		int indiceFace = sorteio.nextInt(faces.length);
		
		// a linha abaixo sorteia um número aleatório entre 0 e 3
		int indiceNipe = sorteio.nextInt(nipes.length);
		
		System.out.println(faces[indiceFace] + nipes[indiceNipe]);

	}

}
