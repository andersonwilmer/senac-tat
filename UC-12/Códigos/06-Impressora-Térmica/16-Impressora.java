/*
DATA: 14 de outubro de 2026
Este código está relacionado com "08-Impressora.md"
Java
*/

/**
 * Impressora térmica (servidor de impressão completo com memória, processador, placa de rede etc)
 * Serviço HTTP (servidor WEB porta 80)
 * Serviço RAW ESC/POS (porta 9100) "Linguagem de impressora"
 */

package impressora;

// importa biblioteca Socket (usada para estabelecer uma conexão com a rede)
import java.net.Socket;
// importação de recursos para trabalhar com data e hora
import java.time.LocalDateTime;
// importação de data e hora
import java.time.format.DateTimeFormatter;
// importa biblioteca OutputStream (usada para enviar e receber dados)
import java.io.OutputStream;

public class Impressora {

	public static void main(String[] args) {
		// tratamento de exceções
		try {
			// criação de objetos
			// modificar o IP conforme rede
			Socket impressora = new Socket("10.26.45.47", 9100);
			OutputStream saida = impressora.getOutputStream();
			
			// Formatação =====
			
			// Imprimir data e hora
			LocalDateTime agora = LocalDateTime.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			
			// criar variável para armazenar a data e hora formatada
			String dataHora = "Data: " + agora.format(formato) + "\n\n";
			
			// impressão da data e hora (imprimir o conteúdo da variável)
			saida.write(dataHora.getBytes("CP850"));
			
			// Negrito(BOLD)
			saida.write(new byte[] {0x1B, 0x45, 0x01});
			
			// Centralizado
			saida.write(new byte[] {0x1B, 0x61, 0x01});
			
			// Aumentar a fonte
			// Atenção! O último byte representa o tamanho
			// 0x00 (normal), 0x11 (2x), 0x22 (3x), 0x33 (4x)
			saida.write(new byte[] {0x1D, 0x21, 0x22});
			
			// CP850 = UTF-8  \n quebra de linha
			saida.write("PDV\n\n".getBytes("CP850"));
			
			// Texto normal (desativar negrito)
			saida.write(new byte[] {0x1B, 0x45, 0x00});
			
			// Alinhamento a esquerda
			saida.write(new byte[] {0x1B, 0x61, 0x00});
			
			// Tamanho normal
			saida.write(new byte[] {0x1D, 0x21, 0x00});
			
			// Alinhamento a direita
			// saida.write(new byte[] {0x1B, 0x61, 0x02});
			
			saida.write("Anderson Wilmer\n".getBytes("CP850"));
			
			// avanço de papel
			// Atenção o último byte 0x05 representa número de linhas
			// 0x02 (2 linhas) 0x05 (5 linhas) 0x06 (6 linhas)...
			saida.write(new byte[] {0x1B, 0x64, 0x05});
			
			// Corte de papel
			saida.write(new byte[] {0x1D, 0x56, 0x00});
			
			// Fim formatação =====
			
			// imprimir
			saida.flush();
			
			// encerrar a conexão
			impressora.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
