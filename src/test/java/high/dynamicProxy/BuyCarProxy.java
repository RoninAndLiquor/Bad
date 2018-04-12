package high.dynamicProxy;

public class BuyCarProxy implements IBuyCar{

	Consumer consumer;
	
	public BuyCarProxy(Consumer consumer){
		this.consumer =  consumer;
	}
	
	@Override
	public void buyCar(int cash) {
		// TODO Auto-generated method stub
		consumer.buyCar(cash);
	}

}
