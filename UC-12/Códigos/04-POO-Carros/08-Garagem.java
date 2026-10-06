/*
DATA: 02 e 06 de outrubro de 2026
Este código está relacionado com "04-Abstração.md e 05-Herança-Poliformismo.md"
Java
*/


package veiculos;

public class Garagem {

	public static void main(String[] args) {
	
		// Objeto 1 - Carro da Ferrari
		Carro Ferrari = new Carro();
		Ferrari.ano = 1947;
		Ferrari.cor = "Vermelho";
		System.out.println("Ano: " + Ferrari.ano);
		System.out.println("Cor: " + Ferrari.cor);
		Ferrari.ligar();
		Ferrari.acelerar();
	
		// Objeto 2 - Carro Fusca
		Carro Fusca = new Carro();
		Fusca.ano = 1938;
		Fusca.cor = "Azul";
		System.out.println("Ano: " + Fusca.ano);
		System.out.println("Cor: " + Fusca.cor);
		Fusca.ligar();
		Fusca.desligar();
	
		Carro Camaro = new Carro();
		Camaro.ano = 1979;
		Camaro.cor = "Amarelo";
		System.out.println("Ano: " + Camaro.ano);
		System.out.println("Cor: " + Camaro.cor);
	
		Carro Uno = new Carro();
		Uno.ano = 2011;
		Uno.cor = "Branco";
		System.out.println("Ano: " + Uno.ano);
		System.out.println("Cor: " + Uno.cor);

	}

}
