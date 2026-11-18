/*
DATA: 19, 20, 21, 26, 27 de outubro, 09, 11 e 16 de novembro de 2026
Este código está relacionado com "11-Java-Maven-MVC.md", "12-Java-Agenda-Codigo.md", "13-Agenda-Data-Tela-Sobre.md", "14-Agenda-Conexao-MySQL.md", "15-Agenda-DAO-Cadastro.md" e "16-Agenda-Relatorios.md"
Java
*/

package view;

import connection.ConnectionFactory;
import controller.ContatoController;
import model.Contato;
import org.openpdf.text.DocumentException;
import service.RelatorioService;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// Criar a herança com JFrame
public class Agenda extends JFrame {

    //Código gerado pelo UI Designer (componentes)
    private JPanel pnlMain;
    private JPanel pnlHeader;
    private JPanel pnlBody;
    private JPanel pnlButton;
    private JPanel pnlFooter;
    private JTextField txtId;
    private JTextField txtNome;
    //Atenção! Observar quando o campo mudar para JFormattedTextField
    private JFormattedTextField txtFone;
    private JButton btnBuscar;
    private JButton btnWhatsapp;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnRelatorio;
    private JButton btnLimpar;
    private JLabel lblSobre;
    private JLabel lblDatabase;
    private JLabel lblBanco;
    private JLabel lblData;

    //controler
    private final ContatoController contatoController = new ContatoController();

    //service (relatórios)
    private final RelatorioService relatorioService = new RelatorioService();

    //filtro para Limitar os caracteres de um componente JTextField
    private static class LimiteCaracteres extends DocumentFilter {

        private final int limite;

        public LimiteCaracteres(int limite) {
            this.limite = limite;
        }

        @Override
        public void insertString(FilterBypass fb, int offset,
                                 String texto, AttributeSet atributos)
                throws BadLocationException {

            replace(fb, offset, 0, texto, atributos);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int tamanho,
                            String texto, AttributeSet atributos)
                throws BadLocationException {

            if (texto == null) {
                texto = "";
            }

            int tamanhoAtual = fb.getDocument().getLength();

            int novoTamanho = tamanhoAtual - tamanho + texto.length();

            if (novoTamanho <= limite) {
                fb.replace(offset, tamanho, texto, atributos);
            }
        }
    }

    //construtor
    public Agenda() {
        //Configuração da janela

        //Tamanho da janela em pixels
        setSize(600, 360);
        //Comportamento da janela ao fechar (encerrar a aplicação)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Título da janela
        setTitle("Agenda");
        //Ícone de janela
        ImageIcon icone = new ImageIcon(getClass().getResource("/icons/favicon.png"));
        setIconImage(icone.getImage());
        //Impedir o redicionamento
        setResizable(false);
        //Inciar em tela cheia
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //quiosque (totem de autoatendimento)
        //setUndecorated(true);
        //Iniciar a janela centralizada
        setLocationRelativeTo(null);

        //Incluir o formulário (UI Designer - painel principal)
        setContentPane(pnlMain);

        //mãozinha
        btnWhatsapp.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        lblSobre.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        //Limitar o campo txtNome
        AbstractDocument documentoTxtNome = (AbstractDocument) txtNome.getDocument();
        documentoTxtNome.setDocumentFilter((new LimiteCaracteres(30)));

        //Uso de máscaras
        try {
            //Definir o tipo de máscara
            //"#" aceita apenas números
            //"A" aceita caracteres e números
            //"U" aceita apenas letras e transforma em maiúsculo (upper case)
            //"L" aceita apenas letras e transforma em minusculo (lower case)
            //"?" aceita qualquer caractere (símbolos, espaço, etc)

            //Máscara de telefone
            MaskFormatter mascaraFone = new MaskFormatter("(##) #####-####");
            mascaraFone.setPlaceholderCharacter('_');
            txtFone.setFormatterFactory(new DefaultFormatterFactory(mascaraFone));
            txtFone.setFocusLostBehavior(JFormattedTextField.PERSIST);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        //"Chamar" o método para atualizar a data
        atualizarData();

        //evento clicar no ícone Sobre para abrir a tela Sobre
        lblSobre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Dica para entendimento da lógica e debug (teste do evento)
                //IO.println("Teste do Sobre");
                //executar o método Sobre
                sobre();
            }
        });

        //"Chamar" o método para testar conexão
        verificarConexao();

        //evento do botão adicionar
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //IO.println("teste do botão adicionar");
                adicionarContato();
            }
        });

        //evento do botão limpar
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        //evento do botão Relatório
        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
    }//fim do construtor

    //Gerar o relatório de contatos
    private void gerarRelatorio() {
        try {
            //PASSO 1 do slide Service
            relatorioService.gerarRelatorioContatos();
            JOptionPane.showMessageDialog(
                    this,
                    "Relatório gerado com sucesso!",
                    "Relatório",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (SQLException | IOException | DocumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível gerar o relatório.\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    //Fim relatório de contatos

    //Limpar campos
    private void limpar() {
        txtId.setText(null);
        txtNome.setText(null);
        txtFone.setValue(null); //JFormattedTextField
        txtNome.requestFocusInWindow();
    }

    //Testar a conexão com o banco de dados
    private void verificarConexao() {
        //Criar o objeto para criar o método testarConexao()
        ConnectionFactory conexao = new ConnectionFactory();
        //se retorno for verdadeiro conexão bem sucedida
        if(conexao.testarConexao() == true) {
            lblBanco.setText("Banco conectado");
            //Trocar o ícone de uma label
            lblDatabase.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/dbon.png"))));
        } else {
            lblBanco.setText("Banco desconectado");
            lblDatabase.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/dboff.png"))));
        }
    }


    //Obter a data
    //Dica de segurança: usar private se o método é exclusivo da classe
    private void atualizarData(){
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //.setText modifica o texto pela data formatada
        lblData.setText(hoje.format(formato));
    }

    //Abrir a tela Sobre
    private void sobre() {
        Sobre sobre = new Sobre(); //criar objeto
        sobre.setVisible(true); //abrir a janela
    }

    //CRUD Create ===============
    private void adicionarContato() {
        //Instanciar o contato (model)
        Contato contato = new Contato();
        //setar as variáveis do model com os dados do form
        //PASSO 1 do slide CRUD Create
        contato.setNome(txtNome.getText());
        contato.setFone(txtFone.getText());
        //mensagens (validações)
        try {
            //validação e execução
            //PASSO 2 do slide CRUD Create
            contatoController.adicionar(contato);
            //caso OK
            JOptionPane.showMessageDialog(
                    this,
                    "Contato cadastrado com sucesso!",
                    "Agenda de Contatos",
                    JOptionPane.INFORMATION_MESSAGE
            );
            //limpar os campos
            limpar();
        } catch (IllegalArgumentException e) {
            //erro de validação
            JOptionPane.showMessageDialog(
                    this,e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (SQLException e) {
            //exceção
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível cadastrar o contato. \n" + e.getMessage(),
                    "Erro no banco de dados",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    //Fim CRUD Create ===========

} //fim da classe Agenda
