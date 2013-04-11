
public class Player extends Monster{

	public static final char PLAYERICON='@';
	
	public Player() {
		name = null;
		enemyMonsters=null;
		setIcon(PLAYERICON);
	}
	
	public Player(String name) {
		this.name = name;
		enemyMonsters=null;
		setIcon(PLAYERICON);
	}

	public String getInfoScreen() {		//basic info screen. (HP, etc.)
		String info="";
		if(name!=null)
			info+="Name: " + name+"\n";
		if(currentHp()>0)
			info+="Hitpoints: "+showHitPoints()+"\n"+"\n";
		return info;
	}
	
	//move functions either override monster moves or work the same way but are called by keyboard input
	
	public void endPlayerTurn(){
		
		Monster[] otherMonsters=currentLevel.levelMonsters;
		for(int i=0;i<otherMonsters.length;i++){
			if(otherMonsters[i]!=null
				&&otherMonsters[i]!=this){		//!= might not be a sophisticated enough operator here
				otherMonsters[i].turn();
			}	
		}
		
		RogueLikeGui.mapDisplayArea.setText(RogueLikeGui.d.getMap());		//should put this stuff in its own function somewhere
		RogueLikeGui.actionDisplayArea.setText(RogueLikeGui.currentMessage);
		RogueLikeGui.infoDisplayArea.setText(RogueLikeGui.player1.getInfoScreen());
	}
	
	
	public void moveTo(int xPos, int yPos){
		//TODO: need error handling to prevent moving onto solid objects, or out of the room.
		if(currentLevel.containsTile(xPos, yPos)){
			if(currentLevel.isPassable(xPos,yPos)){		//idea: some of the code in this if statement might belong better inside of the "setposition" method
				currentTile.clear();
				setPosition(xPos, yPos);
				currentTile.monster=this;
				
				if(currentTile.containsItems())
					pickUpAllTileItems();
				return;
				}
		else if(currentLevel.getTile(xPos, yPos).monster!=null)
			attack(currentLevel.getTile(xPos, yPos).monster);
		}
	}
	
	//public void die() TODO: override monster death with correct player death actions
}
