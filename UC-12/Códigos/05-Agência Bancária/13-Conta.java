/*
DATA: 07 e 13 de outubro de 2026
Este código está relacionado com "06-Modificadores-Acesso.md" e "07-Encapsulamento.md"
Java
*/

package contas;

public class Conta {
	// atributos com encapsulamento
	private double saldo;
	private String cliente;
	
	// métodos get e set para acessar as variáveis encapsuladas
	// Dica: botão direito > source > generate getters and setters
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

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
