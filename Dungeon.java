public class Dungeon {

   public Level [] lvlList; //Char will need to be replaced by the tile object, but just for graphical purposes i will use String
   public int depth = 1;  //Dungeon Size(levels, level width, level depth)
   


   
   //Function to initialize the Map at a the most basic level
   public void setMap() {
     lvlList = new Level[depth]; //Using an arbitrary dungeon size (50X50X50)
   for (int i = 0; i < depth; i++) {
		lvlList[i] =  new Level(i);	
	 } 
   
   }
   
   public String getMap(){
	   String map = "";
	   for (int i = 0; i < depth; i++) {
		   	map+=lvlList[i].getLevel();
		   	map+=("\n\n");
			  }
	   return map;
   }
   
   //Print out map
   public void printMap() {
	   System.out.println(getMap());
   }
}
