package app;

import org.springframework.context.ApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class OperationFactory {

    private final String[] commands = {"+", "-", "*"};
    private final String[] beanId = {"addition", "subtraction", "multiplication"};
    private Map<String, String> beanByCommand= new LinkedHashMap<>();
    private ApplicationContext context;

    public OperationFactory(ApplicationContext context) {
        for (int i = 0; i < commands.length; i++) {
            beanByCommand.put(commands[i], beanId[i]);
        }
        this.context = context;
    }

    public Optional<MathOperation> getOperation(String command) {
        Set<Map.Entry<String, String>> set =  beanByCommand.entrySet();
        for (Map.Entry<String, String> pair: set) {
            if (command.equals(pair.getKey())) return Optional.of( (MathOperation) context.getBean(pair.getValue()));
        }
        return Optional.empty();
    }
}
