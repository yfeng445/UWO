
public class RecList {
	
	private DoubleNode<String> front;
	private DoubleNode<String> rear;
	
	public RecList(DoubleNode<String> front, DoubleNode<String> rear) {
		this.front = front;
		this.rear = rear;
	}
	
	public DoubleNode<String> recForward(DoubleNode<String> node, String value)
	{
		if(node == null)
			return null;
		if(node.getElement().equals(value))
			return node;
		return recForward(node.getNext(), value);
	}
	
	public DoubleNode<String> recBackward(DoubleNode<String> node, String value)
	{
		if(node == null)
			return null;
		if(node.getElement().equals(value))
			return node;
		return recBackward(node.getPrevious(), value);
	}

	public Object[] traverse(String string) {
		// TODO Auto-generated method stub
		Object[] res = new Object[2];
		if(string.length() % 2 == 0)
		{
			res[0] = recForward(front, string);
			res[1] = 1;
		}
		else
		{
			res[0] = recBackward(rear, string);
			res[1] = -1;
		}
		return res;
	}
	
}
