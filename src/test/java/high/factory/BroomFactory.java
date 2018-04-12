package high.factory;

public class BroomFactory extends VehicleFactory {

	@Override
	public Movable create() {
		
		return new Broom();
	}

}
