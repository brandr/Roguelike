import java.util.Random;

public class Movement {  //contains many of the movement functions. TODO: transfer more from player/monster.

	static Random randGenerator=new Random();
	
	public static int numpadToX(char num){	//returns the x direction of each keypad input. default is zero because a non-directional key doesn't allow movement.
		switch(num){
		case('1'):
			return -1;
		case('2'):
			return 0;
		case('3'):
			return 1;
		case('4'):
			return -1;
		case('6'):
			return 1;
		case('7'):
			return -1;
		case('8'):
			return 0;
		case('9'):
			return 1;
		default:
			return 0;
		}
	}
	
	public static int numpadToY(char num){	//returns the y direction of each keypad input.  
		switch(num){
		case('1'):
			return 1;
		case('2'):
			return 1;
		case('3'):
			return 1;
		case('4'):
			return 0;
		case('6'):
			return 0;
		case('7'):
			return -1;
		case('8'):
			return -1;
		case('9'):
			return -1;
		default:
			return 0;
		}
	}
	
	public static char leftOf(char direction){	//determine the direction to the left of this one
		switch(direction){					//TODO: figure out a better way to handle directions than these commands
		case '1':
			return '2';
		case '2':
			return'3';
		case '3':
			return '6';
		case '4':
			return '1';
		case '6':
			return '9';
		case '7':
			return '4';
		case '8':
			return '7';
		case '9':
			return '8';
		default:		
			return '0';
		}
	}
	
	public static char rightOf(char direction){		//determine the direction to the right of this one
		switch(direction){
		case '1':
			return '4';
		case '2':
			return'1';
		case '3':
			return '2';
		case '4':
			return '7';
		case '6':
			return '3';
		case '7':
			return '8';
		case '8':
			return '9';
		case '9':
			return '6';
		default:		
			return '0';
		}
	}
	
	public static char determineDirection(int x1, int y1, int x2, int y2){		//figure out which direction something at x1, y1 is from something at x2, y2
		if(y1==y2){	//4 or 6
			if(x1>x2)		
				return '4';		//left
			if(x1<x2)
				return '6';		//right
		}
		else if(x1==x2){//8 or 2
			if(y1>y2)
				return '8';		//up
			else if(y1<y2)
				return '2';		//down
		}
		else if(x1>x2){
			if(y1>y2)
				return '7';		//up-left
			else
				return '1';		//down-left
		}
		else{
			if(y1>y2)
				return '9';		//up-right
			else
				return '3';		//down-right
		}
		return '5';		//only remaining possibility: monster is in same square as target
	}
	
	public static Tile nearestOpenTile(Monster movingMonster, char direction){
		
		char left=direction;
		char right=direction;
		while(!canMove(movingMonster, direction)){
			switch(randGenerator.nextInt(2)){		//at random, choose one of two actions:
			case(0):		//action 1: try moving left. otherwise, move right.
				left=leftOf(left);
				if(canMove(movingMonster, left))
					return tileInDirection(movingMonster, left);
				else{
					right=rightOf(right);
					if(canMove(movingMonster, right))
						return tileInDirection(movingMonster, right);
				}
			case(1):
				right=rightOf(right);
				if(canMove(movingMonster, right))
					return tileInDirection(movingMonster, right);
				else{
					left=leftOf(left);
					if(canMove(movingMonster, left))
						return tileInDirection(movingMonster, left);
				}
				if(left==direction||right==direction)	//this indicates a full cirlce, meaning the monster is surrounded and cannot move.
					return movingMonster.currentTile;
			}
		}
		return tileInDirection(movingMonster, direction);
	}
	
	public static boolean canMove(Monster movingMonster, char direction){
		return tileInDirection(movingMonster,direction).isPassable;
	}

	private static Tile tileInDirection(Monster movingMonster, char direction) {
		int tileX=movingMonster.getXPos()+numpadToX(direction);
		int tileY=movingMonster.getYPos()+numpadToY(direction);
		return movingMonster.currentLevel.getTile(tileX, tileY);
	}
}
