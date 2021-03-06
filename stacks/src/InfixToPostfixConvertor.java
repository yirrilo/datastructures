// URL: https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/

package stacks;

import java.util.Scanner;
import java.util.Stack;

class InfixToPostfix {
	Stack<Character> stack;

	InfixToPostfix() {
		stack = new Stack<Character>();
	}

	// Upper-case or lower-case ASCII pattern matching
	public boolean isOperand(char ch) {
		return (ch >= 97 && ch <= 122) || (ch >= 65 && ch <= 90);
	}

	public boolean isOperator(char ch) {
		return (ch == '*' || ch == '+' || ch == '/' || ch == '-');
	}

	// Deciding the precedence of the operator based on numeric values
	public int precedence(char operator) {
		if (operator == '+')
			return 1;
		if (operator == '-')
			return 1;
		if (operator == '*')
			return 3;
		if (operator == '/')
			return 4;
		return -1;
	}

	public void processExpression(String expression) {
		for (int i = 0; i < expression.length(); i++) {

			// If it is an operand, simply print it
			if (isOperand(expression.charAt(i))) {
				System.out.print(expression.charAt(i));
			}
			else if (isOperator(expression.charAt(i))) {
				while (!stack.isEmpty() && precedence(expression.charAt(i)) <= precedence(stack.peek())) {
					System.out.print(stack.peek());
					stack.pop();
				}
				stack.push(expression.charAt(i));
			}
			else if (expression.charAt(i) == '(') {
				stack.push('(');
			}
			else if (expression.charAt(i) == ')') {
				while (stack.peek() != '(') {
					System.out.print(stack.peek());
					stack.pop();
				}
				// Extra pop to remove '(' too.
				stack.pop();
			}
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
		System.out.println();
	}
}

public class InfixToPostfixConvertor {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String expression = sc.next();
		InfixToPostfix conv = new InfixToPostfix();
		conv.processExpression(expression);
	}
}