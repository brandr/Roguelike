
public class Inventory {

  public Inventory(){
		maxItems=30;
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
	
	public String toString(){
		
		if(isEmpty()){
			return "empty";
		}
		
		String retVal = "";
		int index = 0;
		while (items[index]!=null){
			retVal+=items[index].toString();
			index++;
		}
		return retVal;
	}
	
	public boolean isEmpty(){
		return items[0]==null;
	}
	
	public Item getItem(int index){
		return items[index];
	}
	
	public void addItem(Item newItem){
		int index=0;
		while(items[index]!=null){
			index++;
			if(index >= maxItems){
				System.out.println("No room.");
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
	
	
	private int maxItems;
	private Item [] items;
	
}
