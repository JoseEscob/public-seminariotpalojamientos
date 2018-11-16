package medObjects;

import java.util.ArrayList;

public interface Extra<T>{
	//Buenísimo
	public void appendChild(T child);
	public void appendChildren(ArrayList<T> children);
	public ArrayList<T> getChildren();
	
}
