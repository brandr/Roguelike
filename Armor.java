
public class Armor extends Equipment{

	public static final char STANDARDARMORICON='[';
	
	public Armor(){
		name=null;
		setStatBoosts();
		wornIndex=-1;
		setIcon(STANDARDARMORICON);
	}
	
	public Armor(String name){
		this.name=name;
		setStatBoosts();
		wornIndex=-1;
		setIcon(STANDARDARMORICON);
	}
	
	public void setArmorType(int index){	//sets where the armor is worn
		wornIndex=index;
	}
	
	public void setPower(int power){	//sets how well the armor protects
		statBoosts[0]=power;
	}	
}
