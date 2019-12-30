package stackForTree;

public class InfixTest {

	public static void main(String[] args) {
		
		Infix infix = new Infix();
		
		// evaluate expression that used single alphabet letter operands
		// alphabet operands must be lower cases. For example, expression that "a+b*c" 
		// or you can use one-digit numeric operands. For example, "1+2*3"
		// And.. you can use both type operands. For example, "a+b*3"
		String expression = "6-3*4^2+5";
		String expression2 = "a+b*c";
		System.out.println("The result is: " + infix.evalInfix(expression));
		System.out.println("The result is: " + infix.evalInfix(expression2));
	}
	

}
