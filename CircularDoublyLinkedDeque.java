public class CircularDoublyLinkedDeque<T> implements DequeInterface<T> {

    // ===== Data fields ===== //
    private DoublyLinkedNode headNode;
    private DoublyLinkedNode tailNode;

    // ===== Constructors ===== //
    public CircularDoublyLinkedDeque()
    {
        headNode = null;
        tailNode = null;
    }

    // ===== Methods ===== //
    @Override
    public void clear()
    {
        headNode = null;
        tailNode = null;
    }

    @Override
    public boolean isEmpty()
    {
        return (headNode == null);
    }

    @Override
    public T getFront()
    {
        return headNode.data;
    }


    @Override
    public T getBack()
    {
        return tailNode.data;
    }

    @Override
    public void addToBack( T data)
    {
        DoublyLinkedNode node = new DoublyLinkedNode(data);

        if(tailNode == null) {
            tailNode = node;
        }
        else{
            //else we need to reassign the lists' previous/next pointers of current tail/head to include the new node
            headNode.previous = node;
            tailNode.next = node;

            //now need the new nodes previous/ to point to head/tail so that it is fully included in the list
            node.next = headNode;

            node.previous = tailNode;

            //lastly need to assign the new node as the tail node
            tailNode = node;   //the old tail node can now be found with tailNode.previous
        }
    }

    @Override
    public void addToFront(T data)
    {
        DoublyLinkedNode node = new DoublyLinkedNode(data);

        if(headNode == null) {
            headNode = node;
            if(tailNode == null)
            {
                tailNode = node;
            }
            headNode.previous = null;
        }
        else{
            //else we need to reassign the lists' previous/next pointers of current tail/head to include the new node
            headNode.previous = node;
            tailNode.next = node;

            //now need the new nodes previous/ to point to head/tail so that it is fully included in the list
            node.next = headNode;
            node.previous = tailNode;

            //lastly need to assign the new node as the tail node
            headNode = node;   //the old head node can now be found with headNode.next

        }
    }

    @Override
    public T removeBack()
    {
        if(tailNode == null)
        {
            System.out.println("Tail is now NULL");
            return null;
        }

        T temp = tailNode.data;


        headNode.previous = tailNode.previous; //reassigning head to point to the Node second to last in tail
        tailNode.previous.next = tailNode.next; //reassinging second to last Node(in tail) to point to front

        tailNode = tailNode.previous;

        return temp;
    }

    @Override
    public T removeFront()
    {
        if(headNode == null)
        {
            System.out.println("HEAD is now NULL");
            return null;
        }

        T temp = headNode.data;

        tailNode.next = headNode.next;
        headNode.next.previous = headNode.previous;

        headNode = headNode.next;

        return temp;
    }


    //=============================================================//
    private class DoublyLinkedNode
    {
        //data fields
        private T data;
        private DoublyLinkedNode next;
        private DoublyLinkedNode previous;

        //constructors
        public DoublyLinkedNode() {}

        //used only if the node being created is the firt one
        public DoublyLinkedNode(T data)
        {
            this(data, null, null);
        }

        //used to create all nodes, minus the first one
        //passing in the node's data and position within the circular Linked List
        public DoublyLinkedNode(T data, DoublyLinkedNode nextNode, DoublyLinkedNode previousNode)
        {
            this.data = data;
            this.next = nextNode;
            this.previous = previousNode;
        }
    }

}