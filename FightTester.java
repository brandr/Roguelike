/*

//TODO: current goal: pick up items to put them in inventory. Use consumables, like health potions.

//TODO category: basic functionality:

//TODO: allow items to sit on ground and be picked up
//TODO: implement item stacks (use boolean "stackable")
 	*display # of item only if stackable=true
 	*allow varying amounts of this item to be dropped

//TODO: decide where to display information like player equipment, strength, etc.
//TODO: allow the player to hold and drink potions which restore HP, increase max HP, etc.
//TODO: improve tile representation so all tiles: 1) have a fixed width 2) can fit any unicode character and 3) have something small (like a ' . ' ) when empty.
//TODO: implement basic experience/leveling.
//TODO: clean up code.
//TODO: make a nicer-looking GUI. (consider using netbeans, or just researching more layout commands.)
//TODO: try to allow the player to move without having to click the text box at the top.
//TODO: allow a stack of items to occupy a tile. (these would be data members belonging to a tile object.) allow players to pick up items.
//TODO: allow travel between floors.
//TODO: if Zach doesn't do it first, add walls and doors.
//TODO: implement "steps" (time). Consult Nick since he mentioned something about this.
//TODO: give monsters the ability to move and attack. Keep in mind that collision detection will get more complex.
//TODO: make a start screen. (Consult Nick)


//TODO category: Expanding Scope

//TODO: clean up movement to ensure that the right checks/actions occur in the right places.
//TODO: implement food.
//TODO: implement gold.
TODO: consider implementing item weight. 
//TODO: determine how gold/consumables/weapons/other items are organized.
//TODO: decide what happens what the player dies. (probably screen to restart/close).
//TODO: implement ranged weapons and ammo. (try to figure out why "throwing" applies to both bows and other objects.)
//TODO: create methods of internally organizing potions, monsters, tile representations etc. Consider .xml files or hashtables.
//TODO: create monster AI.
//TODO: determine how monsters are created, which monsters spawn in which areas, etc.
//TODO: give monsters inventories and determine system for dropping items.
//TODO: add/organize information about rooms, levels, tiles, dungeon etc. so that areas are more noticeably different from one another.
//TODO: refine equipment system as necessary.
//TODO: consider implementing a save feature.
//TODO: consider implementing spells and MP.
//TODO: elaborate on level-up system. Agree on details with Nick/Zach.
//TODO: implement character creation process and parameters. Consider applying these to monsters, as well.
//TODO: implement NPC interaction. (will have to change move->attack transition to check if the monster is hostile.)
	//*talking
	//*buying stuff
	//*deciding to attack (this will require variables to determine which creatures are hostile towards which other creatures. try to keep this simple.)
		//#consider adding a "hostileTowards" array of monster identifiers for each monster, which will grow and shrink. (monsters not on the list are attacked or not attacked based on default rules.)
//TODO: implement monster types. (human, orc, etc.)
	//*consider class types, too.
TODO: implement stats and stat organization. (HP, attack, speed, skills, magic, etc.)


//TODO category game design (as opposed to coding)

//TODO: try to find an easy way to allow Nick to add items without having to know code.
	//*easy way: give him a step-by-step instruction list for changes to make to github (probably .xml files once they're made) 
	//and a format for his update reports at the box at the bottom.
	//*hard way: create some kind of GUI with an editor that automatically generates compatible code when Nick fills out a set of fields for "add item/monster/etc." No idea how to do this.
consider different attack messages for different weapons/monsters/etc. (randomization?)
//TODO: planned monster types:
	//*human
	 	average stats
	//*elf
	//*orc
		kobold
		moblin
//TODO: planned items:
	*weapons
		#melee weapons
		 	%piercing
		 		-swords
		 		-spears
		 		-daggers
		 	%blunt
		 		-maces
		 		-clubs
		 		-staffs
		 	%other
		 		-axes (not sure if blunt or piercing)
		#ranged weapons
			-bow+arrows
			-crossbows+bolts
	*potions
	


*/
import java.util.Scanner;

public class FightTester {
  
	public static void main(String[] args){		//NOTE: this tester is outdated. use the main method of the RogueLikeGui class
												//unless you want to test more specific interactions here.
		
	
		
		//RogueLikeGui gameWindow = new RogueLikeGui("Roguelike");
		
		/*Scanner in = new Scanner(System.in);
	  	
		Player player1 = new Player("Link");
		Monster monster1 = new Monster("Moblin",'M');
		
		player1.setHitPoints(12);
		player1.setBaseDamage(1);
		monster1.setHitPoints(10);
	  	
		Consumable item1 = new Consumable("health potion",0,10);
		
		Armor helmet1 = new Armor("iron helm");
		helmet1.setArmorType(0);
		helmet1.setPower(1);
		
		Armor chest1 = new Armor("iron chestplate");
		chest1.setArmorType(1);
		chest1.setPower(2);
		
		Armor shield1 = new Armor("wooden shield");
		shield1.setArmorType(2);
		shield1.setPower(3);
		
		Weapon sword1 = new Weapon("hero's sword",2);
		
		Armor pants1 = new Armor("iron greaves");
		pants1.setArmorType(4);
		pants1.setPower(1);
		
		Armor boots1 = new Armor("iron boots");
		boots1.setArmorType(5);
		boots1.setPower(1);
		
		
		player1.inventory.addItem(item1);
		
		player1.setHitPoints(35);
		monster1.setHitPoints(30);
		
		player1.setBaseDamage(3);
		monster1.setBaseDamage(4);
		
		
		
		System.out.println(player1.getName()+"\n"+
				"HP:" + player1.showHitPoints()+"\n"+
				"Attack: "+player1.getBaseDamage());
		
		System.out.println();
		
		System.out.println(monster1.getName()+"\n"+
				 	"HP:" +monster1.showHitPoints()+"\n"+
				 	"Attack: "+monster1.getBaseDamage()+"\n");
		
		player1.equipItem(helmet1);
		//player1.equipItem(chest1);
		//player1.equipItem(shield1);
		player1.equipItem(sword1);
		//player1.equipItem(pants1);
		//player1.equipItem(boots1);
		
		System.out.println(player1.getName()+"'s "+ player1.showEquipment()+"\n");
		
		for(int i = 0; i<6;i++){
			
			System.out.println(player1.getName()+" attacks!");
			player1.attack(monster1);
			
			System.out.println(monster1.getName()+" attacks!"+"\n");
			monster1.attack(player1);
			
			System.out.println(player1.getName()+"'s HP: "+player1.showHitPoints());
			System.out.println(monster1.getName()+"'s HP: "+monster1.showHitPoints()+"\n");
			
			if(i==5){
				System.out.println(player1.getName()+" uses "+ item1.name+".");
				player1.useItem(0);
				System.out.println(player1.getName()+" gained " + item1.effect.value+ " HP and now has "+ 
						player1.showHitPoints()+" HP."+"\n");
				
			}
		}	
		player1.inventory.removeItem(0);*/
	}
}
