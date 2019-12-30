package stackForTree;

import java.util.Vector;

public class VectorStack<T> implements StackInterface<T> {
	
	private Vector<T>  stack;
	private static final int DEFAULT_INITIAL_CAPACITY = 50;
	
	public VectorStack()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public VectorStack( int initialCapacity )
	{
		stack = new Vector<T>(initialCapacity);
	}

	@Override
	public void push(T newEntry) {
		// TODO Auto-generated method stub
		stack.add( newEntry );

	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		T top = null;
		
		if (!isEmpty())
			top = stack.remove(stack.size() - 1);
		
		return top;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		T top = null;
		
		if (!isEmpty())
			top = stack.lastElement();
		
		return top;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		stack.clear();

	}

}
