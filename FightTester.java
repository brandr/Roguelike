import java.util.Scanner;

public class FightTester {
  
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		//Create Dungeon and Print Dungeon
	  	Dungeon d = new Dungeon();
	  	d.setMap();
	  	
		Player player1 = new Player("Link");
		Monster monster1 = new Monster("Moblin",'M');
		
		monster1.setCurrentLevel(d.lvlList[0]);
		monster1.setPosition(2,2);
		
		player1.setCurrentLevel(d.lvlList[0]);
		player1.setPosition(4,4);
		
	  	d.printMap();
	  	
	  	for(int i=0;i<100;i++){
	  		char direction=in.next().charAt(0);
	  		player1.move(direction);
	  		d.printMap();
	  	}
	  	
	  	
	  	player1.move('6',-2);
	  	
	  	d.printMap();
		
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
		player1.inventory.removeItem(0);
	}
}
