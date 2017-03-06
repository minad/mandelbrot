/*
 * Mandelbrot.java
 * Geschrieben von Daniel Mendler
 */
import java.applet.Applet;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class Mandelbrot
    extends Applet implements MouseListener, MouseMotionListener {

    private Image   image, backBuffer;
    private int     iterations = 50;
    private Complex start = new Complex(-1.25, -1.25);
    private double  range = 2.5, step, whMax;
    private int     width, height;
    private Point   rectStart = null, rectEnd;

    private static final Color[] palette = {
        Color.YELLOW,
        Color.ORANGE,
        Color.RED,
        Color.PINK,
        Color.MAGENTA,
        Color.GREEN,
        Color.CYAN,
        Color.BLUE,
        Color.BLACK, // Mandelbrotmenge
    };

    public void init() {
        String param = getParameter("iterations");
        if (param != null)
            iterations = Integer.parseInt(param);

        param = getParameter("range");
        if (param != null)
            range = Double.parseDouble(param);

        param = getParameter("start");
        if (param != null) {
            String[] d = param.split(",");
            start.set(Double.parseDouble(d[0]), Double.parseDouble(d[1]));
        }

        width = getWidth();
        height = getHeight();
        whMax = Math.max(width, height);

        step = range / whMax;

        image = createImage(width, height);
        backBuffer = createImage(width, height);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void destroy() {
        removeMouseListener(this);
        removeMouseMotionListener(this);
    }

    public void start() {
        drawMandelbrot();
        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);

        if (rectStart != null) {
            rectEnd.y = rectStart.y + Math.abs(rectEnd.x - rectStart.x)
                       * height / width * (rectEnd.y < rectStart.y ? -1 : 1);
            g.setXORMode(Color.WHITE);
            drawRect(g, rectStart, rectEnd);
	    g.setPaintMode();
        }
    }

    public void update(Graphics g) {
        paint(backBuffer.getGraphics());
        g.drawImage(backBuffer, 0, 0, this);
    }

    private void drawRect(Graphics g, Point min, Point max) {
        g.drawLine(min.x, min.y, max.x, min.y);
        g.drawLine(min.x, max.y, max.x, max.y);
        g.drawLine(min.x, min.y, min.x, max.y);
        g.drawLine(max.x, min.y, max.x, max.y);
    }

    private void drawMandelbrot() {
        Graphics g = image.getGraphics();
        Complex c = start;
        for (int y = 0; y < height; ++y) {
            int lastX = 0, color = 0, lastColor = 0;
            for (int x = 0; x < width; ++x) {
                int iter = mandelbrot(c, iterations);
                if (iter < 0)
                    color = palette.length - 1;
                else
                    color = mandelbrot(c, iterations) % (palette.length - 1);
                if (color != lastColor) {
                    g.setColor(palette[lastColor]);
                    g.drawLine(lastX, y, x - 1, y);
                    lastColor = color;
                    lastX = x;
                }
                c = c.add(step);
            }
            g.setColor(palette[color]);
            g.drawLine(lastX, y, width - 1, y);
            c.real(start.real());
            c = c.addImag(step);
        }
    }

    private int mandelbrot(Complex c, int maxIter) {
        /*
        Complex z = c;
        int n = 0;
        while (n < maxIter) {
            if (z.abs() > 2)
                return n; // Wahrscheinlich divergent
            z = z.multiply(z).add(c);
            ++n;
        }
        return -1; // Wahrscheinlich konvergent
        */
        
        // Optimized
        double zr = c.real(), zi = c.imag(),
               cr = c.real(), ci = c.imag();
        int n = 0;
        while (n < maxIter) {
            if (zr * zr + zi * zi > 4)
                return n; // Wahrscheinlich divergent
            double tmp = 2 * zr * zi + ci;
            zr = zr * zr - zi * zi + cr;
            zi = tmp;
            ++n;
        }
        return -1; // Wahrscheinlich konvergent
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == e.BUTTON1 && rectStart != null)
        {
            if (rectEnd.x < rectStart.x)
                start = start.add(rectEnd.x / whMax * range);
            else
                start = start.add(rectStart.x / whMax * range);

            if (rectEnd.y < rectStart.y)
                start = start.addImag(rectEnd.y / whMax * range);
            else
                start = start.addImag(rectStart.y / whMax * range);

	    iterations *= 2;
            range = range / whMax * Math.abs(rectStart.x - rectEnd.x);
            step = range / whMax;
	    
            rectStart = null;
            drawMandelbrot();
            repaint();
        }
        else if (e.getButton() == e.BUTTON3)
        {
            start = start.add(e.getX() / whMax * range - range).addImag(e.getY() / whMax * range - range);
            range *= 2;
            step = range / whMax;
            drawMandelbrot();
            repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {}
    
    public void mouseDragged(MouseEvent e) {
        if ((e.getModifiersEx() & e.BUTTON1_DOWN_MASK) == 0) {
            rectStart = null;
            return;
        }

        if (rectStart == null)
            rectEnd = rectStart = e.getPoint();
        else
            rectEnd = e.getPoint();
        repaint();
    }
}
