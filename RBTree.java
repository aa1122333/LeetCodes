package leetcodeTest;

//http://www.cnblogs.com/skywang12345/p/3624343.html
public class RBTree<T extends Comparable<T>> {
	private RBTNode<T> root;
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	@SuppressWarnings("hiding")
	public class RBTNode<T extends Comparable<T>>{
		boolean color;
		T key;
		RBTNode<T> left;
		RBTNode<T> right;
		RBTNode<T> parent;
		
		public RBTNode(T key,boolean color,RBTNode<T> parent,RBTNode<T> left,RBTNode<T> right){
			this.key = key;
			this.color = color;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
		public T getKey(){
			return key;
		}
		public String toString(){
			return ""+key+(this.color==RED?"(R)":"B");
		}
	}
	
	public RBTree(){
		root = null;
	}
	
	private RBTNode<T> parentOf(RBTNode<T> node){
		return node!=null?node.parent:null;
	}
	private boolean colorOf(RBTNode<T> node){
		return node!=null?node.color:BLACK;
	}
	private boolean isRed(RBTNode<T> node){
		return ((node!=null)&&(node.color==RED))?true:false;
	}
	private boolean isBlack(RBTNode<T> node){
		return !isRed(node);
	}
	private void setBlack(RBTNode<T> node){
		if(node!=null)
			node.color = BLACK;
	}
	private void setRed(RBTNode<T> node){
		if(node!=null)
			node.color = RED;
	}
	private void setParent(RBTNode<T> node,RBTNode<T> parent){
		if(node!=null)
			node.parent = parent;
	}
	private void setColor(RBTNode<T> node,boolean color){
		if(node!=null)
			node.color = color;
	}
	
	private void preOrder(RBTNode<T> root){
		if(root !=null){
			System.out.print(root.key+" ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	public void preOrder(){
		preOrder(root);
	}
	/** ×óÐý×ª
	 *        /             /
	 *        x             y
	 *       / \           /\
	 *          y    =>   x
	 *         *\         /*
	 * 
	  */
	private void leftRotate(RBTNode<T> x){
		RBTNode<T> y = x.right;
		x.right = y.left;
		if(y.left!=null)
			y.left.parent = x;
		y.parent = x.parent;
		if(x.parent==null){
			root = y;
		}else {
			if(x.parent.left==x)
				x.parent.left = y;
			else 
				x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	/** ÓÒÐý×ª
	 *         /             /
	 *         y            x
	 *        /\            /\
	 *        x      =>       y
	 *       / \\            // \
	 * 
	 */
	public void rightRotate(RBTNode<T> y){
		RBTNode<T> x = y.left;
		y.left = x.right; 
		if(x.right!=null)
			x.right.parent = y;
		x.parent = y.parent;
		if(y.parent==null){
			root = x;
		}else {
			if(y.parent.right==y)
				y.parent.right = x;
			else 
				y.parent.left = x;
		}
		x.right = y;
		y.parent = x;
	}
	
	private void insertFixUp(RBTNode<T> node){
		RBTNode<T> parent,grandparent;
		while(((parent = parentOf(node))!=null) && isRed(parent)){
			grandparent = parentOf(parent);
			
			if(parent == grandparent.left){
				
				//case 1
				RBTNode<T> uncle = grandparent.right;
				if((uncle!=null)&&isRed(uncle)){
					setBlack(uncle);
					setBlack(parent);
					setRed(grandparent);
					node = grandparent;
					continue;
				}
				
				//case 2
				if(parent.right == node){
					RBTNode<T> tmp;
					leftRotate(parent);
					tmp = parent;
					parent = node;
					node = tmp;
				}
				
				//case 3
				setBlack(parent);
				setRed(grandparent);
				rightRotate(grandparent);
			}
			else {
				RBTNode<T> uncle = grandparent.left;
				
				//case 1
				if((uncle!=null)&&isRed(uncle)){
					setBlack(uncle);
					setBlack(parent);
					setRed(grandparent);
					node = grandparent;
					continue;
				}
				
				//case 2
				if(parent.left == node){
					RBTNode<T> tmp;
					rightRotate(parent);
					tmp = parent;
					parent = node;
					node = tmp;
				}
				
				//case 3
				setBlack(parent);
				setRed(grandparent);
				leftRotate(grandparent);
			}
		}
		
		setBlack(root);
	}
	
	private void insert(RBTNode<T> node) {
		int cmp;
		RBTNode<T> y = null;
		RBTNode<T> x = root;

		while (x != null) {
			y = x;
			cmp = node.key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else
				x = x.right;
		}

		node.parent = y;
		if (y != null) {
			cmp = node.key.compareTo(y.key);
			if (cmp < 0)
				y.left = node;
			else
				y.right = node;
		} else {
			root = node;
		}
		node.color = RED;
		insertFixUp(node);
	}
	
	public void insert(T key){
		RBTNode<T> node = new RBTNode<T>(key,BLACK,null,null,null);
		if(node !=null)
			insert(node);
	}
	
	private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
		RBTNode<T> other;

		while ((node == null || isBlack(node)) && (node != root)) {
			if (parent.left == node) {
				other = parent.right;
				if (isRed(other)) {
					setBlack(other);
					setRed(parent);
					leftRotate(parent);
					other = parent.right;
				}

				if ((other.left == null || isBlack(other.left))
						&& (other.right == null || isBlack(other.right))) {
					setRed(other);
					node = parent;
					parent = parentOf(node);
				} else {

					if (other.right == null || isBlack(other.right)) {
						setBlack(other.left);
						setRed(other);
						rightRotate(other);
						other = parent.right;
					}
					setColor(other, colorOf(parent));
					setBlack(parent);
					setBlack(other.right);
					leftRotate(parent);
					node = root;
					break;
				}
			} else {

				other = parent.left;
				if (isRed(other)) {
					setBlack(other);
					setRed(parent);
					rightRotate(parent);
					other = parent.left;
				}

				if ((other.left == null || isBlack(other.left))
						&& (other.right == null || isBlack(other.right))) {
					setRed(other);
					node = parent;
					parent = parentOf(node);
				} else {

					if (other.left == null || isBlack(other.left)) {
						setBlack(other.right);
						setRed(other);
						leftRotate(other);
						other = parent.left;
					}

					setColor(other, colorOf(parent));
					setBlack(parent);
					setBlack(other.left);
					rightRotate(parent);
					node = root;
					break;
				}
			}
		}

		if (node != null)
			setBlack(node);
	}

	private void remove(RBTNode<T> node) {
		RBTNode<T> child, parent;
		boolean color;

		if ((node.left != null) && (node.right != null)) {
			RBTNode<T> replace = node;

			replace = replace.right;
			while (replace.left != null)
				replace = replace.left;

			if (parentOf(node) != null) {
				if (parentOf(node).left == node)
					parentOf(node).left = replace;
				else
					parentOf(node).right = replace;
			} else {
				root = replace;
			}

			child = replace.right;
			parent = parentOf(replace);
			color = colorOf(replace);

			if (parent == node) {
				parent = replace;
			} else {
				if (child != null)
					setParent(child, parent);
				parent.left = child;

				replace.right = node.right;
				setParent(node.right, replace);
			}

			replace.parent = node.parent;
			replace.color = node.color;
			replace.left = node.left;
			node.left.parent = replace;

			if (color == BLACK)
				removeFixUp(child, parent);

			node = null;
			return;
		}

		if (node.left != null) {
			child = node.left;
		} else {
			child = node.right;
		}

		parent = node.parent;
		color = node.color;

		if (child != null)
			child.parent = parent;

		if (parent != null) {
			if (parent.left == node)
				parent.left = child;
			else
				parent.right = child;
		} else {
			root = child;
		}

		if (color == BLACK)
			removeFixUp(child, parent);
		node = null;
	}
	
}
