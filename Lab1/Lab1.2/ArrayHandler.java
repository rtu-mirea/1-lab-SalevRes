import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayHandler {
    private int [] array;

    public ArrayHandler(int[] array){
        this.array= array;
    }

    int getCountOfBiggerThanLast (){
        int count = 0;
        for (int i =0; i<array.length-1;i++){
            if (array[i]>array[array.length-1]){
                count++;
            }
        }
        return count;

    }
    boolean hasElement(int key){
        if (array.length==0){
            return false;
        }
        int index = -1;
        int low = 0;
        int high = array.length;
        int [] sortedArray = array.clone();
        Arrays.sort(sortedArray);

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }

        if (index>=0){
            return true;

        }
        return false;

    }
    void input(int type) {
        Scanner scanner = new Scanner(System.in);
        if (type==1){
            System.out.println("Введите количество чисел");

            int scanValue;
            try{
                scanValue = scanner.nextInt();}
            catch (InputMismatchException e){
                System.out.println("Выбор сделан некорректно");
                return;
            }
            int arrSize = scanValue;
            array = new int[scanValue];
            while (scanValue!=0){
                try{
                    Scanner num = new Scanner(System.in);
                    array[arrSize-scanValue]=num.nextInt();
                    scanValue--;
                }
                catch (Exception e){
                    System.out.println("Вводите только целые числа");
                }
            }
        }
        else if (type == 2) {
            System.out.println("Введите количество чисел");
            int scanValue = -1;
            try{
                scanValue = scanner.nextInt();}
            catch (InputMismatchException e){
                System.out.println("Выбор сделан некорректно");
            }
            array = new int[scanValue];
            for (int i=0; i<scanValue;i++){
                array[i]= (int)(Math.random() * ((10000) + 1));
            }

        }
        else{
            System.out.println("Выбор сделан некорректно");
        }

    }
    void output(int type ){ //Не уверен, что есть смысл заменять int type на boolean isReversedPrint, тк вывод все равно основывается на введеном пользователем в консоль числе
        String outline = "";
        if (type==1){
            outline = Arrays.toString(array);
        }
        else if (type == 2){
            outline+="[";
            for (int i=array.length-1;i>=0;i--){
                outline+=array[i]+", ";
            }
            if (outline.length()>1){
                outline = outline.substring(0,outline.length()-2);
            }
            outline+="]";
        }
        else{
            outline = "Выбор сделан некорректно";
        }
        System.out.println(outline);

    }
}
