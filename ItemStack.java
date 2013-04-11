//NOTE!: use getClass() to tell what type of item something is!
public class ItemStack extends Inventory {  	
	
	public ItemStack(){
		this.items=(new Item[100]);
	}

	public Item topItem(){		//consider moving this to inventory, if it is ever used there
		return getItem(0);
	}
	
	public char stackChar(){
		if(topItem()!=null)
			return topItem().getIcon();		
		else
			return 0;	//an empty stack doesn't need a character anyway. Use this later to tell if a tile has any items to show.
	}
}
