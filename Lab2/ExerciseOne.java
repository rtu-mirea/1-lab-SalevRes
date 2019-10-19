import java.util.Arrays;

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
        String pattern1 = "П)(";
        String pattern2 = "С)(";
        String string;
        String convertedString = "";
        if (splittedByIndentText.length>1){
            string = splittedByIndentText[1];
            convertedString = string;
            for (int i = 0; i<splittedByIndentText[1].length()-3;i++)
                if (string.substring(i, i+3).equals(pattern1) | string.substring(i, i+3).equals(pattern2)){
                    int indexOfBracket = i+3+string.substring(i+3).indexOf(")");
                    convertedString = convertedString.substring(0, i+3)+size+string.substring(indexOfBracket);
                    i = indexOfBracket;

                }
        }
        splittedByIndentText[1] = convertedString;
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
        String[] patterns = {"(О)(С)(", "(О)(П)(","(Ж)(С)(","(Ж)(П)("};
        int lastFontIndex = -1;
        String font = "";
        for (int i =0; i<text.length()-7;i++){
            if (Arrays.asList(patterns).contains(text.substring(i, i+7))){

                if (lastFontIndex!=-1){
                    listOfIntegers+=findIntegersInSubstring(text.substring(lastFontIndex,i), font);
                }
                font = text.substring(i, i+8+text.substring(i+7).indexOf(")"));
                lastFontIndex = i+7+text.substring(i+7).indexOf(")");
            }
        }
        if (listOfIntegers.length()>3){
            return  listOfIntegers.substring(0, listOfIntegers.length()-2);}
        return "";
    }

    private String findIntegersInSubstring(String text, String format){
        String integersList = "";

        text = " "+text+" ";
        int firstIndex = -1;
        for (int i = 0; i<text.length(); i++){

            if (!Character.isDigit(text.charAt(i)) & firstIndex!=-1){

                if (isInteger(text.substring(firstIndex-1, i+1))){
                    integersList += format+" "+text.substring(firstIndex, i)+"\n";

                }
                firstIndex = -1;
            }
            else if (Character.isDigit(text.charAt(i)) & firstIndex==-1){
                firstIndex = i;
            }
        }
        return integersList;
    }

    private boolean isInteger(String text) {
        String[] pool = {"(", " ", ".", "!", "?"};
        return (text.substring(0, 1).equals(")") | text.substring(0, 1).equals(" "))  & Arrays.asList(pool).contains(text.substring(text.length()-1));
    }

    String getText(){return text;}





}
