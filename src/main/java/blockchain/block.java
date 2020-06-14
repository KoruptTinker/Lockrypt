package main.java.blockchain;

import java.util.Date;

public class block {
		public String hash; 
	    public String previousHash; 
	    private String data; 
	    private long timeStamp; 
	    private int nonce;
	    public long get_timestamp() {
	    	return timeStamp;
	    	
	    }
	    public String get_data() {
	    	return data;
	    }
	    public int nonce() {
	    	return nonce;
	    }
	    public block(String data,String previousHash) 
	    { 
	        this.data = data; 
	        this.previousHash = previousHash; 
	        this.timeStamp= new Date().getTime(); 
	        this.hash = calculateHash(); 
	    } 
	    public String calculateHash() 
	    { 
	        String hash_value= Cryptography.SHA256( previousHash +Long.toString(timeStamp) +Integer.toString(nonce) + data ); 
	        return hash_value; 
	    } 
	    public void mining(int difficulty) {
			String target = new String(new char[difficulty]).replace('\0', '0'); 
			while(!hash.substring( 0, difficulty).equals(target)) {
				nonce ++;
				hash = calculateHash();
			}
			System.out.println("BLOCK MINED");
		}
}
