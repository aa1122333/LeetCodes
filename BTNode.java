package leetcodeTest;

import java.util.ArrayList;
import java.util.List;

public class BTNode<E extends Comparable<E>>   {
	public int n = 0;
	public List<E> keys = new ArrayList<E>();
	public List<BTNode<E>> children = new ArrayList<BTNode<E>>();
	
}
