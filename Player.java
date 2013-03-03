
public class Player extends Monster{

  public Player() {
		name = null;
		hitPoints[0]=0;
		hitPoints[1]=0;
		baseDamage = 0;
	}
	
	public Player(String name) {
		this.name = name;
		hitPoints[0]=0;
		hitPoints[1]=0;
		baseDamage = 0;
	}
	
	//move functions either override monster moves or work the same way but are called by keyboard input
	

}
