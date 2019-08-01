import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class HashTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		HashMap HM=new HashMap();
		Random r = new Random();
		int choise,kn=0;
		int as=0,ar=0;
		boolean open=true;
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		while(open){
			System.out.println("Choose 1:\n1:Insert a number of new keys!\n2:Search a spesific key!\n3:Delete a spesific key!\n4:Terminate!\n");
			do{
				choise=keyboard.nextInt();
				if(choise<0 || choise>4){
					System.out.println("Wrong choise pick 1 out of the 4 above!\n");
				}
			}while(choise<0 || choise>4);
			if(choise==1){
				System.out.println("Give the numbers of keys u wanna insert:\n");
				kn=keyboard.nextInt();
				for(int i=0;i<kn;i++){
					HM.put(i, r.nextInt()% 10000);
				}
				HM.ToFile();
			}
			else if(choise==2){
				System.out.println("Give the key you want to find and print:\n");
				kn=keyboard.nextInt();
				as=+HM.search(kn);
			}
			else if(choise==3){
				ar=HM.remove();
			}
			else{
				System.out.println("Number of times that accessed the drive during search:"+as);
				System.out.println("Number of times that accessed the drive during remove:"+ar);
				open=false;
			}
		}
	}

}
