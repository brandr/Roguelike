
//TODO: make a standard empty equipment item. This will help make other methods shorter.

public class Equipment extends Item{

  public Equipment(){
		name=null;
		setStatBoosts();
		wornIndex=-1;		//placeholder value that will be used in error cases.
	}
	
	public String toString(){
		return name;
	}
	
	@Override
	public void use(Monster target) {		//this is only for equipment with special effects.
		
	}
	
	public void setStatBoosts(){
		int[]tempStatBoosts={0,0};
		statBoosts=tempStatBoosts;
	}
	
	public void equip(){				//understood to mean "equip or unequip"
		equipped=!equipped;
	}
	
	public boolean equipped=false;
	protected int[] statBoosts;		//strength values (attack power, armor, etc.)
	//index 0: armor	index 1: attack		(add further indices)
	
	protected int wornIndex; //a number indicating which body part the equipment is worn on
	
	//0: head	1: chest	2: left hand (shield)	3:right hand (weapon)	4:pants		5: boots
	//a 2-handed weapon is technically held in the right hand, but forces the left hand to be empty.

	public boolean twoHanded = false;

	public int getPower(int index) {
		return statBoosts[index];
	}
}
