public class Tile {

    public char icon;
    public boolean isPassable=true;
	public boolean isVisible=true;
	public int floorTag;
	public int xCoord;
	public int yCoord;
	public boolean isRoom;
	
	public Monster monster=null;
	
	//Constructor
	public Tile(char c, boolean p, boolean v, int x, int y, int z) {
	icon = c;
	isPassable = p;
	isVisible = v;
	floorTag = x;
	xCoord = y;
	yCoord = z;
	}
	 
	  //Set isRoom variable 
	public void setIsRoom(boolean b) {
	isRoom = b;
	}	
	 //Constructor
	public Tile() {
	icon = 'Z';
	}
      //Set icon for a tile
	public void setIcon(char c) {
	icon = c;
	}
	
	//Print function
	public void printTile() {
	System.out.print(icon);
	}

	public void clear() {
		monster=null;
		icon=Level.EMPTYTILEICON;
		isPassable=true;
	}
	
}
