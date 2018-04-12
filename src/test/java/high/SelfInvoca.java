package high;

public class SelfInvoca {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		System.out.println(Integer.MAX_VALUE);
		System.out.println(foo(47));
		System.out.println("用时："+(System.currentTimeMillis()-startTime));
	}
	
	public static long foo(int i){  
        if(i<=0)  
            return 0;  
        else if(i>0 && i<=2)  
            return 1;  
        return foo(i-1) + foo(i-2);  
    }  
	
}
