import java.util.Scanner;

public class DataStructuresDriver
{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println(ANSI_BLUE + "\n====================== Welcome To DATA STRUCTURES Project ========================");
        System.out.println("Let's get started!\n\n" + ANSI_RESET);

        sleepTimer(3000);


        // ===== PROJECT 1 - LINKED LISTS ===== //
        System.out.println("=== LINKED BAG Project START =====================================================");
        System.out.println("[+] Creating a LinkedBag...");
        /* since we cannot directly instantiate interfaces, we are using the LinkedBag class for the implementation, this limits
         *  the LinkedBag to only the methods inherited from the Interface on the left*/
        LinkedInterface<String> csc220Bag = new LinkedBag<>();
        // passing our new object to add elements into the linked list
        addElements(csc220Bag);
        removeCertainElements(csc220Bag);
        System.out.println("=== LINKED BAG END ==================================================================\n");

        if(!nextProject("DEQUE"))
        {
            System.out.println("Goodbye!! ");
            return;
        }

        // ===== PROJECT 2 - DEQUE ===== //
        System.out.println("=== CIRCULAR DEQUE START =============================================================");
        DequeInterface<String> myDeque = new CircularDoublyLinkedDeque<>();
        System.out.println("Empty deque: " + myDeque.isEmpty());

        myDeque.addToFront("Dumbo");
        myDeque.addToFront("Cruella");
        myDeque.addToBack("Eeyore");
        myDeque.addToFront("Daisy");
        myDeque.addToBack("Flounder");
        myDeque.addToBack("Gaston");
        myDeque.addToFront("Bambi");
        myDeque.addToFront("Aladdin");

        String front, back;
        front = myDeque.getFront();
        back = myDeque.getBack();

        System.out.print("  [FRONT] " + ANSI_GREEN);
        sleepTimer(700);
        System.out.print(front + " << ");
        myDeque.removeFront();
        front = myDeque.removeFront();
        sleepTimer(700);
        System.out.print(front + " << ");
        myDeque.removeFront();
        front = myDeque.getFront();
        sleepTimer(700);
        System.out.print(front + " << ");
        myDeque.removeFront();
        front = myDeque.removeFront();
        sleepTimer(700);
        System.out.print(front + " << ...\n");

        System.out.print(ANSI_RESET + "  [BACK] " + ANSI_GREEN);
        sleepTimer(700);
        System.out.print(back + " >> ");
        myDeque.removeBack();
        back = myDeque.removeBack();
        sleepTimer(700);
        System.out.print(back + " >> ");
        back = myDeque.getBack();
        sleepTimer(700);
        System.out.print(back + " >> ...\n\n");

        sleepTimer(500);
        System.out.println(ANSI_RESET + "Clearing the circular Deque . . . \n");
        myDeque.clear();
        sleepTimer(1000);
        System.out.println("Empty deque: " + myDeque.isEmpty());

        try {
            front = myDeque.removeFront();
        } // end try
        catch (Exception e) {
            System.err.print("-> removeFront found deque empty\n");
        } // end catch


        try {
            front = myDeque.removeBack();
        } // end try
        catch (Exception e) {
            System.err.println("-> removeBack found deque empty");
        } // end catch

        System.out.println("=== CIRCULAR DEQUE END ================================================================\n");

        if(!nextProject("STACK"))
        {
            System.out.println("Goodbye!! ");
            return;
        }

        // ===== PROJECT 3 - STACK ===== //
        System.out.println("=== STACK START ======================================================================\n");
        String string= "";

        do {
            System.out.print("[>>] Enter a string (or a ! to exit): ");
            string = input.nextLine();

            if (isPalindrome(string)) {
                System.out.println(" [+] Yes. \"" + string + "\" IS a palindrome!");
            } else {
                System.out.println(" [-] No. \"" + string + "\" is NOT a palindrome!");
            }
        }while (!string.equals("!"));

        System.out.println("=== STACK END ====================================================================\n");

        System.out.println(ANSI_BLUE + "=== Thank you! ===" + ANSI_RESET);
    }


    // ===== PROJECT 1 - LINKED LISTS METHODS ===== //
    private static void displayBag(LinkedInterface<String> aBag)
    {
        sleepTimer(1000);

        System.out.print("[>] The bag now contains " + aBag.getCurrentSize() + " string(s): \t");
        //coping the String generic array object into an object array
        Object[] bagArray = aBag.toArray();
        for (Object bagArray1 : bagArray) {
            System.out.print(ANSI_RED + bagArray1 + " ");
        }
        System.out.println(ANSI_RESET);
    }

    private static void removeCertainElements(LinkedInterface<String> aBag)
    {
        sleepTimer(2000);

        // Removing all occurrences of the given entries from a bag
        System.out.println("[+] Creating... a separate 2D test array with the below contents: \t");
        String[][] testArray =
                {
                        {"A", "A", "A", "A", "A", "A"},
                        {"B", "A", "Bb", "B", "Bb", "B"},
                        {"C", "B", "_", "A"},
                        {"n", "u"}
                };

        for (String[] row : testArray)
        {
            System.out.print("\t\t\t\t\t");
            for (String col : row)
            {
                sleepTimer(100);
                System.out.print(ANSI_RED + col + " ");
            }
            System.out.println("");
        }
        System.out.println(ANSI_RESET);

        sleepTimer(1000);

        aBag.removeAllOccurrences(testArray);
        sleepTimer(2000);
        displayBag(aBag);
    }

    private static void addElements(LinkedInterface<String> aBag)
    {
        // Adding strings
        String[] contentsOfBag = {"A", "_", "_", "Bb", "A", "_", "u", "n",
                "A", "A", "Bb", "l", "A", "H", "o !", "o", "o", "l", "e"};

        System.out.print("[+] Adding.... these items to the bag: \t");
        for (String entry : contentsOfBag)
        {
            sleepTimer(400);

            aBag.add(entry);
            System.out.print(ANSI_RED + entry + " ");
        }
        System.out.println(ANSI_RESET);

        displayBag(aBag);
    }


    // ===== PROJECT 2 - STACK ===== //
    private static boolean isPalindrome(String string)
    {
        Stacky<Character> ourStack = new Stacky<>();

        //converting string to lowercase and deleting all punctuation
        string = string.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("\\s", "");

        /* pushing each character into the stack; the string is being pushed on in the fashion that the first
         * character in the string will be on the bottom of the stack. Last character will be on the top of the stack */
        for (int x = 0; x < string.length(); x++)
        {
            ourStack.push(string.charAt(x));
        }

        //assuming that it is not a palindrome
        boolean equalFlag = false;
        if (ourStack.isEqual())
            equalFlag = true;

        // true == palindrome /  false == not
        return equalFlag;
    }


    //SLEEP TIMER function
    private static void sleepTimer(int time)
    {
        try {        //sleep must be surrounded with try{}catch{} in case it is interrupted
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    //CONTINUING TO NEXT project checker
    private static boolean nextProject(String project)
    {
        Scanner input = new Scanner(System.in);

        System.out.print(ANSI_YELLOW + "To continue to " + project +" PROJECT, press any, or 1 to quit:  ");
        String forward = input.nextLine();
        System.out.println(ANSI_RESET);

        if(forward.equals("1"))
            return false;
        else
            return true;
    }

}











