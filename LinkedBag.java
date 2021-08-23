import java.util.ArrayList;
import java.util.Arrays;

//notice when implementing this class we had to include the <T> with the interface name, so that we know the class will be using generics
public final class LinkedBag<T> implements LinkedInterface<T>
{


    // ===== Instance Variables ===== //
    private Node firstNode;       //composition class at end of LinkedBag
    private int numberOfEntries;  //in each linked list, can create n number of linked lists with this class

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";


    // ===== Constructor ===== //
    public LinkedBag()  //overriding default
    {
        firstNode = null;
        numberOfEntries = 0;
    }


    // ===== Methods ===== //
    @Override
    public int getCurrentSize()  {  return this.numberOfEntries;  }

    @Override
    public boolean isEmpty()  {  return this.numberOfEntries == 0;  }

    @Override
    //adding in new nodes into head, which stores list backwards from how it was entered
    public boolean add(T newEntry)
    {
        //creating another link in the chain (new Node)
        Node newestNode = new Node(newEntry);

        //setting newestNode 'pointer' == to the address of the firstNode, must do this so we do not lose the headNode
        //if null, then firstNode is now the object that was created
        newestNode.next = this.firstNode;

        //now assigning the instance object of firstNode == the node object we just created
        //now the head of the linkedList is the newest node we created
        this.firstNode = newestNode;

        //incrementing how many nodes we have in this instance chain
        this.numberOfEntries ++;

        return true;
    }

    @Override
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")   // when downcasting/upcasting, chance to fail
        //creating temp array with size of number of nodes in our linkedlist, will return this array
        T[] tempArray = (T[]) new Object[this.numberOfEntries];

        //need to iterate through the linked list in order to copy nodes into an array

        //need an index number to ensure we are staying within numberOfEntries 
        int index = 0;
        //creating temp Node to iterate through the linkedList
        Node currentNode = this.firstNode;

        while(index < this.numberOfEntries && (currentNode != null)) //could also use for loop here
        {
            tempArray[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;  //iterating to next node in list
        }

        return tempArray;
    }

    @Override
    public boolean removeAllOccurrences(T[][] entries)
    {
        /*Since there is a different number of columns in each row, need to retrieve those
          manually so we can know how many columns/elements there is in total */
        int addingColumnsInEachRow = 0;
        //getting number of elements in each row
        for(int x=0 ; x < entries.length ; x++)
        {
            addingColumnsInEachRow += entries[x].length;
        }

        final int elementsTotal = addingColumnsInEachRow;

        LinkedBag.sleepTimer(2000);
        System.out.println("[+] Removing 2D test array items from the bag...");
        LinkedBag.sleepTimer(2000);
        System.out.print(" [-] Converting 2D array to 1D: ");
        LinkedBag.sleepTimer(2000);

        //creating 1D array to store entries into
        @SuppressWarnings("unchecked")
        T[] array2Dto1D = (T[]) new Object[elementsTotal];

        //for iterating to next element in array, in for loop
        int elementPlaceHolder = 0;
        //iterating through the 2D array
        for(int row=0; row < entries.length ; row++)
        {
            for(int col=0 ; col < entries[row].length; col++)
            {
                //storing everything into 1D array
                array2Dto1D[elementPlaceHolder] = entries[row][col];
                sleepTimer(50);
                System.out.print(ANSI_RED + " " + entries[row][col]);
                elementPlaceHolder++;
            }
        }


        System.out.println(ANSI_RESET + "\n [-] Removing duplicates in 1D array...");

        //for storing non duplicates, *dynamic in size
        ArrayList<T> nonDuplicateArrL = new ArrayList<T>();

        for(int x=0 ; x < array2Dto1D.length ; x++)
        {
            if(x == 0) //first element therefore no duplicates yet
                nonDuplicateArrL.add(array2Dto1D[0]);
            else
            {
                if(!nonDuplicateArrL.contains(array2Dto1D[x])) //if arrayList does not already contain this element
                    nonDuplicateArrL.add(array2Dto1D[x]);
            }
        }


        System.out.print(" [>]The final 1D array now contains: ");

        for(int x=0 ; x<nonDuplicateArrL.size() ; x++)
        {
            System.out.print(ANSI_RED + nonDuplicateArrL.get(x) + " ");
        }
        System.out.println(ANSI_RESET);

        System.out.println(" [-] Removing the final 1D array items from the bag...");

        //need to delete nodes containing same data that is in 1D array

        /*going through each link in the linkedList so we only need to traverse through once thhorugh the list.
          Also cannot do while(Node.next =! null) because we need to reach the last element, and the last element will have a
          null value for next, therefore it will not be reached.
       */
        Node traversingNode = this.firstNode;
        int numOfDuplicates = 0;

        while(traversingNode != null)
        {
            //traversing through the array multiple times, but the LinkedList only once (better than the other way around)
            for(int x=0 ; x < nonDuplicateArrL.size() ; x++)
            {
                if(traversingNode.data == nonDuplicateArrL.get(x))
                {
                    Node nodeN = traversingNode;
                    nodeN.data = firstNode.data; // Replace located entry with entry in first
                    firstNode = firstNode.next; // Remove first node numberOfEntries--;
                    numOfDuplicates++;
                }
            }
            //need to iterate through LinkedList each outer for loop
            traversingNode = traversingNode.next;
        }

        int temp = this.numberOfEntries;
        this.numberOfEntries = this.numberOfEntries - numOfDuplicates;

        while(traversingNode != null)
        {
            if(traversingNode.next == null) traversingNode = null;
        }


        return true;
    }



    //SLEEP TIMER function
    private static void sleepTimer(int time) {
        try {        //sleep must be surrounded with try{}catch{} in case it is interrupted
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }


    //
    //===================== Composition classes ========================
    //
    private class Node
    {

        //creating our own generic, T 
        private T data;
        private Node next;

        //
        //Constructors
        //
        private Node()
        {
            this.data = null;
            this.next = null;
        }

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            //creating current node w/ given data //creating new box full of stuff
            data = dataPortion;
            /*Creating another box, but this one is empty/ But now it is created for the next 
            crop off of data */
            next = nextNode;
        }
    }
}