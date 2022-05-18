package com.bobocode;

import java.util.Stack;

public class DemoApp {
    public static void main(String[] args) {
        Node<Integer> head = createLinkedList(1, 2, 3, 4, 5);
        printReversedRecursively(head);
        System.out.println();
        printReversedUsingStack(head);
    }

    /**
     * Creates a list of linked {@link Node} objects based on the given array of elements and returns a head of the list.
     *
     * @param elements an array of elements that should be added to the list
     * @param <T>      elements type
     * @return head of the list
     */
    @SafeVarargs
    public static <T> Node<T> createLinkedList(T... elements) {
        Node<T> head = new Node<>(elements[0]);
        Node<T> current = head;
        for (int i = 1; i < elements.length; i++) {
            Node<T> next = new Node<>(elements[i]);
            current.next(next);
            current = next;
        }
        return head;
    }

    /**
     * Prints a list in a reserved order using a recursion technique. Please note that it should not change the list,
     * just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedRecursively(Node<T> head) {
        if (head.next() != null) {
            printReversedRecursively(head.next());
            System.out.print(" -> " + head.value());
        } else {
            System.out.print(head.value());
        }
    }

    /**
     * Prints a list in a reserved order using a {@link java.util.Stack} instance. Please note that it should not change
     * the list, just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedUsingStack(Node<T> head) {
        Stack<Node<T>> nodeStack = new Stack<>();
        Node<T> current = head;
        while (current != null) {
            nodeStack.push(current);
            current = current.next();
        }
        System.out.print(nodeStack.pop());
        while (!nodeStack.empty()) {
            System.out.print(" -> ");
            System.out.print(nodeStack.pop());
        }
    }
}
