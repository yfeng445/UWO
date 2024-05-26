import java.lang.ProcessBuilder;

public class RunCommand {

    public  RunCommand() {
    }

    public void run (String command) {
	try {
	    String args[] = command.split(" ");
	    ProcessBuilder pb = new ProcessBuilder(args);
	    Process p = pb.start();
	}
	catch (Exception e) {
	    System.out.println("Error executing command: "+e.getMessage());
	}
    }

}