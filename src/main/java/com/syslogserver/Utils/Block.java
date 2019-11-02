/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import org.bouncycastle.util.encoders.Hex;
import static org.web3j.crypto.Hash.sha3;
/**
 *
 * @author Marlon Prudente
 */
public class Block implements Serializable{
    
    long index;
    Instant timestamp;
    String data;
    public byte[] previousHash;
    public byte[] hash;
    
    public Block(long index, Instant timestamp, String data, byte[] previousHash){
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }
    
    public byte[] calculateHash(){
        byte[] hashValue = null;
        try{
            FileWriter lastHash = new FileWriter("logs/hashs.log", true);
            byte[] c = new byte[previousHash.length + data.getBytes().length];
            System.arraycopy(previousHash, 0, c, 0, previousHash.length);
            System.arraycopy(data.getBytes(), 0, c, previousHash.length, data.getBytes().length);
            hashValue = sha3(c);
            lastHash.write(Hex.toHexString(hashValue));
            lastHash.write(System.lineSeparator());
            lastHash.close();
        }catch(IOException e){
            System.out.println("Erro: " + e);
        }
        return hashValue;
    }    
    public long getIndex(){
        return this.index;
    }
    
    public String getData(){
        return this.data;
    }
}
