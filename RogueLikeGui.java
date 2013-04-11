
//idea: add separate actionListeners for each screen
//TODO: make it so you can't quaff food or eat potions.
//package events;
 
/*
* KeyEventDemo
*/
 
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
 
public class RogueLikeGui extends JFrame
     
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//JTextField typingArea;
	static JTextArea[][] screenList = new JTextArea[20][20];
	
	MainScreenListener mainScreenL = new MainScreenListener();
	InventoryScreenListener inventoryScreenL = new InventoryScreenListener();
	ItemSelectListener itemSelectL = new ItemSelectListener();
	
	String itemType="Item";		//this will be used with the item select listener to determine what type of item is valid to select.

	int currentScreenIndex=1; //0 will be title screen later. 1: normal game screen 2: inventory screen etc.
	
	static JTextArea infoDisplayArea;
	static JTextArea mapDisplayArea;
	static JTextArea actionDisplayArea;
	
	int infoDisplayWidth = 200;
	int infoDisplayHeight = 40;
	
	int mapDisplayWidth=320;
	int mapDisplayHeight=320;
	
	int actionDisplayWidth = 200;	//do this for other window sizes to keep them consistent
	int actionDisplayHeight = 40;
   
	JScrollPane scrollPane;
    //displays for index 2
	
	static JTextArea inventoryDisplayArea;
    
    static final String newline = System.getProperty("line.separator");
    
    static Dungeon d = new Dungeon();
	static Player player1 = new Player("Link");
	static Monster monster1 = new Monster("Moblin",'M');
	static Monster monster2 = new Monster("Deku Scrub",'D');
	
	Weapon sword1 = new Weapon("hero's sword",1);
	Armor shield1 = new Armor("wooden shield");
	Armor chest1 = new Armor("iron chestplate");
	Armor boots1 = new Armor("leather boots");
	
	Potion potion1=new Potion("health potion",0,8);
	
	public static String currentMessage = "";
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
    	
    	//mainScreenLister=new KeyListener();
    	
        //Create and set up the window.
        RogueLikeGui frame = new RogueLikeGui("Roguelike");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Set up the content pane.
        frame.addComponentsToPane();
         
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        
        screenList[1][0]=infoDisplayArea;
    	screenList[1][1]=mapDisplayArea;
    	screenList[1][2]=actionDisplayArea;
    	
    	screenList[2][0]=inventoryDisplayArea;
    	
    	//inventoryDisplayArea.setText("");
    	
        d.setMap();
        frame.startMap();
        
    }
     
    private void startMap() {
    	
    	shield1.setArmorType(2);
		shield1.setPower(1);
		
		chest1.setArmorType(1);
		chest1.setPower(2);
		
		boots1.setArmorType(5);
		boots1.setPower(1);
		
    	player1.setHitPoints(12);
		player1.setBaseDamage(1);
		
		player1.obtainItem(sword1);
		player1.obtainItem(shield1);
		player1.obtainItem(chest1);
		
		monster1.setHitPoints(10);
		monster1.setBaseDamage(4);
		
		monster1.setCurrentLevel(d.lvlList[0]);
		monster1.setPosition(2,2);
		monster1.currentTile.monster=monster1;
		
		monster2.setCurrentLevel(d.lvlList[0]);
		monster2.setPosition(2,8);
		monster2.currentTile.monster=monster2;
		
		monster2.setHitPoints(6);
		monster2.setBaseDamage(2);
		
		player1.setCurrentLevel(d.lvlList[0]);
		player1.setPosition(6,6);
		player1.currentTile.monster=player1;
		
		d.lvlList[0].addMonster(player1);		//TODO: this step should automatically happen upon a player's arrival in a level,
		d.lvlList[0].addMonster(monster1);		//or a monster's creation.
		d.lvlList[0].addMonster(monster2);
		
		monster1.addEnemy(player1);				//TODO: monsters should aggro on player automatically, unless specified otherwise.
		monster2.addEnemy(player1);
		
		//monster1.addEnemy(monster2);	//uncomment this to make monsters attack each other.
		//monster2.addEnemy(monster1);
		
		d.lvlList[0].layout[8][8].addItem(potion1);
		d.lvlList[0].layout[8][10].addItem(boots1);
		
		mapDisplayArea.setText(d.getMap());
		infoDisplayArea.setText(player1.getInfoScreen());
	}

private void addComponentsToPane() {       
        
        //screen 0
        
        infoDisplayArea = new JTextArea();
        infoDisplayArea.setEditable(false);
        
        mapDisplayArea = new JTextArea();
        mapDisplayArea.setEditable(false);
        
        actionDisplayArea = new JTextArea();
        actionDisplayArea.setEditable(false);
        
        mapDisplayArea.addKeyListener(mainScreenL);
        mapDisplayArea.requestFocus();
        
        
        
        //screen 1
        
       inventoryDisplayArea = new JTextArea();
       inventoryDisplayArea.setEditable(false);
       
       inventoryDisplayArea.addKeyListener(inventoryScreenL);
        
       JScrollPane scrollPane = new JScrollPane(mapDisplayArea);
      // scrollPane.setPreferredSize(new Dimension(mapDisplayWidth,mapDisplayHeight));
       mapDisplayArea.setPreferredSize(new Dimension(mapDisplayWidth,mapDisplayHeight));
       mapDisplayArea.setFont(new Font("Courier New", Font.PLAIN, 16));
       infoDisplayArea.setPreferredSize(new Dimension(infoDisplayWidth,infoDisplayHeight));
       actionDisplayArea.setPreferredSize(new Dimension(actionDisplayWidth,actionDisplayHeight));
        
        getContentPane().add(infoDisplayArea,BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(actionDisplayArea, BorderLayout.PAGE_END);
  }
     
    public RogueLikeGui(String name) {
        super(name);
    }
    
    /*private void setScreen(int screenIndex) {	//try to figure out if I need this before deleting it
		if(screenIndex>=0&&screenIndex<screenList.length&&currentScreenIndex!=screenIndex){
			closeScreen(currentScreenIndex);
			openScreen(screenIndex);
			
			currentScreenIndex=screenIndex;
		}
	}*/
	
    private static void refreshScreen(){		//refreshes main screen display
    	mapDisplayArea.setText(d.getMap());
        actionDisplayArea.setText(currentMessage);
        infoDisplayArea.setText(player1.getInfoScreen());
    }
    
	private void openScreen(int screenIndex) {
		for(int i=0;i<screenList[screenIndex].length-1;i++){
			if(screenList[screenIndex][i]!=null){
				
				if(screenIndex==1){
					
					refreshScreen();
		            
		            mapDisplayArea.setVisible(true);
		            actionDisplayArea.setVisible(true);
		            infoDisplayArea.setVisible(true);
		            
		            scrollPane = new JScrollPane(mapDisplayArea);
		            
		            getContentPane().add(infoDisplayArea,BorderLayout.PAGE_START);
		            getContentPane().add(scrollPane, BorderLayout.CENTER);
		            getContentPane().add(actionDisplayArea, BorderLayout.PAGE_END);
		            mapDisplayArea.requestFocus();
					}
			}
		}
	}
	
	/*private void closeScreen(int screenIndex) {	//keep this in case I need it at some point
		for(int i=0;i<screenList[screenIndex].length-1;i++){
			if(screenList[screenIndex][i]!=null)
				screenList[screenIndex][i].setVisible(false);
			 	
		}
	}*/
	
	private void closeScreen() {
		for(int i=0;i<screenList[currentScreenIndex].length-1;i++){
			if(screenList[currentScreenIndex][i]!=null){
				getContentPane().removeAll();
				screenList[currentScreenIndex][i].setVisible(false);
			}
		}
	} 
       

private class MainScreenListener implements KeyListener{

		public MainScreenListener() {
		
		}

		@Override
		public void keyPressed(KeyEvent e) {
			mainKeyPress(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			mainKeyPress(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			mainKeyPress(e);
		}
		
		private void mainKeyPress(KeyEvent e){
			
			currentMessage="";
			if(currentScreenIndex==1){
			int id = e.getID();
			
			if(id==KeyEvent.KEY_RELEASED){
				int keyCode=e.getKeyCode();
				switch (keyCode){
				case(KeyEvent.VK_UP):
					player1.move('8');
					player1.endPlayerTurn();
					return;
				case(KeyEvent.VK_DOWN):
					player1.move('2');
        			player1.endPlayerTurn();
        			return;
				case(KeyEvent.VK_LEFT):
        			player1.move('4');
        			player1.endPlayerTurn();
        			return;
				case(KeyEvent.VK_RIGHT):
					player1.move('6');
        			player1.endPlayerTurn();
        			return;
				}
			}
        	
	        if (id == KeyEvent.KEY_TYPED) {	//the second condition is specific to the main screen.
	            char c = e.getKeyChar();
	            if(Character.isDigit(c)){
	            	
	            	player1.move(c);
	            	player1.endPlayerTurn();
	            }
	            else //if(Character.isLetter(c))
	            	playerCommand(c);
	            refreshScreen();
	        }
		}
	}
		
		public void playerCommand(char keyPressed){	//there will eventually be many player commands. consider moving them to another class, and simply calling them with this method.
			
			switch(keyPressed){		//a lot of this is likely to be repeated for other screens
				
			
			case('i'):		//open inventory
	    			currentScreenIndex=2;
	    			closeScreen();
	    			getContentPane().removeAll();	//TODO: store this in other methods to avoid repetiton.
	    			getContentPane().add(inventoryDisplayArea,BorderLayout.PAGE_START);
	    			inventoryDisplayArea.setVisible(true);		
	    			inventoryDisplayArea.setPreferredSize(new Dimension(300,600));
	    			inventoryDisplayArea.requestFocus();
	    			inventoryDisplayArea.setText("Inventory: " + "\n" + "\n" +
		    				player1.inventory.toString()+ "\n"+"\n"
		    				+player1.showEquipment());
	    			break;
	    		
				case('e'):		//eat a food item
					currentMessage= ("Eat what?");
					itemType="Food";
					selectItem();
					break;
	    			
	    		case('q'):		//quaff a potion
	    			currentMessage= ("Drink which potion?");
    				itemType="Potion";
    				selectItem();
	    			break;
	    		case('E'):		//Equip an item
	    			currentMessage= ("Equip or unequip which item?");
	    			itemType="Equipment";
	    			selectItem();
	    			break;
	    		default:
	    			break;
	    		}
		}
		
		public void selectItem(){		//from the main screen, select an item to do something with.
			mapDisplayArea.removeKeyListener(this);
			mapDisplayArea.addKeyListener(itemSelectL);
		}
	}
//consider putting these action listeners in separate files to avoid a super long file
private class InventoryScreenListener implements KeyListener{		//button commands on inventory screen
	
	@Override
	public void keyPressed(KeyEvent e) {		//TODO: find a way to avoid this sort of repitition
		inventoryKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inventoryKeyPress(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		inventoryKeyPress(e);		
	}

	private void inventoryKeyPress(KeyEvent e) {	//control scheme on inventory screen
		if(currentScreenIndex==2){	//might not need this check. can instead use "removeKeyListener" in appropriate places.
			int id = e.getID();
			if(id==KeyEvent.KEY_RELEASED){
				int keyCode=e.getKeyCode();
				
				switch(keyCode){
				case(KeyEvent.VK_ESCAPE):
					inventoryCommand("escape");
					break;
				default:
					break;
				}
			}
			
			if (id == KeyEvent.KEY_TYPED) {	//TODO: implement more inventory commands
				
				char c = e.getKeyChar();
				if(Character.isLetter(c))
					inventoryCommand(""+c);
				//else //if(Character.isNumber(c))
      
            inventoryDisplayArea.setText(("Inventory: " + "\n" + "\n" +	//this section of code is repeated. should try to store in somewhere, preferrably with other screen text)
    				player1.inventory.toString()+ "\n"+"\n"
    				+player1.showEquipment()));
        	}
		}
		
	}

	private void inventoryCommand(String command) {
		switch(command){
		case("escape"):			//this is the "close to main screen" command, which will probably be on every screen and therefore should be placed elsewhere.
			currentScreenIndex = 1;
			closeScreen();
			openScreen(1);
			break;
		default:
			break;
		}
	}	
}

private class ItemSelectListener implements KeyListener{
	@Override
	public void keyPressed(KeyEvent e) {		//TODO: find a way to avoid this sort of repetition
		itemKeyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		itemKeyPress(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		itemKeyPress(e);		
	}
	
	private void itemKeyPress(KeyEvent e) {	//control scheme for equipping items
		if(currentScreenIndex==1){
			int id = e.getID();
			
			if (id == KeyEvent.KEY_TYPED) {	
				char c = e.getKeyChar();
				int itemIndex=Item.getNumForCharacter(c);
				switch(itemType){
				case("Item"):		//NOTE: as more actions are added, there may be multiple things to do with a single item type. I will either need to add more possibilities for this string, or make getters which return different item types (other methods dictate what actions are taken from there.)
					break;
				case("Food"):
					eatItem(itemIndex);
					break;
				case("Potion"):
					quaffItem(itemIndex);
					break;
				case("Equipment"):
					equipItem(itemIndex);
					break;
				default:
					break;
				}
				
        	}
		}
	}
	
	private void eatItem(int itemIndex) {	//TODO: figure out how to keep this as one class while still including consumable food.
		// TODO: look for commonalities between consumeItem() and equipItem() so that some actions can be combined into one class.
		if(playerHasItem(itemIndex)){	
			if((player1.inventory.getItem(itemIndex)).getClass()==Food.class){
				Food consumingItem=(Food) player1.inventory.getItem(itemIndex);
				player1.eat(consumingItem);
				player1.endPlayerTurn();	//only end turn if an item was consumed.
			}
			else
				currentMessage="You can't eat that "+player1.inventory.getItem(itemIndex)+".";
		}
		returnToMainListener();
	}

	private void quaffItem(int itemIndex) {	//TODO: figure out how to keep this as one class while still including consumable food.
										// TODO: look for commonalities between consumeItem() and equipItem() so that some actions can be combined into one class.
		if(playerHasItem(itemIndex)){	
				if((player1.inventory.getItem(itemIndex)).getClass()==Potion.class){
				Potion quaffingPotion=(Potion) player1.inventory.getItem(itemIndex);
				player1.quaff(quaffingPotion);
				player1.endPlayerTurn();	//only end turn if an item was consumed.
				}
				else
					currentMessage="You can't drink that "+player1.inventory.getItem(itemIndex)+".";
			}
			returnToMainListener();
	}

	private void equipItem(int itemIndex) {
		
		//currentMessage = "";
		
		if(playerHasItem(itemIndex)){		
			if((player1.inventory.getItem(itemIndex)).equippable){
				Equipment equippingItem=(Equipment) player1.inventory.getItem(itemIndex);
				if((equippingItem).equipped)
					currentMessage="Unequipped ";
				else
					currentMessage="Equipped ";
			player1.equip(equippingItem);
			currentMessage+= equippingItem+".";
			player1.endPlayerTurn();	//only end turn if an item was equipped.
			}
			else
				currentMessage="You can't equip that "+player1.inventory.getItem(itemIndex)+".";
		}
		returnToMainListener();
	}
	
	private boolean playerHasItem(int itemIndex){
		return (itemIndex!=-1 
				&& itemIndex<player1.inventory.getMaxItems()
				&& player1.inventory.getItem(itemIndex)!=null);
	}
	
	private void returnToMainListener(){
		refreshScreen();
        mapDisplayArea.removeKeyListener(this);
        mapDisplayArea.addKeyListener(mainScreenL);	
	}
	
}
}







