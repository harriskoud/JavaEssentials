package pizzaPlace;

import pizzaPlace.NyPizza.Size;
import pizzaPlace.Pizza.Topping;
import pizzaPlace.Pizza;

public class CreatePizzaOrder {
	
	
	public static void main(String Args[]) {
		
		NyPizza pizza = new NyPizza.Builder(Size.SMALL).addTopping(Topping.SAUSAGE).addTopping(Topping.ONIN).build();
		
		
		System.out.println("Pizza Ready");
		System.out.println(pizza.toString());
		
		
	}

}
