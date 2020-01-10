import java.io.IOException;

public class Controller extends  Thread{

    public Controller(String name){}
    @Override
    public void run() {

        if(Thread.currentThread().getName().equals("jsonRead")){
            JSONIHandler jsonHandler = new JSONIHandler();
            jsonHandler.read();
        }
        if(getName()=="jsonWrite"){
            JSONIHandler jsonHandler = new JSONIHandler();
            jsonHandler.write();
        }
        if(getName()=="csvRead"){
            CSVFileIHandler csvFileHandler =new CSVFileIHandler();

                csvFileHandler.read();

        }
        if(getName()=="csvWrite"){
            CSVFileIHandler csvFileHandler =new CSVFileIHandler();
            try {
                csvFileHandler.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(getName()=="xmlRead"){
            XMLFileIHandler xmlFileHandler = new XMLFileIHandler();
            xmlFileHandler.read();
        }
        if(getName()=="xmlWrite"){
            XMLFileIHandler xmlFileHandler = new XMLFileIHandler();
            xmlFileHandler.write();
        }
    }

    public static void main(String[] args) {
        Controller jsonRead = new Controller("jsonRead");
        jsonRead.setName("jsonRead");
        jsonRead.start();
        Controller csvRead = new Controller("csvRead");
        csvRead.setName("csvRead");
        csvRead.start();
        Controller xmlRead = new Controller("xmlRead");
        xmlRead.setName("xmlRead");
        xmlRead.start();
        try {
            jsonRead.join();
            csvRead.join();
            xmlRead.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Controller jsonWrite = new Controller("jsonWrite");
        jsonWrite.setName("jsonWrite");
        jsonWrite.start();

        Controller csvWrite = new Controller("csvWrite");
        csvWrite.setName("csvWrite");
        csvWrite.start();

        Controller xmlWrite = new Controller("xmlWrite");
        xmlWrite.setName("xmlWrite");
        xmlWrite.start();

        try {
            jsonWrite.join();
            csvWrite.join();
            xmlWrite.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
