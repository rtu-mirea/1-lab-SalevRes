import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseOne {
    private String text;
    private String[] splittedByIndentText;

    public ExerciseOne(String inputText){
        text = inputText;
        splitTextByIndent();
    }

    void splitTextByIndent(){
        splittedByIndentText = text.split("\n");
    }

    void arrayToString(){
        String newText = "";
        for (int i = 0;i<splittedByIndentText.length; i++){
            newText+= splittedByIndentText[i]+"\n";

        }
        if (newText.length()>2){
            text = newText.substring(0,newText.length()-2);
        }

    }

    int getCountOfJ(){
        int count = 0;
        for (int i = 0; i<splittedByIndentText[0].length()-2;i++){
            if (splittedByIndentText[0].substring(i, i+3).equals("(Ж)")){
                count++;
            }
        }
        return count;
    }

    void changeFontSize(int size){
        if (size<0){
            return;
        }
        Pattern pattern = Pattern.compile("\\(\\d+\\)");
        if (splittedByIndentText.length>1){
            Matcher matcher = pattern.matcher(splittedByIndentText[1]);
            splittedByIndentText[1] = matcher.replaceAll("("+size+")");
        }

        arrayToString();
    }

    String getWordsWithPattern(){
        String newText = "";
        String pattern = "(О)(С)(12)";
        int lastIndex = 0;
        try{
        for (int i =0; i<text.length()-10; i++){
            if (text.substring(i, i+10).equals(pattern)){
                lastIndex = i+10;
                newText += text.substring(i+10, i+10+(text.substring(i+10)).indexOf("(")) + " ";
                i = i+9+text.substring(i+10).indexOf("(");


            }
        }}
        catch (java.lang.StringIndexOutOfBoundsException e){
            newText+= text.substring(lastIndex);

        }
        return newText;

    }

    String getFontOfIntegers(){
        String listOfIntegers = "";
        Pattern fontPattern = Pattern.compile("\\([ЖО]\\)\\([СП]\\)\\(\\d+\\)");
        Matcher fontMatcher = fontPattern.matcher(text);

        String lastFont = "";
        int matchEnd = -1;
        while (fontMatcher.find()){
            if (matchEnd!=-1){
                listOfIntegers += findIntegersInSubstring(text.substring(matchEnd, fontMatcher.start()), lastFont);
            }

            matchEnd = fontMatcher.end();
            lastFont = fontMatcher.group();
        }
        if (lastFont.equals("(О)(С)(12)")){
            listOfIntegers += findIntegersInSubstring(text.substring(matchEnd), lastFont);
        }
        if (listOfIntegers.length()>3){
            return  listOfIntegers.substring(0, listOfIntegers.length()-2);}
        return "";
    }

    private String findIntegersInSubstring(String text, String format){
        String integersList = "";
        Pattern intPattern = Pattern.compile("\\d+");
        Matcher intMatcher = intPattern.matcher(text);
        text = " "+text+" ";
        while(intMatcher.find()){
            if (isInteger(text.substring(intMatcher.start(), intMatcher.end()+2))){
                integersList += format+" "+intMatcher.group()+"\n";
            }



        }
        return integersList;
    }

    private boolean isInteger(String text) {
        return (text.substring(0, 1).matches("[\\)\\s]") & text.substring(text.length()-1).matches("[\\(\\s.!?]"));
    }

    String getText(){return text;}





}
