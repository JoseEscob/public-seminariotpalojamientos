package extra;

import java.util.ArrayList;

public class Tag {

	public String innerName;

	private ArrayList<String> children;
	private ArrayList<String> attributes;

	public Tag(String innerName){
		this.children = new ArrayList<String>();
		this.attributes = new ArrayList<String>();
		this.innerName = innerName;
	}

	public void appendChild(String child){
		this.children.add(child);
	}

	public ArrayList<String> getChildren(){
		return children;
	}
	
	public String asTag(){
		if(this.children.size() == 0){
			return asTagWithoutChildren();
		}
		return asTagWithChildren();
	}
	private String asTagWithChildren(){
		String ret = "<"+innerName;
		for(String a : attributes) {
			ret+=" "+a;
		}
		ret += ">";
		for(String s : children) {
			ret += s;
		}
		
		return ret+"</"+innerName+">";
	}
	private String asTagWithoutChildren(){
		String ret = "<"+innerName;
		for(String a : attributes) {
			ret+=" "+a;
		}
		ret += "/>";

		return ret;	
	}
	
	public void addAttribute(String name, String value) {
		this.attributes.add(name+"='"+value+"'");
	}

	
}