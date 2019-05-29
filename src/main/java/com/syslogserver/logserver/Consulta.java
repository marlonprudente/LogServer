/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.logserver;

import com.syslogserver.Utils.Cryptograph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

/**
 *
 * @author Marlon
 */
public class Consulta {

 
    
    public static void main(String[] args) throws InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        Cryptograph cp = new Cryptograph("tccmarlonprudente");        
           
        while (true) {

            System.out.println("1 - Consultar registros Log no Blockchain");
            Scanner scanner = new Scanner(System.in);
            Integer op = scanner.nextInt();
            String input;
            switch (op) {
                case 1:
                    System.out.println("Digite a data do dia no formato ddMMyyyy: ");
                    input = scanner.next();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("logs/" + input + ".log"));
                        while (br.ready()) {
                            String linha = br.readLine();
                            System.out.println(linha);
                            String data = web3j.ethGetTransactionByHash(linha.trim()).send().getTransaction().get().getInput();
                            byte[] bytes = Hex.decode(data.substring(2));
                            String dec = new String(bytes, "UTF-8");
                            System.out.println("" + dec);
                            //System.out.println(cp.decrypt(dec));
                        }
                        br.close();

                    } catch (Exception e) {
                        System.out.println("Erro: " + e);
                    }
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }

}
