import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class HashMap {
      private final static int TABLE_SIZE = 100;
 
      HashEntry[] table;
      int VN=0;
      int ts=TABLE_SIZE;
      HashMap() {
            table = new HashEntry[TABLE_SIZE];
      }
  
      public void put(int key, int value) {
    	  int hash=key%ts;//to hash einai h 8esh sthn opoia 8a topo8ethsoume to neo antikhmeno HashEntry(key,value) sto table kai arxika orizete ws key%ts									
    	  boolean check=true;
    	  while(check){//Oso to check einai true shmenei oti den exei bre8ei 8esh gia na baloume to neo HashEntry
    		  if((table[hash]!=null) && (table[hash] != DeletedEntry.getUniqueDeletedEntry())){//Ama sthn 8esh hash tou pinaka uparxei idi kapoio antikeimeno kai auto den einai
    			  hash = (hash + 1) % ts;                                                                                            //tupou DeletedEntry tote koitame sthn epomenh 8esh tou pinaka an einai kenh
    			  if(VN%ts==0){//se periptosh pou o pinakas einai gematos kai diplasiazoume to mege8os tou kai ftiagnoume enan neo pinaka ston opoio pername ola ta uparxonta stoixeia
    				  ts=ts*2;		
    				  table=Arrays.copyOf(table, table.length*2);
    				  System.out.println("Size of table:"+ts);
    			  }
    		  }
    		  else{
    			  check=false;//brikame kenh 8esh ston pinaka (table[hash]==null) 
    		  }
    	  }
    	  table[hash]=new HashEntry(key,value);//eisagoume to antikeimeno ston pinaka sthn 8esh hash
    	  VN++;
      }
      public void ToFile() throws IOException{
  		System.out.println("Generating  File...");
  		long size=0;
  		long fullsize=0;
  		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("table"));
  		for(HashEntry En:table){ //Metaferoume sto arxeio Index oles tis Reference mia ka8e fora 
  			if(En!=null){
  				size=size+En.length();//Xrhsimopoihte gia to elegxo poswn object xwraei akoma sthn selida 
  				fullsize=fullsize+En.length();;
  				if(size<=128){//ama ta sunoliko mhkos twn dedomenwn ths selidas den 3epernaei ta 128 bytes me thn eisodo mia neas Reference tote sunexizoume sthn idia selida
  					out.writeObject(En);
  				}
  				else{//allios allazoume selida 
  					out.write('\n');//allagh selidas
  					out.writeObject(En);
  					size=En.length();
  				}
  			}
  		}
  	out.close();
    System.out.println("Table size: " +fullsize  + " bytes");         
  	}
      
      public int search(int key)throws IOException, ClassNotFoundException{
  		ObjectInputStream in=new ObjectInputStream(new FileInputStream("table"));
  		int k=0,n=0;
  		try{//Oso to arxeio exei periexomeno diabazei apo auto
  			while(true){
  				k++;//prosbash sto disko
  				in.read();//se periptosh pou uparxei to '\n' pou xrhsimopoihsa gia thn allagh selidas 
  				HashEntry hs = (HashEntry) in.readObject();	// diabazei ena object tupou HashEntry
  				if(hs.getKey()==key){//table[hash].getKey()==key
  					System.out.println("value="+hs.getValue()+" "+"Key="+hs.getKey());
  					n++;
  				}
  			}
  		}catch(EOFException e){
  			if(n==0){
  				System.out.println("Not found!");
  			}
  			System.out.println("Done");
  		}
  		in.close();
  		return k;
  	}
      public int remove() throws IOException{
    	  new FileOutputStream("table").close();
    	  System.out.println("Deleted!");
    	  return 1;
      }
}