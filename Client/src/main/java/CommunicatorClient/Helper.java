package CommunicatorClient;
import java.util.Timer;
import java.util.TimerTask;

public class Helper extends TimerTask {

    public static int i = 0;

    public void run() {
        System.out.println("Timer ran " + ++i);
    }

}
