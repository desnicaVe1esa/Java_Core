package collections;

import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        Book book = new Book("Книга");
        Book book1 = new Book("Еще книга");
        Book book2 = new Book("И еще книга");
        BookCompare bookCompare = new BookCompare();
        TreeSet<Book> bookTreeSet = new TreeSet<>(bookCompare);
        bookTreeSet.add(new Book("Книга"));
        bookTreeSet.add(new Book("Еще книга"));
        bookTreeSet.add(new Book("И еще книга"));
        System.out.println(bookTreeSet);
    }
}
