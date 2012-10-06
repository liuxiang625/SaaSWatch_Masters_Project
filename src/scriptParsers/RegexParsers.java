package scriptParsers;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParsers {
	public static Matcher test (String s){
		
	    Pattern pattern = Pattern.compile("(\\S*)\\s([0-9]{0,5})\\s(\\S*)\\s(\\S*:[0-9]{4,5})->(.*):(.*)\\s?(\\([A-Z]*\\))");
	    Matcher matcher = pattern.matcher(s);
	    if (matcher.find()){
	    	//System.out.println("Starter index" + matcher.start());
	    	//System.out.println("End index" + matcher.end());
	    	//System.out.println("Matched: "+ matcher.group(4));
	  	}
		return matcher; 
	  }
}
