/*
DATA: 07 de outrubro de 2026
Este código está relacionado com 06-Modificadores-Acesso.md"
Java
*/

package contas;

public class Conta {
	// atributos
	protected double saldo;
	protected String cliente;
	
	// construtor
	public Conta() {
		System.out.println("Agência 0261");
	}
	
	// método
	 protected void exibirSaldo() {
		System.out.println("Saldo: R$ " + saldo);
	}
	 
	 // método (função) que calcula um valor (debitar do saldo)
	 // está função recebe um valor quando "executada"
	 void sacar(double valor) {
		 saldo -= valor;
		 System.out.println("Débito: R$ " + valor);
	 }
	 
	 void depositar(double valor) {
		 saldo += valor;
		 System.out.println("Crédito: R$ " + valor);
	 }
	 
	 // sacar conta origem -> depositar conta destino
	 void transferir(Conta destino, double valor) {
		 this.sacar(valor);
		 destino.depositar(valor);
		 System.out.println("Transferência: " + valor);
	 }
	 
	 // Função que faz um cáculo e retorna um valor
	 // Neste caso NÃO usamos a palavra void e o retorno
	 // é OBRIGATÓRIO
	 double soma(double cc1, double cc2) {
		 double total = cc1 + cc2;
		 return total;
	 }

}
