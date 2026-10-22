/*
DATA: 19, 20 e 21 de outubro de 2026
Este código está relacionado com "11-Java-Maven-MVC.md" e "12-Java-Agenda-Codigo.md"
Java
*/

package view;

import javax.swing.*;

// Criar a herança com JFrame
public class Agenda extends JFrame {

    //Código gerado pelo UI Designer (componentes)
    private JPanel pnlMain;
    private JPanel pnlHeader;
    private JPanel pnlBody;
    private JPanel pnlButton;
    private JPanel pnlFooter;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton buscarButton;
    private JButton whatsappButton;
    private JButton adicionarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JButton relatórioButton;
    private JButton limparButton;

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //quiosque (totem de autoatendimento)
        setUndecorated(true);
        //Iniciar a janela centralizada
        setLocationRelativeTo(null);

        //Incluir o formulário (UI Designer - painel principal)
        setContentPane(pnlMain);

        //mãozinha
        btnWhatsapp.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        lblSobre.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
    }//fim do construtor
} //fim da classe Agenda
