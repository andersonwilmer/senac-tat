/*
DATA: 02 de outrubro de 2026
Este código está relacionado com "04-Abstração.md"
Java
*/

package minecraft;

public class Mundo {

	public static void main(String[] args) {
		
		// Objeto 1 - Bloco de terra
		// a linha abaixo cria (instância) um objeto
		Bloco blocoTerra = new Bloco();
		
		// atribuindo valores aos atributos (variáveis)
		blocoTerra.resistencia = 1;
		blocoTerra.textura = "Terra";
		System.out.println("Bloco: " + blocoTerra.textura);
		System.out.println("Resistência: " + blocoTerra.resistencia);
		
		// uso dos métodos da classe modelo "Bloco"
		blocoTerra.minerar();
		blocoTerra.construir();
		
		System.out.println();
		
		// Objeto 2 - Bloco de madeira
		Bloco blocoMadeira = new Bloco();
		
		blocoMadeira.resistencia = 2;
		blocoMadeira.textura = "Madeira";
		System.out.println("Bloco: " + blocoMadeira.textura);
		System.out.println("Resistência: " + blocoMadeira.resistencia);
		blocoMadeira.minerar();
		blocoMadeira.craftar();

	}

}
