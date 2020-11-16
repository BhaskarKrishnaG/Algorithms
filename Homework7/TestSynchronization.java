package Homework7;

public class TestSynchronization extends Thread {

    private StorageInterface<String> s;

    public TestSynchronization(StorageInterface<String> s) {
        this.s = s;
    }

    public void run(){
        System.out.println("add(null) " + s.add(null));
        System.out.println("add(null) " + s.add(null));
        System.out.println("add(3) " + s.add("3"));
        System.out.println("add(1) " + s.add("1"));
        System.out.println("add(2) " + s.add("2"));
        System.out.println("add(2) " + s.add("2"));
    }

    public static void main(String[] args) {
        StorageInterface<String> a = new SortedStorage<String>();
        TestSynchronization t1 = new TestSynchronization(a);
        TestSynchronization t2 = new TestSynchronization(a);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
