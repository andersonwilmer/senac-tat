/*
DATA: 02 de outrubro de 2026
Este código está relacionado com "04-Abstração.md"
Java
*/

package carros;

public class Garagem {

	public static void main(String[] args) {
		
		// Objeto 1 - Carro da Ferrari
		Carro carroFerrari = new Carro();
		carroFerrari.ano = 1947;
		carroFerrari.cor = "Vermelho";
		System.out.println("Ano: " + carroFerrari.ano);
		System.out.println("Cor: " + carroFerrari.cor);
		carroFerrari.ligar();
		carroFerrari.acelerar();
		
		System.out.println();
		
		// Objeto 2 - Carro Fusca
		Carro carroFusca = new Carro();
		carroFusca.ano = 1938;
		carroFusca.cor = "Azul";
		System.out.println("Ano: " + carroFusca.ano);
		System.out.println("Cor: " + carroFusca.cor);
		carroFusca.ligar();
		carroFusca.desligar();

	}

}
