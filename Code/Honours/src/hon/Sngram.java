package hon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Sngram {
	
	static String text = "";
	
	static Boolean unigrams;
	static Boolean bigrams;
	static Boolean removeUnwanted;
	
	static ArrayList<String> sentences;
	static ArrayList<String> words;
	
	public static String corpus;

	private static InputStreamReader r;
	private static FileInputStream s; 
	private static BufferedReader br;
	
	public static TreeMap <String, Double> unigramCounts = new TreeMap <String, Double> ();
	public static TreeMap <String, TreeMap <String, Double>> bigramCounts = new TreeMap <String, TreeMap <String, Double>> ();

	public static String clue;
	public static String fixedClue = "";

	public static void main(String[] args) {
		
		GUI.buildGUI();	
	}
	
	public static void prepareCorpus(Boolean b) throws IOException{
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		System.out.println("Started at " + dateFormat.format(date));
		
		
		unigrams = true;				// Count unigrams
		bigrams = true;					// Count bigrams
		removeUnwanted = b;				
		
		text = readFile();
		breakIntoSentences(text);

	}
	
	public static void queryAnagram(Boolean removeWordsFromClue) throws IOException{
		
		if(removeWordsFromClue){
			fixClue(getClue());	
		}
		
		AnagramIndicators ai = new AnagramIndicators();
		AnagramIndicators.appendIndicator(getClue());
	}
	
	public static void queryProbability(String theSentence){
		
	}
	
	public static void queryDictionary(Boolean removeWordsFromClue, String letter) throws IOException{
		
		if(removeWordsFromClue){
			fixClue(getClue());	
		}
		
		Dictionary d = new Dictionary(letter);
		Dictionary.appendWord(getClue());
	}
	
	// Read in the text file of the corpus
	public static String readFile() throws IOException{
		
		
		// getCorpus() finds the desired corpus, selected by the user
		s = new FileInputStream("src\\corpora\\" + getCorpus() + ".txt");
		r = new InputStreamReader(s);
		
		int data = r.read();
		
		// Iterate through the file until the entire thing is read
		while(data != -1){
			char c = (char) data;		
			text += c;
			data = r.read();			
		}
		
		// Return the corpus content
		return text;
	}
	
	// Break a sentence into words
	public static ArrayList<String> breakIntoWords(String theSentence){
		
        String [] words;
        ArrayList <String> theWords;
        ArrayList <String> theWordsTidiedUp;
        
        // Separate into words
        words = theSentence.split(" "); 
        theWords = new ArrayList <String>(Arrays.asList(words));
        theWordsTidiedUp = new ArrayList<String> ();
        
        // Remove all punctuation and multiple spaces
        for(String word : theWords) {
                        word = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
                        theWordsTidiedUp.add(word);
        }
		return theWordsTidiedUp;
	}
	
	// Break a text into sentences
	public static void breakIntoSentences(String text) throws IOException{
		
        String theSentence = "";
        ArrayList<String> words = new ArrayList<String>();
        
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.UK);

        iterator.setText(text);
        int start = iterator.first();
        
        // Split the corpus into sentences
        // Extract the information from each sentence, add it to the tree
        // Then move onto the next sentence 
        // Sentences are not held after they are used to save memory
        for(int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()){
        	theSentence = text.substring(start, end);
        	theSentence = theSentence.replaceAll("\n", " ");
        	theSentence = theSentence.replaceAll("[^A-Za-z0-9 ]", "");
        	theSentence = theSentence.replaceAll("\\s+", " ");

        	// Break the sentence into words
            words = breakIntoWords(theSentence);  //separate into words
            
            // if removeUnwanted is true, remove unwanted words
    		if(removeUnwanted){
    			words = removeUnwantedWords(words);
    		}
        	
    		// if unigrams is true, calculate the unigrams
    		if(unigrams){
    			calcUnigrams(words);
    		}		
    		
    		// if bigrams is true, calculate the SBigrams
    		if(bigrams){
    			calcBigrams(words);
    		}
        }
        
        // Write the results to a file
        writeToFile();	
	}

	// Calculate the unigrams (Occurrences of each word)
	public static void calcUnigrams(ArrayList<String> words){
		
	    for (String s : words) {
	    	// Unigram exists - update count
	        if (unigramCounts.containsKey(s)) {
	        	unigramCounts.put(s, unigramCounts.get(s) + 1);
	        } else {
	        	// Unigram doesn't exist - create new branch with value of 1
	            unigramCounts.put(s, 1.0);
	        }
	    }
	}
	
	// Calculate bigrams
	public static void calcBigrams(ArrayList<String> words){
				
		String word1;
		String otherWord;
		TreeMap <String, Double> newEntry;
		TreeMap <String, Double> oldEntry;
	
		for(int i = 0; i < words.size(); i++) {
			word1 = words.get(i);
			for(int j = i + 1; j < words.size(); j++) {
				otherWord = words.get(j);	
				//System.out.println(word1 + " " +otherWord);
				if(bigramCounts.containsKey(word1)) {
					// Key found
					// Get inner map 
					oldEntry = bigramCounts.get(word1);
					if(oldEntry.containsKey(otherWord)) {
						// Bigram already exists
						// Update count
						oldEntry.put(otherWord, oldEntry.get(otherWord) + 1);
						//System.out.println("Bigram exists " +word1 + " " +otherWord +" updating count to " +oldEntry.get(otherWord));
					} else {
						// Bigram doesn't exist
						oldEntry.put(otherWord, 1.0);
						//System.out.println("Key exists - new branch " +word1 + " " +otherWord);
					}
					
				} else {
					// Key not found - make new entry
					newEntry = new TreeMap <String, Double> ();
					newEntry.put(otherWord, 1.0);
					bigramCounts.put(word1, newEntry);
					//System.out.println("New key - adding " +word1 + " " +otherWord);
					
				}
			}			
		}
	}

	// Remove unwanted words using Unwanted words.txt
	public static ArrayList<String> removeUnwantedWords(ArrayList<String> words) throws IOException{
		
		// Load the list of unwanted 
		br = new BufferedReader(new FileReader("src\\res\\Unwanted words.txt"));
		
	    String line;
	    
	    // For each word in the list
	    while ((line = br.readLine()) != null) {

	    	// For each word in the sentence
	    	for(int i = 0; i < words.size(); i++){
	    		
	    		// If the words match, remove the word from the sentence
	    		if(words.get(i).equals(line)){
	    			words.remove(i);
	    		}
	    	}
	    }
		
	    // return the sentence with words removed
		return words;
	}
	
	public static void fixClue(String theClue) throws IOException{
		ArrayList<String> clueInWords = new ArrayList<String>();
		clueInWords = breakIntoWords(getClue());
		clueInWords = removeUnwantedWords(clueInWords);
		
		fixedClue = "";
		
		for(int i = 0; i < clueInWords.size(); i++){
			
			if(i != (clueInWords.size()-1)){
				fixedClue += clueInWords.get(i) + " ";
			}else{
				fixedClue += clueInWords.get(i);
			}
		}
		
		setClue(fixedClue);
	}
	
	// Write to a file since the console isn't big enough
	public static void writeToFile() throws IOException{
		
		FileOutputStream s;
		OutputStreamWriter w;		
		BufferedWriter bw;

		if(unigrams){
			
			s  = new FileOutputStream("src\\unigrams\\" + getCorpus() + ".txt", false);
			w = new OutputStreamWriter(s);
			bw = new BufferedWriter(w);
			
			for(Entry<String, Double> entry : unigramCounts.entrySet()){
		    	
		    	String key = entry.getKey();
		        Double value = entry.getValue();
		        
		        String line = key + "\t" + value;
				bw.write(line);
				bw.newLine();
		        
		    }

			bw.close();
		}
		
		if(bigrams){
			
			s  = new FileOutputStream("src\\bigrams\\" + getCorpus() + ".txt", false);
			w = new OutputStreamWriter(s);
			bw = new BufferedWriter(w);
					
			for(Entry<String, TreeMap<String, Double>> outer : bigramCounts.entrySet()){

		    	String key = outer.getKey();
		    	
		    	for(Entry<String,Double> inner : outer.getValue().entrySet()){
		    		double occurrences = calcTotal(outer.getKey(), inner.getKey(), inner.getValue());
		    		String line = key + " " + inner.getKey() + " " + inner.getValue() + " (" + occurrences + ")" ;
				    bw.write(line);
					bw.newLine();
		    	}
		    	
		    	bw.newLine();
		    }
			bw.close();
			
		}
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();

		System.out.println(getCorpus() + " Loaded at " + dateFormat.format(date));
		
	}
	
	// Add the inverse of a bigram to get an accurate number ("the man" is added to "man the")
	public static double calcTotal(String inWord, String outWord, double occurrences){
		
		if(!inWord.equals(outWord)){
			for(Entry<String, TreeMap<String, Double>> outer : bigramCounts.entrySet()){
				
		    	if(outer.getKey().equals(outWord)){
			    	for(Entry<String,Double> inner : outer.getValue().entrySet()){
			    		
			    		if(inner.getKey().equals(inWord)){
			    			occurrences += inner.getValue();
			    		}
			    	}	
		    	}
		    }	
		}

		return occurrences;
	}
	
	// Evalulate the probability of the sentence and assign a value
	public static double calcProbability(String theSentence){
		
		ArrayList<String> words = new ArrayList<String>();
		
		words = breakIntoWords(theSentence);
		
		double occ = 0;
		
		for(int i = 0; i < words.size(); i++){
			
			for(int j = i+1; j < words.size(); j++){
				occ += (calcTotal(words.get(i),words.get(j),0) + (calcTotal(words.get(j),words.get(i),0)));
				
				//System.out.println(words.get(i) + " " + words.get(j) + " " + occ);
			}
		}
		
		occ = occ / (words.size()*(words.size()-1)/2);
		
		return occ;
	}
	
	
	public static String getCorpus() {
		return corpus;
	}

	public static void setCorpus(String corpus) {
		Sngram.corpus = corpus;
	}
	
	public static String getClue() {
		return clue;
	}

	public static void setClue(String clue) {
		Sngram.clue = clue;
	}

}
