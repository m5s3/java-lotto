package StringCalculator;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final Pattern CHECK_NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static int calculate(String input) {
        validate(input);
        List<String> strings = Arrays.asList(input.split(" "));
        Deque<Integer> operands = new LinkedList<>();
        Queue<String> operators = new LinkedList<>();

        initCalculator(strings, operands, operators);
        process(operators, operands);

        return operands.peek();
    }

    private static void process(Queue<String> operators, Deque<Integer> operands) {
        while (!operators.isEmpty()) {
            String operator = operators.poll();
            operands.addFirst(cal(operator, operands.poll(), operands.poll()));
        }
    }

    private static void initCalculator(List<String> strings, Deque<Integer> operands, Queue<String> operators) {
        for (String string : strings) {
            initOperands(string, operands);
            initOperators(string, operators);
        }
    }


    private static void initOperands(String string, Queue<Integer> operands) {
        if (isNumber(string)) {
            operands.offer(Integer.parseInt(string));
        }
    }

    private static void initOperators(String string, Queue<String> operators) {
        if (!isNumber(string)) {
            operators.offer(string);
        }
    }

    private static int cal(String operator, int leftOperand, int rightOperand) {
        if (operator.equals("+")) {
            return add(leftOperand, rightOperand);
        }

        if (operator.equals("-")) {
            return minus(leftOperand, rightOperand);
        }

        if (operator.equals("/")) {
            return divide(leftOperand, rightOperand);
        }

        if (operator.equals("*")) {
            return multiply(leftOperand, rightOperand);
        }
        throw new IllegalArgumentException();
    }

    private static int multiply(int leftOperand, int rightOperand) {
        return leftOperand * rightOperand;
    }

    private static int divide(int leftOperand, int rightOperand) {
        return leftOperand / rightOperand;
    }

    private static int minus(int leftOperand, int rightOperand) {
        return leftOperand - rightOperand;
    }

    private static int add(int leftOperand, int rightOperand) {
        return leftOperand + rightOperand;
    }

    private static void validate(String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException();
        }

        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isNumber(String input) {
        if (input.isBlank() || input.isEmpty()) {
            return false;
        }
        return CHECK_NUMBER_PATTERN.matcher(input).matches();
    }
}
