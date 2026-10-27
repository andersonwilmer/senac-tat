/*
DATA: 19, 20, 21 e 26 de outubro de 2026
Este código está relacionado com "11-Java-Maven-MVC.md", "12-Java-Agenda-Codigo.md" e "13-Agenda-Data-Tela-Sobre.md"
Java
*/

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private JTextField txtFone;
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

        //Chamar o método para atualizar a data
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
    }//fim do construtor

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
} //fim da classe Agenda
