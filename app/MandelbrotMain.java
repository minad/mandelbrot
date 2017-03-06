import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame;

public class MandelbrotMain {

    public static void main(String[] args) {
        Frame frame = new Frame("Mandelbrot");
        frame.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent event) {
                    System.exit(0);
                }
            }
        );
        frame.add(new MandelbrotComponent());
        frame.setSize(600, 500);
        frame.setVisible(true);
    }
}