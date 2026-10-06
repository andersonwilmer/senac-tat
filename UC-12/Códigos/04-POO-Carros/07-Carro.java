/*
DATA: 02 e 06 de outrubro de 2026
Este código está relacionado com "04-Abstração.md e 05-Heranca-Polimorfismo.md"
Java
*/

package veiculos;

import java.util.Random;

public class Carro {
	
	// atributos
	int ano;
	String cor;
	
	// construtor
	public Carro() {
		System.out.println("---");
		System.out.println();
	
		// exemplo de gerador de caracteres aleatórios
		String chassi = new String("123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		Random gerador = new Random();
		System.out.print("Chassi: * ");
		for (int i = 1; i < 11; i++) {
	
			// obter 10 números aleatórios da String Chassi
			char numeracao = (char) gerador.nextInt(chassi.length());
			System.out.print(chassi.charAt(numeracao));
		}
		System.out.println(" *");
	}
	
	// métodos ("funções")
	void ligar() {
		System.out.println("Carro ligado");
	}
	
	void desligar() {
		System.out.println("Carro desligado");
	}
	
	void acelerar() {
		System.out.println("Acelerando");
	}

}
