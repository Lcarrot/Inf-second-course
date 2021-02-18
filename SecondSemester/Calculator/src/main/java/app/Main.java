package app;

import app.operations.MathOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private final String[] commands = {"+", "-", "*"};

    public Main() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new LinkedList<>();
        ApplicationContext context
                = new FileSystemXmlApplicationContext(getClass().getResource("/spring-config.xml").toString());
        IOperationFactory factory = (IOperationFactory) context.getBean("factory");
        while (true) {
            String str = scanner.nextLine();
            boolean isCommand = false;
            for (int i = 0; i < commands.length && !isCommand; i++) {
                if (str.equals(commands[i])) {
                    MathOperation operation = factory.getOperation(str);
                    System.out.println(operation.calculate(list));
                    isCommand = true;
                    list = new LinkedList<>();
                }
            }
            if (!isCommand) {
                try {
                    list.add(Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    System.out.println("It's not a number!");
                }
            }
        }
    }

}
