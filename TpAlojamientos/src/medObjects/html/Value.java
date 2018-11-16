package medObjects.html;

public class Value {
	
	private String content;
	
	public Value() {
		this.setContent("value of content is not set");
	}
	
	public Value(String value) {
		this.setContent(value);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
