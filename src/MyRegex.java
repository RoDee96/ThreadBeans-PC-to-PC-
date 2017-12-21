
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyRegex {

    static String input;    
    public static boolean isMatch(String str){
        input = str;
        String regexe = "^([0-9]|[1-9][0-9]|[1-2][0-5][0-5])\\.([0-9]|[1-9][0-9]|[1-2][0-5][0-5])\\.([0-9]|[1-9][0-9]|[1-2][0-5][0-5])\\.([0-9]|[1-9][0-9]|[1-2][0-5][0-5])$";
        Pattern pattern = Pattern.compile(regexe, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        Boolean output = matcher.find();
        System.out.println("Regex match: "+output);
        
        return output;
    }
}
