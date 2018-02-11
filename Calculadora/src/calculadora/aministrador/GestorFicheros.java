package calculadora.aministrador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labora1
 */
public class GestorFicheros {
    public static void anadeUsuario(String file, String user, String password, int admin) throws IOException{
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file,true)))) {
            String line = user+" "+password+" "+admin;
            out.println(line);
        }
    }
    public static void anadeOperacion(String file, String user, int result) throws IOException{
        
    }
}
