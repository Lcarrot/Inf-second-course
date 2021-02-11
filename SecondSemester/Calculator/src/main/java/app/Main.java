package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
        OperationFactory factory = new OperationFactory(context);
        while (true) {
            String str = scanner.nextLine();
            Optional<MathOperation> operation = factory.getOperation(str);
            if (operation.isPresent()) {
                System.out.println(operation.get().calculate(list));
                list = new LinkedList<>();
            }
            else {
                list.add(Integer.parseInt(str));
            }
        }
    }
}
