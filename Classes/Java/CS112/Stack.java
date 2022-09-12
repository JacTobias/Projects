public class Stack<T> { // declaring that the Stack will have items of type T

    // T CANNOT NOT BE a primitive data type

    private Node first = null;  // reference to the first node of the LL 

    private class Node {     // inner class Node
        private T item;      // reference to an object of type T      
        private Node next;   // reference to a Node object
    }  
    public boolean isEmpty() {  
        return first == null;  // if first is null, empty stack
    }
    // 4 assignments => O(1)
    public void push(T item) { // inserting a new node (contains item) in the beginning of the LL
        Node oldFirst = first;
        first = new Node();      
        first.item = item;     
        first.next = oldFirst;
    }
    // 2 assignments => O(1)
    public T pop() { // removing the first node of the LL
        T item = first.item; // saving the reference to String item
        first = first.next; // removes the first node of the LL
        return item;
    }

    public static void main (String[] args) {

        Stack<Integer> st = new Stack<Integer>();
        st.push(8);
        st.push(7);
        st.push(6);
        
        Stack<Character> stC = new Stack<Character>(); // instantiating the Stack
        stC.push('I');
        stC.push('h');
        stC.push('a');
        stC.push('d');
        int rand = 7/2;
        System.out.println(rand);
        while (!st.isEmpty()) 
            System.out.println(st.pop());

        while (!stC.isEmpty())
            System.out.println(stC.pop());
    }
}
