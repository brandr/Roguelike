import java.util.Random;

public class Level extends Dungeon {
	public Tile [][] layout;
	public int floor;
	public int size = 15; //For now will assume all dungeon levels are square, but a second size variable can be added if necessary
	public int roomcount;
	public Monster[] levelMonsters=new Monster[100];
	
	public final static char EMPTYTILEICON = '·';
	public final static char WALLICON = 'X';
	
	//Constructor
	public Level(int x) {
	floor = x;
	layout = new Tile[size][size];
	populate();
	roomify();
	}
	
	//Create tiles for a level
	public void populate() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			if(i==0||j==0||i==size-1||j==size-1)
				addWall(i,j);
			else
			layout[i][j] = new Tile (EMPTYTILEICON, true, true, floor, i,j);
			}
		}
	}
	
	//Creates rooms for a level
	public void roomify() {
	Random rng = new Random();
	int x1 = rng.nextInt(size);
	int y1 = rng.nextInt(size);
	int x2 = x1 + rng.nextInt(size-x1);
	int y2 = y1 - rng.nextInt(size-y1);
	Room r1 = new Room(floor, x1, y1, x2, y2, layout);
	
	}
	
	public String getLevel(){
		String level = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(layout[j][i].icon==EMPTYTILEICON)
					level+=""+layout[j][i].icon+"";
				else if(layout[j][i].icon==WALLICON)
					level+=""+layout[j][i].icon+"";
				else
					level+=layout[j][i].icon;
			}
			level+=("\n");
		}
		return level;
	}
	
	//Print function
	public void printLevel() {
	  	/*for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			layout[j][i].printTile();
			}
			System.out.println("");
		}*/
		System.out.println(getLevel());
	}

	public Tile getTile(int xPos, int yPos){
		return layout[xPos][yPos];
	}
	
	public boolean isPassable(int xPos, int yPos) {
		return (layout[xPos][yPos].isPassable);
	}

	public boolean containsTile(int xPos, int yPos) {
		return xPos>=0 && yPos>=0 && xPos<size && yPos < size;
	}
	
	//monster functions
	
	public Monster getMonster(int index){
		return levelMonsters[index];
	}
	
	public void addMonster(Monster newMonster){	//add a monster to list of enemies
		int index=0;							//TODO: this should be grouped with other functions so that a monster is always placed somewhere when added to the dungeon.
		while(levelMonsters[index]!=null)
			index++;
		levelMonsters[index]=newMonster;
	}

	public void removeMonster(Monster removedMonster) {	//this might be a good place to tell other monsters in the level to remove this monster from their hostile list.
		int index=0;
		while(levelMonsters[index]!=null){
			if(levelMonsters[index]==removedMonster){
				levelMonsters[index]=levelMonsters[index+1];
			while(levelMonsters[index+1]!=null){
				index++;
				levelMonsters[index]=levelMonsters[index+1];
				}
			}
			index++;
		}
		
	}
	
	//tile adding functions
	
	public void addFloorTile(int xPos, int yPos){
		if(containsTile(xPos,yPos))
			layout[xPos][yPos]=new Tile(EMPTYTILEICON,true,true,floor,xPos,yPos);
	}
	
	public void addWall(int xPos, int yPos){
		if(containsTile(xPos,yPos))
			layout[xPos][yPos]=new Tile(WALLICON,false,true,floor,xPos,yPos);
	}
	
} 
