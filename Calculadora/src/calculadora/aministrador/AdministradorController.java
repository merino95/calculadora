package calculadora.aministrador;

import calculadora.InterfazCalculadora;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AdministradorController {

    void abrirCalculadora(Administrador admin) {

        java.awt.EventQueue.invokeLater(new RunnableImpl());

        admin.setVisible(false);
    }

    void crearUsuario(String nombre, String contraseña, boolean administrador) {
        File fin = new File("users.txt");
        try (FileInputStream fis = new FileInputStream(fin);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis))) {

            String aLine;
            while ((aLine = in.readLine()) != null) {
                // Check each line for the string, and write if it doesn't have it:
                if ((aLine.substring(0, aLine.indexOf(" "))).equals(nombre)) {
                    JOptionPane.showMessageDialog(null, "Usuario ya exise");
                    return;
                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println("error");
        } catch (IOException ex) {
            System.err.println("error");
        }

        
        File fout = new File("users.txt");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fout, true)));){
            
            
            String adm;
            if (administrador) {
                adm = "1";
            } else {
                adm = "0";
            }
            String line = nombre + " " + contraseña + adm;
            out.println(line);
        } catch (IOException ex) {
            System.err.println("error");
        }
    }

    void borrarUsuario(String nombre) {
        if (nombre.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Carácter no valido (espacio)");
        } else {
            File fin = new File("users.txt");
            File fout = new File("usuariosTMP.txt");

            try (FileWriter fstream = new FileWriter(fout, true);
                    FileInputStream fis = new FileInputStream(fin);
                    BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                    BufferedWriter out = new BufferedWriter(fstream);) {

                String aLine;
                while ((aLine = in.readLine()) != null) {
                    // Check each line for the string, and write if it doesn't have it:
                    if (!(aLine.substring(0, aLine.indexOf(" "))).equals(nombre)) {
                        out.write(aLine);
                        out.newLine();
                    }
                }

                in.close();
                out.close();

                fin.delete();
                fout.renameTo(fin);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al borrar usuario");
            }
        }
    }

    void verHistorial() {
        try {
            File fin = new File("historial.txt");
            FileInputStream fis = new FileInputStream(fin);
            String content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(fis))) {
                content = "";
                String aLine;
                while ((aLine = in.readLine()) != null) {
                    content = content + aLine + "\n";
                }
            }

            JTextArea textArea = new JTextArea(content);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setMargin(new Insets(5, 5, 5, 5));
            JScrollPane scrollPane = new JScrollPane(new JLabel(content));
            scrollPane.setPreferredSize(new Dimension(400, 300));
            scrollPane.getViewport().setView(textArea);
            Object message = scrollPane;            
            JOptionPane.showConfirmDialog(null, message, "Historial de uso", JOptionPane.DEFAULT_OPTION);

        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar usuario "+e);
        }

    }

    private static class RunnableImpl implements Runnable {

        public RunnableImpl() {
        }

        @Override
        public void run() {
            new InterfazCalculadora().setVisible(true);
        }
    }

}
