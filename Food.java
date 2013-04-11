
public class Food extends Consumable{  //TODO: implement hunger. Consult Nick since this is a game design decision.
	public final static char STANDARDFOODICON='%';

	public Food(){
		name=null;
		consumable=true;
		setIcon(STANDARDFOODICON);
	}
	
	public Food(String name) {
		this.name=name;
		consumable=true;
		setIcon(STANDARDFOODICON);
	}
}
