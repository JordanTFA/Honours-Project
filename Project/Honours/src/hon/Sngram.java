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
	static ArrayList<String> sentences;
	static ArrayList<String> words;
	static String[] paragraphs;
	public static String corpus;

	private static InputStreamReader r;
	private static FileInputStream s; 
	
	private static BufferedReader br;

	public static void main(String[] args) throws IOException {
		
		setCorpus("Harry Potter");
		removeUnwanted = true;
		
		text = readFile();
		
		sentences = new ArrayList<String>();
		words = new ArrayList<String>();
		

		
		sentences = breakIntoSentences(text);
		words = breakIntoWords(sentences);
		
		if(removeUnwanted){
			words = removeUnwantedWords(words);
		}
		
		calcUnigrams(words);
		
		/*
		 * Split the text into paragraphs
		 * Can be used if necessary
		 * 
		 * 	paragraphs = breakIntoParagraphs(text);
		 * 	for(int i = 0; i < paragraphs.length; i++){
		 * 		sentencesToAdd = breakIntoSentences(paragraphs[i]);	
		 *		sentences.addAll(sentencesToAdd);
		 *	}
		*/
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
	
	public static ArrayList<String> breakIntoWords(ArrayList<String> sentences){
		
        String [] words;
        ArrayList <String> theWords;
        ArrayList <String> theWordsTidiedUp;
        ArrayList<String> totalWords = new ArrayList<String>();
        
        for(int i = 0; i < sentences.size(); i++){
            words = sentences.get(i).split(" "); //separate into words
            theWords = new ArrayList <String>(Arrays.asList(words));
            theWordsTidiedUp = new ArrayList<String> ();
            for(String word : theWords) {
                            //System.out.println("Before " +word);
                            word = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
                            //System.out.println("After " +word);
                            theWordsTidiedUp.add(word);

            }
            totalWords.addAll(theWordsTidiedUp);
            
        }
		return totalWords;
	}
	
	public static String[] breakIntoParagraphs(String text){
		
		paragraphs = text.split("\n");
		
		return paragraphs;
	}
	
	public static ArrayList<String> breakIntoSentences(String para){
		
        String theSentence;
        ArrayList <String> ss = new ArrayList<String>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.UK);

        iterator.setText(para);
        int start = iterator.first();
        
        for(int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()){
        	theSentence = para.substring(start, end);
        	theSentence = theSentence.replaceAll("\\s+", " ");
        	
        	ss.add(theSentence);
        }
        
		return ss;
		
	}

	public static void calcUnigrams(ArrayList<String> words){
		
		TreeMap <String, Double> unigramCounts = new TreeMap <String, Double> ();
		
	    for (String s : words) {
	        if (unigramCounts.containsKey(s)) {
	        	unigramCounts.put(s, unigramCounts.get(s) + 1);
	        } else {
	            unigramCounts.put(s, 1.0);
	        }
	    }
	    
	    for(Entry<String, Double> entry : unigramCounts.entrySet()){
	    	
	    	String key = entry.getKey();
	        Double value = entry.getValue();
	        
	    	System.out.println(key + "\t\t" + value);
	    }
	    
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
	public static String getCorpus() {
		return corpus;
	}

	public static void setCorpus(String corpus) {
		Sngram.corpus = corpus;
	}

}
