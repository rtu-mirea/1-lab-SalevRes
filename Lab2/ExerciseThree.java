import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseThree {
    private String text;

    public ExerciseThree(String inputText){
        text = inputText;
    }

    public static boolean isNumberMirrored(int number){
        return (Math.abs(number)<100 & Math.abs(number)>9 & number%10==number/10);
    }

    public void changeAllMirroredNumbers(){
        Pattern intPattern = Pattern.compile("\\d+");
        Matcher intMatcher = intPattern.matcher(text);
        int deletedCounter = 0;
        while(intMatcher.find()){
            if (isNumberMirrored(Integer.parseInt(intMatcher.group()))){
                text = text.substring(0, intMatcher.start()-deletedCounter)+"*"+text.substring(intMatcher.end()-deletedCounter);
                deletedCounter++;
            }

        }
    }

    String getText(){
        return text;
    }
}
