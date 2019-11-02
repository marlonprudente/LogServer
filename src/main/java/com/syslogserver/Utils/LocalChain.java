/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syslogserver.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
import static org.web3j.crypto.Hash.sha3;

/**
 *
 * @author Marlon
 */
public class LocalChain implements Serializable {

    ArrayList<Block> chain;

    public LocalChain() {
        chain = new ArrayList();
        Block genesis = createGenesisBlock();
        genesis.hash = sha3("0".getBytes());
        System.out.println("Genesis Hash: " + Hex.toHexString(genesis.hash));
        this.chain.add(0,genesis);
        toLocalChain();
    }

    private Block createGenesisBlock() {
        Date date = new Date();
        return new Block(0, date.toInstant(), "Genesis", "0".getBytes());
    }
    
    private synchronized void toLocalChain() {
        File arq = new File("logs/localchain.lchain");
        try {
            arq.delete();
            arq.createNewFile();
            try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq))) {
                objOutput.writeObject(this);
            }
        } catch (IOException erro) {
            System.out.printf("Erro ao salvar .lchain de localchain: ", erro.getMessage());
        }
    }

    public Block getLatestBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    public void addBlock(String data) {
        Date date = new Date();
        Block newBlock = new Block(getLatestBlock().getIndex() + 1, date.toInstant(), data, this.getLatestBlock().hash);
        this.chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int blocks = 1; blocks < this.chain.size(); blocks++) {
            Block currentBlock = this.chain.get(blocks);
            Block previousBlock = this.chain.get(blocks - 1);
            
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                return false;
            }
            
            if(!currentBlock.previousHash.equals(previousBlock.hash)){
                return false;
            }

        }
        return true;
    }
}
