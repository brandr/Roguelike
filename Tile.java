public class Tile {

    
	
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
		displayIcon();
		isPassable=true;
	}
	
	private void displayIcon() {
		if(tileItems.isEmpty())
			setIcon(Level.EMPTYTILEICON);
		else
			setIcon(tileItems.stackChar());
	}
	
	public void addItem(Item newItem){
		tileItems.addItem(newItem);
		displayIcon();
	}
	
	public void removeItem(Item removedItem) {
		tileItems.removeItem(removedItem);
		displayIcon();
		
	}
	
	public boolean containsItems() {
		return (!tileItems.isEmpty());
	}

	public char icon;
    public boolean isPassable=true;
	public boolean isVisible=true;
	public int floorTag;
	public int xCoord;
	public int yCoord;
	public boolean isRoom;
	
	public Monster monster=null;
	public ItemStack tileItems=new ItemStack();
	
}
