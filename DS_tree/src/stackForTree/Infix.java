package stackForTree;
import java.util.Scanner;

public class Infix {
	
	public int evalInfixOneDigit(String infixExpression)
	{
		System.out.println("Expression: " + infixExpression);
		
		System.out.println( "==========Calculating in Progress...==========");
		
		StackInterface<Character> operatorStack = new VectorStack<Character>();
		StackInterface<Integer> valueStack = new VectorStack<Integer>();
		
		int characterCount = infixExpression.length();
		int index = 0;
		char nextCharacter = ' ';

		
		for ( ; (index < characterCount); index++ )
		{
			nextCharacter = infixExpression.charAt(index);
			System.out.println("NextCharacter: " + nextCharacter);

			switch (nextCharacter)
			{
				case '1': case '2': case '3': case '4': case '5': 
				case '6': case '7': case '8': case '9': case '0':
					valueStack.push( Character.getNumericValue(nextCharacter) );
					break;
				case '^':
					operatorStack.push(nextCharacter);
					break;
				case '+': case '-': case '*': case '/':
					try{
						
						boolean isLowPrecedence = !operatorStack.isEmpty() 
							&& ( precedenceOf(nextCharacter) <= precedenceOf(operatorStack.peek()) );
						System.out.println(" - isLowPrecedence: " + isLowPrecedence );
						
						while ( isLowPrecedence )
						{
							//System.out.println("   - Precedence of NextCharacter: " 
							//		+ precedenceOf(nextCharacter));
							//System.out.println("   - Precedence of OperatorStackPeek: " 
							//		+ precedenceOf(operatorStack.peek()));
							char topOperator = operatorStack.pop();
							int operandTwo = valueStack.pop();
							int operandOne = valueStack.pop();
							int result = calcTwo(operandOne, operandTwo, topOperator);
							valueStack.push(result);
							System.out.println("   [L]ValueStackPeek: " + valueStack.peek());
						}	
					}
					catch (NullPointerException e){}

					operatorStack.push(nextCharacter);
					break;
				case '(':
					operatorStack.push(nextCharacter);
					break;
				case ')':
					char topOperator = operatorStack.pop();
					while (topOperator != '(')
					{
						int operandTwo = valueStack.pop();
						int operandOne = valueStack.pop();
						int result = calcTwo(operandOne, operandTwo, topOperator);
						valueStack.push(result);
						topOperator = operatorStack.pop();
					}
					break;
				default: break;
			}
			
			System.out.println(" - ValueStackPeek: " + valueStack.peek());
			System.out.println(" - OperatorStackPeek: " + operatorStack.peek());
		}
		
		while (!operatorStack.isEmpty())
		{
			char topOperator = operatorStack.pop();
			int operandTwo = valueStack.pop();
			int operandOne = valueStack.pop();
			int result = calcTwo(operandOne, operandTwo, topOperator);
			valueStack.push(result);
			
			System.out.println(" - [B]ValueStackPeek: " + valueStack.peek());
			System.out.println(" - [B]OperatorStackPeek: " + operatorStack.peek());
		}
		System.out.println( "==========Calculating Complete!!!==========");
		return valueStack.peek();
	}
	
	public int evalInfix (String infixExpression)
	{
		System.out.println("Expression: " + infixExpression);
		
		System.out.println( "==========Calculating in Progress...==========");
		
		StackInterface<Character> operatorStack = new VectorStack<Character>();
		StackInterface<Integer> valueStack = new VectorStack<Integer>();
		
		int characterCount = infixExpression.length();
		int index = 0;
		char nextCharacter = ' ';

		
		for ( ; (index < characterCount); index++ )
		{
			nextCharacter = infixExpression.charAt(index);
			System.out.println("NextCharacter: " + nextCharacter);

			switch (nextCharacter)
			{
				case 'a': case 'b': case 'c': case 'd': case 'e': 
				case 'f': case 'g': case 'h': case 'i': case 'j':
				case 'k': case 'l': case 'm': case 'n': case 'o':
				case 'p': case 'q': case 'r': case 's': case 't':
				case 'u': case 'v': case 'w': case 'x': case 'y':
				case 'z':
					valueStack.push( returnToInteger(nextCharacter) );
					break;
				case '1': case '2': case '3': case '4': case '5': 
				case '6': case '7': case '8': case '9': case '0':
					valueStack.push( Character.getNumericValue(nextCharacter) );
					break;
				case '^':
					operatorStack.push(nextCharacter);
					break;
				case '+': case '-': case '*': case '/':
					try{
						
						boolean isLowPrecedence = !operatorStack.isEmpty() 
							&& ( precedenceOf(nextCharacter) <= precedenceOf(operatorStack.peek()) );
						System.out.println(" - isLowPrecedence: " + isLowPrecedence );
						
						while ( isLowPrecedence )
						{
							//System.out.println("   - Precedence of NextCharacter: " 
							//		+ precedenceOf(nextCharacter));
							//System.out.println("   - Precedence of OperatorStackPeek: " 
							//		+ precedenceOf(operatorStack.peek()));
							char topOperator = operatorStack.pop();
							int operandTwo = valueStack.pop();
							int operandOne = valueStack.pop();
							int result = calcTwo(operandOne, operandTwo, topOperator);
							valueStack.push(result);
							System.out.println("   [L]ValueStackPeek: " + valueStack.peek());
						}	
					}
					catch (NullPointerException e){}

					operatorStack.push(nextCharacter);
					break;
				case '(':
					operatorStack.push(nextCharacter);
					break;
				case ')':
					char topOperator = operatorStack.pop();
					while (topOperator != '(')
					{
						int operandTwo = valueStack.pop();
						int operandOne = valueStack.pop();
						int result = calcTwo(operandOne, operandTwo, topOperator);
						valueStack.push(result);
						topOperator = operatorStack.pop();
					}
					break;
				default: break;
			}
			
			System.out.println(" - ValueStackPeek: " + valueStack.peek());
			System.out.println(" - OperatorStackPeek: " + operatorStack.peek());
		}
		
		while (!operatorStack.isEmpty())
		{
			char topOperator = operatorStack.pop();
			int operandTwo = valueStack.pop();
			int operandOne = valueStack.pop();
			int result = calcTwo(operandOne, operandTwo, topOperator);
			valueStack.push(result);
			
			System.out.println(" - [B]ValueStackPeek: " + valueStack.peek());
			System.out.println(" - [B]OperatorStackPeek: " + operatorStack.peek());
		}
		System.out.println( "==========Calculating Complete!!!==========");
		return valueStack.peek();
	}
	
	private int precedenceOf(char operator)
	{
		if ( operator == '+' || operator == '-' )
			return 1;
		else if ( operator == '*' || operator == '/' )
			return 2;
		else if ( operator == '^' )
			return 3;

		
		return 0;
		
	}
	
	private int calcTwo(int operandOne, int operandTwo, char operator)
	{
		int result = 0;
		
		if (operator == '+')
			result = operandOne + operandTwo;
		else if (operator == '-')
			result = operandOne - operandTwo;
		else if (operator == '*')
			result = operandOne * operandTwo;
		else if (operator == '/')
			result = operandOne / operandTwo;
		else if (operator == '^')
			result = (int) Math.pow(operandOne, operandTwo);
	
		return result;
			
	}
	
	private int returnToInteger(char x)
	{
		Scanner scanner = new Scanner( System.in );
		
		int output;
		
		System.out.print("*Hello. Input value of variable \'" + x 
				+ "\' (value must be integer type): " );
		
		output = scanner.nextInt();
		
		return output;
		
	}


}
