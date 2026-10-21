/*
DATA: 15 de outubro de 2026
Este código está relacionado com "09-PDF-Java.md"
Java
*/

package report;

// biblioteca interna que lida com o sistema operacional (interface gráfica)
import java.awt.Desktop;
//biblioteca interna para entrada e saída
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// biblioteca externa (openpdf)
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class RelatorioTemplate {

	public static void main(String[] args) {
		// obrigatório o uso do try catch
		try {
			// Criar o objeto document (ducumento pdf)
			// PageSize.A4.rotate() - página deitada (landscape)
			Document document = new Document(PageSize.A4);
			
			// Caminho e novo do arquivo
			String caminho = "relatório.pdf";
			
			// Criar o arquivo pdf
			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			
			document.open(); // inicio da formatação (conteúdo do pdf)
			
			// Título personalizado
			Font fontTitulo = new Font(Font.HELVETICA, 16, Font.BOLD);
			Paragraph titulo = new Paragraph("RELATÓRIO", fontTitulo);
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);
			
			// imprimir data e hora
			LocalDateTime agora = LocalDateTime.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			
			// A linha abaixo cria uma variável que armazena a data e hora
			String dataHora = agora.format(formato);
			
			// parágrafo centralizado e personalizado
			Paragraph dataEmissao = new Paragraph("Data de emissão: " + dataHora);
			
			// acessando os métodos na classe modelo Paragraph
			dataEmissao.setAlignment(Element.ALIGN_CENTER);
			document.add(dataEmissao);
			
			// parágrafo simples
			document.add(new Paragraph("Conteúdo"));
			
			// espaço
			document.add(new Paragraph(" "));
			
			// tabela
			// Criar um objeto tabela com 3 colunas
			PdfPTable tabela = new PdfPTable(3);
			
			// Espaçamento entre colunas
			tabela.setWidths(new float[] {1.0f, 3.0f, 2.0f});
			
			// Ocupar a largura inteira da folha
			tabela.setWidthPercentage(100);
			
			// Formatação da tabela
			Font fontCabecalho = new Font(Font.HELVETICA, 11, Font.BOLD, java.awt.Color.WHITE);
			
			// cor RGB
			java.awt.Color corCabecalho = new java.awt.Color(0, 124, 240);
			
			// Cabeçalho da tabela
			String[] colunas = {"ID", "Nome", "Fone"};
			for (int i = 0; i < colunas.length; i++) {
				PdfPCell celulaCabecalho = new PdfPCell(new Paragraph(colunas[i], fontCabecalho));
				celulaCabecalho.setBackgroundColor(corCabecalho);
				celulaCabecalho.setPadding(5f); // espaçamento interno
				// celulaCabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
				tabela.addCell(celulaCabecalho);
			}
			
			// corpo da tabela (futuramente vai trazer do banco de dados)
			tabela.addCell("1");
			tabela.addCell("Bill Gates");
			tabela.addCell("(11) 99999-1234");
			
			tabela.addCell("2");
			tabela.addCell("Linus Torvalds");
			tabela.addCell("(11) 99999-4321");
			
			document.add(tabela);
			
			document.close(); // fim da formatação (conteúdo do pdf)
			
			// Abrir o pdf automaticamente no leitor padrão do cliente
			File arquivo = new File(caminho);
			Desktop.getDesktop().open(arquivo);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
