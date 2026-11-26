/*
DATA: 19, 20, 21, 26, 27 de outubro, 09, 11 16 e 25 de novembro de 2026
Este código está relacionado com "11-Java-Maven-MVC.md", "12-Java-Agenda-Codigo.md", "13-Agenda-Data-Tela-Sobre.md", "14-Agenda-Conexao-MySQL.md", 
"15-Agenda-DAO-Cadastro.md", "16-Agenda-Relatorios.md" e "17.md"
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
import java.net.URI;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

//Criar a herança com JFrame
public class Agenda extends JFrame {
    //Código gerado UI Designer (componentes)
    private JPanel pnlMain;
    private JPanel pnlHeader;
    private JPanel pnlButton;
    private JPanel pnlBody;
    private JPanel pnlFooter;
    private JTextField txtId;
    private JTextField txtNome;
    //Observar quando mudar para um campo JFormattedTextField
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

    //CONTROLLER
    private final ContatoController contatoController = new ContatoController();

    //Service (relatórios)
    private final RelatorioService relatorioService = new RelatorioService();

    //Filtro para limitar os caracteres de um componente (JTextField)
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

    //Construtor
    public Agenda() {
        //Configuração da janela

        //Tamanho em pixels
        setSize(600, 360);

        //Comportamento da janela ao fechar (encerrar a aplicação)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Título
        setTitle("Agenda");

        //Ícone de janela
        ImageIcon icone = new ImageIcon(getClass().getResource("/icons/favicon.png"));
        setIconImage(icone.getImage());

        //Impedir o redimensionamento
        setResizable(false);

        //Iniciar em tela cheia
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Quiosque (totem de autoatendimento)
        //setUndecorated(true);

        //Iniciar a janela centralizada
        setLocationRelativeTo(null);

        //Incluir o formulário (UI Designer - painel principal)
        setContentPane(pnlMain);

        //Associar o Enter ao botão de busca
        getRootPane().setDefaultButton(btnBuscar);

        //Mãozinha
        btnWhatsapp.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        lblSobre.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        //Limitar o campo txtNome
        AbstractDocument documentTxtNome = (AbstractDocument) txtNome.getDocument();
        documentTxtNome.setDocumentFilter(new LimiteCaracteres(30));

        //Uso de máscaras
        try {
            //Definir o tipo de máscara
            //"#" Aceita apenas números
            //"A" Aceita caracteres e números
            //"U" Aceita apenas letras e transforma em maiúsculo (Upper case)
            //"L" Aceita apenas letras e transforma em minúsculo (Lower case)
            //"?" Aceita qualquer caractere (símbolos, espaço etc.)

            //Máscara de telefone
            MaskFormatter mascaraFone = new MaskFormatter("(##) #####-####");
            mascaraFone.setPlaceholderCharacter('_');
            txtFone.setFocusLostBehavior(JFormattedTextField.PERSIST);
            txtFone.setFormatterFactory(new DefaultFormatterFactory(mascaraFone));

        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        //"Chamar" o método para atualizar a data
        atualizarData();

        //Chamar o método para testar a conexão
        verificarConexao();

        //Evento: clicar no ícone SOBRE para abrir a tela Sobre (tela secundária)
        lblSobre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //Dica para entendimento da lógica e debug (teste do evento)
                //IO.println("teste do botão SOBRE");

                //Executar o método SOBRE
                sobre();
            }
        });

        //Evento do botão adicionar
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarContato();
            }
        });

        //Evento do botão limpar
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        //Evento do botão relatório
        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });

        //Evento do botão buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarContato();
            }
        });

        //Evento do botão WhatsApp
        btnWhatsapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acessarWhatsapp();
            }
        });

        //Evento do botão editar
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarContato();
            }
        });

    }//Fim do construtor

    //Limpar campos =======================================
    private void limpar() {
        txtId.setText(null);
        txtNome.setText(null);
        txtFone.setValue(null); //JFormattedTextField
        txtNome.requestFocusInWindow(); //Posiciona o cursor no nome
        btnAdicionar.setEnabled(true); //Habilitar o botão adicionar
    }

    //Testar a conexão com o banco de dados
    private void verificarConexao() {
        //Criar o objeto para acessar o método testarConexao()
        ConnectionFactory conexao = new ConnectionFactory();

        //Se retorno for verdadeiro, conexão bem-sucedida
        if (conexao.testarConexao() == true) {
            lblBanco.setText("Banco Conectado");
            lblDatabase.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/dbon.png"))));
        } else {
            lblBanco.setText("Banco desconectado");
            lblDatabase.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/dboff.png"))));
        }
    }

    //Obter a data - criação de método ------------------------------
    //Dica de segurança: usar private se o método é exclusivo da classe
    private void atualizarData() {
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //.setText modifica o texto da label pela data formatada
        lblData.setText(hoje.format(formato));
    }

    //Abrir a tela SOBRE --------------------------
    private void sobre() {
        Sobre sobre = new Sobre(); //Criar o objeto
        sobre.setVisible(true); //Abrir a janela SOBRE
    }

    //CRUD Create ===================================
    private void adicionarContato() {
        //Instanciar o contato (model)
        Contato contato = new Contato();

        //Setar as variáveis do model com os dados do formulário
        //PASSO 1 do slide CRUD Create
        contato.setNome(txtNome.getText());
        contato.setFone(txtFone.getText());

        //Mensagens (validações)
        try {
            //Validação e execução
            //PASSO 2 do slide CRUD Create
            contatoController.adicionar(contato);

            //Caso OK
            JOptionPane.showMessageDialog(
                    this,
                    "Contato cadastrado com sucesso!",
                    "Agenda de contatos",
                    JOptionPane.INFORMATION_MESSAGE
            );

            //Limpar os campos
            limpar();

        } catch (IllegalArgumentException e) {
            //Erro de validação
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {
            //Exceção
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível cadastrar o contato.\n" + e.getMessage(),
                    "Erro no banco de dados",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    //Fim CRUD Create ==============================

    //Gerar relatório de contatos ================================
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
    //Fim relatório de contatos ==================================

    //CRUD Read ====================================================
    private void buscarContato() {
        try {
            //Executar o Controller e DAO passando o nome para busca
            //PASSO 1 do slide CRUD Read
            Contato contato = contatoController.buscarPorNome(txtNome.getText());

            if (contato != null) {
                //Preencher os campos de texto com o conteúdo do Model
                //PASSO 6 do slide CRUD Read
                txtId.setText(String.valueOf(contato.getIdContato()));
                txtNome.setText(contato.getNome());
                txtFone.setText(contato.getFone());

                //Desativar o botão adicionar
                btnAdicionar.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum contato encontrado.",
                        "Agenda de contatos",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {
            //Validação do Controller
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {
            //Erro relacionado ao banco
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao buscar contato.\n" + e.getMessage(),
                    "Erro no banco de dados",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    //Fim CRUD Read ==============================================

    //Acessar WhatsApp Web ========================================
    //Essa implementação considera que os telefones cadastrados são brasileiros
    //e que o campo utiliza a máscara (##) #####-####.
    private void acessarWhatsapp() {
        String fone = txtFone.getText();

        //Validar se o telefone foi preenchido
        if (fone == null || !fone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe um telefone válido antes de abrir o WhatsApp.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            txtFone.requestFocusInWindow();
            return;
        }

        //Remover os caracteres da máscara
        fone = fone.replaceAll("\\D", "");

        //Montar o link do WhatsApp Web
        String url = "https://web.whatsapp.com/send?phone=55" + fone;

        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI(url);
            desktop.browse(uri);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível abrir o WhatsApp Web.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            System.err.println(e.getMessage());
        }
    }
    //Fim WhatsApp ================================================

    //CRUD Update ===================================
    private void editarContato() {
        try {
            //Verificar se um contato foi buscado
            if (txtId.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Busque um contato antes de editar.");
            }

            //Instanciar o contato (model)
            Contato contato = new Contato();

            //Setar as variáveis do model com os dados do formulário
            contato.setIdContato(Integer.parseInt(txtId.getText()));
            contato.setNome(txtNome.getText());
            contato.setFone(txtFone.getText());

            //Validação e execução
            //PASSO 2 do slide CRUD Update
            boolean atualizado = contatoController.editar(contato);

            //Caso OK
            if (atualizado == true) {
                JOptionPane.showMessageDialog(
                        this,
                        "Contato atualizado com sucesso!",
                        "Agenda de contatos",
                        JOptionPane.INFORMATION_MESSAGE
                );

                //Limpar os campos
                limpar();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Contato não encontrado para atualização!",
                        "Agenda de contatos",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {
            //Erro de validação
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {
            //Exceção
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível editar o contato.\n" + e.getMessage(),
                    "Erro no banco de dados",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    //Fim CRUD Update ==============================

}//Fim da classe Agenda
