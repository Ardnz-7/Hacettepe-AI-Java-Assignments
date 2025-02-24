import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> stackData = FileHandler.readFromFile("stack.txt");
        List<Integer> queueData = FileHandler.readFromFile("queue.txt");


        try {
            Scanner commandScanner = new Scanner(new File("command.txt"));
            MyStack stack = new MyStack();
            MyQueue queue = new MyQueue();

            stack.pushList(stackData);
            queue.enqueueList(queueData);


            while (commandScanner.hasNextLine()) {
                String command = commandScanner.nextLine();
                String[] parts = command.split(" ");

                if (parts[0].equals("S")) {
                    // Stack operations
                    if (parts[1].equals("removeGreater")) {
                        int k = Integer.parseInt(parts[2]);
                        StackOperations.removeGreater(stack, k);

                    } else if (parts[1].equals("calculateDistance")) {
                        System.out.println("After calculateDistance:");
                        StackOperations.calculateDistance(stack);

                    } else if (parts[1].equals("addOrRemove")) {
                        int k = Integer.parseInt(parts[2]);
                        StackOperations.addOrRemove(stack, k);

                    } else if (parts[1].equals("reverse")) {
                        int k = Integer.parseInt(parts[2]);
                        StackOperations.reverse(stack, k);

                    } else if (parts[1].equals("sortElements")) {
                         StackOperations.sortElements(stack);

                    } else if (parts[1].equals("distinctElements")) {
                        StackOperations.distinctElements(stack);

                    }

                } else if (parts[0].equals("Q")) {
                    // Queue operations
                    if (parts[1].equals("removeGreater")) {
                        int k = Integer.parseInt(parts[2]);
                        QueueOperations.removeGreater(queue, k);

                    } else if (parts[1].equals("calculateDistance")) {
                        QueueOperations.calculateDistance(queue);

                    } else if (parts[1].equals("addOrRemove")) {
                        int k = Integer.parseInt(parts[2]);
                        QueueOperations.addOrRemove(queue, k);

                    } else if (parts[1].equals("reverse")) {
                        int k = Integer.parseInt(parts[2]);
                        QueueOperations.reverse(queue, k);

                    } else if (parts[1].equals("sortElements")) {
                        QueueOperations.sortElements(queue);

                    } else if (parts[1].equals("distinctElements")) {
                        QueueOperations.distinctElements(queue);

                    }
                }

            }

            commandScanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}