import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    enum OperationType {
        ADDITION,
        MULTIPLICATION
    }

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka

        Stack<Integer> onlyAdditions = new Stack<>();
        char[] expressionCharArray = expression.toCharArray();
        int expressionCharArrayLength = expressionCharArray.length;
        int tmpNumber1 = 0;
        OperationType operationType = null;

        for (int i = 0; i < expressionCharArrayLength;) {
            if (Character.isDigit(expressionCharArray[i])) {
                while (i < expressionCharArrayLength && Character.isDigit(expressionCharArray[i])) {
                    tmpNumber1 *= 10;
                    tmpNumber1 += (Character.getNumericValue(expressionCharArray[i]));
                    i++;
                }
                if (operationType == OperationType.MULTIPLICATION) {
                    Integer lastAddedNumber = onlyAdditions.pop();
                    onlyAdditions.push(lastAddedNumber * tmpNumber1);
                } else {
                    onlyAdditions.push(tmpNumber1);
                }
                tmpNumber1 = 0;
            } else if (expressionCharArray[i] == '*') {
                operationType = OperationType.MULTIPLICATION;
                i++;
            } else {
                operationType = OperationType.ADDITION;
                i++;
            }
        }

        Integer sum = onlyAdditions.stream().reduce(0, Integer::sum);

        return sum;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}