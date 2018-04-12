package high.dynamicProxy;

public class Consumer implements IBuyCar{

	int cash;
	
	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	@Override
	public void buyCar(int cash) {
		this.cash = cash;
		System.out.println("buyCar 买一辆车花费："+cash);
	}

}
