public class level extends dungeon {
  public tile [][] layout;
	public int floor;
	public int size = 50; //For now will assume all dungeon levels are square, but a second size variable can be added if necessary
	public int roomcount;
	
	//Constructor
	public level(int x) {
	floor = x;
	layout = new tile[size][size];
	populate();
	roomify();
	}
	
	//Create tiles for a level
	public void populate() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			layout[i][j] = new tile ('X', true, true, floor, i, j);
			//System.out.println(layout[i][j].icon);
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
	room r1 = new room(floor, x1, y1, x2, y2, layout);
	
	}
	
	//Print function
	public void printLevel() {
	  	for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
			layout[i][j].printTile();
			}
			System.out.println("");
		}
	}
	
} 
