package orange.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiThreadDebugger {
  static List<Object> list ;
  static int number = 5;

  public static void main(String[] args) throws InterruptedException {
    do {
      list = Collections.synchronizedList(new ArrayList<>());
      Thread thread1 = new Thread(() -> addIfAbsent(number));
      Thread thread2 = new Thread(() -> addIfAbsent(number));
      thread1.start();
      thread2.start();

      addIfAbsent(number);

      thread1.join();
      thread2.join();

      System.out.println("List: " + list);

    } while (list.size() != 3);

  }

  private static void addIfAbsent(int i) {
    if (!list.contains(i)) {
//            use 'THREAD' option at break point to catch both threads and get [number, number] in list
      System.out.println(Thread.currentThread().getName());
      list.add(i);
    }
  }
}
