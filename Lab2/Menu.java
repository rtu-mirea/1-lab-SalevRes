import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private ExerciseOne exerciseOne;
    private ExerciseTwo exerciseTwo;
    private ExerciseThree exerciseThree;


    public Menu(){
        exerciseOne = new ExerciseOne(Test.ex1test1);
        exerciseTwo = new ExerciseTwo(Test.ex1test2);
        exerciseThree = new ExerciseThree(Test.ex1test3);
    }

    public void start(){
        while (true){
            System.out.println("1) Запустить первое задание\n2) Запустить второе задание\n3) Запустить третье задание\n0) Выход");
            Scanner scan = new Scanner(System.in);
            int choice = intInput(scan);
            switch (choice){
                case (1):
                    exerciseOneSubmenu();
                    break;
                case (2):
                    exerciseTwoSubmenu();
                    break;
                case (3):
                    exerciseThreeSubmenu();
                    break;
                default:
                    return;
            }
        }
    }

    private void exerciseOneSubmenu(){
        while (true){
            exerciseOnePrintMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case (1):
                    System.out.println(exerciseOne.getCountOfJ());
                    break;
                case (2):
                    System.out.println("Введите размер шрифта:");
                    exerciseOne.changeFontSize(intInput(scanner));
                    System.out.println("Шрифт изменен!\n");
                    break;
                case (3):
                    System.out.println(exerciseOne.getWordsWithPattern()+"\n");
                    break;
                case (4):
                    System.out.println(exerciseOne.getFontOfIntegers()+"\n");
                    break;
                case (5):
                    System.out.println(exerciseOne.getText()+"\n");
                    break;
                default:
                    return;
            }
        }
    }

    private void exerciseTwoSubmenu(){
        while (true){
            exerciseTwoPrintMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case (1):
                    System.out.println("Введите данные сотрудника:");
                    if (exerciseTwo.insertWorker(scanner.nextLine())){
                        System.out.println("Сотрудник добавлен!\n");
                    }
                    else{
                        System.out.println("Некорректный ввод данных!\n");
                    }
                    break;
                case (2):
                    System.out.println("Введите номер сотрудника:");
                    if (exerciseTwo.deleteWorker(intInput(scanner))){
                        System.out.println("Сотрудник удален!\n");
                    }
                    else{
                        System.out.println("Некорректный ввод данных!\n");
                    }
                    break;
                case (3):
                    exerciseTwo.addTwoZerosToAllWorkers();
                    break;
                case (4):
                    System.out.println(exerciseTwo.getText()+"\n");
                    break;
                default:
                    return;
            }
        }
    }

    private void exerciseThreeSubmenu(){
        while (true){
            exerciseThreePrintMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case (1):
                    exerciseThree.changeAllMirroredNumbers();
                    break;
                case (2):
                    System.out.println(exerciseThree.getText()+"\n");
                    break;
                default:
                    return;
            }
        }
    }

    private void exerciseOnePrintMenu(){
        System.out.println("1) Определить, сколько раз в первом предложении использовалось начертание (Ж)\n" +
                "2) Заменить во втором предложении размер шрифта новым\n" +
                "3) Сформировать новую строку, включив в нее слова с форматом (О)(С)(12)\n" +
                "4) Найти слова, которые представляют целые числа, и определить их форматирование\n" +
                "5) Печать текста\n"+
                "0) Выход в главное меню");
    }

    private void exerciseTwoPrintMenu(){
        System.out.println("1) Вставить в текст данные по новому сотруднику, так, чтобы текст остался упорядоченным по коду\n" +
            "2) Удалить сведения о сотруднике, если известен его код\n" +
            "3) Изменить во всех предложениях значение кода, добавив к коду три нуля\n" +
            "4) Печать текста\n"+
            "0) Выход в главное меню");

    }

    private void exerciseThreePrintMenu(){
        System.out.println("1) Замените все числа, удовлетворяющие условию 1 символом *\n" +
                "2) Печать текста\n"+
                "0) Выход в главное меню");
    }

    int intInput(Scanner scanner){
        int value = -1;
        try {
            value = scanner.nextInt();
        }
        catch (InputMismatchException e){

        }
        return value;

    }
}
