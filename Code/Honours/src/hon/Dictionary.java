package hon;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;



/**
 * Dictionary
 * @author      John Owens <j.owens@napier.ac.uk>
 * @version     1.1                 
 * @since       2014-02-24          
 */
public class Dictionary {
	File file;

	private static TreeMap <String, ArrayList<String>> theWords;
	static TreeSet<RankedClue> allClues = new TreeSet<RankedClue>();
		
	public Dictionary(String letter) {
		initialiseStructure();
			//loadDictionaryFromFile();
			//printAll();
		
		switch(letter){
		case "A":
			loadAWords();
			break;	
		case "B":
			loadBWords();
			break;
		case "C":
			loadCWords();
			break;
		case "D":
			loadDWords();
			break;
		case "E":
			loadEWords();
			break;
		case "F":
			loadFWords();
			break;
		case "G":
			loadGWords();
			break;
		case "H":
			loadHWords();
			break;
		case "I":
			loadIWords();
			break;
		case "J":
			loadJWords();
			break;	
		case "K":
			loadKWords();
			break;
		case "L":
			loadLWords();
			break;
		case "M":
			loadMWords();
			break;
		case "N":
			loadNWords();
			break;
		case "O":
			loadOWords();
			break;
		case "P":
			loadPWords();
			break;
		case "Q":
			loadQWords();
			break;
		case "R":
			loadRWords();
			break;
		case "S":
			loadSWords();
			break;
		case "T":
			loadTWords();
			break;
		case "U":
			loadUWords();
			break;
		case "V":
			loadVWords();
			break;
		case "W":
			loadWWords();
			break;
		case "X":
			loadXWords();
			break;
		case "Y":
			loadYWords();
			break;
		case "Z":
			loadZWords();
			break;
		}
	}

	private void initialiseStructure() {
		this.theWords = new TreeMap <String, ArrayList<String>> ();
	}
	
	public static void appendWord(String clue){
		
		String clueWithIndicator = "";
		double prob;
		for(String ti : theWords.keySet()){
			
			clueWithIndicator = clue + " " + ti;
			prob = Sngram.calcProbability(clueWithIndicator);
			RankedClue newClue = new RankedClue(prob,clueWithIndicator);
			
			allClues.add(newClue);
		}
		
		for(RankedClue rc : allClues){
			System.out.println(rc.getProbability() + "\t" + rc.getText());
		}
		
		allClues.clear();
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		System.out.println("Finished at " + dateFormat.format(date));
		
		
	}
	
	public boolean containsWord(String word) {
		return this.theWords.keySet().contains(word);		
	}
	
	public String getRandomWord() {
		Random randomGenerator = new Random();
		int numWords;
		numWords = this.theWords.keySet().size();
		int randomWordNum = randomGenerator.nextInt(numWords);
		
		return (String) this.theWords.keySet().toArray()[randomWordNum];		
		
	}
	
	public String getADefinition(String word) {
		
		ArrayList <String> meanings = this.theWords.get(word);
		
		if(meanings.size() > 1) {
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(meanings.size());
			return meanings.get(randomInt);

		} else {
			return meanings.get(0);
		}
	}
	
	public Set<String> getAllEntries() {
		return this.theWords.keySet();
	}
	
	public void printAll() {
		String output = "";
		ArrayList <String> meanings;
	
			for(Object tempWord : this.theWords.keySet()) {
				output = output + tempWord +"\n";
				meanings = (ArrayList<String>) this.theWords.get(tempWord);
				for(String meaning : meanings) {
					output = output + meaning +"\n";
				}
				output = output + "\n";
			}
		
	
		System.out.println(output);
	
	}
	
	public String toString() {
		String output = "";
		ArrayList <String> meanings;
	
			for(Object tempWord : this.theWords.keySet()) {
				output = output + tempWord +"\n";
				meanings = (ArrayList<String>) this.theWords.get(tempWord);
				for(String meaning : meanings) {
					output = output + meaning +"\n";
				}
				output = output + "\n";
			}
		
	
		return output;
	
	}
	
	public ArrayList<String> getAllDefinitions(String word) {
		
		ArrayList <String> meanings = (ArrayList<String>) this.theWords.get(word);
		
		return meanings;
	}
	
	public int size() {
		return this.theWords.keySet().size();
	}

	private void loadDictionaryFromFile() {
		
		file = new File("dictionary.txt");
		FileInputStream fis = null;
		BufferedReader br = null;
	    BufferedInputStream bis = null;
	    
	    String keyWord, meaning;
	    ArrayList <String> meanings;
		
	    try {
	    	fis = new FileInputStream(file);
	    	// Here BufferedInputStream is added for fast reading.
	        bis = new BufferedInputStream(fis);
	        br = new BufferedReader(new InputStreamReader(bis));
	        
	         while (br.ready()) {
	        	keyWord = br.readLine();
	        	System.out.println("Keyword is " +keyWord);
	        	meanings = new ArrayList <String>();
	        	do {
	        		meaning = br.readLine().trim();
	        		System.out.println("Found " +meaning);
	        		if(!meaning.isEmpty()) {
	        			meanings.add(meaning);
		        		System.out.println("For " +keyWord +" addding " +meaning);
	        		}
	        	} while(!meaning.isEmpty());
	        	this.theWords.put(keyWord, meanings);
	         }//while loop

	         // dispose all the resources after using them.
	         fis.close();
	         bis.close();
	         br.close();

	    } catch (FileNotFoundException e) {
	      	e.printStackTrace();
	    } catch (IOException e) {
	       	e.printStackTrace();
	    }
	}

	private void loadAWords() {
		String key;
		ArrayList <String> meanings;
		
		// load a's
		key = "a";
		meanings = new ArrayList <String>();
		meanings.add("a");
		meanings.add("article");
		meanings.add("first letter");
		meanings.add("one");
		this.theWords.put(key, meanings);

		key = "abates";
		meanings = new ArrayList <String>();
		meanings.add("lets up");
		this.theWords.put(key, meanings);

		key = "abdomen";
		meanings = new ArrayList <String>();
		meanings.add("tummy");
		this.theWords.put(key, meanings);

		key = "abel";
		meanings = new ArrayList <String>();
		meanings.add("murder victim");
		this.theWords.put(key, meanings);

		key = "abet";
		meanings = new ArrayList <String>();
		meanings.add("help a villan");
		this.theWords.put(key, meanings);

		key = "ability";
		meanings = new ArrayList <String>();
		meanings.add("skill");
		meanings.add("strength");
		meanings.add("talent");
		this.theWords.put(key, meanings);

		key = "able";
		meanings = new ArrayList <String>();
		meanings.add("clever");
		meanings.add("competent");
		meanings.add("having talent");
		meanings.add("skillful");
		this.theWords.put(key, meanings);

		key = "abler";
		meanings = new ArrayList <String>();
		meanings.add("more adept");
		this.theWords.put(key, meanings);

		key = "abode";
		meanings = new ArrayList <String>();
		meanings.add("home");
		this.theWords.put(key, meanings);

		key = "abound";
		meanings = new ArrayList <String>();
		meanings.add("flourish");
		this.theWords.put(key, meanings);

		key = "about";
		meanings = new ArrayList <String>();
		meanings.add("in the vicinity");
		meanings.add("more or less");
		this.theWords.put(key, meanings);

		key = "aboveall";  //above all
		meanings = new ArrayList <String>();
		meanings.add("primarily");
		this.theWords.put(key, meanings);

		key = "abreast";
		meanings = new ArrayList <String>();
		meanings.add("side by side");
		this.theWords.put(key, meanings);

		key = "abs";
		meanings = new ArrayList <String>();
		meanings.add("abdominal muscles");
		this.theWords.put(key, meanings);

		key = "absorb";
		meanings = new ArrayList <String>();
		meanings.add("soak up");
		meanings.add("take in");
		this.theWords.put(key, meanings);

		key = "accent";
		meanings = new ArrayList <String>();
		meanings.add("stress");
		this.theWords.put(key, meanings);

		key = "accept";
		meanings = new ArrayList <String>();
		meanings.add("willingly receive");
		this.theWords.put(key, meanings);

		key = "accost";
		meanings = new ArrayList <String>();
		meanings.add("challenge");
		this.theWords.put(key, meanings);

		key = "account";
		meanings = new ArrayList <String>();
		meanings.add("reckoning");
		meanings.add("story");
		this.theWords.put(key, meanings);

		key = "accurate";
		meanings = new ArrayList <String>();
		meanings.add("precise");
		this.theWords.put(key, meanings);

		key = "accursed";
		meanings = new ArrayList <String>();
		meanings.add("damned");
		this.theWords.put(key, meanings);

		key = "accused";
		meanings = new ArrayList <String>();
		meanings.add("one on trial");
		this.theWords.put(key, meanings);

		key = "ace";
		meanings = new ArrayList <String>();
		meanings.add("expert");
		this.theWords.put(key, meanings);

		key = "aces";
		meanings = new ArrayList <String>();
		meanings.add("experts");
		this.theWords.put(key, meanings);

		key = "acer";
		meanings = new ArrayList <String>();
		meanings.add("tree");
		this.theWords.put(key, meanings);

		key = "aces";
		meanings = new ArrayList <String>();
		meanings.add("cards");
		meanings.add("experts");
		meanings.add("high cards");
		this.theWords.put(key, meanings);

		key = "ache";
		meanings = new ArrayList <String>();
		meanings.add("pain");
		this.theWords.put(key, meanings);

		key = "aches";
		meanings = new ArrayList <String>();
		meanings.add("pains");
		this.theWords.put(key, meanings);

		key = "achilles";
		meanings = new ArrayList <String>();
		meanings.add("Greek hero");
		this.theWords.put(key, meanings);

		key = "acid";
		meanings = new ArrayList <String>();
		meanings.add("sharp");
		this.theWords.put(key, meanings);

		key = "acing";
		meanings = new ArrayList <String>();
		meanings.add("serving well");
		this.theWords.put(key, meanings);

		key = "acne";
		meanings = new ArrayList <String>();
		meanings.add("spots");
		this.theWords.put(key, meanings);

		key = "acre";
		meanings = new ArrayList <String>();
		meanings.add("amount of land");
		this.theWords.put(key, meanings);

		key = "acreage";
		meanings = new ArrayList <String>();
		meanings.add("extent of land");
		this.theWords.put(key, meanings);

		key = "acrid";
		meanings = new ArrayList <String>();
		meanings.add("pungent");
		meanings.add("tart");
		this.theWords.put(key, meanings);

		key = "act";
		meanings = new ArrayList <String>();
		meanings.add("deed");
		meanings.add("play");
		this.theWords.put(key, meanings);

		key = "acted";
		meanings = new ArrayList <String>();
		meanings.add("played");
		this.theWords.put(key, meanings);

		key = "acting";
		meanings = new ArrayList <String>();
		meanings.add("pretending");
		this.theWords.put(key, meanings);

		key = "active";
		meanings = new ArrayList <String>();
		meanings.add("animated");
		this.theWords.put(key, meanings);

		key = "acton";//Acton and act on
		meanings = new ArrayList <String>();
		meanings.add("keep playing");
		meanings.add("part of London");
		this.theWords.put(key, meanings);

		key = "actor";
		meanings = new ArrayList <String>();
		meanings.add("film star");
		this.theWords.put(key, meanings);

		key = "actors";
		meanings = new ArrayList <String>();
		meanings.add("film stars");
		this.theWords.put(key, meanings);

		key = "acts";
		meanings = new ArrayList <String>();
		meanings.add("does something");
		meanings.add("legislation");
		this.theWords.put(key, meanings);

		key = "ada";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);

		key = "adam";
		meanings = new ArrayList <String>();
		meanings.add("first man");
		this.theWords.put(key, meanings);

		key = "adder";
		meanings = new ArrayList <String>();
		meanings.add("snake");
		this.theWords.put(key, meanings);

		key = "addled";
		meanings = new ArrayList <String>();
		meanings.add("off");
		this.theWords.put(key, meanings);

		key = "add";
		meanings = new ArrayList <String>();
		meanings.add("do arithmetic");
		this.theWords.put(key, meanings);

		key = "adds";
		meanings = new ArrayList <String>();
		meanings.add("appends");
		this.theWords.put(key, meanings);

		key = "adept";
		meanings = new ArrayList <String>();
		meanings.add("expert");
		meanings.add("proficient");
		this.theWords.put(key, meanings);

		key = "adhere";
		meanings = new ArrayList <String>();
		meanings.add("stick");
		this.theWords.put(key, meanings);

		key = "adhered";
		meanings = new ArrayList <String>();
		meanings.add("stuck");
		this.theWords.put(key, meanings);

		key = "adherent";
		meanings = new ArrayList <String>();
		meanings.add("disciple");
		this.theWords.put(key, meanings);

		key = "adhesive";
		meanings = new ArrayList <String>();
		meanings.add("glue");
		this.theWords.put(key, meanings);

		key = "adios";
		meanings = new ArrayList <String>();
		meanings.add("farewell");
		this.theWords.put(key, meanings);

		key = "admirals";
		meanings = new ArrayList <String>();
		meanings.add("naval officers");
		this.theWords.put(key, meanings);

		key = "admire";
		meanings = new ArrayList <String>();
		meanings.add("applaud");
		this.theWords.put(key, meanings);

		key = "admit";
		meanings = new ArrayList <String>();
		meanings.add("confess");
		meanings.add("let in");
		this.theWords.put(key, meanings);

		key = "admittance";
		meanings = new ArrayList <String>();
		meanings.add("entry");
		this.theWords.put(key, meanings);

		key = "ado";
		meanings = new ArrayList <String>();
		meanings.add("bother");
		this.theWords.put(key, meanings);

		key = "adoration";
		meanings = new ArrayList <String>();
		meanings.add("reverence");
		this.theWords.put(key, meanings);

		key = "adore";
		meanings = new ArrayList <String>();
		meanings.add("love");
		this.theWords.put(key, meanings);

		key = "adoring";
		meanings = new ArrayList <String>();
		meanings.add("worshipping");
		this.theWords.put(key, meanings);

		key = "adverse";
		meanings = new ArrayList <String>();
		meanings.add("contrary");
		this.theWords.put(key, meanings);

		key = "advertisements";
		meanings = new ArrayList <String>();
		meanings.add("notices");
		this.theWords.put(key, meanings);

		key = "advertises";
		meanings = new ArrayList <String>();
		meanings.add("makes known");
		this.theWords.put(key, meanings);

		key = "adverts";
		meanings = new ArrayList <String>();
		meanings.add("publicity");
		this.theWords.put(key, meanings);

		key = "aerials";
		meanings = new ArrayList <String>();
		meanings.add("receivers");
		this.theWords.put(key, meanings);

		key = "aesop";
		meanings = new ArrayList <String>();
		meanings.add("Greek storyteller");
		meanings.add("storyteller");
		this.theWords.put(key, meanings);

		key = "affidavit";
		meanings = new ArrayList <String>();
		meanings.add("written statement confirmed by oath");
		this.theWords.put(key, meanings);

		key = "afraid";
		meanings = new ArrayList <String>();
		meanings.add("fearful");
		this.theWords.put(key, meanings);

		key = "african";//only used for anagrams
		meanings = new ArrayList <String>();
		meanings.add("from Africa");
		this.theWords.put(key, meanings);

		key = "aft";
		meanings = new ArrayList <String>();
		meanings.add("behind");
		this.theWords.put(key, meanings);

		key = "afters";
		meanings = new ArrayList <String>();
		meanings.add("pudding");
		this.theWords.put(key, meanings);

		key = "aga";
		meanings = new ArrayList <String>();
		meanings.add("cooking stove");
		this.theWords.put(key, meanings);

		key = "again";
		meanings = new ArrayList <String>();
		meanings.add("once more");
		meanings.add("period");
		this.theWords.put(key, meanings);

		key = "agate";
		meanings = new ArrayList <String>();
		meanings.add("stone");
		this.theWords.put(key, meanings);

		key = "agave";
		meanings = new ArrayList <String>();
		meanings.add("tropical plant");
		this.theWords.put(key, meanings);

		key = "age";
		meanings = new ArrayList <String>();
		meanings.add("mature");
		meanings.add("time");
		this.theWords.put(key, meanings);

		key = "ageless";
		meanings = new ArrayList <String>();
		meanings.add("not appearing to get older");
		this.theWords.put(key, meanings);

		key = "agenda";
		meanings = new ArrayList <String>();
		meanings.add("plan");
		meanings.add("schedule");
		this.theWords.put(key, meanings);

		key = "agent";
		meanings = new ArrayList <String>();
		meanings.add("rep");
		meanings.add("spy");
		this.theWords.put(key, meanings);

		key = "ages";
		meanings = new ArrayList <String>();
		meanings.add("a long time");
		meanings.add("very long time");
		this.theWords.put(key, meanings);

		key = "aging";
		meanings = new ArrayList <String>();
		meanings.add("getting on");
		this.theWords.put(key, meanings);

		key = "agitate";
		meanings = new ArrayList <String>();
		meanings.add("disturb");
		this.theWords.put(key, meanings);

		key = "agree";
		meanings = new ArrayList <String>();
		meanings.add("concur");
		this.theWords.put(key, meanings);

		key = "agrees";
		meanings = new ArrayList <String>();
		meanings.add("concurs");
		this.theWords.put(key, meanings);

		key = "ahead";
		meanings = new ArrayList <String>();
		meanings.add("in front");
		this.theWords.put(key, meanings);

		key = "aid";
		meanings = new ArrayList <String>();
		meanings.add("help");
		this.theWords.put(key, meanings);

		key = "aida";
		meanings = new ArrayList <String>();
		meanings.add("Verdi opera");
		this.theWords.put(key, meanings);

		key = "aides";
		meanings = new ArrayList <String>();
		meanings.add("they assist");
		this.theWords.put(key, meanings);

		key = "ail";
		meanings = new ArrayList <String>();
		meanings.add("suffer");
		this.theWords.put(key, meanings);

		key = "ailed";
		meanings = new ArrayList <String>();
		meanings.add("was sick");
		this.theWords.put(key, meanings);

		key = "ailing";
		meanings = new ArrayList <String>();
		meanings.add("getting sick");
		meanings.add("suffering");
		this.theWords.put(key, meanings);

		key = "aintree";
		meanings = new ArrayList <String>();
		meanings.add("racecourse");
		this.theWords.put(key, meanings);

		key = "air";
		meanings = new ArrayList <String>();
		meanings.add("demeanour");
		this.theWords.put(key, meanings);

		key = "aircrew";
		meanings = new ArrayList <String>();
		meanings.add("jet set");
		this.theWords.put(key, meanings);

		key = "airman";
		meanings = new ArrayList <String>();
		meanings.add("flier");
		this.theWords.put(key, meanings);

		key = "airs";
		meanings = new ArrayList <String>();
		meanings.add("expresses opinion");
		meanings.add("affected manner");
		this.theWords.put(key, meanings);

		key = "alarm";
		meanings = new ArrayList <String>();
		meanings.add("panic");
		this.theWords.put(key, meanings);

		key = "alas";
		meanings = new ArrayList <String>();
		meanings.add("expression of regret");
		meanings.add("sadly");
		this.theWords.put(key, meanings);

		key = "alcove";
		meanings = new ArrayList <String>();
		meanings.add("recess");
		this.theWords.put(key, meanings);

		key = "ale";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		meanings.add("porter");
		this.theWords.put(key, meanings);

		key = "alec";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);

		key = "alert";
		meanings = new ArrayList <String>();
		meanings.add("notify");
		meanings.add("vigilant");
		meanings.add("warn");
		meanings.add("watchful");
		this.theWords.put(key, meanings);

		key = "ales";
		meanings = new ArrayList <String>();
		meanings.add("drinks");
		this.theWords.put(key, meanings);

		key = "alfresco";
		meanings = new ArrayList <String>();
		meanings.add("outdoors");
		this.theWords.put(key, meanings);

		key = "algorithm";
		meanings = new ArrayList <String>();
		meanings.add("process");
		this.theWords.put(key, meanings);

		key = "alien";
		meanings = new ArrayList <String>();
		meanings.add("foreign");
		this.theWords.put(key, meanings);

		key = "all";
		meanings = new ArrayList <String>();
		meanings.add("each and every");
		meanings.add("everyone");
		meanings.add("everything");
		this.theWords.put(key, meanings);

		key = "allergy";
		meanings = new ArrayList <String>();
		meanings.add("bad reaction");
		this.theWords.put(key, meanings);

		key = "allies";
		meanings = new ArrayList <String>();
		meanings.add("confederates");
		this.theWords.put(key, meanings);

		key = "allots";
		meanings = new ArrayList <String>();
		meanings.add("shares out");
		this.theWords.put(key, meanings);

		key = "allow";
		meanings = new ArrayList <String>();
		meanings.add("let");
		meanings.add("permit");
		this.theWords.put(key, meanings);

		key = "allowed";
		meanings = new ArrayList <String>();
		meanings.add("let");
		meanings.add("permitted");
		this.theWords.put(key, meanings);

		key = "alloy";
		meanings = new ArrayList <String>();
		meanings.add("blend");
		this.theWords.put(key, meanings);

		key = "allure";
		meanings = new ArrayList <String>();
		meanings.add("attraction");
		this.theWords.put(key, meanings);

		key = "ally";
		meanings = new ArrayList <String>();
		meanings.add("friend");
		this.theWords.put(key, meanings);

		key = "alma";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);

		key = "almanac";
		meanings = new ArrayList <String>();
		meanings.add("calendar");
		this.theWords.put(key, meanings);

		key = "almond";
		meanings = new ArrayList <String>();
		meanings.add("nut");
		this.theWords.put(key, meanings);

		key = "alms";
		meanings = new ArrayList <String>();
		meanings.add("charitable gifts");
		meanings.add("charity");
		meanings.add("charity donation");
		this.theWords.put(key, meanings);

		key = "aloft";
		meanings = new ArrayList <String>();
		meanings.add("up in the air");
		this.theWords.put(key, meanings);

		key = "along";
		meanings = new ArrayList <String>();
		meanings.add("by the side of");
		this.theWords.put(key, meanings);

		key = "alp";
		meanings = new ArrayList <String>();
		meanings.add("mountain");
		this.theWords.put(key, meanings);

		key = "alpanist";
		meanings = new ArrayList <String>();
		meanings.add("mountain climber");
		this.theWords.put(key, meanings);

		key = "alps";
		meanings = new ArrayList <String>();
		meanings.add("range");
		this.theWords.put(key, meanings);

		key = "also";
		meanings = new ArrayList <String>();
		meanings.add("as well");
		meanings.add("too");
		this.theWords.put(key, meanings);

		key = "altar";
		meanings = new ArrayList <String>();
		meanings.add("religious table");
		this.theWords.put(key, meanings);

		key = "alter";
		meanings = new ArrayList <String>();
		meanings.add("change");
		this.theWords.put(key, meanings);

		key = "alternative";
		meanings = new ArrayList <String>();
		meanings.add("option");
		this.theWords.put(key, meanings);

		key = "altruism";
		meanings = new ArrayList <String>();
		meanings.add("unselfishness");
		this.theWords.put(key, meanings);

		key = "amass";
		meanings = new ArrayList <String>();
		meanings.add("gather");
		this.theWords.put(key, meanings);

		key = "amateur";
		meanings = new ArrayList <String>();
		meanings.add("non professional");
		this.theWords.put(key, meanings);

		key = "amaze";
		meanings = new ArrayList <String>();
		meanings.add("stagger");
		this.theWords.put(key, meanings);

		key = "amble";
		meanings = new ArrayList <String>();
		meanings.add("slow walk");
		this.theWords.put(key, meanings);

		key = "ambler";
		meanings = new ArrayList <String>();
		meanings.add("walker");
		this.theWords.put(key, meanings);

		key = "ambles";
		meanings = new ArrayList <String>();
		meanings.add("walks");
		this.theWords.put(key, meanings);

		key = "ambush";
		meanings = new ArrayList <String>();
		meanings.add("sudden attack");
		this.theWords.put(key, meanings);

		key = "American";
		meanings = new ArrayList <String>();
		meanings.add("yank");
		this.theWords.put(key, meanings);

		key = "amen";
		meanings = new ArrayList <String>();
		meanings.add("last word");
		meanings.add("so be it");
		this.theWords.put(key, meanings);

		key = "amethyst";
		meanings = new ArrayList <String>();
		meanings.add("stone");
		this.theWords.put(key, meanings);

		key = "amiable";
		meanings = new ArrayList <String>();
		meanings.add("pleasant in manner");
		this.theWords.put(key, meanings);

		key = "amiss";
		meanings = new ArrayList <String>();
		meanings.add("wrong");
		this.theWords.put(key, meanings);

		key = "ammo";
		meanings = new ArrayList <String>();
		meanings.add("bullets");
		this.theWords.put(key, meanings);

		key = "amnesty";
		meanings = new ArrayList <String>();
		meanings.add("pardon");
		this.theWords.put(key, meanings);

		key = "amount";
		meanings = new ArrayList <String>();
		meanings.add("measure");
		this.theWords.put(key, meanings);

		key = "amp";
		meanings = new ArrayList <String>();
		meanings.add("power unit");
		this.theWords.put(key, meanings);

		key = "ample";
		meanings = new ArrayList <String>();
		meanings.add("more than enough");
		this.theWords.put(key, meanings);

		key = "amps";
		meanings = new ArrayList <String>();
		meanings.add("current units");
		this.theWords.put(key, meanings);

		key = "amuse";
		meanings = new ArrayList <String>();
		meanings.add("divert");
		this.theWords.put(key, meanings);

		key = "amy";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		meanings.add("woman");
		this.theWords.put(key, meanings);

		key = "an";
		meanings = new ArrayList <String>();
		meanings.add("article");
		this.theWords.put(key, meanings);

		key = "anagram";
		meanings = new ArrayList <String>();
		meanings.add("wordplay");
		this.theWords.put(key, meanings);

		key = "anarchy";
		meanings = new ArrayList <String>();
		meanings.add("lawless state");
		this.theWords.put(key, meanings);

		key = "anchor";
		meanings = new ArrayList <String>();
		meanings.add("secure a vessel");
		this.theWords.put(key, meanings);

		key = "ancient";
		meanings = new ArrayList <String>();
		meanings.add("very old");
		this.theWords.put(key, meanings);

		key = "and";
		meanings = new ArrayList <String>();
		meanings.add("conjunction");
		this.theWords.put(key, meanings);

		key = "andes";
		meanings = new ArrayList <String>();
		meanings.add("mountain range");
		meanings.add("mountains");
		this.theWords.put(key, meanings);

		key = "andrew";
		meanings = new ArrayList <String>();
		meanings.add("prince");
		this.theWords.put(key, meanings);

		key = "anecdote";
		meanings = new ArrayList <String>();
		meanings.add("funny story");
		this.theWords.put(key, meanings);

		key = "angel";
		meanings = new ArrayList <String>();
		meanings.add("spirit");
		this.theWords.put(key, meanings);

		key = "anger";
		meanings = new ArrayList <String>();
		meanings.add("infuriate");
		this.theWords.put(key, meanings);

		key = "angers";
		meanings = new ArrayList <String>();
		meanings.add("infuriates");
		this.theWords.put(key, meanings);

		key = "angle";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);

		key = "ankle";
		meanings = new ArrayList <String>();
		meanings.add("joint");
		this.theWords.put(key, meanings);

		key = "anglesey";
		meanings = new ArrayList <String>();
		meanings.add("Welsh island");
		this.theWords.put(key, meanings);

		key = "angora";
		meanings = new ArrayList <String>();
		meanings.add("fine wool");
		this.theWords.put(key, meanings);

		key = "animal";
		meanings = new ArrayList <String>();
		meanings.add("neither vegetable nor mineral");
		this.theWords.put(key, meanings);

		key = "ann";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);

		key = "anna";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);

		key = "anne";
		meanings = new ArrayList <String>();
		meanings.add("queen");
		this.theWords.put(key, meanings);

		key = "annie";
		meanings = new ArrayList <String>();
		meanings.add("musical");
		this.theWords.put(key, meanings);

		key = "annual";
		meanings = new ArrayList <String>();
		meanings.add("book");
		this.theWords.put(key, meanings);

		key = "annul";
		meanings = new ArrayList <String>();
		meanings.add("cancel");
		this.theWords.put(key, meanings);

		key = "anon";
		meanings = new ArrayList <String>();
		meanings.add("unknown author");
		this.theWords.put(key, meanings);

		key = "another";
		meanings = new ArrayList <String>();
		meanings.add("extra");
		this.theWords.put(key, meanings);

		key = "answer";
		meanings = new ArrayList <String>();
		meanings.add("response");
		this.theWords.put(key, meanings);

		key = "ant";
		meanings = new ArrayList <String>();
		meanings.add("colony dweller");
		meanings.add("small insect");
		meanings.add("social worker");
		meanings.add("worker");
		this.theWords.put(key, meanings);

		key = "antenna";
		meanings = new ArrayList <String>();
		meanings.add("aerial");
		this.theWords.put(key, meanings);

		key = "anterior";
		meanings = new ArrayList <String>();
		meanings.add("situated at the front");
		meanings.add("preceding");
		this.theWords.put(key, meanings);

		key = "anthem";
		meanings = new ArrayList <String>();
		meanings.add("national song");
		meanings.add("song");
		this.theWords.put(key, meanings);

		key = "anti";
		meanings = new ArrayList <String>();
		meanings.add("opposed to");
		this.theWords.put(key, meanings);

		key = "antic";
		meanings = new ArrayList <String>();
		meanings.add("caper");
		this.theWords.put(key, meanings);

		key = "anvil";
		meanings = new ArrayList <String>();
		meanings.add("block");
		this.theWords.put(key, meanings);

		key = "ants";
		meanings = new ArrayList <String>();
		meanings.add("social workers");
		meanings.add("workers");
		this.theWords.put(key, meanings);

		key = "antwerp";
		meanings = new ArrayList <String>();
		meanings.add("somewhere in Belgium");
		this.theWords.put(key, meanings);

		key = "any";
		meanings = new ArrayList <String>();
		meanings.add("whichever");
		this.theWords.put(key, meanings);

		key = "apart";
		meanings = new ArrayList <String>();
		meanings.add("seperate");
		this.theWords.put(key, meanings);

		key = "ape";
		meanings = new ArrayList <String>();
		meanings.add("big monkey");
		meanings.add("mimic");
		this.theWords.put(key, meanings);

		key = "apes";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		meanings.add("big monkeys");
		meanings.add("mimics");
		this.theWords.put(key, meanings);

		key = "apostle";
		meanings = new ArrayList <String>();
		meanings.add("missionary");
		this.theWords.put(key, meanings);

		key = "apparent";
		meanings = new ArrayList <String>();
		meanings.add("obvious");
		this.theWords.put(key, meanings);

		key = "apparently";
		meanings = new ArrayList <String>();
		meanings.add("evidently");
		this.theWords.put(key, meanings);

		key = "appeal";
		meanings = new ArrayList <String>();
		meanings.add("charm");
		this.theWords.put(key, meanings);

		key = "appetite";
		meanings = new ArrayList <String>();
		meanings.add("desire");
		meanings.add("hunger");
		this.theWords.put(key, meanings);

		key = "apple";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);

		key = "apprise";
		meanings = new ArrayList <String>();
		meanings.add("inform");
		this.theWords.put(key, meanings);

		key = "apron";
		meanings = new ArrayList <String>();
		meanings.add("pinny");
		this.theWords.put(key, meanings);

		key = "apse";
		meanings = new ArrayList <String>();
		meanings.add("part of church");
		this.theWords.put(key, meanings);

		key = "apero";
		meanings = new ArrayList <String>();
		meanings.add("pre-dinner drink");
		this.theWords.put(key, meanings);

		key = "aperient";
		meanings = new ArrayList <String>();
		meanings.add("mild laxetive");
		this.theWords.put(key, meanings);

		key = "aplomb";
		meanings = new ArrayList <String>();
		meanings.add("self-assurance");
		this.theWords.put(key, meanings);

		key = "appear";
		meanings = new ArrayList <String>();
		meanings.add("become visible");
		meanings.add("be published");
		meanings.add("come into sight");
		meanings.add("feature");
		meanings.add("materialise");
		meanings.add("show");
		meanings.add("turn up in court");
		this.theWords.put(key, meanings);

		key = "appetiser";
		meanings = new ArrayList <String>();
		meanings.add("starter");
		this.theWords.put(key, meanings);

		key = "apposite";
		meanings = new ArrayList <String>();
		meanings.add("relevant");
		this.theWords.put(key, meanings);

		key = "apple";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		meanings.add("one of your five a day");
		meanings.add("type of tree");
		this.theWords.put(key, meanings);	

		key = "apples";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);	

		key = "apprenticeship";
		meanings = new ArrayList <String>();
		meanings.add("training period");
		this.theWords.put(key, meanings);

		key = "apricot";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);

		key = "apt";
		meanings = new ArrayList <String>();
		meanings.add("appropriate");
		this.theWords.put(key, meanings);

		key = "arabic";
		meanings = new ArrayList <String>();
		meanings.add("language");
		this.theWords.put(key, meanings);

		key = "arch";
		meanings = new ArrayList <String>();
		meanings.add("curve");
		meanings.add("shape");
		this.theWords.put(key, meanings);

		key = "are";
		meanings = new ArrayList <String>();
		meanings.add("amount to");
		this.theWords.put(key, meanings);

		key = "area";
		meanings = new ArrayList <String>();
		meanings.add("region");
		this.theWords.put(key, meanings);

		key = "arena";
		meanings = new ArrayList <String>();
		meanings.add("battle ground");
		meanings.add("venue");
		this.theWords.put(key, meanings);

		key = "arenas";
		meanings = new ArrayList <String>();
		meanings.add("venues");
		this.theWords.put(key, meanings);

		key = "aria";
		meanings = new ArrayList <String>();
		meanings.add("operatic song");
		meanings.add("song");
		this.theWords.put(key, meanings);

		key = "aristotle";
		meanings = new ArrayList <String>();
		meanings.add("ancient Greek");
		this.theWords.put(key, meanings);

		key = "argentine";
		meanings = new ArrayList <String>();
		meanings.add("south american");
		this.theWords.put(key, meanings);

		key = "ark";
		meanings = new ArrayList <String>();
		meanings.add("old boat");
		meanings.add("vessel");
		this.theWords.put(key, meanings);

		key = "arm";
		meanings = new ArrayList <String>();
		meanings.add("limb");
		meanings.add("provide weapons");
		meanings.add("weapon");
		this.theWords.put(key, meanings);

		key = "armed";
		meanings = new ArrayList <String>();
		meanings.add("carrying a gun");
		this.theWords.put(key, meanings);

		key = "armless";
		meanings = new ArrayList <String>();
		meanings.add("like the Venus De Milo");
		this.theWords.put(key, meanings);

		key = "arno";
		meanings = new ArrayList <String>();
		meanings.add("river in Italy");
		this.theWords.put(key, meanings);

		key = "aroma";
		meanings = new ArrayList <String>();
		meanings.add("pungent smell");
		this.theWords.put(key, meanings);

		key = "arrived";
		meanings = new ArrayList <String>();
		meanings.add("gained recognition");
		this.theWords.put(key, meanings);

		key = "arrow";
		meanings = new ArrayList <String>();
		meanings.add("indicator");
		meanings.add("missile");
		this.theWords.put(key, meanings);

		key = "arsenic";
		meanings = new ArrayList <String>();
		meanings.add("poison");
		this.theWords.put(key, meanings);

		key = "art";
		meanings = new ArrayList <String>();
		meanings.add("creative work");
		this.theWords.put(key, meanings);

		key = "artichoke";
		meanings = new ArrayList <String>();
		meanings.add("something to eat");
		this.theWords.put(key, meanings);

		key = "article";
		meanings = new ArrayList <String>();
		meanings.add("thing");
		this.theWords.put(key, meanings);

		key = "artisan";
		meanings = new ArrayList <String>();
		meanings.add("made in a traditional way");
		this.theWords.put(key, meanings);

		key = "artist";
		meanings = new ArrayList <String>();
		meanings.add("Constable, perhaps");
		this.theWords.put(key, meanings);

		key = "artists";
		meanings = new ArrayList <String>();
		meanings.add("performers");
		this.theWords.put(key, meanings);

		key = "artiste";
		meanings = new ArrayList <String>();
		meanings.add("performer");
		this.theWords.put(key, meanings);

		key = "artless";
		meanings = new ArrayList <String>();
		meanings.add("lacking guile");
		this.theWords.put(key, meanings);

		key = "as";
		meanings = new ArrayList <String>();
		meanings.add("during the time of");
		meanings.add("like");
		this.theWords.put(key, meanings);

		key = "ascend";
		meanings = new ArrayList <String>();
		meanings.add("come up");
		this.theWords.put(key, meanings);

		key = "ascot";
		meanings = new ArrayList <String>();
		meanings.add("racecourse");
		this.theWords.put(key, meanings);

		key = "ash";
		meanings = new ArrayList <String>();
		meanings.add("fire residue");
		meanings.add("tree");
		meanings.add("wood");
		this.theWords.put(key, meanings);

		key = "ashes";
		meanings = new ArrayList <String>();
		meanings.add("trees");
		meanings.add("what's left after burning");
		this.theWords.put(key, meanings);

		key = "ashtray";
		meanings = new ArrayList <String>();
		meanings.add("butt container");
		this.theWords.put(key, meanings);

		key = "aside";
		meanings = new ArrayList <String>();
		meanings.add("apart");
		this.theWords.put(key, meanings);

		key = "ask";
		meanings = new ArrayList <String>();
		meanings.add("request");
		this.theWords.put(key, meanings);

		key = "asp";
		meanings = new ArrayList <String>();
		meanings.add("snake");
		this.theWords.put(key, meanings);

		key = "asps";
		meanings = new ArrayList <String>();
		meanings.add("snakes");
		this.theWords.put(key, meanings);

		key = "aspic";
		meanings = new ArrayList <String>();
		meanings.add("jelly");
		this.theWords.put(key, meanings);

		key = "aspire";
		meanings = new ArrayList <String>();
		meanings.add("have an ambition");
		meanings.add("hope");
		this.theWords.put(key, meanings);

		key = "aspirin";
		meanings = new ArrayList <String>();
		meanings.add("drug");
		this.theWords.put(key, meanings);

		key = "asps";
		meanings = new ArrayList <String>();
		meanings.add("snakes");
		this.theWords.put(key, meanings);

		key = "ass";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("backside");
		this.theWords.put(key, meanings);

		key = "assailed";
		meanings = new ArrayList <String>();
		meanings.add("attacked");
		this.theWords.put(key, meanings);

		key = "assam";
		meanings = new ArrayList <String>();
		meanings.add("part of Inida");
		this.theWords.put(key, meanings);

		key = "assemble";
		meanings = new ArrayList <String>();
		meanings.add("gather");
		this.theWords.put(key, meanings);

		key = "asset";
		meanings = new ArrayList <String>();
		meanings.add("benefit");
		this.theWords.put(key, meanings);

		key = "assign";
		meanings = new ArrayList <String>();
		meanings.add("delegate");
		this.theWords.put(key, meanings);

		key = "assumed";
		meanings = new ArrayList <String>();
		meanings.add("as expected");
		meanings.add("put on");
		this.theWords.put(key, meanings);

		key = "aster";
		meanings = new ArrayList <String>();
		meanings.add("flower");
		this.theWords.put(key, meanings);

		key = "astringency";
		meanings = new ArrayList <String>();
		meanings.add("tart quality");
		this.theWords.put(key, meanings);

		key = "astringent";
		meanings = new ArrayList <String>();
		meanings.add("severe");
		this.theWords.put(key, meanings);

		key = "at";
		meanings = new ArrayList <String>();
		meanings.add("from");
		this.theWords.put(key, meanings);

		key = "atari";
		meanings = new ArrayList <String>();
		meanings.add("old video game maker");
		this.theWords.put(key, meanings);

		key = "ate";
		meanings = new ArrayList <String>();
		meanings.add("consumed");
		meanings.add("devoured");
		this.theWords.put(key, meanings);

		key = "atease";//at ease
		meanings = new ArrayList <String>();
		meanings.add("relaxed");
		this.theWords.put(key, meanings);

		key = "athens";
		meanings = new ArrayList <String>();
		meanings.add("capital");
		this.theWords.put(key, meanings);

		key = "athlete";
		meanings = new ArrayList <String>();
		meanings.add("runner");
		meanings.add("sportsman");
		this.theWords.put(key, meanings);

		key = "athos";
		meanings = new ArrayList <String>();
		meanings.add("musketeer");
		this.theWords.put(key, meanings);

		key = "atissue";
		meanings = new ArrayList <String>();
		meanings.add("under discussion");
		this.theWords.put(key, meanings);

		key = "atlas";
		meanings = new ArrayList <String>();
		meanings.add("book of maps");
		this.theWords.put(key, meanings);

		key = "atlast"; //at last
		meanings = new ArrayList <String>();
		meanings.add("finally");
		this.theWords.put(key, meanings);

		key = "atoll";
		meanings = new ArrayList <String>();
		meanings.add("coral island");
		this.theWords.put(key, meanings);

		key = "atom";
		meanings = new ArrayList <String>();
		meanings.add("basic thing");
		this.theWords.put(key, meanings);

		key = "atonce";
		meanings = new ArrayList <String>();
		meanings.add("without delay");
		meanings.add("without hesitation");
		this.theWords.put(key, meanings);

		key = "attest";
		meanings = new ArrayList <String>();
		meanings.add("bear witness");
		this.theWords.put(key, meanings);

		key = "attics";
		meanings = new ArrayList <String>();
		meanings.add("lofts");
		this.theWords.put(key, meanings);

		key = "audi";
		meanings = new ArrayList <String>();
		meanings.add("foreign car");
		this.theWords.put(key, meanings);

		key = "audition";
		meanings = new ArrayList <String>();
		meanings.add("test for actors");
		this.theWords.put(key, meanings);

		key = "augment";
		meanings = new ArrayList <String>();
		meanings.add("add to");
		this.theWords.put(key, meanings);

		key = "aunt";
		meanings = new ArrayList <String>();
		meanings.add("relative");
		meanings.add("female relative");
		this.theWords.put(key, meanings);

		key = "austere";
		meanings = new ArrayList <String>();
		meanings.add("stark");
		this.theWords.put(key, meanings);

		key = "author";
		meanings = new ArrayList <String>();
		meanings.add("writer");
		this.theWords.put(key, meanings);

		key = "authority";
		meanings = new ArrayList <String>();
		meanings.add("charge");
		this.theWords.put(key, meanings);

		key = "ava";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);

		key = "avarice";
		meanings = new ArrayList <String>();
		meanings.add("deadly sin");
		this.theWords.put(key, meanings);

		key = "avenue";
		meanings = new ArrayList <String>();
		meanings.add("road");
		this.theWords.put(key, meanings);

		key = "aver";
		meanings = new ArrayList <String>();
		meanings.add("declare");
		this.theWords.put(key, meanings);

		key = "averse";
		meanings = new ArrayList <String>();
		meanings.add("reluctant");
		this.theWords.put(key, meanings);

		key = "avon";
		meanings = new ArrayList <String>();
		meanings.add("river");
		meanings.add("waterway");
		this.theWords.put(key, meanings);

		key = "awash";
		meanings = new ArrayList <String>();
		meanings.add("flooded");
		this.theWords.put(key, meanings);

		key = "away";
		meanings = new ArrayList <String>();
		meanings.add("not at home");
		meanings.add("opponents' ground");
		this.theWords.put(key, meanings);

		key = "awe";
		meanings = new ArrayList <String>();
		meanings.add("wonder");
		this.theWords.put(key, meanings);

		key = "awl";
		meanings = new ArrayList <String>();
		meanings.add("tool");
		this.theWords.put(key, meanings);

		key = "axiom";
		meanings = new ArrayList <String>();
		meanings.add("taken for granted");
		this.theWords.put(key, meanings);

		key = "axis";
		meanings = new ArrayList <String>();
		meanings.add("centre of revolution");
		this.theWords.put(key, meanings);
	}//end loadAWords
	
	private void loadBWords() {
		String key;
		ArrayList <String> meanings;
		
		// load b's
				key = "baboon";
				meanings = new ArrayList <String>();
				meanings.add("monkey");
				this.theWords.put(key, meanings);
				
				key = "baby";
				meanings = new ArrayList <String>();
				meanings.add("infant");
				this.theWords.put(key, meanings);
				
				key = "bacillus";
				meanings = new ArrayList <String>();
				meanings.add("germ");
				this.theWords.put(key, meanings);
				
				key = "bacardi";
				meanings = new ArrayList <String>();
				meanings.add("rum");
				this.theWords.put(key, meanings);
				
				key = "bach";
				meanings = new ArrayList <String>();
				meanings.add("composer");
				this.theWords.put(key, meanings);
				
				key = "bachelor";
				meanings = new ArrayList <String>();
				meanings.add("single man");
				this.theWords.put(key, meanings);
				
				key = "back";
				meanings = new ArrayList <String>();
				meanings.add("second");
				meanings.add("support");
				this.theWords.put(key, meanings);
				
				key = "bacon";
				meanings = new ArrayList <String>();
				meanings.add("meat");
				this.theWords.put(key, meanings);
				
				key = "backlog";
				meanings = new ArrayList <String>();
				meanings.add("arrears of work");
				meanings.add("work yet to be done");
				this.theWords.put(key, meanings);
				
				key = "backseat";
				meanings = new ArrayList <String>();
				meanings.add("type of driver");
				this.theWords.put(key, meanings);
				
				key = "bad";
				meanings = new ArrayList <String>();
				meanings.add("immoral");
				this.theWords.put(key, meanings);
				
				key = "bade";
				meanings = new ArrayList <String>();
				meanings.add("ordered");
				this.theWords.put(key, meanings);
				
				key = "bag";
				meanings = new ArrayList <String>();
				meanings.add("get your hands on");
				this.theWords.put(key, meanings);
				
				key = "baglady";
				meanings = new ArrayList <String>();
				meanings.add("homeless person");
				this.theWords.put(key, meanings);
				
				key = "bags";
				meanings = new ArrayList <String>();
				meanings.add("plenty of");
				meanings.add("luggage");
				this.theWords.put(key, meanings);
				
				key = "bagel";
				meanings = new ArrayList <String>();
				meanings.add("roll");
				this.theWords.put(key, meanings);
				
				key = "bail";
				meanings = new ArrayList <String>();
				meanings.add("release");
				this.theWords.put(key, meanings);
				
				key = "bairns";
				meanings = new ArrayList <String>();
				meanings.add("Scottish children");
				this.theWords.put(key, meanings);
				
				key = "balaclava";
				meanings = new ArrayList <String>();
				meanings.add("battle");
				meanings.add("wooly hood");
				this.theWords.put(key, meanings);
				
				key = "bald";
				meanings = new ArrayList <String>();
				meanings.add("hairless");
				this.theWords.put(key, meanings);
				
				key = "balderdash";
				meanings = new ArrayList <String>();
				meanings.add("nonesense");
				this.theWords.put(key, meanings);
				
				key = "bale";
				meanings = new ArrayList <String>();
				meanings.add("bundle");
				this.theWords.put(key, meanings);
				
				key = "ball";
				meanings = new ArrayList <String>();
				meanings.add("dance");
				this.theWords.put(key, meanings);
				
				key = "ballad";
				meanings = new ArrayList <String>();
				meanings.add("song");
				this.theWords.put(key, meanings);
				
				key = "ballet";
				meanings = new ArrayList <String>();
				meanings.add("dance");
				this.theWords.put(key, meanings);
				
				key = "ballot";
				meanings = new ArrayList <String>();
				meanings.add("vote");
				this.theWords.put(key, meanings);
				
				key = "ballots";
				meanings = new ArrayList <String>();
				meanings.add("votes");
				this.theWords.put(key, meanings);
				
				key = "ballpoints";
				meanings = new ArrayList <String>();
				meanings.add("pens");
				this.theWords.put(key, meanings);
				
				key = "ballroom";
				meanings = new ArrayList <String>();
				meanings.add("dance venue");
				this.theWords.put(key, meanings);
				
				key = "balti";
				meanings = new ArrayList <String>();
				meanings.add("Indian food");
				this.theWords.put(key, meanings);
						
				key = "baltic";
				meanings = new ArrayList <String>();
				meanings.add("sea");
				this.theWords.put(key, meanings);
				
				key = "ban";
				meanings = new ArrayList <String>();
				meanings.add("embargo");
				meanings.add("exclude");
				meanings.add("outlaw");
				meanings.add("prohibit");
				//meanings.add("bar");
				this.theWords.put(key, meanings);
				
				key = "bananas";
				meanings = new ArrayList <String>();
				meanings.add("crazy");
				this.theWords.put(key, meanings);
				
				key = "bananaskin";//banana skin
				meanings = new ArrayList <String>();
				meanings.add("might be responsible for a slip");
				this.theWords.put(key, meanings);
				
				key = "bands";
				meanings = new ArrayList <String>();
				meanings.add("gangs");
				this.theWords.put(key, meanings);
				
				key = "bang";
				meanings = new ArrayList <String>();
				meanings.add("gun sound");
				this.theWords.put(key, meanings);
				
				key = "bangor";
				meanings = new ArrayList <String>();
				meanings.add("city");
				this.theWords.put(key, meanings);
				
				key = "bankers";
				meanings = new ArrayList <String>();
				meanings.add("certain bets");
				this.theWords.put(key, meanings);
				
				key = "banners";
				meanings = new ArrayList <String>();
				meanings.add("flags");
				meanings.add("prohibitionists");
				this.theWords.put(key, meanings);

				key = "bans";
				meanings = new ArrayList <String>();
				meanings.add("prohibits");
				this.theWords.put(key, meanings);
				
				key = "bantam";
				meanings = new ArrayList <String>();
				meanings.add("chicken");
				this.theWords.put(key, meanings);

				key = "banyam";
				meanings = new ArrayList <String>();
				meanings.add("tree");
				this.theWords.put(key, meanings);
				
				key = "bar";
				meanings = new ArrayList <String>();
				meanings.add("block");
				meanings.add("forbid");
				meanings.add("prohibit");
				meanings.add("pub counter");
				meanings.add("saloon");
				this.theWords.put(key, meanings);
				
				key = "bards";
				meanings = new ArrayList <String>();
				meanings.add("poets");
				this.theWords.put(key, meanings);
				
				key = "bars";
				meanings = new ArrayList <String>();
				meanings.add("pubs");
				this.theWords.put(key, meanings);
						
				key = "bare";
				meanings = new ArrayList <String>();
				meanings.add("nude");
				this.theWords.put(key, meanings);
						
				key = "barn";
				meanings = new ArrayList <String>();
				meanings.add("granary");
				this.theWords.put(key, meanings);
				
				key = "barns";
				meanings = new ArrayList <String>();
				meanings.add("food stores");
				this.theWords.put(key, meanings);
				
				key = "barnacle";
				meanings = new ArrayList <String>();
				meanings.add("clinging shellfish");
				meanings.add("marine crustacean");
				this.theWords.put(key, meanings);
				
				key = "baron";
				meanings = new ArrayList <String>();
				meanings.add("nobleman");
				this.theWords.put(key, meanings);
				
				key = "barometer";
				meanings = new ArrayList <String>();
				meanings.add("weather forecasting device");
				this.theWords.put(key, meanings);
				
				key = "baroness";
				meanings = new ArrayList <String>();
				meanings.add("lady");
				this.theWords.put(key, meanings);
				
				key = "barrage";
				meanings = new ArrayList <String>();
				meanings.add("artillery fire");
				meanings.add("onslaught");
				this.theWords.put(key, meanings);
				
				key = "barrier";
				meanings = new ArrayList <String>();
				meanings.add("blockage");
				meanings.add("obstacle");
				this.theWords.put(key, meanings);
				
				key = "barrister";
				meanings = new ArrayList <String>();
				meanings.add("lawyer");
				this.theWords.put(key, meanings);
				
				key = "bartender";
				meanings = new ArrayList <String>();
				meanings.add("pub worker");
				this.theWords.put(key, meanings);
				
				key = "basic";
				meanings = new ArrayList <String>();
				meanings.add("primary");
				meanings.add("simple");
				this.theWords.put(key, meanings);
				
				key = "basil";
				meanings = new ArrayList <String>();
				meanings.add("herb");
				this.theWords.put(key, meanings);
				
				key = "basket";
				meanings = new ArrayList <String>();
				meanings.add("container");
				this.theWords.put(key, meanings);
				
				key = "bat";
				meanings = new ArrayList <String>();
				meanings.add("club");
				meanings.add("flier");
				meanings.add("flying mammal");
				this.theWords.put(key, meanings);
				
				key = "bates";
				meanings = new ArrayList <String>();
				meanings.add("psychotic hotel owner");
				this.theWords.put(key, meanings);
				
				key = "bath";
				meanings = new ArrayList <String>();
				meanings.add("southern city");
				meanings.add("UK city");
				this.theWords.put(key, meanings);
				
				key = "bathe";
				meanings = new ArrayList <String>();
				meanings.add("wash");
				this.theWords.put(key, meanings);
				
				key = "bathtub";
				meanings = new ArrayList <String>();
				meanings.add("washing vessel");
				this.theWords.put(key, meanings);
				
				key = "batter";
				meanings = new ArrayList <String>();
				meanings.add("assault");
				meanings.add("baseball player");
				meanings.add("beat");
				meanings.add("pancake mix");
				this.theWords.put(key, meanings);
				
				key = "battle";
				meanings = new ArrayList <String>();
				meanings.add("fight");
				meanings.add("struggle");
				this.theWords.put(key, meanings);
				
				key = "bawl";
				meanings = new ArrayList <String>();
				meanings.add("blubber");
				meanings.add("cry");
				this.theWords.put(key, meanings);
				
				key = "bay";
				meanings = new ArrayList <String>();
				meanings.add("recess");
				this.theWords.put(key, meanings);
				
				key = "be";
				meanings = new ArrayList <String>();
				meanings.add("exist");
				this.theWords.put(key, meanings);
				
				key = "beagle";
				meanings = new ArrayList <String>();
				meanings.add("dog");
				this.theWords.put(key, meanings);
				
				key = "beam";
				meanings = new ArrayList <String>();
				meanings.add("smile at");
				meanings.add("timber support");
				this.theWords.put(key, meanings);

				key = "beano";
				meanings = new ArrayList <String>();
				meanings.add("party");
				this.theWords.put(key, meanings);
				
				key = "bear";
				meanings = new ArrayList <String>();
				meanings.add("animal");
				meanings.add("carry");
				meanings.add("endure");
				this.theWords.put(key, meanings);
				
				key = "bears";
				meanings = new ArrayList <String>();
				meanings.add("carries");
				meanings.add("endures");
				this.theWords.put(key, meanings);
				
				key = "bearskin";
				meanings = new ArrayList <String>();
				meanings.add("hat");
				this.theWords.put(key, meanings);
				
				key = "beastly";
				meanings = new ArrayList <String>();
				meanings.add("unkind");
				this.theWords.put(key, meanings);
				
				key = "beat";
				meanings = new ArrayList <String>();
				meanings.add("strike");
				this.theWords.put(key, meanings);
				
				key = "bed";
				meanings = new ArrayList <String>();
				meanings.add("berth");
				this.theWords.put(key, meanings);
				
				key = "bedlam";
				meanings = new ArrayList <String>();
				meanings.add("state of uproar");
				this.theWords.put(key, meanings);
				
				key = "bedroom";
				meanings = new ArrayList <String>();
				meanings.add("chamber");
				this.theWords.put(key, meanings);
				
				key = "bee";
				meanings = new ArrayList <String>();
				meanings.add("insect");
				meanings.add("worker");
				this.theWords.put(key, meanings);
				
				key = "beech";
				meanings = new ArrayList <String>();
				meanings.add("wood");
				this.theWords.put(key, meanings);
				
				key = "beef";
				meanings = new ArrayList <String>();
				meanings.add("meat");
				this.theWords.put(key, meanings);
				
				key = "beetle";
				meanings = new ArrayList <String>();
				meanings.add("small creature");
				this.theWords.put(key, meanings);
				
				key = "befog";
				meanings = new ArrayList <String>();
				meanings.add("cause confusion");
				this.theWords.put(key, meanings);
				
				key = "beg";
				meanings = new ArrayList <String>();
				meanings.add("petition");
				this.theWords.put(key, meanings);
				
				key = "belgians";
				meanings = new ArrayList <String>();
				meanings.add("Europeans");
				this.theWords.put(key, meanings);
				
				key = "bell";
				meanings = new ArrayList <String>();
				meanings.add("phone pioneer");
				this.theWords.put(key, meanings);
				
				key = "below";
				meanings = new ArrayList <String>();
				meanings.add("underneath");
				this.theWords.put(key, meanings);
				
				key = "belt";
				meanings = new ArrayList <String>();
				meanings.add("beat");
				this.theWords.put(key, meanings);
				
				key = "bemused";
				meanings = new ArrayList <String>();
				meanings.add("puzzled");
				this.theWords.put(key, meanings);
				
				key = "benny";
				meanings = new ArrayList <String>();
				meanings.add("little Benjamin");
				this.theWords.put(key, meanings);
				
				key = "berg";
				meanings = new ArrayList <String>();
				meanings.add("composer");
				this.theWords.put(key, meanings);
				
				key = "bergamot";
				meanings = new ArrayList <String>();
				meanings.add("pear");
				this.theWords.put(key, meanings);
				
				key = "berlin";
				meanings = new ArrayList <String>();
				meanings.add("German city");
				this.theWords.put(key, meanings);
				
				key = "bermuda";
				meanings = new ArrayList <String>();
				meanings.add("country");
				this.theWords.put(key, meanings);
				
				key = "bert";
				meanings = new ArrayList <String>();
				meanings.add("man");
				this.theWords.put(key, meanings);
				
				key = "berth";
				meanings = new ArrayList <String>();
				meanings.add("place for a boat");
				this.theWords.put(key, meanings);
				
				key = "bertha";
				meanings = new ArrayList <String>();
				meanings.add("woman");
				this.theWords.put(key, meanings);
				
				key = "bestend"; // best end
				meanings = new ArrayList <String>();
				meanings.add("cut");
				this.theWords.put(key, meanings);
						
				key = "bet";
				meanings = new ArrayList <String>();
				meanings.add("gamble");
				meanings.add("put ones money on");
				meanings.add("speculation");
				this.theWords.put(key, meanings);
				
				key = "beta";
				meanings = new ArrayList <String>();
				meanings.add("Greek character");
				this.theWords.put(key, meanings);
				
				key = "betrayed";
				meanings = new ArrayList <String>();
				meanings.add("shopped");
				this.theWords.put(key, meanings);
				
				key = "bewitch";
				meanings = new ArrayList <String>();
				meanings.add("charm");
				this.theWords.put(key, meanings);
				
				key = "beyond";
				meanings = new ArrayList <String>();
				meanings.add("on the other side");
				this.theWords.put(key, meanings);
				
				key = "bhutan";
				meanings = new ArrayList <String>();
				meanings.add("mountain kingdom");
				this.theWords.put(key, meanings);
				
				key = "bible";
				meanings = new ArrayList <String>();
				meanings.add("holy book");
				this.theWords.put(key, meanings);
				
				key = "bid";
				meanings = new ArrayList <String>();
				meanings.add("proposal");
				this.theWords.put(key, meanings);
				
				key = "big";
				meanings = new ArrayList <String>();
				meanings.add("important");
				meanings.add("large");
				this.theWords.put(key, meanings);
				
				key = "bigamy";
				meanings = new ArrayList <String>();
				meanings.add("marital crime");
				this.theWords.put(key, meanings);
				
				key = "bigger";
				meanings = new ArrayList <String>();
				meanings.add("more important");
				meanings.add("larger");
				this.theWords.put(key, meanings);
				
				key = "bigot";
				meanings = new ArrayList <String>();
				meanings.add("racist");
				this.theWords.put(key, meanings);
						
				key = "bikers";
				meanings = new ArrayList <String>();
				meanings.add("motorcyclists");
				this.theWords.put(key, meanings);
				
				key = "bin";
				meanings = new ArrayList <String>();
				meanings.add("container");
				meanings.add("get rid of");
				this.theWords.put(key, meanings);
				
				key = "bird";
				meanings = new ArrayList <String>();
				meanings.add("animal");
				this.theWords.put(key, meanings);
				
				key = "bistro";
				meanings = new ArrayList <String>();
				meanings.add("restaurant");
				this.theWords.put(key, meanings);
				
				key = "bit";
				meanings = new ArrayList <String>();
				meanings.add("got teeth into");
				meanings.add("part");
				meanings.add("piece");
				meanings.add("portion");
				this.theWords.put(key, meanings);
				
				key = "bitch";
				meanings = new ArrayList <String>();
				meanings.add("dog");
				this.theWords.put(key, meanings);
				
				key = "bitter";
				meanings = new ArrayList <String>();
				meanings.add("very cold");
				this.theWords.put(key, meanings);
				
				key = "bittern";
				meanings = new ArrayList <String>();
				meanings.add("bird");
				this.theWords.put(key, meanings);
				
				key = "blamed";
				meanings = new ArrayList <String>();
				meanings.add("held responsible for");
				this.theWords.put(key, meanings);
				
				key = "blend";
				meanings = new ArrayList <String>();
				meanings.add("mix");
				this.theWords.put(key, meanings);
				
				key = "blenheim";
				meanings = new ArrayList <String>();
				meanings.add("palace");
				this.theWords.put(key, meanings);
				
				key = "blenny";
				meanings = new ArrayList <String>();
				meanings.add("fish");
				this.theWords.put(key, meanings);
				
				key = "blessed";
				meanings = new ArrayList <String>();
				meanings.add("consecrated");
				this.theWords.put(key, meanings);
				
				key = "blithe";
				meanings = new ArrayList <String>();
				meanings.add("jolly");
				this.theWords.put(key, meanings);
				
				key = "bloater";
				meanings = new ArrayList <String>();
				meanings.add("fish");
				this.theWords.put(key, meanings);
				
				key = "bloc";
				meanings = new ArrayList <String>();
				meanings.add("allied countries");
				this.theWords.put(key, meanings);
				
				key = "block";
				meanings = new ArrayList <String>();
				meanings.add("stop");
				this.theWords.put(key, meanings);
				
				key = "blonde";
				meanings = new ArrayList <String>();
				meanings.add("fair haired person");
				this.theWords.put(key, meanings);
						
				key = "blot";
				meanings = new ArrayList <String>();
				meanings.add("smudge");
				this.theWords.put(key, meanings);
				
				key = "blots";
				meanings = new ArrayList <String>();
				meanings.add("smudges");
				this.theWords.put(key, meanings);
				
				key = "blouse";
				meanings = new ArrayList <String>();
				meanings.add("garment");
				this.theWords.put(key, meanings);

				key = "blubber";
				meanings = new ArrayList <String>();
				meanings.add("cry");
				this.theWords.put(key, meanings);
				
				key = "blue";
				meanings = new ArrayList <String>();
				meanings.add("down");
				this.theWords.put(key, meanings);
				
				key = "bluer";
				meanings = new ArrayList <String>();
				meanings.add("sadder");
				this.theWords.put(key, meanings);
				
				key = "bluebottle";
				meanings = new ArrayList <String>();
				meanings.add("fly");
				this.theWords.put(key, meanings);
				
				key = "boa";
				meanings = new ArrayList <String>();
				meanings.add("snake");
				this.theWords.put(key, meanings);
				
				key = "boar";
				meanings = new ArrayList <String>();
				meanings.add("pig");
				this.theWords.put(key, meanings);
				
				key = "boards";
				meanings = new ArrayList <String>();
				meanings.add("gets on a bus");
				this.theWords.put(key, meanings);
				
				key = "boat";
				meanings = new ArrayList <String>();
				meanings.add("vessel");
				this.theWords.put(key, meanings);
				
				key = "boater";
				meanings = new ArrayList <String>();
				meanings.add("hat");
				this.theWords.put(key, meanings);
				
				key = "boats";
				meanings = new ArrayList <String>();
				meanings.add("containers of sauce");
				meanings.add("vessels");
				this.theWords.put(key, meanings);
				
				key = "body";
				meanings = new ArrayList <String>();
				meanings.add("stiff");
				this.theWords.put(key, meanings);
				
				key = "bogus";
				meanings = new ArrayList <String>();
				meanings.add("false");
				this.theWords.put(key, meanings);
				
				key = "boiled";
				meanings = new ArrayList <String>();
				meanings.add("cooked");
				this.theWords.put(key, meanings);
				
				key = "bollards";
				meanings = new ArrayList <String>();
				meanings.add("traffic posts");
				this.theWords.put(key, meanings);
				
				key = "bomb";
				meanings = new ArrayList <String>();
				meanings.add("explosive");
				this.theWords.put(key, meanings);
				
				key = "bombay";
				meanings = new ArrayList <String>();
				meanings.add("former Indian city");
				this.theWords.put(key, meanings);
				
				key = "bone";
				meanings = new ArrayList <String>();
				meanings.add("study intensively");
				this.theWords.put(key, meanings);
				
				key = "boo";
				meanings = new ArrayList <String>();
				meanings.add("cry of contempt");
				meanings.add("shout displeasure");
				this.theWords.put(key, meanings);
				
				key = "boom";
				meanings = new ArrayList <String>();
				meanings.add("good times");
				this.theWords.put(key, meanings);
						
				key = "boost";
				meanings = new ArrayList <String>();
				meanings.add("raise");
				this.theWords.put(key, meanings);
				
				key = "boot";
				meanings = new ArrayList <String>();
				meanings.add("kick");
				meanings.add("luggage compartment");
				meanings.add("start computer");
				this.theWords.put(key, meanings);
				
				key = "boredom";
				meanings = new ArrayList <String>();
				meanings.add("tedium");
				this.theWords.put(key, meanings);
				
				key = "boring";
				meanings = new ArrayList <String>();
				meanings.add("drilling");
				meanings.add("uninteresting");
				this.theWords.put(key, meanings);
				
				key = "botanist";
				meanings = new ArrayList <String>();
				meanings.add("biologist");
				meanings.add("plant biologist");
				this.theWords.put(key, meanings);
				
				key = "bottle";
				meanings = new ArrayList <String>();
				meanings.add("vessel");
				this.theWords.put(key, meanings);
				
				key = "bough";
				meanings = new ArrayList <String>();
				meanings.add("branch");
				this.theWords.put(key, meanings);
				
				key = "bought";
				meanings = new ArrayList <String>();
				meanings.add("purchased");
				this.theWords.put(key, meanings);
				
				key = "bound";
				meanings = new ArrayList <String>();
				meanings.add("spring");
				this.theWords.put(key, meanings);
				
				key = "bow";
				meanings = new ArrayList <String>();
				meanings.add("part of London");
				this.theWords.put(key, meanings);
				
				key = "bowmore";
				meanings = new ArrayList <String>();
				meanings.add("whisky");
				this.theWords.put(key, meanings);
				
				key = "bowl";
				meanings = new ArrayList <String>();
				meanings.add("make deliveries");
				meanings.add("US stadium");
				this.theWords.put(key, meanings);
				
				key = "bowler";
				meanings = new ArrayList <String>();
				meanings.add("cricketer");
				meanings.add("hat");
				this.theWords.put(key, meanings);
				
				key = "bowls";
				meanings = new ArrayList <String>();
				meanings.add("game on the green");
				this.theWords.put(key, meanings);
				
				key = "boy";
				meanings = new ArrayList <String>();
				meanings.add("young man");
				this.theWords.put(key, meanings);
				
				key = "boys";
				meanings = new ArrayList <String>();
				meanings.add("children");
				this.theWords.put(key, meanings);
				
				key = "brag";
				meanings = new ArrayList <String>();
				meanings.add("boast");
				meanings.add("show off");
				this.theWords.put(key, meanings);
				
				key = "brain";
				meanings = new ArrayList <String>();
				meanings.add("intellect");
				this.theWords.put(key, meanings);
				
				key = "brake";
				meanings = new ArrayList <String>();
				meanings.add("stop quickly");
				this.theWords.put(key, meanings);
				
				key = "bran";
				meanings = new ArrayList <String>();
				meanings.add("cereal");
				this.theWords.put(key, meanings);
				
				key = "branch";
				meanings = new ArrayList <String>();
				meanings.add("part of tree");
				this.theWords.put(key, meanings);
				
				key = "brandish";
				meanings = new ArrayList <String>();
				meanings.add("flourish");
				this.theWords.put(key, meanings);
				
				key = "brandy";
				meanings = new ArrayList <String>();
				meanings.add("drink");
				this.theWords.put(key, meanings);
				
				key = "brassica";
				meanings = new ArrayList <String>();
				meanings.add("a green");
				this.theWords.put(key, meanings);
				
				key = "bravado";
				meanings = new ArrayList <String>();
				meanings.add("false confidence");
				this.theWords.put(key, meanings);
				
				key = "bravo";
				meanings = new ArrayList <String>();
				meanings.add("congratulations");
				this.theWords.put(key, meanings);
				
				key = "breach";
				meanings = new ArrayList <String>();
				meanings.add("gap");
				meanings.add("gap in defence");
				this.theWords.put(key, meanings);
				
				key = "bread";
				meanings = new ArrayList <String>();
				meanings.add("loaf");
				meanings.add("money");
				this.theWords.put(key, meanings);
				
				key = "breadline";
				meanings = new ArrayList <String>();
				meanings.add("food queue");
				this.theWords.put(key, meanings);

				key = "break";
				meanings = new ArrayList <String>();
				meanings.add("opportunity");
				meanings.add("split");
				this.theWords.put(key, meanings);
				
				key = "breakin";//break in
				meanings = new ArrayList <String>();
				meanings.add("unauthorised entry");
				this.theWords.put(key, meanings);
				
				key = "breast";
				meanings = new ArrayList <String>();
				meanings.add("part of a chimney");
				this.theWords.put(key, meanings);
				
				key = "brew";
				meanings = new ArrayList <String>();
				meanings.add("drink");
				meanings.add("make drink");
				meanings.add("make tea");
				this.theWords.put(key, meanings);
				
				key = "bride";
				meanings = new ArrayList <String>();
				meanings.add("newly-wed");
				this.theWords.put(key, meanings);
				
				key = "bridge";
				meanings = new ArrayList <String>();
				meanings.add("game");
				this.theWords.put(key, meanings);
				
				key = "bridget";
				meanings = new ArrayList <String>();
				meanings.add("girl");
				this.theWords.put(key, meanings);
				
				key = "brie";
				meanings = new ArrayList <String>();
				meanings.add("cheese");
				meanings.add("French cheese");
				this.theWords.put(key, meanings);
				
				key = "brief";
				meanings = new ArrayList <String>();
				meanings.add("short");
				this.theWords.put(key, meanings);
				
				key = "brigands";
				meanings = new ArrayList <String>();
				meanings.add("outlaws");
				this.theWords.put(key, meanings);
				
				key = "bright";
				meanings = new ArrayList <String>();
				meanings.add("smart");
				this.theWords.put(key, meanings);
				
				key = "brighton";
				meanings = new ArrayList <String>();
				meanings.add("seaside resort");
				this.theWords.put(key, meanings);
				
				key = "brill";
				meanings = new ArrayList <String>();
				meanings.add("excellent");
				meanings.add("swimmer");
				this.theWords.put(key, meanings);

				key = "bring";
				meanings = new ArrayList <String>();
				meanings.add("transport");
				this.theWords.put(key, meanings);
				
				key = "brogue";
				meanings = new ArrayList <String>();
				meanings.add("shoe");
				this.theWords.put(key, meanings);
				
				key = "broke";
				meanings = new ArrayList <String>();
				meanings.add("insolvent");
				this.theWords.put(key, meanings);
				
				key = "broker";
				meanings = new ArrayList <String>();
				meanings.add("middleman");
				this.theWords.put(key, meanings);
				
				key = "broom";
				meanings = new ArrayList <String>();
				meanings.add("cleaner");
				this.theWords.put(key, meanings);
				
				key = "bros";
				meanings = new ArrayList <String>();
				meanings.add("brothers");
				this.theWords.put(key, meanings);
				
				key = "bruisers";
				meanings = new ArrayList <String>();
				meanings.add("tough guys");
				this.theWords.put(key, meanings);
				
				key = "brush";
				meanings = new ArrayList <String>();
				meanings.add("sweep");
				this.theWords.put(key, meanings);
				
				key = "bubble";
				meanings = new ArrayList <String>();
				meanings.add("illusion");
				this.theWords.put(key, meanings);
				
				key = "buff";
				meanings = new ArrayList <String>();
				meanings.add("enthusiast");
				this.theWords.put(key, meanings);
				
				key = "buffoon";
				meanings = new ArrayList <String>();
				meanings.add("clown");
				this.theWords.put(key, meanings);
				
				key = "bull";
				meanings = new ArrayList <String>();
				meanings.add("animal");
				this.theWords.put(key, meanings);
				
				key = "bulldoze";
				meanings = new ArrayList <String>();
				meanings.add("knock down");
				this.theWords.put(key, meanings);
				
				key = "bun";
				meanings = new ArrayList <String>();
				meanings.add("roll");
				this.theWords.put(key, meanings);
				
				key = "bursary";
				meanings = new ArrayList <String>();
				meanings.add("student grant");
				this.theWords.put(key, meanings);
				
				key = "bus";
				meanings = new ArrayList <String>();
				meanings.add("public transport");
				meanings.add("public vehicle");
				meanings.add("transport");
				meanings.add("vehicle");
				this.theWords.put(key, meanings);
				
				key = "bush";
				meanings = new ArrayList <String>();
				meanings.add("US president");
				this.theWords.put(key, meanings);
				
				key = "bust";
				meanings = new ArrayList <String>();
				meanings.add("broke");
				meanings.add("chest");
				this.theWords.put(key, meanings);
				
				key = "but";
				meanings = new ArrayList <String>();
				meanings.add("however");
				meanings.add("objection");
				this.theWords.put(key, meanings);
				
				key = "butch";
				meanings = new ArrayList <String>();
				meanings.add("well built");
				this.theWords.put(key, meanings);
				
				key = "butt";
				meanings = new ArrayList <String>();
				meanings.add("barrel");
				this.theWords.put(key, meanings);
				
				key = "butter";
				meanings = new ArrayList <String>();
				meanings.add("dairy product");
				meanings.add("fat");
				this.theWords.put(key, meanings);
				
				key = "butterfly";
				meanings = new ArrayList <String>();
				meanings.add("insect");
				this.theWords.put(key, meanings);
				
				key = "by";
				meanings = new ArrayList <String>();
				meanings.add("near");
				this.theWords.put(key, meanings);
	}//end loadB
	
	private void loadCWords() {
		String key;
		ArrayList <String> meanings;

		// load c's
		key = "cab";
		meanings = new ArrayList <String>();
		meanings.add("horse-drawn vehicle");
		meanings.add("taxi");
		this.theWords.put(key, meanings);

		key = "cabs";
		meanings = new ArrayList <String>();
		meanings.add("horse-drawn vehicles");
		meanings.add("taxis");
		this.theWords.put(key, meanings);

		key = "cabaret";
		meanings = new ArrayList <String>();
		meanings.add("entertainment");
		this.theWords.put(key, meanings);

		key = "cable";
		meanings = new ArrayList <String>();
		meanings.add("wire");
		this.theWords.put(key, meanings);

		key = "cad";
		meanings = new ArrayList <String>();
		meanings.add("rotter");
		this.theWords.put(key, meanings);

		key = "cadet";
		meanings = new ArrayList <String>();
		meanings.add("trainee soldier");
		this.theWords.put(key, meanings);

		key = "cadets";
		meanings = new ArrayList <String>();
		meanings.add("trainee soldiers");
		this.theWords.put(key, meanings);

		key = "cafe";
		meanings = new ArrayList <String>();
		meanings.add("snack bar");
		this.theWords.put(key, meanings);

		key = "caff";
		meanings = new ArrayList <String>();
		meanings.add("eatery");
		this.theWords.put(key, meanings);

		key = "cafeteria";
		meanings = new ArrayList <String>();
		meanings.add("self-service restaurant");
		this.theWords.put(key, meanings);

		key = "cages";
		meanings = new ArrayList <String>();
		meanings.add("pens");
		this.theWords.put(key, meanings);

		key = "cain";
		meanings = new ArrayList <String>();
		meanings.add("old murderer");
		this.theWords.put(key, meanings);

		key = "cake";
		meanings = new ArrayList <String>();
		meanings.add("coat");
		meanings.add("cover");
		this.theWords.put(key, meanings);

		key = "calais";
		meanings = new ArrayList <String>();
		meanings.add("port");
		this.theWords.put(key, meanings);

		key = "calf";
		meanings = new ArrayList <String>();
		meanings.add("part of leg");
		this.theWords.put(key, meanings);

		key = "california";
		meanings = new ArrayList <String>();
		meanings.add("state");
		this.theWords.put(key, meanings);

		key = "callow";
		meanings = new ArrayList <String>();
		meanings.add("immature");
		this.theWords.put(key, meanings);

		key = "calm";
		meanings = new ArrayList <String>();
		meanings.add("serene");
		this.theWords.put(key, meanings);

		key = "calms";
		meanings = new ArrayList <String>();
		meanings.add("quietens");
		this.theWords.put(key, meanings);

		key = "cam";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);

		key = "came";
		meanings = new ArrayList <String>();
		meanings.add("arrived");
		meanings.add("turned up");
		this.theWords.put(key, meanings);

		key = "camel";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);

		key = "camelot";
		meanings = new ArrayList <String>();
		meanings.add("legendary city");
		this.theWords.put(key, meanings);

		key = "cameo";
		meanings = new ArrayList <String>();
		meanings.add("brooch");
		this.theWords.put(key, meanings);

		key = "campsite";
		meanings = new ArrayList <String>();
		meanings.add("holiday area");
		this.theWords.put(key, meanings);

		key = "can";
		meanings = new ArrayList <String>();
		meanings.add("are able to");
		meanings.add("container");
		meanings.add("is able to");
		meanings.add("preserve");
		meanings.add("prison");
		meanings.add("tin");
		this.theWords.put(key, meanings);

		key = "canary";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);

		key = "canberra";
		meanings = new ArrayList <String>();
		meanings.add("city down under");
		this.theWords.put(key, meanings);

		key = "cane";
		meanings = new ArrayList <String>();
		meanings.add("stick");
		this.theWords.put(key, meanings);

		key = "canes";
		meanings = new ArrayList <String>();
		meanings.add("sticks");
		this.theWords.put(key, meanings);

		key = "cannot";
		meanings = new ArrayList <String>();
		meanings.add("not able to");
		this.theWords.put(key, meanings);

		key = "canoe";
		meanings = new ArrayList <String>();
		meanings.add("boat");
		this.theWords.put(key, meanings);

		key = "canoes";
		meanings = new ArrayList <String>();
		meanings.add("boats");
		this.theWords.put(key, meanings);

		key = "canon";
		meanings = new ArrayList <String>();
		meanings.add("weapon");
		this.theWords.put(key, meanings);

		key = "cans";
		meanings = new ArrayList <String>();
		meanings.add("containers");
		meanings.add("preserves");
		meanings.add("tins");
		this.theWords.put(key, meanings);

		key = "cap";
		meanings = new ArrayList <String>();
		meanings.add("cover");
		meanings.add("hat");
		meanings.add("limit");
		meanings.add("top");
		this.theWords.put(key, meanings);

		key = "capes";
		meanings = new ArrayList <String>();
		meanings.add("garments");
		this.theWords.put(key, meanings);

		key = "captain";
		meanings = new ArrayList <String>();
		meanings.add("officer");
		this.theWords.put(key, meanings);

		key = "captive";
		meanings = new ArrayList <String>();
		meanings.add("prisoner");
		this.theWords.put(key, meanings);

		key = "car";
		meanings = new ArrayList <String>();
		meanings.add("vehicle");
		this.theWords.put(key, meanings);

		key = "caramel";
		meanings = new ArrayList <String>();
		meanings.add("chewy sweet");
		this.theWords.put(key, meanings);

		key = "cardigan";
		meanings = new ArrayList <String>();
		meanings.add("knitted jacket");
		this.theWords.put(key, meanings);

		key = "care";
		meanings = new ArrayList <String>();
		meanings.add("look after");
		this.theWords.put(key, meanings);

		key = "cared";
		meanings = new ArrayList <String>();
		meanings.add("looked after");
		this.theWords.put(key, meanings);

		key = "carefree";
		meanings = new ArrayList <String>();
		meanings.add("without worries");
		this.theWords.put(key, meanings);

		key = "carer";
		meanings = new ArrayList <String>();
		meanings.add("one who looks after things");
		this.theWords.put(key, meanings);

		key = "cares";
		meanings = new ArrayList <String>();
		meanings.add("is worried");
		this.theWords.put(key, meanings);

		key = "caress";
		meanings = new ArrayList <String>();
		meanings.add("gentle touch");
		meanings.add("stroke");
		this.theWords.put(key, meanings);

		key = "careworn";
		meanings = new ArrayList <String>();
		meanings.add("showing signs of stress");
		this.theWords.put(key, meanings);

		key = "carmel";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);

		key = "carnivore";
		meanings = new ArrayList <String>();
		meanings.add("meat eater");
		this.theWords.put(key, meanings);

		key = "carousel";
		meanings = new ArrayList <String>();
		meanings.add("roundabout");
		this.theWords.put(key, meanings);

		key = "carp";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);

		key = "carpenter";
		meanings = new ArrayList <String>();
		meanings.add("chippy");
		this.theWords.put(key, meanings);

		key = "carpet";
		meanings = new ArrayList <String>();
		meanings.add("floor covering");
		this.theWords.put(key, meanings);

		key = "carpets";
		meanings = new ArrayList <String>();
		meanings.add("floor coverings");
		this.theWords.put(key, meanings);

		key = "carried";
		meanings = new ArrayList <String>();
		meanings.add("transported");
		this.theWords.put(key, meanings);

		key = "cars";
		meanings = new ArrayList <String>();
		meanings.add("vehicles");
		this.theWords.put(key, meanings);

		key = "cart";
		meanings = new ArrayList <String>();
		meanings.add("wagon");
		this.theWords.put(key, meanings);

		key = "cartel";
		meanings = new ArrayList <String>();
		meanings.add("business association");
		this.theWords.put(key, meanings);

		key = "carve";
		meanings = new ArrayList <String>();
		meanings.add("cut");
		this.theWords.put(key, meanings);

		key = "case";
		meanings = new ArrayList <String>();
		meanings.add("legal action");
		this.theWords.put(key, meanings);

		key = "cash";
		meanings = new ArrayList <String>();
		meanings.add("money");
		this.theWords.put(key, meanings);

		key = "cashews";
		meanings = new ArrayList <String>();
		meanings.add("nuts");
		this.theWords.put(key, meanings);

		key = "cashmere";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);

		key = "casing";
		meanings = new ArrayList <String>();
		meanings.add("cover");
		this.theWords.put(key, meanings);

		key = "cassoulet";
		meanings = new ArrayList <String>();
		meanings.add("French stew");
		this.theWords.put(key, meanings);

		key = "cast";
		meanings = new ArrayList <String>();
		meanings.add("actors");
		meanings.add("players");
		this.theWords.put(key, meanings);

		key = "castaway";
		meanings = new ArrayList <String>();
		meanings.add("stranded person");
		this.theWords.put(key, meanings);

		key = "castoff"; //cast off
		meanings = new ArrayList <String>();
		meanings.add("begin a voyage");
		this.theWords.put(key, meanings);

		key = "castro";
		meanings = new ArrayList <String>();
		meanings.add("former Cuban leader");
		this.theWords.put(key, meanings);

		key = "casual";
		meanings = new ArrayList <String>();
		meanings.add("nochalant");
		meanings.add("type of labour");
		this.theWords.put(key, meanings);

		key = "cat";
		meanings = new ArrayList <String>();
		meanings.add("beast");
		meanings.add("pet");
		meanings.add("tiger");
		this.theWords.put(key, meanings);

		key = "cathedral";
		meanings = new ArrayList <String>();
		meanings.add("principal church of a diocese");
		this.theWords.put(key, meanings);

		key = "cats";
		meanings = new ArrayList <String>();
		meanings.add("felines");
		meanings.add("pets");
		this.theWords.put(key, meanings);

		key = "cater";
		meanings = new ArrayList <String>();
		meanings.add("provide food");
		this.theWords.put(key, meanings);
		
		key = "catwalk";
		meanings = new ArrayList <String>();
		meanings.add("narrow walkway");
		meanings.add("American runway");
		this.theWords.put(key, meanings);

		key = "cave";
		meanings = new ArrayList <String>();
		meanings.add("grotto");
		this.theWords.put(key, meanings);

		key = "caw";
		meanings = new ArrayList <String>();
		meanings.add("bird cry");
		meanings.add("cry");
		this.theWords.put(key, meanings);

		key = "ceasing";
		meanings = new ArrayList <String>();
		meanings.add("stopping");
		this.theWords.put(key, meanings);

		key = "ceaser";
		meanings = new ArrayList <String>();
		meanings.add("conqueror of Gaul");
		this.theWords.put(key, meanings);

		key = "cedar";
		meanings = new ArrayList <String>();
		meanings.add("tree");
		this.theWords.put(key, meanings);

		key = "celeriac";
		meanings = new ArrayList <String>();
		meanings.add("vegetable");
		this.theWords.put(key, meanings);

		key = "cellist";
		meanings = new ArrayList <String>();
		meanings.add("musician");
		this.theWords.put(key, meanings);

		key = "census";
		meanings = new ArrayList <String>();
		meanings.add("official survey");
		this.theWords.put(key, meanings);

		key = "cent";
		meanings = new ArrayList <String>();
		meanings.add("currency");
		meanings.add("money");
		this.theWords.put(key, meanings);

		key = "certain";
		meanings = new ArrayList <String>();
		meanings.add("sure");
		meanings.add("without doubt");
		this.theWords.put(key, meanings);

		key = "cha";
		meanings = new ArrayList <String>();
		meanings.add("tea");
		this.theWords.put(key, meanings);

		key = "chaff";
		meanings = new ArrayList <String>();
		meanings.add("rubbish");
		this.theWords.put(key, meanings);

		key = "chain";
		meanings = new ArrayList <String>();
		meanings.add("a series of related events");
		meanings.add("string of islands");
		meanings.add("type of stitch");
		this.theWords.put(key, meanings);

		key = "chained";
		meanings = new ArrayList <String>();
		meanings.add("shackled");
		this.theWords.put(key, meanings);

		key = "chair";
		meanings = new ArrayList <String>();
		meanings.add("presdie over");
		meanings.add("seat");
		this.theWords.put(key, meanings);

		key = "chairman";
		meanings = new ArrayList <String>();
		meanings.add("presiding officer");
		this.theWords.put(key, meanings);

		key = "chalet";
		meanings = new ArrayList <String>();
		meanings.add("holiday home");
		meanings.add("wooden house");
		this.theWords.put(key, meanings);

		key = "chameleon";
		meanings = new ArrayList <String>();
		meanings.add("lizard");
		this.theWords.put(key, meanings);

		key = "chance";
		meanings = new ArrayList <String>();
		meanings.add("likelihood");
		this.theWords.put(key, meanings);

		key = "chancel";
		meanings = new ArrayList <String>();
		meanings.add("part of church");
		this.theWords.put(key, meanings);

		key = "chant";
		meanings = new ArrayList <String>();
		meanings.add("sing");
		this.theWords.put(key, meanings);

		key = "chap";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);

		key = "char";
		meanings = new ArrayList <String>();
		meanings.add("cleaning lady");
		meanings.add("scorch");
		this.theWords.put(key, meanings);

		key = "charles";
		meanings = new ArrayList <String>();
		meanings.add("prince");
		this.theWords.put(key, meanings);

		key = "charleston";
		meanings = new ArrayList <String>();
		meanings.add("dance");
		this.theWords.put(key, meanings);

		key = "charm";
		meanings = new ArrayList <String>();
		meanings.add("appeal");
		meanings.add("spell");
		this.theWords.put(key, meanings);

		key = "charming";
		meanings = new ArrayList <String>();
		meanings.add("delightful");
		meanings.add("prince");
		this.theWords.put(key, meanings);

		key = "chart";
		meanings = new ArrayList <String>();
		meanings.add("map");
		this.theWords.put(key, meanings);

		key = "charts";
		meanings = new ArrayList <String>();
		meanings.add("maps");
		meanings.add("plans");
		this.theWords.put(key, meanings);

		key = "charlotte";
		meanings = new ArrayList <String>();
		meanings.add("pudding");
		this.theWords.put(key, meanings);

		key = "charm";
		meanings = new ArrayList <String>();
		meanings.add("attraction");
		this.theWords.put(key, meanings);

		key = "chase";
		meanings = new ArrayList <String>();
		meanings.add("pursuit");
		this.theWords.put(key, meanings);

		key = "chat";
		meanings = new ArrayList <String>();
		meanings.add("talk");
		this.theWords.put(key, meanings);

		key = "cheap";
		meanings = new ArrayList <String>();
		meanings.add("vulgar");
		this.theWords.put(key, meanings);

		key = "chef";
		meanings = new ArrayList <String>();
		meanings.add("cook");
		this.theWords.put(key, meanings);

		key = "chelsea";
		meanings = new ArrayList <String>();
		meanings.add("part of London");
		this.theWords.put(key, meanings);

		key = "chess";
		meanings = new ArrayList <String>();
		meanings.add("game");
		this.theWords.put(key, meanings);

		key = "chest";
		meanings = new ArrayList <String>();
		meanings.add("box");
		this.theWords.put(key, meanings);

		key = "chesty";
		meanings = new ArrayList <String>();
		meanings.add("coughhing a lot");
		this.theWords.put(key, meanings);

		key = "chestnut";
		meanings = new ArrayList <String>();
		meanings.add("old joke");
		this.theWords.put(key, meanings);

		key = "cher";
		meanings = new ArrayList <String>();
		meanings.add("US singer");
		this.theWords.put(key, meanings);

		key = "cheroots";
		meanings = new ArrayList <String>();
		meanings.add("cigar");
		this.theWords.put(key, meanings);

		key = "cherry";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);

		key = "chew";
		meanings = new ArrayList <String>();
		meanings.add("munch");
		this.theWords.put(key, meanings);

		key = "chi";
		meanings = new ArrayList <String>();
		meanings.add("Greek character");
		this.theWords.put(key, meanings);

		key = "chianti";
		meanings = new ArrayList <String>();
		meanings.add("Italian wine");
		this.theWords.put(key, meanings);

		key = "chic";
		meanings = new ArrayList <String>();
		meanings.add("stylish");
		this.theWords.put(key, meanings);

		key = "chick";
		meanings = new ArrayList <String>();
		meanings.add("little bird");
		this.theWords.put(key, meanings);

		key = "chief";
		meanings = new ArrayList <String>();
		meanings.add("leader");
		meanings.add("uppermost");
		this.theWords.put(key, meanings);
		
		key = "chihuahua";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		this.theWords.put(key, meanings);

		key = "chin";
		meanings = new ArrayList <String>();
		meanings.add("feature");
		meanings.add("prominent feature");
		this.theWords.put(key, meanings);

		key = "china";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);

		key = "choir";
		meanings = new ArrayList <String>();
		meanings.add("group of singers");
		this.theWords.put(key, meanings);

		key = "choked";
		meanings = new ArrayList <String>();
		meanings.add("throttled");
		this.theWords.put(key, meanings);

		key = "chop";
		meanings = new ArrayList <String>();
		meanings.add("piece of meat");
		this.theWords.put(key, meanings);

		key = "chopin";
		meanings = new ArrayList <String>();
		meanings.add("composer");
		this.theWords.put(key, meanings);

		key = "choral";
		meanings = new ArrayList <String>();
		meanings.add("for a choir");
		this.theWords.put(key, meanings);

		key = "chore";
		meanings = new ArrayList <String>();
		meanings.add("task");
		this.theWords.put(key, meanings);

		key = "chosen";
		meanings = new ArrayList <String>();
		meanings.add("selected");
		this.theWords.put(key, meanings);

		key = "chronic";
		meanings = new ArrayList <String>();
		meanings.add("very bad");
		this.theWords.put(key, meanings);

		key = "chronicle";
		meanings = new ArrayList <String>();
		meanings.add("record");
		this.theWords.put(key, meanings);

		key = "chum";
		meanings = new ArrayList <String>();
		meanings.add("friend");
		this.theWords.put(key, meanings);

		key = "chump";
		meanings = new ArrayList <String>();
		meanings.add("fool");
		this.theWords.put(key, meanings);

		key = "church";
		meanings = new ArrayList <String>();
		meanings.add("place of worship");
		this.theWords.put(key, meanings);

		key = "cid";
		meanings = new ArrayList <String>();
		meanings.add("detectives");
		this.theWords.put(key, meanings);

		key = "cider";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);

		key = "cigar";
		meanings = new ArrayList <String>();
		meanings.add("cylindrical roll of cured tobacco leaves");
		this.theWords.put(key, meanings);

		key = "cilla";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);

		key = "cinema";
		meanings = new ArrayList <String>();
		meanings.add("flicks");
		this.theWords.put(key, meanings);

		key = "circle";
		meanings = new ArrayList <String>();
		meanings.add("group of friends");
		meanings.add("shape");
		this.theWords.put(key, meanings);

		key = "circular";
		meanings = new ArrayList <String>();
		meanings.add("round");
		this.theWords.put(key, meanings);

		key = "citadel";
		meanings = new ArrayList <String>();
		meanings.add("fortress");
		this.theWords.put(key, meanings);

		key = "cite";
		meanings = new ArrayList <String>();
		meanings.add("quote");
		this.theWords.put(key, meanings);

		key = "city";
		meanings = new ArrayList <String>();
		meanings.add("big town");
		this.theWords.put(key, meanings);

		key = "clad";
		meanings = new ArrayList <String>();
		meanings.add("clothed");
		this.theWords.put(key, meanings);

		key = "claim";
		meanings = new ArrayList <String>();
		meanings.add("demand");
		this.theWords.put(key, meanings);

		key = "clan";
		meanings = new ArrayList <String>();
		meanings.add("family");
		meanings.add("family group");
		meanings.add("group");
		meanings.add("tribe");
		this.theWords.put(key, meanings);

		key = "clang";
		meanings = new ArrayList <String>();
		meanings.add("sound");
		this.theWords.put(key, meanings);

		key = "clare";
		meanings = new ArrayList <String>();
		meanings.add("Irish county");
		meanings.add("woman");
		this.theWords.put(key, meanings);

		key = "claret";
		meanings = new ArrayList <String>();
		meanings.add("wine");
		this.theWords.put(key, meanings);

		key = "clashes";
		meanings = new ArrayList <String>();
		meanings.add("conflicts");
		this.theWords.put(key, meanings);

		key = "class";
		meanings = new ArrayList <String>();
		meanings.add("form");
		this.theWords.put(key, meanings);

		key = "classical";
		meanings = new ArrayList <String>();
		meanings.add("traditional");
		this.theWords.put(key, meanings);

		key = "clay";
		meanings = new ArrayList <String>();
		meanings.add("soil");
		this.theWords.put(key, meanings);

		key = "clearing";
		meanings = new ArrayList <String>();
		meanings.add("glade");
		this.theWords.put(key, meanings);

		key = "clean";
		meanings = new ArrayList <String>();
		meanings.add("pure");
		this.theWords.put(key, meanings);

		key = "cleanse";
		meanings = new ArrayList <String>();
		meanings.add("free from impurities");
		this.theWords.put(key, meanings);

		key = "clematis";
		meanings = new ArrayList <String>();
		meanings.add("climbing plant");
		this.theWords.put(key, meanings);

		key = "clemency";
		meanings = new ArrayList <String>();
		meanings.add("mercy");
		this.theWords.put(key, meanings);

		key = "cleric";
		meanings = new ArrayList <String>();
		meanings.add("religious leader");
		this.theWords.put(key, meanings);

		key = "clever";
		meanings = new ArrayList <String>();
		meanings.add("bright");
		this.theWords.put(key, meanings);

		key = "cleverly";
		meanings = new ArrayList <String>();
		meanings.add("smartly");
		this.theWords.put(key, meanings);

		key = "climate";
		meanings = new ArrayList <String>();
		meanings.add("conditions");
		this.theWords.put(key, meanings);

		key = "climates";
		meanings = new ArrayList <String>();
		meanings.add("conditions");
		this.theWords.put(key, meanings);

		key = "climb";
		meanings = new ArrayList <String>();
		meanings.add("ascent");
		this.theWords.put(key, meanings);

		key = "climbs";
		meanings = new ArrayList <String>();
		meanings.add("uphill struggles");
		this.theWords.put(key, meanings);

		key = "cling";
		meanings = new ArrayList <String>();
		meanings.add("hold on");
		this.theWords.put(key, meanings);

		key = "cloak";
		meanings = new ArrayList <String>();
		meanings.add("mask");
		this.theWords.put(key, meanings);

		key = "clodhoppers";
		meanings = new ArrayList <String>();
		meanings.add("fools");
		this.theWords.put(key, meanings);

		key = "cloister";
		meanings = new ArrayList <String>();
		meanings.add("shelter for brothers");
		this.theWords.put(key, meanings);

		key = "clone";
		meanings = new ArrayList <String>();
		meanings.add("replica");
		this.theWords.put(key, meanings);

		key = "close";
		meanings = new ArrayList <String>();
		meanings.add("shut");
		this.theWords.put(key, meanings);

		key = "closed";
		meanings = new ArrayList <String>();
		meanings.add("shut");
		this.theWords.put(key, meanings);

		key = "closet";
		meanings = new ArrayList <String>();
		meanings.add("cupboard");
		this.theWords.put(key, meanings);

		key = "clot";
		meanings = new ArrayList <String>();
		meanings.add("fool");
		this.theWords.put(key, meanings);

		key = "clothe";
		meanings = new ArrayList <String>();
		meanings.add("dress");
		this.theWords.put(key, meanings);

		key = "clouts";
		meanings = new ArrayList <String>();
		meanings.add("hits");
		this.theWords.put(key, meanings);

		key = "clover";
		meanings = new ArrayList <String>();
		meanings.add("plant");
		this.theWords.put(key, meanings);

		key = "cloves";
		meanings = new ArrayList <String>();
		meanings.add("flowers");
		this.theWords.put(key, meanings);

		key = "clue";
		meanings = new ArrayList <String>();
		meanings.add("hint");
		meanings.add("lead");
		this.theWords.put(key, meanings);

		key = "cluster";
		meanings = new ArrayList <String>();
		meanings.add("group");
		this.theWords.put(key, meanings);

		key = "coarsest";
		meanings = new ArrayList <String>();
		meanings.add("roughest");
		this.theWords.put(key, meanings);

		key = "coat";
		meanings = new ArrayList <String>();
		meanings.add("garment");
		this.theWords.put(key, meanings);

		key = "coco";
		meanings = new ArrayList <String>();
		meanings.add("clown");
		this.theWords.put(key, meanings);

		key = "cocoa";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);

		key = "cod";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		meanings.add("swimmer");
		this.theWords.put(key, meanings);

		key = "code";
		meanings = new ArrayList <String>();
		meanings.add("cryptic signal");
		meanings.add("morse, perhaps");
		this.theWords.put(key, meanings);

		key = "coe";
		meanings = new ArrayList <String>();
		meanings.add("games organiser");
		meanings.add("Lord");
		this.theWords.put(key, meanings);

		key = "coffee";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);

		key = "coffer";
		meanings = new ArrayList <String>();
		meanings.add("chest");
		this.theWords.put(key, meanings);

		key = "cog";
		meanings = new ArrayList <String>();
		meanings.add("gearwheel");
		this.theWords.put(key, meanings);

		key = "coins";
		meanings = new ArrayList <String>();
		meanings.add("change");
		this.theWords.put(key, meanings);

		key = "cola";
		meanings = new ArrayList <String>();
		meanings.add("soft drink");
		this.theWords.put(key, meanings);

		key = "collapse";
		meanings = new ArrayList <String>();
		meanings.add("fail");
		meanings.add("sudden failure");
		this.theWords.put(key, meanings);

		key = "colt";
		meanings = new ArrayList <String>();
		meanings.add("gun");
		this.theWords.put(key, meanings);

		key = "combat";
		meanings = new ArrayList <String>();
		meanings.add("battle");
		this.theWords.put(key, meanings);

		key = "combine";
		meanings = new ArrayList <String>();
		meanings.add("join together");
		this.theWords.put(key, meanings);

		key = "come";
		meanings = new ArrayList <String>();
		meanings.add("arrive");
		this.theWords.put(key, meanings);

		key = "comets";
		meanings = new ArrayList <String>();
		meanings.add("bright visitors");
		this.theWords.put(key, meanings);

		key = "comic";
		meanings = new ArrayList <String>();
		meanings.add("kind of strip");
		this.theWords.put(key, meanings);

		key = "commo";
		meanings = new ArrayList <String>();
		meanings.add("Italian lake");
		this.theWords.put(key, meanings);

		key = "compact";
		meanings = new ArrayList <String>();
		meanings.add("concise");
		meanings.add("cosmetic case");
		this.theWords.put(key, meanings);

		key = "computers";
		meanings = new ArrayList <String>();
		meanings.add("calculators");
		this.theWords.put(key, meanings);

		key = "con";
		meanings = new ArrayList <String>();
		meanings.add("prisoner");
		meanings.add("study");
		this.theWords.put(key, meanings);

		key = "cone";
		meanings = new ArrayList <String>();
		meanings.add("shape");
		this.theWords.put(key, meanings);

		key = "confined";
		meanings = new ArrayList <String>();
		meanings.add("restricted");
		this.theWords.put(key, meanings);

		key = "considerable";
		meanings = new ArrayList <String>();
		meanings.add("sizeable");
		this.theWords.put(key, meanings);

		key = "container";
		meanings = new ArrayList <String>();
		meanings.add("receptacle");
		this.theWords.put(key, meanings);

		key = "concern";
		meanings = new ArrayList <String>();
		meanings.add("business");
		meanings.add("trouble");
		this.theWords.put(key, meanings);

		key = "concur";
		meanings = new ArrayList <String>();
		meanings.add("see eye-to-eye");
		this.theWords.put(key, meanings);

		key = "content";
		meanings = new ArrayList <String>();
		meanings.add("satisfied");
		meanings.add("what's inside");
		this.theWords.put(key, meanings);

		key = "contest";
		meanings = new ArrayList <String>();
		meanings.add("competition");
		this.theWords.put(key, meanings);

		key = "controversial";
		meanings = new ArrayList <String>();
		meanings.add("disputed");
		this.theWords.put(key, meanings);

		key = "cook";
		meanings = new ArrayList <String>();
		meanings.add("stew");
		this.theWords.put(key, meanings);

		key = "cooker";
		meanings = new ArrayList <String>();
		meanings.add("kitchen appliance");
		this.theWords.put(key, meanings);

		key = "cool";
		meanings = new ArrayList <String>();
		meanings.add("unenthusiastic");
		this.theWords.put(key, meanings);

		key = "cooler";
		meanings = new ArrayList <String>();
		meanings.add("not as hot");
		this.theWords.put(key, meanings);

		key = "coot";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);

		key = "cop";
		meanings = new ArrayList <String>();
		meanings.add("police officer");
		this.theWords.put(key, meanings);

		key = "cope";
		meanings = new ArrayList <String>();
		meanings.add("manage");
		this.theWords.put(key, meanings);

		key = "coping";
		meanings = new ArrayList <String>();
		meanings.add("managing");
		this.theWords.put(key, meanings);

		key = "copper";
		meanings = new ArrayList <String>();
		meanings.add("metal");
		meanings.add("policeman");
		this.theWords.put(key, meanings);

		key = "coppers";
		meanings = new ArrayList <String>();
		meanings.add("police");
		this.theWords.put(key, meanings);

		key = "copse";
		meanings = new ArrayList <String>();
		meanings.add("wood");
		this.theWords.put(key, meanings);

		key = "copy";
		meanings = new ArrayList <String>();
		meanings.add("imitate");
		this.theWords.put(key, meanings);

		key = "core";
		meanings = new ArrayList <String>();
		meanings.add("heart");
		this.theWords.put(key, meanings);

		key = "cores";
		meanings = new ArrayList <String>();
		meanings.add("kernels");
		this.theWords.put(key, meanings);

		key = "coriander";
		meanings = new ArrayList <String>();
		meanings.add("herb");
		this.theWords.put(key, meanings);

		key = "correct";
		meanings = new ArrayList <String>();
		meanings.add("right");
		this.theWords.put(key, meanings);

		key = "cosmic";
		meanings = new ArrayList <String>();
		meanings.add("universal");
		this.theWords.put(key, meanings);

		key = "cost";
		meanings = new ArrayList <String>();
		meanings.add("price");
		this.theWords.put(key, meanings);

		key = "costlier";
		meanings = new ArrayList <String>();
		meanings.add("more expensive");
		this.theWords.put(key, meanings);

		key = "costs";
		meanings = new ArrayList <String>();
		meanings.add("prices");
		this.theWords.put(key, meanings);

		key = "costume";
		meanings = new ArrayList <String>();
		meanings.add("set of clothes");
		this.theWords.put(key, meanings);

		key = "cot";
		meanings = new ArrayList <String>();
		meanings.add("bed");
		meanings.add("small bed");
		this.theWords.put(key, meanings);

		key = "cotter";
		meanings = new ArrayList <String>();
		meanings.add("Highland farmer");
		meanings.add("type of pin");
		this.theWords.put(key, meanings);

		key = "count";
		meanings = new ArrayList <String>();
		meanings.add("continental nobleman");
		meanings.add("sum up");
		this.theWords.put(key, meanings);

		key = "counts";
		meanings = new ArrayList <String>();
		meanings.add("matters");
		meanings.add("nobleman");
		this.theWords.put(key, meanings);

		key = "coventry";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);

		key = "cover";
		meanings = new ArrayList <String>();
		meanings.add("shade");
		this.theWords.put(key, meanings);

		key = "cow";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("beast");
		this.theWords.put(key, meanings);

		key = "cowed";
		meanings = new ArrayList <String>();
		meanings.add("intimidated");
		this.theWords.put(key, meanings);

		key = "cowl";
		meanings = new ArrayList <String>();
		meanings.add("hood");
		meanings.add("head covering");
		this.theWords.put(key, meanings);

		key = "coy";
		meanings = new ArrayList <String>();
		meanings.add("shy");
		this.theWords.put(key, meanings);

		key = "coyote";
		meanings = new ArrayList <String>();
		meanings.add("wild dog");
		this.theWords.put(key, meanings);

		key = "crab";
		meanings = new ArrayList <String>();
		meanings.add("move sideways");
		this.theWords.put(key, meanings);

		key = "crabby";
		meanings = new ArrayList <String>();
		meanings.add("irrascible");
		meanings.add("like a crustacean");
		this.theWords.put(key, meanings);

		key = "crane";
		meanings = new ArrayList <String>();
		meanings.add("lifting machine");
		this.theWords.put(key, meanings);

		key = "cranks";
		meanings = new ArrayList <String>();
		meanings.add("eccentrics");
		this.theWords.put(key, meanings);

		key = "crate";
		meanings = new ArrayList <String>();
		meanings.add("slatted wooden case");
		meanings.add("pack");
		this.theWords.put(key, meanings);

		key = "crack";
		meanings = new ArrayList <String>();
		meanings.add("break");
		this.theWords.put(key, meanings);

		key = "crackers";
		meanings = new ArrayList <String>();
		meanings.add("dry biscuits");
		meanings.add("nuts");
		this.theWords.put(key, meanings);

		key = "crackpot";
		meanings = new ArrayList <String>();
		meanings.add("eccentric person");
		this.theWords.put(key, meanings);

		key = "crayon";
		meanings = new ArrayList <String>();
		meanings.add("coloured pencil");
		meanings.add("pencil");
		this.theWords.put(key, meanings);

		key = "cream";
		meanings = new ArrayList <String>();
		meanings.add("elite");
		this.theWords.put(key, meanings);

		key = "crease";
		meanings = new ArrayList <String>();
		meanings.add("fold");
		this.theWords.put(key, meanings);

		key = "create";
		meanings = new ArrayList <String>();
		meanings.add("make");
		this.theWords.put(key, meanings);

		key = "creek";
		meanings = new ArrayList <String>();
		meanings.add("inlet");
		this.theWords.put(key, meanings);

		key = "crept";
		meanings = new ArrayList <String>();
		meanings.add("moved stealthily");
		this.theWords.put(key, meanings);

		key = "crete";
		meanings = new ArrayList <String>();
		meanings.add("Greek island");
		this.theWords.put(key, meanings);

		key = "crib";
		meanings = new ArrayList <String>();
		meanings.add("baby's bed");
		meanings.add("cheat");
		meanings.add("copy");
		this.theWords.put(key, meanings);

		key = "crime";
		meanings = new ArrayList <String>();
		meanings.add("illegal goings on");
		this.theWords.put(key, meanings);

		key = "crimea";
		meanings = new ArrayList <String>();
		meanings.add("peninsula");
		this.theWords.put(key, meanings);

		key = "crimson";
		meanings = new ArrayList <String>();
		meanings.add("deep red");
		this.theWords.put(key, meanings);

		key = "cringe";
		meanings = new ArrayList <String>();
		meanings.add("cower");
		this.theWords.put(key, meanings);

		key = "crop";
		meanings = new ArrayList <String>();
		meanings.add("cut short");
		meanings.add("rider's whip");
		this.theWords.put(key, meanings);

		key = "cross";
		meanings = new ArrayList <String>();
		meanings.add("annoyed");
		meanings.add("traverse");
		this.theWords.put(key, meanings);

		key = "crossbow";
		meanings = new ArrayList <String>();
		meanings.add("weapon");
		this.theWords.put(key, meanings);

		key = "crow";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);

		key = "crumb";
		meanings = new ArrayList <String>();
		meanings.add("small piece");
		this.theWords.put(key, meanings);

		key = "crude";
		meanings = new ArrayList <String>();
		meanings.add("unrefined");
		this.theWords.put(key, meanings);

		key = "cub";
		meanings = new ArrayList <String>();
		meanings.add("young animal");
		this.theWords.put(key, meanings);

		key = "cuba";
		meanings = new ArrayList <String>();
		meanings.add("island");
		this.theWords.put(key, meanings);

		key = "cuban";
		meanings = new ArrayList <String>();
		meanings.add("islander?");
		this.theWords.put(key, meanings);

		key = "cubans";
		meanings = new ArrayList <String>();
		meanings.add("islanders");
		this.theWords.put(key, meanings);

		key = "cubit";
		meanings = new ArrayList <String>();
		meanings.add("measure");
		this.theWords.put(key, meanings);

		key = "cue";
		meanings = new ArrayList <String>();
		meanings.add("prompt");
		meanings.add("stick");
		this.theWords.put(key, meanings);

		key = "cumbria";
		meanings = new ArrayList <String>();
		meanings.add("county");
		this.theWords.put(key, meanings);

		key = "cup";
		meanings = new ArrayList <String>();
		meanings.add("competition");
		meanings.add("vessel");
		this.theWords.put(key, meanings);

		key = "cupid";
		meanings = new ArrayList <String>();
		meanings.add("deity");
		this.theWords.put(key, meanings);

		key = "cupola";
		meanings = new ArrayList <String>();
		meanings.add("high ceiling");
		this.theWords.put(key, meanings);

		key = "cur";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		meanings.add("mongrel");
		this.theWords.put(key, meanings);

		key = "curate";
		meanings = new ArrayList <String>();
		meanings.add("priest");
		this.theWords.put(key, meanings);

		key = "cure";
		meanings = new ArrayList <String>();
		meanings.add("restore good health");
		meanings.add("a solution");
		meanings.add("preserve");
		meanings.add("remedy");
		this.theWords.put(key, meanings);

		key = "cured";
		meanings = new ArrayList <String>();
		meanings.add("made better");
		this.theWords.put(key, meanings);

		key = "curing";
		meanings = new ArrayList <String>();
		meanings.add("healing");
		this.theWords.put(key, meanings);

		key = "curl";
		meanings = new ArrayList <String>();
		meanings.add("piece of hair");
		meanings.add("weightlifting manoeuvre");
		meanings.add("bend");
		this.theWords.put(key, meanings);

		key = "curs";
		meanings = new ArrayList <String>();
		meanings.add("dogs");
		meanings.add("wretches");
		this.theWords.put(key, meanings);

		key = "curse";
		meanings = new ArrayList <String>();
		meanings.add("use bad language");
		this.theWords.put(key, meanings);

		key = "curt";
		meanings = new ArrayList <String>();
		meanings.add("terse");
		this.theWords.put(key, meanings);

		key = "custer";
		meanings = new ArrayList <String>();
		meanings.add("famous general");
		meanings.add("US general");
		this.theWords.put(key, meanings);

		key = "custom";
		meanings = new ArrayList <String>();
		meanings.add("convention");
		this.theWords.put(key, meanings);

		key = "customer";
		meanings = new ArrayList <String>();
		meanings.add("one who'se always right");
		this.theWords.put(key, meanings);

		key = "customers";
		meanings = new ArrayList <String>();
		meanings.add("shoppers");
		this.theWords.put(key, meanings);

		key = "cut";
		meanings = new ArrayList <String>();
		meanings.add("injured");
		meanings.add("reduced");
		meanings.add("removed");
		this.theWords.put(key, meanings);

		key = "cute";
		meanings = new ArrayList <String>();
		meanings.add("charming");
		this.theWords.put(key, meanings);

		key = "cutlass";
		meanings = new ArrayList <String>();
		meanings.add("weapon");
		this.theWords.put(key, meanings);

		key = "cutlet";
		meanings = new ArrayList <String>();
		meanings.add("portion of meat");
		this.theWords.put(key, meanings);

		key = "cuts";
		meanings = new ArrayList <String>();
		meanings.add("reductions");
		this.theWords.put(key, meanings);

		key = "cyan";
		meanings = new ArrayList <String>();
		meanings.add("colour");
		this.theWords.put(key, meanings);

		key = "cycle";
		meanings = new ArrayList <String>();
		meanings.add("mode of transport");
		this.theWords.put(key, meanings);

		key = "cycles";
		meanings = new ArrayList <String>();
		meanings.add("goes by bike");
		this.theWords.put(key, meanings);

		key = "cyclones";
		meanings = new ArrayList <String>();
		meanings.add("strong winds");
		this.theWords.put(key, meanings);

	}//end loadC
	
	private void loadDWords() {
		String key;
		ArrayList <String> meanings;
		
		//load d's
		key = "dad";
		meanings = new ArrayList <String>();
		meanings.add("father");
		meanings.add("parent");
		this.theWords.put(key, meanings);
		
		key = "dairy";
		meanings = new ArrayList <String>();
		meanings.add("milk factory");
		this.theWords.put(key, meanings);
		
		key = "daisy";
		meanings = new ArrayList <String>();
		meanings.add("flower");
		this.theWords.put(key, meanings);
		
		key = "dale";
		meanings = new ArrayList <String>();
		meanings.add("valley");
		this.theWords.put(key, meanings);
		
		key = "dam";
		meanings = new ArrayList <String>();
		meanings.add("block");
		meanings.add("river barrier");
		this.theWords.put(key, meanings);
		
		key = "damask";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "dame";
		meanings = new ArrayList <String>();
		meanings.add("panto character");
		this.theWords.put(key, meanings);
		
		key = "damn";
		meanings = new ArrayList <String>();
		meanings.add("condemn");
		this.theWords.put(key, meanings);
		
		key = "damsel";
		meanings = new ArrayList <String>();
		meanings.add("young unmarried woman");
		this.theWords.put(key, meanings);
		
		key = "damson";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
				
		key = "dan";
		meanings = new ArrayList <String>();
		meanings.add("desperate man");
		this.theWords.put(key, meanings);
		
		key = "dance";
		meanings = new ArrayList <String>();
		meanings.add("bop");
		this.theWords.put(key, meanings);
		
		key = "dances";
		meanings = new ArrayList <String>();
		meanings.add("bops");
		this.theWords.put(key, meanings);
		
		key = "danes";
		meanings = new ArrayList <String>();
		meanings.add("Europeans");
		this.theWords.put(key, meanings);
		
		key = "danger";
		meanings = new ArrayList <String>();
		meanings.add("peril");
		this.theWords.put(key, meanings);
		
		key = "danish";
		meanings = new ArrayList <String>();
		meanings.add("european");
		this.theWords.put(key, meanings);
				
		key = "dare";
		meanings = new ArrayList <String>();
		meanings.add("challenge");
		this.theWords.put(key, meanings);
		
		key = "daring";
		meanings = new ArrayList <String>();
		meanings.add("adventurous");
		meanings.add("risky");
		this.theWords.put(key, meanings);
		
		key = "darken";
		meanings = new ArrayList <String>();
		meanings.add("cloud over");
		this.theWords.put(key, meanings);
				
		key = "dart";
		meanings = new ArrayList <String>();
		meanings.add("dash");
		this.theWords.put(key, meanings);
		
		key = "darts";
		meanings = new ArrayList <String>();
		meanings.add("game");
		meanings.add("races");
		this.theWords.put(key, meanings);

		key = "data";
		meanings = new ArrayList <String>();
		meanings.add("information");
		this.theWords.put(key, meanings);
		
		key = "date";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		meanings.add("time");
		this.theWords.put(key, meanings);
		
		key = "dates";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "day";
		meanings = new ArrayList <String>();
		meanings.add("24 hours");
		this.theWords.put(key, meanings);
		
		key = "deacons";
		meanings = new ArrayList <String>();
		meanings.add("clergymen");
		this.theWords.put(key, meanings);
		
		key = "dead";
		meanings = new ArrayList <String>();
		meanings.add("expired");
		this.theWords.put(key, meanings);
		
		key = "deadpan";
		meanings = new ArrayList <String>();
		meanings.add("without expression");
		this.theWords.put(key, meanings);
		
		key = "deaf";
		meanings = new ArrayList <String>();
		meanings.add("unable to hear");
		this.theWords.put(key, meanings);
		
		key = "deal";
		meanings = new ArrayList <String>();
		meanings.add("trade");
		meanings.add("transaction");
		this.theWords.put(key, meanings);
		
		key = "dealer";
		meanings = new ArrayList <String>();
		meanings.add("trader");
		this.theWords.put(key, meanings);
				
		key = "dear";
		meanings = new ArrayList <String>();
		meanings.add("regarded with deep affection");
		meanings.add("expensive");
		meanings.add("friendly form of address");
		this.theWords.put(key, meanings);
		
		key = "dears";
		meanings = new ArrayList <String>();
		meanings.add("darlings");
		this.theWords.put(key, meanings);
		
		key = "dearer";
		meanings = new ArrayList <String>();
		meanings.add("more expensive");
		meanings.add("pricier");
		this.theWords.put(key, meanings);
		
		key = "debate";
		meanings = new ArrayList <String>();
		meanings.add("discussion");
		this.theWords.put(key, meanings);
		
		key = "debt";
		meanings = new ArrayList <String>();
		meanings.add("arrears");
		this.theWords.put(key, meanings);
		
		key = "debut";
		meanings = new ArrayList <String>();
		meanings.add("first performance");
		this.theWords.put(key, meanings);
		
		key = "decamp";
		meanings = new ArrayList <String>();
		meanings.add("make off");
		this.theWords.put(key, meanings);
		
		key = "decanter";
		meanings = new ArrayList <String>();
		meanings.add("glass container");
		this.theWords.put(key, meanings);
		
		key = "decide";
		meanings = new ArrayList <String>();
		meanings.add("choose");
		this.theWords.put(key, meanings);
		
		key = "decided";
		meanings = new ArrayList <String>();
		meanings.add("resolute");
		this.theWords.put(key, meanings);
		
		key = "declare";
		meanings = new ArrayList <String>();
		meanings.add("state");
		this.theWords.put(key, meanings);
		
		key = "decline";
		meanings = new ArrayList <String>();
		meanings.add("drop off");
		meanings.add("refuse");
		meanings.add("say no to");
		this.theWords.put(key, meanings);
		
		key = "decode";
		meanings = new ArrayList <String>();
		meanings.add("crack the secret message");
		this.theWords.put(key, meanings);
		
		key = "decor";
		meanings = new ArrayList <String>();
		meanings.add("house style");
		this.theWords.put(key, meanings);
		
		key = "decorum";
		meanings = new ArrayList <String>();
		meanings.add("etiquette");
		this.theWords.put(key, meanings);
		
		key = "dee";
		meanings = new ArrayList <String>();
		meanings.add("river");
		meanings.add("scottish river");
		this.theWords.put(key, meanings);
		
		key = "deed";
		meanings = new ArrayList <String>();
		meanings.add("action");
		meanings.add("document");
		meanings.add("heroic act");
		meanings.add("legal document");
		this.theWords.put(key, meanings);
		
		key = "deep";
		meanings = new ArrayList <String>();
		meanings.add("profound");
		this.theWords.put(key, meanings);
		
		key = "deeper";
		meanings = new ArrayList <String>();
		meanings.add("more profound");
		this.theWords.put(key, meanings);
		
		key = "defiance";
		meanings = new ArrayList <String>();
		meanings.add("bold disobediance");
		this.theWords.put(key, meanings);
		
		key = "defoe";
		meanings = new ArrayList <String>();
		meanings.add("writer");
		this.theWords.put(key, meanings);
		
		key = "degas";
		meanings = new ArrayList <String>();
		meanings.add("French impressionist");
		this.theWords.put(key, meanings);
		
		key = "dehydrate";
		meanings = new ArrayList <String>();
		meanings.add("dry out");
		this.theWords.put(key, meanings);
		
		key = "deli";
		meanings = new ArrayList <String>();
		meanings.add("small delicatessen");
		this.theWords.put(key, meanings);
		
		key = "delta";
		meanings = new ArrayList <String>();
		meanings.add("Greek letter");
		meanings.add("mouth");
		this.theWords.put(key, meanings);
		
		key = "demitasse";
		meanings = new ArrayList <String>();
		meanings.add("cup");
		this.theWords.put(key, meanings);
		
		key = "demo";
		meanings = new ArrayList <String>();
		meanings.add("protest");
		this.theWords.put(key, meanings);
		
		key = "demob";
		meanings = new ArrayList <String>();
		meanings.add("discharge from the army");
		this.theWords.put(key, meanings);
				
		key = "demolish";
		meanings = new ArrayList <String>();
		meanings.add("tear down");
		this.theWords.put(key, meanings);

		key = "demolished";
		meanings = new ArrayList <String>();
		meanings.add("totally ruined");
		this.theWords.put(key, meanings);
		
		key = "den";
		meanings = new ArrayList <String>();
		meanings.add("hideout");
		this.theWords.put(key, meanings);
		
		key = "denote";
		meanings = new ArrayList <String>();
		meanings.add("mark");
		this.theWords.put(key, meanings);
		
		key = "dent";
		meanings = new ArrayList <String>();
		meanings.add("depression");
		meanings.add("impact mark");
		this.theWords.put(key, meanings);
		
		key = "dentures";
		meanings = new ArrayList <String>();
		meanings.add("false teeth");
		this.theWords.put(key, meanings);
		
		key = "deplete";
		meanings = new ArrayList <String>();
		meanings.add("cut back");
		this.theWords.put(key, meanings);
		
		key = "deployed";
		meanings = new ArrayList <String>();
		meanings.add("brought into action");
		this.theWords.put(key, meanings);
		
		key = "deposit";
		meanings = new ArrayList <String>();
		meanings.add("money as security");
		meanings.add("put down");
		meanings.add("sediment");
		this.theWords.put(key, meanings);
		
		key = "depot";
		meanings = new ArrayList <String>();
		meanings.add("warehouse");
		this.theWords.put(key, meanings);
		
		key = "dept";
		meanings = new ArrayList <String>();
		meanings.add("small department");
		this.theWords.put(key, meanings);
		
		key = "deputy";
		meanings = new ArrayList <String>();
		meanings.add("second in command");
		this.theWords.put(key, meanings);
		
		key = "derided";
		meanings = new ArrayList <String>();
		meanings.add("mocked");
		this.theWords.put(key, meanings);
				
		key = "descent";
		meanings = new ArrayList <String>();
		meanings.add("downward movement");
		this.theWords.put(key, meanings);
		
		key = "desert";
		meanings = new ArrayList <String>();
		meanings.add("abandon");
		meanings.add("desolate area");
		meanings.add("sandy area");
		this.theWords.put(key, meanings);
		
		key = "deserts";
		meanings = new ArrayList <String>();
		meanings.add("abandons");
		this.theWords.put(key, meanings);
		
		key = "designer";
		meanings = new ArrayList <String>();
		meanings.add("architect");
		this.theWords.put(key, meanings);

		key = "desire";
		meanings = new ArrayList <String>();
		meanings.add("urge");
		this.theWords.put(key, meanings);

		key = "despair";
		meanings = new ArrayList <String>();
		meanings.add("hopelessness");
		meanings.add("give up hope");
		this.theWords.put(key, meanings);
		
		key = "destroyed";
		meanings = new ArrayList <String>();
		meanings.add("demolished");
		this.theWords.put(key, meanings);
		
		key = "deter";
		meanings = new ArrayList <String>();
		meanings.add("discourage");
		this.theWords.put(key, meanings);
		
		key = "detest";
		meanings = new ArrayList <String>();
		meanings.add("dislike");
		this.theWords.put(key, meanings);

		key = "detrain";
		meanings = new ArrayList <String>();
		meanings.add("alight");
		this.theWords.put(key, meanings);
		
		key = "diagram";
		meanings = new ArrayList <String>();
		meanings.add("drawing");
		this.theWords.put(key, meanings);
		
		key = "diameter";
		meanings = new ArrayList <String>();
		meanings.add("width");
		this.theWords.put(key, meanings);
		
		key = "diamonds";
		meanings = new ArrayList <String>();
		meanings.add("suit");
		this.theWords.put(key, meanings);
		
		key = "diana";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "diatribe";
		meanings = new ArrayList <String>();
		meanings.add("bitter attack");
		this.theWords.put(key, meanings);
		
		key = "did";
		meanings = new ArrayList <String>();
		meanings.add("swindled");
		this.theWords.put(key, meanings);
		
		key = "dido";
		meanings = new ArrayList <String>();
		meanings.add("queen of Carthage");
		this.theWords.put(key, meanings);
		
		key = "diesel";
		meanings = new ArrayList <String>();
		meanings.add("fuel");
		this.theWords.put(key, meanings);
		
		key = "diet";
		meanings = new ArrayList <String>();
		meanings.add("regime");
		this.theWords.put(key, meanings);
		
		key = "diets";
		meanings = new ArrayList <String>();
		meanings.add("regimes");
		this.theWords.put(key, meanings);
		
		key = "dignity";
		meanings = new ArrayList <String>();
		meanings.add("self respect");
		this.theWords.put(key, meanings);
		
		key = "dill";
		meanings = new ArrayList <String>();
		meanings.add("herb");
		this.theWords.put(key, meanings);
				
		key = "diluted";
		meanings = new ArrayList <String>();
		meanings.add("less concentration");
		this.theWords.put(key, meanings);
		
		key = "dime";
		meanings = new ArrayList <String>();
		meanings.add("foreign coin");
		this.theWords.put(key, meanings);
			
		key = "din";
		meanings = new ArrayList <String>();
		meanings.add("noise");
		meanings.add("racket");
		meanings.add("uproar");
		this.theWords.put(key, meanings);
		
		key = "dines";
		meanings = new ArrayList <String>();
		meanings.add("eats");
		this.theWords.put(key, meanings);
		
		key = "dinette";
		meanings = new ArrayList <String>();
		meanings.add("kitchen area");
		this.theWords.put(key, meanings);
				
		key = "ding";
		meanings = new ArrayList <String>();
		meanings.add("noise");
		this.theWords.put(key, meanings);
		
		key = "dingo";
		meanings = new ArrayList <String>();
		meanings.add("wild dog");
		this.theWords.put(key, meanings);
		
		key = "dings";
		meanings = new ArrayList <String>();
		meanings.add("bell sound");
		this.theWords.put(key, meanings);
		
		key = "direct";
		meanings = new ArrayList <String>();
		meanings.add("plain spoken");
		meanings.add("guide");
		this.theWords.put(key, meanings);
		
		key = "director";
		meanings = new ArrayList <String>();
		meanings.add("board member");
		this.theWords.put(key, meanings);
		
		key = "directory";
		meanings = new ArrayList <String>();
		meanings.add("listing");
		this.theWords.put(key, meanings);
		
		key = "dire";
		meanings = new ArrayList <String>();
		meanings.add("dreadful");
		this.theWords.put(key, meanings);
		
		key = "dirge";
		meanings = new ArrayList <String>();
		meanings.add("sad song");
		this.theWords.put(key, meanings);
		
		key = "disc";
		meanings = new ArrayList <String>();
		meanings.add("record");
		this.theWords.put(key, meanings);
		
		key = "disciple";
		meanings = new ArrayList <String>();
		meanings.add("follower");
		this.theWords.put(key, meanings);
		
		key = "disclaim";
		meanings = new ArrayList <String>();
		meanings.add("reject");
		this.theWords.put(key, meanings);
		
		key = "discontinue";
		meanings = new ArrayList <String>();
		meanings.add("stop");
		this.theWords.put(key, meanings);
		
		key = "disembark";
		meanings = new ArrayList <String>();
		meanings.add("get off");
		this.theWords.put(key, meanings);
		
		key = "dish";
		meanings = new ArrayList <String>();
		meanings.add("bowl");
		meanings.add("container");
		this.theWords.put(key, meanings);
		
		key = "disown";
		meanings = new ArrayList <String>();
		meanings.add("abandon");
		this.theWords.put(key, meanings);
		
		key = "dispatch";
		meanings = new ArrayList <String>();
		meanings.add("send off");
		this.theWords.put(key, meanings);
		
		key = "dissent";
		meanings = new ArrayList <String>();
		meanings.add("disagreement");
		this.theWords.put(key, meanings);
		
		key = "distract";
		meanings = new ArrayList <String>();
		meanings.add("divert attention");
		this.theWords.put(key, meanings);
		
		key = "ditch";
		meanings = new ArrayList <String>();
		meanings.add("abandon");
		meanings.add("trench");
		this.theWords.put(key, meanings);
		
		key = "ditherer";
		meanings = new ArrayList <String>();
		meanings.add("irresolute person");
		this.theWords.put(key, meanings);
		
		key = "diva";
		meanings = new ArrayList <String>();
		meanings.add("female star");
		meanings.add("prima donna");
		this.theWords.put(key, meanings);
				
		key = "diver";
		meanings = new ArrayList <String>();
		meanings.add("subsea worker");
		this.theWords.put(key, meanings);
		
		key = "diverting";
		meanings = new ArrayList <String>();
		meanings.add("rerouting");
		this.theWords.put(key, meanings);
		
		key = "do";
		meanings = new ArrayList <String>();
		meanings.add("party");
		meanings.add("work");
		this.theWords.put(key, meanings);
		
		key = "dock";
		meanings = new ArrayList <String>();
		meanings.add("harbour");
		meanings.add("moor");
		meanings.add("weed");
		this.theWords.put(key, meanings);

		key = "dog";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("cur");
		meanings.add("follow");
		meanings.add("trouble");
		this.theWords.put(key, meanings);
		
		key = "dogma";
		meanings = new ArrayList <String>();
		meanings.add("principle");
		this.theWords.put(key, meanings);
		
		key = "dogs";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		this.theWords.put(key, meanings);
				
		key = "dogtired"; //dog tired
		meanings = new ArrayList <String>();
		meanings.add("exhausted");
		this.theWords.put(key, meanings);
		
		key = "doll";
		meanings = new ArrayList <String>();
		meanings.add("toy");
		this.theWords.put(key, meanings);
	
		key = "domain";
		meanings = new ArrayList <String>();
		meanings.add("realm");
		this.theWords.put(key, meanings);
				
		key = "dome";
		meanings = new ArrayList <String>();
		meanings.add("roof");
		this.theWords.put(key, meanings);
		
		key = "don";
		meanings = new ArrayList <String>();
		meanings.add("fellow");
		meanings.add("lecturer");
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "done"; 
		meanings = new ArrayList <String>();
		meanings.add("completed");
		this.theWords.put(key, meanings);
		
		key = "donefor"; //done for
		meanings = new ArrayList <String>();
		meanings.add("ruined");
		this.theWords.put(key, meanings);
		
		key = "donkey";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "donor";
		meanings = new ArrayList <String>();
		meanings.add("benefactor");
		this.theWords.put(key, meanings);
		
		key = "dons";
		meanings = new ArrayList <String>();
		meanings.add("lecturers");
		meanings.add("wears");
		this.theWords.put(key, meanings);
		
		key = "dorset";
		meanings = new ArrayList <String>();
		meanings.add("county");
		this.theWords.put(key, meanings);
		
		key = "dove";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "dover";
		meanings = new ArrayList <String>();
		meanings.add("ferry port");
		meanings.add("port");
		this.theWords.put(key, meanings);
		
		key = "doze";
		meanings = new ArrayList <String>();
		meanings.add("sleep");
		this.theWords.put(key, meanings);
		
		key = "dozen";
		meanings = new ArrayList <String>();
		meanings.add("twelve");
		this.theWords.put(key, meanings);
		
		key = "draft";
		meanings = new ArrayList <String>();
		meanings.add("plan");
		meanings.add("preliminary version");
		this.theWords.put(key, meanings);
		
		key = "drag";
		meanings = new ArrayList <String>();
		meanings.add("something boring");
		this.theWords.put(key, meanings);
		
		key = "dragon";
		meanings = new ArrayList <String>();
		meanings.add("monster");
		this.theWords.put(key, meanings);
		
		key = "dramatists";
		meanings = new ArrayList <String>();
		meanings.add("writers");
		this.theWords.put(key, meanings);
		
		key = "draw";
		meanings = new ArrayList <String>();
		meanings.add("fail to win");
		meanings.add("tie");
		this.theWords.put(key, meanings);
		
		key = "drawaway";//draw away
		meanings = new ArrayList <String>();
		meanings.add("move off");
		this.theWords.put(key, meanings);
		
		key = "drawl";
		meanings = new ArrayList <String>();
		meanings.add("speak slowly");
		this.theWords.put(key, meanings);
		
		key = "dray";
		meanings = new ArrayList <String>();
		meanings.add("cart");
		this.theWords.put(key, meanings);
				
		key = "dread";
		meanings = new ArrayList <String>();
		meanings.add("be afraid");
		meanings.add("fear");
		this.theWords.put(key, meanings);
		
		key = "dreads";
		meanings = new ArrayList <String>();
		meanings.add("fears");
		this.theWords.put(key, meanings);
		
		key = "dream";
		meanings = new ArrayList <String>();
		meanings.add("fantasy");
		this.theWords.put(key, meanings);
		
		key = "dreamup";//dream up
		meanings = new ArrayList <String>();
		meanings.add("conceive");
		this.theWords.put(key, meanings);
		
		key = "dreamy";
		meanings = new ArrayList <String>();
		meanings.add("surreal");
		this.theWords.put(key, meanings);
		
		key = "dress";
		meanings = new ArrayList <String>();
		meanings.add("costume");
		meanings.add("garment");
		this.theWords.put(key, meanings);
		
		key = "dresser";
		meanings = new ArrayList <String>();
		meanings.add("costume designer");
		meanings.add("piece of furniture");
		this.theWords.put(key, meanings);
		
		key = "dries";
		meanings = new ArrayList <String>();
		meanings.add("preserves");
		this.theWords.put(key, meanings);
		
		key = "drift";
		meanings = new ArrayList <String>();
		meanings.add("unpowered motion");
		this.theWords.put(key, meanings);
		
		key = "drill";
		meanings = new ArrayList <String>();
		meanings.add("exercise");
		meanings.add("practice");
		this.theWords.put(key, meanings);
		
		key = "dripping";
		meanings = new ArrayList <String>();
		meanings.add("thoroughly wet");
		meanings.add("fat");
		this.theWords.put(key, meanings);
		
		key = "driver";
		meanings = new ArrayList <String>();
		meanings.add("motorist");
		this.theWords.put(key, meanings);
		
		key = "droll";
		meanings = new ArrayList <String>();
		meanings.add("amusing");
		this.theWords.put(key, meanings);
		
		key = "drover";
		meanings = new ArrayList <String>();
		meanings.add("herdsman");
		this.theWords.put(key, meanings);
		
		key = "drum";
		meanings = new ArrayList <String>();
		meanings.add("instrument");
		this.theWords.put(key, meanings);
	
		key = "dry";
		meanings = new ArrayList <String>();
		meanings.add("not raining");
		meanings.add("uninteresting");
		this.theWords.put(key, meanings);
		
		key = "drydock";
		meanings = new ArrayList <String>();
		meanings.add("where ships are laid up");
		this.theWords.put(key, meanings);
		
		key = "dublin";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "duck";
		meanings = new ArrayList <String>();
		meanings.add("fowl");
		this.theWords.put(key, meanings);
		
		key = "due";
		meanings = new ArrayList <String>();
		meanings.add("expected");
		this.theWords.put(key, meanings);
				
		key = "duenna";
		meanings = new ArrayList <String>();
		meanings.add("chaperone");
		this.theWords.put(key, meanings);
		
		key = "duel";
		meanings = new ArrayList <String>();
		meanings.add("fight");
		this.theWords.put(key, meanings);
		
		key = "duet";
		meanings = new ArrayList <String>();
		meanings.add("twosome");
		this.theWords.put(key, meanings);
				
		key = "dusk";
		meanings = new ArrayList <String>();
		meanings.add("partial darkness");
		meanings.add("twilight");
		this.theWords.put(key, meanings);
		
		key = "dust";
		meanings = new ArrayList <String>();
		meanings.add("clean");
		this.theWords.put(key, meanings);
				
		key = "duty";
		meanings = new ArrayList <String>();
		meanings.add("obligation");
		this.theWords.put(key, meanings);
		
		key = "dweeb";
		meanings = new ArrayList <String>();
		meanings.add("socially inept person");
		this.theWords.put(key, meanings);
		
		key = "dwells";
		meanings = new ArrayList <String>();
		meanings.add("lives");
		this.theWords.put(key, meanings);
		
		key = "dyed";
		meanings = new ArrayList <String>();
		meanings.add("changed colour");
		this.theWords.put(key, meanings);
		
		key = "dynamo";
		meanings = new ArrayList <String>();
		meanings.add("converter");
		this.theWords.put(key, meanings);

	} //end loadD

	private void loadEWords() {
		String key;
		ArrayList <String> meanings;
		
		// load e's
		key = "each";
		meanings = new ArrayList <String>();
		meanings.add("every");
		this.theWords.put(key, meanings);
		
		key = "eager";
		meanings = new ArrayList <String>();
		meanings.add("keen");
		this.theWords.put(key, meanings);
		
		key = "eagle";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "eaglet";
		meanings = new ArrayList <String>();
		meanings.add("baby bird");
		this.theWords.put(key, meanings);
		
		key = "ear";
		meanings = new ArrayList <String>();
		meanings.add("organ");
		meanings.add("part of the head");
		this.theWords.put(key, meanings);
		
		key = "earldom";
		meanings = new ArrayList <String>();
		meanings.add("noble land");
		this.theWords.put(key, meanings);
		
		key = "earlobe";
		meanings = new ArrayList <String>();
		meanings.add("part of lug");
		this.theWords.put(key, meanings);
		
		key = "earned";
		meanings = new ArrayList <String>();
		meanings.add("merited");
		this.theWords.put(key, meanings);
		
		key = "earner";
		meanings = new ArrayList <String>();
		meanings.add("one making money");
		this.theWords.put(key, meanings);
		
		key = "earth";
		meanings = new ArrayList <String>();
		meanings.add("world");
		this.theWords.put(key, meanings);
		
		key = "ease";
		meanings = new ArrayList <String>();
		meanings.add("relax");
		this.theWords.put(key, meanings);
		
		key = "eased";
		meanings = new ArrayList <String>();
		meanings.add("relieved");
		this.theWords.put(key, meanings);
		
		key = "easel";
		meanings = new ArrayList <String>();
		meanings.add("stand");
		this.theWords.put(key, meanings);
		
		key = "eases";
		meanings = new ArrayList <String>();
		meanings.add("reduces");
		this.theWords.put(key, meanings);
				
		key = "east";
		meanings = new ArrayList <String>();
		meanings.add("point");
		this.theWords.put(key, meanings);
		
		key = "eastenders";
		meanings = new ArrayList <String>();
		meanings.add("TV series");
		this.theWords.put(key, meanings);
		
		key = "easter";
		meanings = new ArrayList <String>();
		meanings.add("festival");
		meanings.add("holiday");
		this.theWords.put(key, meanings);
		
		key = "easteregg";
		meanings = new ArrayList <String>();
		meanings.add("seasonal treat");
		this.theWords.put(key, meanings);
		/*
		key = "easters";
		meanings = new ArrayList <String>();
		meanings.add("festivals");
		meanings.add("holidays");
		this.theWords.put(key, meanings);
		*/
		key = "eastern";
		meanings = new ArrayList <String>();
		meanings.add("from the orient");
		meanings.add("oriental");
		this.theWords.put(key, meanings);
		
		key = "eastgermany";// East Germany
		meanings = new ArrayList <String>();
		meanings.add("former state");
		this.theWords.put(key, meanings);
		
		key = "easy";
		meanings = new ArrayList <String>();
		meanings.add("simple");
		this.theWords.put(key, meanings);
		
		key = "eat";
		meanings = new ArrayList <String>();
		meanings.add("consume");
		meanings.add("have dinner");
		this.theWords.put(key, meanings);
		
		key = "eatable";
		meanings = new ArrayList <String>();
		meanings.add("fit to be consumed");
		this.theWords.put(key, meanings);
		
		key = "echidna";
		meanings = new ArrayList <String>();
		meanings.add("anteater");
		this.theWords.put(key, meanings);
		
		key = "echo";
		meanings = new ArrayList <String>();
		meanings.add("repeat");
		this.theWords.put(key, meanings);
		
		key = "eclair";
		meanings = new ArrayList <String>();
		meanings.add("pastry");
		this.theWords.put(key, meanings);
		
		key = "ed";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "edam";
		meanings = new ArrayList <String>();
		meanings.add("Dutch cheese");
		this.theWords.put(key, meanings);
		
		key = "eddy";
		meanings = new ArrayList <String>();
		meanings.add("current");
		this.theWords.put(key, meanings);
		
		key = "eden";
		meanings = new ArrayList <String>();
		meanings.add("Cumbrian river");
		meanings.add("garden");
		this.theWords.put(key, meanings);
				
		key = "edge";
		meanings = new ArrayList <String>();
		meanings.add("advantage");
		this.theWords.put(key, meanings);
		
		key = "edict";
		meanings = new ArrayList <String>();
		meanings.add("order");
		this.theWords.put(key, meanings);
		
		key = "edison";
		meanings = new ArrayList <String>();
		meanings.add("inventor");
		meanings.add("recording pioneer");
		this.theWords.put(key, meanings);
		
		key = "editor";
		meanings = new ArrayList <String>();
		meanings.add("journalist's boss");
		this.theWords.put(key, meanings);
				
		key = "edna";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "eel";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "eels";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
				
		key = "ego";
		meanings = new ArrayList <String>();
		meanings.add("oneself");
		this.theWords.put(key, meanings);
		
		key = "egret";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
				
		key = "elder";
		meanings = new ArrayList <String>();
		meanings.add("not so young");
		meanings.add("tree");
		this.theWords.put(key, meanings);
		
		key = "elf";
		meanings = new ArrayList <String>();
		meanings.add("sprite");
		this.theWords.put(key, meanings);
		
		key = "elfin";
		meanings = new ArrayList <String>();
		meanings.add("small and delicate");
		this.theWords.put(key, meanings);
		
		key = "elfish";
		meanings = new ArrayList <String>();
		meanings.add("like a fairy");
		this.theWords.put(key, meanings);
		
		key = "else";
		meanings = new ArrayList <String>();
		meanings.add("besdies");
		this.theWords.put(key, meanings);
		
		key = "eli";
		meanings = new ArrayList <String>();
		meanings.add("biblical priest");
		this.theWords.put(key, meanings);
		
		key = "eliot";
		meanings = new ArrayList <String>();
		meanings.add("poet");
		this.theWords.put(key, meanings);
		
		key = "elite";
		meanings = new ArrayList <String>();
		meanings.add("best");
		meanings.add("select few");
		this.theWords.put(key, meanings);
		
		key = "elitist";
		meanings = new ArrayList <String>();
		meanings.add("organised for the good of a few");
		this.theWords.put(key, meanings);
		
		key = "epee";
		meanings = new ArrayList <String>();
		meanings.add("fencing weapon");
		this.theWords.put(key, meanings);
		
		key = "epsilon";
		meanings = new ArrayList <String>();
		meanings.add("letter from Greece");
		this.theWords.put(key, meanings);
		
		key = "eight";
		meanings = new ArrayList <String>();
		meanings.add("number");
		this.theWords.put(key, meanings);
		
		key = "eire";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "elaborate";
		meanings = new ArrayList <String>();
		meanings.add("convoluted");
		meanings.add("detailed");
		meanings.add("expand");
		meanings.add("flesh out");
		meanings.add("intricate");
		meanings.add("ornate");
		this.theWords.put(key, meanings);
		
		key = "elaborately";
		meanings = new ArrayList <String>();
		meanings.add("in minute detal");
		this.theWords.put(key, meanings);
		
		key = "elapse";
		meanings = new ArrayList <String>();
		meanings.add("slip away");
		this.theWords.put(key, meanings);
		
		key = "elapsed";
		meanings = new ArrayList <String>();
		meanings.add("passed by");
		this.theWords.put(key, meanings);
				
		key = "elation";
		meanings = new ArrayList <String>();
		meanings.add("high spirits");
		this.theWords.put(key, meanings);
		
		key = "elbe";
		meanings = new ArrayList <String>();
		meanings.add("European flower");
		this.theWords.put(key, meanings);
		
		key = "elbow";
		meanings = new ArrayList <String>();
		meanings.add("part of the body");
		this.theWords.put(key, meanings);
		
		key = "elect";
		meanings = new ArrayList <String>();
		meanings.add("choose");
		this.theWords.put(key, meanings);
		
		key = "electrifies";
		meanings = new ArrayList <String>();
		meanings.add("thrills");
		this.theWords.put(key, meanings);
		
		key = "elegantly";
		meanings = new ArrayList <String>();
		meanings.add("in a graceful manner");
		this.theWords.put(key, meanings);
		
		key = "elephant";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "elevate";
		meanings = new ArrayList <String>();
		meanings.add("uplift");
		this.theWords.put(key, meanings);
		
		key = "elevator";
		meanings = new ArrayList <String>();
		meanings.add("lift");
		this.theWords.put(key, meanings);
		
		key = "eleven";
		meanings = new ArrayList <String>();
		meanings.add("number");
		this.theWords.put(key, meanings);
		
		key = "elgar";
		meanings = new ArrayList <String>();
		meanings.add("composer");
		this.theWords.put(key, meanings);
		
		key = "elk";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("large deer");
		this.theWords.put(key, meanings);
		
		key = "elks";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		this.theWords.put(key, meanings);
		
		key = "elm";
		meanings = new ArrayList <String>();
		meanings.add("tree");
		meanings.add("wood");
		this.theWords.put(key, meanings);
		
		key = "elope";
		meanings = new ArrayList <String>();
		meanings.add("run off together");
		this.theWords.put(key, meanings);
		
		key = "elvis";
		meanings = new ArrayList <String>();
		//meanings.add("king of rock and roll");
		meanings.add("singer");
		this.theWords.put(key, meanings);
		
		key = "eluding";
		meanings = new ArrayList <String>();
		meanings.add("evading");
		this.theWords.put(key, meanings);
		
		key = "emblem";
		meanings = new ArrayList <String>();
		meanings.add("badge");
		this.theWords.put(key, meanings);
		
		key = "embroider";
		meanings = new ArrayList <String>();
		meanings.add("exaggerate details");
		this.theWords.put(key, meanings);
		
		key = "emerald";
		meanings = new ArrayList <String>();
		meanings.add("green");
		this.theWords.put(key, meanings);
		
		key = "emeralds";
		meanings = new ArrayList <String>();
		meanings.add("stones");
		this.theWords.put(key, meanings);
		
		key = "emerges";
		meanings = new ArrayList <String>();
		meanings.add("slowly appears");
		this.theWords.put(key, meanings);
		
		key = "emit";
		meanings = new ArrayList <String>();
		meanings.add("give out");
		meanings.add("send");
		this.theWords.put(key, meanings);
		
		key = "emigrant";
		meanings = new ArrayList <String>();
		meanings.add("traveller");
		this.theWords.put(key, meanings);
		
		key = "emigrates";
		meanings = new ArrayList <String>();
		meanings.add("moves abroad");
		this.theWords.put(key, meanings);
		
		key = "eminent";
		meanings = new ArrayList <String>();
		meanings.add("renowned");
		this.theWords.put(key, meanings);
		
		key = "emirates";
		meanings = new ArrayList <String>();
		meanings.add("arab states");
		this.theWords.put(key, meanings);
		
		key = "emotion";
		meanings = new ArrayList <String>();
		meanings.add("feeling");
		this.theWords.put(key, meanings);
		
		key = "empathise";
		meanings = new ArrayList <String>();
		meanings.add("have feelings for");
		this.theWords.put(key, meanings);
		
		key = "emphasise";
		meanings = new ArrayList <String>();
		meanings.add("underline");
		this.theWords.put(key, meanings);
		
		key = "nero";
		meanings = new ArrayList <String>();
		meanings.add("emperor");
		this.theWords.put(key, meanings);
		
		key = "emu";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "emulate";
		meanings = new ArrayList <String>();
		meanings.add("strive to equal");
		this.theWords.put(key, meanings);
		
		key = "emus";
		meanings = new ArrayList <String>();
		meanings.add("Australian birds");
		this.theWords.put(key, meanings);
		
		key = "enchant";
		meanings = new ArrayList <String>();
		meanings.add("charm");
		meanings.add("dazzle");
		meanings.add("delight");
		this.theWords.put(key, meanings);
		
		key = "end";
		meanings = new ArrayList <String>();
		meanings.add("finish");
		meanings.add("last");
		meanings.add("stop");
		meanings.add("termination");
		this.theWords.put(key, meanings);
		
		key = "ended";
		meanings = new ArrayList <String>();
		meanings.add("finished");
		this.theWords.put(key, meanings);
		
		key = "ending";
		meanings = new ArrayList <String>();
		meanings.add("final");
		this.theWords.put(key, meanings);
		
		key = "endorse";
		meanings = new ArrayList <String>();
		meanings.add("back");
		meanings.add("show support for");
		this.theWords.put(key, meanings);
		
		key = "endow";
		meanings = new ArrayList <String>();
		meanings.add("provide for");
		this.theWords.put(key, meanings);
		
		key = "ends";
		meanings = new ArrayList <String>();
		meanings.add("finishes");
		this.theWords.put(key, meanings);
		
		key = "endure";
		meanings = new ArrayList <String>();
		meanings.add("last");
		this.theWords.put(key, meanings);
		
		key = "engaged";
		meanings = new ArrayList <String>();
		meanings.add("betrothed");
		meanings.add("busy");
		meanings.add("unavailable");
		this.theWords.put(key, meanings);
		
		key = "england";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "engulf";
		meanings = new ArrayList <String>();
		meanings.add("swamp");
		this.theWords.put(key, meanings);
			
		key = "enid";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "enlarge";
		meanings = new ArrayList <String>();
		meanings.add("expand");
		meanings.add("swell");
		this.theWords.put(key, meanings);
		
		key = "enlarges";
		meanings = new ArrayList <String>();
		meanings.add("expands");
		meanings.add("swells");
		this.theWords.put(key, meanings);
		
		key = "enlisted";
		meanings = new ArrayList <String>();
		meanings.add("engaged");
		this.theWords.put(key, meanings);
		
		key = "ennui";
		meanings = new ArrayList <String>();
		meanings.add("boredom");
		this.theWords.put(key, meanings);
		
		key = "enough";
		meanings = new ArrayList <String>();
		meanings.add("sufficent");
		this.theWords.put(key, meanings);
		
		key = "enormous";
		meanings = new ArrayList <String>();
		meanings.add("very large");
		this.theWords.put(key, meanings);
		
		key = "enraged";
		meanings = new ArrayList <String>();
		meanings.add("caused fury");
		this.theWords.put(key, meanings);
		
		key = "enrol";
		meanings = new ArrayList <String>();
		meanings.add("register");
		this.theWords.put(key, meanings);
		
		key = "enroute"; // en route
		meanings = new ArrayList <String>();
		meanings.add("on the way");
		this.theWords.put(key, meanings);
		
		key = "ensue";
		meanings = new ArrayList <String>();
		meanings.add("follow");
		this.theWords.put(key, meanings);
		
		key = "ensuite";
		meanings = new ArrayList <String>();
		meanings.add("with a bathroom");
		this.theWords.put(key, meanings);
		
		key = "ensure";
		meanings = new ArrayList <String>();
		meanings.add("make certain");
		this.theWords.put(key, meanings);
			
		key = "entail";
		meanings = new ArrayList <String>();
		meanings.add("involve");
		this.theWords.put(key, meanings);
	
		key = "enter";
		meanings = new ArrayList <String>();
		meanings.add("record");
		meanings.add("to go into");
		this.theWords.put(key, meanings);
		
		key = "enters";
		meanings = new ArrayList <String>();
		meanings.add("comes in");
		this.theWords.put(key, meanings);
		
		key = "enthral";
		meanings = new ArrayList <String>();
		meanings.add("hold spellbound");
		meanings.add("rivet");
		this.theWords.put(key, meanings);
		
		key = "entice";
		meanings = new ArrayList <String>();
		meanings.add("lure");
		this.theWords.put(key, meanings);
		
		key = "entire";
		meanings = new ArrayList <String>();
		meanings.add("complete");
		this.theWords.put(key, meanings);
		
		key = "entrance";
		meanings = new ArrayList <String>();
		meanings.add("place to go in");
		meanings.add("delight");
		this.theWords.put(key, meanings);
		
		key = "entrap";
		meanings = new ArrayList <String>();
		meanings.add("catch");
		this.theWords.put(key, meanings);
		
		key = "entree";
		meanings = new ArrayList <String>();
		meanings.add("starter");
		this.theWords.put(key, meanings);
		
		key = "enumeration";
		meanings = new ArrayList <String>();
		meanings.add("list");
		this.theWords.put(key, meanings);
		
		key = "environment";
		meanings = new ArrayList <String>();
		meanings.add("habitat");
		this.theWords.put(key, meanings);
		
		key = "envoy";
		meanings = new ArrayList <String>();
		meanings.add("diplomat");
		this.theWords.put(key, meanings);
		
		key = "envy";
		meanings = new ArrayList <String>();
		meanings.add("resent");
		meanings.add("resentment");
		this.theWords.put(key, meanings);
		
		key = "enya";
		meanings = new ArrayList <String>();
		meanings.add("singer");
		this.theWords.put(key, meanings);
		
		key = "epic";
		meanings = new ArrayList <String>();
		meanings.add("surpassing the usual");
		this.theWords.put(key, meanings);
		
		key = "epicentre";
		meanings = new ArrayList <String>();
		meanings.add("momentus");
		meanings.add("surpassing the usual");
		this.theWords.put(key, meanings);
		
		key = "epicures";
		meanings = new ArrayList <String>();
		meanings.add("gourmets");
		this.theWords.put(key, meanings);
		
		key = "equal";
		meanings = new ArrayList <String>();
		meanings.add("match");
		this.theWords.put(key, meanings);
		
		key = "equality";
		meanings = new ArrayList <String>();
		meanings.add("no difference");
		this.theWords.put(key, meanings);
		
		key = "equip";
		meanings = new ArrayList <String>();
		meanings.add("provide kit");
		this.theWords.put(key, meanings);
		
		key = "era";
		meanings = new ArrayList <String>();
		meanings.add("time period");
		meanings.add("age");
		meanings.add("epoch");
		meanings.add("period");
		this.theWords.put(key, meanings);
		
		key = "Erasmus";
		meanings = new ArrayList <String>();
		meanings.add("scholar");
		this.theWords.put(key, meanings);
		
		key = "ere";
		meanings = new ArrayList <String>();
		meanings.add("before");
		this.theWords.put(key, meanings);
		
		key = "eric";
		meanings = new ArrayList <String>();
		meanings.add("boy");
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "erica";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "erne";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "erode";
		meanings = new ArrayList <String>();
		meanings.add("wear down");
		this.theWords.put(key, meanings);
		
		key = "eros";
		meanings = new ArrayList <String>();
		meanings.add("love god");
		this.theWords.put(key, meanings);
		
		key = "err";
		meanings = new ArrayList <String>();
		meanings.add("go wrong");
		meanings.add("stray");
		this.theWords.put(key, meanings);
		
		key = "errand";
		meanings = new ArrayList <String>();
		meanings.add("a job to do");
		this.theWords.put(key, meanings);
		
		key = "erratic";
		meanings = new ArrayList <String>();
		meanings.add("unpredictable");
		meanings.add("unstable");
		this.theWords.put(key, meanings);
		
		key = "error";
		meanings = new ArrayList <String>();
		meanings.add("mistake");
		this.theWords.put(key, meanings);
		
		key = "errors";
		meanings = new ArrayList <String>();
		meanings.add("mistakes");
		this.theWords.put(key, meanings);
		
		key = "eskimo";
		meanings = new ArrayList <String>();
		meanings.add("arctic dweller");
		this.theWords.put(key, meanings);
		
		key = "esoteric";
		meanings = new ArrayList <String>();
		meanings.add("difficult to understand");
		this.theWords.put(key, meanings);
		
		key = "essayist";
		meanings = new ArrayList <String>();
		meanings.add("writer");
		this.theWords.put(key, meanings);
		
		key = "essex";
		meanings = new ArrayList <String>();
		meanings.add("county");
		this.theWords.put(key, meanings);
		
		key = "estate";
		meanings = new ArrayList <String>();
		meanings.add("land");
		meanings.add("wealth");
		this.theWords.put(key, meanings);
		
		key = "estonia";
		meanings = new ArrayList <String>();
		meanings.add("land");
		this.theWords.put(key, meanings);
		
		key = "etc";
		meanings = new ArrayList <String>();
		meanings.add("and so on");
		this.theWords.put(key, meanings);
		
		key = "etch";
		meanings = new ArrayList <String>();
		meanings.add("produce artwork");
		meanings.add("score");
		this.theWords.put(key, meanings);
		
		key = "eternal";
		meanings = new ArrayList <String>();
		meanings.add("everlasting");
		this.theWords.put(key, meanings);
		
		key = "ethos";
		meanings = new ArrayList <String>();
		meanings.add("character of a person");
		meanings.add("moral significance");
		this.theWords.put(key, meanings);
		
		key = "eton";
		meanings = new ArrayList <String>();
		meanings.add("public school");
		meanings.add("school");
		this.theWords.put(key, meanings);
		
		key = "etna";
		meanings = new ArrayList <String>();
		meanings.add("volcano");
		this.theWords.put(key, meanings);
		
		key = "europe";
		meanings = new ArrayList <String>();
		meanings.add("continent");
		meanings.add("group of countries");
		this.theWords.put(key, meanings);
		
		key = "eva";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "eve";
		meanings = new ArrayList <String>();
		meanings.add("first lady");
		meanings.add("first woman");
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "even";
		meanings = new ArrayList <String>();
		meanings.add("flat");
		meanings.add("regular");
		meanings.add("uniform");
		this.theWords.put(key, meanings);
		
		key = "event";
		meanings = new ArrayList <String>();
		meanings.add("occasion");
		meanings.add("something that happens");
		this.theWords.put(key, meanings);
		
		key = "eventide";
		meanings = new ArrayList <String>();
		meanings.add("late in the day");
		this.theWords.put(key, meanings);
		
		key = "ever";
		meanings = new ArrayList <String>();
		meanings.add("always");
		meanings.add("all time");
		this.theWords.put(key, meanings);
		
		key = "evergreen";
		meanings = new ArrayList <String>();
		meanings.add("holly perhaps");
		this.theWords.put(key, meanings);
		
		key = "everton";
		meanings = new ArrayList <String>();
		meanings.add("football club");
		meanings.add("football team");
		this.theWords.put(key, meanings);
		
		key = "everest";
		meanings = new ArrayList <String>();
		meanings.add("mountain");
		this.theWords.put(key, meanings);
		
		key = "evian";
		meanings = new ArrayList <String>();
		meanings.add("spring water");
		this.theWords.put(key, meanings);
		
		key = "evict";
		meanings = new ArrayList <String>();
		meanings.add("kick out");
		this.theWords.put(key, meanings);
		
		key = "evident";
		meanings = new ArrayList <String>();
		meanings.add("obvious");
		this.theWords.put(key, meanings);
		
		key = "evil";
		meanings = new ArrayList <String>();
		meanings.add("fiendish");
		meanings.add("wickedness");
		this.theWords.put(key, meanings);
		
		key = "evils";
		meanings = new ArrayList <String>();
		meanings.add("bad things");
		this.theWords.put(key, meanings);
		
		key = "evoke";
		meanings = new ArrayList <String>();
		meanings.add("call up");
		this.theWords.put(key, meanings);
		
		key = "ewe";
		meanings = new ArrayList <String>();
		meanings.add("sheep");
		this.theWords.put(key, meanings);
		
		key = "ewer";
		meanings = new ArrayList <String>();
		meanings.add("vessel");
		this.theWords.put(key, meanings);
		
		key = "ex";
		meanings = new ArrayList <String>();
		meanings.add("former partner");
		this.theWords.put(key, meanings);
		
		key = "examples";
		meanings = new ArrayList <String>();
		meanings.add("typical specimens");
		this.theWords.put(key, meanings);
		
		key = "exeter";
		meanings = new ArrayList <String>();
		meanings.add("west country");
		this.theWords.put(key, meanings);
		
		key = "exhibition";
		meanings = new ArrayList <String>();
		meanings.add("display");
		this.theWords.put(key, meanings);
		
		key = "exile";
		meanings = new ArrayList <String>();
		meanings.add("banish");
		this.theWords.put(key, meanings);
		
		key = "exiled";
		meanings = new ArrayList <String>();
		meanings.add("banished");
		this.theWords.put(key, meanings);
		
		key = "exit";
		meanings = new ArrayList <String>();
		meanings.add("departure");
		this.theWords.put(key, meanings);
		
		key = "expertise";
		meanings = new ArrayList <String>();
		meanings.add("special skill");
		this.theWords.put(key, meanings);
		
		key = "explain";
		meanings = new ArrayList <String>();
		meanings.add("clear up");
		this.theWords.put(key, meanings);
		
		key = "exploited";
		meanings = new ArrayList <String>();
		meanings.add("made full use of");
		this.theWords.put(key, meanings);
		
		key = "exporter";
		meanings = new ArrayList <String>();
		meanings.add("overseas trader");
		this.theWords.put(key, meanings);
				
		key = "expose";
		meanings = new ArrayList <String>();
		meanings.add("unmask");
		this.theWords.put(key, meanings);
		
		key = "external";
		meanings = new ArrayList <String>();
		meanings.add("outside");
		this.theWords.put(key, meanings);
		
		key = "extra";
		meanings = new ArrayList <String>();
		meanings.add("additional feature");
		meanings.add("casual actor");
		meanings.add("more");
		this.theWords.put(key, meanings);
				
		key = "exude";
		meanings = new ArrayList <String>();
		meanings.add("give off");
		this.theWords.put(key, meanings);

		key = "eye";
		meanings = new ArrayList <String>();
		meanings.add("look at");
		meanings.add("observe");
		meanings.add("part of a needle");
		meanings.add("see");
		meanings.add("view");
		this.theWords.put(key, meanings);
		
		key = "eyelet";
		meanings = new ArrayList <String>();
		meanings.add("small hole");
		meanings.add("small round hole");
		this.theWords.put(key, meanings);
		
		key = "eyelid";
		meanings = new ArrayList <String>();
		meanings.add("facial feature");
		this.theWords.put(key, meanings);
				
		key = "eyeliner";
		meanings = new ArrayList <String>();
		meanings.add("cosmetic");
		this.theWords.put(key, meanings);

	}//end loadE
	
	private void loadFWords() {
		String key;
		ArrayList <String> meanings;
		
		// load f's
		key = "fable";
		meanings = new ArrayList <String>();
		meanings.add("legend");
		meanings.add("story");
		this.theWords.put(key, meanings);
		
		key = "fab";
		meanings = new ArrayList <String>();
		meanings.add("super");
		this.theWords.put(key, meanings);
		
		key = "fabric";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "fact";
		meanings = new ArrayList <String>();
		meanings.add("truth");
		this.theWords.put(key, meanings);
		
		key = "factory";
		meanings = new ArrayList <String>();
		meanings.add("production unit");
		this.theWords.put(key, meanings);
				
		key = "fade";
		meanings = new ArrayList <String>();
		meanings.add("grow faint");
		this.theWords.put(key, meanings);
		
		key = "fag";
		meanings = new ArrayList <String>();
		meanings.add("cigarette");
		this.theWords.put(key, meanings);
		
		key = "fail";
		meanings = new ArrayList <String>();
		meanings.add("don't pass");
		meanings.add("flop");
		this.theWords.put(key, meanings);
		
		key = "failing";
		meanings = new ArrayList <String>();
		meanings.add("flopping");
		meanings.add("not passing");
		meanings.add("not suceeding");
		this.theWords.put(key, meanings);
		
		key = "fair";
		meanings = new ArrayList <String>();
		meanings.add("just");
		this.theWords.put(key, meanings);
		
		key = "fan";
		meanings = new ArrayList <String>();
		meanings.add("cooler");
		meanings.add("supporter");
		this.theWords.put(key, meanings);
		
		key = "fanfare";
		meanings = new ArrayList <String>();
		meanings.add("flourish");
		this.theWords.put(key, meanings);
		
		key = "fantail";
		meanings = new ArrayList <String>();
		meanings.add("pigeon");
		this.theWords.put(key, meanings);
		
		key = "far";
		meanings = new ArrayList <String>();
		meanings.add("distant");
		this.theWords.put(key, meanings);
		
		key = "fare";
		meanings = new ArrayList <String>();
		meanings.add("food");
		this.theWords.put(key, meanings);
		
		key = "farm";
		meanings = new ArrayList <String>();
		meanings.add("grange");
		this.theWords.put(key, meanings);
		
		key = "father";
		meanings = new ArrayList <String>();
		meanings.add("parent");
		this.theWords.put(key, meanings);
		
		key = "fast";
		meanings = new ArrayList <String>();
		meanings.add("fix");
		meanings.add("not eating");
		meanings.add("secured");
		meanings.add("very quick");
		this.theWords.put(key, meanings);
		
		key = "faster";
		meanings = new ArrayList <String>();
		meanings.add("more quickly");
		this.theWords.put(key, meanings);
		
		key = "fat";
		meanings = new ArrayList <String>();
		meanings.add("corpulent");
		meanings.add("oily substance");
		meanings.add("plump");
		this.theWords.put(key, meanings);
		
		key = "fate";
		meanings = new ArrayList <String>();
		meanings.add("destiny");
		this.theWords.put(key, meanings);
				
		key = "fatherly";
		meanings = new ArrayList <String>();
		meanings.add("protective and affectionate");
		this.theWords.put(key, meanings);
		
		key = "fault";
		meanings = new ArrayList <String>();
		meanings.add("error");
		this.theWords.put(key, meanings);
		
		key = "favour";
		meanings = new ArrayList <String>();
		meanings.add("kind act");
		this.theWords.put(key, meanings);
		
		key = "feat";
		meanings = new ArrayList <String>();
		meanings.add("achievement");
		meanings.add("accomplishment");
		meanings.add("heroic deed");
		this.theWords.put(key, meanings);
		
		key = "fears";
		meanings = new ArrayList <String>();
		meanings.add("dreads");
		this.theWords.put(key, meanings);
		
		key = "feast";
		meanings = new ArrayList <String>();
		meanings.add("large meal");
		this.theWords.put(key, meanings);
				
		key = "fed";
		meanings = new ArrayList <String>();
		meanings.add("gave food");
		this.theWords.put(key, meanings);
		
		key = "fee";
		meanings = new ArrayList <String>();
		meanings.add("charge");
		this.theWords.put(key, meanings);
		
		key = "feeble";
		meanings = new ArrayList <String>();
		meanings.add("lacking in strength");
		this.theWords.put(key, meanings);
		
		key = "feel";
		meanings = new ArrayList <String>();
		meanings.add("sense");
		this.theWords.put(key, meanings);
		
		key = "felon";
		meanings = new ArrayList <String>();
		meanings.add("criminal");
		this.theWords.put(key, meanings);
		
		key = "femur";
		meanings = new ArrayList <String>();
		meanings.add("bone");
		this.theWords.put(key, meanings);
		
		key = "fencing";
		meanings = new ArrayList <String>();
		meanings.add("sport");
		this.theWords.put(key, meanings);
		
		key = "fervent";
		meanings = new ArrayList <String>();
		meanings.add("passionate");
		this.theWords.put(key, meanings);
		
		key = "feta";
		meanings = new ArrayList <String>();
		meanings.add("cheese");
		this.theWords.put(key, meanings);
		
		key = "fetch";
		meanings = new ArrayList <String>();
		meanings.add("get");
		this.theWords.put(key, meanings);
		
		key = "fetching";
		meanings = new ArrayList <String>();
		meanings.add("getting");
		meanings.add("attractive");
		this.theWords.put(key, meanings);
		
		key = "fete";
		meanings = new ArrayList <String>();
		meanings.add("gala");
		meanings.add("party");
		this.theWords.put(key, meanings);
		
		key = "fever";
		meanings = new ArrayList <String>();
		meanings.add("high temperature");
		meanings.add("hot feeling");
		this.theWords.put(key, meanings);
		
		key = "fiance";
		meanings = new ArrayList <String>();
		meanings.add("intended");
		meanings.add("gromm-to-be");
		this.theWords.put(key, meanings);
			
		key = "fib";
		meanings = new ArrayList <String>();
		meanings.add("lie");
		this.theWords.put(key, meanings);
		
		key = "fiction";
		meanings = new ArrayList <String>();
		meanings.add("literary genre");
		this.theWords.put(key, meanings);
		
		key = "fiddle";
		meanings = new ArrayList <String>();
		meanings.add("tinker with");
		meanings.add("instrument");
		this.theWords.put(key, meanings);
		
		key = "fidget";
		meanings = new ArrayList <String>();
		meanings.add("wriggle");
		this.theWords.put(key, meanings);

		key = "field";
		meanings = new ArrayList <String>();
		meanings.add("meadow");
		this.theWords.put(key, meanings);

		key = "fiend";
		meanings = new ArrayList <String>();
		meanings.add("devil");
		this.theWords.put(key, meanings);
		
		key = "fiesta";
		meanings = new ArrayList <String>();
		meanings.add("foreign festival");
		this.theWords.put(key, meanings);
		
		key = "films";
		meanings = new ArrayList <String>();
		meanings.add("pictures");
		this.theWords.put(key, meanings);
		
		key = "finance";
		meanings = new ArrayList <String>();
		meanings.add("money");
		this.theWords.put(key, meanings);
	
		key = "fin";
		meanings = new ArrayList <String>();
		meanings.add("swimming aid");
		this.theWords.put(key, meanings);
		
		key = "final";
		meanings = new ArrayList <String>();
		meanings.add("last");
		this.theWords.put(key, meanings);
		
		key = "find";
		meanings = new ArrayList <String>();
		meanings.add("come across");
		this.theWords.put(key, meanings);
		
		key = "fine";
		meanings = new ArrayList <String>();
		meanings.add("excellent");
		meanings.add("punishment");
		this.theWords.put(key, meanings);
		
		key = "fined";
		meanings = new ArrayList <String>();
		meanings.add("given penalty");
		this.theWords.put(key, meanings);
		
		key = "finn";
		meanings = new ArrayList <String>();
		meanings.add("european");
		this.theWords.put(key, meanings);
				
		key = "fir";
		meanings = new ArrayList <String>();
		meanings.add("evergreen");
		this.theWords.put(key, meanings);
		
		key = "fire";
		meanings = new ArrayList <String>();
		meanings.add("dismiss");
		this.theWords.put(key, meanings);
				
		key = "firem";
		meanings = new ArrayList <String>();
		meanings.add("business");
		this.theWords.put(key, meanings);
		
		key = "fireman";
		meanings = new ArrayList <String>();
		meanings.add("emergency worker");
		this.theWords.put(key, meanings);
		
		key = "fireside";
		meanings = new ArrayList <String>();
		meanings.add("somewhere warm");
		this.theWords.put(key, meanings);
		
		key = "firm";
		meanings = new ArrayList <String>();
		meanings.add("business");
		meanings.add("company");
		meanings.add("partnership");
		meanings.add("secure");
		meanings.add("settled");
		this.theWords.put(key, meanings);
		
		key = "first";
		meanings = new ArrayList <String>();
		meanings.add("leading");
		this.theWords.put(key, meanings);
		
		key = "fish";
		meanings = new ArrayList <String>();
		meanings.add("swimmer");
		this.theWords.put(key, meanings);
		
		key = "fishguard";
		meanings = new ArrayList <String>();
		meanings.add("port");
		this.theWords.put(key, meanings);
		
		key = "fit";
		meanings = new ArrayList <String>();
		meanings.add("appropriate");
		meanings.add("in good condition");
		meanings.add("install");
		meanings.add("suitable");
		this.theWords.put(key, meanings);
		
		key = "fitness";
		meanings = new ArrayList <String>();
		meanings.add("physical well-being");
		this.theWords.put(key, meanings);
		
		key = "flag";
		meanings = new ArrayList <String>();
		meanings.add("banner");
		this.theWords.put(key, meanings);
		
		key = "flail";
		meanings = new ArrayList <String>();
		meanings.add("wave wildly");
		this.theWords.put(key, meanings);
		
		key = "flair";
		meanings = new ArrayList <String>();
		meanings.add("natural ability");
		meanings.add("talent");
		this.theWords.put(key, meanings);
		
		key = "flaming";
		meanings = new ArrayList <String>();
		meanings.add("burning");
		this.theWords.put(key, meanings);
		
		key = "flamingo";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "flan";
		meanings = new ArrayList <String>();
		meanings.add("dish");
		meanings.add("sweet");
		this.theWords.put(key, meanings);
		
		key = "flank";
		meanings = new ArrayList <String>();
		meanings.add("side");
		this.theWords.put(key, meanings);
				
		key = "flask";
		meanings = new ArrayList <String>();
		meanings.add("thermos");
		this.theWords.put(key, meanings);
		
		key = "flatter";
		meanings = new ArrayList <String>();
		meanings.add("give insincere praise");
		meanings.add("less sparkling");
		this.theWords.put(key, meanings);
		
		key = "flattery";
		meanings = new ArrayList <String>();
		meanings.add("soft soap");
		this.theWords.put(key, meanings);
		
		key = "flavour";
		meanings = new ArrayList <String>();
		meanings.add("distinctive taste");
		this.theWords.put(key, meanings);
		
		key = "fleece";
		meanings = new ArrayList <String>();
		meanings.add("rip off");
		meanings.add("coat");
		this.theWords.put(key, meanings);
		
		key = "fleet";
		meanings = new ArrayList <String>();
		meanings.add("fast");
		meanings.add("group of trucks");
		this.theWords.put(key, meanings);
		
		key = "fliers";
		meanings = new ArrayList <String>();
		meanings.add("leaflets");
		this.theWords.put(key, meanings);
		
		key = "fling";
		meanings = new ArrayList <String>();
		meanings.add("cast");
		this.theWords.put(key, meanings);
		
		key = "flints";
		meanings = new ArrayList <String>();
		meanings.add("stones");
		this.theWords.put(key, meanings);
		
		key = "flip";
		meanings = new ArrayList <String>();
		meanings.add("hit the roof");
		this.theWords.put(key, meanings);
		
		key = "flippant";
		meanings = new ArrayList <String>();
		meanings.add("frivolous");
		this.theWords.put(key, meanings);
		
		key = "flimsy";
		meanings = new ArrayList <String>();
		meanings.add("weak");
		this.theWords.put(key, meanings);
		
		key = "flirt";
		meanings = new ArrayList <String>();
		meanings.add("make advances");
		this.theWords.put(key, meanings);
		
		key = "flit";
		meanings = new ArrayList <String>();
		meanings.add("move swiftly");
		this.theWords.put(key, meanings);
		
		key = "float";
		meanings = new ArrayList <String>();
		meanings.add("levitate");
		this.theWords.put(key, meanings);
				
		key = "flog";
		meanings = new ArrayList <String>();
		meanings.add("thrash");
		this.theWords.put(key, meanings);
		
		key = "flower";
		meanings = new ArrayList <String>();
		meanings.add("bloom");
		this.theWords.put(key, meanings);
		
		key = "flowing";
		meanings = new ArrayList <String>();
		meanings.add("uninterrupted");
		this.theWords.put(key, meanings);
		
		key = "flung";
		meanings = new ArrayList <String>();
		meanings.add("threw");
		this.theWords.put(key, meanings);
		
		key = "fluster";
		meanings = new ArrayList <String>();
		meanings.add("turmoil");
		this.theWords.put(key, meanings);
		
		key = "flute";
		meanings = new ArrayList <String>();
		meanings.add("instrument");
		meanings.add("tall glass");
		this.theWords.put(key, meanings);
		
		key = "fly";
		meanings = new ArrayList <String>();
		meanings.add("creature");
		meanings.add("cunning");
		this.theWords.put(key, meanings);
		
		key = "flytrap";
		meanings = new ArrayList <String>();
		meanings.add("insect catcher");
		this.theWords.put(key, meanings);

		key = "foe";
		meanings = new ArrayList <String>();
		meanings.add("enemy");
		this.theWords.put(key, meanings);
		
		key = "fog";
		meanings = new ArrayList <String>();
		meanings.add("confusion");
		this.theWords.put(key, meanings);
		
		key = "foil";
		meanings = new ArrayList <String>();
		meanings.add("prevent");
		meanings.add("sword");
		meanings.add("thwart");
		this.theWords.put(key, meanings);
		
		key = "foliage";
		meanings = new ArrayList <String>();
		meanings.add("leaves");
		this.theWords.put(key, meanings);
		
		key = "folio";
		meanings = new ArrayList <String>();
		meanings.add("leaf");
		meanings.add("page of a book");
		meanings.add("sheet");
		this.theWords.put(key, meanings);
				
		key = "food";
		meanings = new ArrayList <String>();
		meanings.add("sustenance");
		this.theWords.put(key, meanings);
		
		key = "fool";
		meanings = new ArrayList <String>();
		meanings.add("idiot");
		this.theWords.put(key, meanings);
		
		key = "for";
		meanings = new ArrayList <String>();
		meanings.add("in favour of");
		this.theWords.put(key, meanings);
		
		key = "forage";
		meanings = new ArrayList <String>();
		meanings.add("search for");
		this.theWords.put(key, meanings);
		
		key = "forbid";
		meanings = new ArrayList <String>();
		meanings.add("prohibit");
		this.theWords.put(key, meanings);
		
		key = "force";
		meanings = new ArrayList <String>();
		meanings.add("might");
		meanings.add("power");
		this.theWords.put(key, meanings);
			
		key = "forced";
		meanings = new ArrayList <String>();
		meanings.add("appear unnatural");
		this.theWords.put(key, meanings);
		
		key = "forces";
		meanings = new ArrayList <String>();
		meanings.add("troops");
		this.theWords.put(key, meanings);
				
		key = "ford";
		meanings = new ArrayList <String>();
		meanings.add("water");
		this.theWords.put(key, meanings);
				
		key = "foreign";
		meanings = new ArrayList <String>();
		meanings.add("from abroad");
		this.theWords.put(key, meanings);
		
		key = "forest";
		meanings = new ArrayList <String>();
		meanings.add("wooodland");
		this.theWords.put(key, meanings);
		
		key = "forum";
		meanings = new ArrayList <String>();
		meanings.add("discussion group");
		this.theWords.put(key, meanings);
		
		key = "for";
		meanings = new ArrayList <String>();
		meanings.add("in favour");
		meanings.add("in favour of");
		this.theWords.put(key, meanings);
		
		key = "foreman";
		meanings = new ArrayList <String>();
		meanings.add("supervisor");
		this.theWords.put(key, meanings);
		
		key = "formal";
		meanings = new ArrayList <String>();
		meanings.add("bona fide");
		this.theWords.put(key, meanings);
		
		key = "form";
		meanings = new ArrayList <String>();
		meanings.add("shape");
		this.theWords.put(key, meanings);
		
		key = "former";
		meanings = new ArrayList <String>();
		meanings.add("previous");
		this.theWords.put(key, meanings);
		
		key = "fortress";
		meanings = new ArrayList <String>();
		meanings.add("castle");
		this.theWords.put(key, meanings);
		
		key = "fortune";
		meanings = new ArrayList <String>();
		meanings.add("huge amount of money");
		meanings.add("lot of money");
		this.theWords.put(key, meanings);
				
		key = "forwards";
		meanings = new ArrayList <String>();
		meanings.add("ahead");
		this.theWords.put(key, meanings);
		
		key = "found";
		meanings = new ArrayList <String>();
		meanings.add("discovered");
		this.theWords.put(key, meanings);
				
		key = "four";
		meanings = new ArrayList <String>();
		meanings.add("quartet");
		this.theWords.put(key, meanings);
		
		key = "fourteen";
		meanings = new ArrayList <String>();
		meanings.add("figure");
		this.theWords.put(key, meanings);
		
		key = "foyer";
		meanings = new ArrayList <String>();
		meanings.add("entrance hall");
		this.theWords.put(key, meanings);
		
		key = "france";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);

		key = "francis";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "fray";
		meanings = new ArrayList <String>();
		meanings.add("disturbance");
		this.theWords.put(key, meanings);
		
		key = "free";
		meanings = new ArrayList <String>();
		meanings.add("for nothing");
		this.theWords.put(key, meanings);
		
		key = "freely";
		meanings = new ArrayList <String>();
		meanings.add("without compulsion");
		this.theWords.put(key, meanings);
		
		key = "fresco";
		meanings = new ArrayList <String>();
		meanings.add("painting on wall");
		this.theWords.put(key, meanings);
		
		key = "fresh";
		meanings = new ArrayList <String>();
		meanings.add("new");
		this.theWords.put(key, meanings);
		
		key = "friction";
		meanings = new ArrayList <String>();
		meanings.add("antagonism");
		this.theWords.put(key, meanings);
		
		key = "fridge";
		meanings = new ArrayList <String>();
		meanings.add("cooler");
		this.theWords.put(key, meanings);
		
		key = "freight";
		meanings = new ArrayList <String>();
		meanings.add("cargo");
		this.theWords.put(key, meanings);
		
		key = "friend";
		meanings = new ArrayList <String>();
		meanings.add("pal");
		this.theWords.put(key, meanings);
		
		key = "frog";
		meanings = new ArrayList <String>();
		meanings.add("amphibian");
		this.theWords.put(key, meanings);
		
		key = "fry";
		meanings = new ArrayList <String>();
		meanings.add("cook");
		this.theWords.put(key, meanings);
		
		key = "fulcrum";
		meanings = new ArrayList <String>();
		meanings.add("pivotal point");
		this.theWords.put(key, meanings);
		
		key = "full";
		meanings = new ArrayList <String>();
		meanings.add("complete");
		this.theWords.put(key, meanings);
		
		key = "fulmar";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "fun";
		meanings = new ArrayList <String>();
		meanings.add("amusing");
		this.theWords.put(key, meanings);
		
		key = "fungi";
		meanings = new ArrayList <String>();
		meanings.add("mushrooms");
		this.theWords.put(key, meanings);
		
		key = "funny";
		meanings = new ArrayList <String>();
		meanings.add("peculiar");
		this.theWords.put(key, meanings);
		
		key = "futon";
		meanings = new ArrayList <String>();
		meanings.add("bed");
		this.theWords.put(key, meanings);
		
		key = "fur";
		meanings = new ArrayList <String>();
		meanings.add("hairy coat");
		this.theWords.put(key, meanings);
		
		key = "furrows";
		meanings = new ArrayList <String>();
		meanings.add("wrinkles");
		this.theWords.put(key, meanings);

	}//end loadF
	
	private void loadGWords() {
		String key;
		ArrayList <String> meanings;
		
		// load g's
		key = "gable";
		meanings = new ArrayList <String>();
		meanings.add("part of roof");
		this.theWords.put(key, meanings);
		
		key = "gadget";
		meanings = new ArrayList <String>();
		meanings.add("thing");
		this.theWords.put(key, meanings);
		
		key = "gag";
		meanings = new ArrayList <String>();
		meanings.add("heave");
		meanings.add("muzzle");
		meanings.add("muffle");
		meanings.add("silence");
		meanings.add("stifle");
		this.theWords.put(key, meanings);
		
		key = "gal";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "gale";
		meanings = new ArrayList <String>();
		meanings.add("high wind");
		this.theWords.put(key, meanings);
		
		key = "gallery";
		meanings = new ArrayList <String>();
		meanings.add("exhibition room");
		this.theWords.put(key, meanings);
		
		key = "gallops";
		meanings = new ArrayList <String>();
		meanings.add("runs");
		this.theWords.put(key, meanings);
		
		key = "game";
		meanings = new ArrayList <String>();
		meanings.add("match");
		this.theWords.put(key, meanings);
		
		key = "gamut";
		meanings = new ArrayList <String>();
		meanings.add("full extent");
		this.theWords.put(key, meanings);
				
		key = "gander";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		meanings.add("quick look at");
		this.theWords.put(key, meanings);
		
		key = "gap";
		meanings = new ArrayList <String>();
		meanings.add("space");
		this.theWords.put(key, meanings);
		
		key = "gape";
		meanings = new ArrayList <String>();
		meanings.add("stare");
		this.theWords.put(key, meanings);
		
		key = "garage";
		meanings = new ArrayList <String>();
		meanings.add("car shop");
		this.theWords.put(key, meanings);
		
		key = "garb";
		meanings = new ArrayList <String>();
		meanings.add("clothes");
		meanings.add("clothing");
		this.theWords.put(key, meanings);
		
		key = "garbled";
		meanings = new ArrayList <String>();
		meanings.add("mixed up");
		this.theWords.put(key, meanings);
		
		key = "gardener";
		meanings = new ArrayList <String>();
		meanings.add("one who grows");
		this.theWords.put(key, meanings);
		
		key = "garnish";
		meanings = new ArrayList <String>();
		meanings.add("embellishment");
		this.theWords.put(key, meanings);
		
		key = "gas";
		meanings = new ArrayList <String>();
		meanings.add("in America petrol");
		meanings.add("talk on");
		this.theWords.put(key, meanings);
				
		key = "gash";
		meanings = new ArrayList <String>();
		meanings.add("cut");
		this.theWords.put(key, meanings);
		
		key = "gate";
		meanings = new ArrayList <String>();
		meanings.add("number of spectators");
		this.theWords.put(key, meanings);
				
		key = "gatsby";
		meanings = new ArrayList <String>();
		meanings.add("great fictional character");
		this.theWords.put(key, meanings);
		
		key = "gave";
		meanings = new ArrayList <String>();
		meanings.add("donated");
		this.theWords.put(key, meanings);
		
		key = "gear";
		meanings = new ArrayList <String>();
		meanings.add("equipment");
		meanings.add("garb");
		this.theWords.put(key, meanings);
		
		key = "gears";
		meanings = new ArrayList <String>();
		meanings.add("part of bike");
		this.theWords.put(key, meanings);
		
		key = "gem";
		meanings = new ArrayList <String>();
		meanings.add("stone");
		this.theWords.put(key, meanings);
		
		key = "gemini";
		meanings = new ArrayList <String>();
		meanings.add("sign");
		meanings.add("twins");
		this.theWords.put(key, meanings);
		
		key = "gems";
		meanings = new ArrayList <String>();
		meanings.add("diamonds");
		this.theWords.put(key, meanings);
		
		key = "gemstone";
		meanings = new ArrayList <String>();
		meanings.add("precious object");
		this.theWords.put(key, meanings);
		
		key = "gen";
		meanings = new ArrayList <String>();
		meanings.add("information");
		this.theWords.put(key, meanings);
		
		key = "general";
		meanings = new ArrayList <String>();
		meanings.add("officer");
		this.theWords.put(key, meanings);
		
		key = "generals";
		meanings = new ArrayList <String>();
		meanings.add("officers");
		this.theWords.put(key, meanings);
		
		key = "geneva";
		meanings = new ArrayList <String>();
		meanings.add("city");
		meanings.add("Swiss city");
		this.theWords.put(key, meanings);
		
		key = "genial";
		meanings = new ArrayList <String>();
		meanings.add("sociable");
		this.theWords.put(key, meanings);
		
		key = "genoa";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "gent";
		meanings = new ArrayList <String>();
		meanings.add("bloke");
		meanings.add("chap");
		meanings.add("man");
		meanings.add("well-mannered chap");
		this.theWords.put(key, meanings);
		
		key = "geraniums";
		meanings = new ArrayList <String>();
		meanings.add("flowers");
		this.theWords.put(key, meanings);
		
		key = "germans";
		meanings = new ArrayList <String>();
		meanings.add("Europeans");
		this.theWords.put(key, meanings);
		
		key = "get";
		meanings = new ArrayList <String>();
		meanings.add("obtain");
		this.theWords.put(key, meanings);
		
		key = "gets";
		meanings = new ArrayList <String>();
		meanings.add("obtains");
		this.theWords.put(key, meanings);
				
		key = "gifted";
		meanings = new ArrayList <String>();
		meanings.add("talented");
		this.theWords.put(key, meanings);
		
		key = "gig";
		meanings = new ArrayList <String>();
		meanings.add("carriage");
		this.theWords.put(key, meanings);
		
		key = "gigantic";
		meanings = new ArrayList <String>();
		meanings.add("huge");
		this.theWords.put(key, meanings);
		
		key = "gill";
		meanings = new ArrayList <String>();
		meanings.add("small amount of drink");
		this.theWords.put(key, meanings);
		
		key = "gin";
		meanings = new ArrayList <String>();
		meanings.add("card game");
		meanings.add("drink");
		meanings.add("spirit");
		this.theWords.put(key, meanings);
		
		key = "gins";
		meanings = new ArrayList <String>();
		meanings.add("drinks");
		meanings.add("spirits");
		this.theWords.put(key, meanings);
		
		key = "girl";
		meanings = new ArrayList <String>();
		meanings.add("young woman");
		this.theWords.put(key, meanings);
		
		key = "girls";
		meanings = new ArrayList <String>();
		meanings.add("young women");
		this.theWords.put(key, meanings);
		
		key = "gite";
		meanings = new ArrayList <String>();
		meanings.add("french cottage");
		this.theWords.put(key, meanings);

		key = "given";
		meanings = new ArrayList <String>();
		meanings.add("donated");
		this.theWords.put(key, meanings);
		
		key = "glacier";
		meanings = new ArrayList <String>();
		meanings.add("mass of ice");
		this.theWords.put(key, meanings);
		
		key = "glad";
		meanings = new ArrayList <String>();
		meanings.add("happy");
		meanings.add("pleased");
		this.theWords.put(key, meanings);
		
		key = "gladden";
		meanings = new ArrayList <String>();
		meanings.add("please");
		this.theWords.put(key, meanings);
		
		key = "glade";
		meanings = new ArrayList <String>();
		meanings.add("clearing");
		this.theWords.put(key, meanings);
				
		key = "glance";
		meanings = new ArrayList <String>();
		meanings.add("quick look");
		this.theWords.put(key, meanings);

		key = "gland";
		meanings = new ArrayList <String>();
		meanings.add("organ");
		this.theWords.put(key, meanings);
		
		key = "gleam";
		meanings = new ArrayList <String>();
		meanings.add("sparkle");
		this.theWords.put(key, meanings);
		
		key = "glen";
		meanings = new ArrayList <String>();
		meanings.add("narrow valley");
		meanings.add("valley");
		this.theWords.put(key, meanings);
		
		key = "glimpse";
		meanings = new ArrayList <String>();
		meanings.add("brief view");
		this.theWords.put(key, meanings);
		
		key = "glory";
		meanings = new ArrayList <String>();
		meanings.add("splendour");
		this.theWords.put(key, meanings);
		
		key = "gloves";
		meanings = new ArrayList <String>();
		meanings.add("catches");//cricket
		meanings.add("hand warmers");
		this.theWords.put(key, meanings);
		
		key = "glum";
		meanings = new ArrayList <String>();
		meanings.add("dejected");
		this.theWords.put(key, meanings);
		
		key = "glut";
		meanings = new ArrayList <String>();
		meanings.add("excess");
		this.theWords.put(key, meanings);
		
		key = "glutton";
		meanings = new ArrayList <String>();
		meanings.add("overeater");
		this.theWords.put(key, meanings);
		
		key = "gnu";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "go";
		meanings = new ArrayList <String>();
		meanings.add("leave");
		meanings.add("turn");
		meanings.add("work");
		this.theWords.put(key, meanings);
		
		key = "goal";
		meanings = new ArrayList <String>();
		meanings.add("aim");
		meanings.add("objective");
		meanings.add("plan");
		this.theWords.put(key, meanings);
		
		key = "goat";
		meanings = new ArrayList <String>();
		meanings.add("billy");
		this.theWords.put(key, meanings);
		
		key = "goalie";
		meanings = new ArrayList <String>();
		meanings.add("last line of defence");
		this.theWords.put(key, meanings);
		
		key = "gobi";
		meanings = new ArrayList <String>();
		meanings.add("desert");
		this.theWords.put(key, meanings);
		
		key = "god";
		meanings = new ArrayList <String>();
		meanings.add("deity");
		this.theWords.put(key, meanings);
		
		key = "godiva";
		meanings = new ArrayList <String>();
		meanings.add("Coventry lady");
		meanings.add("naked lady");
		this.theWords.put(key, meanings);
		
		key = "gofar"; // go far
		meanings = new ArrayList <String>();
		meanings.add("succeed");
		this.theWords.put(key, meanings);
		
		key = "going";
		meanings = new ArrayList <String>();
		meanings.add("leaving");
		this.theWords.put(key, meanings);
		
		key = "golf";
		meanings = new ArrayList <String>();
		meanings.add("game");
		this.theWords.put(key, meanings);
		
		key = "gondola";
		meanings = new ArrayList <String>();
		meanings.add("boat");
		meanings.add("form of transport");
		this.theWords.put(key, meanings);
		
		key = "gondoliers";
		meanings = new ArrayList <String>();
		meanings.add("boatmen");
		this.theWords.put(key, meanings);
		
		key = "good";
		meanings = new ArrayList <String>();
		meanings.add("well-behaved");
		this.theWords.put(key, meanings);
				
		key = "gophers";
		meanings = new ArrayList <String>();
		meanings.add("burrowing rodents");
		this.theWords.put(key, meanings);
		
		key = "gorge";
		meanings = new ArrayList <String>();
		meanings.add("cheddar");
		meanings.add("eat too much");
		this.theWords.put(key, meanings);
		
		key = "gory";
		meanings = new ArrayList <String>();
		meanings.add("bloodbath");
		this.theWords.put(key, meanings);
		
		key = "gosling";
		meanings = new ArrayList <String>();
		meanings.add("little bird");
		this.theWords.put(key, meanings);
		
		key = "goslings";
		meanings = new ArrayList <String>();
		meanings.add("little birds");
		this.theWords.put(key, meanings);
		
		key = "gossip";
		meanings = new ArrayList <String>();
		meanings.add("tittle-tattle");
		this.theWords.put(key, meanings);
		
		key = "got";
		meanings = new ArrayList <String>();
		meanings.add("received");
		this.theWords.put(key, meanings);
		
		key = "grain";
		meanings = new ArrayList <String>();
		meanings.add("cereal crop");
		this.theWords.put(key, meanings);
		
		key = "grains";
		meanings = new ArrayList <String>();
		meanings.add("seeds");
		this.theWords.put(key, meanings);
		
		key = "gran";
		meanings = new ArrayList <String>();
		meanings.add("elderly relative");
		meanings.add("elderly woman");
		this.theWords.put(key, meanings);
		
		key = "granada";
		meanings = new ArrayList <String>();
		meanings.add("spanish city");
		this.theWords.put(key, meanings);
		
		key = "grapes";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "grasmere";
		meanings = new ArrayList <String>();
		meanings.add("lake");
		this.theWords.put(key, meanings);
		
		key = "grasp";
		meanings = new ArrayList <String>();
		meanings.add("get hold of");
		this.theWords.put(key, meanings);
				
		key = "grasshopper";
		meanings = new ArrayList <String>();
		meanings.add("little creature");
		this.theWords.put(key, meanings);

		key = "grate";
		meanings = new ArrayList <String>();
		meanings.add("irritate");
		meanings.add("shred");
		this.theWords.put(key, meanings);
		
		key = "grave";
		meanings = new ArrayList <String>();
		meanings.add("resting place");
		meanings.add("serious");
		this.theWords.put(key, meanings);
		
		key = "gravel";
		meanings = new ArrayList <String>();
		meanings.add("stones");
		this.theWords.put(key, meanings);
		
		key = "gravestone";
		meanings = new ArrayList <String>();
		meanings.add("memorial");
		this.theWords.put(key, meanings);
			
		key = "grease";
		meanings = new ArrayList <String>();
		meanings.add("lubricate");
		meanings.add("musical");
		meanings.add("oil");
		this.theWords.put(key, meanings);
		
		key = "great";
		meanings = new ArrayList <String>();
		meanings.add("wonderful");
		this.theWords.put(key, meanings);
		
		key = "green";
		meanings = new ArrayList <String>();
		meanings.add("caring for the environment");
		meanings.add("colour");
		meanings.add("lacking experience");
		this.theWords.put(key, meanings);
		
		key = "greenfinch";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "greenfly";
		meanings = new ArrayList <String>();
		meanings.add("garden pests");
		this.theWords.put(key, meanings);
		
		key = "greet";
		meanings = new ArrayList <String>();
		meanings.add("cry");
		meanings.add("welcome");
		this.theWords.put(key, meanings);
		
		key = "grenade";
		meanings = new ArrayList <String>();
		meanings.add("weapon");
		this.theWords.put(key, meanings);
		
		key = "grenadier";
		meanings = new ArrayList <String>();
		meanings.add("soldier");
		this.theWords.put(key, meanings);
		
		key = "greta";
		meanings = new ArrayList <String>();
		meanings.add("Garbo");
		this.theWords.put(key, meanings);
		
		key = "grill";
		meanings = new ArrayList <String>();
		meanings.add("cook");
		this.theWords.put(key, meanings);
				
		key = "grim";
		meanings = new ArrayList <String>();
		meanings.add("miserable");
		this.theWords.put(key, meanings);
		
		key = "grin";
		meanings = new ArrayList <String>();
		meanings.add("smile");
		this.theWords.put(key, meanings);
		
		key = "grins";
		meanings = new ArrayList <String>();
		meanings.add("smiles");
		this.theWords.put(key, meanings);
		
		key = "grind";
		meanings = new ArrayList <String>();
		meanings.add("hard work");
		this.theWords.put(key, meanings);
		
		key = "grit";
		meanings = new ArrayList <String>();
		meanings.add("courage");
		this.theWords.put(key, meanings);
		
		key = "groan";
		meanings = new ArrayList <String>();
		meanings.add("whine");
		this.theWords.put(key, meanings);
		
		key = "groom";
		meanings = new ArrayList <String>();
		meanings.add("stable boy");
		this.theWords.put(key, meanings);
		
		key = "grooms";
		meanings = new ArrayList <String>();
		meanings.add("stable men");
		this.theWords.put(key, meanings);
		
		key = "grounds";
		meanings = new ArrayList <String>();
		meanings.add("justifications");
		meanings.add("reasons");
		this.theWords.put(key, meanings);
		
		key = "groundsel";
		meanings = new ArrayList <String>();
		meanings.add("common weed");
		this.theWords.put(key, meanings);
		
		key = "groundswell";
		meanings = new ArrayList <String>();
		meanings.add("increase in public opinion");
		this.theWords.put(key, meanings);
		
		key = "grouse";
		meanings = new ArrayList <String>();
		meanings.add("game bird");
		this.theWords.put(key, meanings);
		
		key = "grow";
		meanings = new ArrayList <String>();
		meanings.add("expand");
		this.theWords.put(key, meanings);
		
		key = "guard";
		meanings = new ArrayList <String>();
		meanings.add("look out");
		this.theWords.put(key, meanings);
		
		key = "guess";
		meanings = new ArrayList <String>();
		meanings.add("conjecture");
		meanings.add("estimate");
		meanings.add("predict");
		meanings.add("prediction");
		meanings.add("theory");
		this.theWords.put(key, meanings);
		
		key = "gum";
		meanings = new ArrayList <String>();
		meanings.add("something sticky");
		meanings.add("sticky substance");
		this.theWords.put(key, meanings);
		
		key = "gunboat";
		meanings = new ArrayList <String>();
		meanings.add("armed vessel");
		this.theWords.put(key, meanings);
		
		key = "gush";
		meanings = new ArrayList <String>();
		meanings.add("show profuse admiration");
		this.theWords.put(key, meanings);

	}//end loadG
	
	private void loadHWords() {
		String key;
		ArrayList <String> meanings;
		
		// load h's
		key = "habitual";
		meanings = new ArrayList <String>();
		meanings.add("customary");
		this.theWords.put(key, meanings);
				
		key = "had";
		meanings = new ArrayList <String>();
		meanings.add("tricked");
		this.theWords.put(key, meanings);
		
		key = "haddock";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "hag";
		meanings = new ArrayList <String>();
		meanings.add("witch");
		this.theWords.put(key, meanings);
		
		key = "haggis";
		meanings = new ArrayList <String>();
		meanings.add("Scottish food");
		this.theWords.put(key, meanings);
		
		key = "half";
		meanings = new ArrayList <String>();
		meanings.add("fifty percent");
		this.theWords.put(key, meanings);
		
		key = "halibut";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "hall";
		meanings = new ArrayList <String>();
		meanings.add("room");
		this.theWords.put(key, meanings);
		
		key = "hallowed";
		meanings = new ArrayList <String>();
		meanings.add("consecrated");
		this.theWords.put(key, meanings);
		
		key = "halt";
		meanings = new ArrayList <String>();
		meanings.add("stop");
		this.theWords.put(key, meanings);
		
		key = "ham";
		meanings = new ArrayList <String>();
		meanings.add("bad actor");
		meanings.add("meat");
		this.theWords.put(key, meanings);
		
		key = "hamlet";
		meanings = new ArrayList <String>();
		meanings.add("shakespearean production");
		this.theWords.put(key, meanings);
		
		key = "hamper";
		meanings = new ArrayList <String>();
		meanings.add("basket");
		meanings.add("picnic basket");
		this.theWords.put(key, meanings);
		
		key = "hams";
		meanings = new ArrayList <String>();
		meanings.add("one of Noah's sons");
		this.theWords.put(key, meanings);
		
		key = "hand";
		meanings = new ArrayList <String>();
		meanings.add("worker");
		this.theWords.put(key, meanings);
		
		key = "handicap";
		meanings = new ArrayList <String>();
		meanings.add("drawback");
		this.theWords.put(key, meanings);
		
		key = "handsome";
		meanings = new ArrayList <String>();
		meanings.add("good-looking");
		this.theWords.put(key, meanings);
		
		key = "hanky";
		meanings = new ArrayList <String>();
		meanings.add("handkerchief");
		this.theWords.put(key, meanings);
		
		key = "hanoi";
		meanings = new ArrayList <String>();
		meanings.add("capital");
		this.theWords.put(key, meanings);
		
		key = "happy";
		meanings = new ArrayList <String>();
		meanings.add("delighted");
		this.theWords.put(key, meanings);
		
		key = "hard";
		meanings = new ArrayList <String>();
		meanings.add("difficult");
		this.theWords.put(key, meanings);

		key = "hardship";
		meanings = new ArrayList <String>();
		meanings.add("poverty");
		this.theWords.put(key, meanings);

		key = "hark";
		meanings = new ArrayList <String>();
		meanings.add("listen");
		this.theWords.put(key, meanings);
				
		key = "harm";
		meanings = new ArrayList <String>();
		meanings.add("damage");
		meanings.add("injure");
		this.theWords.put(key, meanings);
		
		key = "harmed";
		meanings = new ArrayList <String>();
		meanings.add("injured");
		this.theWords.put(key, meanings);
		
		key = "harmless";
		meanings = new ArrayList <String>();
		meanings.add("benign");
		this.theWords.put(key, meanings);
		
		key = "harpist";
		meanings = new ArrayList <String>();
		meanings.add("musician");
		this.theWords.put(key, meanings);
		
		key = "harrow";
		meanings = new ArrayList <String>();
		meanings.add("london borough");
		this.theWords.put(key, meanings);
		
		key = "has";
		meanings = new ArrayList <String>();
		meanings.add("possesses");
		this.theWords.put(key, meanings);
		
		key = "hassle";
		meanings = new ArrayList <String>();
		meanings.add("bother");
		this.theWords.put(key, meanings);
		
		key = "hastings";
		meanings = new ArrayList <String>();
		meanings.add("battle");
		this.theWords.put(key, meanings);
		
		key = "hasty";
		meanings = new ArrayList <String>();
		meanings.add("quick");
		this.theWords.put(key, meanings);
		
		key = "hat";
		meanings = new ArrayList <String>();
		meanings.add("headwear");
		this.theWords.put(key, meanings);
		
		key = "hats";
		meanings = new ArrayList <String>();
		meanings.add("headwear");
		this.theWords.put(key, meanings);
				
		key = "have";
		meanings = new ArrayList <String>();
		meanings.add("own");
		this.theWords.put(key, meanings);
		
		key = "haven";
		meanings = new ArrayList <String>();
		meanings.add("retreat");
		this.theWords.put(key, meanings);
		
		key = "hawthorn";
		meanings = new ArrayList <String>();
		meanings.add("plant");
		this.theWords.put(key, meanings);
		
		key = "he";
		meanings = new ArrayList <String>();
		meanings.add("a man");
		meanings.add("male");
		meanings.add("the man");
		this.theWords.put(key, meanings);
		
		key = "head";
		meanings = new ArrayList <String>();
		meanings.add("boss");
		meanings.add("part of the body");
		meanings.add("lead");
		meanings.add("principle");
		meanings.add("source of a river");
		meanings.add("top of a beer");
		this.theWords.put(key, meanings);
		
		key = "headstrong";
		meanings = new ArrayList <String>();
		meanings.add("self-willed");
		this.theWords.put(key, meanings);
		
		key = "hear";
		meanings = new ArrayList <String>();
		meanings.add("catch");
		this.theWords.put(key, meanings);
		
		key = "heal";
		meanings = new ArrayList <String>();
		meanings.add("cure");
		this.theWords.put(key, meanings);
		
		key = "healthy";
		meanings = new ArrayList <String>();
		meanings.add("sound");
		this.theWords.put(key, meanings);
		
		key = "heart";
		meanings = new ArrayList <String>();
		meanings.add("centre");
		meanings.add("organ");
		this.theWords.put(key, meanings);
		
		key = "hearten";
		meanings = new ArrayList <String>();
		meanings.add("cheer");
		this.theWords.put(key, meanings);
		
		key = "heartily";
		meanings = new ArrayList <String>();
		meanings.add("thoroughly");
		this.theWords.put(key, meanings);
		
		key = "heartless";
		meanings = new ArrayList <String>();
		meanings.add("cruel");
		this.theWords.put(key, meanings);
		
		key = "heat";
		meanings = new ArrayList <String>();
		meanings.add("passion");
		meanings.add("warmth");
		this.theWords.put(key, meanings);
		
		key = "heated";
		meanings = new ArrayList <String>();
		meanings.add("passionate");
		meanings.add("warmed");
		this.theWords.put(key, meanings);
		
		key = "heathen";
		meanings = new ArrayList <String>();
		meanings.add("pagan");
		this.theWords.put(key, meanings);
		
		key = "heel";
		meanings = new ArrayList <String>();
		meanings.add("part of the foot");
		meanings.add("shoe part");
		this.theWords.put(key, meanings);
		
		key = "held";
		meanings = new ArrayList <String>();
		meanings.add("grasped");
		this.theWords.put(key, meanings);
		
		key = "hello";
		meanings = new ArrayList <String>();
		meanings.add("greeting");
		this.theWords.put(key, meanings);
		
		key = "helm";
		meanings = new ArrayList <String>();
		meanings.add("ship's steering gear");
		this.theWords.put(key, meanings);
		
		key = "helmet";
		meanings = new ArrayList <String>();
		meanings.add("protective cover");
		this.theWords.put(key, meanings);
		
		key = "helpers";
		meanings = new ArrayList <String>();
		meanings.add("assistants");
		this.theWords.put(key, meanings);
		
		key = "hem";
		meanings = new ArrayList <String>();
		meanings.add("border");
		this.theWords.put(key, meanings);
		
		key = "hen";
		meanings = new ArrayList <String>();
		meanings.add("fowl");
		this.theWords.put(key, meanings);
		
		key = "hens";
		meanings = new ArrayList <String>();
		meanings.add("birds");
		this.theWords.put(key, meanings);
		
		key = "her";
		meanings = new ArrayList <String>();
		meanings.add("part of title");
		this.theWords.put(key, meanings);
		
		key = "herbal";
		meanings = new ArrayList <String>();
		meanings.add("type of tea");
		this.theWords.put(key, meanings);
		
		key = "here";
		meanings = new ArrayList <String>();
		meanings.add("in this place");
		this.theWords.put(key, meanings);
		
		key = "hereford";
		meanings = new ArrayList <String>();
		meanings.add("cathedral city");
		this.theWords.put(key, meanings);
		
		key = "hermes";
		meanings = new ArrayList <String>();
		meanings.add("Olympian messenger");
		this.theWords.put(key, meanings);
		
		key = "hermit";
		meanings = new ArrayList <String>();
		meanings.add("recluse");
		this.theWords.put(key, meanings);
		
		key = "hesitant";
		meanings = new ArrayList <String>();
		meanings.add("unsure");
		this.theWords.put(key, meanings);
		
		key = "hide";
		meanings = new ArrayList <String>();
		meanings.add("conceal");
		meanings.add("skin");
		this.theWords.put(key, meanings);
		
		key = "hideout";
		meanings = new ArrayList <String>();
		meanings.add("secret place");
		this.theWords.put(key, meanings);
		
		key = "hiding";
		meanings = new ArrayList <String>();
		meanings.add("beating");
		this.theWords.put(key, meanings);
				
		key = "high";
		meanings = new ArrayList <String>();
		meanings.add("lofty");
		this.theWords.put(key, meanings);
		
		key = "hill";
		meanings = new ArrayList <String>();
		meanings.add("high ground");
		this.theWords.put(key, meanings);
		
		key = "hind";
		meanings = new ArrayList <String>();
		meanings.add("back");
		this.theWords.put(key, meanings);
		
		key = "hindi";
		meanings = new ArrayList <String>();
		meanings.add("language");
		this.theWords.put(key, meanings);
		
		key = "hinge";
		meanings = new ArrayList <String>();
		meanings.add("entirely depend on");
		meanings.add("joint");
		this.theWords.put(key, meanings);
		
		key = "hint";
		meanings = new ArrayList <String>();
		meanings.add("clue");
		this.theWords.put(key, meanings);
		
		key = "hip";
		meanings = new ArrayList <String>();
		meanings.add("trendy");
		this.theWords.put(key, meanings);
		
		key = "hippo";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "his";
		meanings = new ArrayList <String>();
		meanings.add("belonging to him");
		this.theWords.put(key, meanings);
		
		key = "historical";
		meanings = new ArrayList <String>();
		meanings.add("old");
		this.theWords.put(key, meanings);
		
		key = "hit";
		meanings = new ArrayList <String>();
		meanings.add("beat");
		meanings.add("punch");
		meanings.add("punched");
		meanings.add("slap");
		meanings.add("slapped");
		meanings.add("thump");
		meanings.add("thumped");
		this.theWords.put(key, meanings);
		
		key = "hive";
		meanings = new ArrayList <String>();
		meanings.add("bee colony");
		this.theWords.put(key, meanings);
		
		key = "history";
		meanings = new ArrayList <String>();
		meanings.add("past events");
		this.theWords.put(key, meanings);
		
		key = "hobart";
		meanings = new ArrayList <String>();
		meanings.add("Tasmanian city");
		this.theWords.put(key, meanings);
		
		key = "hock";
		meanings = new ArrayList <String>();
		meanings.add("German wine");
		this.theWords.put(key, meanings);
				
		key = "hockey";
		meanings = new ArrayList <String>();
		meanings.add("game");
		this.theWords.put(key, meanings);
		
		key = "hog";
		meanings = new ArrayList <String>();
		meanings.add("swine");
		this.theWords.put(key, meanings);
		
		key = "hold";
		meanings = new ArrayList <String>();
		meanings.add("grasp");
		this.theWords.put(key, meanings);
		
		key = "holdall";
		meanings = new ArrayList <String>();
		meanings.add("bag");
		this.theWords.put(key, meanings);
		
		key = "hole";
		meanings = new ArrayList <String>();
		meanings.add("opening");
		this.theWords.put(key, meanings);
		
		key = "home";
		meanings = new ArrayList <String>();
		meanings.add("place of care");
		this.theWords.put(key, meanings);
		
		key = "hone";
		meanings = new ArrayList <String>();
		meanings.add("refine");
		this.theWords.put(key, meanings);
		
		key = "honestly";
		meanings = new ArrayList <String>();
		meanings.add("truethful way");
		this.theWords.put(key, meanings);
		
		key = "honey";
		meanings = new ArrayList <String>();
		meanings.add("darling");
		meanings.add("sweetheart");
		this.theWords.put(key, meanings);
		
		key = "honeysuckle";
		meanings = new ArrayList <String>();
		meanings.add("plant");
		this.theWords.put(key, meanings);
				
		key = "honour";
		meanings = new ArrayList <String>();
		meanings.add("respect");
		this.theWords.put(key, meanings);
		
		key = "hoods";
		meanings = new ArrayList <String>();
		meanings.add("coverings");
		this.theWords.put(key, meanings);
		
		key = "hop";
		meanings = new ArrayList <String>();
		meanings.add("beer ingredient");
		this.theWords.put(key, meanings);
		
		key = "hope";
		meanings = new ArrayList <String>();
		meanings.add("want something to happen");
		this.theWords.put(key, meanings);
		
		key = "hopper";
		meanings = new ArrayList <String>();
		meanings.add("container");
		this.theWords.put(key, meanings);
		
		key = "hops";
		meanings = new ArrayList <String>();
		meanings.add("jumps");
		this.theWords.put(key, meanings);
				
		key = "hopscotch";
		meanings = new ArrayList <String>();
		meanings.add("game");
		this.theWords.put(key, meanings);
		
		key = "horn";
		meanings = new ArrayList <String>();
		meanings.add("source of blast");
		this.theWords.put(key, meanings);
		
		key = "hornpipe";
		meanings = new ArrayList <String>();
		meanings.add("sailor's dance");
		this.theWords.put(key, meanings);
		
		key = "hornsea";
		meanings = new ArrayList <String>();
		meanings.add("seaside resort");
		this.theWords.put(key, meanings);
		
		key = "hospital";
		meanings = new ArrayList <String>();
		meanings.add("imfirmary");
		this.theWords.put(key, meanings);
		
		key = "hot";
		meanings = new ArrayList <String>();
		meanings.add("stolen");
		meanings.add("very warm");
		this.theWords.put(key, meanings);
		
		key = "hotcakes"; //hot cakes
		meanings = new ArrayList <String>();
		meanings.add("best sellers");
		this.theWords.put(key, meanings);
		
		key = "hotdogs";
		meanings = new ArrayList <String>();
		meanings.add("food");
		this.theWords.put(key, meanings);
		
		key = "hotel";
		meanings = new ArrayList <String>();
		meanings.add("accommodation");
		this.theWords.put(key, meanings);
		
		key = "hottest";
		meanings = new ArrayList <String>();
		meanings.add("spiciest");
		this.theWords.put(key, meanings);
				
		key = "hour";
		meanings = new ArrayList <String>();
		meanings.add("time");
		this.theWords.put(key, meanings);
		
		key = "hovel";
		meanings = new ArrayList <String>();
		meanings.add("insanitary dwelling");
		meanings.add("squalid dwelling");
		this.theWords.put(key, meanings);
		
		key = "how";
		meanings = new ArrayList <String>();
		meanings.add("in what way");
		this.theWords.put(key, meanings);
		
		key = "hound";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		meanings.add("relentlessly pursue");
		this.theWords.put(key, meanings);
		
		key = "hounds";
		meanings = new ArrayList <String>();
		meanings.add("dogs");
		meanings.add("relentlessly pursues");
		this.theWords.put(key, meanings);
		
		key = "hudson";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "hug";
		meanings = new ArrayList <String>();
		meanings.add("embrace");
		this.theWords.put(key, meanings);
		
		key = "huge";
		meanings = new ArrayList <String>();
		meanings.add("giant");
		this.theWords.put(key, meanings);
		
		key = "hugs";
		meanings = new ArrayList <String>();
		meanings.add("embraces");
		this.theWords.put(key, meanings);
		
		key = "human";
		meanings = new ArrayList <String>();
		meanings.add("person");
		this.theWords.put(key, meanings);
		
		key = "hung";
		meanings = new ArrayList <String>();
		meanings.add("draped");
		this.theWords.put(key, meanings);
		
		key = "husbands";
		meanings = new ArrayList <String>();
		meanings.add("married men");
		this.theWords.put(key, meanings);
		
		key = "hustle";
		meanings = new ArrayList <String>();
		meanings.add("jostle");
		this.theWords.put(key, meanings);
		
		key = "hustlers";
		meanings = new ArrayList <String>();
		meanings.add("aggressive sellers");
		this.theWords.put(key, meanings);
		
		key = "hut";
		meanings = new ArrayList <String>();
		meanings.add("cabin");
		meanings.add("shak");
		meanings.add("shed");
		meanings.add("shelter");
		this.theWords.put(key, meanings);
		
		key = "hydro";
		meanings = new ArrayList <String>();
		meanings.add("spa hotel");
		this.theWords.put(key, meanings);
		
		key = "hydrogen";
		meanings = new ArrayList <String>();
		meanings.add("gas");
		this.theWords.put(key, meanings);

	}//end loadH
	
	private void loadIWords() {
		String key;
		ArrayList <String> meanings;
		
		// load i's
		key = "i";
		meanings = new ArrayList <String>();
		meanings.add("the ninth letter of the alphabet");
		this.theWords.put(key, meanings);
		
		key = "iberia";
		meanings = new ArrayList <String>();
		meanings.add("peninsula");
		this.theWords.put(key, meanings);
		
		key = "ice";
		meanings = new ArrayList <String>();
		meanings.add("frozen water");
		this.theWords.put(key, meanings);
		
		key = "icecap";
		meanings = new ArrayList <String>();
		meanings.add("covering");
		this.theWords.put(key, meanings);
		
		key = "iced";
		meanings = new ArrayList <String>();
		meanings.add("frozen");
		this.theWords.put(key, meanings);
		
		key = "iceland";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "icy";
		meanings = new ArrayList <String>();
		meanings.add("chilling");
		meanings.add("very cold");
		this.theWords.put(key, meanings);
		
		key = "idea";
		meanings = new ArrayList <String>();
		meanings.add("brainwave");
		meanings.add("inkling");
		meanings.add("thought");
		this.theWords.put(key, meanings);
		
		key = "ideal";
		meanings = new ArrayList <String>();
		meanings.add("just the job");
		meanings.add("perfect");
		this.theWords.put(key, meanings);
		
		key = "idealise";
		meanings = new ArrayList <String>();
		meanings.add("regard as perfect");
		this.theWords.put(key, meanings);
		
		key = "ideally";
		meanings = new ArrayList <String>();
		meanings.add("perfectly");
		this.theWords.put(key, meanings);
		
		key = "idea";
		meanings = new ArrayList <String>();
		meanings.add("brainwaves");
		meanings.add("inklings");
		meanings.add("thoughts");
		this.theWords.put(key, meanings);
		
		key = "idiom";
		meanings = new ArrayList <String>();
		meanings.add("regional speech");
		this.theWords.put(key, meanings);
		
		key = "idiot";
		meanings = new ArrayList <String>();
		meanings.add("fool");
		this.theWords.put(key, meanings);
		
		key = "idle";
		meanings = new ArrayList <String>();
		meanings.add("doing nothing");
		this.theWords.put(key, meanings);
		
		key = "idled";
		meanings = new ArrayList <String>();
		meanings.add("didn't work");
		this.theWords.put(key, meanings);
		
		key = "idles";
		meanings = new ArrayList <String>();
		meanings.add("does nothing");
		meanings.add("doesn't work");
		this.theWords.put(key, meanings);
		
		key = "idol";
		meanings = new ArrayList <String>();
		meanings.add("object of desire");
		this.theWords.put(key, meanings);
		
		key = "idols";
		meanings = new ArrayList <String>();
		meanings.add("images");
		meanings.add("objects of desire");
		this.theWords.put(key, meanings);
			
		key = "if";
		meanings = new ArrayList <String>();
		meanings.add("despite");
		this.theWords.put(key, meanings);
		
		key = "ignites";
		meanings = new ArrayList <String>();
		meanings.add("lights");
		this.theWords.put(key, meanings);
		
		key = "ignore";
		meanings = new ArrayList <String>();
		meanings.add("disregard");
		meanings.add("take no notice of");
		this.theWords.put(key, meanings);
		
		key = "ilk";
		meanings = new ArrayList <String>();
		meanings.add("kind");
		this.theWords.put(key, meanings);
		
		key = "ill";
		meanings = new ArrayList <String>();
		meanings.add("not fit");
		meanings.add("sick");
		meanings.add("unwell");
		this.theWords.put(key, meanings);
		
		key = "images";
		meanings = new ArrayList <String>();
		meanings.add("pictures");
		meanings.add("visions");
		this.theWords.put(key, meanings);
		
		key = "imagination";
		meanings = new ArrayList <String>();
		meanings.add("vision");
		this.theWords.put(key, meanings);
		
		key = "imagine";
		meanings = new ArrayList <String>();
		meanings.add("dream up");
		this.theWords.put(key, meanings);
		
		key = "immediate";
		meanings = new ArrayList <String>();
		meanings.add("without delay");
		this.theWords.put(key, meanings);
		
		key = "immense";
		meanings = new ArrayList <String>();
		meanings.add("huge");
		this.theWords.put(key, meanings);
		
		key = "immoral";
		meanings = new ArrayList <String>();
		meanings.add("corrupt");
		this.theWords.put(key, meanings);
		
		key = "immortal";
		meanings = new ArrayList <String>();
		meanings.add("death-defying");
		this.theWords.put(key, meanings);
		
		key = "imp";
		meanings = new ArrayList <String>();
		meanings.add("little devil");
		meanings.add("naughty child");
		meanings.add("rascal");
		this.theWords.put(key, meanings);
		
		key = "impact";
		meanings = new ArrayList <String>();
		meanings.add("have an effect");
		this.theWords.put(key, meanings);
		
		key = "impair";
		meanings = new ArrayList <String>();
		meanings.add("cause damage");
		this.theWords.put(key, meanings);
		
		key = "impala";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "impend";
		meanings = new ArrayList <String>();
		meanings.add("threaten");
		this.theWords.put(key, meanings);
		
		key = "impetus";
		meanings = new ArrayList <String>();
		meanings.add("drive");
		this.theWords.put(key, meanings);
		
		key = "impulse";
		meanings = new ArrayList <String>();
		meanings.add("sudden urge");
		this.theWords.put(key, meanings);
		
		key = "in";
		meanings = new ArrayList <String>();
		//meanings.add("in");
		meanings.add("at home");
		meanings.add("fashionable");
		meanings.add("popular");
		this.theWords.put(key, meanings);
			
		key = "inadvertent";
		meanings = new ArrayList <String>();
		meanings.add("unintentional");
		this.theWords.put(key, meanings);
		
		key = "inane";
		meanings = new ArrayList <String>();
		meanings.add("silly");
		this.theWords.put(key, meanings);
		
		key = "inca";
		meanings = new ArrayList <String>();
		meanings.add("ancient Peruvian");
		this.theWords.put(key, meanings);
		
		key = "inch";
		meanings = new ArrayList <String>();
		meanings.add("move along slowly");
		this.theWords.put(key, meanings);
		
		key = "increase";
		meanings = new ArrayList <String>();
		meanings.add("additional pay");
		meanings.add("grow bigger");
		this.theWords.put(key, meanings);
		
		key = "indecorous";
		meanings = new ArrayList <String>();
		meanings.add("improper");
		this.theWords.put(key, meanings);
		
		key = "infant";
		meanings = new ArrayList <String>();
		meanings.add("child");
		this.theWords.put(key, meanings);
		
		key = "infantry";
		meanings = new ArrayList <String>();
		meanings.add("troops");
		this.theWords.put(key, meanings);
		
		key = "india";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "indian";
		meanings = new ArrayList <String>();
		meanings.add("take-away");
		this.theWords.put(key, meanings);
		
		key = "indiana";
		meanings = new ArrayList <String>();
		meanings.add("US state");
		this.theWords.put(key, meanings);
			
		key = "indulge";
		meanings = new ArrayList <String>();
		meanings.add("give way to urges");
		this.theWords.put(key, meanings);
		
		key = "inept";
		meanings = new ArrayList <String>();
		meanings.add("ham fisted");
		meanings.add("useless");
		this.theWords.put(key, meanings);
		
		key = "inert";
		meanings = new ArrayList <String>();
		meanings.add("not reacting");
		this.theWords.put(key, meanings);
		
		key = "inexact";
		meanings = new ArrayList <String>();
		meanings.add("not precise");
		this.theWords.put(key, meanings);
		
		key = "infidel";
		meanings = new ArrayList <String>();
		meanings.add("heathen");
		this.theWords.put(key, meanings);
		
		key = "ingot";
		meanings = new ArrayList <String>();
		meanings.add("piece of gold");
		this.theWords.put(key, meanings);
		
		key = "ingots";
		meanings = new ArrayList <String>();
		meanings.add("metal blocks");
		meanings.add("pieces of gold");
		this.theWords.put(key, meanings);
		
		key = "ingress";
		meanings = new ArrayList <String>();
		meanings.add("entrance");
		this.theWords.put(key, meanings);
				
		key = "inhuman";
		meanings = new ArrayList <String>();
		meanings.add("barbaric");
		this.theWords.put(key, meanings);
		
		key = "inmate";
		meanings = new ArrayList <String>();
		meanings.add("prisoner");
		this.theWords.put(key, meanings);
		
		key = "inn";
		meanings = new ArrayList <String>();
		meanings.add("boozer");
		meanings.add("tavern");
		this.theWords.put(key, meanings);
			
		key = "insane";
		meanings = new ArrayList <String>();
		meanings.add("mad");
		this.theWords.put(key, meanings);
		
		key = "insect";
		meanings = new ArrayList <String>();
		meanings.add("creepy crawly");
		this.theWords.put(key, meanings);
		
		key = "inserted";
		meanings = new ArrayList <String>();
		meanings.add("put in");
		this.theWords.put(key, meanings);
		
		key = "intact";
		meanings = new ArrayList <String>();
		meanings.add("whole");
		this.theWords.put(key, meanings);
		
		key = "integral";
		meanings = new ArrayList <String>();
		meanings.add("necessary");
		this.theWords.put(key, meanings);
				
		key = "integrity";
		meanings = new ArrayList <String>();
		meanings.add("honesty");
		this.theWords.put(key, meanings);
				
		key = "intend";
		meanings = new ArrayList <String>();
		meanings.add("mean");
		this.theWords.put(key, meanings);
		
		key = "intended";
		meanings = new ArrayList <String>();
		meanings.add("meant to be");
		this.theWords.put(key, meanings);
				
		key = "inter";
		meanings = new ArrayList <String>();
		meanings.add("bury");
		this.theWords.put(key, meanings);
		
		key = "interval";
		meanings = new ArrayList <String>();
		meanings.add("break");
		this.theWords.put(key, meanings);
		
		key = "into";
		meanings = new ArrayList <String>();
		meanings.add("keen on");
		this.theWords.put(key, meanings);
		
		key = "intrepid";
		meanings = new ArrayList <String>();
		meanings.add("daring");
		this.theWords.put(key, meanings);
		
		key = "invader";
		meanings = new ArrayList <String>();
		meanings.add("attacker");
		this.theWords.put(key, meanings);
		
		key = "inverse";
		meanings = new ArrayList <String>();
		meanings.add("opposite");
		this.theWords.put(key, meanings);
		
		key = "iota";
		meanings = new ArrayList <String>();
		meanings.add("letter");
		this.theWords.put(key, meanings);
		
		key = "iran";
		meanings = new ArrayList <String>();
		meanings.add("country");
		meanings.add("Persia, some time ago");
		this.theWords.put(key, meanings);
		
		key = "ire";
		meanings = new ArrayList <String>();
		meanings.add("anger");
		meanings.add("wrath");
		meanings.add("fury");
		meanings.add("rage");
		this.theWords.put(key, meanings);
		
		key = "iron";
		meanings = new ArrayList <String>();
		meanings.add("metal");
		this.theWords.put(key, meanings);
		
		key = "irony";
		meanings = new ArrayList <String>();
		meanings.add("like metal");
		meanings.add("sarcastic wit");
		this.theWords.put(key, meanings);
		
		key = "iris";
		meanings = new ArrayList <String>();
		meanings.add("flower");
		meanings.add("woman");
		this.theWords.put(key, meanings);	
		
		key = "irish";
		meanings = new ArrayList <String>();
		meanings.add("from Dublin");
		this.theWords.put(key, meanings);	
		
		key = "is";
		meanings = new ArrayList <String>();
		meanings.add("third person singular present of be");
		this.theWords.put(key, meanings);
		
		key = "isaac";
		meanings = new ArrayList <String>();
		meanings.add("son of Abraham");
		this.theWords.put(key, meanings);
		
		key = "islay";
		meanings = new ArrayList <String>();
		meanings.add("island");
		this.theWords.put(key, meanings);
		
		key = "it";
		meanings = new ArrayList <String>();
		meanings.add("the thing");
		meanings.add("thing");
		this.theWords.put(key, meanings);		
		
		key = "item";
		meanings = new ArrayList <String>();
		meanings.add("object");
		meanings.add("thing");
		this.theWords.put(key, meanings);
		
		key = "items";
		meanings = new ArrayList <String>();
		meanings.add("objects");
		meanings.add("things");
		this.theWords.put(key, meanings);

	}//end loadI
	
	private void loadJWords() {
		String key;
		ArrayList <String> meanings;
		
		// load j's
		key = "jack";
		meanings = new ArrayList <String>();
		meanings.add("knave");
		this.theWords.put(key, meanings);
		
		key = "jackpot";
		meanings = new ArrayList <String>();
		meanings.add("big winnings");
		this.theWords.put(key, meanings);
		
		key = "jacks";
		meanings = new ArrayList <String>();
		meanings.add("cards");
		meanings.add("devices for lifting");
		this.theWords.put(key, meanings);
		
		key = "jail";
		meanings = new ArrayList <String>();
		meanings.add("prison");
		this.theWords.put(key, meanings);
		
		key = "jet";
		meanings = new ArrayList <String>();
		meanings.add("black");
		meanings.add("stream of liquid");
		this.theWords.put(key, meanings);
		
		key = "jets";
		meanings = new ArrayList <String>();
		meanings.add("streams");
		this.theWords.put(key, meanings);
		
		key = "jetsam";
		meanings = new ArrayList <String>();
		meanings.add("what's thrown overboard");
		this.theWords.put(key, meanings);
				
		key = "jig";
		meanings = new ArrayList <String>();
		meanings.add("dance");
		this.theWords.put(key, meanings);
		
		key = "jigsaw";
		meanings = new ArrayList <String>();
		meanings.add("puzzle");
		this.theWords.put(key, meanings);
		
		key = "joke";
		meanings = new ArrayList <String>();
		meanings.add("gag");
		this.theWords.put(key, meanings);
		
		key = "joker";
		meanings = new ArrayList <String>();
		meanings.add("prankster");
		this.theWords.put(key, meanings);
		
		key = "joust";
		meanings = new ArrayList <String>();
		meanings.add("old contest");
		this.theWords.put(key, meanings);
		
		key = "jumble";
		meanings = new ArrayList <String>();
		meanings.add("mix up");
		meanings.add("unwanted household goods");
		this.theWords.put(key, meanings);
		
		key = "just";
		meanings = new ArrayList <String>();
		meanings.add("only");
		this.theWords.put(key, meanings);

	}//end loadJ
	
	private void loadKWords() {
		String key;
		ArrayList <String> meanings;
		
		// load k's
		key = "kale";
		meanings = new ArrayList <String>();
		meanings.add("cabbage");
		this.theWords.put(key, meanings);
		
		key = "keats";
		meanings = new ArrayList <String>();
		meanings.add("poet");
		this.theWords.put(key, meanings);
		
		key = "kebab";
		meanings = new ArrayList <String>();
		meanings.add("something to eat");
		this.theWords.put(key, meanings);
		
		key = "keel";
		meanings = new ArrayList <String>();
		meanings.add("part of ship");
		this.theWords.put(key, meanings);
		
		key = "keep";
		meanings = new ArrayList <String>();
		meanings.add("retain");
		this.theWords.put(key, meanings);
		
		key = "keg";
		meanings = new ArrayList <String>();
		meanings.add("vessel");
		this.theWords.put(key, meanings);
		
		key = "ken";
		meanings = new ArrayList <String>();
		meanings.add("little Kenneth");
		this.theWords.put(key, meanings);
		
		key = "kendo";
		meanings = new ArrayList <String>();
		meanings.add("Japanese martial arts");
		this.theWords.put(key, meanings);
				
		key = "kenya";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "ketch";
		meanings = new ArrayList <String>();
		meanings.add("boat");
		this.theWords.put(key, meanings);
		
		key = "key";
		meanings = new ArrayList <String>();
		meanings.add("vitally important");
		this.theWords.put(key, meanings);
		
		key = "kids";
		meanings = new ArrayList <String>();
		meanings.add("children");
		this.theWords.put(key, meanings);
		
		key = "killed";
		meanings = new ArrayList <String>();
		meanings.add("bumped off");
		this.theWords.put(key, meanings);
		
		key = "kin";
		meanings = new ArrayList <String>();
		meanings.add("family");
		meanings.add("relatives");
		this.theWords.put(key, meanings);
		
		key = "kind";
		meanings = new ArrayList <String>();
		meanings.add("sort");
		meanings.add("variety");
		this.theWords.put(key, meanings);
		
		key = "kindle";
		meanings = new ArrayList <String>();
		meanings.add("arouse");
		this.theWords.put(key, meanings);
		
		key = "king";
		meanings = new ArrayList <String>();
		meanings.add("monarch");
		meanings.add("ruler");
		meanings.add("sovereign");
		this.theWords.put(key, meanings);
		
		key = "kings";
		meanings = new ArrayList <String>();
		meanings.add("rulers");
		this.theWords.put(key, meanings);
		
		key = "kingscross";
		meanings = new ArrayList <String>();
		meanings.add("station");
		this.theWords.put(key, meanings);
		
		key = "kipper";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "kit";
		meanings = new ArrayList <String>();
		meanings.add("baggage");
		meanings.add("equipment");
		this.theWords.put(key, meanings);
		
		key = "kitchen";
		meanings = new ArrayList <String>();
		meanings.add("scullery");
		this.theWords.put(key, meanings);
		
		key = "kith";
		meanings = new ArrayList <String>();
		meanings.add("friends");
		this.theWords.put(key, meanings);
		
		key = "kneecap";
		meanings = new ArrayList <String>();
		meanings.add("bone");
		this.theWords.put(key, meanings);
		
		key = "kneel";
		meanings = new ArrayList <String>();
		meanings.add("get ready to pray");
		this.theWords.put(key, meanings);
		
		key = "knit";
		meanings = new ArrayList <String>();
		meanings.add("bond");
		meanings.add("use needles");
		this.theWords.put(key, meanings);
		
		key = "knockout";
		meanings = new ArrayList <String>();
		meanings.add("sort of tournament");
		meanings.add("stunning");
		this.theWords.put(key, meanings);
		
		key = "know";
		meanings = new ArrayList <String>();
		meanings.add("be aware of");
		this.theWords.put(key, meanings);
		
		key = "knowingly";
		meanings = new ArrayList <String>();
		meanings.add("with full knowledge");
		this.theWords.put(key, meanings);

	}//end loadK
	
	private void loadLWords() {
		String key;
		ArrayList <String> meanings;
		
		// load l's
		key = "label";
		meanings = new ArrayList <String>();
		meanings.add("tag");
		this.theWords.put(key, meanings);
		
		key = "lace";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "laces";
		meanings = new ArrayList <String>();
		meanings.add("shoestrings");
		this.theWords.put(key, meanings);
		
		key = "lacier";
		meanings = new ArrayList <String>();
		meanings.add("more frills");
		this.theWords.put(key, meanings);
		
		key = "lacrosse";
		meanings = new ArrayList <String>();
		meanings.add("game");
		this.theWords.put(key, meanings);
		
		key = "lad";
		meanings = new ArrayList <String>();
		meanings.add("boy");
		this.theWords.put(key, meanings);
		
		key = "lads";
		meanings = new ArrayList <String>();
		meanings.add("boys");
		this.theWords.put(key, meanings);
		
		key = "ladder";
		meanings = new ArrayList <String>();
		meanings.add("way up");
		this.theWords.put(key, meanings);
		
		key = "ladies";
		meanings = new ArrayList <String>();
		meanings.add("women");
		meanings.add("women");
		this.theWords.put(key, meanings);
		
		key = "lag";
		meanings = new ArrayList <String>();
		meanings.add("prisoner");
		this.theWords.put(key, meanings);
		
		key = "lagoon";
		meanings = new ArrayList <String>();
		meanings.add("stretch of water");
		this.theWords.put(key, meanings);
		
		key = "lake";
		meanings = new ArrayList <String>();
		meanings.add("body of water");
		this.theWords.put(key, meanings);
		
		key = "lamb";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("young sheep");
		this.theWords.put(key, meanings);
		
		key = "lambs";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		meanings.add("young sheep");
		this.theWords.put(key, meanings);
		
		key = "lambast";
		meanings = new ArrayList <String>();
		meanings.add("attack");
		this.theWords.put(key, meanings);
		
		key = "lambeth";
		meanings = new ArrayList <String>();
		meanings.add("London borough");
		this.theWords.put(key, meanings);
		
		key = "lame";
		meanings = new ArrayList <String>();
		meanings.add("not convincing");
		this.theWords.put(key, meanings);
		
		key = "lament";
		meanings = new ArrayList <String>();
		meanings.add("express sorrow");
		this.theWords.put(key, meanings);
		
		key = "lamina";
		meanings = new ArrayList <String>();
		meanings.add("thin layer");
		this.theWords.put(key, meanings);
		
		key = "lance";
		meanings = new ArrayList <String>();
		meanings.add("weapon");
		this.theWords.put(key, meanings);
		
		key = "lancet";
		meanings = new ArrayList <String>();
		meanings.add("surgical instrument");
		this.theWords.put(key, meanings);
		
		key = "land";
		meanings = new ArrayList <String>();
		meanings.add("come down");
		meanings.add("real estate");
		meanings.add("secure");
		this.theWords.put(key, meanings);
		
		key = "landlords";
		meanings = new ArrayList <String>();
		meanings.add("letters");
		this.theWords.put(key, meanings);
		
		key = "lane";
		meanings = new ArrayList <String>();
		meanings.add("little road");
		meanings.add("road");
		this.theWords.put(key, meanings);
		
		key = "lanes";
		meanings = new ArrayList <String>();
		meanings.add("country roads");
		meanings.add("roads");
		this.theWords.put(key, meanings);
		
		key = "language";
		meanings = new ArrayList <String>();
		meanings.add("speech");
		this.theWords.put(key, meanings);
		
		key = "languish";
		meanings = new ArrayList <String>();
		meanings.add("flag");
		this.theWords.put(key, meanings);
		
		key = "lanyard";
		meanings = new ArrayList <String>();
		meanings.add("ship's rope");
		this.theWords.put(key, meanings);
		
		key = "lap";
		meanings = new ArrayList <String>();
		meanings.add("circuit");
		this.theWords.put(key, meanings);
		
		key = "lapdog";
		meanings = new ArrayList <String>();
		meanings.add("chihuahua");
		this.theWords.put(key, meanings);
		
		key = "lapse";
		meanings = new ArrayList <String>();
		meanings.add("slip");
		this.theWords.put(key, meanings);
		
		key = "lard";
		meanings = new ArrayList <String>();
		meanings.add("animal fat");
		this.theWords.put(key, meanings);
		
		key = "large";
		meanings = new ArrayList <String>();
		meanings.add("very big");
		this.theWords.put(key, meanings);
		
		key = "largo";
		meanings = new ArrayList <String>();
		meanings.add("slow movement");
		this.theWords.put(key, meanings);
		
		key = "larva";
		meanings = new ArrayList <String>();
		meanings.add("grub");
		this.theWords.put(key, meanings);
		
		key = "late";
		meanings = new ArrayList <String>();
		meanings.add("behind");
		meanings.add("not on time");
		this.theWords.put(key, meanings);
		
		key = "later";
		meanings = new ArrayList <String>();
		meanings.add("afterwards");
		meanings.add("some time in the future");
		meanings.add("subsequent");
		this.theWords.put(key, meanings);
		
		key = "latest";
		meanings = new ArrayList <String>();
		meanings.add("most recent");
		this.theWords.put(key, meanings);
		
		key = "lathe";
		meanings = new ArrayList <String>();
		meanings.add("metal-working machine");
		this.theWords.put(key, meanings);
		
		key = "latin";
		meanings = new ArrayList <String>();
		meanings.add("ancient language");
		this.theWords.put(key, meanings);
		
		key = "lasagne";
		meanings = new ArrayList <String>();
		meanings.add("Italian food");
		this.theWords.put(key, meanings);
		
		key = "lashes";
		meanings = new ArrayList <String>();
		meanings.add("strokes");
		this.theWords.put(key, meanings);
		
		key = "lass";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "lasso";
		meanings = new ArrayList <String>();
		meanings.add("rope");
		this.theWords.put(key, meanings);
		
		key = "last";
		meanings = new ArrayList <String>();
		meanings.add("final");
		this.theWords.put(key, meanings);
		
		key = "law";
		meanings = new ArrayList <String>();
		meanings.add("system of rules");
		this.theWords.put(key, meanings);
		
		key = "lay";
		meanings = new ArrayList <String>();
		meanings.add("non-professional");
		meanings.add("place");
		meanings.add("position");
		meanings.add("put down");
		this.theWords.put(key, meanings);
		
		key = "layer";
		meanings = new ArrayList <String>();
		meanings.add("coat");
		this.theWords.put(key, meanings);
		
		key = "layon"; //lay on
		meanings = new ArrayList <String>();
		meanings.add("provide");
		this.theWords.put(key, meanings);
		
		key = "laze";
		meanings = new ArrayList <String>();
		meanings.add("do nothing");
		this.theWords.put(key, meanings);
		
		key = "lead";
		meanings = new ArrayList <String>();
		meanings.add("metal");
		meanings.add("promising clue");
		this.theWords.put(key, meanings);
		
		key = "leading";
		meanings = new ArrayList <String>();
		meanings.add("first");
		this.theWords.put(key, meanings);
		
		key = "leaf";
		meanings = new ArrayList <String>();
		meanings.add("bit of foliage");
		this.theWords.put(key, meanings);
		
		key = "leaflet";
		meanings = new ArrayList <String>();
		meanings.add("flyer");
		this.theWords.put(key, meanings);
		
		key = "leaps";
		meanings = new ArrayList <String>();
		meanings.add("jumps");
		this.theWords.put(key, meanings);
				
		key = "lean";
		meanings = new ArrayList <String>();
		meanings.add("thin");
		this.theWords.put(key, meanings);
		
		key = "leans";
		meanings = new ArrayList <String>();
		meanings.add("inclines");
		meanings.add("slants");
		meanings.add("slopes");
		this.theWords.put(key, meanings);
			
		key = "lear";
		meanings = new ArrayList <String>();
		meanings.add("king");
		this.theWords.put(key, meanings);
		
		key = "learn";
		meanings = new ArrayList <String>();
		meanings.add("discover");
		this.theWords.put(key, meanings);
		
		key = "learner";
		meanings = new ArrayList <String>();
		meanings.add("apprentice");
		this.theWords.put(key, meanings);
		
		key = "lease";
		meanings = new ArrayList <String>();
		meanings.add("let");
		this.theWords.put(key, meanings);
		
		key = "leased";
		meanings = new ArrayList <String>();
		meanings.add("let");
		this.theWords.put(key, meanings);
		
		key = "leases";
		meanings = new ArrayList <String>();
		meanings.add("rents");
		this.theWords.put(key, meanings);
		
		key = "least";
		meanings = new ArrayList <String>();
		meanings.add("smallest amount");
		this.theWords.put(key, meanings);
		
		key = "leave";
		meanings = new ArrayList <String>();
		meanings.add("abandon");
		meanings.add("don't take");
		meanings.add("holiday");
		meanings.add("time off");
		this.theWords.put(key, meanings);
		
		key = "lebanon";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "lecture";
		meanings = new ArrayList <String>();
		meanings.add("spoken lesson");
		meanings.add("exposition");
		meanings.add("what students should attend");
		this.theWords.put(key, meanings);
		
		key = "led";
		meanings = new ArrayList <String>();
		meanings.add("been first");
		meanings.add("came first");
		meanings.add("light");
		meanings.add("ran");
		meanings.add("was first");
		meanings.add("went first");
		this.theWords.put(key, meanings);
		
		key = "ledge";
		meanings = new ArrayList <String>();
		meanings.add("shelf");
		this.theWords.put(key, meanings);
		
		key = "lee";
		meanings = new ArrayList <String>();
		meanings.add("boy");
		meanings.add("shelter");
		this.theWords.put(key, meanings);
		
		key = "leek";
		meanings = new ArrayList <String>();
		meanings.add("vegetable");
		this.theWords.put(key, meanings);
		
		key = "leeway";
		meanings = new ArrayList <String>();
		meanings.add("slack");
		this.theWords.put(key, meanings);
		
		key = "left";
		meanings = new ArrayList <String>();
		meanings.add("remained");
		this.theWords.put(key, meanings);
		
		key = "leg";
		meanings = new ArrayList <String>();
		meanings.add("limb");
		meanings.add("member");
		meanings.add("support");
		this.theWords.put(key, meanings);
		
		key = "legal";
		meanings = new ArrayList <String>();
		meanings.add("lawful");
		this.theWords.put(key, meanings);
		
		key = "legend";
		meanings = new ArrayList <String>();
		meanings.add("old story");
		this.theWords.put(key, meanings);
		
		key = "legume";
		meanings = new ArrayList <String>();
		meanings.add("vegetable");
		this.theWords.put(key, meanings);
		
		key = "leicester";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "leo";
		meanings = new ArrayList <String>();
		meanings.add("boy");
		meanings.add("sign");
		this.theWords.put(key, meanings);
		
		key = "leotard";
		meanings = new ArrayList <String>();
		meanings.add("sports garment");
		this.theWords.put(key, meanings);
		
		key = "lent";
		meanings = new ArrayList <String>();
		meanings.add("Christian feast");
		this.theWords.put(key, meanings);
				
		key = "less";
		meanings = new ArrayList <String>();
		meanings.add("fewer");
		this.theWords.put(key, meanings);
		
		key = "let";
		meanings = new ArrayList <String>();
		meanings.add("allow");
		meanings.add("allowed");
		meanings.add("permit");
		meanings.add("permitted");
		meanings.add("don't prevent");
		this.theWords.put(key, meanings);
		
		key = "lethal";
		meanings = new ArrayList <String>();
		meanings.add("deadly");
		this.theWords.put(key, meanings);
		
		key = "lets";
		meanings = new ArrayList <String>();
		meanings.add("allows");
		meanings.add("permits");
		this.theWords.put(key, meanings);
		
		key = "lettuce";
		meanings = new ArrayList <String>();
		meanings.add("salad vegetable");
		this.theWords.put(key, meanings);
		
		key = "lever";
		meanings = new ArrayList <String>();
		meanings.add("bar");
		this.theWords.put(key, meanings);
		
		key = "leveret";
		meanings = new ArrayList <String>();
		meanings.add("young animal");
		this.theWords.put(key, meanings);
		
		key = "leverets";
		meanings = new ArrayList <String>();
		meanings.add("young animals");
		this.theWords.put(key, meanings);
		
		key = "lexicon";
		meanings = new ArrayList <String>();
		meanings.add("dictionary");
		this.theWords.put(key, meanings);
		
		key = "leylandii";
		meanings = new ArrayList <String>();
		meanings.add("conifer");
		this.theWords.put(key, meanings);
		
		key = "liability";
		meanings = new ArrayList <String>();
		meanings.add("hindrance");
		this.theWords.put(key, meanings);
				
		key = "liable";
		meanings = new ArrayList <String>();
		meanings.add("bound");
		meanings.add("likely");
		this.theWords.put(key, meanings);
				
		key = "liar";
		meanings = new ArrayList <String>();
		meanings.add("fibber");
		this.theWords.put(key, meanings);
		
		key = "libelled";
		meanings = new ArrayList <String>();
		meanings.add("defamed");
		this.theWords.put(key, meanings);
		
		key = "lice";
		meanings = new ArrayList <String>();
		meanings.add("parasites");
		this.theWords.put(key, meanings);
		
		key = "lid";
		meanings = new ArrayList <String>();
		meanings.add("cover");
		this.theWords.put(key, meanings);
		
		key = "lido";
		meanings = new ArrayList <String>();
		meanings.add("bathing beach");
		meanings.add("outside pool");
		this.theWords.put(key, meanings);
		
		key = "lie";
		meanings = new ArrayList <String>();
		meanings.add("falsehood");
		meanings.add("story");
		this.theWords.put(key, meanings);
		
		key = "lied";
		meanings = new ArrayList <String>();
		meanings.add("fibbed");
		meanings.add("told fib");
		this.theWords.put(key, meanings);
		
		key = "lies";
		meanings = new ArrayList <String>();
		meanings.add("falsehoods");
		this.theWords.put(key, meanings);
		
		key = "lifelong";
		meanings = new ArrayList <String>();
		meanings.add("forever");
		this.theWords.put(key, meanings);
		
		key = "light";
		meanings = new ArrayList <String>();
		meanings.add("illuminate");
		meanings.add("set fire to");
		this.theWords.put(key, meanings);
		
		key = "lighter";
		meanings = new ArrayList <String>();
		meanings.add("having lost weight");
		meanings.add("weighing less");
		this.theWords.put(key, meanings);
				
		key = "like";
		meanings = new ArrayList <String>();
		meanings.add("as");
		meanings.add("be fond of");
		meanings.add("something similar");
		this.theWords.put(key, meanings);
		
		key = "liked";
		meanings = new ArrayList <String>();
		meanings.add("popular");
		this.theWords.put(key, meanings);
		
		key = "lilac";
		meanings = new ArrayList <String>();
		meanings.add("colour");
		meanings.add("garden shrub");
		this.theWords.put(key, meanings);
		
		key = "limb";
		meanings = new ArrayList <String>();
		meanings.add("member");
		this.theWords.put(key, meanings);
		
		key = "limbo";
		meanings = new ArrayList <String>();
		meanings.add("dance");
		this.theWords.put(key, meanings);
		
		key = "limbs";
		meanings = new ArrayList <String>();
		meanings.add("members");
		this.theWords.put(key, meanings);
		
		key = "limit";
		meanings = new ArrayList <String>();
		meanings.add("boundary");
		this.theWords.put(key, meanings);
		
		key = "limpet";
		meanings = new ArrayList <String>();
		meanings.add("shellfish");
		this.theWords.put(key, meanings);
		
		key = "line";
		meanings = new ArrayList <String>();
		meanings.add("railway");
		meanings.add("rope");
		this.theWords.put(key, meanings);
		
		key = "lineage";
		meanings = new ArrayList <String>();
		meanings.add("descent");
		this.theWords.put(key, meanings);
		
		key = "liner";
		meanings = new ArrayList <String>();
		meanings.add("ship");
		this.theWords.put(key, meanings);
		
		key = "ling";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		meanings.add("heather");
		this.theWords.put(key, meanings);
		
		key = "linger";
		meanings = new ArrayList <String>();
		meanings.add("persist");
		meanings.add("stay");
		this.theWords.put(key, meanings);
		
		key = "lingerie";
		meanings = new ArrayList <String>();
		meanings.add("underwear");
		this.theWords.put(key, meanings);
				
		key = "linnet";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "lino";
		meanings = new ArrayList <String>();
		meanings.add("floor covering");
		this.theWords.put(key, meanings);
		
		key = "liquor";
		meanings = new ArrayList <String>();
		meanings.add("alcoholic drink");
		this.theWords.put(key, meanings);
		
		key = "liquorice";
		meanings = new ArrayList <String>();
		meanings.add("sweet");
		this.theWords.put(key, meanings);
		
		key = "list";
		meanings = new ArrayList <String>();
		meanings.add("catalogue");
		this.theWords.put(key, meanings);
		
		key = "listen";
		meanings = new ArrayList <String>();
		meanings.add("give one's attention to");
		meanings.add("hark");
		this.theWords.put(key, meanings);

		key = "lit";
		meanings = new ArrayList <String>();
		meanings.add("illuminated");
		meanings.add("set fire to");
		this.theWords.put(key, meanings);
		
		key = "literature";
		meanings = new ArrayList <String>();
		meanings.add("writing");
		this.theWords.put(key, meanings);
		
		key = "lithe";
		meanings = new ArrayList <String>();
		meanings.add("nimble");
		this.theWords.put(key, meanings);
		
		key = "litres";
		meanings = new ArrayList <String>();
		meanings.add("units of capacity");
		this.theWords.put(key, meanings);
		
		key = "litup"; //lit up
		meanings = new ArrayList <String>();
		meanings.add("illuminated");
		this.theWords.put(key, meanings);
		
		key = "live";
		meanings = new ArrayList <String>();
		meanings.add("exist");
		this.theWords.put(key, meanings);
		
		key = "lives";
		meanings = new ArrayList <String>();
		meanings.add("exists");
		this.theWords.put(key, meanings);
		
		key = "llama";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "loan";
		meanings = new ArrayList <String>();
		meanings.add("advance");
		meanings.add("credit");
		this.theWords.put(key, meanings);
		
		key = "loath";
		meanings = new ArrayList <String>();
		meanings.add("reluctant");
		this.theWords.put(key, meanings);
		
		key = "lob";
		meanings = new ArrayList <String>();
		meanings.add("chuck");
		this.theWords.put(key, meanings);
		
		key = "local";
		meanings = new ArrayList <String>();
		meanings.add("pub");
		this.theWords.put(key, meanings);
		
		key = "locale";
		meanings = new ArrayList <String>();
		meanings.add("setting");
		this.theWords.put(key, meanings);
		
		key = "location";
		meanings = new ArrayList <String>();
		meanings.add("place");
		this.theWords.put(key, meanings);
				
		key = "loco";
		meanings = new ArrayList <String>();
		meanings.add("crazy");
		meanings.add("railway engine");
		this.theWords.put(key, meanings);
		
		key = "locust";
		meanings = new ArrayList <String>();
		meanings.add("insect");
		this.theWords.put(key, meanings);
		
		key = "loft";
		meanings = new ArrayList <String>();
		meanings.add("attic");
		this.theWords.put(key, meanings);
		
		key = "loftier";
		meanings = new ArrayList <String>();
		meanings.add("taller");
		this.theWords.put(key, meanings);
		
		key = "log";
		meanings = new ArrayList <String>();
		meanings.add("record");
		this.theWords.put(key, meanings);
		
		key = "logarithm";
		meanings = new ArrayList <String>();
		//meanings.add("exponent");
		meanings.add("mathematical invention");
		this.theWords.put(key, meanings);
		
		key = "logic";
		meanings = new ArrayList <String>();
		meanings.add("good sense");
		this.theWords.put(key, meanings);
		
		key = "logical";
		meanings = new ArrayList <String>();
		meanings.add("rational");
		this.theWords.put(key, meanings);
		
		key = "logo";
		meanings = new ArrayList <String>();
		meanings.add("symbol");
		this.theWords.put(key, meanings);
		
		key = "logs";
		meanings = new ArrayList <String>();
		meanings.add("records");
		this.theWords.put(key, meanings);
				
		key = "loire";
		meanings = new ArrayList <String>();
		meanings.add("European river");
		this.theWords.put(key, meanings);
		
		key = "lois";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "lone";
		meanings = new ArrayList <String>();
		meanings.add("solitary");
		this.theWords.put(key, meanings);
				
		key = "loner";
		meanings = new ArrayList <String>();
		meanings.add("recluse");
		this.theWords.put(key, meanings);
		
		key = "loners";
		meanings = new ArrayList <String>();
		meanings.add("recluses");
		this.theWords.put(key, meanings);
			
		key = "londoners";
		meanings = new ArrayList <String>();
		meanings.add("capital folk");
		this.theWords.put(key, meanings);
		
		key = "long";
		meanings = new ArrayList <String>();
		meanings.add("pine");
		meanings.add("tall");
		meanings.add("yearn");
		this.theWords.put(key, meanings);
		
		key = "lookout";
		meanings = new ArrayList <String>();
		meanings.add("guard");
		meanings.add("warning cry");
		this.theWords.put(key, meanings);
		
		key = "lops";
		meanings = new ArrayList <String>();
		meanings.add("cuts");
		this.theWords.put(key, meanings);
		
		key = "lords";
		meanings = new ArrayList <String>();
		meanings.add("cricket ground");
		this.theWords.put(key, meanings);
		
		key = "lore";
		meanings = new ArrayList <String>();
		meanings.add("culture");
		meanings.add("mythology");
		this.theWords.put(key, meanings);
				
		key = "losangeles";//Los Angeles
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "losers";
		meanings = new ArrayList <String>();
		meanings.add("beaten contestants");
		this.theWords.put(key, meanings);
		
		key = "lost";
		meanings = new ArrayList <String>();
		meanings.add("adrift");
		meanings.add("departed");
		this.theWords.put(key, meanings);
		
		key = "lot";
		meanings = new ArrayList <String>();
		meanings.add("item");
		meanings.add("large quantity");
		this.theWords.put(key, meanings);
		
		key = "lots";
		meanings = new ArrayList <String>();
		meanings.add("items");
		meanings.add("many");
		this.theWords.put(key, meanings);
		
		key = "lough";
		meanings = new ArrayList <String>();
		meanings.add("Irish loch");
		this.theWords.put(key, meanings);
		
		key = "lounge";
		meanings = new ArrayList <String>();
		meanings.add("room");
		this.theWords.put(key, meanings);
		
		key = "lourdes";
		meanings = new ArrayList <String>();
		meanings.add("holy place");
		this.theWords.put(key, meanings);
		
		key = "louse";
		meanings = new ArrayList <String>();
		meanings.add("insect");
		this.theWords.put(key, meanings);
		
		key = "louvre";
		meanings = new ArrayList <String>();
		meanings.add("French gallery");
		meanings.add("museum");
		this.theWords.put(key, meanings);
		
		key = "louvred";
		meanings = new ArrayList <String>();
		meanings.add("with slats");
		this.theWords.put(key, meanings);
		
		key = "love";
		meanings = new ArrayList <String>();
		meanings.add("like very much");
		meanings.add("nothing in tennis");
		this.theWords.put(key, meanings);
		
		key = "loveable";
		meanings = new ArrayList <String>();
		meanings.add("adorable");
		this.theWords.put(key, meanings);
		
		key = "loves";
		meanings = new ArrayList <String>();
		meanings.add("adores");
		this.theWords.put(key, meanings);
		
		key = "lower";
		meanings = new ArrayList <String>();
		meanings.add("deeper");
		this.theWords.put(key, meanings);
		
		key = "loyal";
		meanings = new ArrayList <String>();
		meanings.add("faithful");
		this.theWords.put(key, meanings);
		
		key = "lucky";
		meanings = new ArrayList <String>();
		meanings.add("fortunate");
		this.theWords.put(key, meanings);
		
		key = "lucy";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "lunch";
		meanings = new ArrayList <String>();
		meanings.add("meal");
		this.theWords.put(key, meanings);
		
		key = "lung";
		meanings = new ArrayList <String>();
		meanings.add("organ");
		this.theWords.put(key, meanings);
		
		key = "lunge";
		meanings = new ArrayList <String>();
		meanings.add("dive forward");
		meanings.add("thrust");
		meanings.add("thrust forward");
		this.theWords.put(key, meanings);
		
		key = "lumbago";
		meanings = new ArrayList <String>();
		meanings.add("complaint");
		this.theWords.put(key, meanings);
		
		key = "lumpy";
		meanings = new ArrayList <String>();
		meanings.add("uneven");
		this.theWords.put(key, meanings);
		
		key = "lustre";
		meanings = new ArrayList <String>();
		meanings.add("radiance");
		this.theWords.put(key, meanings);
		
		key = "lute";
		meanings = new ArrayList <String>();
		meanings.add("instrument");
		this.theWords.put(key, meanings);
		
		key = "luton";
		meanings = new ArrayList <String>();
		meanings.add("airport");
		this.theWords.put(key, meanings);
		
		key = "lying";
		meanings = new ArrayList <String>();
		meanings.add("not telling the truth");
		this.theWords.put(key, meanings);

	}//end loadL
	
	private void loadMWords() {
		String key;
		ArrayList <String> meanings;
		
		// load m's
		key = "ma";
		meanings = new ArrayList <String>();
		meanings.add("mother");
		this.theWords.put(key, meanings);
		
		key = "mac";
		meanings = new ArrayList <String>();
		meanings.add("coat");
		this.theWords.put(key, meanings);
		
		key = "macabre";
		meanings = new ArrayList <String>();
		meanings.add("gruesome");
		this.theWords.put(key, meanings);
		
		key = "mad";
		meanings = new ArrayList <String>();
		meanings.add("crazy");
		meanings.add("nuts");
		this.theWords.put(key, meanings);
		
		key = "madame";
		meanings = new ArrayList <String>();
		meanings.add("French woman");
		this.theWords.put(key, meanings);

		key = "made";
		meanings = new ArrayList <String>();
		meanings.add("created");
		this.theWords.put(key, meanings);
		
		key = "madeup"; //made up
		meanings = new ArrayList <String>();
		meanings.add("invented");
		meanings.add("resolved differences");
		this.theWords.put(key, meanings);
		
		key = "maestro";
		meanings = new ArrayList <String>();
		meanings.add("eminent performer");
		meanings.add("great conductor");
		this.theWords.put(key, meanings);
		
		key = "magenta";
		meanings = new ArrayList <String>();
		meanings.add("red");
		this.theWords.put(key, meanings);
		
		key = "magi";
		meanings = new ArrayList <String>();
		meanings.add("Christmas visitors");
		this.theWords.put(key, meanings);
		
		key = "magic";
		meanings = new ArrayList <String>();
		meanings.add("sorcery");
		this.theWords.put(key, meanings);
		
		key = "magnetic";
		meanings = new ArrayList <String>();
		meanings.add("attractive");
		this.theWords.put(key, meanings);
		
		key = "maid";
		meanings = new ArrayList <String>();
		meanings.add("cleaner");
		this.theWords.put(key, meanings);
		
		key = "mail";
		meanings = new ArrayList <String>();
		meanings.add("letters");
		this.theWords.put(key, meanings);
		
		key = "main";
		meanings = new ArrayList <String>();
		meanings.add("dominant");
		meanings.add("principal");
		this.theWords.put(key, meanings);
		
		key = "maine";
		meanings = new ArrayList <String>();
		meanings.add("state");
		this.theWords.put(key, meanings);
		
		key = "mains";
		meanings = new ArrayList <String>();
		meanings.add("chief fuel supply");
		this.theWords.put(key, meanings);
		
		key = "major";
		meanings = new ArrayList <String>();
		meanings.add("officer");
		this.theWords.put(key, meanings);
		
		key = "majorca";
		meanings = new ArrayList <String>();
		meanings.add("spanish island");
		this.theWords.put(key, meanings);
		
		key = "make";
		meanings = new ArrayList <String>();
		meanings.add("tradename");
		this.theWords.put(key, meanings);
		
		key = "makes";
		meanings = new ArrayList <String>();
		meanings.add("produces");
		this.theWords.put(key, meanings);
		
		key = "maker";
		meanings = new ArrayList <String>();
		meanings.add("inventor");
		this.theWords.put(key, meanings);
		
		key = "makeup";
		meanings = new ArrayList <String>();
		meanings.add("invent");//make up
		meanings.add("cosmetics");
		this.theWords.put(key, meanings);
		
		key = "maladies";
		meanings = new ArrayList <String>();
		meanings.add("uneasy feeling");
		this.theWords.put(key, meanings);
		
		key = "malaise";
		meanings = new ArrayList <String>();
		meanings.add("complaints");
		meanings.add("uneasy feeling");
		this.theWords.put(key, meanings);
		
		key = "malaria";
		meanings = new ArrayList <String>();
		meanings.add("fever");
		this.theWords.put(key, meanings);
		
		key = "males";
		meanings = new ArrayList <String>();
		meanings.add("men");
		this.theWords.put(key, meanings);
		
		key = "mali";
		meanings = new ArrayList <String>();
		meanings.add("African country");
		this.theWords.put(key, meanings);
		
		key = "malice";
		meanings = new ArrayList <String>();
		meanings.add("ill will");
		this.theWords.put(key, meanings);
		
		key = "malign";
		meanings = new ArrayList <String>();
		meanings.add("speak ill of");
		this.theWords.put(key, meanings);
		
		key = "maligned";
		meanings = new ArrayList <String>();
		meanings.add("defamed");
		this.theWords.put(key, meanings);
		
		key = "mallet";
		meanings = new ArrayList <String>();
		meanings.add("sort of hammer");
		this.theWords.put(key, meanings);
		
		key = "malt";
		meanings = new ArrayList <String>();
		meanings.add("whisky");
		this.theWords.put(key, meanings);
		
		key = "malta";
		meanings = new ArrayList <String>();
		meanings.add("Mediterranean Island");
		meanings.add("island");
		this.theWords.put(key, meanings);
		
		key = "mamba";
		meanings = new ArrayList <String>();
		meanings.add("snake");
		this.theWords.put(key, meanings);
		
		key = "mammoth";
		meanings = new ArrayList <String>();
		meanings.add("massive");
		this.theWords.put(key, meanings);
		
		key = "man";
		meanings = new ArrayList <String>();
		meanings.add("chap");
		meanings.add("male");
		meanings.add("person");
		this.theWords.put(key, meanings);
		
		key = "mandarin";
		meanings = new ArrayList <String>();
		meanings.add("Chinese");
		this.theWords.put(key, meanings);
		
		key = "mandrill";
		meanings = new ArrayList <String>();
		meanings.add("big baboon");
		this.theWords.put(key, meanings);
		
		key = "manila";
		meanings = new ArrayList <String>();
		meanings.add("Philippine city");
		this.theWords.put(key, meanings);
		
		key = "mankind";
		meanings = new ArrayList <String>();
		meanings.add("human race");
		this.theWords.put(key, meanings);
		
		key = "mansions";
		meanings = new ArrayList <String>();
		meanings.add("large houses");
		this.theWords.put(key, meanings);
		
		key = "mantra";
		meanings = new ArrayList <String>();
		meanings.add("chant");
		this.theWords.put(key, meanings);
		
		key = "mantrap";
		meanings = new ArrayList <String>();
		meanings.add("snare for trespasser's");
		this.theWords.put(key, meanings);
		
		key = "manual";
		meanings = new ArrayList <String>();
		meanings.add("handbook");
		this.theWords.put(key, meanings);
		
		key = "many";
		meanings = new ArrayList <String>();
		meanings.add("a lot");
		this.theWords.put(key, meanings);
		
		key = "map";
		meanings = new ArrayList <String>();
		meanings.add("chart");
		this.theWords.put(key, meanings);
		
		key = "mar";
		meanings = new ArrayList <String>();
		meanings.add("have a detrimental effect on");
		meanings.add("spoil");
		this.theWords.put(key, meanings);
		
		key = "maracas";
		meanings = new ArrayList <String>();
		meanings.add("they'll be shaken");
		this.theWords.put(key, meanings);
		
		key = "march";
		meanings = new ArrayList <String>();
		meanings.add("parade");
		this.theWords.put(key, meanings);
		
		key = "mare";
		meanings = new ArrayList <String>();
		meanings.add("horse");
		this.theWords.put(key, meanings);
		
		key = "mares";
		meanings = new ArrayList <String>();
		meanings.add("horses");
		this.theWords.put(key, meanings);
		
		key = "margin";
		meanings = new ArrayList <String>();
		meanings.add("border");
		this.theWords.put(key, meanings);
		
		key = "margins";
		meanings = new ArrayList <String>();
		meanings.add("borders");
		this.theWords.put(key, meanings);
		
		key = "maria";
		meanings = new ArrayList <String>();
		meanings.add("Callas");
		this.theWords.put(key, meanings);
		
		key = "marinate";
		meanings = new ArrayList <String>();
		meanings.add("soak");
		this.theWords.put(key, meanings);
		
		key = "marine";
		meanings = new ArrayList <String>();
		meanings.add("of the sea");
		this.theWords.put(key, meanings);
		
		key = "maritime";
		meanings = new ArrayList <String>();
		meanings.add("nautical");
		this.theWords.put(key, meanings);
				
		key = "mark";
		meanings = new ArrayList <String>();
		meanings.add("former currency");
		meanings.add("stain");
		this.theWords.put(key, meanings);
		
		key = "market";
		meanings = new ArrayList <String>();
		meanings.add("advertise");
		this.theWords.put(key, meanings);
		
		key = "marks";
		meanings = new ArrayList <String>();
		meanings.add("stains");
		this.theWords.put(key, meanings);
				
		key = "marlin";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "maroon";
		meanings = new ArrayList <String>();
		meanings.add("red");
		this.theWords.put(key, meanings);
		
		key = "marooned";
		meanings = new ArrayList <String>();
		meanings.add("left high and dry");
		this.theWords.put(key, meanings);
		
		key = "mars";
		meanings = new ArrayList <String>();
		meanings.add("god");
		meanings.add("Roman god");
		this.theWords.put(key, meanings);
		
		key = "mart";
		meanings = new ArrayList <String>();
		meanings.add("shop");
		this.theWords.put(key, meanings);
		
		key = "mary";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "mascot";
		meanings = new ArrayList <String>();
		meanings.add("lucky charm");
		this.theWords.put(key, meanings);
		
		key = "mash";
		meanings = new ArrayList <String>();
		meanings.add("reduce to a pulp");
		meanings.add("crush");
		meanings.add("smash");
		meanings.add("a brewing technique");
		this.theWords.put(key, meanings);
		
		key = "mask";
		meanings = new ArrayList <String>();
		meanings.add("disguise");
		this.theWords.put(key, meanings);
		
		key = "masks";
		meanings = new ArrayList <String>();
		meanings.add("they disguise");
		this.theWords.put(key, meanings);
		
		key = "masonry";
		meanings = new ArrayList <String>();
		meanings.add("brickwork");
		this.theWords.put(key, meanings);
		
		key = "mass";
		meanings = new ArrayList <String>();
		meanings.add("large amount");
		meanings.add("multitude");
		meanings.add("service");
		this.theWords.put(key, meanings);
		
		key = "masseur";
		meanings = new ArrayList <String>();
		meanings.add("manipulator");
		this.theWords.put(key, meanings);
		
		key = "masterly";
		meanings = new ArrayList <String>();
		meanings.add("appearing good at it");
		this.theWords.put(key, meanings);
		
		key = "mastery";
		meanings = new ArrayList <String>();
		meanings.add("complete control");
		this.theWords.put(key, meanings);
				
		key = "mastiff";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		this.theWords.put(key, meanings);
		
		key = "mat";
		meanings = new ArrayList <String>();
		meanings.add("tangle");
		this.theWords.put(key, meanings);
		
		key = "mate";
		meanings = new ArrayList <String>();
		meanings.add("pal");
		this.theWords.put(key, meanings);
		
		key = "mattress";
		meanings = new ArrayList <String>();
		meanings.add("bedding");
		this.theWords.put(key, meanings);
		
		key = "Mauritius";
		meanings = new ArrayList <String>();
		meanings.add("island");
		this.theWords.put(key, meanings);
		
		key = "max";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "may";
		meanings = new ArrayList <String>();
		meanings.add("month");
		this.theWords.put(key, meanings);
		
		key = "mayhem";
		meanings = new ArrayList <String>();
		meanings.add("chaos");
		this.theWords.put(key, meanings);
		
		key = "maze";
		meanings = new ArrayList <String>();
		meanings.add("labyrinth");
		this.theWords.put(key, meanings);
		
		key = "me";
		meanings = new ArrayList <String>();
		meanings.add("myself");
		meanings.add("yours truly");
		this.theWords.put(key, meanings);
		
		key = "mead";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "meal";
		meanings = new ArrayList <String>();
		meanings.add("dinner");
		this.theWords.put(key, meanings);
		
		key = "mean";
		meanings = new ArrayList <String>();
		meanings.add("adept");
		meanings.add("intend");
		meanings.add("signify");
		meanings.add("tight with money");
		meanings.add("very skilful");
		this.theWords.put(key, meanings);
		
		key = "meat";
		meanings = new ArrayList <String>();
		meanings.add("the flesh of an animal");
		meanings.add("chief part of");
		this.theWords.put(key, meanings);
		
		key = "meats";
		meanings = new ArrayList <String>();
		meanings.add("food");
		this.theWords.put(key, meanings);
		
		key = "medals";
		meanings = new ArrayList <String>();
		meanings.add("gongs");
		this.theWords.put(key, meanings);
		
		key = "median";
		meanings = new ArrayList <String>();
		meanings.add("at the midpoint");
		meanings.add("in the middle");
		this.theWords.put(key, meanings);
		
		key = "mediate";
		meanings = new ArrayList <String>();
		meanings.add("act as a peace maker");
		meanings.add("reconcile");
		this.theWords.put(key, meanings);
		
		key = "medicine";
		meanings = new ArrayList <String>();
		meanings.add("treatment");
		this.theWords.put(key, meanings);
		
		key = "meds";
		meanings = new ArrayList <String>();
		meanings.add("prescriptions");
		this.theWords.put(key, meanings);
				
		key = "meek";
		meanings = new ArrayList <String>();
		meanings.add("humble");
		this.theWords.put(key, meanings);
		
		key = "meekly";
		meanings = new ArrayList <String>();
		meanings.add("in a humble way");
		this.theWords.put(key, meanings);
		
		key = "meeting";
		meanings = new ArrayList <String>();
		meanings.add("coming across");
		meanings.add("encountering");
		this.theWords.put(key, meanings);
		
		key = "melbourne";
		meanings = new ArrayList <String>();
		meanings.add("Australian city");
		this.theWords.put(key, meanings);
		
		key = "member";
		meanings = new ArrayList <String>();
		meanings.add("one who's in a club");
		this.theWords.put(key, meanings);
		
		key = "memo";
		meanings = new ArrayList <String>();
		meanings.add("note");
		this.theWords.put(key, meanings);
		
		key = "men";
		meanings = new ArrayList <String>();
		meanings.add("chaps");
		meanings.add("people");
		this.theWords.put(key, meanings);
		
		key = "menace";
		meanings = new ArrayList <String>();
		meanings.add("alarm");
		meanings.add("threaten");
		this.theWords.put(key, meanings);
		
		key = "menaces";
		meanings = new ArrayList <String>();
		meanings.add("alarms");
		this.theWords.put(key, meanings);
		
		key = "mend";
		meanings = new ArrayList <String>();
		meanings.add("repair");
		this.theWords.put(key, meanings);
		
		key = "mends";
		meanings = new ArrayList <String>();
		meanings.add("repairs");
		this.theWords.put(key, meanings);
		
		key = "mention";
		meanings = new ArrayList <String>();
		meanings.add("divulge");
		this.theWords.put(key, meanings);
		
		key = "menu";
		meanings = new ArrayList <String>();
		meanings.add("food list");
		this.theWords.put(key, meanings);
		
		key = "mep";
		meanings = new ArrayList <String>();
		meanings.add("politician");
		this.theWords.put(key, meanings);
		
		key = "mere";
		meanings = new ArrayList <String>();
		meanings.add("lake");
		meanings.add("only");
		this.theWords.put(key, meanings);
		
		key = "merely";
		meanings = new ArrayList <String>();
		meanings.add("just");
		this.theWords.put(key, meanings);
		
		key = "merges";
		meanings = new ArrayList <String>();
		meanings.add("mixes");
		this.theWords.put(key, meanings);
		
		key = "merino";
		meanings = new ArrayList <String>();
		meanings.add("sheep");
		this.theWords.put(key, meanings);
				
		key = "mesh";
		meanings = new ArrayList <String>();
		meanings.add("network");
		this.theWords.put(key, meanings);
		
		key = "mess";
		meanings = new ArrayList <String>();
		meanings.add("canteen");
		meanings.add("disorder");
		meanings.add("disorderely state");
		meanings.add("where soldiers eat");
		this.theWords.put(key, meanings);
		
		key = "message";
		meanings = new ArrayList <String>();
		meanings.add("notification");
		meanings.add("sense");
		this.theWords.put(key, meanings);
		
		key = "messages";
		meanings = new ArrayList <String>();
		meanings.add("communications");
		meanings.add("notifications");
		this.theWords.put(key, meanings);

		key = "met";
		meanings = new ArrayList <String>();
		meanings.add("came across");
		meanings.add("encountered");
		this.theWords.put(key, meanings);
		
		key = "metal";
		meanings = new ArrayList <String>();
		meanings.add("hard shiny material");
		this.theWords.put(key, meanings);
		
		key = "meteor";
		meanings = new ArrayList <String>();
		meanings.add("shooting star");
		this.theWords.put(key, meanings);
		
		key = "meter";
		meanings = new ArrayList <String>();
		meanings.add("gauge");
		meanings.add("measuring device");
		this.theWords.put(key, meanings);
		
		key = "method";
		meanings = new ArrayList <String>();
		meanings.add("system");
		this.theWords.put(key, meanings);
		
		key = "methodist";
		meanings = new ArrayList <String>();
		meanings.add("believer");
		this.theWords.put(key, meanings);
				
		key = "mexican";
		meanings = new ArrayList <String>();
		meanings.add("");
		this.theWords.put(key, meanings);
		
		key = "mice";
		meanings = new ArrayList <String>();
		meanings.add("small animals");
		this.theWords.put(key, meanings);
		
		key = "mighty";
		meanings = new ArrayList <String>();
		meanings.add("big");
		this.theWords.put(key, meanings);
		
		key = "mile";
		meanings = new ArrayList <String>();
		meanings.add("distance");
		this.theWords.put(key, meanings);
		
		key = "mill";
		meanings = new ArrayList <String>();
		meanings.add("factory");
		this.theWords.put(key, meanings);
		
		key = "mine";
		meanings = new ArrayList <String>();
		meanings.add("belonging to me");
		meanings.add("colliery");
		this.theWords.put(key, meanings);
		
		key = "miner";
		meanings = new ArrayList <String>();
		meanings.add("colliery worker");
		meanings.add("pit worker");
		this.theWords.put(key, meanings);
		
		key = "mini";
		meanings = new ArrayList <String>();
		meanings.add("car");
		this.theWords.put(key, meanings);
		
		key = "minion";
		meanings = new ArrayList <String>();
		meanings.add("lackey");
		this.theWords.put(key, meanings);
		
		key = "minor";
		meanings = new ArrayList <String>();
		meanings.add("child");
		meanings.add("insignificant");
		this.theWords.put(key, meanings);
		
		key = "minorca";
		meanings = new ArrayList <String>();
		meanings.add("Spanish island");
		this.theWords.put(key, meanings);
		
		key = "minister";
		meanings = new ArrayList <String>();
		meanings.add("clergyman");
		this.theWords.put(key, meanings);
		
		key = "minster";
		meanings = new ArrayList <String>();
		meanings.add("church");
		this.theWords.put(key, meanings);
		
		key = "mint";
		meanings = new ArrayList <String>();
		meanings.add("herb");
		meanings.add("in perfect condition");
		this.theWords.put(key, meanings);
		
		key = "minute";
		meanings = new ArrayList <String>();
		meanings.add("extremely small");
		meanings.add("period of time");
		this.theWords.put(key, meanings);
		
		key = "minx";
		meanings = new ArrayList <String>();
		meanings.add("mischief maker");
		this.theWords.put(key, meanings);
		
		key = "miracle";
		meanings = new ArrayList <String>();
		meanings.add("phenomenon");
		this.theWords.put(key, meanings);
		
		key = "mire";
		meanings = new ArrayList <String>();
		meanings.add("boggy ground");
		this.theWords.put(key, meanings);
		
		key = "miser";
		meanings = new ArrayList <String>();
		meanings.add("skinflint");
		this.theWords.put(key, meanings);
		
		key = "misery";
		meanings = new ArrayList <String>();
		meanings.add("grief");
		this.theWords.put(key, meanings);
		
		key = "mislay";
		meanings = new ArrayList <String>();
		meanings.add("lose");
		this.theWords.put(key, meanings);
		
		key = "misleads";
		meanings = new ArrayList <String>();
		meanings.add("deceptive");
		meanings.add("sends astray");
		this.theWords.put(key, meanings);
		
		key = "misprint";
		meanings = new ArrayList <String>();
		meanings.add("typring error");
		this.theWords.put(key, meanings);
		
		key = "miss";
		meanings = new ArrayList <String>();
		meanings.add("female");
		meanings.add("off target");
		meanings.add("title");
		meanings.add("young girl");
		this.theWords.put(key, meanings);
		
		key = "missed";
		meanings = new ArrayList <String>();
		meanings.add("overlooked");
		this.theWords.put(key, meanings);
		
		key = "mistletoe";
		meanings = new ArrayList <String>();
		meanings.add("plant");
		this.theWords.put(key, meanings);
		
		key = "mitre";
		meanings = new ArrayList <String>();
		meanings.add("hat");
		meanings.add("headdress"); 
		this.theWords.put(key, meanings);
		
		key = "mix";
		meanings = new ArrayList <String>();
		meanings.add("socialise");
		this.theWords.put(key, meanings);
				
		key = "mob";
		meanings = new ArrayList <String>();
		meanings.add("besiege");
		meanings.add("mafiosi");
		this.theWords.put(key, meanings);
		
		key = "model";
		meanings = new ArrayList <String>();
		meanings.add("ideal");
		meanings.add("poser");
		this.theWords.put(key, meanings);

		key = "models";
		meanings = new ArrayList <String>();
		meanings.add("posers");
		this.theWords.put(key, meanings);
		
		key = "mode";
		meanings = new ArrayList <String>();
		meanings.add("fashion");
		this.theWords.put(key, meanings);
		
		key = "modes";
		meanings = new ArrayList <String>();
		meanings.add("fashions");
		this.theWords.put(key, meanings);
		
		key = "monarch";
		meanings = new ArrayList <String>();
		meanings.add("sovereign");
		this.theWords.put(key, meanings);
		
		key = "monastery";
		meanings = new ArrayList <String>();
		meanings.add("abbey");
		this.theWords.put(key, meanings);
		
		key = "months";
		meanings = new ArrayList <String>();
		meanings.add("periods of time");
		this.theWords.put(key, meanings);
		
		key = "moo";
		meanings = new ArrayList <String>();
		meanings.add("low");
		this.theWords.put(key, meanings);
		
		key = "mood";
		meanings = new ArrayList <String>();
		meanings.add("temper");
		this.theWords.put(key, meanings);
		
		key = "moor";
		meanings = new ArrayList <String>();
		meanings.add("tie");
		this.theWords.put(key, meanings);
		
		key = "more";
		meanings = new ArrayList <String>();
		meanings.add("extra");
		this.theWords.put(key, meanings);
		
		key = "morose";
		meanings = new ArrayList <String>();
		meanings.add("gloomy");
		this.theWords.put(key, meanings);
				
		key = "moses";
		meanings = new ArrayList <String>();
		meanings.add("prophet");
		this.theWords.put(key, meanings);
		
		key = "mosquitoes";
		meanings = new ArrayList <String>();
		meanings.add("bloodsuckers");
		this.theWords.put(key, meanings);
		
		key = "moss";
		meanings = new ArrayList <String>();
		meanings.add("plant");
		this.theWords.put(key, meanings);
		
		key = "most";
		meanings = new ArrayList <String>();
		meanings.add("greatest amount");
		this.theWords.put(key, meanings);
		
		key = "mot";
		meanings = new ArrayList <String>();
		meanings.add("annual check");
		this.theWords.put(key, meanings);
		
		key = "moth";
		meanings = new ArrayList <String>();
		meanings.add("insect");
		this.theWords.put(key, meanings);
		
		key = "mother";
		meanings = new ArrayList <String>();
		meanings.add("parent");
		meanings.add("relative");
		this.theWords.put(key, meanings);
		
		key = "motto";
		meanings = new ArrayList <String>();
		meanings.add("proverb");
		this.theWords.put(key, meanings);
		
		key = "mount";
		meanings = new ArrayList <String>();
		meanings.add("horse");
		this.theWords.put(key, meanings);
		
		key = "mountain";
		meanings = new ArrayList <String>();
		meanings.add("peak");
		this.theWords.put(key, meanings);
		
		key = "mountaineer";
		meanings = new ArrayList <String>();
		meanings.add("climber");
		this.theWords.put(key, meanings);
		
		key = "mouse";
		meanings = new ArrayList <String>();
		meanings.add("rodent");
		this.theWords.put(key, meanings);
		
		key = "mouth";
		meanings = new ArrayList <String>();
		meanings.add("speaker");
		this.theWords.put(key, meanings);
		
		key = "mud";
		meanings = new ArrayList <String>();
		meanings.add("sludge");
		meanings.add("wet earth");
		this.theWords.put(key, meanings);
		
		key = "mug";
		meanings = new ArrayList <String>();
		meanings.add("tankard");
		this.theWords.put(key, meanings);
		
		key = "mull";
		meanings = new ArrayList <String>();
		meanings.add("warm");
		meanings.add("Scottish isle");
		this.theWords.put(key, meanings);
		
		key = "mum";
		meanings = new ArrayList <String>();
		meanings.add("mother");
		this.theWords.put(key, meanings);

		key = "mumps";
		meanings = new ArrayList <String>();
		meanings.add("disease");
		this.theWords.put(key, meanings);
		
		key = "mums";
		meanings = new ArrayList <String>();
		meanings.add("mothers");
		this.theWords.put(key, meanings);
		
		key = "murderous";
		meanings = new ArrayList <String>();
		meanings.add("bloodthirsty");
		this.theWords.put(key, meanings);
		
		key = "muse";
		meanings = new ArrayList <String>();
		meanings.add("source of inspirtion");
		this.theWords.put(key, meanings);
		
		key = "mushroom";
		meanings = new ArrayList <String>();
		meanings.add("expand suddenly");
		meanings.add("fungal growth");
		this.theWords.put(key, meanings);
		
		key = "music";
		meanings = new ArrayList <String>();
		meanings.add("entertainment");
		this.theWords.put(key, meanings);
		
		key = "musing";
		meanings = new ArrayList <String>();
		meanings.add("thinking");
		this.theWords.put(key, meanings);
		
		key = "must";
		meanings = new ArrayList <String>();
		meanings.add("be obliged to");
		this.theWords.put(key, meanings);
		
		key = "my";
		meanings = new ArrayList <String>();
		meanings.add("expression of surprise");
		this.theWords.put(key, meanings);
		
		key = "mysterious";
		meanings = new ArrayList <String>();
		meanings.add("puzzling");
		this.theWords.put(key, meanings);

	}//end loadM
	
	private void loadNWords() {
		String key;
		ArrayList <String> meanings;
		
		//load n's
		key = "nacho";
		meanings = new ArrayList <String>();
		meanings.add("Mexican snack");
		this.theWords.put(key, meanings);
		
		key = "nae";
		meanings = new ArrayList <String>();
		meanings.add("no Scots");
		meanings.add("Scots no");
		this.theWords.put(key, meanings);
		
		key = "nag";
		meanings = new ArrayList <String>();
		meanings.add("bother persistently");
		meanings.add("old horse");
		meanings.add("pester");
		meanings.add("scold");
		this.theWords.put(key, meanings);
		
		key = "nags";
		meanings = new ArrayList <String>();
		meanings.add("bothers persistently");
		meanings.add("old horses");
		meanings.add("pesters");
		meanings.add("scolds");
		this.theWords.put(key, meanings);
		
		key = "nail";
		meanings = new ArrayList <String>();
		meanings.add("fastener");
		this.theWords.put(key, meanings);
		
		key = "naive";
		meanings = new ArrayList <String>();
		meanings.add("green");
		this.theWords.put(key, meanings);
		
		key = "name";
		meanings = new ArrayList <String>();
		meanings.add("reputation");// seen in i
		meanings.add("title");
		this.theWords.put(key, meanings);
		
		key = "narcotics";
		meanings = new ArrayList <String>();
		meanings.add("drugs");
		this.theWords.put(key, meanings);
		
		key = "narnia";
		meanings = new ArrayList <String>();
		meanings.add("magical land");
		this.theWords.put(key, meanings);
		
		key = "narrate";
		meanings = new ArrayList <String>();
		meanings.add("tell tales");
		this.theWords.put(key, meanings);
		
		key = "narrowest";
		meanings = new ArrayList <String>();
		meanings.add("least broad");
		this.theWords.put(key, meanings);
		
		key = "nastier";
		meanings = new ArrayList <String>();
		meanings.add("more unkind");
		this.theWords.put(key, meanings);
		
		key = "nasty";
		meanings = new ArrayList <String>();
		meanings.add("unkind");
		this.theWords.put(key, meanings);
		
		key = "natal";
		meanings = new ArrayList <String>();
		meanings.add("part of South Africa");
		this.theWords.put(key, meanings);
				
		key = "nations";
		meanings = new ArrayList <String>();
		meanings.add("countries");
		this.theWords.put(key, meanings);
			
		key = "native";
		meanings = new ArrayList <String>();
		meanings.add("someone who lives in a place");
		this.theWords.put(key, meanings);
		
		key = "natter";
		meanings = new ArrayList <String>();
		meanings.add("chinwag");
		this.theWords.put(key, meanings);
		
		key = "nattier";
		meanings = new ArrayList <String>();
		meanings.add("being more stylish");
		this.theWords.put(key, meanings);
		
		key = "navy";
		meanings = new ArrayList <String>();
		meanings.add("many sailors");
		this.theWords.put(key, meanings);
		
		key = "nay";
		meanings = new ArrayList <String>();
		meanings.add("no");
		this.theWords.put(key, meanings);
		
		key = "nazareth";
		meanings = new ArrayList <String>();
		meanings.add("biblical city");
		this.theWords.put(key, meanings);
		
		key = "near";
		meanings = new ArrayList <String>();
		meanings.add("close");
		this.theWords.put(key, meanings);
		
		key = "neat";
		meanings = new ArrayList <String>();
		meanings.add("tidy");
		meanings.add("without water");
		this.theWords.put(key, meanings);
		
		key = "nearest";
		meanings = new ArrayList <String>();
		meanings.add("least distant");
		this.theWords.put(key, meanings);
		
		key = "nebraska";
		meanings = new ArrayList <String>();
		meanings.add("state");
		this.theWords.put(key, meanings);
				
		key = "needles";
		meanings = new ArrayList <String>();
		meanings.add("pointers");
		this.theWords.put(key, meanings);
		
		key = "needless";
		meanings = new ArrayList <String>();
		meanings.add("unnecessary");
		this.theWords.put(key, meanings);
				
		key = "negative";
		meanings = new ArrayList <String>();
		meanings.add("no");
		this.theWords.put(key, meanings);
		
		key = "nemo";
		meanings = new ArrayList <String>();
		meanings.add("captain");
		this.theWords.put(key, meanings);
		
		key = "nero";
		meanings = new ArrayList <String>();
		meanings.add("fiddler");
		this.theWords.put(key, meanings);
		
		key = "nerve";
		meanings = new ArrayList <String>();
		meanings.add("courage");
		this.theWords.put(key, meanings);
		
		key = "nervous";
		meanings = new ArrayList <String>();
		meanings.add("on edge");
		this.theWords.put(key, meanings);
		
		key = "ness";
		meanings = new ArrayList <String>();
		meanings.add("loch");
		this.theWords.put(key, meanings);
		
		key = "nest";
		meanings = new ArrayList <String>();
		meanings.add("home");
		this.theWords.put(key, meanings);
		
		key = "net";
		meanings = new ArrayList <String>();
		meanings.add("fishing aid");
		meanings.add("trap");
		this.theWords.put(key, meanings);
		
		key = "nett";
		meanings = new ArrayList <String>();
		meanings.add("after deductions");
		this.theWords.put(key, meanings);
				
		key = "never";
		meanings = new ArrayList <String>();
		meanings.add("at no time");
		this.theWords.put(key, meanings);
		
		key = "neuralgic";
		meanings = new ArrayList <String>();
		meanings.add("nervous pain");
		this.theWords.put(key, meanings);
		
		key = "neurotic";
		meanings = new ArrayList <String>();
		meanings.add("disturbed person");
		meanings.add("unstable");
		this.theWords.put(key, meanings);
		
		key = "new";
		meanings = new ArrayList <String>();
		meanings.add("novel");
		this.theWords.put(key, meanings);
		
		key = "newdelhi"; //New Delhi
		meanings = new ArrayList <String>();
		meanings.add("capital city");
		this.theWords.put(key, meanings);
		
		key = "neworleans"; //New Orleans
		meanings = new ArrayList <String>();
		meanings.add("Louisiana city");
		this.theWords.put(key, meanings);
		
		key = "news";
		meanings = new ArrayList <String>();
		meanings.add("information not previously known");
		this.theWords.put(key, meanings);
		
		key = "newspaper";
		meanings = new ArrayList <String>();
		meanings.add("rag");
		this.theWords.put(key, meanings);
		
		key = "newton";
		meanings = new ArrayList <String>();
		meanings.add("famous scientist");
		this.theWords.put(key, meanings);
		
		key = "nib";
		meanings = new ArrayList <String>();
		meanings.add("pen component");
		this.theWords.put(key, meanings);
		
		key = "nice";
		meanings = new ArrayList <String>();
		meanings.add("French resort");
		meanings.add("pleasant");
		meanings.add("resort");
		this.theWords.put(key, meanings);
		
		key = "nicer";
		meanings = new ArrayList <String>();
		meanings.add("more enjoyable");
		meanings.add("more pleasant");
		this.theWords.put(key, meanings);
				
		key = "nicest";
		meanings = new ArrayList <String>();
		meanings.add("most pleasing");
		this.theWords.put(key, meanings);
		
		key = "nick";
		meanings = new ArrayList <String>();
		meanings.add("pinch");
		meanings.add("steal");
		this.theWords.put(key, meanings);
		
		key = "nickel";
		meanings = new ArrayList <String>();
		meanings.add("coin");
		meanings.add("metal");
		this.theWords.put(key, meanings);
		
		key = "niece";
		meanings = new ArrayList <String>();
		meanings.add("relation");
		this.theWords.put(key, meanings);
		
		key = "niche";
		meanings = new ArrayList <String>();
		meanings.add("corner");
		this.theWords.put(key, meanings);
		
		key = "nigh";
		meanings = new ArrayList <String>();
		meanings.add("almost");
		this.theWords.put(key, meanings);
		
		key = "night";
		meanings = new ArrayList <String>();
		meanings.add("darkness");
		meanings.add("time for bed?");
		this.theWords.put(key, meanings);
		
		key = "nightly";
		meanings = new ArrayList <String>();
		meanings.add("nocturnal");
		this.theWords.put(key, meanings);
		
		key = "nightowl"; //night owl
		meanings = new ArrayList <String>();
		meanings.add("one's late to bed");
		this.theWords.put(key, meanings);
		
		key = "nil";
		meanings = new ArrayList <String>();
		meanings.add("nothing");
		this.theWords.put(key, meanings);
				
		key = "nile";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "nine";
		meanings = new ArrayList <String>();
		meanings.add("number");
		this.theWords.put(key, meanings);
		
		key = "nips";
		meanings = new ArrayList <String>();
		meanings.add("drinks");
		this.theWords.put(key, meanings);
		
		key = "nirvana";
		meanings = new ArrayList <String>();
		meanings.add("bliss");
		this.theWords.put(key, meanings);
		
		key = "nit";
		meanings = new ArrayList <String>();
		meanings.add("fool");
		meanings.add("young louse");
		this.theWords.put(key, meanings);
		
		key = "nitrate";
		meanings = new ArrayList <String>();
		meanings.add("salt");
		this.theWords.put(key, meanings);
		
		//this word was only added for test purposes
		//testing augmented anagram + 3 letters
		key = "nitratine";
		meanings = new ArrayList <String>();
		meanings.add("mineral");
		this.theWords.put(key, meanings);
		
		key = "nirvana";
		meanings = new ArrayList <String>();
		meanings.add("bliss");
		this.theWords.put(key, meanings);
		
		key = "no";
		meanings = new ArrayList <String>();
		meanings.add("not any");
		meanings.add("vote against");
		this.theWords.put(key, meanings);
		
		key = "noble";
		meanings = new ArrayList <String>();
		meanings.add("aristocrat");
		this.theWords.put(key, meanings);
		
		key = "nod";
		meanings = new ArrayList <String>();
		meanings.add("move head");
		meanings.add("sign of agreement");
		this.theWords.put(key, meanings);
		
		key = "nodice";// no dice
		meanings = new ArrayList <String>();
		meanings.add("negative answer");
		this.theWords.put(key, meanings);
		
		key = "noel";
		meanings = new ArrayList <String>();
		meanings.add("christmas");
		meanings.add("christmas season");
		this.theWords.put(key, meanings);
			
		key = "noise";
		meanings = new ArrayList <String>();
		meanings.add("racket");
		this.theWords.put(key, meanings);
		
		key = "nomad";
		meanings = new ArrayList <String>();
		meanings.add("wanderer");
		this.theWords.put(key, meanings);

		key = "nonchalant";
		meanings = new ArrayList <String>();
		meanings.add("cool");
		this.theWords.put(key, meanings);
		
		key = "none";
		meanings = new ArrayList <String>();
		meanings.add("not any");
		this.theWords.put(key, meanings);
		
		key = "nonplus";
		meanings = new ArrayList <String>();
		meanings.add("perplex");
		this.theWords.put(key, meanings);
				
		key = "noodles";
		meanings = new ArrayList <String>();
		meanings.add("part of a Chinese meal, perhaps");
		this.theWords.put(key, meanings);
		
		key = "nook";
		meanings = new ArrayList <String>();
		meanings.add("corner");
		meanings.add("recess");
		this.theWords.put(key, meanings);
		
		key = "noon";
		meanings = new ArrayList <String>();
		meanings.add("midday");
		this.theWords.put(key, meanings);
		
		key = "nora";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "norm";
		meanings = new ArrayList <String>();
		meanings.add("standard");
		this.theWords.put(key, meanings);
		
		key = "norma";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "norman";
		meanings = new ArrayList <String>();
		meanings.add("frenchman");
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "north";
		meanings = new ArrayList <String>();
		meanings.add("direction");
		this.theWords.put(key, meanings);
		
		key = "northern";
		meanings = new ArrayList <String>();
		meanings.add("from Newcastle, for example");
		this.theWords.put(key, meanings);
		
		key = "nose";
		meanings = new ArrayList <String>();
		meanings.add("snout");
		this.theWords.put(key, meanings);
		
		key = "nosebleed";
		meanings = new ArrayList <String>();
		meanings.add("losing blood");
		this.theWords.put(key, meanings);
		
		key = "nosh";
		meanings = new ArrayList <String>();
		meanings.add("food");
		this.theWords.put(key, meanings);
		
		key = "not";
		meanings = new ArrayList <String>();
		meanings.add("Boolean operator");
		this.theWords.put(key, meanings);
		
		key = "note";
		meanings = new ArrayList <String>();
		meanings.add("observe");
		meanings.add("paper money");
		meanings.add("short letter");
		this.theWords.put(key, meanings);
		
		key = "noted";
		meanings = new ArrayList <String>();
		meanings.add("famous");
		this.theWords.put(key, meanings);
		
		key = "notelet";
		meanings = new ArrayList <String>();
		meanings.add("short letter");
		this.theWords.put(key, meanings);
				
		key = "notes";
		meanings = new ArrayList <String>();
		meanings.add("musical sounds");
		meanings.add("records");
		this.theWords.put(key, meanings);
		
		key = "nothing";
		meanings = new ArrayList <String>();
		meanings.add("nil");
		this.theWords.put(key, meanings);
		
		key = "noticed";
		meanings = new ArrayList <String>();
		meanings.add("seen");
		this.theWords.put(key, meanings);
		
		key = "nought";
		meanings = new ArrayList <String>();
		meanings.add("nil");
		this.theWords.put(key, meanings);
		
		key = "nova";
		meanings = new ArrayList <String>();
		meanings.add("star");
		this.theWords.put(key, meanings);
		
		key = "novel";
		meanings = new ArrayList <String>();
		meanings.add("book");
		this.theWords.put(key, meanings);
		
		key = "now";
		meanings = new ArrayList <String>();
		meanings.add("immediately");
		meanings.add("today");
		this.theWords.put(key, meanings);
		
		key = "noxious";
		meanings = new ArrayList <String>();
		meanings.add("poisonous");
		this.theWords.put(key, meanings);
		
		key = "nude";
		meanings = new ArrayList <String>();
		meanings.add("naked");
		this.theWords.put(key, meanings);
		
		key = "nuisances";
		meanings = new ArrayList <String>();
		meanings.add("pests");
		this.theWords.put(key, meanings);
		
		key = "numb";
		meanings = new ArrayList <String>();
		meanings.add("unfeeling");
		this.theWords.put(key, meanings);
		
		key = "number";
		meanings = new ArrayList <String>();
		meanings.add("amount");
		this.theWords.put(key, meanings);
		
		key = "nuncio";
		meanings = new ArrayList <String>();
		meanings.add("papal ambassador");
		this.theWords.put(key, meanings);
		
		key = "nurse";
		meanings = new ArrayList <String>();
		meanings.add("tend");
		this.theWords.put(key, meanings);
		
		key = "nursing";
		meanings = new ArrayList <String>();
		meanings.add("looking after");
		meanings.add("profession");
		meanings.add("tending");
		this.theWords.put(key, meanings);
		
		key = "nut";
		meanings = new ArrayList <String>();
		meanings.add("maniac");
		this.theWords.put(key, meanings);
		
		key = "nylon";
		meanings = new ArrayList <String>();
		meanings.add("stocking material");
		this.theWords.put(key, meanings);

	}//end loadN
	
	private void loadOWords() {
		String key;
		ArrayList <String> meanings;
		
		//load o's
		key = "oak";
		meanings = new ArrayList <String>();
		meanings.add("tree");
		this.theWords.put(key, meanings);
		
		key = "oat";
		meanings = new ArrayList <String>();
		meanings.add("cereal");
		this.theWords.put(key, meanings);
		
		key = "oatcake";
		meanings = new ArrayList <String>();
		meanings.add("Scottish biscuit");
		this.theWords.put(key, meanings);
		
		key = "oath";
		meanings = new ArrayList <String>();
		meanings.add("promise");
		this.theWords.put(key, meanings);
		
		key = "oatmeal";
		meanings = new ArrayList <String>();
		meanings.add("food");
		this.theWords.put(key, meanings);
		
		key = "oats";
		meanings = new ArrayList <String>();
		meanings.add("cereal");
		this.theWords.put(key, meanings);
		
		key = "obey";
		meanings = new ArrayList <String>();
		meanings.add("take orders");
		this.theWords.put(key, meanings);
		
		key = "oblong";
		meanings = new ArrayList <String>();
		meanings.add("shape");
		this.theWords.put(key, meanings);
		
		key = "oboes";
		meanings = new ArrayList <String>();
		meanings.add("instrument");
		this.theWords.put(key, meanings);
		
		key = "obnoxious";
		meanings = new ArrayList <String>();
		meanings.add("offensive");
		this.theWords.put(key, meanings);
		
		key = "obscene";
		meanings = new ArrayList <String>();
		meanings.add("disgusting");
		this.theWords.put(key, meanings);
		
		key = "obscure";
		meanings = new ArrayList <String>();
		meanings.add("not easily understood");
		this.theWords.put(key, meanings);
		
		key = "observe";
		meanings = new ArrayList <String>();
		meanings.add("watch");
		this.theWords.put(key, meanings);
		
		key = "obsolete";
		meanings = new ArrayList <String>();
		meanings.add("out of date");
		this.theWords.put(key, meanings);
		
		key = "obstinate";
		meanings = new ArrayList <String>();
		meanings.add("contrary");
		meanings.add("stubborn");
		this.theWords.put(key, meanings);
				
		key = "obtain";
		meanings = new ArrayList <String>();
		meanings.add("get");
		this.theWords.put(key, meanings);
				
		key = "ocean";
		meanings = new ArrayList <String>();
		meanings.add("sea");
		this.theWords.put(key, meanings);
		
		key = "ocelots";
		meanings = new ArrayList <String>();
		meanings.add("cats");
		this.theWords.put(key, meanings);
		
		key = "octet";
		meanings = new ArrayList <String>();
		meanings.add("group of eight");
		this.theWords.put(key, meanings);
		
		key = "octopus";
		meanings = new ArrayList <String>();
		meanings.add("denizen of the deep");
		this.theWords.put(key, meanings);
		
		key = "odd";
		meanings = new ArrayList <String>();
		meanings.add("rum");
		meanings.add("strange");
		this.theWords.put(key, meanings);
		
		key = "oddest";
		meanings = new ArrayList <String>();
		meanings.add("most peculiar");
		this.theWords.put(key, meanings);
		
		key = "oddity";
		meanings = new ArrayList <String>();
		meanings.add("peculiar thing");
		this.theWords.put(key, meanings);
		
		key = "oddson"; //odds on
		meanings = new ArrayList <String>();
		meanings.add("better than even chance");
		meanings.add("very likely to happen");
		this.theWords.put(key, meanings);
		
		key = "ode";
		meanings = new ArrayList <String>();
		meanings.add("poem");
		this.theWords.put(key, meanings);
		
		key = "odes";
		meanings = new ArrayList <String>();
		meanings.add("poems");
		this.theWords.put(key, meanings);
		
		key = "odessa";
		meanings = new ArrayList <String>();
		meanings.add("Black Sea port");
		this.theWords.put(key, meanings);
		
		key = "odin";
		meanings = new ArrayList <String>();
		meanings.add("Norse god");
		this.theWords.put(key, meanings);
		
		key = "odourless";
		meanings = new ArrayList <String>();
		meanings.add("lacking scent");
		this.theWords.put(key, meanings);
		
		key = "of";
		meanings = new ArrayList <String>();
		meanings.add("from");
		this.theWords.put(key, meanings);
		
		key = "off";
		meanings = new ArrayList <String>();
		meanings.add("absent");
		meanings.add("not on");
		meanings.add("on holiday");
		meanings.add("rancid");
		meanings.add("start of race");
		this.theWords.put(key, meanings);
		
		key = "offal";
		meanings = new ArrayList <String>();
		meanings.add("waste meat");
		this.theWords.put(key, meanings);
		
		key = "offbeat";
		meanings = new ArrayList <String>();
		meanings.add("eccentric");
		this.theWords.put(key, meanings);
		
		key = "offer";
		meanings = new ArrayList <String>();
		meanings.add("tender");
		this.theWords.put(key, meanings);
		
		key = "offhand";
		meanings = new ArrayList <String>();
		meanings.add("casual");
		this.theWords.put(key, meanings);
		
		key = "official";
		meanings = new ArrayList <String>();
		meanings.add("formal");
		this.theWords.put(key, meanings);
		
		key = "oft";
		meanings = new ArrayList <String>();
		meanings.add("many a time");
		this.theWords.put(key, meanings);
		
		key = "often";
		meanings = new ArrayList <String>();
		meanings.add("frequently");
		this.theWords.put(key, meanings);
		
		key = "ogle";
		meanings = new ArrayList <String>();
		meanings.add("leer");
		this.theWords.put(key, meanings);
		
		key = "ogling";
		meanings = new ArrayList <String>();
		meanings.add("leering");
		this.theWords.put(key, meanings);
		
		key = "ok";
		meanings = new ArrayList <String>();
		meanings.add("acceptable");
		meanings.add("all right");
		meanings.add("approval");
		meanings.add("go ahead");
		this.theWords.put(key, meanings);
		
		key = "oil";
		meanings = new ArrayList <String>();
		meanings.add("lubricate");
		meanings.add("petroleum");
		this.theWords.put(key, meanings);
		
		key = "oils";
		meanings = new ArrayList <String>();
		meanings.add("lubricates");
		meanings.add("puts grease on");
		this.theWords.put(key, meanings);
		
		key = "oilskin";
		meanings = new ArrayList <String>();
		meanings.add("protective coat");
		this.theWords.put(key, meanings);
		
		key = "oiled";
		meanings = new ArrayList <String>();
		meanings.add("drunk");
		this.theWords.put(key, meanings);
		
		key = "ointment";
		meanings = new ArrayList <String>();
		meanings.add("medicinal cream");
		this.theWords.put(key, meanings);
				
		key = "okra";
		meanings = new ArrayList <String>();
		meanings.add("vegetable");
		this.theWords.put(key, meanings);
		
		key = "old";
		meanings = new ArrayList <String>();
		meanings.add("ancient");
		meanings.add("not new");
		this.theWords.put(key, meanings);
		
		key = "olden";
		meanings = new ArrayList <String>();
		meanings.add("of former times");
		this.theWords.put(key, meanings);
		
		key = "ole";
		meanings = new ArrayList <String>();
		meanings.add("Spanish cry");
		this.theWords.put(key, meanings);
		
		key = "olympus";
		meanings = new ArrayList <String>();
		meanings.add("Greek mountain");
		this.theWords.put(key, meanings);
		
		key = "omen";
		meanings = new ArrayList <String>();
		meanings.add("sign");
		this.theWords.put(key, meanings);
				
		key = "omit";
		meanings = new ArrayList <String>();
		meanings.add("leave out");
		this.theWords.put(key, meanings);
		
		key = "omitted";
		meanings = new ArrayList <String>();
		meanings.add("left out");
		this.theWords.put(key, meanings);
		
		key = "on";
		meanings = new ArrayList <String>();
		meanings.add("appearing");
		meanings.add("performing");
		meanings.add("supported by");
		meanings.add("working");
		this.theWords.put(key, meanings);
				
		key = "once";
		meanings = new ArrayList <String>();
		meanings.add("at some time");
		meanings.add("formerely");
		this.theWords.put(key, meanings);
		
		key = "one";
		meanings = new ArrayList <String>();
		meanings.add("number");
		meanings.add("unit");
		this.theWords.put(key, meanings);
		
		key = "onerous";
		meanings = new ArrayList <String>();
		meanings.add("weighty");
		this.theWords.put(key, meanings);
		
		key = "onus";
		meanings = new ArrayList <String>();
		meanings.add("responsibility");
		this.theWords.put(key, meanings);
		
		key = "oodles";
		meanings = new ArrayList <String>();
		meanings.add("lots");
		this.theWords.put(key, meanings);
		
		key = "opal";
		meanings = new ArrayList <String>();
		meanings.add("jewel");
		this.theWords.put(key, meanings);
		
		key = "open";
		meanings = new ArrayList <String>();
		meanings.add("golf tournament");
		meanings.add("sporting competition");
		meanings.add("unlocked");
		meanings.add("unrestricted");
		this.theWords.put(key, meanings);
		
		key = "openair"; //open air
		meanings = new ArrayList <String>();
		meanings.add("out of doors");
		this.theWords.put(key, meanings);
		
		key = "openly";
		meanings = new ArrayList <String>();
		meanings.add("freely");
		this.theWords.put(key, meanings);
		
		key = "opera";
		meanings = new ArrayList <String>();
		meanings.add("several works");
		meanings.add("musical drama");
		meanings.add("musical entertainment");
		this.theWords.put(key, meanings);
		
		key = "operation";
		meanings = new ArrayList <String>();
		meanings.add("surgery");
		this.theWords.put(key, meanings);
		
		key = "operetta";
		meanings = new ArrayList <String>();
		meanings.add("show");
		this.theWords.put(key, meanings);
		
		key = "opinion";
		meanings = new ArrayList <String>();
		meanings.add("view");
		this.theWords.put(key, meanings);
		
		key = "oppress";
		meanings = new ArrayList <String>();
		meanings.add("persecute");
		this.theWords.put(key, meanings);
				
		key = "opus";
		meanings = new ArrayList <String>();
		meanings.add("work");
		this.theWords.put(key, meanings);
		
		key = "or";
		meanings = new ArrayList <String>();
		meanings.add("either");
		this.theWords.put(key, meanings);
		
		key = "orange";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "oranges";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "orate";
		meanings = new ArrayList <String>();
		meanings.add("give a speech");
		meanings.add("speak publicly");
		meanings.add("speak pompously");
		this.theWords.put(key, meanings);
		
		key = "oration";
		meanings = new ArrayList <String>();
		meanings.add("lecture");
		meanings.add("speech");
		this.theWords.put(key, meanings);
		
		key = "orb";
		meanings = new ArrayList <String>();
		meanings.add("globe");
		this.theWords.put(key, meanings);
		
		key = "orchestra";
		meanings = new ArrayList <String>();
		meanings.add("musicians");
		this.theWords.put(key, meanings);
		
		key = "ordain";
		meanings = new ArrayList <String>();
		meanings.add("decree");
		this.theWords.put(key, meanings);
		
		key = "ordeal";
		meanings = new ArrayList <String>();
		meanings.add("trial");
		this.theWords.put(key, meanings);
		
		key = "ordinarily";
		meanings = new ArrayList <String>();
		meanings.add("normally");
		this.theWords.put(key, meanings);
				
		key = "ordnance";
		meanings = new ArrayList <String>();
		meanings.add("artillery");
		this.theWords.put(key, meanings);
				
		key = "ore";
		meanings = new ArrayList <String>();
		meanings.add("output of mine");
		meanings.add("swedish money");
		this.theWords.put(key, meanings);
		
		key = "organ";
		meanings = new ArrayList <String>();
		meanings.add("instrument");
		this.theWords.put(key, meanings);
		
		key = "organism";
		meanings = new ArrayList <String>();
		meanings.add("living thing");
		this.theWords.put(key, meanings);
		
		key = "organist";
		meanings = new ArrayList <String>();
		meanings.add("musician");
		this.theWords.put(key, meanings);
		
		key = "ornate";
		meanings = new ArrayList <String>();
		meanings.add("elaborate");
		meanings.add("elaborately decorated");
		meanings.add("fancy");
		this.theWords.put(key, meanings);
		
		key = "ornately";
		meanings = new ArrayList <String>();
		meanings.add("flamboyantly");
		this.theWords.put(key, meanings);
		
		key = "oscar";
		meanings = new ArrayList <String>();
		meanings.add("film award");
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "ostler";
		meanings = new ArrayList <String>();
		meanings.add("stable boy");
		this.theWords.put(key, meanings);
		
		key = "othello";
		meanings = new ArrayList <String>();
		meanings.add("Shakespearean character");
		this.theWords.put(key, meanings);
		
		key = "other";
		meanings = new ArrayList <String>();
		meanings.add("alternative");
		this.theWords.put(key, meanings);
		
		key = "otis";
		meanings = new ArrayList <String>();
		meanings.add("manf");
		this.theWords.put(key, meanings);
		
		key = "otter";
		meanings = new ArrayList <String>();
		meanings.add("aquatic creature");
		this.theWords.put(key, meanings);
		
		key = "otters";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		this.theWords.put(key, meanings);
		
		key = "ought";
		meanings = new ArrayList <String>();
		meanings.add("should be");
		this.theWords.put(key, meanings);
		
		key = "ounce";
		meanings = new ArrayList <String>();
		meanings.add("weight");
		this.theWords.put(key, meanings);
		
		key = "our";
		meanings = new ArrayList <String>();
		meanings.add("belonging to");
		this.theWords.put(key, meanings);
		
		key = "ours";
		meanings = new ArrayList <String>();
		meanings.add("belonging to us");
		this.theWords.put(key, meanings);
		
		key = "out";
		meanings = new ArrayList <String>();
		meanings.add("not at home");
		this.theWords.put(key, meanings);
		
		key = "outcast";
		meanings = new ArrayList <String>();
		meanings.add("pariah");
		this.theWords.put(key, meanings);
		
		key = "outer";
		meanings = new ArrayList <String>();
		meanings.add("exterior");
		this.theWords.put(key, meanings);
		
		key = "outfit";
		meanings = new ArrayList <String>();
		meanings.add("clothes");
		meanings.add("company");
		this.theWords.put(key, meanings);
		
		key = "outlaw";
		meanings = new ArrayList <String>();
		meanings.add("ban");
		meanings.add("bandit");
		this.theWords.put(key, meanings);
		
		key = "outpace";
		meanings = new ArrayList <String>();
		meanings.add("exceed");
		meanings.add("surpass");
		this.theWords.put(key, meanings);
		
		key = "outrage";
		meanings = new ArrayList <String>();
		meanings.add("anger");
		meanings.add("fury");
		meanings.add("indignation");
		this.theWords.put(key, meanings);
		
		key = "outsider";
		meanings = new ArrayList <String>();
		meanings.add("stranger");
		this.theWords.put(key, meanings);
		
		key = "oval";
		meanings = new ArrayList <String>();
		meanings.add("shape");
		this.theWords.put(key, meanings);
		
		key = "ovaries";
		meanings = new ArrayList <String>();
		meanings.add("organs");
		this.theWords.put(key, meanings);
		
		key = "ovation";
		meanings = new ArrayList <String>();
		meanings.add("applause");
		this.theWords.put(key, meanings);
		
		key = "over";
		meanings = new ArrayList <String>();
		meanings.add("cricket period");
		meanings.add("finished");
		meanings.add("six deliveries");
		this.theWords.put(key, meanings);
		
		key = "overlook";
		meanings = new ArrayList <String>();
		meanings.add("ignore");
		meanings.add("survey");
		this.theWords.put(key, meanings);
		
		key = "overt";
		meanings = new ArrayList <String>();
		meanings.add("frank");
		meanings.add("unconcealed");
		this.theWords.put(key, meanings);
		
		key = "ovid";
		meanings = new ArrayList <String>();
		meanings.add("Roman poet");
		this.theWords.put(key, meanings);
		
		key = "owing";
		meanings = new ArrayList <String>();
		meanings.add("in debt");
		this.theWords.put(key, meanings);
		
		key = "owl";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "owlet";
		meanings = new ArrayList <String>();
		meanings.add("little bird");
		this.theWords.put(key, meanings);
		
		key = "owls";
		meanings = new ArrayList <String>();
		meanings.add("birds");
		meanings.add("hooters");
		this.theWords.put(key, meanings);
		
		key = "ox";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "oxford";
		meanings = new ArrayList <String>();
		meanings.add("university city");
		this.theWords.put(key, meanings);
		
		key = "oyster";
		meanings = new ArrayList <String>();
		meanings.add("marine creature");
		this.theWords.put(key, meanings);
		
		key = "ozone";
		meanings = new ArrayList <String>();
		meanings.add("atmosphere layer");
		this.theWords.put(key, meanings);

	}//end loadOWords
	
	private void loadPWords() {
		String key;
		ArrayList <String> meanings;
		
		//load p's
		key = "pace";
		meanings = new ArrayList <String>();
		meanings.add("speed");
		meanings.add("step");
		meanings.add("rate");
		this.theWords.put(key, meanings);
		
		key = "paces";
		meanings = new ArrayList <String>();
		meanings.add("steps");
		this.theWords.put(key, meanings);
		
		key = "pacing";
		meanings = new ArrayList <String>();
		meanings.add("regulating speed");
		meanings.add("speed");
		meanings.add("striding");
		this.theWords.put(key, meanings);
		
		key = "pact";
		meanings = new ArrayList <String>();
		meanings.add("agreement");
		this.theWords.put(key, meanings);
		
		key = "pacy";
		meanings = new ArrayList <String>();
		meanings.add("fast moving");
		this.theWords.put(key, meanings);
		
		key = "page";
		meanings = new ArrayList <String>();
		meanings.add("attendant");
		meanings.add("summon");
		this.theWords.put(key, meanings);
		
		key = "pageant";
		meanings = new ArrayList <String>();
		meanings.add("procession");
		this.theWords.put(key, meanings);
		
		key = "pail";
		meanings = new ArrayList <String>();
		meanings.add("bucket");
		this.theWords.put(key, meanings);
		
		key = "pain";
		meanings = new ArrayList <String>();
		meanings.add("ache");
		meanings.add("nuisance");
		this.theWords.put(key, meanings);
		
		key = "painless";
		meanings = new ArrayList <String>();
		meanings.add("uncomplicated");
		this.theWords.put(key, meanings);
		
		key = "painter";
		meanings = new ArrayList <String>();
		meanings.add("decorator");
		meanings.add("artist");
		this.theWords.put(key, meanings);
		
		key = "paints";
		meanings = new ArrayList <String>();
		meanings.add("depicts");
		this.theWords.put(key, meanings);
		
		key = "pair";
		meanings = new ArrayList <String>();
		meanings.add("couple");
		meanings.add("two");
		this.theWords.put(key, meanings);
		
		key = "pairs";
		meanings = new ArrayList <String>();
		meanings.add("couples");
		this.theWords.put(key, meanings);
		
		key = "pal";
		meanings = new ArrayList <String>();
		meanings.add("friend");
		this.theWords.put(key, meanings);
		
		key = "palace";
		meanings = new ArrayList <String>();
		meanings.add("ruler's official residence");
		this.theWords.put(key, meanings);
		
		key = "palaver";
		meanings = new ArrayList <String>();
		meanings.add("rigmarole");
		this.theWords.put(key, meanings);
		
		key = "pale";
		meanings = new ArrayList <String>();
		meanings.add("ashen");
		this.theWords.put(key, meanings);
		
		key = "paler";
		meanings = new ArrayList <String>();
		meanings.add("lighter");
		this.theWords.put(key, meanings);
		
		key = "palette";
		meanings = new ArrayList <String>();
		meanings.add("mixing board");
		this.theWords.put(key, meanings);
		
		key = "paltry";
		meanings = new ArrayList <String>();
		meanings.add("insignificant");
		this.theWords.put(key, meanings);
		
		key = "pan";
		meanings = new ArrayList <String>();
		meanings.add("criticise");
		meanings.add("god");
		meanings.add("kitchen utensil");
		this.theWords.put(key, meanings);
		
		key = "pangs";
		meanings = new ArrayList <String>();
		meanings.add("twinges of conscience");
		this.theWords.put(key, meanings);
		
		key = "panama";
		meanings = new ArrayList <String>();
		meanings.add("canal");
		meanings.add("country");
		meanings.add("South American canal");
		this.theWords.put(key, meanings);
		
		key = "panic";
		meanings = new ArrayList <String>();
		meanings.add("alarm");
		this.theWords.put(key, meanings);
		
		key = "panics";
		meanings = new ArrayList <String>();
		meanings.add("alarms");
		this.theWords.put(key, meanings);
		
		key = "panorama";
		meanings = new ArrayList <String>();
		meanings.add("extensive view");
		meanings.add("view");
		this.theWords.put(key, meanings);
		
		key = "pans";
		meanings = new ArrayList <String>();
		meanings.add("pots");
		this.theWords.put(key, meanings);
		
		key = "pansy";
		meanings = new ArrayList <String>();
		meanings.add("flower");
		this.theWords.put(key, meanings);
		
		key = "pant";
		meanings = new ArrayList <String>();
		meanings.add("breathe heavily");
		meanings.add("gasp");
		meanings.add("wheeze");
		this.theWords.put(key, meanings);
		
		key = "panthers";
		meanings = new ArrayList <String>();
		meanings.add("wild cats");
		this.theWords.put(key, meanings);
		
		key = "panto";
		meanings = new ArrayList <String>();
		meanings.add("seasonal entertainment");
		this.theWords.put(key, meanings);
		
		key = "pants";
		meanings = new ArrayList <String>();
		meanings.add("clothing");
		meanings.add("garments");
		this.theWords.put(key, meanings);
		
		key = "paper";
		meanings = new ArrayList <String>();
		meanings.add("material made from wood");
		meanings.add("decorate");
		this.theWords.put(key, meanings);
		
		key = "paperbacks";
		meanings = new ArrayList <String>();
		meanings.add("books");
		this.theWords.put(key, meanings);
		
		key = "papers";
		meanings = new ArrayList <String>();
		meanings.add("documents");
		this.theWords.put(key, meanings);
		
		key = "par";
		meanings = new ArrayList <String>();
		meanings.add("average");
		this.theWords.put(key, meanings);
		
		key = "parade";
		meanings = new ArrayList <String>();
		meanings.add("air");
		meanings.add("display");
		this.theWords.put(key, meanings);
		
		key = "paradise";
		meanings = new ArrayList <String>();
		meanings.add("heaven");
		this.theWords.put(key, meanings);
		
		key = "parent";
		meanings = new ArrayList <String>();
		meanings.add("guardian");
		this.theWords.put(key, meanings);
		
		key = "paris";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "parish";
		meanings = new ArrayList <String>();
		meanings.add("churchgoers");
		this.theWords.put(key, meanings);
		
		key = "park";
		meanings = new ArrayList <String>();
		meanings.add("leave car");
		meanings.add("recreation area");
		this.theWords.put(key, meanings);
		
		key = "parka";
		meanings = new ArrayList <String>();
		meanings.add("coat");
		this.theWords.put(key, meanings);
		
		key = "parley";
		meanings = new ArrayList <String>();
		meanings.add("conference");
		this.theWords.put(key, meanings);
		
		key = "parmesan";
		meanings = new ArrayList <String>();
		meanings.add("cheese");
		meanings.add("hard cheese");
		this.theWords.put(key, meanings);
		
		key = "part";
		meanings = new ArrayList <String>();
		meanings.add("fraction");
		meanings.add("role");
		this.theWords.put(key, meanings);
		
		key = "partly";
		meanings = new ArrayList <String>();
		meanings.add("somewhat");
		this.theWords.put(key, meanings);
		
		key = "parse";
		meanings = new ArrayList <String>();
		meanings.add("analyse");
		meanings.add("split into pieces");
		this.theWords.put(key, meanings);
		
		key = "parsley";
		meanings = new ArrayList <String>();
		meanings.add("herb");
		this.theWords.put(key, meanings);
		
		key = "parsnips";
		meanings = new ArrayList <String>();
		meanings.add("vegetables");
		this.theWords.put(key, meanings);
		
		key = "part";
		meanings = new ArrayList <String>();
		meanings.add("role");
		this.theWords.put(key, meanings);
		
		key = "party";
		meanings = new ArrayList <String>();
		meanings.add("political group");
		this.theWords.put(key, meanings);
		
		key = "pass";
		meanings = new ArrayList <String>();
		meanings.add("entry document");
		meanings.add("don't ask me");
		this.theWords.put(key, meanings);
		
		key = "passed";
		meanings = new ArrayList <String>();
		meanings.add("didn't fail");
		this.theWords.put(key, meanings);
		
		key = "past";
		meanings = new ArrayList <String>();
		meanings.add("finished");
		this.theWords.put(key, meanings);
		
		key = "pasta";
		meanings = new ArrayList <String>();
		meanings.add("dish from Italy");
		this.theWords.put(key, meanings);
		
		key = "pat";
		meanings = new ArrayList <String>();
		meanings.add("postman");
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "patch";
		meanings = new ArrayList <String>();
		meanings.add("eye protector");
		this.theWords.put(key, meanings);
		
		key = "patriot";
		meanings = new ArrayList <String>();
		meanings.add("nationalist");
		this.theWords.put(key, meanings);
		
		key = "patriotic";
		meanings = new ArrayList <String>();
		meanings.add("nationalistic");
		this.theWords.put(key, meanings);
		
		key = "patella";
		meanings = new ArrayList <String>();
		meanings.add("kneecap");
		this.theWords.put(key, meanings);
		
		key = "paul";
		meanings = new ArrayList <String>();
		meanings.add("man");
		meanings.add("saint");
		this.theWords.put(key, meanings);
		
		key = "paw";
		meanings = new ArrayList <String>();
		meanings.add("animal's foot");
		this.theWords.put(key, meanings);
		
		key = "pay";
		meanings = new ArrayList <String>();
		meanings.add("income");
		this.theWords.put(key, meanings);
		
		key = "payphone";
		meanings = new ArrayList <String>();
		meanings.add("communications device");
		this.theWords.put(key, meanings);
		
		key = "pea";
		meanings = new ArrayList <String>();
		meanings.add("vegetable");
		this.theWords.put(key, meanings);
		
		key = "peace";
		meanings = new ArrayList <String>();
		meanings.add("serenity");
		meanings.add("tranquility");
		this.theWords.put(key, meanings);
				
		key = "peach";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "peak";
		meanings = new ArrayList <String>();
		meanings.add("highest position");
		this.theWords.put(key, meanings);

		key = "peal";
		meanings = new ArrayList <String>();
		meanings.add("chime");
		this.theWords.put(key, meanings);
		
		key = "peanut";
		meanings = new ArrayList <String>();
		meanings.add("snack");
		meanings.add("inadequate sum of money");
		meanings.add("very little money");
		this.theWords.put(key, meanings);
				
		key = "peanuts";
		meanings = new ArrayList <String>();
		meanings.add("inadequate sum of money");
		meanings.add("cartoon strip");
		this.theWords.put(key, meanings);
		
		key = "pear";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "pearl";
		meanings = new ArrayList <String>();
		meanings.add("contents of oyster");
		this.theWords.put(key, meanings);
		
		key = "pears";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "peas";
		meanings = new ArrayList <String>();
		meanings.add("vegetables");
		this.theWords.put(key, meanings);
				
		key = "peasant";
		meanings = new ArrayList <String>();
		meanings.add("countryman");
		this.theWords.put(key, meanings);
		
		key = "pedant";
		meanings = new ArrayList <String>();
		meanings.add("stickler");
		this.theWords.put(key, meanings);
		
		key = "pedometer";
		meanings = new ArrayList <String>();
		meanings.add("it measures the steps taken");
		this.theWords.put(key, meanings);
		
		key = "peerage";
		meanings = new ArrayList <String>();
		meanings.add("aristocracy");
		this.theWords.put(key, meanings);
		
		key = "peered";
		meanings = new ArrayList <String>();
		meanings.add("looked");
		this.theWords.put(key, meanings);
		
		key = "pele";
		meanings = new ArrayList <String>();
		meanings.add("footballer");
		this.theWords.put(key, meanings);
		
		key = "pellet";
		meanings = new ArrayList <String>();
		meanings.add("little ball");
		this.theWords.put(key, meanings);
		
		key = "pelmet";
		meanings = new ArrayList <String>();
		meanings.add("ornamental drapery");
		this.theWords.put(key, meanings);
		
		key = "pelvis";
		meanings = new ArrayList <String>();
		meanings.add("bony structure");
		this.theWords.put(key, meanings);
		
		key = "pen";
		meanings = new ArrayList <String>();
		meanings.add("writer");
		this.theWords.put(key, meanings);
		
		key = "penchant";
		meanings = new ArrayList <String>();
		meanings.add("fondness");
		this.theWords.put(key, meanings);
		
		key = "pent";
		meanings = new ArrayList <String>();
		meanings.add("cooped up");
		this.theWords.put(key, meanings);
		
		key = "pentangle";
		meanings = new ArrayList <String>();
		meanings.add("magical shape");
		this.theWords.put(key, meanings);
		
		key = "people";
		meanings = new ArrayList <String>();
		meanings.add("nation");
		this.theWords.put(key, meanings);
		
		key = "per";
		meanings = new ArrayList <String>();
		meanings.add("for each");
		this.theWords.put(key, meanings);
		
		key = "period";
		meanings = new ArrayList <String>();
		meanings.add("full stop");
		meanings.add("time");
		this.theWords.put(key, meanings);
		
		key = "persia";
		meanings = new ArrayList <String>();
		meanings.add("old country");
		this.theWords.put(key, meanings);
		
		key = "persist";
		meanings = new ArrayList <String>();
		meanings.add("carry on");
		this.theWords.put(key, meanings);
		
		key = "person";
		meanings = new ArrayList <String>();
		meanings.add("human being");
		this.theWords.put(key, meanings);
		
		key = "personal";
		meanings = new ArrayList <String>();
		meanings.add("private");
		this.theWords.put(key, meanings);
		
		key = "pest";
		meanings = new ArrayList <String>();
		meanings.add("nuisance");
		this.theWords.put(key, meanings);
		
		key = "pestle";
		meanings = new ArrayList <String>();
		meanings.add("pounding tool");
		this.theWords.put(key, meanings);
		
		key = "pet";
		meanings = new ArrayList <String>();
		meanings.add("domesticated animal");
		meanings.add("favourite");
		this.theWords.put(key, meanings);
		
		key = "peter";
		meanings = new ArrayList <String>();
		meanings.add("saint");
		this.theWords.put(key, meanings);
		
		key = "petite";
		meanings = new ArrayList <String>();
		meanings.add("little");
		meanings.add("small");
		this.theWords.put(key, meanings);
		
		key = "petitioner";
		meanings = new ArrayList <String>();
		meanings.add("one wit a suit");
		this.theWords.put(key, meanings);
		
		key = "petunia";
		meanings = new ArrayList <String>();
		meanings.add("flower");
		this.theWords.put(key, meanings);
		
		key = "pew";
		meanings = new ArrayList <String>();
		meanings.add("seat");
		this.theWords.put(key, meanings);
		
		key = "phantom";
		meanings = new ArrayList <String>();
		meanings.add("spirit");
		this.theWords.put(key, meanings);
		
		key = "pheasant";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "photo";
		meanings = new ArrayList <String>();
		meanings.add("snap");
		this.theWords.put(key, meanings);
		
		key = "photos";
		meanings = new ArrayList <String>();
		meanings.add("pics");
		this.theWords.put(key, meanings);
		
		key = "piano";
		meanings = new ArrayList <String>();
		meanings.add("instrument");
		this.theWords.put(key, meanings);
			
		key = "pic";
		meanings = new ArrayList <String>();
		meanings.add("card with a face on it");//??
		meanings.add("little picture");
		this.theWords.put(key, meanings);
		
		key = "picas";
		meanings = new ArrayList <String>();
		meanings.add("units of measurement");
		this.theWords.put(key, meanings);
		
		key = "picasso";
		meanings = new ArrayList <String>();
		meanings.add("painter");
		this.theWords.put(key, meanings);
		
		key = "pickle";
		meanings = new ArrayList <String>();
		meanings.add("difficult situation");
		meanings.add("relish");
		this.theWords.put(key, meanings);

		key = "pie";
		meanings = new ArrayList <String>();
		meanings.add("food");
		meanings.add("something to eat");
		this.theWords.put(key, meanings);
		
		key = "pier";
		meanings = new ArrayList <String>();
		meanings.add("jetty");
		meanings.add("seaside attraction");
		this.theWords.put(key, meanings);
		
		key = "pierce";
		meanings = new ArrayList <String>();
		meanings.add("make hole in");
		meanings.add("skewer");
		this.theWords.put(key, meanings);
		
		key = "pig";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("porker");
		this.theWords.put(key, meanings);
		
		key = "pigs";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		meanings.add("porkers");
		this.theWords.put(key, meanings);
		
		key = "piglet";
		meanings = new ArrayList <String>();
		meanings.add("small animal");
		this.theWords.put(key, meanings);
		
		key = "pigs";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		meanings.add("porkers");
		this.theWords.put(key, meanings);
		
		key = "pile";
		meanings = new ArrayList <String>();
		meanings.add("heap");
		this.theWords.put(key, meanings);
		
		key = "pilot";
		meanings = new ArrayList <String>();
		meanings.add("aviator");
		this.theWords.put(key, meanings);
		
		key = "pin";
		meanings = new ArrayList <String>();
		meanings.add("fastener");
		this.theWords.put(key, meanings);
		
		key = "pine";
		meanings = new ArrayList <String>();
		meanings.add("tree");
		this.theWords.put(key, meanings);
		
		key = "pineapple";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "ping";
		meanings = new ArrayList <String>();
		meanings.add("noise");
		this.theWords.put(key, meanings);
		
		key = "pings";
		meanings = new ArrayList <String>();
		meanings.add("high-pitched sounds");
		this.theWords.put(key, meanings);
		
		key = "pinion";
		meanings = new ArrayList <String>();
		meanings.add("wing");
		this.theWords.put(key, meanings);
		
		key = "pint";
		meanings = new ArrayList <String>();
		meanings.add("unit of volume");
		meanings.add("beer");
		this.theWords.put(key, meanings);
		
		key = "pious";
		meanings = new ArrayList <String>();
		meanings.add("devout");
		this.theWords.put(key, meanings);
		
		key = "pirate";
		meanings = new ArrayList <String>();
		meanings.add("villan at sea");
		meanings.add("illegaly copy");
		this.theWords.put(key, meanings);
		
		key = "piste";
		meanings = new ArrayList <String>();
		meanings.add("ski slope");
		this.theWords.put(key, meanings);
				
		key = "pit";
		meanings = new ArrayList <String>();
		meanings.add("mine");
		this.theWords.put(key, meanings);
		
		key = "pithy";
		meanings = new ArrayList <String>();
		meanings.add("laconic");
		this.theWords.put(key, meanings);
		
		key = "pitta";
		meanings = new ArrayList <String>();
		meanings.add("Greek bread");
		this.theWords.put(key, meanings);
		
		key = "pity";
		meanings = new ArrayList <String>();
		meanings.add("shame");
		this.theWords.put(key, meanings);
		
		key = "pius";
		meanings = new ArrayList <String>();
		meanings.add("pope");
		this.theWords.put(key, meanings);
		
		key = "place";
		meanings = new ArrayList <String>();
		meanings.add("a certain spot");
		this.theWords.put(key, meanings);
		
		key = "placenta";
		meanings = new ArrayList <String>();
		meanings.add("organ");
		this.theWords.put(key, meanings);
		
		key = "places";
		meanings = new ArrayList <String>();
		meanings.add("certain spots");
		meanings.add("puts");
		this.theWords.put(key, meanings);
		
		key = "plaice";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "plain";
		meanings = new ArrayList <String>();
		meanings.add("flat");
		meanings.add("simple");
		this.theWords.put(key, meanings);
		
		key = "plan";
		meanings = new ArrayList <String>();
		meanings.add("have in mind");
		meanings.add("intend");
		meanings.add("scale drawing");
		this.theWords.put(key, meanings);
		
		key = "plane";
		meanings = new ArrayList <String>();
		meanings.add("completely level");
		this.theWords.put(key, meanings);
		
		key = "planes";
		meanings = new ArrayList <String>();
		meanings.add("aircraft");
		meanings.add("trees");
		this.theWords.put(key, meanings);
		
		key = "plank";
		meanings = new ArrayList <String>();
		meanings.add("strip of wood");
		this.theWords.put(key, meanings);
		
		key = "plankton";
		meanings = new ArrayList <String>();
		meanings.add("little creatures");
		this.theWords.put(key, meanings);
		
		key = "plate";
		meanings = new ArrayList <String>();
		meanings.add("dish");
		this.theWords.put(key, meanings);
		
		key = "plates";
		meanings = new ArrayList <String>();
		meanings.add("dishes");
		this.theWords.put(key, meanings);
		
		key = "plateau";
		meanings = new ArrayList <String>();
		meanings.add("level surface");
		this.theWords.put(key, meanings);
		
		key = "platter";
		meanings = new ArrayList <String>();
		meanings.add("serving dish");
		this.theWords.put(key, meanings);
		
		key = "player";
		meanings = new ArrayList <String>();
		meanings.add("sportsman");
		this.theWords.put(key, meanings);
		
		key = "players";
		meanings = new ArrayList <String>();
		meanings.add("sportsmen");
		this.theWords.put(key, meanings);
				
		key = "pleas";
		meanings = new ArrayList <String>();
		meanings.add("entreaties");
		this.theWords.put(key, meanings);
		
		key = "pleasant";
		meanings = new ArrayList <String>();
		meanings.add("nice");
		this.theWords.put(key, meanings);
		
		key = "please";
		meanings = new ArrayList <String>();
		meanings.add("delight");
		this.theWords.put(key, meanings);
		
		key = "pleasure";
		meanings = new ArrayList <String>();
		meanings.add("joy");
		this.theWords.put(key, meanings);
				
		key = "pliable";
		meanings = new ArrayList <String>();
		meanings.add("flexible");
		this.theWords.put(key, meanings);
		
		key = "plonk";
		meanings = new ArrayList <String>();
		meanings.add("cheap wine");
		meanings.add("put down noisily");
		this.theWords.put(key, meanings);
		
		key = "plot";
		meanings = new ArrayList <String>();
		meanings.add("conspiracy");
		this.theWords.put(key, meanings);
		
		key = "plough";
		meanings = new ArrayList <String>();
		meanings.add("farm implement");
		this.theWords.put(key, meanings);
		
		key = "ploy";
		meanings = new ArrayList <String>();
		meanings.add("scheme");
		this.theWords.put(key, meanings);
		
		key = "plug";
		meanings = new ArrayList <String>();
		meanings.add("stopper");
		this.theWords.put(key, meanings);
		
		key = "plumbago";
		meanings = new ArrayList <String>();
		meanings.add("graphite");
		this.theWords.put(key, meanings);
		
		key = "plunge";
		meanings = new ArrayList <String>();
		meanings.add("dive");
		this.theWords.put(key, meanings);
		
		key = "plus";
		meanings = new ArrayList <String>();
		meanings.add("also");
		this.theWords.put(key, meanings);
		
		key = "plush";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "pluto";
		meanings = new ArrayList <String>();
		meanings.add("Disney character");
		this.theWords.put(key, meanings);
		
		key = "poetry";
		meanings = new ArrayList <String>();
		meanings.add("verse");
		this.theWords.put(key, meanings);
		
		key = "poison";
		meanings = new ArrayList <String>();
		meanings.add("toxic substance");
		this.theWords.put(key, meanings);
		
		key = "poker";
		meanings = new ArrayList <String>();
		meanings.add("card game");
		this.theWords.put(key, meanings);
		
		key = "pole";
		meanings = new ArrayList <String>();
		meanings.add("stick");
		this.theWords.put(key, meanings);
		
		key = "poles";
		meanings = new ArrayList <String>();
		meanings.add("sticks");
		this.theWords.put(key, meanings);
		
		key = "polecat";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "policy";
		meanings = new ArrayList <String>();
		meanings.add("course");
		this.theWords.put(key, meanings);
		
		key = "polish";
		meanings = new ArrayList <String>();
		meanings.add("buff");
		meanings.add("central European");
		meanings.add("from warsaw");
		this.theWords.put(key, meanings);
		
		key = "polished";
		meanings = new ArrayList <String>();
		meanings.add("elegant");
		this.theWords.put(key, meanings);
		
		key = "poly";
		meanings = new ArrayList <String>();
		meanings.add("college");
		this.theWords.put(key, meanings);
		
		key = "pond";
		meanings = new ArrayList <String>();
		meanings.add("water feature");
		this.theWords.put(key, meanings);
		
		key = "ponder";
		meanings = new ArrayList <String>();
		meanings.add("contemplate");
		this.theWords.put(key, meanings);
		
		key = "poodle";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		this.theWords.put(key, meanings);
		
		key = "poodles";
		meanings = new ArrayList <String>();
		meanings.add("dogs");
		this.theWords.put(key, meanings);
		
		key = "poole";
		meanings = new ArrayList <String>();
		meanings.add("Dorset resort");
		this.theWords.put(key, meanings);
		
		key = "pools";
		meanings = new ArrayList <String>();
		meanings.add("puddles");
		this.theWords.put(key, meanings);
		
		key = "pop";
		meanings = new ArrayList <String>();
		meanings.add("father");
		this.theWords.put(key, meanings);
		
		key = "pope";
		meanings = new ArrayList <String>();
		meanings.add("Bishop of Rome");
		this.theWords.put(key, meanings);
		
		key = "poplin";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
				
		key = "popular";
		meanings = new ArrayList <String>();
		meanings.add("admired by many");
		meanings.add("liked");
		this.theWords.put(key, meanings);
				
		key = "porker";
		meanings = new ArrayList <String>();
		meanings.add("pig");
		this.theWords.put(key, meanings);
		
		key = "porridge";
		meanings = new ArrayList <String>();
		meanings.add("breakfast food");
		meanings.add("prison");
		this.theWords.put(key, meanings);
		
		key = "port";
		meanings = new ArrayList <String>();
		meanings.add("left at sea");
		meanings.add("wine");
		this.theWords.put(key, meanings);
		
		key = "portents";
		meanings = new ArrayList <String>();
		meanings.add("omens");
		this.theWords.put(key, meanings);
		
		key = "porter";
		meanings = new ArrayList <String>();
		meanings.add("hotel worker");
		this.theWords.put(key, meanings);
		
		key = "pose";
		meanings = new ArrayList <String>();
		meanings.add("what models do");
		this.theWords.put(key, meanings);
		
		key = "poser";
		meanings = new ArrayList <String>();
		meanings.add("riddle");
		meanings.add("show off");
		this.theWords.put(key, meanings);
		
		key = "posted";
		meanings = new ArrayList <String>();
		meanings.add("sent off");
		this.theWords.put(key, meanings);
		
		key = "pot";
		meanings = new ArrayList <String>();
		meanings.add("container");
		meanings.add("kitty");
		meanings.add("trophy");
		meanings.add("vessel");
		this.theWords.put(key, meanings);
		
		key = "pots";
		meanings = new ArrayList <String>();
		meanings.add("lots of money");
		this.theWords.put(key, meanings);
		
		key = "potent";
		meanings = new ArrayList <String>();
		meanings.add("powerful");
		this.theWords.put(key, meanings);
		
		key = "pound";
		meanings = new ArrayList <String>();
		meanings.add("weight");
		this.theWords.put(key, meanings);
		
		key = "powder";
		meanings = new ArrayList <String>();
		meanings.add("apply cosmetic");
		this.theWords.put(key, meanings);
		
		key = "poetic";
		meanings = new ArrayList <String>();
		meanings.add("expressed in verse");
		this.theWords.put(key, meanings);
		
		key = "pomp";
		meanings = new ArrayList <String>();
		meanings.add("ceremony");
		this.theWords.put(key, meanings);
		
		key = "port";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "portuguese";
		meanings = new ArrayList <String>();
		meanings.add("european");
		this.theWords.put(key, meanings);
		
		key = "poser";
		meanings = new ArrayList <String>();
		meanings.add("difficult problem");
		this.theWords.put(key, meanings);
		
		key = "pot";
		meanings = new ArrayList <String>();
		meanings.add("container");
		this.theWords.put(key, meanings);
		
		key = "potter";
		meanings = new ArrayList <String>();
		meanings.add("amble");
		meanings.add("ceramic maker");
		this.theWords.put(key, meanings);
		
		key = "potters";
		meanings = new ArrayList <String>();
		meanings.add("ambles");
		meanings.add("ceramic makers");
		this.theWords.put(key, meanings);
		
		key = "praise";
		meanings = new ArrayList <String>();
		meanings.add("approval");
		meanings.add("commend");
		this.theWords.put(key, meanings);
		
		key = "pram";
		meanings = new ArrayList <String>();
		meanings.add("pushchair");
		this.theWords.put(key, meanings);
		
		key = "pray";
		meanings = new ArrayList <String>();
		meanings.add("ask desperately");
		this.theWords.put(key, meanings);
		
		key = "preach";
		meanings = new ArrayList <String>();
		meanings.add("evangelise");
		this.theWords.put(key, meanings);
		
		key = "precede";
		meanings = new ArrayList <String>();
		meanings.add("go before");
		this.theWords.put(key, meanings);
		
		key = "precis";
		meanings = new ArrayList <String>();
		meanings.add("summary");
		this.theWords.put(key, meanings);
		
		key = "precise";
		meanings = new ArrayList <String>();
		meanings.add("exact");
		meanings.add("specific");
		this.theWords.put(key, meanings);
		
		key = "predict";
		meanings = new ArrayList <String>();
		meanings.add("forecast");
		this.theWords.put(key, meanings);
		
		key = "prefer";
		meanings = new ArrayList <String>();
		meanings.add("favour");
		this.theWords.put(key, meanings);
		
		key = "present";
		meanings = new ArrayList <String>();
		meanings.add("gift");
		this.theWords.put(key, meanings);
		
		key = "presents";
		meanings = new ArrayList <String>();
		meanings.add("gifts");
		this.theWords.put(key, meanings);
		
		key = "preserve";
		meanings = new ArrayList <String>();
		meanings.add("keep");
		this.theWords.put(key, meanings);
		
		key = "press";
		meanings = new ArrayList <String>();
		meanings.add("journalists");
		meanings.add("reporters");
		meanings.add("urge");
		meanings.add("use an iron");
		this.theWords.put(key, meanings);
		
		key = "president";
		meanings = new ArrayList <String>();
		meanings.add("such as bush");
		this.theWords.put(key, meanings);
		
		key = "presidents";
		meanings = new ArrayList <String>();
		meanings.add("heads");
		this.theWords.put(key, meanings);
		
		key = "prevail";
		meanings = new ArrayList <String>();
		meanings.add("come out on top");
		this.theWords.put(key, meanings);
		
		key = "pride";
		meanings = new ArrayList <String>();
		meanings.add("arrogance");
		this.theWords.put(key, meanings);
		
		key = "priests";
		meanings = new ArrayList <String>();
		meanings.add("clergymen");
		meanings.add("men with collars");
		this.theWords.put(key, meanings);
		
		key = "printed";
		meanings = new ArrayList <String>();
		meanings.add("on paper");
		this.theWords.put(key, meanings);
		
		key = "printer";
		meanings = new ArrayList <String>();
		meanings.add("computer accessory");
		this.theWords.put(key, meanings);
		
		key = "prised";
		meanings = new ArrayList <String>();
		meanings.add("forced");
		this.theWords.put(key, meanings);
		
		key = "prison";
		meanings = new ArrayList <String>();
		meanings.add("jail");
		this.theWords.put(key, meanings);
		
		key = "prize";
		meanings = new ArrayList <String>();
		meanings.add("award");
		this.theWords.put(key, meanings);
		
		key = "pro";
		meanings = new ArrayList <String>();
		meanings.add("for");
		meanings.add("in favour of");
		meanings.add("professional");
		this.theWords.put(key, meanings);
		
		key = "probe";
		meanings = new ArrayList <String>();
		meanings.add("close examination");
		this.theWords.put(key, meanings);
		
		key = "proceeded";
		meanings = new ArrayList <String>();
		meanings.add("went ahead with");
		this.theWords.put(key, meanings);
		
		key = "profit";
		meanings = new ArrayList <String>();
		meanings.add("gain");
		this.theWords.put(key, meanings);
				
		key = "profound";
		meanings = new ArrayList <String>();
		meanings.add("deep");
		this.theWords.put(key, meanings);
		
		key = "project";
		meanings = new ArrayList <String>();
		meanings.add("collaborative enterprise");
		meanings.add("estimate");
		meanings.add("forecast");
		meanings.add("protrude");
		this.theWords.put(key, meanings);
		
		key = "pronoun";
		meanings = new ArrayList <String>();
		meanings.add("part of grammar");
		this.theWords.put(key, meanings);
		
		key = "prop";
		meanings = new ArrayList <String>();
		meanings.add("support");
		this.theWords.put(key, meanings);
				
		key = "proper";
		meanings = new ArrayList <String>();
		meanings.add("right");
		this.theWords.put(key, meanings);
		
		key = "pros";
		meanings = new ArrayList <String>();
		meanings.add("experts");
		this.theWords.put(key, meanings);
		
		key = "prosaic";
		meanings = new ArrayList <String>();
		meanings.add("dull");
		this.theWords.put(key, meanings);
		
		key = "prospector";
		meanings = new ArrayList <String>();
		meanings.add("dull");
		this.theWords.put(key, meanings);
		
		key = "prosper";
		meanings = new ArrayList <String>();
		meanings.add("do well");
		this.theWords.put(key, meanings);
				
		key = "prospero";
		meanings = new ArrayList <String>();
		meanings.add("shakespear character");
		this.theWords.put(key, meanings);
		
		key = "protect";
		meanings = new ArrayList <String>();
		meanings.add("cushion");
		meanings.add("defend");
		meanings.add("guard");
		meanings.add("safeguard");
		meanings.add("shelter");
		this.theWords.put(key, meanings);
		
		key = "protest";
		meanings = new ArrayList <String>();
		meanings.add("demonstrate");
		meanings.add("demonstration");
		this.theWords.put(key, meanings);
		
		key = "protractor";
		meanings = new ArrayList <String>();
		meanings.add("used by geometry students");
		this.theWords.put(key, meanings);
		
		key = "protruded";
		meanings = new ArrayList <String>();
		meanings.add("stuck out");
		this.theWords.put(key, meanings);
		
		key = "proves";
		meanings = new ArrayList <String>();
		meanings.add("demonstrates");
		this.theWords.put(key, meanings);
		
		key = "publisher";
		meanings = new ArrayList <String>();
		meanings.add("supplier of literature");
		this.theWords.put(key, meanings);
		
		key = "pud";
		meanings = new ArrayList <String>();
		meanings.add("sweet");
		this.theWords.put(key, meanings);
		
		key = "pug";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		this.theWords.put(key, meanings);
		
		key = "pulse";
		meanings = new ArrayList <String>();
		meanings.add("beat");
		this.theWords.put(key, meanings);
		
		key = "pumice";
		meanings = new ArrayList <String>();
		meanings.add("volcanic rock");
		this.theWords.put(key, meanings);
		
		key = "pump";
		meanings = new ArrayList <String>();
		meanings.add("ply");
		this.theWords.put(key, meanings);
		
		key = "pumpkin";
		meanings = new ArrayList <String>();
		meanings.add("feature of Halloween");
		this.theWords.put(key, meanings);
		
		key = "pun";
		meanings = new ArrayList <String>();
		meanings.add("joke");
		this.theWords.put(key, meanings);
		
		key = "pungent";
		meanings = new ArrayList <String>();
		meanings.add("pervasive");
		meanings.add("powerful");
		this.theWords.put(key, meanings);
		
		key = "puritans";
		meanings = new ArrayList <String>();
		meanings.add("church reformers");
		this.theWords.put(key, meanings);
		
		key = "push";
		meanings = new ArrayList <String>();
		meanings.add("advance");
		this.theWords.put(key, meanings);
		
		key = "put";
		meanings = new ArrayList <String>();
		meanings.add("placed");
		this.theWords.put(key, meanings);
		
		key = "putdown"; //put down
		meanings = new ArrayList <String>();
		meanings.add("recorded");
		meanings.add("snub");
		this.theWords.put(key, meanings);
		
		key = "putter";
		meanings = new ArrayList <String>();
		meanings.add("golf club");
		this.theWords.put(key, meanings);

	}//end loadPWords
	
	private void loadQWords() {
		String key;
		ArrayList <String> meanings;
		
		//load q's
		key = "qualifying";
		meanings = new ArrayList <String>();
		meanings.add("getting through");
		this.theWords.put(key, meanings);
		
		key = "quality";
		meanings = new ArrayList <String>();
		meanings.add("attribute");
		this.theWords.put(key, meanings);
		
		key = "quarters";
		meanings = new ArrayList <String>();
		meanings.add("accommodation");
		meanings.add("certain parts");
		this.theWords.put(key, meanings);
		
		key = "quartets";
		meanings = new ArrayList <String>();
		meanings.add("chamber music");
		this.theWords.put(key, meanings);
		
		key = "quip";
		meanings = new ArrayList <String>();
		meanings.add("witticism");
		this.theWords.put(key, meanings);
		
		key = "quire";
		meanings = new ArrayList <String>();
		meanings.add("25 sheets of paper");
		this.theWords.put(key, meanings);
		
		key = "quito";
		meanings = new ArrayList <String>();
		meanings.add("South American city");
		this.theWords.put(key, meanings);
		
		key = "quiver";
		meanings = new ArrayList <String>();
		meanings.add("container for arrows");
		meanings.add("shake");
		this.theWords.put(key, meanings);

	}//end loadQWords
	
	private void loadRWords() {
		String key;
		ArrayList <String> meanings;
		
		//load r's
		key = "rabbi";
		meanings = new ArrayList <String>();
		meanings.add("religious leader");
		this.theWords.put(key, meanings);
		
		key = "rabbit";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("chatter");
		this.theWords.put(key, meanings);
		
		key = "rabid";
		meanings = new ArrayList <String>();
		meanings.add("like a mad dog");
		this.theWords.put(key, meanings);
		
		key = "race";
		meanings = new ArrayList <String>();
		meanings.add("contest");
		meanings.add("people");
		meanings.add("sporting event");
		this.theWords.put(key, meanings);
		
		key = "raced";
		meanings = new ArrayList <String>();
		meanings.add("competed");
		this.theWords.put(key, meanings);
		
		key = "races";
		meanings = new ArrayList <String>();
		meanings.add("sporting events");
		this.theWords.put(key, meanings);
		
		key = "racism";
		meanings = new ArrayList <String>();
		meanings.add("bigotry");
		this.theWords.put(key, meanings);
		
		key = "racist";
		meanings = new ArrayList <String>();
		meanings.add("bigot");
		this.theWords.put(key, meanings);
		
		key = "racy";
		meanings = new ArrayList <String>();
		meanings.add("lively");
		this.theWords.put(key, meanings);
		
		key = "radiant";
		meanings = new ArrayList <String>();
		meanings.add("beaming");
		this.theWords.put(key, meanings);
		
		key = "radio";
		meanings = new ArrayList <String>();
		meanings.add("means of receiving");
		meanings.add("send a message");
		this.theWords.put(key, meanings);
		
		key = "rag";
		meanings = new ArrayList <String>();
		meanings.add("paper");
		meanings.add("scrap of cloth");
		this.theWords.put(key, meanings);
		
		key = "rags";
		meanings = new ArrayList <String>();
		meanings.add("papers");
		meanings.add("scraps of cloth");
		this.theWords.put(key, meanings);
		
		key = "rage";
		meanings = new ArrayList <String>();
		meanings.add("anger");
		this.theWords.put(key, meanings);
		
		key = "rages";
		meanings = new ArrayList <String>();
		meanings.add("storms");
		this.theWords.put(key, meanings);
		
		key = "raid";
		meanings = new ArrayList <String>();
		meanings.add("attack");
		meanings.add("foray");
		meanings.add("plunder");
		meanings.add("enter by force");
		meanings.add("short sudden attack");
		this.theWords.put(key, meanings);
		
		key = "raiding";
		meanings = new ArrayList <String>();
		meanings.add("attacking");
		this.theWords.put(key, meanings);
		
		key = "rain";
		meanings = new ArrayList <String>();
		meanings.add("bad weather");
		meanings.add("downpour");
		meanings.add("shower");
		meanings.add("stops play at the oval");
		meanings.add("wet weather");
		this.theWords.put(key, meanings);
		
		key = "raise";
		meanings = new ArrayList <String>();
		meanings.add("lift");
		this.theWords.put(key, meanings);
		
		key = "raisin";
		meanings = new ArrayList <String>();
		meanings.add("dried black grape");
		this.theWords.put(key, meanings);
		
		key = "rake";
		meanings = new ArrayList <String>();
		meanings.add("garden tool");
		this.theWords.put(key, meanings);
		
		key = "rally";
		meanings = new ArrayList <String>();
		meanings.add("convention");
		meanings.add("regroup");
		this.theWords.put(key, meanings);
		
		key = "ramble";
		meanings = new ArrayList <String>();
		meanings.add("leisurely walk");
		this.theWords.put(key, meanings);
				
		key = "rambler";
		meanings = new ArrayList <String>();
		meanings.add("hiker");
		this.theWords.put(key, meanings);
		
		key = "ramekin";
		meanings = new ArrayList <String>();
		meanings.add("dish for one");
		this.theWords.put(key, meanings);
		
		key = "ram";
		meanings = new ArrayList <String>();
		meanings.add("sheep");
		this.theWords.put(key, meanings);
		
		key = "ramp";
		meanings = new ArrayList <String>();
		meanings.add("slope");
		this.theWords.put(key, meanings);
		
		key = "rampage";
		meanings = new ArrayList <String>();
		meanings.add("rush around");
		this.theWords.put(key, meanings);
		
		
		key = "ran";
		meanings = new ArrayList <String>();
		meanings.add("competed");
		meanings.add("governed");
		meanings.add("managed");
		meanings.add("operated");
		meanings.add("organised");
		this.theWords.put(key, meanings);
		
		key = "ranch";
		meanings = new ArrayList <String>();
		meanings.add("farm");
		meanings.add("Wetsern farm");
		this.theWords.put(key, meanings);
		
		key = "rand";
		meanings = new ArrayList <String>();
		meanings.add("foreign money");
		this.theWords.put(key, meanings);
		
		key = "random";
		meanings = new ArrayList <String>();
		meanings.add("haphazard");
		meanings.add("unsystematic");
		this.theWords.put(key, meanings);
		
		key = "randy";
		meanings = new ArrayList <String>();
		meanings.add("lascivious");
		this.theWords.put(key, meanings);
		
		key = "rang";
		meanings = new ArrayList <String>();
		meanings.add("phoned");
		this.theWords.put(key, meanings);
		
		key = "range";
		meanings = new ArrayList <String>();
		meanings.add("mountains");
		meanings.add("stove");
		meanings.add("scope");
		this.theWords.put(key, meanings);
		
		key = "ranges";
		meanings = new ArrayList <String>();
		meanings.add("mountains");
		this.theWords.put(key, meanings);
		
		key = "ranger";
		meanings = new ArrayList <String>();
		meanings.add("forest keeper");
		meanings.add("guide");
		this.theWords.put(key, meanings);
		
		key = "rangers";
		meanings = new ArrayList <String>();
		meanings.add("wardens");
		this.theWords.put(key, meanings);
		
		key = "rangoon";
		meanings = new ArrayList <String>();
		meanings.add("Burmese city");
		this.theWords.put(key, meanings);
		
		key = "rank";
		meanings = new ArrayList <String>();
		meanings.add("row");
		this.theWords.put(key, meanings);

		key = "ranks";
		meanings = new ArrayList <String>();
		meanings.add("rows");
		this.theWords.put(key, meanings);
		
		key = "ransack";
		meanings = new ArrayList <String>();
		meanings.add("lay waste");
		meanings.add("plunder");
		this.theWords.put(key, meanings);
		
		key = "ransacks";
		meanings = new ArrayList <String>();
		meanings.add("plunders");
		this.theWords.put(key, meanings);
		
		key = "ransom";
		meanings = new ArrayList <String>();
		meanings.add("hostage payment");
		this.theWords.put(key, meanings);
		
		key = "rant";
		meanings = new ArrayList <String>();
		meanings.add("sound off");
		meanings.add("tirade");
		this.theWords.put(key, meanings);
		
		key = "rants";
		meanings = new ArrayList <String>();
		meanings.add("sounds off");
		meanings.add("tirades");
		this.theWords.put(key, meanings);
			
		key = "rapid";
		meanings = new ArrayList <String>();
		meanings.add("quick");
		this.theWords.put(key, meanings);
		
		key = "rapier";
		meanings = new ArrayList <String>();
		meanings.add("sword");
		this.theWords.put(key, meanings);
		
		key = "rapping";
		meanings = new ArrayList <String>();
		meanings.add("striking hard");
		this.theWords.put(key, meanings);
		
		key = "raps";
		meanings = new ArrayList <String>();
		meanings.add("strikes hard");
		this.theWords.put(key, meanings);
		
		key = "rare";
		meanings = new ArrayList <String>();
		meanings.add("unusual");
		this.theWords.put(key, meanings);
		
		key = "rarebit";
		meanings = new ArrayList <String>();
		meanings.add("welsh dish");
		this.theWords.put(key, meanings);
		
		key = "rasp";
		meanings = new ArrayList <String>();
		meanings.add("tool");
		this.theWords.put(key, meanings);
		
		key = "rasputin";
		meanings = new ArrayList <String>();
		meanings.add("sinister monk");
		this.theWords.put(key, meanings);
		
		key = "rat";
		meanings = new ArrayList <String>();
		meanings.add("deceitful person");
		meanings.add("rodent");
		meanings.add("vermin");
		this.theWords.put(key, meanings);
		
		key = "rats";
		meanings = new ArrayList <String>();
		meanings.add("deceitful people");
		meanings.add("rodents");
		meanings.add("vermin");
		this.theWords.put(key, meanings);
		
		key = "rate";
		meanings = new ArrayList <String>();
		meanings.add("assign a value to");
		meanings.add("frequency");
		meanings.add("measure");
		meanings.add("quantity");
		meanings.add("score");
		meanings.add("speed");
		meanings.add("value");
		this.theWords.put(key, meanings);	
		
		key = "rates";
		meanings = new ArrayList <String>();
		meanings.add("measures");
		meanings.add("rates");
		meanings.add("scores");
		this.theWords.put(key, meanings);
		
		key = "rated";
		meanings = new ArrayList <String>();
		meanings.add("measured");
		meanings.add("ranked");
		meanings.add("scored");
		meanings.add("assigned a value to");
		this.theWords.put(key, meanings);
		
		key = "rating";
		meanings = new ArrayList <String>();
		meanings.add("rank");
		this.theWords.put(key, meanings);
		
		key = "ratio";
		meanings = new ArrayList <String>();
		meanings.add("aportion");
		this.theWords.put(key, meanings);
		
		key = "ration";
		meanings = new ArrayList <String>();
		meanings.add("helping");
		this.theWords.put(key, meanings);
		
		key = "rationally";
		meanings = new ArrayList <String>();
		meanings.add("logically");
		this.theWords.put(key, meanings);
		
		key = "rats";
		meanings = new ArrayList <String>();
		meanings.add("vermin");
		this.theWords.put(key, meanings);
		
		key = "rave";
		meanings = new ArrayList <String>();
		meanings.add("dance party");
		this.theWords.put(key, meanings);
		
		key = "ravel";
		meanings = new ArrayList <String>();
		meanings.add("composer");
		meanings.add("French composer");
		meanings.add("musician");
		this.theWords.put(key, meanings);
		
		key = "ravine";
		meanings = new ArrayList <String>();
		meanings.add("narrow gorge");
		this.theWords.put(key, meanings);
		
		key = "raw";
		meanings = new ArrayList <String>();
		meanings.add("uncooked");
		this.theWords.put(key, meanings);
		
		key = "ray";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "rayon";
		meanings = new ArrayList <String>();
		meanings.add("artificial silk");
		meanings.add("fabric");
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "re";
		meanings = new ArrayList <String>();
		meanings.add("about");
		meanings.add("concerning");
		//this.theWords.put(key, meanings);
		
		key = "reach";
		meanings = new ArrayList <String>();
		meanings.add("get to");
		meanings.add("open stretch of water");
		this.theWords.put(key, meanings);
		
		key = "read";
		meanings = new ArrayList <String>();
		meanings.add("look at and comprehend");
		meanings.add("discover");
		meanings.add("inspect");
		meanings.add("study");
		meanings.add("hear and understand");
		this.theWords.put(key, meanings);
		
		key = "reader";
		meanings = new ArrayList <String>();
		meanings.add("university lecturer perhaps");
		meanings.add("the meter man?");
		this.theWords.put(key, meanings);
		
		key = "readiest";
		meanings = new ArrayList <String>();
		meanings.add("most willing");
		this.theWords.put(key, meanings);
		
		key = "ready";
		meanings = new ArrayList <String>();
		meanings.add("prepared");
		this.theWords.put(key, meanings);
		
		key = "real";
		meanings = new ArrayList <String>();
		meanings.add("existing");
		meanings.add("true");
		this.theWords.put(key, meanings);
		
		key = "reality";
		meanings = new ArrayList <String>();
		meanings.add("real life");
		this.theWords.put(key, meanings);
		
		key = "really";
		meanings = new ArrayList <String>();
		meanings.add("actually");
		this.theWords.put(key, meanings);
		
		key = "realm";
		meanings = new ArrayList <String>();
		meanings.add("kingdom");
		meanings.add("province");
		this.theWords.put(key, meanings);
		
		key = "realms";
		meanings = new ArrayList <String>();
		meanings.add("kingdoms");
		meanings.add("provinces");
		this.theWords.put(key, meanings);
		
		key = "reap";
		meanings = new ArrayList <String>();
		meanings.add("harvest");
		this.theWords.put(key, meanings);
		
		key = "reaped";
		meanings = new ArrayList <String>();
		meanings.add("gathered");
		this.theWords.put(key, meanings);
		
		key = "reaps";
		meanings = new ArrayList <String>();
		meanings.add("scythes");
		meanings.add("gathers");
		meanings.add("obtains");
		meanings.add("receives");
		this.theWords.put(key, meanings);
				
		key = "rear";
		meanings = new ArrayList <String>();
		meanings.add("behind");
		this.theWords.put(key, meanings);
		
		key = "reared";
		meanings = new ArrayList <String>();
		meanings.add("brought up");
		meanings.add("bred");
		meanings.add("raised");
		meanings.add("stood on back legs");
		this.theWords.put(key, meanings);
		
		key = "rearm";
		meanings = new ArrayList <String>();
		meanings.add("take up weapons again");
		this.theWords.put(key, meanings);
		
		key = "reason";
		meanings = new ArrayList <String>();
		meanings.add("motive");
		this.theWords.put(key, meanings);
		
		key = "reassert";
		meanings = new ArrayList <String>();
		meanings.add("claim again");
		this.theWords.put(key, meanings);
		
		key = "rebel";
		meanings = new ArrayList <String>();
		meanings.add("rise up");
		this.theWords.put(key, meanings);
		
		key = "rebus";
		meanings = new ArrayList <String>();
		meanings.add("puzzle");
		this.theWords.put(key, meanings);
		
		key = "recede";
		meanings = new ArrayList <String>();
		meanings.add("move back");
		this.theWords.put(key, meanings);
		
		key = "recipe";
		meanings = new ArrayList <String>();
		meanings.add("formula");
		this.theWords.put(key, meanings);
		
		key = "recipes";
		meanings = new ArrayList <String>();
		meanings.add("formulas");
		this.theWords.put(key, meanings);
		
		key = "recital";
		meanings = new ArrayList <String>();
		meanings.add("reading");
		this.theWords.put(key, meanings);
		
		key = "recto";
		meanings = new ArrayList <String>();
		meanings.add("right hand page");
		this.theWords.put(key, meanings);
		
		key = "rector";
		meanings = new ArrayList <String>();
		meanings.add("clergyman");
		this.theWords.put(key, meanings);
		
		key = "red";
		meanings = new ArrayList <String>();
		meanings.add("colour");
		meanings.add("crimson");
		meanings.add("embarrassed");
		this.theWords.put(key, meanings);
		
		key = "redadmirals"; // red admirals
		meanings = new ArrayList <String>();
		meanings.add("butterflies");
		this.theWords.put(key, meanings);
		
		key = "redcoat";
		meanings = new ArrayList <String>();
		meanings.add("old soldier");
		this.theWords.put(key, meanings);
		
		key = "redcoats";
		meanings = new ArrayList <String>();
		meanings.add("soldiers");
		this.theWords.put(key, meanings);
		
		key = "redhead";
		meanings = new ArrayList <String>();
		meanings.add("ginger");
		this.theWords.put(key, meanings);
		
		key = "redrag";
		meanings = new ArrayList <String>();
		meanings.add("something infuriating");
		this.theWords.put(key, meanings);
		
		key = "redress";
		meanings = new ArrayList <String>();
		meanings.add("put right");
		this.theWords.put(key, meanings);
		
		key = "reds";
		meanings = new ArrayList <String>();
		meanings.add("Liverpool");
		this.theWords.put(key, meanings);
		
		key = "recent";
		meanings = new ArrayList <String>();
		meanings.add("contemporary");
		this.theWords.put(key, meanings);
		
		key = "recession";
		meanings = new ArrayList <String>();
		meanings.add("slump");
		this.theWords.put(key, meanings);

		key = "recover";
		meanings = new ArrayList <String>();
		meanings.add("get better");
		meanings.add("work as an upholsterer");
		this.theWords.put(key, meanings);
		
		key = "recur";
		meanings = new ArrayList <String>();
		meanings.add("come back");
		this.theWords.put(key, meanings);
		
		key = "red";
		meanings = new ArrayList <String>();
		meanings.add("blushing");
		this.theWords.put(key, meanings);
		
		key = "reed";
		meanings = new ArrayList <String>();
		meanings.add("grass");
		this.theWords.put(key, meanings);
		
		key = "reef";
		meanings = new ArrayList <String>();
		meanings.add("chain of rocks");
		this.theWords.put(key, meanings);
		
		key = "reek";
		meanings = new ArrayList <String>();
		meanings.add("smell");
		this.theWords.put(key, meanings);
		
		key = "reel";
		meanings = new ArrayList <String>();
		meanings.add("dance");
		this.theWords.put(key, meanings);
		
		key = "ref";
		meanings = new ArrayList <String>();
		meanings.add("umpire");
		meanings.add("whistle blower");
		this.theWords.put(key, meanings);
		
		key = "refer";
		meanings = new ArrayList <String>();
		meanings.add("allude");
		meanings.add("mention");
		meanings.add("send");
		this.theWords.put(key, meanings);
		
		key = "referee";
		meanings = new ArrayList <String>();
		meanings.add("adjudicator");
		meanings.add("judge");
		meanings.add("mediatior");
		meanings.add("umpire");
		this.theWords.put(key, meanings);
		
		key = "refits";
		meanings = new ArrayList <String>();
		meanings.add("restorations");
		this.theWords.put(key, meanings);
		
		key = "refuse";
		meanings = new ArrayList <String>();
		meanings.add("litter");
		meanings.add("say no");
		this.theWords.put(key, meanings);
		
		key = "refused";
		meanings = new ArrayList <String>();
		meanings.add("said no to");
		this.theWords.put(key, meanings);
		
		key = "reg";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "regal";
		meanings = new ArrayList <String>();
		meanings.add("splendid");
		this.theWords.put(key, meanings);
		
		key = "regiment";
		meanings = new ArrayList <String>();
		meanings.add("group of soldiers");
		this.theWords.put(key, meanings);
		
		key = "region";
		meanings = new ArrayList <String>();
		meanings.add("area");
		this.theWords.put(key, meanings);
		
		key = "register";
		meanings = new ArrayList <String>();
		meanings.add("official list");
		meanings.add("record");
		this.theWords.put(key, meanings);
		
		key = "reheat";
		meanings = new ArrayList <String>();
		meanings.add("warm up again");
		this.theWords.put(key, meanings);
		
		key = "reign";
		meanings = new ArrayList <String>();
		meanings.add("rule by monarch");
		this.theWords.put(key, meanings);
		
		key = "reigns";
		meanings = new ArrayList <String>();
		meanings.add("rules");
		this.theWords.put(key, meanings);
		
		key = "related";
		meanings = new ArrayList <String>();
		meanings.add("connected");
		this.theWords.put(key, meanings);
		
		key = "relatives";
		meanings = new ArrayList <String>();
		meanings.add("members of the family");
		this.theWords.put(key, meanings);
		
		key = "relay";
		meanings = new ArrayList <String>();
		meanings.add("broadcast");
		this.theWords.put(key, meanings);
		
		key = "relent";
		meanings = new ArrayList <String>();
		meanings.add("relax");
		this.theWords.put(key, meanings);
		
		key = "relentless";
		meanings = new ArrayList <String>();
		meanings.add("inexorable");
		this.theWords.put(key, meanings);		
		
		key = "relish";
		meanings = new ArrayList <String>();
		meanings.add("dressing");
		meanings.add("enjoy");
		this.theWords.put(key, meanings);
		
		key = "relishes";
		meanings = new ArrayList <String>();
		meanings.add("chutneys");
		meanings.add("likes the taste of");
		this.theWords.put(key, meanings);
		
		key = "relishing";
		meanings = new ArrayList <String>();
		meanings.add("really enjoying");
		this.theWords.put(key, meanings);
		
		key = "rely";
		meanings = new ArrayList <String>();
		meanings.add("depend on");
		this.theWords.put(key, meanings);
		
		key = "regimen";
		meanings = new ArrayList <String>();
		meanings.add("diet");
		this.theWords.put(key, meanings);
		
		key = "regiment";
		meanings = new ArrayList <String>();
		meanings.add("military unit");
		this.theWords.put(key, meanings);
		
		key = "regret";
		meanings = new ArrayList <String>();
		meanings.add("feel sorry about");
		this.theWords.put(key, meanings);
		
		key = "regrets";
		meanings = new ArrayList <String>();
		meanings.add("feels sorry about");
		meanings.add("rues");
		this.theWords.put(key, meanings);
		
		key = "remain";
		meanings = new ArrayList <String>();
		meanings.add("stay");
		this.theWords.put(key, meanings);
		
		key = "remains";
		meanings = new ArrayList <String>();
		meanings.add("corpse");
		this.theWords.put(key, meanings);
		
		key = "remand";
		meanings = new ArrayList <String>();
		meanings.add("send back");
		this.theWords.put(key, meanings);
		
		key = "remark";
		meanings = new ArrayList <String>();
		meanings.add("observation");
		meanings.add("something said");
		this.theWords.put(key, meanings);
		
		key = "remarks";
		meanings = new ArrayList <String>();
		meanings.add("comments");
		this.theWords.put(key, meanings);
		
		key = "remember";
		meanings = new ArrayList <String>();
		meanings.add("don't forget");
		this.theWords.put(key, meanings);
				
		key = "remiss";
		meanings = new ArrayList <String>();
		meanings.add("negligent");
		this.theWords.put(key, meanings);
		
		key = "remit";
		meanings = new ArrayList <String>();
		meanings.add("send");
		this.theWords.put(key, meanings);
		
		key = "remnant";
		meanings = new ArrayList <String>();
		meanings.add("scrap");
		this.theWords.put(key, meanings);
		
		key = "remote";
		meanings = new ArrayList <String>();
		meanings.add("faraway");
		meanings.add("far off");
		this.theWords.put(key, meanings);
		
		key = "removed";
		meanings = new ArrayList <String>();
		meanings.add("took off");
		this.theWords.put(key, meanings);
		
		key = "rend";
		meanings = new ArrayList <String>();
		meanings.add("split");
		this.theWords.put(key, meanings);
		
		key = "rene";
		meanings = new ArrayList <String>();
		meanings.add("frenchman");
		this.theWords.put(key, meanings);
		
		key = "renewals";
		meanings = new ArrayList <String>();
		meanings.add("continuation");
		this.theWords.put(key, meanings);
		
		key = "rent";
		meanings = new ArrayList <String>();
		meanings.add("split");
		this.theWords.put(key, meanings);
		
		key = "rental";
		meanings = new ArrayList <String>();
		meanings.add("income from property");
		this.theWords.put(key, meanings);
		
		key = "rep";
		meanings = new ArrayList <String>();
		meanings.add("salesman");
		this.theWords.put(key, meanings);
		
		key = "repaint";
		meanings = new ArrayList <String>();
		meanings.add("decorate again");
		meanings.add("render again");
		this.theWords.put(key, meanings);
				
		key = "repair";
		meanings = new ArrayList <String>();
		meanings.add("fix");
		this.theWords.put(key, meanings);
		
		key = "repartee";
		meanings = new ArrayList <String>();
		meanings.add("banter");
		this.theWords.put(key, meanings);
		
		key = "repeat";
		meanings = new ArrayList <String>();
		meanings.add("do again");
		this.theWords.put(key, meanings);
		
		key = "repent";
		meanings = new ArrayList <String>();
		meanings.add("be sorry for");
		meanings.add("rue");
		this.theWords.put(key, meanings);
		
		//this word was only added for test purposes
		//testing augmented anagram + 3 letters
		key = "repertain";
		meanings = new ArrayList <String>();
		meanings.add("pertain again");
		this.theWords.put(key, meanings);
		
		key = "replenish";
		meanings = new ArrayList <String>();
		meanings.add("top up");
		this.theWords.put(key, meanings);
		
		key = "replicate";
		meanings = new ArrayList <String>();
		meanings.add("make copy of");
		this.theWords.put(key, meanings);
		
		key = "reporters";
		meanings = new ArrayList <String>();
		meanings.add("journalists");
		this.theWords.put(key, meanings);
		
		key = "reproach";
		meanings = new ArrayList <String>();
		meanings.add("rebuke");
		this.theWords.put(key, meanings);
		
		key = "reptile";
		meanings = new ArrayList <String>();
		meanings.add("cold-blooded creature");
		this.theWords.put(key, meanings);
		
		key = "required";
		meanings = new ArrayList <String>();
		meanings.add("needed");
		this.theWords.put(key, meanings);
		
		key = "rescue";
		meanings = new ArrayList <String>();
		meanings.add("free");
		this.theWords.put(key, meanings);
		
		key = "resents";
		meanings = new ArrayList <String>();
		meanings.add("dislikes");
		this.theWords.put(key, meanings);
		
		key = "reserve";
		meanings = new ArrayList <String>();
		meanings.add("book");
		this.theWords.put(key, meanings);
		
		key = "reserved";
		meanings = new ArrayList <String>();
		meanings.add("kept");
		meanings.add("being shy");
		this.theWords.put(key, meanings);
		
		key = "reside";
		meanings = new ArrayList <String>();
		meanings.add("live");
		this.theWords.put(key, meanings);
		
		key = "resident";
		meanings = new ArrayList <String>();
		meanings.add("living in a place");
		this.theWords.put(key, meanings);
		
		key = "residential";
		meanings = new ArrayList <String>();
		meanings.add("people to live in");
		this.theWords.put(key, meanings);
		
		key = "resigned";
		meanings = new ArrayList <String>();
		meanings.add("gave up");
		this.theWords.put(key, meanings);
		
		key = "resist";
		meanings = new ArrayList <String>();
		meanings.add("counter");
		meanings.add("put up a fight");
		this.theWords.put(key, meanings);
		
		key = "resit";
		meanings = new ArrayList <String>();
		meanings.add("further examination");
		this.theWords.put(key, meanings);
		
		key = "resolved";
		meanings = new ArrayList <String>();
		meanings.add("cracked again");
		meanings.add("sorted out");
		this.theWords.put(key, meanings);
		
		key = "respite";
		meanings = new ArrayList <String>();
		meanings.add("welcome break");
		this.theWords.put(key, meanings);
		
		key = "rest";
		meanings = new ArrayList <String>();
		meanings.add("others");
		meanings.add("support");
		meanings.add("take it easy");
		this.theWords.put(key, meanings);
		
		key = "restful";
		meanings = new ArrayList <String>();
		meanings.add("still");
		this.theWords.put(key, meanings);
		
		key = "restored";
		meanings = new ArrayList <String>();
		meanings.add("made good");
		this.theWords.put(key, meanings);
				
		key = "restrain";
		meanings = new ArrayList <String>();
		meanings.add("hold back");
		this.theWords.put(key, meanings);
		
		key = "restricted";
		meanings = new ArrayList <String>();
		meanings.add("limited");
		this.theWords.put(key, meanings);
		
		key = "result";
		meanings = new ArrayList <String>();
		meanings.add("outcome");
		this.theWords.put(key, meanings);
		
		key = "retain";
		meanings = new ArrayList <String>();
		meanings.add("absorb and hold");
		meanings.add("continue to have");
		meanings.add("keep");
		meanings.add("keep in employment");
		this.theWords.put(key, meanings);
		
		key = "retainer";
		meanings = new ArrayList <String>();
		meanings.add("deposit");
		this.theWords.put(key, meanings);
		
		key = "retains";
		meanings = new ArrayList <String>();
		meanings.add("keeps");
		this.theWords.put(key, meanings);
		
		key = "retch";
		meanings = new ArrayList <String>();
		meanings.add("be sick");
		this.theWords.put(key, meanings);
		
		key = "retina";
		meanings = new ArrayList <String>();
		meanings.add("part of the eye");
		meanings.add("where an image is formed");
		this.theWords.put(key, meanings);
		
		key = "retire";
		meanings = new ArrayList <String>();
		meanings.add("finish work");
		meanings.add("go to bed");
		meanings.add("retreat");
		this.theWords.put(key, meanings);
		
		key = "retired";
		meanings = new ArrayList <String>();
		meanings.add("finished work");
		meanings.add("went to bed");
		this.theWords.put(key, meanings);
		
		key = "retirement";
		meanings = new ArrayList <String>();
		meanings.add("when work is over");
		this.theWords.put(key, meanings);
		
		key = "retiring";
		meanings = new ArrayList <String>();
		meanings.add("finishing work");
		meanings.add("going to bed");
		this.theWords.put(key, meanings);
				
		key = "reunion";
		meanings = new ArrayList <String>();
		meanings.add("nostalgic meeting");
		this.theWords.put(key, meanings);
		
		key = "rev";
		meanings = new ArrayList <String>();
		meanings.add("priest");
		this.theWords.put(key, meanings);
		
		key = "reveal";
		meanings = new ArrayList <String>();
		meanings.add("make known");
		this.theWords.put(key, meanings);
		
		key = "revealed";
		meanings = new ArrayList <String>();
		meanings.add("no longer hidden");
		this.theWords.put(key, meanings);
		
		key = "revel";
		meanings = new ArrayList <String>();
		meanings.add("make merry");
		this.theWords.put(key, meanings);
		
		key = "reversed";
		meanings = new ArrayList <String>();
		meanings.add("vice-versa");
		this.theWords.put(key, meanings);
		
		key = "rewarded";
		meanings = new ArrayList <String>();
		meanings.add("recognised");
		this.theWords.put(key, meanings);
		
		key = "rhyme";
		meanings = new ArrayList <String>();
		meanings.add("verse");
		this.theWords.put(key, meanings);
		
		key = "rialto";
		meanings = new ArrayList <String>();
		meanings.add("Italian bridge");
		this.theWords.put(key, meanings);
		
		key = "rib";
		meanings = new ArrayList <String>();
		meanings.add("bone");
		meanings.add("make fun of");
		this.theWords.put(key, meanings);
		
		key = "ribs";
		meanings = new ArrayList <String>();
		meanings.add("bones");
		meanings.add("makes fun of");
		this.theWords.put(key, meanings);
		
		key = "rice";
		meanings = new ArrayList <String>();
		meanings.add("staple food");
		this.theWords.put(key, meanings);
		
		key = "rich";
		meanings = new ArrayList <String>();
		meanings.add("boy");
		meanings.add("well off");
		this.theWords.put(key, meanings);
		
		key = "rid";
		meanings = new ArrayList <String>();
		meanings.add("free");
		this.theWords.put(key, meanings);
		
		key = "ride";
		meanings = new ArrayList <String>();
		meanings.add("trip");
		this.theWords.put(key, meanings);
		
		key = "rides";
		meanings = new ArrayList <String>();
		meanings.add("amusement park attraction");
		this.theWords.put(key, meanings);
		
		key = "rider";
		meanings = new ArrayList <String>();
		meanings.add("cyclist");
		this.theWords.put(key, meanings);
		
		key = "ridge";
		meanings = new ArrayList <String>();
		meanings.add("hilltop");
		this.theWords.put(key, meanings);
		
		key = "rifles";
		meanings = new ArrayList <String>();
		meanings.add("arms");
		this.theWords.put(key, meanings);
		
		key = "rift";
		meanings = new ArrayList <String>();
		meanings.add("opening");
		this.theWords.put(key, meanings);
		
		key = "rifts";
		meanings = new ArrayList <String>();
		meanings.add("disagreements");
		this.theWords.put(key, meanings);
		
		key = "rig";
		meanings = new ArrayList <String>();
		meanings.add("equipment");
		this.theWords.put(key, meanings);
		
		key = "right";
		meanings = new ArrayList <String>();
		meanings.add("correct");
		this.theWords.put(key, meanings);
		
		key = "rim";
		meanings = new ArrayList <String>();
		meanings.add("border");
		meanings.add("edge");
		meanings.add("flange");
		meanings.add("part of wheel");
		this.theWords.put(key, meanings);
		
		key = "rims";
		meanings = new ArrayList <String>();
		meanings.add("borders");
		this.theWords.put(key, meanings);

		key = "ring";
		meanings = new ArrayList <String>();
		meanings.add("band");
		meanings.add("circle");
		this.theWords.put(key, meanings);
		
		key = "rio";
		meanings = new ArrayList <String>();
		meanings.add("Brazilian city");
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "riot";
		meanings = new ArrayList <String>();
		meanings.add("disturbance");
		this.theWords.put(key, meanings);
		
		key = "rioted";
		meanings = new ArrayList <String>();
		meanings.add("ran amok");
		this.theWords.put(key, meanings);
		
		key = "rip";
		meanings = new ArrayList <String>();
		meanings.add("tear");
		this.theWords.put(key, meanings);
		
		key = "ripe";
		meanings = new ArrayList <String>();
		meanings.add("ready for eating");
		meanings.add("ready to be picked");
		this.theWords.put(key, meanings);

		key = "rise";
		meanings = new ArrayList <String>();
		meanings.add("get up");
		meanings.add("increase");
		this.theWords.put(key, meanings);
		
		key = "rises";
		meanings = new ArrayList <String>();
		meanings.add("increases");
		this.theWords.put(key, meanings);
		
		key = "risk";
		meanings = new ArrayList <String>();
		meanings.add("chance");
		this.theWords.put(key, meanings);
		
		key = "risking";
		meanings = new ArrayList <String>();
		meanings.add("chancing");
		this.theWords.put(key, meanings);
		
		key = "rising";
		meanings = new ArrayList <String>();
		meanings.add("on the up");
		meanings.add("rebellion");
		this.theWords.put(key, meanings);
		
		key = "rite";
		meanings = new ArrayList <String>();
		meanings.add("ceremony");
		this.theWords.put(key, meanings);
		
		key = "river";
		meanings = new ArrayList <String>();
		meanings.add("flower");
		this.theWords.put(key, meanings);
		
		key = "riviera";
		meanings = new ArrayList <String>();
		meanings.add("part of Mediterranean");
		this.theWords.put(key, meanings);
		
		key = "roach";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "road";
		meanings = new ArrayList <String>();
		meanings.add("thoroughfare");
		this.theWords.put(key, meanings);
		
		key = "roast";
		meanings = new ArrayList <String>();
		meanings.add("cook");
		meanings.add("severely criticise");
		this.theWords.put(key, meanings);
		
		key = "roasting";
		meanings = new ArrayList <String>();
		meanings.add("cooking");
		meanings.add("severe criticism");
		this.theWords.put(key, meanings);
		
		key = "rob";
		meanings = new ArrayList <String>();
		meanings.add("steal");
		this.theWords.put(key, meanings);
		
		key = "robe";
		meanings = new ArrayList <String>();
		meanings.add("clothing");
		meanings.add("garment");
		meanings.add("gown");
		this.theWords.put(key, meanings);
				
		key = "robot";
		meanings = new ArrayList <String>();
		meanings.add("android");
		this.theWords.put(key, meanings);
		
		key = "robust";
		meanings = new ArrayList <String>();
		meanings.add("strong");
		this.theWords.put(key, meanings);
		
		key = "roc";
		meanings = new ArrayList <String>();
		meanings.add("old bird");
		this.theWords.put(key, meanings);
		
		key = "rod";
		meanings = new ArrayList <String>();
		meanings.add("pole");
		this.theWords.put(key, meanings);
				
		key = "rode";
		meanings = new ArrayList <String>();
		meanings.add("travelled in a vehicle");
		meanings.add("went on a bike");
		this.theWords.put(key, meanings);
		
		key = "rodent";
		meanings = new ArrayList <String>();
		meanings.add("gnawing mammal");
		this.theWords.put(key, meanings);
		
		key = "rodeo";
		meanings = new ArrayList <String>();
		meanings.add("western show");
		this.theWords.put(key, meanings);
			
		key = "rodney";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "rogue";
		meanings = new ArrayList <String>();
		meanings.add("scoundrel");
		this.theWords.put(key, meanings);
		
		key = "rogues";
		meanings = new ArrayList <String>();
		meanings.add("scoundrels");
		this.theWords.put(key, meanings);
		
		key = "role";
		meanings = new ArrayList <String>();
		meanings.add("part");
		this.theWords.put(key, meanings);
		
		key = "roll";
		meanings = new ArrayList <String>();
		meanings.add("go round");
		this.theWords.put(key, meanings);
		
		//key = "roma";
		//meanings = new ArrayList <String>();
		//meanings.add("foreign port");
		//this.theWords.put(key, meanings);
		
		key = "roman";
		meanings = new ArrayList <String>();
		meanings.add("italian");
		this.theWords.put(key, meanings);
		
		key = "romanians";
		meanings = new ArrayList <String>();
		meanings.add("europeans");
		this.theWords.put(key, meanings);
		
		key = "romance";
		meanings = new ArrayList <String>();
		meanings.add("love story");
		this.theWords.put(key, meanings);
				
		key = "rome";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "romeo";
		meanings = new ArrayList <String>();
		meanings.add("ladies' man");
		this.theWords.put(key, meanings);
		
		key = "room";
		meanings = new ArrayList <String>();
		meanings.add("accommodation");
		meanings.add("space");
		this.theWords.put(key, meanings);
		
		key = "rooms";
		meanings = new ArrayList <String>();
		meanings.add("accommodation");
		this.theWords.put(key, meanings);
		
		key = "root";
		meanings = new ArrayList <String>();
		meanings.add("origin");
		this.theWords.put(key, meanings);
		
		key = "rope";
		meanings = new ArrayList <String>();
		meanings.add("catche");
		this.theWords.put(key, meanings);
		
		key = "ropes";
		meanings = new ArrayList <String>();
		meanings.add("catches");
		this.theWords.put(key, meanings);
			
		key = "rose";
		meanings = new ArrayList <String>();
		meanings.add("got to his feet");
		this.theWords.put(key, meanings);
		
		key = "roses";
		meanings = new ArrayList <String>();
		meanings.add("flowers");
		this.theWords.put(key, meanings);
		
		key = "rosette";
		meanings = new ArrayList <String>();
		meanings.add("like a rose");
		this.theWords.put(key, meanings);
		
		key = "rosehip";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "rot";
		meanings = new ArrayList <String>();
		meanings.add("decay");
		this.theWords.put(key, meanings);
		
		key = "rotters";
		meanings = new ArrayList <String>();
		meanings.add("nasty people");
		meanings.add("unkind people");
		this.theWords.put(key, meanings);
		
		key = "round";
		meanings = new ArrayList <String>();
		meanings.add("circular");
		this.theWords.put(key, meanings);
		
		key = "rouse";
		meanings = new ArrayList <String>();
		meanings.add("stir");
		this.theWords.put(key, meanings);
		
		key = "route";
		meanings = new ArrayList <String>();
		meanings.add("path");
		meanings.add("way");
		this.theWords.put(key, meanings);
		
		key = "row";
		meanings = new ArrayList <String>();
		meanings.add("argue");
		meanings.add("argument");
		meanings.add("dispute");
		meanings.add("line");
		this.theWords.put(key, meanings);
		
		key = "rowan";
		meanings = new ArrayList <String>();
		meanings.add("tree");
		this.theWords.put(key, meanings);
		
		key = "rows";
		meanings = new ArrayList <String>();
		meanings.add("lines");
		this.theWords.put(key, meanings);
		
		key = "royal";
		meanings = new ArrayList <String>();
		meanings.add("princely");
		this.theWords.put(key, meanings);
		
		key = "rub";
		meanings = new ArrayList <String>();
		meanings.add("massage");
		this.theWords.put(key, meanings);
		
		key = "rubber";
		meanings = new ArrayList <String>();
		meanings.add("eraser");
		meanings.add("massuer");
		this.theWords.put(key, meanings);
		
		key = "rubs";
		meanings = new ArrayList <String>();
		meanings.add("massages");
		this.theWords.put(key, meanings);
		
		key = "ruder";
		meanings = new ArrayList <String>();
		meanings.add("less polite");
		this.theWords.put(key, meanings);
		
		key = "rudder";
		meanings = new ArrayList <String>();
		meanings.add("boating device");
		this.theWords.put(key, meanings);
		
		key = "rue";
		meanings = new ArrayList <String>();
		meanings.add("regret");
		this.theWords.put(key, meanings);
		
		key = "ruer";
		meanings = new ArrayList <String>();
		meanings.add("one who regrets");
		this.theWords.put(key, meanings);
		
		key = "rugby";
		meanings = new ArrayList <String>();
		meanings.add("game");
		this.theWords.put(key, meanings);
		
		key = "ruin";
		meanings = new ArrayList <String>();
		meanings.add("wreck");
		this.theWords.put(key, meanings);
		
		key = "ruins";
		meanings = new ArrayList <String>();
		meanings.add("wrecks");
		this.theWords.put(key, meanings);
		
		key = "ruler";
		meanings = new ArrayList <String>();
		meanings.add("sovereign");
		this.theWords.put(key, meanings);
		
		key = "rum";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		meanings.add("eccentric");
		meanings.add("funny");
		meanings.add("odd");
		meanings.add("peculiar");
		meanings.add("strange");
		this.theWords.put(key, meanings);
		
		key = "rumba";
		meanings = new ArrayList <String>();
		meanings.add("dance");
		this.theWords.put(key, meanings);
		
		key = "rumoured";
		meanings = new ArrayList <String>();
		meanings.add("unverified");
		this.theWords.put(key, meanings);
		
		key = "run";
		meanings = new ArrayList <String>();
		meanings.add("flee");
		meanings.add("ladder");
		meanings.add("manage");
		meanings.add("race");
		meanings.add("sprint");
		this.theWords.put(key, meanings);
				
		key = "runaway";
		meanings = new ArrayList <String>();
		meanings.add("fugitive");
		this.theWords.put(key, meanings);
		
		key = "rung";
		meanings = new ArrayList <String>();
		meanings.add("step");
		this.theWords.put(key, meanings);
		
		key = "runs";
		meanings = new ArrayList <String>();
		meanings.add("races");
		this.theWords.put(key, meanings);
		
		key = "runt";
		meanings = new ArrayList <String>();
		meanings.add("littlest piggy");
		this.theWords.put(key, meanings);
		
		key = "runway";
		meanings = new ArrayList <String>();
		meanings.add("airstrip");
		this.theWords.put(key, meanings);
		
		key = "rupert";
		meanings = new ArrayList <String>();
		meanings.add("bear");
		this.theWords.put(key, meanings);
		
		key = "rural";
		meanings = new ArrayList <String>();
		meanings.add("rustic");
		this.theWords.put(key, meanings);
		
		key = "rurally";
		meanings = new ArrayList <String>();
		meanings.add("in the country");
		this.theWords.put(key, meanings);
		
		key = "rush";
		meanings = new ArrayList <String>();
		meanings.add("charge");
		this.theWords.put(key, meanings);
		
		key = "rustic";
		meanings = new ArrayList <String>();
		meanings.add("from the country");
		this.theWords.put(key, meanings);
		
		key = "rut";
		meanings = new ArrayList <String>();
		meanings.add("dreary routine");
		this.theWords.put(key, meanings);
		
		key = "ruth";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "ruthless";
		meanings = new ArrayList <String>();
		meanings.add("without pity");
		this.theWords.put(key, meanings);

	}//end loadRWords
	
	private void loadSWords() {
		String key;
		ArrayList <String> meanings;
		
		//load s's
		key = "sabbath";
		meanings = new ArrayList <String>();
		meanings.add("rest day");
		this.theWords.put(key, meanings);
		
		key = "sabot";
		meanings = new ArrayList <String>();
		meanings.add("French clog");
		this.theWords.put(key, meanings);
		
		key = "sabotages";
		meanings = new ArrayList <String>();
		meanings.add("deliberately wrecks");
		this.theWords.put(key, meanings);
		
		key = "sabre";
		meanings = new ArrayList <String>();
		meanings.add("weapon");
		this.theWords.put(key, meanings);
		
		key = "sack";
		meanings = new ArrayList <String>();
		meanings.add("fire");
		meanings.add("container");
		meanings.add("large bag");
		meanings.add("mailbag");
		this.theWords.put(key, meanings);
		
		key = "sackrace";//sack race
		meanings = new ArrayList <String>();
		meanings.add("competition");
		this.theWords.put(key, meanings);
		
		key = "sacks";
		meanings = new ArrayList <String>();
		meanings.add("bags");
		meanings.add("mailbags");
		this.theWords.put(key, meanings);
		
		key = "sadder";
		meanings = new ArrayList <String>();
		meanings.add("more unhappy");
		this.theWords.put(key, meanings);
		
		key = "saddle";
		meanings = new ArrayList <String>();
		meanings.add("seat");
		this.theWords.put(key, meanings);
		
		key = "saddler";
		meanings = new ArrayList <String>();
		meanings.add("leather worker");
		this.theWords.put(key, meanings);
		
		key = "safe";
		meanings = new ArrayList <String>();
		meanings.add("secure");
		this.theWords.put(key, meanings);
		
		key = "sag";
		meanings = new ArrayList <String>();
		meanings.add("droop");
		meanings.add("slump");
		meanings.add("wilt");
		this.theWords.put(key, meanings);
		
		key = "saga";
		meanings = new ArrayList <String>();
		meanings.add("long story");
		this.theWords.put(key, meanings);	
				
		key = "sage";
		meanings = new ArrayList <String>();
		meanings.add("herb");
		this.theWords.put(key, meanings);
		
		key = "sail";
		meanings = new ArrayList <String>();
		meanings.add("travel by boat");
		this.theWords.put(key, meanings);
		
		key = "saint";
		meanings = new ArrayList <String>();
		meanings.add("patient person");
		this.theWords.put(key, meanings);
		
		key = "sallow";
		meanings = new ArrayList <String>();
		meanings.add("pale");
		this.theWords.put(key, meanings);
		
		key = "sam";
		meanings = new ArrayList <String>();
		meanings.add("uncle");
		meanings.add("US uncle");
		this.theWords.put(key, meanings);

		key = "samba";
		meanings = new ArrayList <String>();
		meanings.add("ballroom dance");
		meanings.add("card game");
		meanings.add("dance");
		this.theWords.put(key, meanings);
		
		key = "same";
		meanings = new ArrayList <String>();
		meanings.add("no different");
		meanings.add("similar");
		this.theWords.put(key, meanings);
		
		key = "sample";
		meanings = new ArrayList <String>();
		meanings.add("specimen");
		this.theWords.put(key, meanings);
		
		key = "sandpipers";
		meanings = new ArrayList <String>();
		meanings.add("birds");
		this.theWords.put(key, meanings);
		
		key = "sandwich";
		meanings = new ArrayList <String>();
		meanings.add("golf course");
		meanings.add("snack");
		this.theWords.put(key, meanings);
				
		key = "sanmarino";
		meanings = new ArrayList <String>();
		meanings.add("independent republic");
		this.theWords.put(key, meanings);
		
		key = "santa";
		meanings = new ArrayList <String>();
		meanings.add("Father Christmas");
		this.theWords.put(key, meanings);
		
		key = "sap";
		meanings = new ArrayList <String>();
		meanings.add("juice of plants");
		meanings.add("lie");
		this.theWords.put(key, meanings);
		
		key = "sardine";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "sari";
		meanings = new ArrayList <String>();
		meanings.add("Eastern garment");
		this.theWords.put(key, meanings);
		
		key = "sash";
		meanings = new ArrayList <String>();
		meanings.add("band");
		this.theWords.put(key, meanings);
		
		key = "sated";
		meanings = new ArrayList <String>();
		meanings.add("fed up");
		this.theWords.put(key, meanings);
		
		key = "satin";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "saturn";
		meanings = new ArrayList <String>();
		meanings.add("Roman god");
		this.theWords.put(key, meanings);
		
		key = "satyr";
		meanings = new ArrayList <String>();
		meanings.add("deity");
		this.theWords.put(key, meanings);
		
		key = "sauce";
		meanings = new ArrayList <String>();
		meanings.add("cheek");
		this.theWords.put(key, meanings);
		
		key = "saucepan";
		meanings = new ArrayList <String>();
		meanings.add("kitchen equipment");
		this.theWords.put(key, meanings);
		
		key = "sauna";
		meanings = new ArrayList <String>();
		meanings.add("steam bath");
		this.theWords.put(key, meanings);
		
		key = "saw";
		meanings = new ArrayList <String>();
		meanings.add("noticed");
		meanings.add("witnessed");
		this.theWords.put(key, meanings);
		
		key = "say";
		meanings = new ArrayList <String>();
		meanings.add("state");
		this.theWords.put(key, meanings);
		
		key = "says";
		meanings = new ArrayList <String>();
		meanings.add("states");
		this.theWords.put(key, meanings);
		
		key = "scaffold";
		meanings = new ArrayList <String>();
		meanings.add("raised platform");
		this.theWords.put(key, meanings);
		
		key = "scalpel";
		meanings = new ArrayList <String>();
		meanings.add("one useful in theatres");
		this.theWords.put(key, meanings);
		
		key = "scam";
		meanings = new ArrayList <String>();
		meanings.add("con");
		meanings.add("fraud");
		this.theWords.put(key, meanings);
		
		key = "scamp";
		meanings = new ArrayList <String>();
		meanings.add("mischief maker");
		meanings.add("rascal");
		this.theWords.put(key, meanings);
		
		key = "scampi";
		meanings = new ArrayList <String>();
		meanings.add("prawns");
		this.theWords.put(key, meanings);
		
		key = "scan";
		meanings = new ArrayList <String>();
		meanings.add("cast an eye over");
		meanings.add("digitise a picture");
		meanings.add("read");
		this.theWords.put(key, meanings);
				
		key = "scar";
		meanings = new ArrayList <String>();
		meanings.add("mark");
		this.theWords.put(key, meanings);
				
		key = "scarf";
		meanings = new ArrayList <String>();
		meanings.add("garment");
		this.theWords.put(key, meanings);
		
		key = "scare";
		meanings = new ArrayList <String>();
		meanings.add("frighten");
		this.theWords.put(key, meanings);
		
		key = "scares";
		meanings = new ArrayList <String>();
		meanings.add("frightens");
		this.theWords.put(key, meanings);
		
		key = "scarlet";
		meanings = new ArrayList <String>();
		meanings.add("colour");
		meanings.add("bright red");
		this.theWords.put(key, meanings);
		
		key = "scarring";
		meanings = new ArrayList <String>();
		meanings.add("marks");
		this.theWords.put(key, meanings);
		
		key = "scatter";
		meanings = new ArrayList <String>();
		meanings.add("disperse");
		this.theWords.put(key, meanings);
		
		key = "scene";
		meanings = new ArrayList <String>();
		meanings.add("part in play");
		this.theWords.put(key, meanings);
		
		key = "scenic";
		meanings = new ArrayList <String>();
		meanings.add("picturesque");
		this.theWords.put(key, meanings);
				
		key = "scone";
		meanings = new ArrayList <String>();
		meanings.add("cake");
		this.theWords.put(key, meanings);
		
		key = "scoot";
		meanings = new ArrayList <String>();
		meanings.add("dash");
		this.theWords.put(key, meanings);

		key = "scope";
		meanings = new ArrayList <String>();
		meanings.add("range");
		this.theWords.put(key, meanings);
		
		key = "scores";
		meanings = new ArrayList <String>();
		meanings.add("many");
		this.theWords.put(key, meanings);
		
		key = "scorn";
		meanings = new ArrayList <String>();
		meanings.add("contempt");
		this.theWords.put(key, meanings);
		
		key = "scorpion";
		meanings = new ArrayList <String>();
		meanings.add("stinging creature");
		this.theWords.put(key, meanings);
		
		key = "scot";
		meanings = new ArrayList <String>();
		meanings.add("Highlander");
		this.theWords.put(key, meanings);
		
		key = "scotch";
		meanings = new ArrayList <String>();
		meanings.add("whisky");
		this.theWords.put(key, meanings);
		
		key = "scotland";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "scout";
		meanings = new ArrayList <String>();
		meanings.add("search for");
		this.theWords.put(key, meanings);
		
		key = "scowl";
		meanings = new ArrayList <String>();
		meanings.add("sullen look");
		this.theWords.put(key, meanings);
		
		key = "scowling";
		meanings = new ArrayList <String>();
		meanings.add("looking displeased");
		this.theWords.put(key, meanings);
		
		key = "scythe";
		meanings = new ArrayList <String>();
		meanings.add("cutter");
		this.theWords.put(key, meanings);
		
		key = "scrap";
		meanings = new ArrayList <String>();
		meanings.add("small piece of food");
		meanings.add("fight");
		this.theWords.put(key, meanings);
		
		key = "scrape";
		meanings = new ArrayList <String>();
		meanings.add("pickle");
		meanings.add("superficial wound");
		this.theWords.put(key, meanings);
		
		key = "scrawny";
		meanings = new ArrayList <String>();
		meanings.add("thin and boney");
		meanings.add("unpleasently thin");
		this.theWords.put(key, meanings);
		
		key = "scream";
		meanings = new ArrayList <String>();
		meanings.add("shout loudly");
		this.theWords.put(key, meanings);
		
		key = "scree";
		meanings = new ArrayList <String>();
		meanings.add("fallen rocks");
		meanings.add("stones");
		this.theWords.put(key, meanings);
		
		key = "screech";
		meanings = new ArrayList <String>();
		meanings.add("strident noise");
		this.theWords.put(key, meanings);
		
		key = "screen";
		meanings = new ArrayList <String>();
		meanings.add("put on television");
		this.theWords.put(key, meanings);
		
		key = "script";
		meanings = new ArrayList <String>();
		meanings.add("writing");
		this.theWords.put(key, meanings);
		
		key = "scuba";
		meanings = new ArrayList <String>();
		meanings.add("breathing apparatus");
		this.theWords.put(key, meanings);
		
		key = "scuff";
		meanings = new ArrayList <String>();
		meanings.add("scrape");
		this.theWords.put(key, meanings);
		
		key = "scuffle";
		meanings = new ArrayList <String>();
		meanings.add("fight");
		this.theWords.put(key, meanings);
		
		key = "scuppered";
		meanings = new ArrayList <String>();
		meanings.add("runied");
		this.theWords.put(key, meanings);
		
		key = "sea";
		meanings = new ArrayList <String>();
		meanings.add("body of water");
		this.theWords.put(key, meanings);
		
		key = "seafarer";
		meanings = new ArrayList <String>();
		meanings.add("sailor");
		this.theWords.put(key, meanings);
		
		key = "seance";
		meanings = new ArrayList <String>();
		meanings.add("spirited meeting");
		this.theWords.put(key, meanings);
		
		key = "seat";
		meanings = new ArrayList <String>();
		meanings.add("behind");
		meanings.add("rear");
		meanings.add("arrange");
		this.theWords.put(key, meanings);
				
		key = "sect";
		meanings = new ArrayList <String>();
		meanings.add("cult");
		this.theWords.put(key, meanings);
		
		key = "secure";
		meanings = new ArrayList <String>();
		meanings.add("fasten");
		this.theWords.put(key, meanings);
	
		key = "sediment";
		meanings = new ArrayList <String>();
		meanings.add("deposit");
		this.theWords.put(key, meanings);
		
		key = "seduction";
		meanings = new ArrayList <String>();
		meanings.add("lure");
		this.theWords.put(key, meanings);

		key = "see";
		meanings = new ArrayList <String>();
		meanings.add("look at");
		meanings.add("observe");
		this.theWords.put(key, meanings);
		
		key = "seed";
		meanings = new ArrayList <String>();
		meanings.add("pip");
		meanings.add("sow");
		this.theWords.put(key, meanings);
				
		key = "seep";
		meanings = new ArrayList <String>();
		meanings.add("ooze");
		this.theWords.put(key, meanings);
				
		key = "seer";
		meanings = new ArrayList <String>();
		meanings.add("guy predicting");
		meanings.add("viewer");
		this.theWords.put(key, meanings);
		
		key = "seethes";
		meanings = new ArrayList <String>();
		meanings.add("boils");
		this.theWords.put(key, meanings);
		
		key = "segregate";
		meanings = new ArrayList <String>();
		meanings.add("seperate");
		this.theWords.put(key, meanings);
		
		key = "select";
		meanings = new ArrayList <String>();
		meanings.add("choose");
		this.theWords.put(key, meanings);
		
		key = "selfish";
		meanings = new ArrayList <String>();
		meanings.add("ungenerous");
		this.theWords.put(key, meanings);
		
		key = "selkirk";
		meanings = new ArrayList <String>();
		meanings.add("Scottish town");
		this.theWords.put(key, meanings);
		
		key = "seller";
		meanings = new ArrayList <String>();
		meanings.add("vendor");
		this.theWords.put(key, meanings);
		
		key = "sells";
		meanings = new ArrayList <String>();
		meanings.add("vends");
		this.theWords.put(key, meanings);
		
		key = "semi";
		meanings = new ArrayList <String>();
		meanings.add("half");
		this.theWords.put(key, meanings);
		
		key = "sergeant";
		meanings = new ArrayList <String>();
		meanings.add("rank");
		this.theWords.put(key, meanings);
		
		key = "serial";
		meanings = new ArrayList <String>();
		meanings.add("repeatedly");
		this.theWords.put(key, meanings);
		
		key = "serpent";
		meanings = new ArrayList <String>();
		meanings.add("snake");
		this.theWords.put(key, meanings);
		
		key = "serve";
		meanings = new ArrayList <String>();
		meanings.add("attend to customers");
		this.theWords.put(key, meanings);
		
		key = "sesame";
		meanings = new ArrayList <String>();
		meanings.add("sort of seed");
		this.theWords.put(key, meanings);
		
		key = "set";
		meanings = new ArrayList <String>();
		meanings.add("assign");
		meanings.add("determined");
		meanings.add("group");
		meanings.add("hardened");
		meanings.add("lay");
		meanings.add("placed");
		this.theWords.put(key, meanings);
		
		key = "setoff";//set off
		meanings = new ArrayList <String>();
		meanings.add("show to advantage");
		meanings.add("start");
		this.theWords.put(key, meanings);
		
		key = "sets";
		meanings = new ArrayList <String>();
		meanings.add("assigns");
		meanings.add("hardens");
		meanings.add("lays");
		this.theWords.put(key, meanings);

		key = "settees";
		meanings = new ArrayList <String>();
		meanings.add("furniture");
		this.theWords.put(key, meanings);
		
		key = "setter";
		meanings = new ArrayList <String>();
		meanings.add("fixing agent");
		this.theWords.put(key, meanings);
		
		key = "settle";
		meanings = new ArrayList <String>();
		meanings.add("resolve");
		this.theWords.put(key, meanings);
		
		key = "settler";
		meanings = new ArrayList <String>();
		meanings.add("dweller");
		this.theWords.put(key, meanings);
		
		key = "seattle";
		meanings = new ArrayList <String>();
		meanings.add("city in America");
		this.theWords.put(key, meanings);
		
		key = "seaworthy";
		meanings = new ArrayList <String>();
		meanings.add("fit to sail");
		this.theWords.put(key, meanings);
		
		key = "seen";
		meanings = new ArrayList <String>();
		meanings.add("spotted");
		this.theWords.put(key, meanings);
		
		key = "sees";
		meanings = new ArrayList <String>();
		meanings.add("spots");
		this.theWords.put(key, meanings);
		
		key = "senator";
		meanings = new ArrayList <String>();
		meanings.add("US politician");
		this.theWords.put(key, meanings);
		
		key = "sender";
		meanings = new ArrayList <String>();
		meanings.add("poster");
		this.theWords.put(key, meanings);
		
		key = "sends";
		meanings = new ArrayList <String>();
		meanings.add("dispatches");
		this.theWords.put(key, meanings);
		
		key = "seniors";
		meanings = new ArrayList <String>();
		meanings.add("elders");
		this.theWords.put(key, meanings);
		
		key = "sent";
		meanings = new ArrayList <String>();
		meanings.add("dispatched");
		this.theWords.put(key, meanings);
		
		key = "sentimental";
		meanings = new ArrayList <String>();
		meanings.add("emotional");
		this.theWords.put(key, meanings);
		
		key = "sentry";
		meanings = new ArrayList <String>();
		meanings.add("guard");
		this.theWords.put(key, meanings);
		
		key = "serene";
		meanings = new ArrayList <String>();
		meanings.add("calm");
		this.theWords.put(key, meanings);
		
		key = "serengeti";
		meanings = new ArrayList <String>();
		meanings.add("game reserve");
		this.theWords.put(key, meanings);
		
		key = "serf";
		meanings = new ArrayList <String>();
		meanings.add("slave");
		this.theWords.put(key, meanings);
		
		key = "servant";
		meanings = new ArrayList <String>();
		meanings.add("domestic worker");
		this.theWords.put(key, meanings);
		
		key = "seventh";
		meanings = new ArrayList <String>();
		meanings.add("in the top ten, for example");
		this.theWords.put(key, meanings);
		
		key = "sever";
		meanings = new ArrayList <String>();
		meanings.add("cut off");
		this.theWords.put(key, meanings);
		
		key = "sewing";
		meanings = new ArrayList <String>();
		meanings.add("needlework");
		this.theWords.put(key, meanings);
		
		key = "sewn";
		meanings = new ArrayList <String>();
		meanings.add("stitched together");
		this.theWords.put(key, meanings);
		
		key = "shabby";
		meanings = new ArrayList <String>();
		meanings.add("run-down");
		this.theWords.put(key, meanings);
		
		key = "shallots";
		meanings = new ArrayList <String>();
		meanings.add("vegetables");
		this.theWords.put(key, meanings);
		
		key = "shallow";
		meanings = new ArrayList <String>();
		meanings.add("superficial");
		this.theWords.put(key, meanings);
				
		key = "sham";
		meanings = new ArrayList <String>();
		meanings.add("not real");
		meanings.add("lie");
		this.theWords.put(key, meanings);
		
		key = "shambles";
		meanings = new ArrayList <String>();
		meanings.add("chaos");
		meanings.add("mess");
		this.theWords.put(key, meanings);
		
		key = "shame";
		meanings = new ArrayList <String>();
		meanings.add("disgrace");
		this.theWords.put(key, meanings);
		
		key = "shape";
		meanings = new ArrayList <String>();
		meanings.add("form");
		this.theWords.put(key, meanings);
		
		key = "shaped";
		meanings = new ArrayList <String>();
		meanings.add("moulded");
		meanings.add("influenced");
		this.theWords.put(key, meanings);
		
		key = "shark";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
				
		key = "shaw";
		meanings = new ArrayList <String>();
		meanings.add("playright");
		this.theWords.put(key, meanings);
		
		key = "shawl";
		meanings = new ArrayList <String>();
		meanings.add("garment");
		this.theWords.put(key, meanings);
		
		key = "shay";
		meanings = new ArrayList <String>();
		meanings.add("carriage");
		this.theWords.put(key, meanings);
		
		key = "she";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "shed";
		meanings = new ArrayList <String>();
		meanings.add("throw off");
		meanings.add("garden building");
		this.theWords.put(key, meanings);
		
		key = "sheep";
		meanings = new ArrayList <String>();
		meanings.add("merino");
		this.theWords.put(key, meanings);
		
		key = "sherpas";
		meanings = new ArrayList <String>();
		meanings.add("people in the Hymalayas");
		this.theWords.put(key, meanings);
		
		key = "sherry";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "shield";
		meanings = new ArrayList <String>();
		meanings.add("visor");
		this.theWords.put(key, meanings);
		
		key = "ship";
		meanings = new ArrayList <String>();
		meanings.add("boat");
		this.theWords.put(key, meanings);
				
		key = "shivering";
		meanings = new ArrayList <String>();
		meanings.add("trembling");
		this.theWords.put(key, meanings);
		
		key = "shogun";
		meanings = new ArrayList <String>();
		meanings.add("emperor");
		this.theWords.put(key, meanings);
		
		key = "shoo";
		meanings = new ArrayList <String>();
		meanings.add("go away");
		this.theWords.put(key, meanings);
		
		key = "shoot";
		meanings = new ArrayList <String>();
		meanings.add("fire gun");
		this.theWords.put(key, meanings);
		
		key = "shop";
		meanings = new ArrayList <String>();
		meanings.add("tell on");
		this.theWords.put(key, meanings);
		
		key = "shopper";
		meanings = new ArrayList <String>();
		meanings.add("one making purchases");
		meanings.add("retailer");
		this.theWords.put(key, meanings);
		
		key = "shot";
		meanings = new ArrayList <String>();
		meanings.add("attempt");
		meanings.add("fired");
		this.theWords.put(key, meanings);
				
		key = "shotput";
		meanings = new ArrayList <String>();
		meanings.add("field event");
		this.theWords.put(key, meanings);
		
		key = "shovel";
		meanings = new ArrayList <String>();
		meanings.add("gardening tool");
		meanings.add("tool");
		this.theWords.put(key, meanings);
		
		key = "show";
		meanings = new ArrayList <String>();
		meanings.add("display");
		meanings.add("exhibit");
		this.theWords.put(key, meanings);
		
		key = "shrieked";
		meanings = new ArrayList <String>();
		meanings.add("made a lot of noise");
		this.theWords.put(key, meanings);
		
		key = "shrive";
		meanings = new ArrayList <String>();
		meanings.add("hear the confession of");
		this.theWords.put(key, meanings);
		
		key = "shrivel";
		meanings = new ArrayList <String>();
		meanings.add("dry up");
		this.theWords.put(key, meanings);
		
		key = "shrub";
		meanings = new ArrayList <String>();
		meanings.add("bush");
		meanings.add("woody plant");
		this.theWords.put(key, meanings);
		
		key = "shrug";
		meanings = new ArrayList <String>();
		meanings.add("express a lack of interest");
		this.theWords.put(key, meanings);
		
		key = "shut";
		meanings = new ArrayList <String>();
		meanings.add("closed");
		this.theWords.put(key, meanings);
		
		key = "shutup";
		meanings = new ArrayList <String>();
		meanings.add("confined");
		meanings.add("keep mum");
		this.theWords.put(key, meanings);
				
		key = "shy";
		meanings = new ArrayList <String>();
		meanings.add("bashful");
		meanings.add("quick throw");
		meanings.add("reserved");
		meanings.add("reticant");
		meanings.add("short");
		meanings.add("throw quickly");
		meanings.add("timid");
		this.theWords.put(key, meanings);
		
		key = "side";
		meanings = new ArrayList <String>();
		meanings.add("players");
		meanings.add("team");
		this.theWords.put(key, meanings);
		
		key = "sidle";
		meanings = new ArrayList <String>();
		meanings.add("move cautiously");
		this.theWords.put(key, meanings);
				
		key = "sieve";
		meanings = new ArrayList <String>();
		meanings.add("strainer");
		this.theWords.put(key, meanings);
		
		key = "sigma";
		meanings = new ArrayList <String>();
		meanings.add("Greek character");
		this.theWords.put(key, meanings);
		
		key = "sign";
		meanings = new ArrayList <String>();
		meanings.add("banner");
		meanings.add("indication");
		meanings.add("nonverbal action");
		meanings.add("notice");
		meanings.add("part of zodiac");
		this.theWords.put(key, meanings);
		
		key = "signal";
		meanings = new ArrayList <String>();
		meanings.add("sign");
		this.theWords.put(key, meanings);
		
		key = "signore";
		meanings = new ArrayList <String>();
		meanings.add("italian woman");
		this.theWords.put(key, meanings);
		
		key = "silent";
		meanings = new ArrayList <String>();
		meanings.add("not speaking");
		this.theWords.put(key, meanings);
		
		key = "silk";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "silo";
		meanings = new ArrayList <String>();
		meanings.add("storage");
		this.theWords.put(key, meanings);
		
		key = "silver";
		meanings = new ArrayList <String>();
		meanings.add("colour");
		meanings.add("precious metal");
		this.theWords.put(key, meanings);
		
		key = "simmer";
		meanings = new ArrayList <String>();
		meanings.add("cook");
		this.theWords.put(key, meanings);
		
		key = "simple";
		meanings = new ArrayList <String>();
		meanings.add("easy");
		this.theWords.put(key, meanings);
		
		key = "sin";
		meanings = new ArrayList <String>();
		meanings.add("evil");
		this.theWords.put(key, meanings);
		
		key = "since";
		meanings = new ArrayList <String>();
		meanings.add("because");
		this.theWords.put(key, meanings);	
		
		key = "sincere";
		meanings = new ArrayList <String>();
		meanings.add("genuine");
		this.theWords.put(key, meanings);
		
		key = "sinew";
		meanings = new ArrayList <String>();
		meanings.add("tendon");
		this.theWords.put(key, meanings);
		
		key = "sing";
		meanings = new ArrayList <String>();
		meanings.add("make musical sounds");
		this.theWords.put(key, meanings);
		
		key = "singe";
		meanings = new ArrayList <String>();
		meanings.add("superficial burn");
		this.theWords.put(key, meanings);
		
		key = "singer";
		meanings = new ArrayList <String>();
		meanings.add("vocalist");
		this.theWords.put(key, meanings);
		
		key = "singers";
		meanings = new ArrayList <String>();
		meanings.add("entertainers");
		meanings.add("vocalists");
		this.theWords.put(key, meanings);
		
		key = "singlet";
		meanings = new ArrayList <String>();
		meanings.add("vest");
		this.theWords.put(key, meanings);
				
		key = "sinister";
		meanings = new ArrayList <String>();
		meanings.add("threatening");
		this.theWords.put(key, meanings);
		
		key = "sink";
		meanings = new ArrayList <String>();
		meanings.add("diminish");
		meanings.add("drink alcohol");
		meanings.add("go down");
		this.theWords.put(key, meanings);
		
		key = "siphon";
		meanings = new ArrayList <String>();
		meanings.add("draw off");
		this.theWords.put(key, meanings);
		
		key = "sir";
		meanings = new ArrayList <String>();
		meanings.add("knight");
		this.theWords.put(key, meanings);
		
		key = "sister";
		meanings = new ArrayList <String>();
		meanings.add("one of the family");
		meanings.add("relative");
		meanings.add("sibling");
		this.theWords.put(key, meanings);
		
		key = "site";
		meanings = new ArrayList <String>();
		meanings.add("location");
		this.theWords.put(key, meanings);
		
		key = "sits";
		meanings = new ArrayList <String>();
		meanings.add("rests");
		this.theWords.put(key, meanings);
		
		key = "six";
		meanings = new ArrayList <String>();
		meanings.add("high boundary");
		this.theWords.put(key, meanings);
		
		key = "sixth";
		meanings = new ArrayList <String>();
		meanings.add("roughly 17%");
		this.theWords.put(key, meanings);
					
		key = "sketch";
		meanings = new ArrayList <String>();
		meanings.add("draw");
		this.theWords.put(key, meanings);
		
		key = "skew";
		meanings = new ArrayList <String>();
		meanings.add("crooked");
		this.theWords.put(key, meanings);
		
		key = "skewbald";
		meanings = new ArrayList <String>();
		meanings.add("mottled horse");
		this.theWords.put(key, meanings);
		
		key = "ski";
		meanings = new ArrayList <String>();
		meanings.add("snow shoe");
		this.theWords.put(key, meanings);
		
		key = "skilled";
		meanings = new ArrayList <String>();
		meanings.add("expert");
		this.theWords.put(key, meanings);
		
		key = "skimp";
		meanings = new ArrayList <String>();
		meanings.add("be sparing");
		this.theWords.put(key, meanings);
		
		key = "skin";
		meanings = new ArrayList <String>();
		meanings.add("hide");
		this.theWords.put(key, meanings);
		
		key = "skindeep";//skin deep
		meanings = new ArrayList <String>();
		meanings.add("superficial");
		this.theWords.put(key, meanings);
		
		key = "skinflints";
		meanings = new ArrayList <String>();
		meanings.add("mean people");
		this.theWords.put(key, meanings);
		
		key = "skip";
		meanings = new ArrayList <String>();
		meanings.add("rubbish container");
		this.theWords.put(key, meanings);
		
	
		key = "skin";
		meanings = new ArrayList <String>();
		meanings.add("outer layer");
		meanings.add("peel");
		this.theWords.put(key, meanings);
		
		key = "skipper";
		meanings = new ArrayList <String>();
		meanings.add("captain");
		this.theWords.put(key, meanings);
		
		key = "slacken";
		meanings = new ArrayList <String>();
		meanings.add("become loose");
		this.theWords.put(key, meanings);
		
		key = "slang";
		meanings = new ArrayList <String>();
		meanings.add("jargon");
		this.theWords.put(key, meanings);
		
		key = "slat";
		meanings = new ArrayList <String>();
		meanings.add("piece of wood");
		this.theWords.put(key, meanings);
		
		key = "slate";
		meanings = new ArrayList <String>();
		meanings.add("roofing material");
		meanings.add("lambaste");
		meanings.add("savage");
		meanings.add("severely criticise");
		this.theWords.put(key, meanings);
		
		key = "slay";
		meanings = new ArrayList <String>();
		meanings.add("bump off");
		meanings.add("kill");
		meanings.add("massacre");
		meanings.add("murder");
		this.theWords.put(key, meanings);
		
		key = "slaying";
		meanings = new ArrayList <String>();
		meanings.add("murder");
		this.theWords.put(key, meanings);
		
		key = "sledge";
		meanings = new ArrayList <String>();
		meanings.add("vehicle mounted on runners");
		this.theWords.put(key, meanings);
		
		key = "sleep";
		meanings = new ArrayList <String>();
		meanings.add("nap");
		this.theWords.put(key, meanings);
		
		key = "sleepy";
		meanings = new ArrayList <String>();
		meanings.add("ready for bed");
		this.theWords.put(key, meanings);
		
		key = "sleek";
		meanings = new ArrayList <String>();
		meanings.add("smooth and shiny");
		this.theWords.put(key, meanings);
		
		key = "slender";
		meanings = new ArrayList <String>();
		meanings.add("narrow");
		this.theWords.put(key, meanings);
		
		key = "sleuth";
		meanings = new ArrayList <String>();
		meanings.add("detective");
		meanings.add("private investigator");
		this.theWords.put(key, meanings);
		
		key = "slide";
		meanings = new ArrayList <String>();
		meanings.add("playgraound feature");
		this.theWords.put(key, meanings);
		
		key = "sligo";
		meanings = new ArrayList <String>();
		meanings.add("town in Ireland");
		this.theWords.put(key, meanings);
		
		key = "slimmer";
		meanings = new ArrayList <String>();
		meanings.add("not so fat");
		this.theWords.put(key, meanings);
		
		key = "sling";
		meanings = new ArrayList <String>();
		meanings.add("chuck");
		meanings.add("support");
		this.theWords.put(key, meanings);
		
		key = "slings";
		meanings = new ArrayList <String>();
		meanings.add("supports");
		this.theWords.put(key, meanings);
		
		key = "sloop";
		meanings = new ArrayList <String>();
		meanings.add("sailing boat");
		this.theWords.put(key, meanings);
		
		key = "slope";
		meanings = new ArrayList <String>();
		meanings.add("part of a mountain");
		meanings.add("lie at an angle");
		this.theWords.put(key, meanings);
		
		key = "slot";
		meanings = new ArrayList <String>();
		meanings.add("opening");
		this.theWords.put(key, meanings);
		
		key = "sloth";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		this.theWords.put(key, meanings);
		
		key = "slow";
		meanings = new ArrayList <String>();
		meanings.add("unhurried");
		meanings.add("lagging behind");
		this.theWords.put(key, meanings);
		
		key = "smash";
		meanings = new ArrayList <String>();
		meanings.add("big hit");
		this.theWords.put(key, meanings);
		
		key = "smoke";
		meanings = new ArrayList <String>();
		meanings.add("vapour");
		this.theWords.put(key, meanings);
		
		key = "smother";
		meanings = new ArrayList <String>();
		meanings.add("stifle");
		this.theWords.put(key, meanings);
		
		key = "smug";
		meanings = new ArrayList <String>();
		meanings.add("overly confident");
		this.theWords.put(key, meanings);
		
		key = "smut";
		meanings = new ArrayList <String>();
		meanings.add("obscene stuff");
		this.theWords.put(key, meanings);
		
		key = "snag";
		meanings = new ArrayList <String>();
		meanings.add("catch");
		meanings.add("problem");
		meanings.add("sharp protuberance");
		meanings.add("unforseen obstacle");
		this.theWords.put(key, meanings);
		
		key = "snail";
		meanings = new ArrayList <String>();
		meanings.add("mollusc");
		this.theWords.put(key, meanings);
		
		key = "snared";
		meanings = new ArrayList <String>();
		meanings.add("trapped");
		this.theWords.put(key, meanings);
		
		key = "sneer";
		meanings = new ArrayList <String>();
		meanings.add("mock");
		this.theWords.put(key, meanings);
		
		key = "snide";
		meanings = new ArrayList <String>();
		meanings.add("disparaging");
		this.theWords.put(key, meanings);
		
		key = "snip";
		meanings = new ArrayList <String>();
		meanings.add("bargain");
		this.theWords.put(key, meanings);
		
		key = "sniper";
		meanings = new ArrayList <String>();
		meanings.add("marksman");
		this.theWords.put(key, meanings);
		
		key = "snips";
		meanings = new ArrayList <String>();
		meanings.add("cuts");
		this.theWords.put(key, meanings);
		
		key = "snoop";
		meanings = new ArrayList <String>();
		meanings.add("investigate");
		this.theWords.put(key, meanings);
		
		key = "snoops";
		meanings = new ArrayList <String>();
		meanings.add("spies");
		this.theWords.put(key, meanings);
		
		key = "snoopy";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		this.theWords.put(key, meanings);
		
		key = "so";
		meanings = new ArrayList <String>();
		meanings.add("as such");
		meanings.add("thus");
		this.theWords.put(key, meanings);
		
		key = "sock";
		meanings = new ArrayList <String>();
		meanings.add("hard blow");
		this.theWords.put(key, meanings);
		
		key = "socrates";
		meanings = new ArrayList <String>();
		meanings.add("philosopher");
		this.theWords.put(key, meanings);
		
		key = "sod";
		meanings = new ArrayList <String>();
		meanings.add("turf");
		this.theWords.put(key, meanings);
		
		key = "soda";
		meanings = new ArrayList <String>();
		meanings.add("mixer");
		this.theWords.put(key, meanings);
		
		key = "sofa";
		meanings = new ArrayList <String>();
		meanings.add("setee");
		this.theWords.put(key, meanings);
		
		key = "sofia";
		meanings = new ArrayList <String>();
		meanings.add("capital");
		this.theWords.put(key, meanings);
		
		key = "soho";
		meanings = new ArrayList <String>();
		meanings.add("London district");
		this.theWords.put(key, meanings);
		
		key = "soil";
		meanings = new ArrayList <String>();
		meanings.add("earth");
		this.theWords.put(key, meanings);
		
		key = "sold";
		meanings = new ArrayList <String>();
		meanings.add("auctioned off");
		this.theWords.put(key, meanings);
		
		key = "sole";
		meanings = new ArrayList <String>();
		meanings.add("only");
		meanings.add("part of a shoe");
		this.theWords.put(key, meanings);
		
		key = "solid";
		meanings = new ArrayList <String>();
		meanings.add("firm");
		this.theWords.put(key, meanings);
		
		key = "solution";
		meanings = new ArrayList <String>();
		meanings.add("answer");
		this.theWords.put(key, meanings);
				
		key = "solve";		
		meanings = new ArrayList <String>();
		meanings.add("crack");
		this.theWords.put(key, meanings);
		
		key = "somali";
		meanings = new ArrayList <String>();
		meanings.add("african");
		this.theWords.put(key, meanings);
		
		key = "sombre";
		meanings = new ArrayList <String>();
		meanings.add("solemn");
		this.theWords.put(key, meanings);
		
		key = "some";
		meanings = new ArrayList <String>();
		meanings.add("a few");
		meanings.add("part");
		this.theWords.put(key, meanings);
		
		key = "somerset";
		meanings = new ArrayList <String>();
		meanings.add("county");
		this.theWords.put(key, meanings);
		
		key = "son";
		meanings = new ArrayList <String>();
		meanings.add("boy");
		meanings.add("child");
		meanings.add("family member");
		this.theWords.put(key, meanings);
			
		key = "sonata";
		meanings = new ArrayList <String>();
		meanings.add("musical work");
		this.theWords.put(key, meanings);
				
		key = "song";
		meanings = new ArrayList <String>();
		meanings.add("vocal");
		this.theWords.put(key, meanings);
		
		key = "songstress";
		meanings = new ArrayList <String>();
		meanings.add("female singer");
		this.theWords.put(key, meanings);
		
		key = "soppy";
		meanings = new ArrayList <String>();
		meanings.add("sentimental");
		this.theWords.put(key, meanings);
		
		key = "sorbet";
		meanings = new ArrayList <String>();
		meanings.add("water ice");
		this.theWords.put(key, meanings);
		
		key = "sore";
		meanings = new ArrayList <String>();
		meanings.add("tender");
		this.theWords.put(key, meanings);
		
		key = "sort";
		meanings = new ArrayList <String>();
		meanings.add("type");
		this.theWords.put(key, meanings);
		
		key = "sot";
		meanings = new ArrayList <String>();
		meanings.add("drunkard");
		this.theWords.put(key, meanings);
		
		key = "sots";
		meanings = new ArrayList <String>();
		meanings.add("drunkards");
		this.theWords.put(key, meanings);
		
		key = "sow";
		meanings = new ArrayList <String>();
		meanings.add("pig");
		this.theWords.put(key, meanings);
				
		key = "soy";
		meanings = new ArrayList <String>();
		meanings.add("sauce");
		this.theWords.put(key, meanings);
		
		key = "spa";
		meanings = new ArrayList <String>();
		meanings.add("health resort");
		this.theWords.put(key, meanings);
		
		key = "spades";
		meanings = new ArrayList <String>();
		meanings.add("shovels");
		meanings.add("suit");
		this.theWords.put(key, meanings);
		
		key = "spas";
		meanings = new ArrayList <String>();
		meanings.add("health resorts");
		this.theWords.put(key, meanings);
		
		key = "space";
		meanings = new ArrayList <String>();
		meanings.add("room");
		this.theWords.put(key, meanings);
		
		key = "spacing";
		meanings = new ArrayList <String>();
		meanings.add("distance apart");
		meanings.add("gaps");
		meanings.add("the distance between any two objects");
		this.theWords.put(key, meanings);
		
		key = "spacious";
		meanings = new ArrayList <String>();
		meanings.add("large");
		this.theWords.put(key, meanings);
		
		key = "spaniard";
		meanings = new ArrayList <String>();
		meanings.add("European");
		this.theWords.put(key, meanings);
		
		key = "spaniel";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		this.theWords.put(key, meanings);
		
		key = "spaniels";
		meanings = new ArrayList <String>();
		meanings.add("dogs");
		this.theWords.put(key, meanings);
		
		key = "spanish";
		meanings = new ArrayList <String>();
		meanings.add("European");
		this.theWords.put(key, meanings);

		key = "spar";
		meanings = new ArrayList <String>();
		meanings.add("pole");
		this.theWords.put(key, meanings);
		
		key = "spare";
		meanings = new ArrayList <String>();
		meanings.add("free");
		meanings.add("held in reserve");
		meanings.add("left over");
		meanings.add("superfluous");
		this.theWords.put(key, meanings);
		
		key = "spark";
		meanings = new ArrayList <String>();
		meanings.add("small amount");
		this.theWords.put(key, meanings);
		
		key = "sparrow";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "spartan";
		meanings = new ArrayList <String>();
		meanings.add("old Greek");
		this.theWords.put(key, meanings);
		
		key = "spatula";
		meanings = new ArrayList <String>();
		meanings.add("kitchen equipment");
		this.theWords.put(key, meanings);
		
		key = "speak";
		meanings = new ArrayList <String>();
		meanings.add("say");
		this.theWords.put(key, meanings);
		
		key = "spear";
		meanings = new ArrayList <String>();
		meanings.add("long stick with a sharp tip");
		meanings.add("long thin strip");
		meanings.add("sharp tool");
		this.theWords.put(key, meanings);

		key = "spearhead";
		meanings = new ArrayList <String>();
		meanings.add("lead an attack");
		meanings.add("sharp end of tool");
		this.theWords.put(key, meanings);
		
		key = "specimen";
		meanings = new ArrayList <String>();
		meanings.add("example");
		this.theWords.put(key, meanings);
		
		key = "spectacles";
		meanings = new ArrayList <String>();
		meanings.add("glasses");
		this.theWords.put(key, meanings);
		
		key = "spectra";
		meanings = new ArrayList <String>();
		meanings.add("colour ranges");
		this.theWords.put(key, meanings);
		
		key = "speed";
		meanings = new ArrayList <String>();
		meanings.add("pace");
		this.theWords.put(key, meanings);
		
		key = "spend";
		meanings = new ArrayList <String>();
		meanings.add("occupy");
		this.theWords.put(key, meanings);
		
		key = "spent";
		meanings = new ArrayList <String>();
		meanings.add("exhausted");
		this.theWords.put(key, meanings);
		
		key = "spider";
		meanings = new ArrayList <String>();
		meanings.add("little crawler");
		this.theWords.put(key, meanings);
		
		key = "spigot";
		meanings = new ArrayList <String>();
		meanings.add("cask peg");
		this.theWords.put(key, meanings);
		
		key = "spin";
		meanings = new ArrayList <String>();
		meanings.add("rotate");
		meanings.add("turn");
		this.theWords.put(key, meanings);
				
		key = "spinach";
		meanings = new ArrayList <String>();
		meanings.add("green");
		this.theWords.put(key, meanings);
		
		key = "spire";
		meanings = new ArrayList <String>();
		meanings.add("part of church");
		meanings.add("steeple");
		this.theWords.put(key, meanings);
		
		key = "spite";
		meanings = new ArrayList <String>();
		meanings.add("hurt");
		meanings.add("malice");
		this.theWords.put(key, meanings);
		
		key = "splatter";
		meanings = new ArrayList <String>();
		meanings.add("messily daub");
		this.theWords.put(key, meanings);
		
		key = "split";
		meanings = new ArrayList <String>();
		meanings.add("Adriatic port");
		meanings.add("leave");
		this.theWords.put(key, meanings);
		
		key = "spoon";
		meanings = new ArrayList <String>();
		meanings.add("utensil");
		this.theWords.put(key, meanings);
		
		key = "spoons";
		meanings = new ArrayList <String>();
		meanings.add("items in canteen");
		meanings.add("utensils");
		this.theWords.put(key, meanings);
		
		key = "spot";
		meanings = new ArrayList <String>();
		meanings.add("notice");
		this.theWords.put(key, meanings);
		
		key = "spots";
		meanings = new ArrayList <String>();
		meanings.add("defects");
		meanings.add("notices");
		this.theWords.put(key, meanings);
		
		key = "sportive";
		meanings = new ArrayList <String>();
		meanings.add("playful");
		this.theWords.put(key, meanings);
				
		key = "spray";
		meanings = new ArrayList <String>();
		meanings.add("jet");
		meanings.add("shower");
		this.theWords.put(key, meanings);
		
		key = "spread";
		meanings = new ArrayList <String>();
		meanings.add("reach");
		this.theWords.put(key, meanings);
		
		key = "spree";
		meanings = new ArrayList <String>();
		meanings.add("shopping trip");
		this.theWords.put(key, meanings);
		
		key = "spring";
		meanings = new ArrayList <String>();
		meanings.add("before summer");
		meanings.add("source of water");
		this.theWords.put(key, meanings);
				
		key = "springy";
		meanings = new ArrayList <String>();
		meanings.add("elastic");
		this.theWords.put(key, meanings);
		
		key = "sprint";
		meanings = new ArrayList <String>();
		meanings.add("run");
		this.theWords.put(key, meanings);
		
		key = "sprite";
		meanings = new ArrayList <String>();
		meanings.add("elf");
		meanings.add("fairy");
		meanings.add("hobgoblin");
		this.theWords.put(key, meanings);
		
		key = "sprites";
		meanings = new ArrayList <String>();
		meanings.add("spirits");
		meanings.add("elves");
		meanings.add("hobgoblins");
		meanings.add("computer graphics maybe?");
		this.theWords.put(key, meanings);
		
		key = "sprout";
		meanings = new ArrayList <String>();
		meanings.add("produce new leaves");
		meanings.add("vegetable");
		this.theWords.put(key, meanings);
		
		key = "spud";
		meanings = new ArrayList <String>();
		meanings.add("potato");
		this.theWords.put(key, meanings);
		
		key = "spy";
		meanings = new ArrayList <String>();
		meanings.add("agent");
		meanings.add("see");
		this.theWords.put(key, meanings);
		
		key = "squatter";
		meanings = new ArrayList <String>();
		meanings.add("unlawful occupant");
		this.theWords.put(key, meanings);
		
		key = "stab";
		meanings = new ArrayList <String>();
		meanings.add("attempt");
		this.theWords.put(key, meanings);
		
		key = "stabiliser";
		meanings = new ArrayList <String>();
		meanings.add("food additive");
		this.theWords.put(key, meanings);
		
		key = "stable";
		meanings = new ArrayList <String>();
		meanings.add("farm building");
		meanings.add("place for horses");
		meanings.add("steady");
		meanings.add("with no wobble");
		this.theWords.put(key, meanings);
		
		key = "stabling";
		meanings = new ArrayList <String>();
		meanings.add("accommodation for horses");
		this.theWords.put(key, meanings);
		
		key = "stack";
		meanings = new ArrayList <String>();
		meanings.add("tall chimney");
		this.theWords.put(key, meanings);
		
		key = "stacks";
		meanings = new ArrayList <String>();
		meanings.add("heaps");
		this.theWords.put(key, meanings);
		
		key = "stag";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("deer");
		this.theWords.put(key, meanings);
		
		key = "stage";
		meanings = new ArrayList <String>();
		meanings.add("leg");
		this.theWords.put(key, meanings);
		
		key = "stains";
		meanings = new ArrayList <String>();
		meanings.add("spots");
		this.theWords.put(key, meanings);
		
		key = "stairwell";
		meanings = new ArrayList <String>();
		meanings.add("vertical space for building");
		this.theWords.put(key, meanings);
		
		key = "stale";
		meanings = new ArrayList <String>();
		meanings.add("hackneyed");
		meanings.add("jaded");
		meanings.add("old");
		this.theWords.put(key, meanings);
		
		key = "stalemate";
		meanings = new ArrayList <String>();
		meanings.add("draw");
		this.theWords.put(key, meanings);
		
		key = "stamen";
		meanings = new ArrayList <String>();
		meanings.add("part of a flower");
		this.theWords.put(key, meanings);
		
		key = "stamped";
		meanings = new ArrayList <String>();
		meanings.add("trod on");
		this.theWords.put(key, meanings);
		
		key = "stampede";
		meanings = new ArrayList <String>();
		meanings.add("mad rush");
		this.theWords.put(key, meanings);
		
		key = "stan";
		meanings = new ArrayList <String>();
		meanings.add("Ollie's pal");
		this.theWords.put(key, meanings);
		
		key = "stand";
		meanings = new ArrayList <String>();
		meanings.add("bear");
		meanings.add("leave your seat");
		this.theWords.put(key, meanings);
		
		key = "stank";
		meanings = new ArrayList <String>();
		meanings.add("drain");
		this.theWords.put(key, meanings);
		
		key = "star";
		meanings = new ArrayList <String>();
		meanings.add("celebrity");
		meanings.add("sun");
		this.theWords.put(key, meanings);
		
		key = "starch";
		meanings = new ArrayList <String>();
		meanings.add("stiffener");
		this.theWords.put(key, meanings);
		
		key = "stare";
		meanings = new ArrayList <String>();
		meanings.add("ogle");
		this.theWords.put(key, meanings);
		
		key = "starers";
		meanings = new ArrayList <String>();
		meanings.add("gazers");
		this.theWords.put(key, meanings);
		
		key = "starkers";
		meanings = new ArrayList <String>();
		meanings.add("naked");
		this.theWords.put(key, meanings);
		
		key = "starling";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "start";
		meanings = new ArrayList <String>();
		meanings.add("begin");
		meanings.add("beginning");
		meanings.add("commence");
		this.theWords.put(key, meanings);
		
		key = "startling";
		meanings = new ArrayList <String>();
		meanings.add("surprising");
		this.theWords.put(key, meanings);
		
		key = "starved";
		meanings = new ArrayList <String>();
		meanings.add("went hungry");
		this.theWords.put(key, meanings);
		
		key = "state";
		meanings = new ArrayList <String>();
		meanings.add("say");
		this.theWords.put(key, meanings);
		
		key = "static";
		meanings = new ArrayList <String>();
		meanings.add("still");
		this.theWords.put(key, meanings);
		
		key = "station";
		meanings = new ArrayList <String>();
		meanings.add("position");
		this.theWords.put(key, meanings);
		
		key = "stay";
		meanings = new ArrayList <String>();
		meanings.add("remain");
		meanings.add("support");
		this.theWords.put(key, meanings);
		
		key = "steadier";
		meanings = new ArrayList <String>();
		meanings.add("more secure");
		this.theWords.put(key, meanings);
		
		key = "steak";
		meanings = new ArrayList <String>();
		meanings.add("cut of beef");
		meanings.add("piece of meat");
		this.theWords.put(key, meanings);
		
		key = "steal";
		meanings = new ArrayList <String>();
		meanings.add("nick");
		this.theWords.put(key, meanings);
		
		key = "stealing";
		meanings = new ArrayList <String>();
		meanings.add("illegally taking");
		meanings.add("nicking");
		this.theWords.put(key, meanings);
		
		key = "steam";
		meanings = new ArrayList <String>();
		meanings.add("old engine driver");
		meanings.add("one way to cook");
		meanings.add("vapour");
		this.theWords.put(key, meanings);
		
		key = "steaming";
		meanings = new ArrayList <String>();
		meanings.add("cooking");
		this.theWords.put(key, meanings);
		
		key = "steed";
		meanings = new ArrayList <String>();
		meanings.add("horse");
		this.theWords.put(key, meanings);
		
		key = "steel";
		meanings = new ArrayList <String>();
		meanings.add("metal");
		this.theWords.put(key, meanings);
		
		key = "steely";
		meanings = new ArrayList <String>();
		meanings.add("tough");
		this.theWords.put(key, meanings);
		
		key = "step";
		meanings = new ArrayList <String>();
		meanings.add("pace");
		this.theWords.put(key, meanings);
		
		key = "steerage";
		meanings = new ArrayList <String>();
		meanings.add("accommodation aboard ship");
		this.theWords.put(key, meanings);
		
		key = "sterile";
		meanings = new ArrayList <String>();
		meanings.add("lacking vitality");
		this.theWords.put(key, meanings);
		
		key = "sterling";
		meanings = new ArrayList <String>();
		meanings.add("currency");
		this.theWords.put(key, meanings);
		
		key = "stern";
		meanings = new ArrayList <String>();
		meanings.add("rear of ship");
		meanings.add("rigid");
		meanings.add("serious");
		meanings.add("violinist");//Isaac Stern, Soviet-born violinist
		this.theWords.put(key, meanings);
		
		key = "sternum";
		meanings = new ArrayList <String>();
		meanings.add("bone");
		meanings.add("part of a skeleton");
		this.theWords.put(key, meanings);
		
		key = "stereo";
		meanings = new ArrayList <String>();
		meanings.add("music system");
		this.theWords.put(key, meanings);
		
		key = "stetson";
		meanings = new ArrayList <String>();
		meanings.add("cowboy hat");
		this.theWords.put(key, meanings);
		
		key = "stew";
		meanings = new ArrayList <String>();
		meanings.add("casserole");
		this.theWords.put(key, meanings);
		
		key = "stewards";
		meanings = new ArrayList <String>();
		meanings.add("race officials");
		this.theWords.put(key, meanings);
				
		key = "stiff";
		meanings = new ArrayList <String>();
		meanings.add("rigid");
		this.theWords.put(key, meanings);
		
		key = "stigma";
		meanings = new ArrayList <String>();
		meanings.add("shame");
		this.theWords.put(key, meanings);
		
		key = "still";
		meanings = new ArrayList <String>();
		meanings.add("unmoving");
		this.theWords.put(key, meanings);
		
		key = "stilton";
		meanings = new ArrayList <String>();
		meanings.add("cheese");
		this.theWords.put(key, meanings);
		
		key = "stilts";
		meanings = new ArrayList <String>();
		meanings.add("circus props");
		this.theWords.put(key, meanings);
		
		key = "stinker";
		meanings = new ArrayList <String>();
		meanings.add("smelly person");
		this.theWords.put(key, meanings);
		
		key = "stipend";
		meanings = new ArrayList <String>();
		meanings.add("salary");
		this.theWords.put(key, meanings);
		
		key = "stipple";
		meanings = new ArrayList <String>();
		meanings.add("cover with dots");
		this.theWords.put(key, meanings);
		
		key = "stir";
		meanings = new ArrayList <String>();
		meanings.add("commotion");
		meanings.add("prison");
		this.theWords.put(key, meanings);
		
		key = "stirring";
		meanings = new ArrayList <String>();
		meanings.add("causing upset");
		this.theWords.put(key, meanings);
		
		key = "stockings";
		meanings = new ArrayList <String>();
		meanings.add("hose");
		this.theWords.put(key, meanings);
		
		key = "stole";
		meanings = new ArrayList <String>();
		meanings.add("thieved");
		this.theWords.put(key, meanings);
		
		key = "stop";
		meanings = new ArrayList <String>();
		meanings.add("end");
		this.theWords.put(key, meanings);
		
		key = "stops";
		meanings = new ArrayList <String>();
		meanings.add("prevents");
		this.theWords.put(key, meanings);
		
		key = "stories";
		meanings = new ArrayList <String>();
		meanings.add("accounts");
		this.theWords.put(key, meanings);
		
		key = "story";
		meanings = new ArrayList <String>();
		meanings.add("fable");
		meanings.add("tale");
		this.theWords.put(key, meanings);
		
		key = "strain";
		meanings = new ArrayList <String>();
		meanings.add("force");
		meanings.add("remove solids");
		this.theWords.put(key, meanings);
				
		key = "straitlaced"; //strait laced
		meanings = new ArrayList <String>();
		meanings.add("prim");
		this.theWords.put(key, meanings);
		
		key = "strange";
		meanings = new ArrayList <String>();
		meanings.add("alien");
		meanings.add("odd");
		meanings.add("unusual");
		this.theWords.put(key, meanings);
		
		key = "stranger";
		meanings = new ArrayList <String>();
		meanings.add("one unfamiliar with the area?");
		meanings.add("unknown person");
		this.theWords.put(key, meanings);
		
		key = "strangle";
		meanings = new ArrayList <String>();
		meanings.add("throttle");
		this.theWords.put(key, meanings);
		
		key = "strangler";
		meanings = new ArrayList <String>();
		meanings.add("killer");
		this.theWords.put(key, meanings);
		
		key = "straw";
		meanings = new ArrayList <String>();
		meanings.add("dried corn stalks");
		this.theWords.put(key, meanings);
		
		key = "stream";
		meanings = new ArrayList <String>();
		meanings.add("brook");
		this.theWords.put(key, meanings);
		
		key = "street";
		meanings = new ArrayList <String>();
		meanings.add("way");
		this.theWords.put(key, meanings);
		
		key = "stress";
		meanings = new ArrayList <String>();
		meanings.add("strain");
		this.theWords.put(key, meanings);
		
		key = "stretches";
		meanings = new ArrayList <String>();
		meanings.add("extends");
		meanings.add("prison sentences");
		this.theWords.put(key, meanings);
				
		key = "stretchy";
		meanings = new ArrayList <String>();
		meanings.add("rubbery");
		this.theWords.put(key, meanings);
		
		key = "strict";
		meanings = new ArrayList <String>();
		meanings.add("stern");
		this.theWords.put(key, meanings);
		
		key = "strife";
		meanings = new ArrayList <String>();
		meanings.add("conflict");
		this.theWords.put(key, meanings);
		
		key = "strip";
		meanings = new ArrayList <String>();
		meanings.add("take off");
		meanings.add("teams clothing");
		this.theWords.put(key, meanings);
		
		key = "stripe";
		meanings = new ArrayList <String>();
		meanings.add("band");
		this.theWords.put(key, meanings);
		
		key = "stripes";
		meanings = new ArrayList <String>();
		meanings.add("patterns");
		meanings.add("military badges");
		this.theWords.put(key, meanings);
		
		key = "strobe";
		meanings = new ArrayList <String>();
		meanings.add("bright light");
		this.theWords.put(key, meanings);
		
		key = "strode";
		meanings = new ArrayList <String>();
		meanings.add("took large step");
		this.theWords.put(key, meanings);
		
		key = "stroll";
		meanings = new ArrayList <String>();
		meanings.add("gentle walk");
		meanings.add("saunter");
		this.theWords.put(key, meanings);
		
		key = "strong";
		meanings = new ArrayList <String>();
		meanings.add("powerfull");
		this.theWords.put(key, meanings);
		
		key = "stud";
		meanings = new ArrayList <String>();
		meanings.add("breeding farm");
		meanings.add("hunk");
		this.theWords.put(key, meanings);
		
		key = "studious";
		meanings = new ArrayList <String>();
		meanings.add("bookish");
		this.theWords.put(key, meanings);
		
		key = "study";
		meanings = new ArrayList <String>();
		meanings.add("room");
		this.theWords.put(key, meanings);
		
		key = "sturdy";
		meanings = new ArrayList <String>();
		meanings.add("robust");
		this.theWords.put(key, meanings);
		
		key = "sty";
		meanings = new ArrayList <String>();
		meanings.add("enclosure");
		meanings.add("farmyard feature");
		meanings.add("pen");
		this.theWords.put(key, meanings);
		
		key = "stye";
		meanings = new ArrayList <String>();
		meanings.add("eye disorder");
		meanings.add("eyesore");
		meanings.add("infection");
		this.theWords.put(key, meanings);
		
		key = "styx";
		meanings = new ArrayList <String>();
		meanings.add("river in Hades");
		this.theWords.put(key, meanings);
		
		key = "suckle";
		meanings = new ArrayList <String>();
		meanings.add("feed");
		this.theWords.put(key, meanings);
		
		key = "sudanese";
		meanings = new ArrayList <String>();
		meanings.add("Africans");
		this.theWords.put(key, meanings);
		
		key = "sue";
		meanings = new ArrayList <String>();
		meanings.add("take legal action");
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "sues";
		meanings = new ArrayList <String>();
		meanings.add("takes legal action");
		this.theWords.put(key, meanings);
		
		key = "suet";
		meanings = new ArrayList <String>();
		meanings.add("fat");
		this.theWords.put(key, meanings);
		
		key = "suez";
		meanings = new ArrayList <String>();
		meanings.add("canal");
		this.theWords.put(key, meanings);
		
		key = "sum";
		meanings = new ArrayList <String>();
		meanings.add("amount");
		meanings.add("problem");
		meanings.add("total");
		this.theWords.put(key, meanings);
		
		key = "sumo";
		meanings = new ArrayList <String>();
		meanings.add("Japanese wrestling");
		this.theWords.put(key, meanings);
		
		key = "sun";
		meanings = new ArrayList <String>();
		meanings.add("star");
		this.theWords.put(key, meanings);
		
		key = "sunrise";
		meanings = new ArrayList <String>();
		meanings.add("break of day");
		this.theWords.put(key, meanings);
		
		key = "sup";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "super";
		meanings = new ArrayList <String>();
		meanings.add("brilliant");
		meanings.add("fantastic");
		meanings.add("great");
		this.theWords.put(key, meanings);
		
		key = "superb";
		meanings = new ArrayList <String>();
		meanings.add("fantastic");
		this.theWords.put(key, meanings);
		
		key = "supermarket";
		meanings = new ArrayList <String>();
		meanings.add("store");
		this.theWords.put(key, meanings);
		
		key = "supervisor";
		meanings = new ArrayList <String>();
		meanings.add("controller");
		this.theWords.put(key, meanings);
		
		key = "supper";
		meanings = new ArrayList <String>();
		meanings.add("meal");
		this.theWords.put(key, meanings);
		
		key = "support";
		meanings = new ArrayList <String>();
		meanings.add("encourage");
		this.theWords.put(key, meanings);
		
		key = "support";
		meanings = new ArrayList <String>();
		meanings.add("encourage");
		this.theWords.put(key, meanings);
		
		key = "sure";
		meanings = new ArrayList <String>();
		meanings.add("certain");
		this.theWords.put(key, meanings);
		
		key = "surer";
		meanings = new ArrayList <String>();
		meanings.add("more confident");
		this.theWords.put(key, meanings);
		
		key = "sushi";
		meanings = new ArrayList <String>();
		meanings.add("Japanese dish");
		this.theWords.put(key, meanings);
		
		key = "sustains";
		meanings = new ArrayList <String>();
		meanings.add("bears");
		this.theWords.put(key, meanings);
		
		key = "swaddle";
		meanings = new ArrayList <String>();
		meanings.add("wrap up");
		this.theWords.put(key, meanings);
		
		key = "swagger";
		meanings = new ArrayList <String>();
		meanings.add("walk in a cocky manner");
		this.theWords.put(key, meanings);
		
		key = "swallow";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		meanings.add("consume");
		this.theWords.put(key, meanings);
		
		key = "swam";
		meanings = new ArrayList <String>();
		meanings.add("moved through water");
		this.theWords.put(key, meanings);
		
		key = "swamp";
		meanings = new ArrayList <String>();
		meanings.add("marsh");
		this.theWords.put(key, meanings);
		
		key = "swede";
		meanings = new ArrayList <String>();
		meanings.add("european");
		meanings.add("vegetable");
		this.theWords.put(key, meanings);
		
		key = "sweetheart";
		meanings = new ArrayList <String>();
		meanings.add("loved one");
		this.theWords.put(key, meanings);
				
		key = "sweetmeat";
		meanings = new ArrayList <String>();
		meanings.add("sugar coated goody");
		this.theWords.put(key, meanings);
		
		key = "swell";
		meanings = new ArrayList <String>();
		meanings.add("expand");
		meanings.add("fine");
		this.theWords.put(key, meanings);
				
		key = "swine";
		meanings = new ArrayList <String>();
		meanings.add("rotter");
		this.theWords.put(key, meanings);
		
		key = "swing";
		meanings = new ArrayList <String>();
		meanings.add("move from side to side");
		this.theWords.put(key, meanings);
		
		key = "switch";
		meanings = new ArrayList <String>();
		meanings.add("change");
		this.theWords.put(key, meanings);

	}//end loadSWords
	
	private void loadTWords() {
		String key;
		ArrayList <String> meanings;
		
		//load t's
		
		key = "ta";
		meanings = new ArrayList <String>();
		meanings.add("brief thanks");
		meanings.add("thanks");
		meanings.add("thank you");
		this.theWords.put(key, meanings);
		
		key = "table";
		meanings = new ArrayList <String>();
		meanings.add("present formally");
		this.theWords.put(key, meanings);
		
		key = "tables";
		meanings = new ArrayList <String>();
		meanings.add("presents formally");
		this.theWords.put(key, meanings);
		
		key = "tablet";
		meanings = new ArrayList <String>();
		meanings.add("small computer");
		meanings.add("pill");
		this.theWords.put(key, meanings);
		
		key = "taboo";
		meanings = new ArrayList <String>();
		meanings.add("forbidden");
		this.theWords.put(key, meanings);
				
		key = "tack";
		meanings = new ArrayList <String>();
		meanings.add("nail");
		this.theWords.put(key, meanings);
		
		key = "tackle";
		meanings = new ArrayList <String>();
		meanings.add("equipment");
		meanings.add("get to grips with");
		this.theWords.put(key, meanings);
		
		key = "tackled";
		meanings = new ArrayList <String>();
		meanings.add("had a go at");
		this.theWords.put(key, meanings);
		
		key = "taffeta";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "tagged";
		meanings = new ArrayList <String>();
		meanings.add("added on");
		meanings.add("labelled");
		this.theWords.put(key, meanings);
		
		key = "tail";
		meanings = new ArrayList <String>();
		meanings.add("follow");
		this.theWords.put(key, meanings);
						
		key = "tailor";
		meanings = new ArrayList <String>();
		meanings.add("outfitter");
		meanings.add("customise");
		this.theWords.put(key, meanings);
		
		key = "tailspin";
		meanings = new ArrayList <String>();
		meanings.add("out of control");
		this.theWords.put(key, meanings);
		
		key = "tain";
		meanings = new ArrayList <String>();
		meanings.add("tinfoil");
		this.theWords.put(key, meanings);
		
		key = "take";
		meanings = new ArrayList <String>();
		meanings.add("confiscate");
		this.theWords.put(key, meanings);
		
		key = "talc";
		meanings = new ArrayList <String>();
		meanings.add("perfumed powder");
		meanings.add("powder");
		this.theWords.put(key, meanings);
		
		key = "tale";
		meanings = new ArrayList <String>();
		meanings.add("story");
		this.theWords.put(key, meanings);
		
		key = "talents";
		meanings = new ArrayList <String>();
		meanings.add("aptitudes");
		meanings.add("gifts");
		this.theWords.put(key, meanings);
		
		key = "tales";
		meanings = new ArrayList <String>();
		meanings.add("stories");
		this.theWords.put(key, meanings);
		
		key = "talks";
		meanings = new ArrayList <String>();
		meanings.add("chats");
		this.theWords.put(key, meanings);
		
		key = "tame";
		meanings = new ArrayList <String>();
		meanings.add("boring");
		meanings.add("domesticated");
		this.theWords.put(key, meanings);
		
		key = "tan";
		meanings = new ArrayList <String>();
		meanings.add("bronze");
		meanings.add("tawny");
		meanings.add("turn brown");
		meanings.add("yellowish-brown colour");
		this.theWords.put(key, meanings);
		
		key = "tans";
		meanings = new ArrayList <String>();
		meanings.add("browns");
		this.theWords.put(key, meanings);
		
		key = "tank";
		meanings = new ArrayList <String>();
		meanings.add("container");
		meanings.add("large receptacle");
		meanings.add("armoured vehicle");
		this.theWords.put(key, meanings);
		
		key = "tang";
		meanings = new ArrayList <String>();
		meanings.add("strong tast");
		this.theWords.put(key, meanings);
		
		key = "tangerine";
		meanings = new ArrayList <String>();
		meanings.add("fruit");
		this.theWords.put(key, meanings);
		
		key = "tangible";
		meanings = new ArrayList <String>();
		meanings.add("can be touched");
		this.theWords.put(key, meanings);
		
		key = "tangle";
		meanings = new ArrayList <String>();
		meanings.add("become involved with");
		meanings.add("confusion");
		this.theWords.put(key, meanings);
		
		key = "tap";
		meanings = new ArrayList <String>();
		meanings.add("bug");
		this.theWords.put(key, meanings);
		
		key = "tape";
		meanings = new ArrayList <String>();
		meanings.add("record");
		this.theWords.put(key, meanings);
		
		key = "taper";
		meanings = new ArrayList <String>();
		meanings.add("gradually narrow");
		meanings.add("gradually lessen");
		meanings.add("slender lighter");
		this.theWords.put(key, meanings);
		
		key = "tapes";
		meanings = new ArrayList <String>();
		meanings.add("records");
		this.theWords.put(key, meanings);
		
		key = "tapestry";
		meanings = new ArrayList <String>();
		meanings.add("hanging");
		this.theWords.put(key, meanings);
		
		key = "taproom";
		meanings = new ArrayList <String>();
		meanings.add("bar");
		this.theWords.put(key, meanings);
		
		key = "tar";
		meanings = new ArrayList <String>();
		meanings.add("dark, thick flammable liquid");
		meanings.add("sailor");
		this.theWords.put(key, meanings);
		
		key = "tarn";
		meanings = new ArrayList <String>();
		meanings.add("small mountain lake");
		this.theWords.put(key, meanings);
		
		key = "tarns";
		meanings = new ArrayList <String>();
		meanings.add("small mountain lakes");
		this.theWords.put(key, meanings);
		
		key = "tarpaulin";
		meanings = new ArrayList <String>();
		meanings.add("cover");
		this.theWords.put(key, meanings);
		
		key = "tart";
		meanings = new ArrayList <String>();
		meanings.add("pastry");
		this.theWords.put(key, meanings);
		
		key = "tartar";
		meanings = new ArrayList <String>();
		meanings.add("sauce");
		this.theWords.put(key, meanings);
		
		key = "tarzan";
		meanings = new ArrayList <String>();
		meanings.add("swinger");
		this.theWords.put(key, meanings);
		
		key = "taskmaster";
		meanings = new ArrayList <String>();
		meanings.add("boss");
		this.theWords.put(key, meanings);
		
		key = "taste";
		meanings = new ArrayList <String>();
		meanings.add("sense");
		this.theWords.put(key, meanings);
		
		key = "tasty";
		meanings = new ArrayList <String>();
		meanings.add("delicious");
		this.theWords.put(key, meanings);
		
		key = "tat";
		meanings = new ArrayList <String>();
		meanings.add("rubbish");
		meanings.add("shoddy goods");
		this.theWords.put(key, meanings);
		
		key = "tate";
		meanings = new ArrayList <String>();
		meanings.add("gallery");
		this.theWords.put(key, meanings);
		
		key = "tattle";
		meanings = new ArrayList <String>();
		meanings.add("mere gossip");
		this.theWords.put(key, meanings);
		
		key = "taunt";
		meanings = new ArrayList <String>();
		meanings.add("tease");
		this.theWords.put(key, meanings);
		
		key = "taverns";
		meanings = new ArrayList <String>();
		meanings.add("inns");
		this.theWords.put(key, meanings);
		
		key = "tax";
		meanings = new ArrayList <String>();
		meanings.add("charge");
		meanings.add("levy");
		this.theWords.put(key, meanings);
		
		key = "taxi";
		meanings = new ArrayList <String>();
		meanings.add("cab");
		meanings.add("hired vehicle");
		meanings.add("transport");
		this.theWords.put(key, meanings);
		
		key = "tay";
		meanings = new ArrayList <String>();
		meanings.add("Scottish river");
		this.theWords.put(key, meanings);
		
		key = "tea";
		meanings = new ArrayList <String>();
		meanings.add("beverage");
		meanings.add("drink");
		meanings.add("early meal");
		meanings.add("hot drink");
		meanings.add("meal");
		this.theWords.put(key, meanings);
		
		key = "teacher";
		meanings = new ArrayList <String>();
		meanings.add("tutor");
		this.theWords.put(key, meanings);
		
		key = "teachers";
		meanings = new ArrayList <String>();
		meanings.add("tutors");
		this.theWords.put(key, meanings);
		
		key = "teak";
		meanings = new ArrayList <String>();
		meanings.add("wood");
		this.theWords.put(key, meanings);
		
		key = "teal";
		meanings = new ArrayList <String>();
		meanings.add("duck");
		this.theWords.put(key, meanings);
		
		key = "teapot";
		meanings = new ArrayList <String>();
		meanings.add("vessel");
		this.theWords.put(key, meanings);
		
		key = "teas";
		meanings = new ArrayList <String>();
		meanings.add("drinks");
		this.theWords.put(key, meanings);
		
		key = "tease";
		meanings = new ArrayList <String>();
		meanings.add("kid");
		meanings.add("make fun of");
		this.theWords.put(key, meanings);
				
		key = "team";
		meanings = new ArrayList <String>();
		meanings.add("crew");
		meanings.add("group of players");
		meanings.add("side");
		meanings.add("two or more people working together");
		this.theWords.put(key, meanings);
		
		key = "tear";
		meanings = new ArrayList <String>();
		meanings.add("pull apart");
		meanings.add("pull to pieces");
		meanings.add("rip");
		meanings.add("make a hole in");
		meanings.add("come apart");
		meanings.add("damage");
		this.theWords.put(key, meanings);
		
		key = "tearinto"; //tear into
		meanings = new ArrayList <String>();
		meanings.add("attack vigorously");
		this.theWords.put(key, meanings);
		
		key = "tee";
		meanings = new ArrayList <String>();
		meanings.add("where golf starts");
		meanings.add("warm weather top");
		this.theWords.put(key, meanings);
				
		key = "teem";
		meanings = new ArrayList <String>();
		meanings.add("abound");
		this.theWords.put(key, meanings);
		
		key = "teeth";
		meanings = new ArrayList <String>();
		meanings.add("gnashers");
		this.theWords.put(key, meanings);
		
		key = "tehran";
		meanings = new ArrayList <String>();
		meanings.add("capital");
		this.theWords.put(key, meanings);
		
		key = "telegram";
		meanings = new ArrayList <String>();
		meanings.add("wire");
		this.theWords.put(key, meanings);
		
		key = "tell";
		meanings = new ArrayList <String>();
		meanings.add("famous archer");
		meanings.add("narrate");
		meanings.add("report");
		this.theWords.put(key, meanings);
				
		key = "temp";
		meanings = new ArrayList <String>();
		meanings.add("part-timer");
		this.theWords.put(key, meanings);
		
		key = "temple";
		meanings = new ArrayList <String>();
		meanings.add("place of worship");
		this.theWords.put(key, meanings);
		
		key = "tempo";
		meanings = new ArrayList <String>();
		meanings.add("speed");
		this.theWords.put(key, meanings);
		
		key = "teenager";
		meanings = new ArrayList <String>();
		meanings.add("young person");
		this.theWords.put(key, meanings);
		
		key = "teens";
		meanings = new ArrayList <String>();
		meanings.add("young people");
		this.theWords.put(key, meanings);
		
		key = "tees";
		meanings = new ArrayList <String>();
		meanings.add("northern river");
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "ten";
		meanings = new ArrayList <String>();
		meanings.add("number");
		this.theWords.put(key, meanings);
		
		key = "tench";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "tend";
		meanings = new ArrayList <String>();
		meanings.add("lean");
		meanings.add("nurse");
		this.theWords.put(key, meanings);
		
		key = "tended";
		meanings = new ArrayList <String>();
		meanings.add("looked after");
		meanings.add("nursed");
		this.theWords.put(key, meanings);
		
		key = "tender";
		meanings = new ArrayList <String>();
		meanings.add("offer");
		this.theWords.put(key, meanings);
		
		key = "tendon";
		meanings = new ArrayList <String>();
		meanings.add("sinew");
		this.theWords.put(key, meanings);
				
		key = "tent";
		meanings = new ArrayList <String>();
		meanings.add("portable shelter");
		meanings.add("shelter");
		meanings.add("temporary accommodation");
		this.theWords.put(key, meanings);
				
		key = "terrain";
		meanings = new ArrayList <String>();
		meanings.add("earth");
		meanings.add("stretch of land");
		this.theWords.put(key, meanings);
		
		key = "terrapin";
		meanings = new ArrayList <String>();
		meanings.add("reptile");
		meanings.add("turtle");
		this.theWords.put(key, meanings);
		
		key = "terrier";
		meanings = new ArrayList <String>();
		meanings.add("dog");
		this.theWords.put(key, meanings);
		
		key = "terrified";
		meanings = new ArrayList <String>();
		meanings.add("very scared");
		this.theWords.put(key, meanings);

		key = "term";
		meanings = new ArrayList <String>();
		meanings.add("period of time");
		this.theWords.put(key, meanings);
		
		key = "terminal";
		meanings = new ArrayList <String>();
		meanings.add("end");
		this.theWords.put(key, meanings);
		
		key = "tern";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		this.theWords.put(key, meanings);
		
		key = "terror";
		meanings = new ArrayList <String>();
		meanings.add("dread");
		this.theWords.put(key, meanings);
		
		key = "tess";
		meanings = new ArrayList <String>();
		meanings.add("Hardy heroin");
		this.theWords.put(key, meanings);
		
		key = "test";
		meanings = new ArrayList <String>();
		meanings.add("cricket match");
		meanings.add("exam");
		meanings.add("examination");
		meanings.add("trial");
		this.theWords.put(key, meanings);
		
		key = "testament";
		meanings = new ArrayList <String>();
		meanings.add("will");
		meanings.add("dividion of the bible");
		this.theWords.put(key, meanings);
		
		key = "tester";
		meanings = new ArrayList <String>();
		meanings.add("examiner");
		meanings.add("sample");
		this.theWords.put(key, meanings);
		
		key = "text";
		meanings = new ArrayList <String>();
		meanings.add("words");
		this.theWords.put(key, meanings);
		
		key = "thames";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "than";
		meanings = new ArrayList <String>();
		meanings.add("preposition, say");
		this.theWords.put(key, meanings);
		
		key = "thank";
		meanings = new ArrayList <String>();
		meanings.add("express gratitude");
		this.theWords.put(key, meanings);
		
		key = "thankyou"; //thank you
		meanings = new ArrayList <String>();
		meanings.add("gratitude");
		this.theWords.put(key, meanings);
				
		key = "thaw";
		meanings = new ArrayList <String>();
		meanings.add("melt");
		this.theWords.put(key, meanings);
		
		key = "the";
		meanings = new ArrayList <String>();
		meanings.add("article");
		this.theWords.put(key, meanings);
		
		key = "theatre";
		meanings = new ArrayList <String>();
		meanings.add("auditorium");
		meanings.add("playhouse");
		this.theWords.put(key, meanings);
		
		key = "theft";
		meanings = new ArrayList <String>();
		meanings.add("robbery");
		this.theWords.put(key, meanings);
		
		key = "their";
		meanings = new ArrayList <String>();
		meanings.add("belonging to");
		this.theWords.put(key, meanings);
		
		key = "theme";
		meanings = new ArrayList <String>();
		meanings.add("subject");
		this.theWords.put(key, meanings);
		
		key = "there";
		meanings = new ArrayList <String>();
		meanings.add("that place");
		this.theWords.put(key, meanings);
		
		key = "theresa";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "thermostat";
		meanings = new ArrayList <String>();
		meanings.add("temperature regulator");
		this.theWords.put(key, meanings);
		
		key = "thesaurus";
		meanings = new ArrayList <String>();
		meanings.add("a type of dictionary");
		this.theWords.put(key, meanings);
		
		key = "thesis";
		meanings = new ArrayList <String>();
		meanings.add("dissertation");
		this.theWords.put(key, meanings);
		
		key = "thespian";
		meanings = new ArrayList <String>();
		meanings.add("actor");
		this.theWords.put(key, meanings);
		
		key = "thief";
		meanings = new ArrayList <String>();
		meanings.add("burglar");
		meanings.add("criminal");
		meanings.add("plagarist");
		meanings.add("robber");
		this.theWords.put(key, meanings);
		
		key = "thigh";
		meanings = new ArrayList <String>();
		meanings.add("part of leg");
		this.theWords.put(key, meanings);
		
		key = "thin";
		meanings = new ArrayList <String>();
		meanings.add("like a rake");
		meanings.add("slender");
		this.theWords.put(key, meanings);
		
		key = "thing";
		meanings = new ArrayList <String>();
		meanings.add("item");
		this.theWords.put(key, meanings);
				
		key = "think";
		meanings = new ArrayList <String>();
		meanings.add("consider");
		this.theWords.put(key, meanings);
		
		key = "this";
		meanings = new ArrayList <String>();
		meanings.add("identifier");
		this.theWords.put(key, meanings);
		
		key = "thistle";
		meanings = new ArrayList <String>();
		meanings.add("Scotland's emblem");
		this.theWords.put(key, meanings);
				
		key = "thong";
		meanings = new ArrayList <String>();
		meanings.add("leather strap");
		this.theWords.put(key, meanings);
		
		key = "those";
		meanings = new ArrayList <String>();
		meanings.add("them things");
		this.theWords.put(key, meanings);
		
		key = "thor";
		meanings = new ArrayList <String>();
		meanings.add("god");
		meanings.add("Norse god");
		this.theWords.put(key, meanings);
		
		key = "thorny";
		meanings = new ArrayList <String>();
		meanings.add("awkward");
		this.theWords.put(key, meanings);
		
		key = "though";
		meanings = new ArrayList <String>();
		meanings.add("even");
		meanings.add("nevertheless");
		this.theWords.put(key, meanings);
		
		key = "thought";
		meanings = new ArrayList <String>();
		meanings.add("deliberation");
		meanings.add("idea");
		this.theWords.put(key, meanings);
		
		key = "threat";
		meanings = new ArrayList <String>();
		meanings.add("ultimatum");
		this.theWords.put(key, meanings);
		
		key = "thrill";
		meanings = new ArrayList <String>();
		meanings.add("kick");
		this.theWords.put(key, meanings);
		
		key = "thrive";
		meanings = new ArrayList <String>();
		meanings.add("do well");
		this.theWords.put(key, meanings);
		
		key = "throne";
		meanings = new ArrayList <String>();
		meanings.add("ceremonial chair");
		this.theWords.put(key, meanings);
		
		key = "throng";
		meanings = new ArrayList <String>();
		meanings.add("crowd");
		this.theWords.put(key, meanings);
		
		key = "throw";
		meanings = new ArrayList <String>();
		meanings.add("cast");
		this.theWords.put(key, meanings);
		
		key = "thrust";
		meanings = new ArrayList <String>();
		meanings.add("push");
		this.theWords.put(key, meanings);
				
		key = "thug";
		meanings = new ArrayList <String>();
		meanings.add("bully");
		this.theWords.put(key, meanings);
		
		key = "thus";
		meanings = new ArrayList <String>();
		meanings.add("like this");
		this.theWords.put(key, meanings);
				
		key = "thy";
		meanings = new ArrayList <String>();
		meanings.add("your old");
		this.theWords.put(key, meanings);
		
		key = "tibet";
		meanings = new ArrayList <String>();
		meanings.add("country");
		meanings.add("himalayan ridge");
		this.theWords.put(key, meanings);
		
		key = "tick";
		meanings = new ArrayList <String>();
		meanings.add("parasite");
		this.theWords.put(key, meanings);
		
		key = "tickled";
		meanings = new ArrayList <String>();
		meanings.add("amused");
		this.theWords.put(key, meanings);
		
		key = "tide";
		meanings = new ArrayList <String>();
		meanings.add("sea movement");
		this.theWords.put(key, meanings);
		
		key = "tidings";
		meanings = new ArrayList <String>();
		meanings.add("news");
		this.theWords.put(key, meanings);
		
		key = "tidying";
		meanings = new ArrayList <String>();
		meanings.add("cleaning up");
		this.theWords.put(key, meanings);
		
		key = "tier";
		meanings = new ArrayList <String>();
		meanings.add("part of wedding cake");
		meanings.add("row");
		this.theWords.put(key, meanings);
		
		key = "tiger";
		meanings = new ArrayList <String>();
		meanings.add("animal");
		meanings.add("big cat");
		this.theWords.put(key, meanings);
		
		key = "tigers";
		meanings = new ArrayList <String>();
		meanings.add("animals");
		meanings.add("big cats");
		this.theWords.put(key, meanings);
		
		key = "tight";
		meanings = new ArrayList <String>();
		meanings.add("mean");
		this.theWords.put(key, meanings);
		
		key = "tighten";
		meanings = new ArrayList <String>();
		meanings.add("narrow");
		meanings.add("secure");
		this.theWords.put(key, meanings);
		
		key = "tile";
		meanings = new ArrayList <String>();
		meanings.add("roofing item");
		this.theWords.put(key, meanings);
		
		key = "tilt";
		meanings = new ArrayList <String>();
		meanings.add("lean");
		this.theWords.put(key, meanings);
		
		key = "tilts";
		meanings = new ArrayList <String>();
		meanings.add("leanings");
		this.theWords.put(key, meanings);
		
		key = "tiber";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "timber";
		meanings = new ArrayList <String>();
		meanings.add("wood");
		this.theWords.put(key, meanings);
		
		key = "time";
		meanings = new ArrayList <String>();
		meanings.add("alloted period");
		meanings.add("occasion");
		meanings.add("record");
		meanings.add("interval between two events");
		meanings.add("what landlords call");
		this.theWords.put(key, meanings);
		
		key = "timer";
		meanings = new ArrayList <String>();
		meanings.add("clock");
		this.theWords.put(key, meanings);
		
		key = "times";
		meanings = new ArrayList <String>();
		meanings.add("multiply");
		this.theWords.put(key, meanings);
		
		key = "tin";
		meanings = new ArrayList <String>();
		meanings.add("can");
		meanings.add("metal");
		this.theWords.put(key, meanings);
		
		key = "tinker";
		meanings = new ArrayList <String>();
		meanings.add("mess about");
		this.theWords.put(key, meanings);
		
		key = "tinsel";
		meanings = new ArrayList <String>();
		meanings.add("christmas decoration");
		this.theWords.put(key, meanings);
		
		key = "tiny";
		meanings = new ArrayList <String>();
		meanings.add("very small");
		this.theWords.put(key, meanings);
		
		key = "tipple";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "tirade";
		meanings = new ArrayList <String>();
		meanings.add("long angry speech");
		this.theWords.put(key, meanings);

		key = "tire";
		meanings = new ArrayList <String>();
		meanings.add("become weary");
		this.theWords.put(key, meanings);
		
		key = "tired";
		meanings = new ArrayList <String>();
		meanings.add("worn out");
		this.theWords.put(key, meanings);
		
		key = "tissue";
		meanings = new ArrayList <String>();
		meanings.add("dsiposable item");
		this.theWords.put(key, meanings);
		
		key = "tit";
		meanings = new ArrayList <String>();
		meanings.add("bird");
		meanings.add("little bird");
		meanings.add("small bird");
		this.theWords.put(key, meanings);
		
		key = "titan";
		meanings = new ArrayList <String>();
		meanings.add("enormous man");
		this.theWords.put(key, meanings);
		
		key = "titbit";
		meanings = new ArrayList <String>();
		meanings.add("morsel");
		this.theWords.put(key, meanings);
		
		key = "title";
		meanings = new ArrayList <String>();
		meanings.add("name");
		this.theWords.put(key, meanings);
		
		key = "to";
		meanings = new ArrayList <String>();
		meanings.add("in a direction");
		meanings.add("until");
		this.theWords.put(key, meanings);
		
		key = "toast";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "tobago";
		meanings = new ArrayList <String>();
		meanings.add("island");
		this.theWords.put(key, meanings);
		
		key = "toby";
		meanings = new ArrayList <String>();
		meanings.add("man");
		this.theWords.put(key, meanings);
		
		key = "toe";
		meanings = new ArrayList <String>();
		meanings.add("front of a shoe");
		this.theWords.put(key, meanings);
		
		key = "toes";
		meanings = new ArrayList <String>();
		meanings.add("digits");
		this.theWords.put(key, meanings);
		
		key = "toecap";
		meanings = new ArrayList <String>();
		meanings.add("boot protector");
		this.theWords.put(key, meanings);
		
		key = "toga";
		meanings = new ArrayList <String>();
		meanings.add("Roman costume");
		this.theWords.put(key, meanings);
		
		key = "toil";
		meanings = new ArrayList <String>();
		meanings.add("work");
		this.theWords.put(key, meanings);
		
		key = "toiletroll";
		meanings = new ArrayList <String>();
		meanings.add("extensive quantity of paper");
		this.theWords.put(key, meanings);
		
		key = "tolerance";
		meanings = new ArrayList <String>();
		meanings.add("ability to endure");
		this.theWords.put(key, meanings);
		
		key = "tolerate";
		meanings = new ArrayList <String>();
		meanings.add("bear");
		this.theWords.put(key, meanings);
		
		key = "toll";
		meanings = new ArrayList <String>();
		meanings.add("charge");
		meanings.add("payment");
		this.theWords.put(key, meanings);
		
		key = "tom";
		meanings = new ArrayList <String>();
		meanings.add("boy");
		meanings.add("cat");
		this.theWords.put(key, meanings);
		
		key = "ton";
		meanings = new ArrayList <String>();
		meanings.add("heavyweight");
		meanings.add("weight");
		this.theWords.put(key, meanings);
		
		key = "tonga";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "tonight";
		meanings = new ArrayList <String>();
		meanings.add("this evening");
		this.theWords.put(key, meanings);
		
		key = "too";
		meanings = new ArrayList <String>();
		meanings.add("overly");
		this.theWords.put(key, meanings);
				
		key = "toomuch"; // too much
		meanings = new ArrayList <String>();
		meanings.add("over the top");
		this.theWords.put(key, meanings);
		
		key = "top";
		meanings = new ArrayList <String>();
		meanings.add("summit");
		this.theWords.put(key, meanings);
		
		key = "topside";
		meanings = new ArrayList <String>();
		meanings.add("cut of beef");
		this.theWords.put(key, meanings);
		
		key = "tor";
		meanings = new ArrayList <String>();
		meanings.add("hill");
		meanings.add("mountain");
		this.theWords.put(key, meanings);
		
		key = "torch";
		meanings = new ArrayList <String>();
		meanings.add("light");
		this.theWords.put(key, meanings);
		
		key = "tore";
		meanings = new ArrayList <String>();
		meanings.add("ripped");
		this.theWords.put(key, meanings);
		
		key = "tories";
		meanings = new ArrayList <String>();
		meanings.add("politicians");
		this.theWords.put(key, meanings);
		
		
		key = "tornado";
		meanings = new ArrayList <String>();
		meanings.add("storm");
		this.theWords.put(key, meanings);
		
		key = "torrent";
		meanings = new ArrayList <String>();
		meanings.add("rushing stream");
		this.theWords.put(key, meanings);
		
		key = "tortilla";
		meanings = new ArrayList <String>();
		meanings.add("Mexican dish");
		this.theWords.put(key, meanings);
		
		key = "tory";
		meanings = new ArrayList <String>();
		meanings.add("conservative");
		meanings.add("politician");
		this.theWords.put(key, meanings);
		
		key = "tosca";
		meanings = new ArrayList <String>();
		meanings.add("one of Puccini's operas");
		this.theWords.put(key, meanings);
		
		key = "toss";
		meanings = new ArrayList <String>();
		meanings.add("flip a coin");
		this.theWords.put(key, meanings);
		
		key = "tot";
		meanings = new ArrayList <String>();
		meanings.add("child");
		meanings.add("little drink");
		meanings.add("small child");
		meanings.add("small drink");
		this.theWords.put(key, meanings);
		
		key = "total";
		meanings = new ArrayList <String>();
		meanings.add("whole");
		this.theWords.put(key, meanings);
		
		key = "totally";
		meanings = new ArrayList <String>();
		meanings.add("without reservation");
		this.theWords.put(key, meanings);
		
		key = "toted";
		meanings = new ArrayList <String>();
		meanings.add("carried");
		this.theWords.put(key, meanings);
		
		key = "totter";
		meanings = new ArrayList <String>();
		meanings.add("rock");
		this.theWords.put(key, meanings);
		
		key = "touch";
		meanings = new ArrayList <String>();
		meanings.add("adjoin");
		meanings.add("nudge");
		meanings.add("poke");
		meanings.add("press");
		meanings.add("prod");
		meanings.add("tap");
		this.theWords.put(key, meanings);
		
		key = "tour";
		meanings = new ArrayList <String>();
		meanings.add("bike race");
		meanings.add("excursion");
		meanings.add("trip");
		this.theWords.put(key, meanings);
		
		key = "tourism";
		meanings = new ArrayList <String>();
		meanings.add("traveling for recreation");
		this.theWords.put(key, meanings);
		
		key = "tower";
		meanings = new ArrayList <String>();
		meanings.add("tall building");
		this.theWords.put(key, meanings);
		
		key = "toy";
		meanings = new ArrayList <String>();
		meanings.add("plaything");
		this.theWords.put(key, meanings);
		
		key = "trace";
		meanings = new ArrayList <String>();
		meanings.add("find");
		this.theWords.put(key, meanings);
		
		key = "traceable";
		meanings = new ArrayList <String>();
		meanings.add("can be found");
		this.theWords.put(key, meanings);
		
		key = "tract";
		meanings = new ArrayList <String>();
		meanings.add("pamphlet");
		this.theWords.put(key, meanings);
		
		key = "tractor";
		meanings = new ArrayList <String>();
		meanings.add("farm vehicle");
		this.theWords.put(key, meanings);
		
		
		key = "trade";
		meanings = new ArrayList <String>();
		meanings.add("deal");
		this.theWords.put(key, meanings);
		
		key = "traders";
		meanings = new ArrayList <String>();
		meanings.add("vendors");
		this.theWords.put(key, meanings);
		
		key = "trail";
		meanings = new ArrayList <String>();
		meanings.add("sequence");
		this.theWords.put(key, meanings);
				
		key = "train";
		meanings = new ArrayList <String>();
		meanings.add("means of transport");
		meanings.add("part of a dress");
		meanings.add("an orderly succession of related events");
		meanings.add("coach");
		meanings.add("prepare physically");
		meanings.add("focus");
		meanings.add("aim");
		this.theWords.put(key, meanings);
				
		key = "trainee";
		meanings = new ArrayList <String>();
		meanings.add("apprentice");
		meanings.add("new recruit");
		this.theWords.put(key, meanings);
		
		key = "trainees";
		meanings = new ArrayList <String>();
		meanings.add("new recruits");
		this.theWords.put(key, meanings);
		
		key = "trains";
		meanings = new ArrayList <String>();
		meanings.add("coaches");
		meanings.add("prepares physically");
		meanings.add("aims");
		this.theWords.put(key, meanings);
		
		key = "trained";
		meanings = new ArrayList <String>();
		meanings.add("prepared for physically");
		this.theWords.put(key, meanings);
		
		key = "trainer";
		meanings = new ArrayList <String>();
		meanings.add("coach");
		meanings.add("sports shoe");
		this.theWords.put(key, meanings);
		
		key = "tram";
		meanings = new ArrayList <String>();
		meanings.add("form of transport");
		meanings.add("transport");
		meanings.add("vehicle");
		this.theWords.put(key, meanings);
		
		key = "tramline";
		meanings = new ArrayList <String>();
		meanings.add("inflexible principle");
		this.theWords.put(key, meanings);
		
		key = "tramp";
		meanings = new ArrayList <String>();
		meanings.add("hobo");
		meanings.add("long walk");
		meanings.add("vagrant");
		meanings.add("walk heavily");
		this.theWords.put(key, meanings);
		
		key = "trance";
		meanings = new ArrayList <String>();
		meanings.add("dream");
		this.theWords.put(key, meanings);
		
		key = "transitory";
		meanings = new ArrayList <String>();
		meanings.add("passing");
		this.theWords.put(key, meanings);
		
		key = "translate";
		meanings = new ArrayList <String>();
		meanings.add("change position of");
		meanings.add("convert");
		meanings.add("decipher");
		meanings.add("interpret");
		meanings.add("render");
		this.theWords.put(key, meanings);
		
		key = "traps";
		meanings = new ArrayList <String>();
		meanings.add("catches");
		this.theWords.put(key, meanings);
		
		key = "trashy";
		meanings = new ArrayList <String>();
		meanings.add("poor quality");
		meanings.add("worthless");
		this.theWords.put(key, meanings);
		
		key = "travel";
		meanings = new ArrayList <String>();
		meanings.add("journey");
		meanings.add("make a journey");
		this.theWords.put(key, meanings);
		
		key = "trawler";
		meanings = new ArrayList <String>();
		meanings.add("fishing boat");
		this.theWords.put(key, meanings);
		
		key = "tray";
		meanings = new ArrayList <String>();
		meanings.add("carrier");
		this.theWords.put(key, meanings);
				
		key = "tread";
		meanings = new ArrayList <String>();
		meanings.add("plod");
		this.theWords.put(key, meanings);
		
		key = "treadle";
		meanings = new ArrayList <String>();
		meanings.add("peddle");
		this.theWords.put(key, meanings);
		
		key = "treadmill";
		meanings = new ArrayList <String>();
		meanings.add("dreary routine");
		this.theWords.put(key, meanings);
		
		key = "treason";
		meanings = new ArrayList <String>();
		meanings.add("crime");
		meanings.add("disloyalty");
		this.theWords.put(key, meanings);
		
		key = "treasured";
		meanings = new ArrayList <String>();
		meanings.add("cherished");
		this.theWords.put(key, meanings);
		
		key = "treatment";
		meanings = new ArrayList <String>();
		meanings.add("handling");
		meanings.add("medicine");
		this.theWords.put(key, meanings);
		
		key = "tree";
		meanings = new ArrayList <String>();
		meanings.add("palm");
		meanings.add("plant");
		this.theWords.put(key, meanings);
		
		key = "trees";
		meanings = new ArrayList <String>();
		meanings.add("plants");
		this.theWords.put(key, meanings);
		
		key = "trefoil";
		meanings = new ArrayList <String>();
		meanings.add("plant");
		this.theWords.put(key, meanings);
		
		key = "tremble";
		meanings = new ArrayList <String>();
		meanings.add("quiver");
		this.theWords.put(key, meanings);
		
		key = "trench";
		meanings = new ArrayList <String>();
		meanings.add("ditch");
		this.theWords.put(key, meanings);
		
		key = "trend";
		meanings = new ArrayList <String>();
		meanings.add("fashion");
		this.theWords.put(key, meanings);
		
		key = "trent";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "tress";
		meanings = new ArrayList <String>();
		meanings.add("lock");
		meanings.add("hair");
		this.theWords.put(key, meanings);
		
		key = "trestle";
		meanings = new ArrayList <String>();
		meanings.add("table support");
		this.theWords.put(key, meanings);
		
		key = "trevi";
		meanings = new ArrayList <String>();
		meanings.add("fountain");
		this.theWords.put(key, meanings);
		
		key = "triangle";
		meanings = new ArrayList <String>();
		meanings.add("shape");
		this.theWords.put(key, meanings);
		
		key = "trial";
		meanings = new ArrayList <String>();
		meanings.add("court case");
		this.theWords.put(key, meanings);
		
		key = "tribal";
		meanings = new ArrayList <String>();
		meanings.add("clannish");
		this.theWords.put(key, meanings);
		
		key = "tribe";
		meanings = new ArrayList <String>();
		meanings.add("clan");
		this.theWords.put(key, meanings);
		
		key = "tribunal";
		meanings = new ArrayList <String>();
		meanings.add("court");
		this.theWords.put(key, meanings);
		
		key = "tried";
		meanings = new ArrayList <String>();
		meanings.add("attempted");
		this.theWords.put(key, meanings);
		
		key = "tries";
		meanings = new ArrayList <String>();
		meanings.add("attempts");
		this.theWords.put(key, meanings);
		
		key = "trill";
		meanings = new ArrayList <String>();
		meanings.add("birdsong");
		this.theWords.put(key, meanings);
		
		key = "trimester";
		meanings = new ArrayList <String>();
		meanings.add("part of academic year");
		meanings.add("three months");
		this.theWords.put(key, meanings);
		
		key = "trio";
		meanings = new ArrayList <String>();
		meanings.add("small group");
		meanings.add("threesome");
		this.theWords.put(key, meanings);
		
		key = "trios";
		meanings = new ArrayList <String>();
		meanings.add("musicians");
		meanings.add("threesomes");
		this.theWords.put(key, meanings);
		
		key = "trip";
		meanings = new ArrayList <String>();
		meanings.add("fall over");
		meanings.add("journey");
		meanings.add("voyage");
		this.theWords.put(key, meanings);
		
		key = "tripe";
		meanings = new ArrayList <String>();
		meanings.add("food");
		meanings.add("rubbish");
		this.theWords.put(key, meanings);
		
		key = "triplet";
		meanings = new ArrayList <String>();
		meanings.add("one of three");
		this.theWords.put(key, meanings);
		
		key = "tripoli";
		meanings = new ArrayList <String>();
		meanings.add("capital");
		this.theWords.put(key, meanings);
		
		key = "troopers";
		meanings = new ArrayList <String>();
		meanings.add("cavalrymen");
		this.theWords.put(key, meanings);
		
		key = "troll";
		meanings = new ArrayList <String>();
		meanings.add("abusive surfer");
		meanings.add("internet commenter");
		this.theWords.put(key, meanings);
		
		key = "troy";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "true";
		meanings = new ArrayList <String>();
		meanings.add("faithful");
		this.theWords.put(key, meanings);
	
		key = "trust";
		meanings = new ArrayList <String>();
		meanings.add("charitable organisation");
		this.theWords.put(key, meanings);
		
		key = "try";
		meanings = new ArrayList <String>();
		meanings.add("attempt");
		meanings.add("make an effort");
		meanings.add("vex");
		this.theWords.put(key, meanings);
		
		key = "tryolean";
		meanings = new ArrayList <String>();
		meanings.add("austrian");
		this.theWords.put(key, meanings);
		
		key = "tryst";
		meanings = new ArrayList <String>();
		meanings.add("meeting");
		this.theWords.put(key, meanings);
		
		key = "tsarina";
		meanings = new ArrayList <String>();
		meanings.add("russian princess");
		this.theWords.put(key, meanings);
		
		key = "tub";
		meanings = new ArrayList <String>();
		meanings.add("large plant container");
		this.theWords.put(key, meanings);
		
		key = "tug";
		meanings = new ArrayList <String>();
		meanings.add("pull");
		this.theWords.put(key, meanings);
		
		key = "tulip";
		meanings = new ArrayList <String>();
		meanings.add("bulb");
		this.theWords.put(key, meanings);
		
		key = "tum";
		meanings = new ArrayList <String>();
		meanings.add("stomach");
		this.theWords.put(key, meanings);
		
		key = "tun";
		meanings = new ArrayList <String>();
		meanings.add("cask");
		meanings.add("large cask");
		this.theWords.put(key, meanings);
				
		key = "tuna";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "tune";
		meanings = new ArrayList <String>();
		meanings.add("melody");
		meanings.add("piece of music");
		this.theWords.put(key, meanings);
		
		key = "tunic";
		meanings = new ArrayList <String>();
		meanings.add("garment");
		this.theWords.put(key, meanings);
		
		key = "turn";
		meanings = new ArrayList <String>();
		meanings.add("go");
		this.theWords.put(key, meanings);
		
		key = "tuscan";
		meanings = new ArrayList <String>();
		meanings.add("Italian");
		this.theWords.put(key, meanings);
		
		key = "tuscany";
		meanings = new ArrayList <String>();
		meanings.add("Italian region");
		this.theWords.put(key, meanings);
		
		key = "tweak";
		meanings = new ArrayList <String>();
		meanings.add("pinch");
		this.theWords.put(key, meanings);
		
		key = "twaddle";
		meanings = new ArrayList <String>();
		meanings.add("rubbish");
		this.theWords.put(key, meanings);
		
		key = "twee";
		meanings = new ArrayList <String>();
		meanings.add("cute");
		this.theWords.put(key, meanings);
		
		key = "tweed";
		meanings = new ArrayList <String>();
		meanings.add("fabric");
		this.theWords.put(key, meanings);
		
		key = "type";
		meanings = new ArrayList <String>();
		meanings.add("sort");
		meanings.add("use a keyboard");
		this.theWords.put(key, meanings);
		
		key = "tyre";
		meanings = new ArrayList <String>();
		meanings.add("cover");
		this.theWords.put(key, meanings);

	}//end loadTWords
	
	private void loadUWords() {
		String key;
		ArrayList <String> meanings;
		
		//load u's
		key = "um";
		meanings = new ArrayList <String>();
		meanings.add("hesitation");
		this.theWords.put(key, meanings);
		
		key = "umbria";
		meanings = new ArrayList <String>();
		meanings.add("somewhere in Italy");
		this.theWords.put(key, meanings);
		
		key = "una";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		this.theWords.put(key, meanings);
		
		key = "uncle";
		meanings = new ArrayList <String>();
		meanings.add("family member");
		meanings.add("relative");
		this.theWords.put(key, meanings);

		key = "unclean";
		meanings = new ArrayList <String>();
		meanings.add("impure");
		this.theWords.put(key, meanings);
		
		key = "unclear";
		meanings = new ArrayList <String>();
		meanings.add("ambiguous");
		this.theWords.put(key, meanings);
		
		key = "undid";
		meanings = new ArrayList <String>();
		meanings.add("reversed");
		this.theWords.put(key, meanings);
		
		key = "undoubted";
		meanings = new ArrayList <String>();
		meanings.add("certain");
		this.theWords.put(key, meanings);
		
		key = "unfed";
		meanings = new ArrayList <String>();
		meanings.add("nothing to eat");
		this.theWords.put(key, meanings);
		
		key = "unfortunate";
		meanings = new ArrayList <String>();
		meanings.add("luckless");
		this.theWords.put(key, meanings);
		
		key = "unicorn";
		meanings = new ArrayList <String>();
		meanings.add("fabulous creature");
		this.theWords.put(key, meanings);
		
		key = "unicycle";
		meanings = new ArrayList <String>();
		meanings.add("form of transport");
		this.theWords.put(key, meanings);
		
		key = "union";
		meanings = new ArrayList <String>();
		meanings.add("association");
		this.theWords.put(key, meanings);
		
		key = "unit";
		meanings = new ArrayList <String>();
		meanings.add("kitchen fitment");
		this.theWords.put(key, meanings);
		
		key = "unite";
		meanings = new ArrayList <String>();
		meanings.add("join");
		this.theWords.put(key, meanings);
		
		key = "universe";
		meanings = new ArrayList <String>();
		meanings.add("everything");
		this.theWords.put(key, meanings);
		
		key = "unlatch";
		meanings = new ArrayList <String>();
		meanings.add("open gate");
		this.theWords.put(key, meanings);
		
		key = "untied";
		meanings = new ArrayList <String>();
		meanings.add("freed from");
		this.theWords.put(key, meanings);
		
		key = "unveiled";
		meanings = new ArrayList <String>();
		meanings.add("exposed to public view");
		this.theWords.put(key, meanings);
		
		key = "unwell";
		meanings = new ArrayList <String>();
		meanings.add("sick");
		this.theWords.put(key, meanings);
		
		key = "up";
		meanings = new ArrayList <String>();
		meanings.add("out of bed");
		meanings.add("promoted");
		this.theWords.put(key, meanings);
		
		key = "upheaval";
		meanings = new ArrayList <String>();
		meanings.add("disturbance");
		this.theWords.put(key, meanings);
		
		key = "uplighter";
		meanings = new ArrayList <String>();
		meanings.add("lamp");
		this.theWords.put(key, meanings);
		
		key = "upper";
		meanings = new ArrayList <String>();
		meanings.add("part of shoe");
		this.theWords.put(key, meanings);
		
		key = "uppercut";
		meanings = new ArrayList <String>();
		meanings.add("punch");
		this.theWords.put(key, meanings);
		
		key = "uppermost";
		meanings = new ArrayList <String>();
		meanings.add("top");
		this.theWords.put(key, meanings);
		
		key = "upon";
		meanings = new ArrayList <String>();
		meanings.add("very soon after");
		this.theWords.put(key, meanings);
		
		key = "urals";
		meanings = new ArrayList <String>();
		meanings.add("mountain range");
		this.theWords.put(key, meanings);
		
		key = "ur";
		meanings = new ArrayList <String>();
		meanings.add("old city");
		this.theWords.put(key, meanings);
		
		key = "ure";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "urgent";
		meanings = new ArrayList <String>();
		meanings.add("requires immediate attention");
		this.theWords.put(key, meanings);
		
		key = "us";
		meanings = new ArrayList <String>();
		meanings.add("me and you");
		this.theWords.put(key, meanings);
		
		key = "used";
		meanings = new ArrayList <String>();
		meanings.add("employed");
		meanings.add("being exploited");
		this.theWords.put(key, meanings);
		
		key = "user";
		meanings = new ArrayList <String>();
		meanings.add("employer");
		this.theWords.put(key, meanings);
		
		key = "uses";
		meanings = new ArrayList <String>();
		meanings.add("employs");
		this.theWords.put(key, meanings);
		
		key = "using";
		meanings = new ArrayList <String>();
		meanings.add("being exploitative");
		this.theWords.put(key, meanings);
		
		key = "usk";
		meanings = new ArrayList <String>();
		meanings.add("river");
		this.theWords.put(key, meanings);
		
		key = "usurer";
		meanings = new ArrayList <String>();
		meanings.add("loan shark");
		this.theWords.put(key, meanings);
				
		key = "utilised";
		meanings = new ArrayList <String>();
		meanings.add("used");
		this.theWords.put(key, meanings);
		
		key = "utter";
		meanings = new ArrayList <String>();
		meanings.add("speak");
		this.theWords.put(key, meanings);
		
		key = "utterly";
		meanings = new ArrayList <String>();
		meanings.add("completely");
		this.theWords.put(key, meanings);

	}// end loadUWords
	
	private void loadVWords() {
		String key;
		ArrayList <String> meanings;
		
		//load v's
		key = "vain";
		meanings = new ArrayList <String>();
		meanings.add("conceited");
		this.theWords.put(key, meanings);
		
		key = "val";
		meanings = new ArrayList <String>();
		meanings.add("girl");
		meanings.add("little girl");
		this.theWords.put(key, meanings);
		
		key = "vale";
		meanings = new ArrayList <String>();
		meanings.add("glen");
		this.theWords.put(key, meanings);
		
		key = "valerie";
		meanings = new ArrayList <String>();
		meanings.add("girl's name");
		this.theWords.put(key, meanings);
		
		key = "valise";
		meanings = new ArrayList <String>();
		meanings.add("travelling bag");
		this.theWords.put(key, meanings);
		
		key = "van";
		meanings = new ArrayList <String>();
		meanings.add("vehicle");
		this.theWords.put(key, meanings);
		
		key = "varied";
		meanings = new ArrayList <String>();
		meanings.add("not the same");
		this.theWords.put(key, meanings);
		
		key = "varies";
		meanings = new ArrayList <String>();
		meanings.add("changes");
		this.theWords.put(key, meanings);
		
		key = "vase";
		meanings = new ArrayList <String>();
		meanings.add("container");
		this.theWords.put(key, meanings);
		
		key = "veal";
		meanings = new ArrayList <String>();
		meanings.add("meat");
		this.theWords.put(key, meanings);
		
		key = "veils";
		meanings = new ArrayList <String>();
		meanings.add("conceals");
		this.theWords.put(key, meanings);
		
		key = "veleta";
		meanings = new ArrayList <String>();
		meanings.add("dance");
		this.theWords.put(key, meanings);
		
		key = "ventilation";
		meanings = new ArrayList <String>();
		meanings.add("air circulating");
		this.theWords.put(key, meanings);
		
		key = "venue";
		meanings = new ArrayList <String>();
		meanings.add("meeting place");
		this.theWords.put(key, meanings);
		
		key = "vera";
		meanings = new ArrayList <String>();
		meanings.add("woman");
		this.theWords.put(key, meanings);
		
		key = "veracity";
		meanings = new ArrayList <String>();
		meanings.add("truth");
		this.theWords.put(key, meanings);
		
		key = "verbose";
		meanings = new ArrayList <String>();
		meanings.add("wordy");
		this.theWords.put(key, meanings);
		
		key = "vermouth";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "versatile";
		meanings = new ArrayList <String>();
		meanings.add("adaptable");
		this.theWords.put(key, meanings);
		
		key = "verse";
		meanings = new ArrayList <String>();
		meanings.add("piece of poetry");
		meanings.add("poetry");
		this.theWords.put(key, meanings);
		
		key = "versions";
		meanings = new ArrayList <String>();
		meanings.add("sorts");
		this.theWords.put(key, meanings);
		
		key = "vert";
		meanings = new ArrayList <String>();
		meanings.add("French green");
		this.theWords.put(key, meanings);
		
		key = "very";
		meanings = new ArrayList <String>();
		meanings.add("extremely");
		this.theWords.put(key, meanings);
		
		key = "vest";
		meanings = new ArrayList <String>();
		meanings.add("garment");
		this.theWords.put(key, meanings);
		
		key = "vestry";
		meanings = new ArrayList <String>();
		meanings.add("changing room");
		this.theWords.put(key, meanings);
		
		key = "vet";
		meanings = new ArrayList <String>();
		meanings.add("animal doctor");
		this.theWords.put(key, meanings);
		
		key = "veto";
		meanings = new ArrayList <String>();
		meanings.add("ban");
		meanings.add("don't allow");
		meanings.add("refuse");
		this.theWords.put(key, meanings);
		
		key = "vets";
		meanings = new ArrayList <String>();
		meanings.add("assesses");
		this.theWords.put(key, meanings);
		
		key = "vex";
		meanings = new ArrayList <String>();
		meanings.add("annoy");
		this.theWords.put(key, meanings);
		
		key = "via";
		meanings = new ArrayList <String>();
		meanings.add("by way of");
		this.theWords.put(key, meanings);
		
		key = "viable";
		meanings = new ArrayList <String>();
		meanings.add("feasible");
		this.theWords.put(key, meanings);
		
		key = "vice";
		meanings = new ArrayList <String>();
		meanings.add("bad habit");
		this.theWords.put(key, meanings);
		
		key = "victoria";
		meanings = new ArrayList <String>();
		meanings.add("queen");
		this.theWords.put(key, meanings);
		
		key = "vie";
		meanings = new ArrayList <String>();
		meanings.add("compete eagerly");
		this.theWords.put(key, meanings);
		
		key = "vienna";
		meanings = new ArrayList <String>();
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "vietnam";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "villa";
		meanings = new ArrayList <String>();
		meanings.add("house");
		this.theWords.put(key, meanings);
		
		key = "village";
		meanings = new ArrayList <String>();
		meanings.add("small town");
		this.theWords.put(key, meanings);
		
		key = "villain";
		meanings = new ArrayList <String>();
		meanings.add("rogue");
		this.theWords.put(key, meanings);
		
		key = "villainy";
		meanings = new ArrayList <String>();
		meanings.add("evil goings on");
		this.theWords.put(key, meanings);

		key = "vin";
		meanings = new ArrayList <String>();
		meanings.add("French wine");
		this.theWords.put(key, meanings);
		
		key = "vinegar";
		meanings = new ArrayList <String>();
		meanings.add("it's sour");
		this.theWords.put(key, meanings);
		
		key = "violin";
		meanings = new ArrayList <String>();
		meanings.add("instrument");
		this.theWords.put(key, meanings);
		
		key = "visa";
		meanings = new ArrayList <String>();
		meanings.add("permit");
		this.theWords.put(key, meanings);
		
		key = "visor";
		meanings = new ArrayList <String>();
		meanings.add("shield");
		this.theWords.put(key, meanings);
		
		key = "vital";
		meanings = new ArrayList <String>();
		meanings.add("essential");
		this.theWords.put(key, meanings);
				
		key = "void";
		meanings = new ArrayList <String>();
		meanings.add("not valid");
		this.theWords.put(key, meanings);
		
		key = "vote";
		meanings = new ArrayList <String>();
		meanings.add("elect");
		this.theWords.put(key, meanings);

	}// end loadVWords
	
	private void loadWWords() {
		String key;
		ArrayList <String> meanings;
		
		//load w's
		key = "wacko";
		meanings = new ArrayList <String>();
		meanings.add("crazy person");
		meanings.add("insane");
		meanings.add("mad");
		this.theWords.put(key, meanings);
		
		key = "waddle";
		meanings = new ArrayList <String>();
		meanings.add("walk unsteadily");
		this.theWords.put(key, meanings);
		
		key = "wag";
		meanings = new ArrayList <String>();
		meanings.add("joker");
		meanings.add("play truant");
		meanings.add("shake");
		this.theWords.put(key, meanings);
		
		key = "wage";
		meanings = new ArrayList <String>();
		meanings.add("money earned");
		this.theWords.put(key, meanings);
		
		key = "wager";
		meanings = new ArrayList <String>();
		meanings.add("bet");
		this.theWords.put(key, meanings);
		
		key = "wagger";
		meanings = new ArrayList <String>();
		meanings.add("dog's tail");
		this.theWords.put(key, meanings);
				
		key = "wagner";
		meanings = new ArrayList <String>();
		meanings.add("composer");
		this.theWords.put(key, meanings);
		
		key = "wales";
		meanings = new ArrayList <String>();
		meanings.add("country");
		this.theWords.put(key, meanings);
		
		key = "walk";
		meanings = new ArrayList <String>();
		meanings.add("march");
		this.theWords.put(key, meanings);
		
		key = "walking";
		meanings = new ArrayList <String>();
		meanings.add("on foot");
		this.theWords.put(key, meanings);
		
		key = "wall";
		meanings = new ArrayList <String>();
		meanings.add("barrier");
		this.theWords.put(key, meanings);
		
		key = "wallet";
		meanings = new ArrayList <String>();
		meanings.add("purse");
		this.theWords.put(key, meanings);
		
		key = "walnut";
		meanings = new ArrayList <String>();
		meanings.add("colour");
		this.theWords.put(key, meanings);
		
		key = "walrus";
		meanings = new ArrayList <String>();
		meanings.add("creature");
		this.theWords.put(key, meanings);
		
		key = "wand";
		meanings = new ArrayList <String>();
		meanings.add("stick");
		this.theWords.put(key, meanings);
		
		key = "want";
		meanings = new ArrayList <String>();
		meanings.add("desire");
		this.theWords.put(key, meanings);
		
		key = "wants";
		meanings = new ArrayList <String>();
		meanings.add("desires");
		this.theWords.put(key, meanings);
		
		key = "war";
		meanings = new ArrayList <String>();
		meanings.add("conflict");
		this.theWords.put(key, meanings);
		
		key = "wards";
		meanings = new ArrayList <String>();
		meanings.add("hospital units");
		this.theWords.put(key, meanings);
		
		key = "warranty";
		meanings = new ArrayList <String>();
		meanings.add("car guarantee");
		this.theWords.put(key, meanings);
		
		key = "warsaw";
		meanings = new ArrayList <String>();
		meanings.add("capital");
		meanings.add("city");
		this.theWords.put(key, meanings);
		
		key = "warty";
		meanings = new ArrayList <String>();
		meanings.add("like a toad");
		this.theWords.put(key, meanings);
		
		key = "was";
		meanings = new ArrayList <String>();
		meanings.add("used to be");
		this.theWords.put(key, meanings);
		
		key = "wash";
		meanings = new ArrayList <String>();
		meanings.add("have a bath");
		meanings.add("laundry");
		this.theWords.put(key, meanings);
		
		key = "wasps";
		meanings = new ArrayList <String>();
		meanings.add("insects");
		this.theWords.put(key, meanings);
				
		key = "wash";
		meanings = new ArrayList <String>();
		meanings.add("laundry");
		this.theWords.put(key, meanings);
		
		key = "wash";
		meanings = new ArrayList <String>();
		meanings.add("laundry");
		this.theWords.put(key, meanings);
		
		key = "wastes";
		meanings = new ArrayList <String>();
		meanings.add("squanders");
		this.theWords.put(key, meanings);
				
		key = "watch";
		meanings = new ArrayList <String>();
		meanings.add("keep an eye on");
		meanings.add("observe");
		meanings.add("timekeeper");
		this.theWords.put(key, meanings);
		
		key = "watchdog";
		meanings = new ArrayList <String>();
		meanings.add("monitoring organisation");
		this.theWords.put(key, meanings);
		
		key = "way";
		meanings = new ArrayList <String>();
		meanings.add("route");
		meanings.add("method");
		meanings.add("mode");
		meanings.add("habitual manner");
		this.theWords.put(key, meanings);
		
		key = "wayout"; //way out
		meanings = new ArrayList <String>();
		meanings.add("exit");
		meanings.add("extremelly unconventional");
		meanings.add("far from correct");
		this.theWords.put(key, meanings);
		
		key = "ways";
		meanings = new ArrayList <String>();
		meanings.add("routes");
		meanings.add("methods");
		meanings.add("modes");
		meanings.add("habitual manners");
		this.theWords.put(key, meanings);
		
		key = "we";
		meanings = new ArrayList <String>();
		meanings.add("us");
		meanings.add("you and I");
		this.theWords.put(key, meanings);
		
		key = "weak";
		meanings = new ArrayList <String>();
		meanings.add("frail");
		this.theWords.put(key, meanings);
		
		key = "weapon";
		meanings = new ArrayList <String>();
		meanings.add("harmful device");
		meanings.add("gun");
		this.theWords.put(key, meanings);
		
		key = "wear";
		meanings = new ArrayList <String>();
		meanings.add("sport");
		this.theWords.put(key, meanings);
		
		key = "weaver";
		meanings = new ArrayList <String>();
		meanings.add("craftsman");
		this.theWords.put(key, meanings);
		
		key = "web";
		meanings = new ArrayList <String>();
		meanings.add("network");
		this.theWords.put(key, meanings);
		
		key = "wed";
		meanings = new ArrayList <String>();
		meanings.add("married");
		this.theWords.put(key, meanings);
		
		key = "wee";
		meanings = new ArrayList <String>();
		meanings.add("little");
		meanings.add("small");
		meanings.add("very small");
		this.theWords.put(key, meanings);
		
		key = "weed";
		meanings = new ArrayList <String>();
		meanings.add("wimp");
		this.theWords.put(key, meanings);
		
		key = "well";
		meanings = new ArrayList <String>();
		meanings.add("healthy");
		meanings.add("source of water");
		this.theWords.put(key, meanings);
		
		key = "wells";
		meanings = new ArrayList <String>();
		meanings.add("science fiction writer");
		this.theWords.put(key, meanings);
		
		key = "welsh";
		meanings = new ArrayList <String>();
		meanings.add("from part of Britain");
		this.theWords.put(key, meanings);
		
		key = "weeping";
		meanings = new ArrayList <String>();
		meanings.add("crying");
		this.theWords.put(key, meanings);
		
		key = "weevil";
		meanings = new ArrayList <String>();
		meanings.add("beetle");
		this.theWords.put(key, meanings);
		
		key = "wheel";
		meanings = new ArrayList <String>();
		meanings.add("very early invention");
		this.theWords.put(key, meanings);
		
		key = "white";
		meanings = new ArrayList <String>();
		meanings.add("colour");
		this.theWords.put(key, meanings);
		
		key = "whiting";
		meanings = new ArrayList <String>();
		meanings.add("fish");
		this.theWords.put(key, meanings);
		
		key = "whole";
		meanings = new ArrayList <String>();
		meanings.add("complete");
		this.theWords.put(key, meanings);
		
		key = "wired";
		meanings = new ArrayList <String>();
		meanings.add("edgy");
		this.theWords.put(key, meanings);
		
		key = "wish";
		meanings = new ArrayList <String>();
		meanings.add("desire");
		this.theWords.put(key, meanings);
		
		key = "with";
		meanings = new ArrayList <String>();
		meanings.add("accompanying");
		this.theWords.put(key, meanings);
		
		key = "wild";
		meanings = new ArrayList <String>();
		meanings.add("not domesticated");
		meanings.add("raving");
		this.theWords.put(key, meanings);
		
		key = "wilde";
		meanings = new ArrayList <String>();
		meanings.add("playwright");
		this.theWords.put(key, meanings);
		
		key = "wilderness";
		meanings = new ArrayList <String>();
		meanings.add("badlands");
		this.theWords.put(key, meanings);
		
		key = "will";
		meanings = new ArrayList <String>();
		meanings.add("resolve");
		this.theWords.put(key, meanings);
		
		key = "win";
		meanings = new ArrayList <String>();
		meanings.add("success");
		meanings.add("victory");
		this.theWords.put(key, meanings);
		
		key = "windfall";
		meanings = new ArrayList <String>();
		meanings.add("a bit of good fortune");
		this.theWords.put(key, meanings);
		
		key = "wine";
		meanings = new ArrayList <String>();
		meanings.add("drink");
		this.theWords.put(key, meanings);
		
		key = "wing";
		meanings = new ArrayList <String>();
		meanings.add("limb");
		this.theWords.put(key, meanings);
		
		key = "winter";
		meanings = new ArrayList <String>();
		meanings.add("season");
		this.theWords.put(key, meanings);
		
		key = "wintry";
		meanings = new ArrayList <String>();
		meanings.add("cold and frosty");
		this.theWords.put(key, meanings);
		
		key = "witch";
		meanings = new ArrayList <String>();
		meanings.add("sorceress");
		this.theWords.put(key, meanings);
		
		key = "women";
		meanings = new ArrayList <String>();
		meanings.add("females");
		meanings.add("ladies");
		this.theWords.put(key, meanings);
		
		key = "won";
		meanings = new ArrayList <String>();
		meanings.add("having been victorious");
		this.theWords.put(key, meanings);
		
		key = "wonderful";
		meanings = new ArrayList <String>();
		meanings.add("marvellous");
		this.theWords.put(key, meanings);
		
		key = "wool";
		meanings = new ArrayList <String>();
		meanings.add("material");
		this.theWords.put(key, meanings);
		
		key = "woollies";
		meanings = new ArrayList <String>();
		meanings.add("warm clothes");
		this.theWords.put(key, meanings);
		
		key = "worth";
		meanings = new ArrayList <String>();
		meanings.add("value");
		this.theWords.put(key, meanings);
		
		key = "writ";
		meanings = new ArrayList <String>();
		meanings.add("court order");
		meanings.add("legal document");
		this.theWords.put(key, meanings);
			
		key = "write";
		meanings = new ArrayList <String>();
		meanings.add("correspond");
		this.theWords.put(key, meanings);
		
		key = "writhe";
		meanings = new ArrayList <String>();
		meanings.add("squirm");
		this.theWords.put(key, meanings);
		
		key = "written";
		meanings = new ArrayList <String>();
		meanings.add("composed");
		this.theWords.put(key, meanings);
		
		key = "wrote";
		meanings = new ArrayList <String>();
		meanings.add("scribed");
		this.theWords.put(key, meanings);

	}// end loadWWords
	
	private void loadXWords() {
		String key;
		ArrayList <String> meanings;
		
		
	}
	
	private void loadYWords() {
		String key;
		ArrayList <String> meanings;
		
		//load y's
		key = "yard";
		meanings = new ArrayList <String>();
		meanings.add("garden");
		meanings.add("three feet");
		this.theWords.put(key, meanings);
				
		key = "ye";
		meanings = new ArrayList <String>();
		meanings.add("you");
		this.theWords.put(key, meanings);
		
		key = "year";
		meanings = new ArrayList <String>();
		meanings.add("period");
		meanings.add("time");
		this.theWords.put(key, meanings);
		
		key = "yearn";
		meanings = new ArrayList <String>();
		meanings.add("long");
		this.theWords.put(key, meanings);
		
		key = "yearned";
		meanings = new ArrayList <String>();
		meanings.add("longed");
		this.theWords.put(key, meanings);
		
		key = "yearns";
		meanings = new ArrayList <String>();
		meanings.add("longs for");
		this.theWords.put(key, meanings);
		
		key = "yeast";
		meanings = new ArrayList <String>();
		meanings.add("raising agent");
		this.theWords.put(key, meanings);
		
		key = "yes";
		meanings = new ArrayList <String>();
		meanings.add("ok");
		this.theWords.put(key, meanings);
		
		key = "yet";
		meanings = new ArrayList <String>();
		meanings.add("nevertheless");
		meanings.add("thus far");
		this.theWords.put(key, meanings);
		
		key = "yeti";
		meanings = new ArrayList <String>();
		meanings.add("bigfoot");
		this.theWords.put(key, meanings);
		
		key = "yield";
		meanings = new ArrayList <String>();
		meanings.add("harvest");
		this.theWords.put(key, meanings);
		
		key = "yokel";
		meanings = new ArrayList <String>();
		meanings.add("bumpkin");
		this.theWords.put(key, meanings);
		
		key = "yon";
		meanings = new ArrayList <String>();
		meanings.add("over there");
		this.theWords.put(key, meanings);
		
		key = "yonder";
		meanings = new ArrayList <String>();
		meanings.add("over there");
		this.theWords.put(key, meanings);
		
		key = "yorkshire";
		meanings = new ArrayList <String>();
		meanings.add("county");
		this.theWords.put(key, meanings);
		
		key = "you";
		meanings = new ArrayList <String>();
		meanings.add("thee");
		this.theWords.put(key, meanings);
		
	}// end loadYWords
	
	private void loadZWords() {
		String key;
		ArrayList <String> meanings;
		
		//load z's
		key = "zealot";
		meanings = new ArrayList <String>();
		meanings.add("fanatic");
		this.theWords.put(key, meanings);
		
		key = "zen";
		meanings = new ArrayList <String>();
		meanings.add("Buddhist faith");
		this.theWords.put(key, meanings);
		
		key = "zero";
		meanings = new ArrayList <String>();
		meanings.add("nothing");
		this.theWords.put(key, meanings);
		
		key = "zeus";
		meanings = new ArrayList <String>();
		meanings.add("Greek god");
		this.theWords.put(key, meanings);
	}
	
}//end class

