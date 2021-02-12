package app;


public interface IOperationFactory {

    MathOperation getOperation(String command);
}
