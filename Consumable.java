
public class Consumable extends Item{

  public Consumable(){
		consumable=true;
		
		name = null;
		effect = new Effect();
	}
	
	public Consumable(String name){
		consumable=true;
		
		this.name=name;
		effect = new Effect();
	}
	
	public Consumable(String name, int effectIndex, int value){
		consumable=true;
		
		this.name=name;
		effect=new Effect(effectIndex);
		effect.value=value;
	}
	
	@Override
	public void use(Monster target) {
		effect.takeEffect(target);
		
	}

	protected Effect effect;
	//public static boolean consumable=true;
}
