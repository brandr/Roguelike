
public class Inventory {

	public static final int MAXITEMS=30;
	
	public Inventory(){
		maxItems=MAXITEMS;
		items = new Item[maxItems];
	}
	
	public Inventory(int maxItems){
		this.maxItems=maxItems;
		items = new Item[maxItems];
	}
	
/*	public void setNewInventory(Item[] items){//TODO: resets inventory (should be done in initializations. might not be necessary at all)
		for(int i=0;i<items.length;i++){
			this.items[i]=items[i];
		}
	}*/
	
	
	public String[] listInventory(){	//redundant?
		String[] itemNames = new String[items.length];
		for(int i=0;i<items.length;i++){
			itemNames[i]=items[i].toString();
		}
		return itemNames;
	}
	
	public String toString(){	//idea: string array? (might be more versatile for displaying.)
		
		if(isEmpty()){
			return "(empty)";
		}
		
		String retVal = "";
		int index = 0;
		while (items[index]!=null){
			if(items[index].equippable)
				retVal+=((Equipment)items[index]);
			else
				retVal+=items[index].toString();
			retVal+="\n";
			index++;
		}
		return retVal;
	}
	
	public boolean isEmpty(){
		return items[0]==null;
	}
	
	public boolean isFull(){
		return items[maxItems-1]!=null;
	}
	
	public Item getItem(int index){
		return items[index];
	}
	
	public void addItem(Item newItem){
		int index=0;
		while(items[index]!=null){
			index++;
			if(index >= maxItems){
				System.out.println("No room.");	//need a way to display in gui.
			}
		}
		items[index]=newItem;
	}
	
	public void removeItem(int index){		//rough. Will need revising.
		if(items[index]!=null){
			
			while(items[index+1]!= null){
				
				if(index+1==maxItems){
					items[index]=items[index+1];
					items[index+1]=null;
					return;
				}
				
				items[index]=items[index+1];
				index++;
			}
			items[index]=null;
		}
	}
	
	public void removeItem(Item removedItem) {	//special function with an item as an arg instead of an int. Will probably be useful.
		int index=0;
		while(items[index]!=removedItem&&items[index]!=null)
			index++;
		
		removeItem(index);
	}
	
	public Item takeItem(int index){
		if(containsItem(index)){
		Item takenItem=getItem(index);		//will this work? might cause shallow copy/deep copy problems
		removeItem(index);
		return takenItem;
		}
		else
			return null;	//might need a better case for when the index is not correct for an item
	}
	
	private boolean containsItem(int index) {
		if(index>=0&&index<maxItems&&items[index]!=null){
			return true;
		}
		return false;
	}
	
	public Item topItem(){		//consider moving this to inventory, if it is ever used there
		return items[0];
	}

	public int getMaxItems(){
		return maxItems;
	}
	
	/*public void setItems(Item[] items) {
		for(int i=0;i<items.length;i++){
			this.items[i]=items[i];
		}
		
	}*/
	
	private int maxItems;
	protected Item [] items;
	
}
