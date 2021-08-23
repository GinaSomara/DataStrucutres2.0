import java.util.Stack;

public class Stacky<T> implements StackInterface<T> {

    //
    //Instance variables
    //
    Stack<T> stackPalindrome;

    //
    //Constructors
    //
    public Stacky()   //overriding default
    {
        stackPalindrome = new Stack<T>();
    }

    //
    //Methods
    //
    public boolean isEqual()
    {  
        /* creating temp stack to store reverse of stackPalindrome,
         * if still equal to original stack, then will return true : true = yes, it is a Palindrome */

        // copying original stack object, *copy needed since we are popping off these elements, once popped we can no longer access
        Stack<T> originalPalindrome = (Stack<T>)this.stackPalindrome.clone();
        //creating new object to store reverse T (string, int, etc)
        Stack<T> reversePalindrome = new Stack<T>();

        while(!originalPalindrome.empty())
        {
            /* popping off top items, **these stack items are store with the last character from the user string being on the
            *  top of the stack. reversePalindrome obj will have first character in user string stored at top */
            reversePalindrome.push(originalPalindrome.pop());
        }

        //assuming not a palindrome
        boolean equalFlag = false;

        //comparing stacks (one has first character of user string at bottom, other has it at the top. IF both stack == then palindrome)
        if (this.stackPalindrome.equals(reversePalindrome))
            equalFlag = true;

        return equalFlag;
    }

    @Override  //all overrides from StackInterface
    public void push(T newEntry)
    {
        this.stackPalindrome.push(newEntry);
    }

    @Override
    public T peek()
    {
        return this.stackPalindrome.peek();
    }

    @Override
    public T pop()
    {
        return this.stackPalindrome.pop();
    }

    @Override
    public boolean isEmpty()
    {
        boolean flag = false;
        if(this.stackPalindrome.empty())
            flag = true;

        return flag;
    }

    @Override
    public void clear()
    {
        while(!this.stackPalindrome.empty())
            this.stackPalindrome.pop();
    }
}