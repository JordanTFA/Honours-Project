package hon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Sngram {
	
	static String text = "";
	static Boolean removeUnwanted;
	static Boolean unigrams;
	static Boolean bigrams;
	static ArrayList<String> sentences;
	static ArrayList<String> words;
	public static String corpus;

	private static InputStreamReader r;
	private static FileInputStream s; 
	private static BufferedReader br;
	public static TreeMap <String, Double> unigramCounts = new TreeMap <String, Double> ();
	public static TreeMap <String, TreeMap <String, Double>> bigramCounts = new TreeMap <String, TreeMap <String, Double>> ();

	public static void main(String[] args) throws IOException {
	
		setCorpus("I am Sam");	// Choose corpus
		
		removeUnwanted = false;	// remove words listed in "Unwanted words.txt"
		unigrams = true;		// Count unigrams
		bigrams = false;		// Count bigrams
		
		text = readFile();
		breakIntoSentences(text);
	}
	
	public static String readFile() throws IOException{
		
		s = new FileInputStream("src\\corpora\\" + getCorpus() + ".txt");
		r = new InputStreamReader(s);
		
		int data = r.read();
		
		while(data != -1){
			char c = (char) data;		
			text += c;
			data = r.read();			
		}
		
		return text;
	}
	
	public static ArrayList<String> breakIntoWords(String theSentence){
		
        String [] words;
        ArrayList <String> theWords;
        ArrayList <String> theWordsTidiedUp;
        
        words = theSentence.split(" "); //separate into words
        theWords = new ArrayList <String>(Arrays.asList(words));
        theWordsTidiedUp = new ArrayList<String> ();
        for(String word : theWords) {
                        //System.out.println("Before " +word);
                        word = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
                        //System.out.println("After " +word);
                        theWordsTidiedUp.add(word);

        }
		return theWordsTidiedUp;
	}
	
	
	public static void breakIntoSentences(String para) throws IOException{
		
        String theSentence = "";
        ArrayList<String> words = new ArrayList<String>();
        
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.UK);

        iterator.setText(para);
        int start = iterator.first();
        
        for(int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()){
        	theSentence = para.substring(start, end);
        	theSentence = theSentence.replaceAll("[^A-Za-z0-9 ]", "");
        	theSentence = theSentence.replaceAll("\\s+", " ");

            words = breakIntoWords(theSentence);  //separate into words
            
    		if(removeUnwanted){
    			words = removeUnwantedWords(words);
    		}
    		
    		System.out.println(words);
        	
    		if(unigrams){
    			calcUnigrams(words);
    		}		
    		
    		if(bigrams){
    			calcBigrams(words);
    		}
        }
        
        displayUnigrams();
        displayBigrams();
		
	}

	public static void calcUnigrams(ArrayList<String> words){
		
	    for (String s : words) {
	        if (unigramCounts.containsKey(s)) {
	        	unigramCounts.put(s, unigramCounts.get(s) + 1);
	        } else {
	            unigramCounts.put(s, 1.0);
	        }
	    }
	}
	
	public static void calcBigrams(ArrayList<String> words){
				
		/*for(int i = 0; i < words.size(); i++){
			if(bigramCounts.containsKey(words.get(i))){
				if(bigramCounts.containsValue(words.get(i+1))){
					bigramCounts.put(words.get(i), words.get(i+1), 1.00);
				}	
			}
		}
	    /*for (String s : words) {
	    	//System.out.println(s + " " +(s));
	        if (bigramCounts.containsKey(s)) {
	        	if(bigramCounts.containsValue(s+1)){
	        		
	        		
	        	}
	        	//bigramCounts.put(s, bigramCounts.get(s) + 1);
	        } else {
	        	//bigramCounts.put(s, 1.0);
	        }
	    }*/
	}

	public static ArrayList<String> removeUnwantedWords(ArrayList<String> words) throws IOException{
		
		br = new BufferedReader(new FileReader("src\\res\\Unwanted words.txt"));
		
	    String line;
	    
	    
	    while ((line = br.readLine()) != null) {

	    	for(int i = 0; i < words.size(); i++){
	    		
	    		if(words.get(i).equals(line)){
	    			words.remove(i);
	    		}
	    	}
	    }
		
		return words;
	}
	
	public static void displayUnigrams(){
		
	    for(Entry<String, Double> entry : unigramCounts.entrySet()){
	    	
	    	String key = entry.getKey();
	        Double value = entry.getValue();
	        
	        System.out.println(key + "\t\t" + value);
	        
	    }  
		
	}
	
	public static void displayBigrams(){
		
	    for(Entry<String, TreeMap<String, Double>> entry : bigramCounts.entrySet()){
	    	
	    	String key = entry.getKey();
	        TreeMap<String, Double> value = entry.getValue();
	        
	    	System.out.println(key + "\t\t" + value);
	    }
		
		
	}
	
	public static String getCorpus() {
		return corpus;
	}

	public static void setCorpus(String corpus) {
		Sngram.corpus = corpus;
	}

}
