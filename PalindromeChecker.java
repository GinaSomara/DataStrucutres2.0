import java.util.Scanner;

public class PalindromeChecker {

    private static boolean isPalindrome(String string)
    {
        Stacky<Character> ourStack = new Stacky<Character>();

        //converting string to lowercase and deleting all punctation
        string = string.toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("\\s", "");

        //pushing each character into the stack
        for (int x = 0; x < string.length(); x++) {
            ourStack.push(string.charAt(x));
        }

        //assuming that it is not a palindrome
        boolean equalFlag = false;
        if (ourStack.isEqual())
            equalFlag = true;

        return equalFlag;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("[>>] Enter a string (or a ! to exit): ");
        String string = input.nextLine();

        while (!string.equals("!")) {
            if (isPalindrome(string)) {
                System.out.println(" [+] Yes. \"" + string + "\" IS a palindrome!");
            } else {
                System.out.println(" [-] No. \"" + string + "\" is NOT a palindrome!");
            }
            System.out.print("[>>] Enter a string: ");
            string = input.nextLine();
        }

        System.out.println("[<<] Thank you!");
        //
        // - Do not change the "main" method.
        // - Please ADD CODE to complete implementing the program
        //
    }

}