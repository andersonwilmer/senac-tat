/*
DATA: 07 de outrubro de 2026
Este código está relacionado com 06-Modificadores-Acesso.md"
Java
*/

package seguros;

// importação da classe Conta (outro pacote)
import contas.Conta;

public class SeguroPessoaFisica extends Conta {

	public static void main(String[] args) {
		// Objeto 1
		SeguroPessoaFisica cc3 = new SeguroPessoaFisica();
		cc3.cliente = "Junior Magalhães";
		cc3.saldo = 9000;
		System.out.println("Cliente: " + cc3.cliente);
		cc3.exibirSaldo();

	}

}
