
public class ProcessID {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();

        System.out.println("Process ID: " + pid);
    }
}