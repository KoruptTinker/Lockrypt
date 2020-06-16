package com.lockrypt.backend.blockchain;

import java.util.ArrayList; 

public class block_chain {
	public static ArrayList<block> blockchain = new ArrayList<block>();
	public static int difficulty = 3;
    public static void Create_Blockchain(){
		byte[] data2 =new byte[0];
    	blockchain.add(new block("Initial block", "0",data2, null));
    	blockchain.get(0).mining(difficulty);
    }
	
	public static void Add_Block(String data, byte[] data2, String fileName){
		
		blockchain.add(new block( data, blockchain.get(blockchain.size() - 1) .hash,data2, fileName)); 
		blockchain.get(blockchain.size() - 1).mining(difficulty);
	}
	public static Boolean verify() {
		block current; 
		block previous;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		for(int i=1; i < blockchain.size(); i++) {
			current = blockchain.get(i);
			previous = blockchain.get(i-1);
			
			if(!current.hash.equals(current.calculateHash()) ){			
				return false;
			}
			if(!previous.hash.equals(current.previousHash) ) {
				return false;
			}
			if(!current.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
	public static void display(){
		
		for(int i=0; i < blockchain.size(); i++) {
		 System.out.print("Block"+i);	
		 System.out.println("");
		 System.out.print(blockchain.get(i).hash);
		 System.out.print(" "+ blockchain.get(i).previousHash);
		 System.out.print(" "+ blockchain.get(i).get_data());
		 System.out.print(" "+ blockchain.get(i).get_timestamp());
		 System.out.print(" "+ blockchain.get(i).nonce());
		 System.out.println("");
		}
		
		
	}

}
