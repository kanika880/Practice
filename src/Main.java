public class Main {

    public static void main(String[] args) {
        CustomLinkedList cll = new CustomLinkedList();
        cll.insertFirst(1);
        cll.insertFirst(19);
        cll.insertFirst(2);
        cll.insertFirst(5);
        cll.insertFirst(8);
        cll.insertLast(99);
        cll.insert(100, 3);
        cll.display();
        System.out.println(cll.deleteFirst());
        cll.display();
        System.out.println(cll.deleteLast());
        cll.display();
        System.out.println(cll.delete(2));
        cll.display();
    }
}
