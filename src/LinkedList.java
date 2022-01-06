public class LinkedList {
     class Node {
        Student elem;
        Node next;

        public Node(){
            elem = null;
            next = null;
        }
        public Node(Student elem,  Node next){
            this.elem = elem;
            this.next = next;
        }
    }
    Node head;
    private long size;

    public LinkedList(){
        head = null;
        size = 0;
    }
    public LinkedList(Student elem, Node next){
        head = new Node(elem, next);
        size = 0;
    }

    public void add(Student elem){
        if(head == null){
            head = new Node(elem, null);
        }
        else{
            Node node = head;
            while(node.next != null){
                node = node.next;
            }
            node.next = new Node(elem, null);

        }
        size++;
    }

    public void remove(long elem){
        if(head == null){

        }else if(head.elem.getSIDC() == elem){
            head = head.next;
        }
        else{
            Node node = head;
            while(node.next!= null){
                if(node.next.elem.getSIDC() == elem){
                    node.next = node.next.next;
                    break;
                }else
                    node = node.next;
            }
        }
        size--;
    }

    public long size(){
        return size;
    }
    public boolean exist(long key){
        if (head == null)
            return false;
        else{
            Node n = head;
            while(n!=null){
                if(n.elem.getSIDC()==key) return true;
                else n=n.next;
            }
        }
        return false;
    }

    public String getValue(long key) {
        if(head == null){
        }else {
            Node n = head;
            while (n != null) {
                if (n.elem.getSIDC() == key)
                    return n.elem.toString();
                else n = n.next;
            }
        }
        return "No student registered with SIDC "+key;
    }
}
