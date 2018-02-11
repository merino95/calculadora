/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora.login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author inaki
 */
public class CalculadoraScrum {

   
    public static boolean isAdmin(String userName){        
        try(BufferedReader br = new BufferedReader(new FileReader("users.txt"));) {            
            String aux = br.readLine();
            while (!aux.substring(0, aux.indexOf(" ")).equals(userName)){
                aux = br.readLine();
            }
            return aux.charAt(aux.length()-1) == '1';
        } catch (FileNotFoundException ex) {
            System.err.println("Error no se encuentra el fichero de usuarios en la comprobacion de credenciales: " + ex);
            return false;
        } catch (IOException ex) {
            System.err.println("Excepcion en la comprobacion de credenciales: " + ex);
            return false;
        }        
    }
    
    public static boolean credentialsCheck(String userName, String password) {
        userName = userName.replace("\n","");
        userName = userName.replace(" ","");
        password = password.replace("\n","");
        password = password.replace(" ","");
        
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"));){
            
            String aux;

            while ((aux = br.readLine()) != null && !aux.substring(0, aux.indexOf(" ")).equals(userName)){}
            if(aux == null)
                return false;
            return aux.subSequence(aux.indexOf(" ") + 1, aux.length() - 1).equals(password);

        } catch (FileNotFoundException ex) {
            System.err.println("Error no se encuentra el fichero de usuarios en la comprobacion de credenciales: " + ex);
            return false;
        } catch (IOException ex) {
            System.err.println("Excepcion en la comprobacion de credenciales: " + ex);
            return false;
        }

    }

}
