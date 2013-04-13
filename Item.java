
public abstract class Item {		//other classes should interface, not inherit (since effects/functionality may differ greatly)

	public final static int MAXSTACKSIZE=99;
	
	
public Item(){
	this.name=null;
}

public Item(String name){
	this.name=name;
}
	
public abstract void use(Monster target);

public String toString(){		//I forget why I commented this out at one point. I think it's because the function might be overridden or something.
	return name;
}

public int getStackSize() {
	return stackSize;
}

public void setStackSize(int stackSize) {
	if(stackSize>0&&stackSize<=MAXSTACKSIZE)
	this.stackSize = stackSize;
}

public char getIcon(){
	if(this.icon!=0)
	return icon;
	else
		return Level.EMPTYTILEICON;
}

public void setIcon(char icon){
	this.icon=icon;
}

public static int getNumForCharacter(char character) {	//for the purposes of equipping items, associate numbers with letters
    char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    for(int i=0;i<alphabet.length;i++){
    	if(character==alphabet[i])
    		return i;
    }
    return -1;
}

public String name=null;
private char icon=0;
public boolean consumable=false;
public boolean equippable;
private int stackSize=1; //TODO: implement stackability for items.
}
