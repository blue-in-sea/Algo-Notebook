class LinkedList {
    Node head;

    // Method to delete a specific node from the list
    public void deleteNode(Node nodeToDelete) {
        Node current = head;
        Node previous = null;

        // If the node to delete is the head node
        if (head == nodeToDelete) {
            head = nodeToDelete.next;
            return;
        }

        // Traverse the list to find the node to delete
        while (current != null && current != nodeToDelete) {
            previous = current;
            current = current.next;
        }

        // If the node was found, unlink it
        if (current == nodeToDelete) {
            previous.next = current.next;
        }
    }

    // Method to add a new node at the end of the list
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Method to display the list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println("Original List:");
        list.display();

        // Assume we want to delete the node with data 3
        Node nodeToDelete = list.head.next.next; // This would be the node with data 3
        list.deleteNode(nodeToDelete);
        System.out.println("List after deleting node with data 3:");
        list.display();

        // Assume we want to delete the head node
        nodeToDelete = list.head; // This would be the head node
        list.deleteNode(nodeToDelete);
        System.out.println("List after deleting the head node:");
        list.display();
    }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
