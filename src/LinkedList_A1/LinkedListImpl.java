/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel;// this will be the entry point to your linked list (the
					// head)

	/*
	 * numElts is a field to maintain the size of the linked list, it will be
	 * used for the size, clear, and isEmpty methods
	 * 
	 * the head and the curr are nodes that are used to cycle through the lists
	 */

	private int numElts;
	private Node head;
	private Node curr;

	public LinkedListImpl() {// this constructor is needed for testing purposes.
								// Please don't modify!
		sentinel = new Node(Double.NaN);// Note that the root's data is not a
										// true part
		// of your data set!

		// numElts must be 0 because the sentinel doesn't count and the size of
		// an empty list is 0
		numElts = 0;
	}

	// implement all methods in interface, and include the getRoot method we
	// made for testing purposes. Feel free to implement private helper methods!

	public Node getRoot() { // leave this method as is, used by the grader to
							// grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {

		/*
		 * The insert method cycles through the list starting with sentinel
		 * until it reaches the index, and then it creates a new node with the
		 * parameter data value and changes the previous to the current
		 * previous, the current previous to the new node, the new nodes
		 * previous node's next field to the new, and the new nodes next field
		 * to the current, this adds an element to the linked list
		 * 
		 * the numElts changes by +1
		 * 
		 * also, if the list is empty, it adds an element and changes the
		 * sentinel's prev and next to the new node and vice versa
		 */

		if (index < 0 || index > numElts) {
			return false;
		}

		Node newnode = new Node(elt);

		if (numElts > 0) {
			head = sentinel.next;

			curr = head;
			int count = 0;

			while (curr != sentinel) {
				if (count < index) {
					curr = curr.next;
					count++;
				} else {
					break;
				}
			}

			newnode.prev = curr.prev;
			curr.prev = newnode;
			newnode.prev.next = newnode;
			newnode.next = curr;

			if (count == index - 1) {
				sentinel.prev = curr;
			}
		} else {
			sentinel.next = newnode;
			newnode.next = sentinel;
			sentinel.prev = newnode;
			newnode.prev = sentinel;
		}

		numElts++;
		return true;
	}

	@Override
	public boolean remove(int index) {

		/*
		 * the remove method cycles through the list starting with sentinel, and
		 * once the count reaches the index it breaks the cycle and it changes
		 * the previous node's next field to the next, and the next node's
		 * previous field to the previous, in essence removing the current node
		 * from the linked list
		 * 
		 * the numElts changes by -1
		 */

		if (index < 0 || index > size()) {
			return false;
		}

		if (numElts > 0) {
			head = sentinel.next;
			curr = head;
			int count = 0;

			while (curr != sentinel) {
				if (count < index) {
					curr = curr.next;
					count++;
				} else {
					break;
				}
			}

			curr.next.prev = curr.prev;
			curr.prev.next = curr.next;
		} else {
			return false;
		}

		numElts--;
		return true;
	}

	@Override
	public double get(int index) {

		/*
		 * the get method cycles through the list starting with the sentinel and
		 * when it has cycled through index elements, it returns the data value
		 * of that element
		 */

		if (index < 0 || index > size()) {
			return Double.NaN;
		}

		if (numElts > 0) {
			head = sentinel.next;
			curr = head;
			int count = 0;

			while (curr != sentinel) {
				if (count == index) {
					break;
				}
				count++;
				curr = curr.next;
			}
		} else {
			return Double.NaN;
		}
		return curr.getData();
	}

	@Override
	public int size() {
		return numElts;
	}

	@Override
	public boolean isEmpty() {
		return numElts == 0;
	}

	@Override
	public void clear() {
		numElts = 0;

		// clearing the next and prev empties the list frees the anchor
		// (sentinel)
		sentinel.next = null;
		sentinel.prev = null;
	}
}