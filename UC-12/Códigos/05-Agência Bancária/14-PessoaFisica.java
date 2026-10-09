/*
DATA: 07 de outrubro de 2026
Este código está relacionado com 06-Modificadores-Acesso.md"
Java
*/

package contas;

public class PessoaFisica {

	public static void main(String[] args) {
		// Objeto 1
		Conta cc1 = new Conta();
		cc1.cliente = "Leandro Ramos";
		cc1.saldo = 10000.00;
		System.out.println("Cliente: " + cc1.cliente);
		cc1.exibirSaldo();
		cc1.sacar(1000);
		cc1.exibirSaldo();
		System.out.println("-------------------------");
		
		// Objeto 2
		Conta cc2 = new Conta();
		cc2.cliente = "Sirlene Sanches";
		cc2.saldo = 800.00;
		System.out.println("Cliente: " + cc2.cliente);
		cc2.exibirSaldo();
		cc2.depositar(1000);
		cc2.exibirSaldo();
		System.out.println("-------------------------");
		
		// transferência
		System.out.println("Transferência");
		System.out.println("Cliente: " + cc1.cliente);
		System.out.println("Favorecido: " + cc2.cliente);
		cc1.transferir(cc2, 2000);
		System.out.println();
		System.out.println("Cliente: " + cc1.cliente);
		cc1.exibirSaldo();
		System.out.println("Cliente: " + cc2.cliente);
		cc2.exibirSaldo();

	}

}
