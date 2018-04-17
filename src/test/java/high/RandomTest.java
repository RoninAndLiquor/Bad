package high;

public class RandomTest {

	public static void main(String[] args) {
		int count = 100000000;
		int one = 0;int two = 0;int three =0; int four = 0;int five = 0;int six = 0;int seven = 0;int eight =0;int nine=0;int zero = 0;
		for(int i=0;i<count;i++){
			int temp = (int) (Math.random()*10);
			switch(temp){
				case 1:
					one++;
					break;
				case 2:
					two++;
					break;
				case 3:
					three++;
					break;
				case 4:
					four++;
					break;
				case 5:
					five++;
					break;
				case 6:
					six++;
					break;
				case 7:
					seven++;
					break;
				case 8:
					eight++;
					break;
				case 9:
					nine++;
					break;
				case 0:
					zero++;
					break;
			}
		}
		System.out.println("one="+(float)one/(float)count*100+"%");
		System.out.println("two="+(float)two/(float)count*100+"%");
		System.out.println("three="+(float)three/(float)count*100+"%");
		System.out.println("four="+(float)four/(float)count*100+"%");
		System.out.println("five="+(float)five/(float)count*100+"%");
		System.out.println("six="+(float)six/(float)count*100+"%");
		System.out.println("seven="+(float)seven/(float)count*100+"%");
		System.out.println("eight="+(float)eight/(float)count*100+"%");
		System.out.println("nine="+(float)nine/(float)count*100+"%");
		System.out.println("zero="+(float)zero/(float)count*100+"%");
		
	}
	
}
