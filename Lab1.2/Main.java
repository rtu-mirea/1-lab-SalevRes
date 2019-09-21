import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        int [] singleArray = new int[0];
        ArrayHandler handler = new ArrayHandler(singleArray);
        menuStarter(handler);
    }
    static void menuStarter(ArrayHandler handler) {
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
                    handler.input(subChoice);}
                    catch (InputMismatchException e){
                        System.out.println("Вводите только целые числа");
                    }
                    break;

                case 2:
                    System.out.println("1)Вывод слева направо\n2)Вывод справа налево");
                    try{
                    subChoice = scanner.nextInt();
                    handler.output(subChoice);
                    }
                    catch (InputMismatchException e){
                    System.out.println("Вводите только целые числа");
                    }
                    break;

                case 3:
                    System.out.println(handler.getCountOfBiggerThanLast());
                    break;

                case 4:
                    System.out.println("Введите искомый элемент");
                    try{
                        subChoice = scanner.nextInt();
                        if (handler.hasElement(subChoice)){
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


}
