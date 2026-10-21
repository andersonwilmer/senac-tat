/*
DATA: 19 e 20 de outrubro de 2026
Este código está relacionado com "12-Java-Agenda-Codigo.md"
Java
*/

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import view.Agenda;

public class Main {

    //psvma (cria o método main)
    public static void main(String[] args) {
        //desativar avisos verbosos --enable-native-access=ALL-UNNAMED
        //Uso do Flatlaf (tema visual moderno)
        FlatLightLaf.setup();
        //FlatDarkLaf.setup();

        Agenda janelaPrincipal = new Agenda();
        //Iniciando a janela principal
        janelaPrincipal.setVisible(true);
    }
}
