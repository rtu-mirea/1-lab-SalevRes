import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        int []a = new int[0];
        menu(a);
    }
    static void menu(int []a) {
        int operation=-1;
        while (operation!=0){
            try{
                System.out.println("Меню:\n"+
                        "1)Ввести массив\n2)Вывеси массив\n"+
                        "3)Количество чисел в массиве, больше последнего\n"+
                        "4)Входит ли число в массив\n0)Выход");
                Scanner test = new Scanner(System.in);
                operation = test.nextInt();
                if (operation>4 || operation<0){
                    operation = 0;
                }
                else if (operation ==1){
                    System.out.println("1)Ввод с клавиатуры\n2)Ввод с помощью генератора случайных чисел");
                    Scanner scan = new Scanner(System.in);
                    int subchoice = scan.nextInt();
                    a= input(subchoice);

                }
                else if (operation ==2){
                    System.out.println("1)Вывод слева направо\n2)Вывод справа налево");
                    Scanner scan = new Scanner(System.in);
                    int subchoice = scan.nextInt();
                    output(a ,subchoice);
                }
                else if (operation ==3){
                    System.out.println(moreLast(a));
                }
                else if (operation ==4){
                    System.out.println("Введите искомое число");
                    Scanner scan = new Scanner(System.in);
                    int subchoice = scan.nextInt();
                    System.out.println(binSearch(a ,subchoice));
                }
                else if (operation ==0){
                    System.exit(0);
                }
            }
            catch (InputMismatchException e){
                operation = 0;
                System.out.println("Выбор сделан некорректно");

            }

        }

    }
    static int[] input(int type) {
        System.out.println("Введите количество чисел");
        Scanner scaner = new Scanner(System.in);
        int scan = scaner.nextInt();
        int arrsize = scan;
        int a[] = new int[scan];
        if (type==1){
            while (scan!=0){
                try{

                    Scanner num = new Scanner(System.in);
                    a[arrsize-scan]=num.nextInt();
                    scan--;
                }
                catch (Exception e){
                    System.out.println("Вводите только числа!");
                }
            }
        } else if (type == 2) {
            for (int i=0; i<scan;i++){
                a[i]= (int)(Math.random() * ((10000) + 1));
            }
        }

        return a;
    }
    static void output(int[] a, int type ){
        String outline = "";
        if (type==1){
            outline = Arrays.toString(a);
        }
        else if (type ==2){
            outline+="[";
            for (int i=a.length-1;i>=0;i--){
                outline+=a[i]+", ";
            }
            outline = outline.substring(0,outline.length()-2);
            outline+="]";
        }
        System.out.println(outline);
    }
    static int moreLast (int[]a){

        int count = 0;
        for (int i =0; i<a.length-1;i++){
            if (a[i]>a[a.length-1]){
                count++;
            }
        }
        return count;

    }
    static String binSearch(int[] arr, int key){
        int [] a = arr.clone();
        Arrays.sort(a);
        int value = Arrays.binarySearch(a, key);
        if (value>=0){
            return "Элемент входит в массив";


        }
        else{
            return "Искомого элемента не существует";
        }
    }

}
