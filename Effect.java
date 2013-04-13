
public class Effect {		//effects that happen to players
							//TODO: implement food effects
	public Effect(){
		name=null;
		value=0;
		duration=0;
		effectIndex=-1;
	}
	
	public Effect(int index){
		effectIndex=index;
		setName(index);
	}
	
	private void setName(int index) {
		switch(index){
		case 0:
			name="heal or harm";	//regen or damage over time still fall into this category.		
			break;					//TODO: should healing and harming be separate? harming potions should ignore armor.
		case 1:
			name="instant kill";
			break;
		case 2:
			name="adjust max HP";
			break;
		default:
			name=null;
			break;
		}
		
	}

	public void takeEffect(Monster target){
		switch(effectIndex){
		case 0:
			target.restoreHealth(value);
			break;
		case 1:
			target.die();
			break;
		case 2:
			target.setMaxHitPoints(target.maxHitPoints()+value);
			break;
		default:
			break;
		}
	}
	
	//effect types: healing, harming, boosting/lowering stats like attack, armor or speed, insta-killing, teleporting, etc
	protected String name;
	protected int value=0;	//amount healed, increase in speed, etc.
	protected int duration=0; //zero is for instantaneous
	protected int effectIndex;
	
}
