/*
DATA: 26 de outubro de 2026
Este código está relacionado com "13-Agenda-Data-Tela-Sobre.md"
Java
*/

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class Sobre extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel pnlBody;
    private JLabel lblGithub;

    public Sobre() {
        //configuração da janela
        setSize(380,230);
        //comportamento ao fechar
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //titulo
        setTitle("Sobre");
        //Ícone de janela
        ImageIcon icone = new ImageIcon(getClass().getResource("/icons/favicon.png"));
        setIconImage(icone.getImage());
        //Impedir o redicionamento
        setResizable(false);
        //Iniciar a janela centralizada
        setLocationRelativeTo(null);

        setContentPane(contentPane); //painel principal
        setModal(true); //impedir a abertura de múltiplas instâncias da janela
        getRootPane().setDefaultButton(buttonOK); //associado ao Enter

        //mãozinha
        lblGithub.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        //Evento do botão OK (pressionar o botão)
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(); //executa o método onOK()
            }
        });
        //Evento do link do github (abrir o repositório)
        lblGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                acessarGithub("https://github.com/andersonwilmer");
            }
        });
    } //fim do construtor

    //método para fechar a janela
    private void onOK() {
        //fecha apenas está janela
        dispose();
    }

    //método para abrir um link no navegador padrão do desktop cliente
    private void acessarGithub(String url) {
        //a linha abaixo obtém o sistema operacional do cliente
        Desktop desktop = Desktop.getDesktop();
        try {
            //criação de um objeto para acessar os métodos necessários estabelecer uma conexão com a internet abrindo o link no navegador padrão do cliente
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} //fim da classe Sobre
