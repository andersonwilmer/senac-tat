/*
DATA: 16 de novembro de 2026
Este código está relacionado com "16-Agenda-Relatorios.md"
Java
*/

package service;

import dao.ContatoDAO;
import model.Contato;
import org.openpdf.text.*;
import org.openpdf.text.Font;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioService {
    private final ContatoDAO contatoDAO = new ContatoDAO();


    public void gerarRelatorioContatos()
            throws SQLException, IOException, DocumentException {

        // Buscar os contatos cadastrados
        //PASSO 2 do slide Service (espera o retorno)
        List<Contato> contatos = contatoDAO.listarTodos();

        // Criar a pasta de relatórios
        File pasta = new File(
                System.getProperty("user.home"),
                "Agenda/relatorios"
        );

        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        // Definir o arquivo PDF
        File arquivo = new File(pasta, "relatorio_contatos.pdf");

        // Criar o documento
        Document documento = new Document();

        try {
            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream(arquivo)
            );

            documento.open();

            // Título
            Font fonteTitulo = new Font(
                    Font.HELVETICA, 18, Font.BOLD
            );

            Paragraph titulo = new Paragraph(
                    "RELATÓRIO DE CONTATOS", fonteTitulo
            );

            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // Data e hora
            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String dataHora =
                    LocalDateTime.now().format(formato);

            Paragraph data = new Paragraph(
                    "Data de emissão: " + dataHora
            );

            data.setAlignment(Element.ALIGN_CENTER);
            documento.add(data);

            documento.add(new Paragraph(" "));

            // Tabela
            PdfPTable tabela = new PdfPTable(3);

            tabela.setWidths(new float[]{1.0f, 3.0f, 2.0f});
            tabela.setWidthPercentage(100);

            tabela.addCell("ID");
            tabela.addCell("Nome");
            tabela.addCell("Telefone");

            //Percorre o model listando os contatos
            for (Contato contato : contatos) {
                tabela.addCell(String.valueOf(contato.getIdContato()));
                tabela.addCell(contato.getNome());
                tabela.addCell(contato.getFone());
            }

            documento.add(tabela);

            documento.add(new Paragraph(" "));

            // Total de contatos
            Font fonteTotal = new Font(
                    Font.HELVETICA, 10, Font.BOLD
            );

            Paragraph total = new Paragraph(
                    "Total de contatos: " + contatos.size(),
                    fonteTotal
            );

            total.setAlignment(Element.ALIGN_RIGHT);
            documento.add(total);

        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }

        //PASSO 6 do slide Service

        // Abrir o PDF no programa padrão
        if (Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {

            Desktop.getDesktop().open(arquivo);
        }
    }

}
