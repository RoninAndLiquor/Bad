package high.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import high.annotation.FruitColor;
import high.annotation.FruitName;
import high.annotation.FruitColor.Color;

@Component
public class Fruit {

	@FruitName(value="Apple")
	private String name;
	
	@FruitColor(fruitColor=Color.BLUE)
	private String color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
			
}
