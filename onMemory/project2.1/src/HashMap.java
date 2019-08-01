import java.util.Arrays;

public class HashMap {
      private final static int TABLE_SIZE = 100;
 
      HashEntry[] table;
      int VN=0;
      int ts=TABLE_SIZE;
      int NoCP=0,NoSP=0,NoRP=0;
      HashMap() {
            table = new HashEntry[TABLE_SIZE];
      }
  
      public void put(int key, int value) {
    	  int hash=key%ts;//to hash einai h 8esh sthn opoia 8a topo8ethsoume to neo antikhmeno HashEntry(key,value) sto table kai arxika orizete ws key%ts
    	  NoCP++;																												 // au3anoume ton metrith pou metra ton ari8mo sigkrisew kata thn eisagwgh stoixeiwn ston pinaka
    	  boolean check=true;
    	  while(check){//Oso to check einai true shmenei oti den exei bre8ei 8esh gia na baloume to neo HashEntry
    		  if((table[hash]!=null) && (table[hash] != DeletedEntry.getUniqueDeletedEntry())){//Ama sthn 8esh hash tou pinaka uparxei idi kapoio antikeimeno kai auto den einai
    			  hash = (hash + 1) % ts;                                                                                            //tupou DeletedEntry tote koitame sthn epomenh 8esh tou pinaka an einai kenh
    			  NoCP++;																												 // au3anoume ton metrith pou metra ton ari8mo sigkrisew kata thn eisagwgh stoixeiwn ston pinaka
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
      
      public void print(){//sunarthsh gia ektupwsh olwn twn stoixeiwn tou pinaka
    	  for(int i=0;i<ts;i++){
    		  if(table[i]!=null){
    			  System.out.println("hash="+i+" "+"value="+table[i].getValue()+" "+"Key="+table[i].getKey());
    		  }
    	  }
      }
      
      public void search(int key){
    	  int hash=key%ts;	//to hash einai h 8esh sthn opoia pi8anos na exoume  topo8ethsei arxika to antikhmeno HashEntry(key,value) sto table kai arxika orizete ws key%ts
    	  NoSP++; // au3anoume ton metrith pou metra ton ari8mo sigkrisew kata thn anazhthsh stoixeiwn ston pinaka
    	  int n=0,n2=0;
    	  while(n2<ts){//oso to n2 einai mikrotero apo to mege8os tou pinaka anazhtame sto table ta antikeimena me table[hash].key=key 
    		  if((table[hash]!=null) && (table[hash] != DeletedEntry.getUniqueDeletedEntry())){//Ama sthn 8esh hash tou pinaka uparxei idi kapoio antikeimeno kai auto den einai
    			  																																//tupou DeletedEntry tote elenxoume to periexomeno tou kai sthn sunexei proxorame sto epomeno stoixeio
    			  if((table[hash]!=null) &&table[hash].getKey()==key){//an to stoixeio einai auto pou yagnoume tote ektuponoume to periexomeno tou
    				  System.out.println("hash["+key+"]="+hash+" "+"value="+table[hash].getValue()+" "+"Key="+table[hash].getKey());
    				  n++;
    			  }
    			  hash = (hash + 1) % ts; //epomenh 8esh ston pinaka
    			  NoSP++; // au3anoume ton metrith pou metra ton ari8mo sigkrisew kata thn anazhthsh stoixeiwn ston pinaka
    		  }
    		  n2++;
    	  }
    	  if(n==0){
    		  System.out.println("Not found!");
    	  }
      }
      
      public void remove(int key){
    	  int hash=key%ts;//to hash einai h 8esh sthn opoia pi8anos na exoume  topo8ethsei arxika to antikhmeno HashEntry(key,value) sto table kai arxika orizete ws key%ts
    	  NoRP++;//au3anoume ton metrhth twn sugkrhsewn kata thn diagrafh 
    	  boolean check=true;
    	  while(check){//oso to chech einai true shmenei oti sunexeizoume thn anazhthsh sto table gia na diagrayoume stoixeia me table[hash].key=key 
    		  if((table[hash]!=null) && (table[hash] != DeletedEntry.getUniqueDeletedEntry())){ //Ama sthn 8esh hash tou pinaka uparxei idi kapoio antikeimeno kai auto den einai
					//tupou DeletedEntry tote elenxoume to periexomeno tou kai sthn sunexei proxorame sto epomeno stoixeio
    			  if((table[hash]!=null) &&table[hash].getKey()==key){//an to stoixeio einai auto pou yagnoume tote 
    				  table[hash] = DeletedEntry.getUniqueDeletedEntry();//antika8hstame to periexomeno tou me thn delete Entry
    			  }
    			  hash = (hash + 1) % ts; 
    			  NoRP++;//au3anoume ton metrhth twn sugkrhsewn kata thn diagrafh 
    		  }
    		  else{
    			  check=false;
    		  }
    	  }
      }
      public void compNum(){//sunarthsh pou emfanizei twn ari8mo twn sugkrisewn kata ton termatismo tou programmatos 
    	  System.out.println("Number of compares during inputting:"+NoCP);
    	  System.out.println("Number of compares during searching:"+NoSP);
    	  System.out.println("Number of compares during deleting:"+NoRP);
      }
}