/*
DATA: 07 e 13 de outubro de 2026
Este código está relacionado com "06-Modificadores-Acesso.md" e "07-Encapsulamento.md"
Java
*/

package seguros;

// importação da classe Conta (outro pacote)
import contas.Conta;

public class SeguroPessoaFisica extends Conta {

	public static void main(String[] args) {
		// Objeto 1
		SeguroPessoaFisica cc3 = new SeguroPessoaFisica();
		cc3.setCliente("Junior Magalhães");
		cc3.setSaldo(9000);
		System.out.println("Cliente: " + cc3.getCliente());
		cc3.exibirSaldo();

	}

}
