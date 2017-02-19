package hon;

public class RankedClue implements Comparable {
	
	private double perplexity;
	private String text;
	
	public RankedClue(double perplexity, String text) {
		setPerplexity(perplexity);
		setText(text);
	}
	
	public double getPerplexity() {
		return this.perplexity;
	}
	public void setPerplexity(double perplexity) {
		this.perplexity = perplexity;
	}
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public int compareTo(Object o) {
		RankedClue theClue = (RankedClue) o;
		
		
		if(this.perplexity < theClue.getPerplexity()) {
			return 1;
		}
		if(this.perplexity == theClue.getPerplexity()) {
			return -1;
		}
		return -1;
	}
	
}
