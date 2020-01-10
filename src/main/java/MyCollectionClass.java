import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MyCollectionClass {
   public static int readCounter=0;
   public static int writeCounter=0;
    private MyCollectionClass() {
    }
    private static MyCollectionClass instance = null;
    private static Object obj = new Object();
    private List<Employee> list = list = new ArrayList<Employee>();
    private Iterator ie = list.iterator();
    public static MyCollectionClass getInstance() {
        synchronized (obj) {
            if (instance == null) {
                instance = new MyCollectionClass();
            }
            return instance;
        }
    }
    private static Object sync = new Object();
    public void add(Employee employee) {

        synchronized (sync) {
            Object obj = new Object();
            list.add(employee);
            writeCounter++;
            System.out.println("Size " + list.size());
        }
    }


    public Employee get(){
        synchronized (sync){
            readCounter++;
            System.out.println("Write " + readCounter);
            return list.get(readCounter-1);
        }
    }
}