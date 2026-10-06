/*
DATA: 05 de outrubro de 2026
Este código está relacionado com 05-Heranca-Polimorfismo.md"
Java
*/

package veiculos;

public class Hangar {

	public static void main(String[] args) {
		// Objeto 1
		Aviao Boing = new Aviao();
		Boing.ano = 1947;
		Boing.cor = "Vermelho";
		System.out.println("Avião: Boing");
		System.out.println("Ano: " + Boing.ano);
		System.out.println("Cor: " + Boing.cor);
		Boing.acelerar();
		Boing.aterrizar();

	}

}
