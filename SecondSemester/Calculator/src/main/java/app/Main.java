package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new LinkedList<>();
        ApplicationContext context
                = new FileSystemXmlApplicationContext(getClass().getResource("/spring-config.xml").toString());
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("+") || str.equals("-") || str.equals("*")) {
                MathOperation operation;
                if (str.equals("+")) {
                    operation = (MathOperation)context.getBean("addition");
                } else if (str.equals("-")) {
                    operation = (MathOperation) context.getBean("subtraction");
                } else {
                    operation = (MathOperation)context.getBean("multiplication");
                }
                System.out.println(operation.calculate(list));
                list = new LinkedList<>();
            }
            else {
                list.add(Integer.parseInt(str));
            }
        }
    }
}
