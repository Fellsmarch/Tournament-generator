package bracketStage;

public class BinaryTree
	{
		Node root = new Node();
		
		public BinaryTree(int numParticipants) { //Creates the empty binary tree
			
		}
		
		private void createEmptyTree(int numParticipants) {
			int numNodes = 1;
			Node currentNode = root;
			while (numNodes <= numParticipants) {
				if (currentNode.left == null) {
					Node newNode = new Node(currentNode);
					currentNode.left = newNode;
					numNodes++;
				} else if (currentNode.right == null) {
					Node newNode = new Node(currentNode);
					currentNode.right = newNode;
					numNodes++;
				}
			}
		}
		
		private Node nextEmptySpace(Node currentNode, Node parentNode) { //Returns the next empty space for a node
			if (currentNode == null) {
				if (parentNode != null) {
					return parentNode;
				}else {return currentNode;} //Returns the root if we are on the root
			}			
			
			if (currentNode.left != null) {
				return nextEmptySpace(currentNode.left, currentNode);
			}
			if (currentNode.right != null) {
				return nextEmptySpace(currentNode.right, currentNode);
			}
			return new Node();
			
//			Node left = nextEmptySpace(currentNode.left, currentNode);
//			if (left == null) {return left.parent;}
//			Node right = nextEmptySpace(currentNode.right);
		}
		
		
		class Node {
			String team; //Value
			Node left;
			Node right;
			Node parent;
			
			public Node() { //This is for creating the root node
				left = null;
				right = null;
				parent = null;
			}

			public Node(Node parent) {
				left = null;
				right = null;
				this.parent = parent;
			}
		}
	}
