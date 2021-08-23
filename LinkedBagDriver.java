public class LinkedBagDriver
{
    public static void main(String[] args)
    {
        System.out.println("=== Linked Bag Project ==========================================================");
        System.out.println("[+] Creating a LinkedBag...");
        /* since we cannot directly instatiate interfaces, we are using the LinkedBag class for the implementation, this limits
         *  the LinkedBag to only the methods inherited from the Interface on the left*/
        LinkedInterface<String> csc220Bag = new LinkedBag<>();
        // passing our new object to
        addElements(csc220Bag);
        testRemoveAllOccurrences(csc220Bag);
        System.out.println("=== LINKED BAG ==========================================================");
    }

    private static void displayBag(LinkedInterface<String> aBag)
    {
        System.out.print("[>] The bag now contains " + aBag.getCurrentSize() + " string(s): \t");
        //coping the String generic array object into an object array
        Object[] bagArray = aBag.toArray();
        for (Object bagArray1 : bagArray) {
            System.out.print(bagArray1 + " ");
        }
        System.out.println();
    }

    private static void testRemoveAllOccurrences(LinkedInterface<String> aBag)
    {
        // Removing all occurrences of the given entries from a bag
        System.out.println("[+] Creating... a 2D test array with the below contents: \t");
        String[][] testArray =
                {
                        {"A", "A", "A", "A", "A", "A"},
                        {"B", "A", "Bb", "B", "Bb", "B"},
                        {"C", "B", "_", "A"},
                        {"n", "u", "l", "l"}
                };

        for (String[] row : testArray) {
            System.out.print("\t\t\t\t\t");
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println("");
        }

        aBag.removeAllOccurrences(testArray);
        displayBag(aBag);
    }

    private static void addElements(LinkedInterface<String> aBag)
    {
        // Adding strings
        String[] contentsOfBag = { "A", "_", "_", "G", "Bb", "A", "_", "u", "n",
                "o", "A", "o", "d", "Bb", "A", "A", "l", "l"};

        System.out.print("[+] Adding.... these items to the bag: \t");
        for (String entry : contentsOfBag) {
            aBag.add(entry);
            System.out.print(entry + " ");
        }
        System.out.println();

        displayBag(aBag);
    }
}
