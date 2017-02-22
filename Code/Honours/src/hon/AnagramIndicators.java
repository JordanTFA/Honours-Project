package hon;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

/**
 * AnagramIndicators
 * @author      John Owens <j.owens@napier.ac.uk>
 * @version     1.1                 
 * @since       2014-02-24          
 */
public class AnagramIndicators {

	private static ArrayList <String> indicators;
	static TreeSet<RankedClue> allClues = new TreeSet<RankedClue>();
	
	public AnagramIndicators() {
		indicators = new ArrayList <String> ();
		loadWords();
	}
	
	public ArrayList <String> getAllIndicators() {
		return indicators;
	}
	
	public String getOneRandomIndicator() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(indicators.size());
		return indicators.get(randomInt);
	}
	
	public void printAnagramIndicators() {
		String output = "";
		
		for(String tempIndicator : indicators) {
			output = output + tempIndicator +"\n";
		}
		System.out.println(output);
		
	}
	
	public static void appendIndicator(String clue){
		
		String clueWithIndicator;
		double prob;
		for(String ti : indicators){
			
			clueWithIndicator = clue + " " + ti;
			prob = Sngram.calcProbability(clueWithIndicator);
			RankedClue newClue = new RankedClue(prob,clueWithIndicator);
			
			allClues.add(newClue);
		}
		
		for(RankedClue rc : allClues){
			System.out.println(rc.getProbability() + "\t" + rc.getText());
		}
		
		
	}
	
	public String toString() {
		String output = "ANAGRAM INDICATORS \n\n";
		
		for(String tempIndicator : indicators) {
			output = output + tempIndicator +"\n";
		}
		return output;
		
	}

	private void loadWords() {
		indicators.add("abandoned");
		indicators.add("abnormal");
		indicators.add("about");
		indicators.add("absurd");
		indicators.add("adapt");
		indicators.add("adapted");
		indicators.add("addled");
		indicators.add("adjust");
		indicators.add("adjustable");
		indicators.add("adjusted");
		indicators.add("adjustment");
		indicators.add("adrift");
		indicators.add("afresh");
		indicators.add("after a fashion");
		indicators.add("after refreshment");
		indicators.add("agitated");
		indicators.add("agitates");
		indicators.add("alarmingly");
		indicators.add("all at sea");
		indicators.add("all over");
		indicators.add("alter");
		indicators.add("alteration");
		indicators.add("altered");
		indicators.add("altering");
		indicators.add("alternative");
		indicators.add("amendment");
		indicators.add("amiss");
		indicators.add("anew");
		indicators.add("another");
		indicators.add("another way");
		indicators.add("anyhow");
		indicators.add("apart");
		indicators.add("around");
		indicators.add("arranged");
		indicators.add("arrangement");
		indicators.add("arranging");
		indicators.add("askew");
		indicators.add("assemble");
		indicators.add("assembled");
		indicators.add("assembling");
		indicators.add("assembly");
		indicators.add("assorted");
		indicators.add("assortment");
		indicators.add("astray");
		indicators.add("at odds");
		indicators.add("at sea");
		indicators.add("at sixes and sevens");
		indicators.add("away");
		indicators.add("awful");
		indicators.add("awfully");
		indicators.add("awkward");
		indicators.add("awkwardly");
		indicators.add("awry");
		indicators.add("bad");
		indicators.add("badly");
		indicators.add("bananas");
		indicators.add("bats");
		indicators.add("battered");
		indicators.add("batty");
		indicators.add("beaten");
		indicators.add("beaten up");
		indicators.add("become");
		indicators.add("becomes");
		indicators.add("becoming");
		indicators.add("becoming upset");
		indicators.add("bend");
		indicators.add("bends");
		indicators.add("bent");
		indicators.add("beserk");
		indicators.add("bizarre");
		indicators.add("blend");
		indicators.add("blended");
		indicators.add("boil");
		indicators.add("boiled");
		indicators.add("boiling");
		indicators.add("break");
		indicators.add("breaks");
		indicators.add("breaking");
		indicators.add("brew");
		indicators.add("brewed");
		indicators.add("brewing");
		indicators.add("broadcast");
		indicators.add("broke");
		indicators.add("broken");
		indicators.add("build");
		indicators.add("building");
		indicators.add("built");
		indicators.add("bumpy");
		indicators.add("burst");
		indicators.add("bursting");
		indicators.add("busy");
		indicators.add("by arrangement");
		indicators.add("by mistake");
		indicators.add("can be");
		indicators.add("careless");
		indicators.add("carelessly");
		indicators.add("cast");
		indicators.add("casting");
		indicators.add("cavort");
		indicators.add("cavorting");
		indicators.add("change");
		indicators.add("change of");
		indicators.add("changed");
		indicators.add("changes");
		indicators.add("changing");
		indicators.add("chaos");
		indicators.add("chaotic");
		indicators.add("characters");
		indicators.add("chop");
		indicators.add("chopped");
		indicators.add("chopped up");
		indicators.add("circulating");
		indicators.add("cocktail");
		indicators.add("collapse");
		indicators.add("complicate");
		indicators.add("complicated");
		indicators.add("components");
		indicators.add("compose");
		indicators.add("composed");
		indicators.add("composer");
		indicators.add("composing");
		indicators.add("compound");
		indicators.add("comprise");
		indicators.add("concealed");
		indicators.add("concealing");
		indicators.add("conceals");
		indicators.add("confuse");
		indicators.add("confused");
		indicators.add("confusing");
		indicators.add("constituents");
		indicators.add("construct");
		indicators.add("construction");
		indicators.add("controversial");
		indicators.add("conversion");
		indicators.add("convert");
		indicators.add("converted");
		indicators.add("convertible");
		indicators.add("cook");
		indicators.add("cooked");
		indicators.add("cooking");
		indicators.add("correct");
		indicators.add("corrected");
		indicators.add("corrupt");
		indicators.add("corrupted");
		indicators.add("could be");
		indicators.add("crack");
		indicators.add("cracked");
		indicators.add("crackers");
		indicators.add("cracking");
		indicators.add("crash");
		indicators.add("crashed");
		indicators.add("crashing");
		indicators.add("crazy");
		indicators.add("crooked");
		indicators.add("criminal");
		indicators.add("crude");
		indicators.add("cruelly");
		indicators.add("cuckoo");
		indicators.add("cultivated");
		indicators.add("cultivating");
		indicators.add("cunningly");
		indicators.add("curious");
		indicators.add("curiously");
		indicators.add("damage");
		indicators.add("damaged");
		indicators.add("damaging");
		indicators.add("dance");
		indicators.add("dancing");
		indicators.add("dealt");
		indicators.add("deciphered");
		indicators.add("deform");
		indicators.add("deformed");
		indicators.add("demolished");
		indicators.add("deploy");
		indicators.add("deployed");
		indicators.add("derelict");
		indicators.add("derived from");
		indicators.add("design");
		indicators.add("designed");
		indicators.add("destroyed");
		indicators.add("destruction");
		indicators.add("devastated");
		indicators.add("develop");
		indicators.add("developed");
		indicators.add("developing");
		indicators.add("development of");
		indicators.add("deviation");
		indicators.add("devious");
		indicators.add("dicky");
		indicators.add("different");
		indicators.add("differently");
		indicators.add("disarray");
		indicators.add("disaster");
		indicators.add("disastrous");
		indicators.add("disfigure");
		indicators.add("disfigured");
		indicators.add("disguise");
		indicators.add("disguised");
		indicators.add("dishevelled");
		indicators.add("dislocated");
		indicators.add("dislodged");
		indicators.add("dismantled");
		indicators.add("disorder");
		indicators.add("disordered");
		indicators.add("disorderly");
		indicators.add("displaced");
		indicators.add("dispersed");
		indicators.add("disposed");
		indicators.add("disruption");
		indicators.add("disseminated");
		indicators.add("distressed");
		indicators.add("distributed");
		indicators.add("disturb");
		indicators.add("disturbs");
		indicators.add("disturbed");
		indicators.add("disturbing");
		indicators.add("diversified");
		indicators.add("diverted");
		indicators.add("dizzy");
		indicators.add("doctor");
		indicators.add("doctored");
		indicators.add("dodgy");
		indicators.add("dotty");
		indicators.add("dreadful");
		indicators.add("dreadfully");
		indicators.add("dressed");
		indicators.add("drunk");
		indicators.add("dud");
		indicators.add("duff");
		indicators.add("eccentric");
		indicators.add("edit");
		indicators.add("edited");
		indicators.add("engendering");
		indicators.add("engineered");
		indicators.add("ensemble");
		indicators.add("entangled");
		indicators.add("erected");
		indicators.add("erratic");
		indicators.add("erupting");
		indicators.add("essentials");
		indicators.add("excited");
		indicators.add("exercises");
		indicators.add("exotic");
		indicators.add("explode");
		indicators.add("exploded");
		indicators.add("exploding");
		indicators.add("explosion");
		indicators.add("extraordinary");
		indicators.add("fabricated");
		indicators.add("failing");
		indicators.add("false");
		indicators.add("fanciful");
		indicators.add("fancy");
		indicators.add("fantastic");
		indicators.add("fashion");
		indicators.add("fashioned");
		indicators.add("fashoning");
		indicators.add("faulty");
		indicators.add("fermented");
		indicators.add("feverishly");
		indicators.add("fiddle");
		indicators.add("fiddled");
		indicators.add("figthing");
		indicators.add("find");
		indicators.add("fixed");
		indicators.add("flapping");
		indicators.add("fluid");
		indicators.add("flurried");
		indicators.add("foolish");
		indicators.add("foolishly");
		indicators.add("for a change");
		indicators.add("for distribution");//??
		indicators.add("forced");
		indicators.add("forge");
		indicators.add("forged");
		indicators.add("form");
		indicators.add("form of");
		indicators.add("fractured");
		indicators.add("fragmented");
		indicators.add("fragments");
		indicators.add("frantic");
		indicators.add("franticly");
		indicators.add("free");
		indicators.add("freely");
		indicators.add("fresh");
		indicators.add("freshly");
		indicators.add("frolicking");
		//indicators.add("from");
		indicators.add("fudge");
		indicators.add("funny");
		indicators.add("gamboling");
		indicators.add("garbled");
		indicators.add("gathered");
		indicators.add("generating");
		indicators.add("gets");
		indicators.add("gets broken");
		indicators.add("gets treatment");
		indicators.add("getting involved");
		indicators.add("getting upset");
		indicators.add("gibberish");
		indicators.add("grotesque");
		indicators.add("ground");
		indicators.add("haphazard");
		indicators.add("Harry");
		indicators.add("has been ordered");
		indicators.add("hash");
		indicators.add("haywire");
		indicators.add("hectic");
		//indicators.add("hide");
		//indicators.add("hiding");
		indicators.add("higgeldy-piggeldy");
		indicators.add("ill");
		indicators.add("ill-disposed");
		indicators.add("ill-formed");
		indicators.add("ill-treated");
		indicators.add("improper");
		indicators.add("improperley");
		indicators.add("in a ferment");
		indicators.add("in a jumble");
		indicators.add("in a mess");
		indicators.add("in a storm");
		indicators.add("in a whirl");
		indicators.add("in chaos");
		indicators.add("incorrect");
		indicators.add("incorrectly");
		indicators.add("in disarray");
		indicators.add("in disguise");
		indicators.add("in distress");
		indicators.add("in error");
		indicators.add("injured");
		indicators.add("in trouble");
		indicators.add("ingredients");
		indicators.add("in order");
		//indicators.add("in order to get");
		indicators.add("in ruins");
		indicators.add("interfered with");
		indicators.add("in trouble");
		indicators.add("involve");
		indicators.add("involved");
		indicators.add("is changing");//??
		indicators.add("is complicated");//??
		indicators.add("is in distress");//??
		indicators.add("is ruined");//??
		indicators.add("is transplanted");//??
		indicators.add("irregular");
		indicators.add("jittery");
		indicators.add("joining");
		indicators.add("juggled");
		indicators.add("jumbled");
		indicators.add("jumping");
		indicators.add("kind of");
		indicators.add("kinky");
		indicators.add("knotted");
		indicators.add("lawless");
		indicators.add("letters");
		indicators.add("liquid");
		indicators.add("loose");
		indicators.add("loosely");
		indicators.add("lunatic");
		indicators.add("mad");
		indicators.add("made");
		indicators.add("made up");
		indicators.add("madly");
		indicators.add("make");
		indicators.add("makes");
		indicators.add("make up");
		indicators.add("making");
		indicators.add("malformed");
		indicators.add("malfunction");
		indicators.add("maltreated");
		indicators.add("managed");
		indicators.add("mangled");
		indicators.add("manoeuvre");
		indicators.add("manufactured");
		indicators.add("mash");
		indicators.add("massaged");
		indicators.add("maybe");
		indicators.add("may be");
		indicators.add("may be ordered");
		indicators.add("meandering");
		indicators.add("medley");
		indicators.add("melee");
		indicators.add("mess");
		indicators.add("messy");
		indicators.add("might be");
		indicators.add("minced");
		indicators.add("mishandle");
		indicators.add("misled");
		indicators.add("misplaced");
		indicators.add("misshapen");
		indicators.add("mistake");
		indicators.add("mistaken");
		indicators.add("misuse");
		indicators.add("mix");
		indicators.add("mixed");
		indicators.add("mixture");
		indicators.add("mix up");
		indicators.add("mobile");
		indicators.add("modelled");
		indicators.add("modified");
		indicators.add("move");
		indicators.add("movement");
		indicators.add("moving");
		indicators.add("muddy");
		indicators.add("muddle");
		indicators.add("muddled");
		indicators.add("mutilated");
		indicators.add("mutinous");
		indicators.add("mysterious");
		indicators.add("nasty");
		indicators.add("naughty");
		indicators.add("new");
		indicators.add("new form");
		indicators.add("newly");
		indicators.add("new setting");
		indicators.add("not in order");
		indicators.add("not properly");
		indicators.add("not right");
		indicators.add("not straight");
		indicators.add("not working");
		indicators.add("novel");
		indicators.add("nuts");
		indicators.add("nutty");
		indicators.add("odd");
		indicators.add("oddly");
		indicators.add("off");
		indicators.add("off track");
		indicators.add("order");
		indicators.add("ordered");
		indicators.add("orderley");
		indicators.add("organise");
		indicators.add("organised");
		indicators.add("original");
		indicators.add("originally");
		indicators.add("otherwise");
		indicators.add("out");
		indicators.add("out of");
		indicators.add("out of joint");
		indicators.add("out of order");
		indicators.add("out of place");
		indicators.add("out of sorts");
		indicators.add("over");
		indicators.add("pants");
		indicators.add("parlous");
		indicators.add("peculiar");
		indicators.add("peculiarly");
		indicators.add("perhaps");
		indicators.add("perverse");
		indicators.add("perversely");
		indicators.add("pervert");
		indicators.add("perverted");
		indicators.add("phoney");
		indicators.add("pie");
		indicators.add("pitching");
		indicators.add("placed");
		indicators.add("plastic");
		indicators.add("played");
		indicators.add("playing");
		indicators.add("playing with");//??
		indicators.add("playfully");
		indicators.add("poor");
		indicators.add("poorly");
		indicators.add("possible");
		indicators.add("possibly");
		indicators.add("potential");
		indicators.add("potentially");
		indicators.add("prepare");
		indicators.add("prepared");
		indicators.add("problem");
		indicators.add("processing");
		indicators.add("produce");
		indicators.add("producing");
		indicators.add("pseudo");
		indicators.add("put out");
		indicators.add("put right");
		indicators.add("put straight");
		indicators.add("queer");
		indicators.add("queerly");
		indicators.add("questionable");
		indicators.add("quirky");
		indicators.add("ragged");
		indicators.add("rambling");
		indicators.add("random");
		indicators.add("randomly");
		indicators.add("rare");
		indicators.add("re-arranged");
		indicators.add("rebuilt");
		indicators.add("recollected");
		indicators.add("reconstructed");
		indicators.add("rectified");
		indicators.add("reeling");
		indicators.add("reels");
		indicators.add("refined");
		indicators.add("reform");
		indicators.add("reformed");
		indicators.add("refurbished");
		indicators.add("regulated");
		indicators.add("repair");
		indicators.add("repaired");
		indicators.add("replaced");
		indicators.add("resetting");
		indicators.add("reshuffle");
		indicators.add("resort");
		indicators.add("review");
		indicators.add("revert");//??
		indicators.add("reviewed");
		indicators.add("revolt");
		indicators.add("revolting");
		indicators.add("revolutionary");
		indicators.add("rewrite");
		indicators.add("rickety");
		indicators.add("rigged");
		indicators.add("riot");
		indicators.add("rioting");
		indicators.add("riotous");
		indicators.add("roaming");
		indicators.add("roaming wild");
		indicators.add("rock");
		indicators.add("rocks");
		indicators.add("rocking");
		indicators.add("rocky");
		indicators.add("rotten");
		indicators.add("rough");
		indicators.add("roughly");
		indicators.add("round");
		indicators.add("ruin");
		indicators.add("ruined");
		indicators.add("ruined by");
		indicators.add("ruinous");
		indicators.add("rum");
		indicators.add("run");
		indicators.add("running");
		indicators.add("runs");
		indicators.add("ruptured");
		indicators.add("sad");
		indicators.add("sadly");
		indicators.add("scatter");
		indicators.add("scattered");
		indicators.add("scatty");
		indicators.add("scrambled");
		indicators.add("scrambling");
		indicators.add("set off");
		indicators.add("set out");
		indicators.add("shake");
		indicators.add("shaken");
		indicators.add("shaking");
		indicators.add("shaky");
		indicators.add("shambles");
		indicators.add("shattered");
		indicators.add("shift");
		indicators.add("shifted");
		indicators.add("shifting");
		indicators.add("shockingly");
		indicators.add("shot");
		indicators.add("shuffled");
		indicators.add("silly");
		indicators.add("skidding");
		indicators.add("sloppy");
		indicators.add("sloshed");
		indicators.add("smash");
		indicators.add("smashed");
		indicators.add("smashing");
		indicators.add("snarled");
		indicators.add("some");
		indicators.add("somehow");
		indicators.add("sort");
		indicators.add("sorted out");
		indicators.add("sort of");
		indicators.add("sort out");
		indicators.add("spill");
		indicators.add("spilled");
		indicators.add("spilt");
		indicators.add("spin");
		indicators.add("spinning");
		indicators.add("spoil");
		indicators.add("spoils");
		indicators.add("spoilt");
		indicators.add("spoiled");
		indicators.add("spread");
		indicators.add("spun");
		indicators.add("staggered");
		indicators.add("staggering");
		indicators.add("stew");
		indicators.add("straggling");
		indicators.add("strange");
		indicators.add("strangely");
		indicators.add("stray");
		indicators.add("struggling");
		indicators.add("substitute");
		indicators.add("substitutes");
		indicators.add("surprising");
		indicators.add("surprisingly");
		indicators.add("suspect");
		indicators.add("swarming");
		indicators.add("swimming");
		indicators.add("swings");
		indicators.add("swirl");
		indicators.add("swirling");
		indicators.add("swirling around");
		indicators.add("switch");
		indicators.add("switched");
		indicators.add("switched into");//??
		indicators.add("taken round");
		indicators.add("tangled");
		indicators.add("tattered");
		indicators.add("terrible");
		indicators.add("terribly");
		indicators.add("that's different");
		indicators.add("that could be");
		indicators.add("tidied");
		indicators.add("tipsy");
		indicators.add("to confuse");
		indicators.add("torn");
		indicators.add("tortuous");
		indicators.add("torturing");
		//indicators.add("train");
		indicators.add("trained");
		indicators.add("training");
		indicators.add("transform");
		indicators.add("transformed");
		indicators.add("translate");
		indicators.add("translated");
		indicators.add("translation");
		indicators.add("transplanted");
		indicators.add("transported");
		indicators.add("transporting");
		indicators.add("travel");
		indicators.add("travelling");
		indicators.add("treat");
		indicators.add("treated");
		indicators.add("treatment");
		indicators.add("tricky");
		indicators.add("trim");
		indicators.add("trip");
		indicators.add("trouble");
		indicators.add("troubled");
		indicators.add("tumbled");
		indicators.add("tumbling");
		indicators.add("turn");
		indicators.add("turned");
		indicators.add("turning");
		indicators.add("turns");
		indicators.add("twist");
		indicators.add("twisted");
		indicators.add("turbulent");
		indicators.add("twisted");
		indicators.add("undone");
		indicators.add("uneasily");
		indicators.add("unexpectedly");
		indicators.add("unlikely");
		indicators.add("unnatural");
		indicators.add("unravelling");
		indicators.add("unrestrained");
		indicators.add("unruly");
		indicators.add("unsettled");
		indicators.add("unsteady");
		indicators.add("untidy");
		indicators.add("unusual");
		indicators.add("unusually");
		indicators.add("upheaval");
		indicators.add("upset");
		indicators.add("used");
		indicators.add("vague");
		indicators.add("vaguely");
		indicators.add("vandalised");
		indicators.add("variance");
		indicators.add("variation");
		indicators.add("varied");
		indicators.add("variety");
		indicators.add("various");
		indicators.add("variously");
		indicators.add("version");
		indicators.add("volatile");
		indicators.add("wander");
		indicators.add("wandering");
		indicators.add("warped");
		indicators.add("wasted");
		indicators.add("waving");
		indicators.add("wayward");
		indicators.add("weave");
		indicators.add("weaving");
		indicators.add("weird");
		indicators.add("went off");
		indicators.add("wild");
		indicators.add("wilder");
		indicators.add("wildly");
		indicators.add("with arrangement");
		indicators.add("wobbles");
		indicators.add("worked");
		indicators.add("working");
		indicators.add("worried");
		indicators.add("woven");
		indicators.add("wrecked");
		indicators.add("wrecks");
		indicators.add("writhing");
		indicators.add("wrong");
		indicators.add("wrongly");
		indicators.add("wounded");
		
	}
	
	
}