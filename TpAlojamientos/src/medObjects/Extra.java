package medObjects;

import java.util.ArrayList;

public interface Extra<T>{
	//Buen�simo
	public void appendChild(T child);
	public void appendChildren(ArrayList<T> children);
	public ArrayList<T> getChildren();
	
}
