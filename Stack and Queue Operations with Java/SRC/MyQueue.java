import java.util.List;
public class MyQueue {
    protected static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public String printQueue() {
        StringBuilder stringBuilder = new StringBuilder("");
        Node current = front;

        while (current != null) {
            stringBuilder.append(current.data).append(" ");
            current = current.next;
        }

        return stringBuilder.toString();
    }



    protected Node front;
    protected Node rear;

    MyQueue() {
        this.front = null;
        this.rear = null;
    }

    void enqueueList(List<Integer> dataList) {
        for (int data : dataList) {
            enqueue(data);
        }
    }

    void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    boolean isEmpty() {
        return front == null;
    }

    int size() {
        int count = 0;
        Node current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    boolean contains(int element) {
        MyQueue tempQueue = new MyQueue();
        boolean found = false;

        while (!isEmpty()) {
            int currentElement = dequeue();

            if (currentElement == element) {
                found = true;
            }

            tempQueue.enqueue(currentElement);
        }

        while (!tempQueue.isEmpty()) {
            enqueue(tempQueue.dequeue());
        }

        return found;
    }

}