package com.hasanmen;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * BU KOD KITAPTAN ALINMISTIR
 * BAZI KISIMLARI KLASS YAPISINA UYARLAMAK ICIN DEGISTIRILDI
 *
 * Translates an infix expression to a postfix expression.
 *
 * @author Koffman & Wolfgang
 */

public class InfixToPostfix {

	// Nested Exception Class
	public static class SyntaxErrorException
			extends Exception {
		SyntaxErrorException(String message) {
			super(message);
		}
	}

	private Stack<Operator> operatorStack; // operator stackine okuma yapilir
	private StringBuilder postfix;


	public String convert(String infix) throws SyntaxErrorException {
		operatorStack = new Stack<Operator>();
		postfix = new StringBuilder();
		StringTokenizer infixTokens = new StringTokenizer(infix);
		try {
			// Process each token in the infix string.
			while (infixTokens.hasMoreTokens()) {
				String nextToken = infixTokens.nextToken();
				Character firstChar = nextToken.charAt(0);
				// Is it an operand?
				if (Character.isJavaIdentifierStart(firstChar)
						|| Character.isDigit(firstChar)) {
					postfix.append(nextToken);
					postfix.append(' ');
				} // Is it an operator?
				else if (Operator.isOperator(nextToken)) {
					processOperator(new Operator(nextToken));
				} else {
					throw new SyntaxErrorException
							("Unexpected Character Encountered: "
									+ firstChar);
				}
			} // End while.

			while (!operatorStack.empty()) {
				Operator op = operatorStack.pop();
				postfix.append(op);
				postfix.append(' ');
			}
			return postfix.toString();
		} catch (EmptyStackException ex) {
			throw new SyntaxErrorException
					("Syntax Error: The stack is empty!");
		} catch (Operator.OperatorException e){
			throw new SyntaxErrorException
					("Syntax Error: Operator is unresolved!");

		}
	}

	private void processOperator(Operator op) {
		if (operatorStack.empty()) {
			operatorStack.push(op);
		} else {
			Operator topOp = operatorStack.peek();
			if (op.getPrecedence() > topOp.getPrecedence()) {
				operatorStack.push(op);
			} else {
				while (!operatorStack.empty()
						&& op.getPrecedence() <= topOp.getPrecedence()) {
					operatorStack.pop();
					postfix.append(topOp);
					postfix.append(' ');
					if (!operatorStack.empty()) {
						topOp = operatorStack.peek();
					}
				}
				operatorStack.push(op);
			}
		}
	}
}
