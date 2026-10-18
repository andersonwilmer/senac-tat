/*
DATA: 14 de outubro de 2026
Este código está relacionado com "08-Impressora.md"
Java
*/

package impressora;

//importa biblioteca Socket (usada para estabelecer uma conexão com a rede)
import java.net.Socket;
//importação de recursos para trabalhar com data e hora
import java.time.LocalDateTime;
//importação de data e hora
import java.time.format.DateTimeFormatter;
//importa biblioteca OutputStream (usada para enviar e receber dados)
import java.io.OutputStream;

public class Recibo {

	public static void main(String[] args) {
		// tratamento de exceções
		try {
			// criação de objetos
			// modificar o IP conforme rede
			Socket impressora = new Socket("10.26.45.49", 9100);
			OutputStream saida = impressora.getOutputStream();
			
			saida.write(new byte[] {0x1B, 0x40}); //Reseta e inicia a impressora
			saida.write(new byte[] {0x1B, 0x74, 0x02}); // Define tabela de caracteres
			
			LocalDateTime agora = LocalDateTime.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm"); //imprime data e hora
			DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //imprime data
			
			String dataHora = agora.format(formato) + "\n"; //variável data e hora
			String data = agora.format(formato2);
			
			saida.write(new byte[] {0x1B, 0x45, 0x01}); //negrito
			
			saida.write(new byte[] {0x1B, 0x61, 0x01}); //centraliza
			
			// 0x00 (normal), 0x11 (2x), 0x22 (3x), 0x33 (4x)
			saida.write(new byte[] {0x1D, 0x21, 0x11});
			
			saida.write("GRUPO GR\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x45, 0x00}); //tira o negrito
			
			saida.write(new byte[] {0x1D, 0x21, 0x00}); //LINHA normal
			
			saida.write("NOME DA RAZÃO SOCIAL COM. DE INFORMÁTICA LTDA-ME\n\n".getBytes("CP850"));
			
			saida.write("Rua dos Equipamentos, 9 - Sobreloja 101 e 102\n\n".getBytes("CP850"));
			
			saida.write("Centro - 20000-00 - Quillacollo/CBBA\n\n".getBytes("CP850"));
			
			saida.write("(11) 1111-2222 / 3333-4444\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x00}); //à esquerda
			
			saida.write("CNPJ: 11.111.111/0001-11          IE: 11.222.333\n\n".getBytes("CP850"));
			
			saida.write("CLIENTE: CONSUMIDOR FINAL\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x45, 0x01}); //negrito
			
			saida.write(new byte[] {0x1B, 0x61, 0x02}); //à direita
			
			saida.write("COMPROVANTE DE VENDA\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x45, 0x00}); //tira o negrito
			
			saida.write(new byte[] {0x1B, 0x61, 0x00}); //ESQUERDA
			
			saida.write(dataHora.getBytes("CP850"));
			
			saida.write(new byte[] {0x1D, 0x21, 0x11}); //LINHA 2x
			
			saida.write(new byte[] {0x1B, 0x61, 0x02}); //DIREITA
			
			saida.write("N°000001\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1D, 0x21, 0x00}); //LINHA normal
			
			saida.write(new byte[] {0x1B, 0x61, 0x01}); //centraliza
			
			saida.write("________________________________________________\n\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x00}); //ESQUERDA
			
			saida.write("CÓDIGO               DESCRIÇÃO          GARANTIA\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x02}); //DIREITA
			
			saida.write("QTD x UNIT         R$ VALOR\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x01}); //centraliza
			
			saida.write("________________________________________________\n\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x00}); //ESQUERDA
			
			saida.write(new byte[] {0x1B, 0x45, 0x01}); //negrito
			
			saida.write("05001  HD 500GB SEAGATE MOMENUS 7200.4        3M\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x02}); //DIREITA
			
			saida.write("1 x 250,00      250,00\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x00}); //ESQUERDA
			
			saida.write("05001 MEMÓRIA 4GB DDR3 133MHZ - MARKVISION    3M\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x02}); //DIREITA
			
			saida.write("1 x 70,00       70,00\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x01}); //centraliza
			
			saida.write("________________________________________________\n\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x00}); //ESQUERDA
			
			saida.write("Total da nota R$                          320,00\n\n".getBytes("CP850"));
			
			saida.write("Valor recebido R$                         400,00\n\n".getBytes("CP850"));
			
			saida.write("Troco R$                                   80,00\n\n\n".getBytes("CP850"));
			
			saida.write("FORMA DE PGTO.:".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x45, 0x00}); //tira o negrito
			
			saida.write("À VISTA\n\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x45, 0x01}); //negrito
			
			saida.write("DATA PGTO            R$ VALOR       TIPO PGTO\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x45, 0x00}); //tira o negrito
			
			saida.write(data.getBytes("CP850"));
			
			saida.write("            320,00         DINHEIRO\n\n".getBytes("CP850"));
			
			saida.write("________________________________________________\n\n".getBytes("CP850"));
			
			saida.write("VENDEDOR(A): VENDEDOR 1\n".getBytes("CP850"));
			
			saida.write("________________________________________________\n\n\n\n".getBytes("CP850"));
			
			saida.write("Números de Série:\n".getBytes("CP850"));
			
			saida.write("HJGB458454FJH, YHRUEF785848347583\n\n\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x61, 0x01}); //centraliza
			
			saida.write("Recebi a(s) mercadoria(s) acima descrita(s), \n".getBytes("CP850"));
			
			saida.write("concordando plenamente com os prazos e condições de garantia.\n\n\n\n".getBytes("CP850"));
			
			saida.write(".........................................\n".getBytes("CP850"));
			
			saida.write("ASSINATURA DO CLIENTE\n\n".getBytes("CP850"));
			
			saida.write("________________________________________________\n\n".getBytes("CP850"));
			
			saida.write(new byte[] {0x1B, 0x45, 0x01}); //negrito
			
			saida.write("* OBRIGADO E VOLTE SEMPRE *\n\n".getBytes("CP850"));
			
			// avanço de papel
			// Atenção o último byte 0x05 representa número de linhas
			// 0x02 (2 linhas) 0x05 (5 linhas) 0x06 (6 linhas)...
			saida.write(new byte[] {0x1B, 0x64, 0x05});
			
			// Corte de papel
			saida.write(new byte[] {0x1D, 0x56, 0x00});
			
			// imprimir
			saida.flush();
						
			// encerrar a conexão
			impressora.close();

			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
