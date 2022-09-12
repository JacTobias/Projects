import java.util.NoSuchElementException;

public class LinkedQueueOfStrings {
   
   private Node first; // refers to the first node in the LL
   private Node last; // refer to the last node in the LL

   private class Node {     // inner class Node
      private String item; // reference to a String object      
      private Node next;   // reference to a Node object
   }

    public boolean isEmpty() {
       return first == null;
    }

    public void enqueue (String item) {
      Node oldLast = last;   
      last = new Node();
      last.item = item;
      last.next = null;
      if (isEmpty()) 
         // If the queue is empty, the new node is the only one in the LL.
         // Make first and last point to the same node
         first = last;
      else {          
         oldLast.next = last;
      }
    }
    
    public String dequeue() {

      if (isEmpty()) {
         throw new NoSuchElementException();
      }
       String item = first.item;
       first       = first.next;
       if (isEmpty()) 
         // If the last item was removed, update last
         last = null;
       return 
         item;
      }

public static void main (String[] args){
   LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
   queue.enqueue("carol");
   queue.enqueue("fred");
   queue.enqueue("alice");
   StdOut.println((59) % 11);

   while(!queue.isEmpty()){
      String value = queue.dequeue();
      System.out.println(value);
   }
}
}