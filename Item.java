
public abstract class Item {  	//other classes should interface, not inherit (since effects/functionality may differ greatly)

public abstract void use(Monster target);

public String toString(){
	return name;
}

public String name;
public boolean consumable=false;
public int stackSize; //TODO: implememnt stackability for items.
}
