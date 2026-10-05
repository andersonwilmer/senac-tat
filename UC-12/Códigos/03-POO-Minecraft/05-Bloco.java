/*
DATA: 02 de outrubro de 2026
Este código está relacionado com "04-Abstração.md"
Java
*/

package minecraft;

public class Bloco {
	
	// atributos
	int resistencia;
	String textura;
	
	// construtor (injeção de código ao criar o objeto)
	// para criar o construtor usamos o mesmo nome da classe sem o "class"
	public Bloco() {
		System.out.println(" ____ ");
		System.out.println("|    |");
		System.out.println("|____|");
	}
	
	// métodos ("funções")
	void construir() {
		System.out.println("Bloco colocado");
	}
	
	void minerar() {
		System.out.println("Recursos obtidos");
	}
	
	void craftar() {
		System.out.println("Item criado");
	}

}
