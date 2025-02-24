import java.util.List;

public class MyStack {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    void pushList(List<Integer> dataList) {
        for (int data : dataList) {
            push(data);
        }
    }

    private Node top;

    MyStack() {
        this.top = null;
    }

    void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int data = top.data;
        top = top.next;
        return data;
    }


    boolean isEmpty() {
        return top == null;
    }

    public void reverse() {
        if (isEmpty() || size() == 1) {

            return;
        }

        Node prev = null;
        Node current = top;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        top = prev;
    }


    public String printStack() {
        StringBuilder stringBuilder = new StringBuilder("");
        MyStack.Node current = top;

        while (current != null) {
            stringBuilder.append(current.data).append(" ");
            current = current.next;
        }

        return stringBuilder.toString();
    }



    int size() {
        int count = 0;
        Node current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    boolean contains(int element) {
        MyStack tempStack = new MyStack();
        boolean found = false;

        while (!isEmpty()) {
            int currentElement = pop();
            tempStack.push(currentElement);

            if (currentElement == element) {
                found = true;
            }
        }

        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }

        return  found;
    }
}