public class Room { 

  public Tile [][] tList;
  public int floor;
  public int coord1x;  //Bottom left corner of room (All rooms are going to be rectangular at this point)
  public int coord1y;
  public int coord2x; //Top right corner of room
  public int coord2y;  
  public int isConnected;
  
	//Constructor
  public Room(int x, int y, int z, int a, int b, Tile [][] layout) {
	floor = x;
	coord1x = y;
	coord1y = z;
	coord2x = a;
	coord2y = b;
	addTiles(layout);
	}
	
	//Add tiles to the rooms tile list
  public void addTiles(Tile [][] layout) {

   for (int i = coord1x; i < coord2x; i++) {
	for (int j = coord1y; j < coord2y; j++) {
	  layout[i][j].setIsRoom(true);
	  layout[i][j].setIcon('_');
	}
   }
}
}
