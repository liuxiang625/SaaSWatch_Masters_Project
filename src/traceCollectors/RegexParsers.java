package traceCollectors;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParsers {
	public static Matcher lsofParser (String s){
		
	    Pattern pattern = Pattern.compile("(\\S*)\\s([0-9]{0,5})\\s(\\S*)\\s(\\S*:[0-9]{4,5})->(.*):(\\S*)\\s?(\\(\\S*\\))");//(\\S*)\\s([0-9]{0,5})\\s(\\S*)\\s(\\S*:[0-9]{4,5})->(.*):(.*)\\s?(\\([A-Z]*\\))
	    Matcher matcher = pattern.matcher(s);
		return matcher; 
	 }
	public static Matcher ngrepParser (String s){
		
	    Pattern pattern = Pattern.compile("(\\S*)\\s([0-9]{0,5})\\s(\\S*)\\s(\\S*:[0-9]{4,5})->(.*):(.*)\\s?(\\([A-Z]*\\))");
	    Matcher matcher = pattern.matcher(s);
		return matcher; 
	 }
}
