/*
DATA: 05 de outrubro de 2026
Este código está relacionado com 05-Heranca-Polimorfismo.md"
Java
*/

package minecraft;

// "extends" cria uma herança Enxada da classe Bloco
public class Enxada extends Bloco{
	
	// atributos
	boolean conquista;
	
	// métodos
	void arar() {
		System.out.println("Terra preparada para o plantio!");
		conquista = true;
	}
	
	// polimorfismo (modifica um método existente na classe pai)
	// obrigatoriamente o método precisa ter o mesmo nome
	void minerar() {
		System.out.println("Dano atribuído!");
	}

}
