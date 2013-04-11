import java.util.Random;	//is this import necessary?


public class Monster extends Entity{

	public final int MAXINVENTORYSIZE=30;	//maximum inventory size
	public final int INVENTORYSLOTS=6;		//number of places to wear equipment
	
	Random randGenerator=new Random();
	
	public Monster(){
		name = null;
	}
	
	public Monster(String name){
		this.name = name;
	}
	
	public Monster(String name, char icon){
		this.name = name;
		setIcon(icon);
	}

	//toString methods
	
	public String toString(){
		return name;
	}
	
	public String showHitPoints(){
		return hitPoints[0]+"/"+hitPoints[1];
	}
	
	public String showEquipment(){
		boolean noEquipment=true;
		String retVal="Equipped items:"+"\n"+"\n";
		
		for(int i=0;i<INVENTORYSLOTS;i++){
			if(equippedItems[i]!=null){		//if an item is equipped in the slot
				noEquipment=false;
				String slotName="";
				switch(i){					//maybe this switch statement should have its own method
				case(0):
					slotName="Head";
					break;
				case(1):
					slotName="Chest";
					break;
				case(2):
					slotName="LH";
					break;
				case(3):
					slotName="RH";
					break;
				case(4):
					slotName="Pants";
					break;
				case(5):
					slotName="Boots";
					break;
				default:
					break;
				}
				retVal+=(slotName+": "+equippedItems[i].equippedToString()+"\n");
			}
		}
		
		if(noEquipment){
			retVal+="none";
		}
		
		return retVal;
		
	}
	
	//basic AI
	
	public void turn(){	//monster decides what to do this turn
		detectNearestEnemy();
	}
	
	private void detectNearestEnemy() {	//detects the nearest hostile monster and moves towards it
		Monster nearestEnemy=determineClosest(allEnemiesOnLevel());
		moveTowards(nearestEnemy);
	}
	
	private Monster determineClosest(Monster[] availableMonsters){
		
		if(availableMonsters[0]==null)
			return null;
		Monster closestMonster=availableMonsters[0];
		int index=0;
		while(availableMonsters[index]!=null){
			if(distanceFromMonster(availableMonsters[index])<
				distanceFromMonster(closestMonster))
					closestMonster=availableMonsters[index];
			index++;
		}
		return closestMonster;
	}

	int distanceFromMonster(Monster closestMonster) {
		
		return Math.max(Math.abs(getXPos()-closestMonster.getXPos()),
				Math.abs(getYPos()-closestMonster.getYPos()));
	}

	public void detectAdjacentMonster(){	//see if there is a monster in adjacent squares
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if(!(i==0&&j==0)
				&&currentLevel.getTile(getXPos()+i,getYPos()+j).monster!=null){
					reactToAdjacentMonster(currentLevel.getTile(getXPos()+i,getYPos()+j).monster);
				}
			}
		}
	}
	
	private void reactToAdjacentMonster(Monster adjacentMonster) {		//determine reaction to an adjacent monster. Ideally much of this can be set by default (don't attack own species, etc.)
		if(hostileTowards(adjacentMonster)){
			attack(adjacentMonster);
		}
	}
	
	private boolean hostileTowards(Monster otherMonster) {	//determine if this monster is hostile towards another one
		for(int i=0;i<enemyMonsters.length;i++){
			if(enemyMonsters[i]!=null&&			//might need something more sophisticated than == to see if two monsters are the same monster
				enemyMonsters[i]==otherMonster)
				return true;
		}
		return false;
	}
	
	public void addEnemy(Monster newEnemy){	//add a monster to list of enemies
		int index=0;
		while(enemyMonsters[index]!=null)
			index++;
		enemyMonsters[index]=newEnemy;	
	}
	
	public Monster[] allEnemiesOnLevel(){ //returns all monsters on the current level;
		Monster[] enemiesOnLevel=new Monster[100];
		Monster[] allMonstersOnLevel=currentLevel.levelMonsters;
		int index=0;
		for(int i=0; i<allMonstersOnLevel.length;i++){
			if(allMonstersOnLevel[i]!=null&&hostileTowards((allMonstersOnLevel[i]))){
					enemiesOnLevel[index]=allMonstersOnLevel[i];
					index++;
			}
		}
		return enemiesOnLevel;
	}
	
	//movement methods		(consider giving some of these their own class)
	
	public void move (char direction){
		move(direction, 1);
	}
	
	public void move(char direction, int magnitude){
		relativeMove(Movement.numpadToX(direction)*magnitude,
				Movement.numpadToY(direction)*magnitude);
	}
	
	public void relativeMove(int relativeXPos, int relativeYPos){
		moveTo(getXPos()+relativeXPos, getYPos()+relativeYPos);
	}
	
	public void moveTo(int xPos, int yPos){		//since the player moveTo() overrides this one, it should include handling for monsters trying to move around obstacles.
		//TODO: need error handling to prevent moving onto solid objects, or out of the room.
		if(getXPos()==xPos&&getYPos()==yPos){	//if the monster is moving to its own location, this happens. (should be overriden for player using self-targeting actions. or maybe down?)
			return;
		}
		
		if(currentLevel.containsTile(xPos, yPos)){
			if(currentLevel.isPassable(xPos,yPos)){		//idea: some of the code in this if statement might belong better inside of the "setposition" method
				currentTile.clear();
				setPosition(xPos, yPos);
				currentTile.monster=this;
				return;
				}
			else if(currentLevel.getTile(xPos, yPos).monster!=null		//idea: keep some of this code for player, but prompt to decide whether or not to attack non-hostile monsters.
				&&hostileTowards(currentLevel.getTile(xPos, yPos).monster)){
					attack(currentLevel.getTile(xPos, yPos).monster);
					return;
			}
			else{	
				moveAround(Movement.determineDirection(getXPos(),getYPos(),xPos,yPos));
				return;
			}
		}	
	}
	
	private void moveAround(char direction) {//this is for when the monster's path is blocked by a nonhostile but impassable entity.
		Tile nearestOpen=Movement.nearestOpenTile(this, direction);
		moveTo(nearestOpen.xCoord,nearestOpen.yCoord);
	}
	
	private void moveTowards(Monster targetMonster) {
		char direction = determineMonsterDirection(targetMonster);
		move(direction);
	}

	private char determineMonsterDirection(Monster targetMonster) {	//figure out which direction another monster is in
		
		int targetX=targetMonster.getXPos();
		int targetY=targetMonster.getYPos();
		
		return Movement.determineDirection(this.getXPos(), this.getYPos(),targetX, targetY);
	}
	
	//damage-related functions
	
	public int determineDamage(){	//will be more complicated later
		int retVal = baseDamage;
		if(equippedItems[3]!=null){
			retVal+=equippedItems[3].getPower(1);
		}
		return retVal;
	}
	
	public void attack(Monster target){		//attacks a target
		int damage = determineDamage();
		appendToCurrentMessage(name+ " attacked "+target.name+"!");
		target.takeDamage(damage);
	}
	
	public void takeDamage(int damage){		//takes damage	
		for(int i = 0;i<INVENTORYSLOTS;i++){
			if(equippedItems[i]!=null){
			damage-=equippedItems[i].getPower(0);
			}
		}
		
		if(damage<0)		//if there is more than enough armor to mitigate the damage, no damage is dealt.
			damage = 0;
		
		hitPoints[0]-=damage;
		if(hitPoints[0]<=0){
			die();
		}
	}
	
	public void restoreHealth(int health){	//restores health
		hitPoints[0]+=health;
		if(hitPoints[0]>maxHitPoints()){
			fillHitPoints();
		}
	}
	
	public void die(){				//develop this more		(drop items, can no longer perform actions, object gets deleted, more specific message appears, etc.)	
		appendToCurrentMessage(name+" died.");		//TODO: make sure the monster can no longer attack or do anything.
		currentTile.addItem(new Food(name+" corpse"));	
		currentTile.clear();
		currentLevel.removeMonster(this);
			//TODO: figure out how monsters that are hostile towards another monster will handle its death.
			//idea: in the bit of code where this monster is discovered to have a null location, remove it from the list of hostiles.
			//alternate idea: expand the "removeMonster" function in currentLevel.
	}
	
	
	
	//hit point getters and setters	(Note: currently allows negative numbers. Change this if it causes problems.
	
	public int[] getHitPoints() {
		return hitPoints;
	}
	
	public int maxHitPoints() {
		return hitPoints[1];
	}
	
	
	public int currentHp(){
		return hitPoints[0];
	}
	
	public void setHitPoints(int hitPoints) {		//only meant for initially creating a monster with full health.
		this.hitPoints[0] = hitPoints;
		this.hitPoints[1] = hitPoints;
	}
	
	public void fillHitPoints(){					//restore to full HP
		hitPoints[0]=hitPoints[1];
	}
	
	public void setMaxHitPoints(int newMax){
		hitPoints[1]=newMax;
	}
	
	//damage getters and setters
	
	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
	//Item related functions
	
	public void pickUpItem(int index){
		if(!inventory.isFull()){
			if(getClass()==(Player.class))
				appendToCurrentMessage("Picked");
			else
				appendToCurrentMessage(name+" picked");
			appendToCurrentMessage(" up "+currentTile.tileItems.getItem(index)+".");
			obtainItem(currentTile.tileItems.takeItem(index));	
		}
	}
	
	public void pickUpAllTileItems(){
		int index=0;
		while(currentTile.tileItems.getItem(index)!=null && !inventory.isFull())
			pickUpItem(index);
	}
	
	public void	obtainItem(Item obtainedItem){	//TODO: should send a message(to the player only) if inventory is full.
		if(!inventory.isFull())
			inventory.addItem(obtainedItem);
	}
		
		public void useItem(int index){
			if(inventory.getItem(index)!=null){
				inventory.getItem(index).use(this);
				if(inventory.getItem(index).consumable==true){
					inventory.removeItem(index);
				}
			}
			
		}
		
		public void equip(Equipment item){				//TODO: make it so program can stop player from equipping non-equipment items.
			
			int index = item.wornIndex;
			
			if(item.equipped==false){						//case for equipping an item
			
				item.equip();
				
				if(equippedItems[index]==null)	//checks to see if an item is already worn there
					equippedItems[index]=item;
				else{											//if an item is already worn there, do this
					unequipItem(item.wornIndex);				//this step unequips the current item and equips the chosen item in its place.
					
					if(item.wornIndex==3 && item.twoHanded){	//if the item is a two-handed weapon, unequip the left-hand item.
						unequipItem(2);
					}
					
					else if(item.wornIndex==2	&&	equippedItems[3].twoHanded){	//if equipping a left-hand item while holding a two-handed weapon, unequip the weapon.
						unequipItem(2);
						equippedItems[2]=item;
						
						unequipItem(3);
					}
					
					equippedItems[index]=item;
				}
				
			}
			
			else{											//case for unequipping an item
				unequipItem(item.wornIndex);
			}
		}
		
		public void unequipItem(int index){		//unequip an item
			if(equippedItems[index]!=null){
				equippedItems[index].equip();		
				equippedItems[index]=null;
			}
		}
		
		//consumable methods
		
		public void consume(Consumable consumedItem){	//TODO: consumables can be either potions or food.
			
			if (consumedItem.getClass()==Potion.class)
				quaff((Potion)consumedItem);
			else if (consumedItem.getClass()==Food.class)
				eat((Food)consumedItem);
		}
		
		public void quaff(Potion quaffedPotion) {
			if(getClass()==(Player.class))
				setCurrentMessage("Quaffed ");
			else
				setCurrentMessage(name+" quaffed ");
			appendToCurrentMessage(quaffedPotion.name+".");
			quaffedPotion.use(this);
			inventory.removeItem((Item)quaffedPotion);
		}
		
		public void eat(Food eatenFood) {	//TODO: implement food
			if(getClass()==(Player.class))
				setCurrentMessage("Ate ");
			else
				setCurrentMessage(name+" ate ");
			appendToCurrentMessage(eatenFood.name+".");
			eatenFood.use(this);
			inventory.removeItem((Item)eatenFood);
		}
		
		//name getters and setters
		
		

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		protected void setCurrentMessage(String message) {
			RogueLikeGui.currentMessage=message;
		}
		
		private void appendToCurrentMessage(String message) {
			RogueLikeGui.currentMessage+=" "+message;
			
		}

	protected String name;
	protected int[] hitPoints={0,0};	//two-int array, with first as current and second and maximum
	protected int baseDamage=0;
	
	protected Inventory inventory = new Inventory();
	protected Equipment[] equippedItems = new Equipment[INVENTORYSLOTS];
	
	protected Monster[] enemyMonsters= new Monster[100];	//monsters that this monster will attack on sight.
	
}
