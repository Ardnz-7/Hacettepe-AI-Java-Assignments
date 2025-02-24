import java.util.*;

public class QueueOperations {
    static void removeGreater(MyQueue queue, int k) {

        MyQueue tempQueue = new MyQueue();


        while (!queue.isEmpty()) {
            int currentElement = queue.dequeue();
            if (currentElement <= k) {
                tempQueue.enqueue(currentElement);
            }
        }


        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }


        String output = "After removeGreater " + k + ": "+ "\n" + queue.printQueue() +"\n";
        FileHandler.writeToFile("queueOut.txt", output);
        System.out.println(output);
        queue.printQueue();
    }

    static void calculateDistance(MyQueue queue) {
        MyQueue tempQueue = new MyQueue();
        int sum = 0;

        MyQueue copyQueue = new MyQueue();
        while (!queue.isEmpty()) {
            int currentElement = queue.dequeue();
            copyQueue.enqueue(currentElement);
        }

        while (!copyQueue.isEmpty()) {
            int currentElement = copyQueue.dequeue();

            MyQueue.Node current = copyQueue.front;
            while (current != null) {
                sum += Math.abs(currentElement - current.data);
                current = current.next;
            }

            tempQueue.enqueue(currentElement);
        }

        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

        String output = "After calculateDistance: " +"\n" + "Total distance="  +"\n";
        FileHandler.writeToFile("queueOut.txt", output);
        System.out.println(output);
        System.out.println("Sum of distances in queue: " + sum +"\n");
    }


    static void addOrRemove(MyQueue queue, int k) {

        int a = k;
        if (k > 0) {

            Random random = new Random();
            for (int i = 0; i < k; i++) {
                queue.enqueue(random.nextInt(51));
            }
        } else if (k < 0) {

            k = Math.abs(k);
            for (int i = 0; i < k && !queue.isEmpty(); i++) {
                queue.dequeue();
            }
        }

        String output = "After addOrRemove " + a + ":"+"\n" + queue.printQueue() +"\n";
        FileHandler.writeToFile("queueOut.txt", output);
        System.out.println(output);
        queue.printQueue();
    }

    static void reverse(MyQueue queue, int k) {
        if (k <= 0 || k > queue.size()) {
            System.out.println("Invalid value of k for reverse operation");
            return;
        }

        MyQueue tempQueue = new MyQueue();
        Stack<Integer> stack = new Stack<>();


        for (int i = 0; i < k; i++) {
            int element = queue.dequeue();
            stack.push(element);
        }


        while (!stack.isEmpty()) {
            tempQueue.enqueue(stack.pop());
        }


        while (!queue.isEmpty()) {
            tempQueue.enqueue(queue.dequeue());
        }


        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }


        String output = "After reverse " + k +":\n" + queue.printQueue() +"\n";
        FileHandler.writeToFile("queueOut.txt", output);
        System.out.println(output);
        queue.printQueue();
    }


    static void sortElements(MyQueue queue) {

        List<Integer> sortedList = new ArrayList<>();
        while (!queue.isEmpty()) {
            sortedList.add(queue.dequeue());
        }
        Collections.sort(sortedList);
        for (int element : sortedList) {
            queue.enqueue(element);
        }

        String output = "After sortElements: " +"\n" + queue.printQueue() +"\n";
        FileHandler.writeToFile("queueOut.txt", output);
        System.out.println(output);
        queue.printQueue();
    }

    static int distinctElements(MyQueue queue) {
        int count = 0;
        MyQueue tempQueue = new MyQueue();

        while (!queue.isEmpty()) {
            int currentElement = queue.dequeue();


            if (!tempQueue.contains(currentElement)) {
                count++;
            }

            tempQueue.enqueue(currentElement);
        }


        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

        String output = "After distinctElements:" + "\n" +"Total distinct element=" + count +"\n";
        FileHandler.writeToFile("queueOut.txt", output);
        System.out.println(count);
        return  count;
    }
}