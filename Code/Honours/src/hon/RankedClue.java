package hon;

public class RankedClue implements Comparable {
	
	private double probability;
	private String text;
	
	public RankedClue(double probability, String text) {
		setProbability(probability);
		setText(text);
	}
	
	public double getProbability() {
		return this.probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public int compareTo(Object o) {
		RankedClue theClue = (RankedClue) o;
		
		
		if(this.probability < theClue.getProbability()) {
			return 1;
		}
		if(this.probability == theClue.getProbability()) {
			return -1;
		}
		return -1;
	}
	
}
