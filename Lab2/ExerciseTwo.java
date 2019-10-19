
public class ExerciseTwo {
    private StringBuffer text;
    private int workersCount;

    public ExerciseTwo(String inputText){
        text = new StringBuffer(inputText);
        workersCount = inputText.split("\n").length;
    }


    boolean insertWorker(String worker){
        if (isNotWorker(worker)){
            return false;
        }
        text.append("\n");
        int lastIndex = 0;
        for (int i =0;i<workersCount;i++){
            String line = text.substring(lastIndex,text.indexOf("\n", lastIndex)-1);
            if (getIdOfWorker(line)>getIdOfWorker(worker)){
                text.insert(lastIndex, worker+"\n");
                text.delete(text.length()-1, text.length());
                return true;
            }
            else{
                lastIndex = text.indexOf("\n", lastIndex)+1;
            }
        }

        text.append(worker);
        workersCount++;
        return true;
    }

    boolean deleteWorker(int number){
        if (number<0){
            return false;
        }
        int lastIndex = 0;
        text.append("\n");
        for (int i =0;i<workersCount;i++){
            String line = text.substring(lastIndex,text.indexOf("\n", lastIndex)-1);
            if (getIdOfWorker(line)==number){
                text.delete(lastIndex,text.indexOf("\n", lastIndex)+1);
                text.delete(text.length()-1, text.length());
                return true;
            }
            else{
                lastIndex = text.indexOf("\n", lastIndex)+1;
            }
        }
        text.delete(text.length()-1, text.length());
        return  false;
    }

    int getIdOfWorker(String str){
        String returnInt = "";
        for (int i=0;i<str.length();i++){
            if (Character.isDigit(str.charAt(i))){
                returnInt=str.substring(0,i+1);
            }
            else{
                return Integer.parseInt(returnInt);
            }
        }
        return -1;
    }


    void addTwoZerosToAllWorkers(){
        int lastIndex = 0;
        text.append("\n");
        for (int i =0;i<workersCount;i++) {
            String line = text.substring(lastIndex, text.indexOf("\n", lastIndex) - 1);
            text.insert(lastIndex+indexOfInsertion(line), "000");
            lastIndex = text.indexOf("\n", lastIndex)+1;
        }
        text.delete(text.length()-1, text.length());

    }

    int indexOfInsertion(String str){
        for (int i=0;i<str.length();i++){
            if (!Character.isDigit(str.charAt(i))){
                return i;
            }
        }
        return -1;
    }

    boolean isNotWorker(String worker){
        return !(Character.isDigit(worker.charAt(0))&Character.isDigit(worker.charAt(1))&Character.isSpaceChar(worker.charAt(2)));
    }

    String getText(){return text.toString();}


}
