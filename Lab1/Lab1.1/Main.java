import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        int []singleArray = new int[0];
        menuStarter(singleArray);
    }

    static void menuStarter(int []array) {
        int operation=-1;
        while (operation!=0){
            Scanner scanner = new Scanner(System.in);
            try{
                System.out.println("Меню:\n"+
                        "1)Ввести массив\n2)Вывеси массив\n"+
                        "3)Количество чисел в массиве, больше последнего\n"+
                        "4)Входит ли число в массив\n0)Выход");
                operation = scanner.nextInt();}
            catch (InputMismatchException e){

            }
            int subChoice;
            switch (operation){

                case 1:
                    System.out.println("1)Ввод с клавиатуры\n2)Ввод с помощью генератора случайных чисел");
                    try{
                    subChoice = scanner.nextInt();
                    array= input(subChoice);}
                    catch (InputMismatchException e){
                        System.out.println("Вводите только целые числа");
                    }
                    break;

                case 2:
                    System.out.println("1)Вывод слева направо\n2)Вывод справа налево");
                    try{
                    subChoice = scanner.nextInt();
                    output(array ,subChoice);}
                    catch (InputMismatchException e){
                        System.out.println("Вводите только целые числа");
                    }
                    break;

                case 3:
                    System.out.println(getCountOfBiggerThanLast(array));
                    break;

                case 4:
                    System.out.println("Введите искомый элемент");
                    try{
                        subChoice = scanner.nextInt();
                        if (hasElement(array, subChoice)){
                            System.out.println("Элемент присутствует в массиве");
                        }
                        else{
                            System.out.println("Такого элемента не существует");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Вводите только целые числа");
                    }
                    break;



                case 0:
                    System.exit(0);
                default:
                    System.out.println("Выбор сделан некорректно");
            }
            operation=-1;
        }

    }

    static int[] input(int type) {
        int [] array = new int [0];
        Scanner scanner = new Scanner(System.in);
        if (type==1){
            System.out.println("Введите количество чисел");

            int scanValue = 0;
            try{
                scanValue = scanner.nextInt();}
            catch (InputMismatchException e){
                System.out.println("Выбор сделан некорректно");
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

        return array;
    }
    static void output(int[] array, int type ){
        String outline = "";
        if (type==1){
            outline = Arrays.toString(array);
        }
        else if (type ==2){
            outline+="[";
            for (int i=array.length-1;i>=0;i--){
                outline+=array[i]+", ";
            }
            outline = outline.substring(0,outline.length()-2);
            outline+="]";
        }
        else{
            outline = "Выбор сделан некорректно";
        }
        System.out.println(outline);
    }

    static int getCountOfBiggerThanLast (int[]a){
        int count = 0;
            for (int i =0; i<a.length-1;i++){
                if (a[i]>a[a.length-1]){
                    count++;
                }
            }
        return count;

    }
    static boolean hasElement(int[] array, int key){
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

}
