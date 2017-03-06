import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Graphics;
import java.awt.Image;

public class MandelbrotComponent extends Component implements ComponentListener, Runnable {

    private double range = 2.5;
    private Complex start = new Complex(-2.0, -1.2);
    private double step;
    private int iterations = 100;
    private Image image = null;
    private boolean imageFinished = false, imageThreadRestart = false;
    private Graphics imageGraph;
    private Thread imageThread = null;

    public MandelbrotComponent() {
        addComponentListener(this);
    }

    public void paint(Graphics graph) {
        if (imageFinished)
            graph.drawImage(image, 0, 0, this);
        else
            graph.clearRect(0, 0, getWidth(), getHeight());
    }

    public void run() {
        Complex c = start;
        for (int y = 0; y < getHeight(); ++y) {
            int lastX = 0, value = 0, lastValue = -1;
            for (int x = 0; x < getWidth(); ++x) {

                if (imageThreadRestart) {
                     imageThreadRestart = false;
                     c = start;
                     x = y = lastX = 0;
                     lastValue = 0;
                }

                value = (int)(Math.min(mandelbrot(c, iterations), 255) / (double)Math.min(iterations, 255) * 255);
                if (value != lastValue) {
                    if (lastValue != -1) {
                        imageGraph.setColor(new Color(lastValue, lastValue, lastValue));
                        imageGraph.drawLine(lastX, y, x - 1, y);
                    }
                    lastValue = value;
                    lastX = x;
                }
                c = c.add(step);
            }
            imageGraph.setColor(new Color(value, value, value));
            imageGraph.drawLine(lastX, y, getWidth() - 1, y);
            c.real(start.real());
            c = c.addImag(step);
        }

        imageFinished = true;
        repaint();
    }

    private int mandelbrot(Complex c, int iterations) {
        Complex z = c;
        while (iterations > 0) {
            if (z.abs() > 2)
                break;
            z = z.multiply(z).add(c);
            --iterations;
        }
        return iterations;
    }

    public void componentResized(ComponentEvent event) {
        step = range / Math.min(getWidth(), getHeight());

        image = createImage(getWidth(), getHeight());
        imageGraph = image.getGraphics();
        imageFinished = false;

        if (imageThread != null && imageThread.isAlive())
            imageThreadRestart = true;
        else {
            imageThread = new Thread(this);
            imageThread.start();
        }
    }

    public void componentMoved(ComponentEvent event) { }
    public void componentShown(ComponentEvent event) { }
    public void componentHidden(ComponentEvent event) { }
}