import edu.wxu.pojo.Student;
import edu.wxu.views.*;

public class TestViews {
    public static void main(String[] args) throws Exception {
        //new LoginJFrame();
        //new AdminJFrame(null);
        //new AddWorkerJFrame();
        //new AddStudentJFrame(null);
        //new WorkerJFrame(null);
        //new AddBookJFrame(null);
        //new AddRecordJFrame(null);
        Student student = new Student(1,"吕林","123456", (short) 0,20);
        new StudentJFrame(student);
    }
}
