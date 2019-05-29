
import com.syslogserver.Utils.Cryptograph;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marlon
 */
public class CriptoTest {

    public static void main(String[] args) {
        try {
            Cryptograph cp = new Cryptograph("teste123");
            String enc = cp.encrypt("Marlon Prudente");
            System.out.println("Enc: " + enc);
            String dec = cp.decrypt(enc);
            System.out.println("Dec: " + dec);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
