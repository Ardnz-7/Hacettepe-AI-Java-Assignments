import java.util.*;

public class StackOperations {
    static void removeGreater(MyStack stack, int k) {

        MyStack tempStack = new MyStack();

        while (!stack.isEmpty() && stack.peek() > k) {
            tempStack.push(stack.pop());
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        String output = "After removeGreater " + k + ": "+ "\n" + stack.printStack() +"\n";
        FileHandler.writeToFile("stackOut.txt", output);
        System.out.println(output);
        stack.printStack();
    }

    static void calculateDistance(MyStack stack) {
        MyStack tempStack = new MyStack();
        int sum = 0;

        MyStack stackCopy = new MyStack();
        MyStack stackCopy1 = new MyStack();
        while (!stack.isEmpty()) {
            int element = stack.pop();
            stackCopy.push(element);
            stackCopy1.push(element);
        }

        while (!stackCopy1.isEmpty()) {
            int element = stackCopy1.pop();
            stack.push(element);
        }
        stack.reverse();

        while (!stackCopy.isEmpty()) {
            int currentElement = stackCopy.pop();

            MyStack tempStackCopy = new MyStack();
            while (!stackCopy.isEmpty()) {
                int element = stackCopy.pop();
                sum += Math.abs(currentElement - element);
                tempStackCopy.push(element);
            }


            while (!tempStackCopy.isEmpty()) {
                stackCopy.push(tempStackCopy.pop());
            }

            tempStack.push(currentElement);
        }
        String output = "After calculateDistance: "+ "\n" + "Total distance="+ sum +"\n";
        FileHandler.writeToFile("stackOut.txt", output);
        System.out.println(output);
        System.out.println("Total distance=" + sum);
    }


    static void sortElements(MyStack stack) {

        List<Integer> sortedList = new ArrayList<>();

        while (!stack.isEmpty()) {
            sortedList.add(stack.pop());
        }


        Collections.sort(sortedList, Collections.reverseOrder());

        for (int element : sortedList) {
            stack.push(element);
        }

        String output = "After sortElements: "+ "\n" + stack.printStack() +"\n";
        FileHandler.writeToFile("stackOut.txt", output);
        System.out.println(output);
        stack.printStack();
    }


    static void addOrRemove(MyStack stack, int k) {

        int a = k;
        if (k > 0) {
            Random random = new Random();
            for (int i = 0; i < k; i++) {
                stack.push(random.nextInt(51));
            }
        } else if (k < 0) {
            k = Math.abs(k);
            for (int i = 0; i < k && !stack.isEmpty(); i++) {
                stack.pop();
            }
        }
        String output = "After addOrRemove " + a + ": "+ "\n" + stack.printStack() +"\n";
        FileHandler.writeToFile("stackOut.txt", output);
        System.out.println(output);
        stack.printStack();
    }

    static void reverse(MyStack stack, int k) {

        if (k > 0 && k <= stack.size()) {
            MyStack tempStack = new MyStack();

            for (int i = 0; i < k; i++) {
                tempStack.push(stack.pop());
            }
            tempStack.reverse();

            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }

        String output = "After reverse " + k + ": "+ "\n" + stack.printStack() +"\n";
        FileHandler.writeToFile("stackOut.txt", output);
        System.out.println(output);
        stack.printStack();
    }

    static int distinctElements(MyStack stack) {
        int count = 0;
        MyStack tempStack = new MyStack();

        while (!stack.isEmpty()) {
            int currentElement = stack.pop();

            if (!tempStack.contains(currentElement)) {
                tempStack.push(currentElement);
                count++;
            }
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        String output = "After distinctElements" + "\n" +"Total distinct elements = " + count +"\n";
        FileHandler.writeToFile("stackOut.txt", output);
        System.out.println(count);
        return  count;
    }
}