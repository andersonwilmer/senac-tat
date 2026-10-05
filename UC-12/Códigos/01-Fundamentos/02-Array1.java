/*
DATA: 30 de setembro de 2026
Este código está relacionado com "03-Array.md"
Java
Descrição: Exemplo de utilização de vetores estáticos (Array), demonstrando armazenamento, recuperação e percurso de elementos.
*/

package array;

public class Array1 {

	public static void main(String[] args) {
		// ìndice          [0]      [1]     [2]      [3]
		String[] nomes = {"Bill", "José", "Bruce", "Frank"};
		// recuperando o nome Bruce
		System.out.println(nomes[2]);
		// tamanho do array
		System.out.println(nomes.length);
		// percorrendo o array com o uso do laço for
		for(int i = 0; i < nomes.length; i++) {
			System.out.println(nomes[i]);
		}

	}

}
