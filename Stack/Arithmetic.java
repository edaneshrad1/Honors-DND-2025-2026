import java.util.ArrayList;

public class Arithmetic<E> {

    // Checks if something is an operator
    public static boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' || c == '('
                || c == ')') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperator(String str) {
        if (str == "+" || str == "-" || str == "*" || str == "/" || str == "%" || str == "^"
                || str == "(" || str == ")") {
            return true;
        } else {
            return false;
        }
    }

    // Evaluates a String exp that has an arithmetic expression, written in classic notation
    public static int evaluate(String exp) {
        String expGood = convertClassicToStout(exp);
        return evaluateStout(expGood);
    }


    // Returns the result of doing operand1 operation operand2,
    // e.g. operate(5, 2, "-") should return 3
    public static int operate(int operand1, int operand2, String operation) {
        if (operation.equals("+")) {
            return operand1 + operand2;
        } else if (operation.equals("-")) {
            return operand1 - operand2;
        } else if (operation.equals("*")) {
            return operand1 * operand2;
        } else if (operation.equals("/")) {
            if (operand2 == 0) {
                throw new ArithmeticException("Cannot divide " + operand1 + "by " + operand2);
            } else {
                return operand1 / operand2;
            }
        } else if (operation.equals("^")) {
            return (int) (Math.pow(operand1, operand2));
        } else if (operation.equals("%")) {
            return operand1 % operand2;
        } else {
            throw new IllegalArgumentException("Invalid operationa: " + operation);
        }
    }

    // Evaluates a String exp that has an arithmetic expression written in STOUT notation
    public static int evaluateStout(String exp) {
        ArrayList<String> stoutList = convertStoutToList(exp);
        return evaluateStoutHelper(stoutList);
    }

    // method that evaluates stout notation but takes in an ArrayList
    public static int evaluateStoutHelper(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<String>();

        int returnValue = 0;

        if (list.size() == 1) {
            returnValue += Integer.parseInt(list.get(0));
            return returnValue;
        } else {
            int newLoopStartFrom = 0;

            // loop through list until you find an operator at index: i
            for (int i = 0; i < list.size(); i++) {
                newLoopStartFrom++;
                char[] currentIndexArray = list.get(i).toCharArray();
                if (!isOperator(currentIndexArray[0])) {
                    continue;
                } else {
                    // run operate(i - 2, i - 1, i)
                    int addedInt = operate(Integer.parseInt(list.get(i - 2)),
                            Integer.parseInt(list.get(i - 1)), list.get(i));
                    // add everything in list before i-2 to new list
                    for (int j = 0; j < i - 2; j++) {
                        newList.add("" + list.get(j));
                    }
                    // add the value from that operation to a new list
                    newList.add("" + addedInt);
                    break;
                }
            }
            // add everything in list after i to that new list
            for (int j = newLoopStartFrom; j < list.size(); j++) {
                newList.add("" + list.get(j));
            }
        }
        // recursively call this method with the new list
        return evaluateStoutHelper(newList);
    }

    // make a method that takes each operator and operand in exp and adds them into an arrayList
    public static ArrayList<String> convertStoutToList(String exp) {
        ArrayList<String> returnList = new ArrayList<String>();
        // loop through each char in exp
        for (int i = 0; i < exp.length(); i++) {
            // if the current char is a space continue
            if (exp.charAt(i) == ' ') {
                continue;
            } else {
                // if the current char isn't a space make a new String builder and append the
                // current char
                StringBuilder tempString = new StringBuilder();
                tempString.append(exp.charAt(i));
                // append each subsequent char to the StringBuilder untill you hit a space
                int addToI = 0;
                for (int j = i + 1; j < exp.length(); j++) {
                    if (exp.charAt(j) != ' ') {
                        tempString.append(exp.charAt(j));
                        addToI++;
                    } else {
                        break;
                    }
                }
                i += addToI;
                // add the string builder to the list
                returnList.add(tempString.toString());
            }
        }
        return returnList;
    }

    public static String convertClassicToStout(String exp) {
        StringBuilder returnString = new StringBuilder();
        MyStack<Character> stack = new MyStack<>();

        // itterate through each char in exp
        for (int i = 0; i < exp.length(); i++) {
            // if the last index of return string is a space continue (avoids double spaces)
            if (returnString.length() != 0 && returnString.charAt(returnString.length() - 1) == ' '
                    && exp.charAt(i) == ' ') {
                continue;
            }
            // if the current char isn't an operator append it to returnString
            if (!isOperator(exp.charAt(i))) {
                returnString.append(exp.charAt(i));
                continue;
            } else {
                // if the current char is an operator run doWhatWithOp on it
                doWhatWithOp(exp.charAt(i), returnString, stack);
            }
        }

        // if there is still stuff in the stack append everything to returnString
        if (!stack.empty()) {
            while (stack.getHeight() != 0) {
                if (stack.peek() == '(' || stack.peek() == ')') {
                    stack.pop();
                    continue;
                } else {
                    returnString.append(" ");
                    returnString.append(stack.peek());
                    stack.pop();
                }
            }
        }
        return returnString.toString();
    }

    // make a helper that takes in an operator and decides what to do with it
    public static void doWhatWithOp(char op, StringBuilder returnString, MyStack<Character> stack) {
        // if there are no operators in the stack push the current operator onto the stack
        if (stack.empty() || stack.peek() == '(') {
            stack.push(op);
            return;
        }

        // if the current operator is a closed parentheses pop & append everything in stack until
        // the next open parenthesis
        if (op == ')') {
            while (stack.peek() != '(') {
                if (stack.peek() != ')') {
                    returnString.append(stack.peek());
                }
                stack.pop();
            }
            stack.pop();
            return;
        }

        // if the current operator is less than or equal to the top operator
        if (assignOperatorValue(op) <= assignOperatorValue(stack.peek())) {
            // append and pop the top operator
            returnString.append(stack.peek());
            stack.pop();
            // push the current operator
            stack.push(op);
            // if the current operator is greater than the top operator
        } else {
            // pusht the current operator onto the stack
            stack.push(op);
        }
    }

    // gives each operator a specific value
    // addition and subtraction are 0
    // multiplication and division are 1
    // exponents are 2
    // parentheses are 3
    public static int assignOperatorValue(char op) {
        if (op == '+' || op == '-') {
            return 0;
        } else if (op == '*' || op == '/' || op == '%') {
            return 1;
        } else if (op == '^') {
            return 2;
        } else if (op == '(' || op == ')') {
            return 3;
        } else {
            return -1;
        }
    }
}
