/*
DATA: 07 e 13 de outubro de 2026
Este código está relacionado com "06-Modificadores-Acesso.md" e "07-Encapsulamento.md"
Java
*/

package contas;

public class PessoaFisica {

	public static void main(String[] args) {
		// Objeto 1
		Conta cc1 = new Conta();
		cc1.setCliente("Leandro Ramos");
		cc1.setSaldo(10000.00);
		System.out.println("Cliente: " + cc1.getCliente());
		cc1.exibirSaldo();
		cc1.sacar(1000);
		cc1.exibirSaldo();
		System.out.println("-------------------------");
		
		// Objeto 2
		Conta cc2 = new Conta();
		cc2.setCliente("Sirlene Sanches");
		cc2.setSaldo(800.00);
		System.out.println("Cliente: " + cc2.getCliente());
		cc2.exibirSaldo();
		cc2.depositar(1000);
		cc2.exibirSaldo();
		System.out.println("-------------------------");
		
		// transferência
		System.out.println("Transferência");
		System.out.println("Cliente: " + cc1.getCliente());
		System.out.println("Favorecido: " + cc2.getCliente());
		cc1.transferir(cc2, 2000);
		System.out.println();
		System.out.println("Cliente: " + cc1.getCliente());
		cc1.exibirSaldo();
		System.out.println("Cliente: " + cc2.getCliente());
		cc2.exibirSaldo();
		System.out.println("-------------------------");

		// relatório gerencial
		System.out.println("Relatório Gerencial");
		Conta gerente = new Conta();
		// a linha abaixo cria um variável que armazena o resultado (retorno) da função soma(cc1, cc2)
		double relatorio = gerente.soma(cc1.getSaldo(), cc2.getSaldo());
		System.out.println("Saldo total nas contas: R$ " + relatorio);

	}

}
