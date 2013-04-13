
public class Consumable extends Item{	//currently written assuming potions.

	
	
	public Consumable(){
		name = null;
		consumable=true;
		effect = new Effect();
		//setIcon(STANDARDCONSUMABLEICON);
	}
	
	public Consumable(String name){
		this.name=name;
		consumable=true;
		effect = new Effect();
		//setIcon(STANDARDCONSUMABLEICON);
	}
	
	public Consumable(String name, int effectIndex, int value){
		this.name=name;
		consumable=true;
		effect=new Effect(effectIndex);
		effect.value=value;
		//setIcon(STANDARDCONSUMABLEICON);
	}
	
	public String toString(){
		return name;
	}
	
	@Override
	public void use(Monster target) {
		effect.takeEffect(target);
	}

	protected Effect effect;	//both potions and food have effects.
	//public static boolean consumable=true;
}
