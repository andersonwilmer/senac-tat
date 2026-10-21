/*
DATA: 05 de outrubro de 2026
Este código está relacionado com 05-Heranca-Polimorfismo.md"
Java
*/

package minecraft;

public class Itens {

	public static void main(String[] args) {
		Enxada enxadaMadeira = new Enxada();
		enxadaMadeira.textura = "Madeira";
		enxadaMadeira.resistencia = 2;
		System.out.println("Item: Enxada");
		System.out.println("Textura: " + enxadaMadeira.textura);
		System.out.println("Resistência: " + enxadaMadeira.resistencia);
		enxadaMadeira.arar();
		if (enxadaMadeira.conquista == true) {
			System.out.println("Conquista obtida!");
			System.out.println("Dedicação séria. Hora do plantio");
		}
	
		Enxada enxadaDiamante = new Enxada();
		enxadaDiamante.textura = "Diamante";
		enxadaDiamante.resistencia = 10;
		System.out.println("Item: Enxada");
		System.out.println("Textura: " + enxadaDiamante.textura);
		System.out.println("Resistência: " + enxadaDiamante.resistencia);
		enxadaDiamante.minerar();

	}

}
