
public class Potion extends Consumable{
  public final static char STANDARDPOTIONICON='!';
	
	public Potion(){
		name=null;
		consumable=true;
		effect = new Effect();
		setIcon(STANDARDPOTIONICON);
	}
	
	public Potion(String name) {
		this.name=name;
		consumable=true;
		effect = new Effect();
		setIcon(STANDARDPOTIONICON);
	}
	
	public Potion(String name, int effectIndex, int value){
		this.name=name;
		consumable=true;
		effect=new Effect(effectIndex);
		effect.value=value;
		setIcon(STANDARDPOTIONICON);
	}

}
