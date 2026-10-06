/*
DATA: 06 de outrubro de 2026
Este código está relacionado com 05-Heranca-Polimorfismo.md"
Java
*/

package veiculos;

public class Aviao extends Carro {
	
	// atributos
	double envergadura;
	
	//métodos
	void aterrizar() {
		System.out.println("Aterrizando");
	}
	
	//polimorfismo
	void acelerar() {
		System.out.println("Zumbindo");
	}

}
