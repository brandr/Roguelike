
public class Player extends Monster{

	public static final char PLAYERICON='@';
	
	public Player() {
		name = null;
		setIcon(PLAYERICON);
	}
	
	public Player(String name) {
		this.name = name;
		setIcon(PLAYERICON);
	}

	public String getInfo() {
		String info="";
		if(name!=null)
			info+="Name: " + name+"\n";
		if(currentHp()>0)
			info+="Hitpoints: "+showHitPoints();
		return info;
	}
	
	//move functions either override monster moves or work the same way but are called by keyboard input
	

}
