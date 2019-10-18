import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseTwo {
    private StringBuffer text;

    public ExerciseTwo(String inputText){
        text = new StringBuffer(inputText);
    }

    boolean insertWorker(String worker){
        if (isNotWorker(worker)){
            return false;
        }
        Pattern intPattern = Pattern.compile("\\d+\\s[а-яА-Яa-zA-Z]");
        Matcher intMatcher = intPattern.matcher(text);
        Matcher numberOfWorker = intPattern.matcher(worker);
        int number = 0;
        if (numberOfWorker.find()){
            number = parseToIntWithoutLastChar(numberOfWorker.group());
        }
        while (intMatcher.find() & number!=-1){
            if (parseToIntWithoutLastChar(intMatcher.group())>number){
                text.insert(intMatcher.start(), worker+"\n");
                return true;
            }
        }
        text.append("\n"+worker);
        return true;
    }

    boolean deleteWorker(int number){
        if (number<0){
            return false;
        }
        Pattern intPattern = Pattern.compile("\\n"+number+"\\s");
        Matcher intMatcher = intPattern.matcher(text);
        if (intMatcher.find()){
            int endOfLineIndex = intMatcher.end()+text.substring(intMatcher.end()).indexOf("\n");
            text.delete(intMatcher.start(), endOfLineIndex);
            return true;

        }
        return  false;
    }

    void addTwoZerosToNumber(){
        Pattern p = Pattern.compile("[^\\n]\\d+\\s+[а-яА-Яa-zA-Z]+");
        Matcher m = p.matcher(text);
        while (m.find()){
            text.insert(m.start()+2, "00");
        }

    }

    boolean isNotWorker(String worker){
        Pattern p = Pattern.compile("\\d+\\s[а-яА-Яa-zA-Z]+\\s\\d+");
        Matcher m = p.matcher(worker);
        return !m.matches();
    }

    String getText(){return text.toString();}

    int parseToIntWithoutLastChar(String text){
        try{
            return Integer.parseInt(text.substring(0,text.length()-2));
        }
        catch (Exception e){
            return -1;
        }

    }
}
