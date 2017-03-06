/**
 * Ein Objekt der Klasse <code>Complex</code> repräsentiert eine komplexe Zahl.
 * Es werden die grundlegenden Rechenoperationen für komplexe Zahlen unterstützt.
 *
 * @author Daniel Mendler
 * @version 0.3
 */
public class Complex {
    private double real, imag;

    /**
     * Der Standard-Konstruktor initialisiert die komplexe Zahl mit 0 + 0i.
     */
    public Complex() {
        zero();
    }

    /**
     * Die komplexe Zahl wird mit der komplexen Zahl <code>z</code> initialisiert.
     *
     * @param z Die komplexe Zahl, mit der initialisiert wird
     */
    public Complex(Complex z) {
        set(z);
    }

    /**
     * Die komplexe Zahl wird mit der reellen Zahl <code>real</code> initalisiert.
     *
     * @param real Die reelle Zahl, mit der initialisiert wird
     */
    public Complex(double real) {
        set(real, 0);
    }

    /**
     * Der reelle und der imaginäre Teil der komplexen Zahl werden initialisiert.
     *
     * @param real Der reelle Teil der komplexen Zahl
     * @param imag Der imaginäre Teil der komplexen Zahl
     */
    public Complex(double real, double imag) {
        set(real, imag);
    }

    /**
     * Die komplexe Zahl wird auf den Wert 0 + 0i gesetzt.
     */
    public void zero() {
        set(0, 0);
    }

    /**
     * Die komplexe Zahl wird auf den Wert der komplexen Zahl <code>z</code> gesetzt.
     *
     * @param z Die komplexe Zahl
     */
    public void set(Complex z) {
        set(z.real, z.imag);
    }

    /**
     * Der reelle und der imaginäre Teil der komplexen Zahl wird gesetzt.
     *
     * @param real Der rellee Teil der komplexen Zahl
     * @param imag Der imaginäre Teil der komplexen Zahl
     */
    public void set(double real, double imag) {
        real(real);
        imag(imag);
    }

    /**
     * Der reelle Teil der komplexen Zahl wird zurückgegeben.
     *
     * @return Der reelle Teil der komplexen Zahl
     */
    public double real() {
        return real;
    }

    /**
     * Der reelle Teil der komplexen Zahl wird gesetzt.
     *
     * @param real Der reelle Teil der komplexen Zahl
     */
    public void real(double real) {
        this.real = real;
    }

    /**
     * Der imaginäre Teil der komplexen Zahl wird zurückgegeben.
     *
     * @return Der imaginäre Teil der komplexen Zahl
     */
    public double imag() {
        return imag;
    }

    /**
     * Der imaginäre Teil der komplexen Zahl wird gesetzt.
     *
     * @param imag Der imaginäre Teil der komplexen Zahl
     */
    public void imag(double imag) {
        this.imag = imag;
    }

    /**
     * Der Betrag der komplexen Zahl wird zurückgegeben.
     *
     * @return Der Betrag
     */
    public double abs() {
        return Math.sqrt(real * real + imag * imag);
    }

    /**
     * Die konjugierte komplexe Zahl wird zurückgegeben.
     *
     * @return Die konjugierte komplexe Zahl
     */
    public Complex conjugate() {
        return new Complex(real, -imag);
    }

    /**
     * Die Negation der komplexe Zahl wird zurückgegeben.
     *
     * @return Die Negation der komplexen Zahl
     */
    public Complex negate() {
        return new Complex(-real, -imag);
    }

    /**
     * Die komplexe Zahl wird mit der komplexen Zahl <code>z</code> addiert.
     *
     * @param z Die komplexe Zahl, die addiert werden soll
     * @return Die Summe dieser Zahl und der komplexen Zahl <code>z</code>
     */
    public Complex add(Complex z) {
        return new Complex(real + z.real, imag + z.imag);
    }

    /**
     * Die komplexe Zahl wird mit der reellen Zahl <code>real</code> addiert.
     *
     * @param real Die reelle Zahl, die addiert werden soll
     * @return Die Summe dieser Zahl und der reellen Zahl <code>real</code>
     */
    public Complex add(double real) {
        return new Complex(this.real + real, imag);
    }

    /**
     * Die komplexe Zahl wird mit der imaginären Zahl <code>imag</code> addiert.
     *
     * @param imag Die imaginäre Zahl, die addiert werden soll
     * @return Die Summe dieser Zahl und der imaginären Zahl <code>imag</code>
     */
    public Complex addImag(double imag) {
        return new Complex(real, this.imag + imag);
    }

    /**
     * Die komplexe Zahl <code>z</code> wird von der komplexen Zahl subtrahiert.
     *
     * @param z Die komplexe Zahl, die subtrahiert werden soll
     * @return Die Differenz dieser Zahl und der komplexen Zahl <code>z</code>
     */
    public Complex subtract(Complex z) {
        return new Complex(real - z.real, imag - z.imag);
    }

    /**
     * Die reelle Zahl <code>real</code> wird von der komplexen Zahl subtrahiert.
     *
     * @param real Die reelle Zahl, die subtrahiert werden soll
     * @return Die Differenz dieser Zahl und der reellen Zahl <code>real</code>
     */
    public Complex subtract(double real) {
        return new Complex(this.real - real, imag);
    }

    /**
     * Die imaginäre Zahl <code>imag</code> wird von der komplexen Zahl subtrahiert.
     *
     * @param imag Die reelle Zahl, die subtrahiert werden soll
     * @return Die Differenz dieser Zahl und der imaginären Zahl <code>imag</code>
     */
    public Complex subtractImag(double imag) {
        return new Complex(real, this.imag - imag);
    }

    /**
     * Die komplexe Zahl wird mit der komplexen Zahl <code>z</code> multipliziert.
     *
     * @param z Die komplexe Zahl, die mit dieser Zahl multipliziert werden soll
     * @return Das Produkt dieser Zahl und der komplexen Zahl <code>z</code>
     */
    public Complex multiply(Complex z) {
        return new Complex(real * z.real - imag * z.imag, real * z.imag + imag * z.real);
    }

    /**
     * Die komplexe Zahl wird mit der reellen Zahl <code>real</code> multipliziert.
     *
     * @param real Die reelle Zahl, die mit dieser Zahl multipliziert werden soll
     * @return Das Produkt dieser Zahl und der reellen Zahl <code>real</code>
     */
    public Complex multiply(double real) {
        return new Complex(this.real * real, imag * real);
    }

    /**
     * Die komplexe Zahl wird mit der imaginären Zahl <code>imag</code> multipliziert.
     *
     * @param image Die imaginäre Zahl, die mit dieser Zahl multipliziert werden soll
     * @return Das Produkt dieser Zahl und der imaginären Zahl <code>imag</code>
     */
    public Complex multiplyImag(double imag) {
        return new Complex(-this.imag * imag, real * imag);
    }

    /**
     * Die komplexe Zahl wird durch die komplexe Zahl <code>z</code> dividiert.
     *
     * @param z Die komplexe Zahl, durch die dividiert werden soll
     * @return Der Quotient dieser Zahl durch die komplexen Zahl <code>z</code>
     */
    public Complex divide(Complex z) {
        double x = z.real * z.real + z.imag * z.imag;
        return new Complex((real * z.real + imag * z.imag) / x, (imag * z.real - real * z.imag) / x);
    }

    /**
     * Die komplexe Zahl wird durch die reelle Zahl <code>real</code> dividiert.
     *
     * @param real Die reelle Zahl, durch die dividiert werden soll
     * @return Der Quotient dieser Zahl durch die reelle Zahl <code>real</code>
     */
    public Complex divide(double real) {
        return new Complex(this.real / real, imag / real);
    }

    /**
     * Die komplexe Zahl wird durch die imaginäre Zahl <code>imag</code> dividiert.
     *
     * @param imag Die imaginäre Zahl, durch die dividiert werden soll
     * @return Der Quotient dieser Zahl durch die imaginäre Zahl <code>imag</code>
     */
    public Complex divideImag(double imag) {
        return new Complex(this.imag / imag, -real / imag);
    }

    /**
     * Es wird geprüft, ob der Betrag der komplexen Zahl unendlich groß oder klein ist.
     * Der Wert unendlich ist definiert als 1.0 / 0.0 bzw. -1.0 / 0.0.
     *
     * @return true, wenn der Betrag der komplexen Zahl unendlich groß oder klein ist
     * @see java.lang.Double#isInfinite()
     */
    public boolean isInfinite() {
        /* Strebt der imaginäre oder der relle Teil der komplexen Zahl gegen unendlich,
         * so ist auch der Betrag unendlich groß.
         */
        return (Double.isInfinite(real) || Double.isInfinite(imag));
    }

    /**
     * Es wird geprüft, ob ein Teil der komplexen Zahl den Wert NaN hat.
     * Der Wert NaN ist definiert als 0.0 / 0.0.
     *
     * @return true, wenn ein Teil der komplexen Zahl den Wert NaN hat
     * @see java.lang.Double#isNaN()
     */
    public boolean isNaN() {
        return (Double.isNaN(real) || Double.isNaN(imag));
    }

    /**
     * Die komplexe Zahl wird mit dem Objekt <code>obj</code> auf Gleichheit geprüft.
     *
     * @param obj Das Objekt, mit dem verglichen werden soll.
     * @return true, falls das Objekt <code>obj</code> den Typ <code>Complex</code> hat
               und die beiden komplexen Zahlen gleich sind
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object obj) {
        return (obj instanceof Complex)
            && (real == ((Complex)obj).real && imag == ((Complex)obj).imag);
    }

    /**
     * Die Stringrepräsentation der komplexen Zahl wird zurückgegeben.
     * Beispiele: (7.5 + 12.2i); (-99.0 - 1.2i)
     *
     * @return Die Stringrepräsentation der komplexen Zahl
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "(" + real + (imag < 0 ? (" - " + (-imag)) : (" + " + imag)) + "i)";
    }

    /**
     * Diese Methode ist der Einsprungspunkt dieser Klasse für die JVM.
     * Sie dient dazu, die Klasse zu testen und
     * sollte von keiner Klasse aufgerufen werden.
     */
    public static void main(String[] args) {
        Complex a = new Complex(10, 10),
                b = new Complex(5, -5);

        System.out.println(
            "Standardoperationen:\n" +
            a + " + " + b + " = " + a.add(b) + '\n' +
            a + " - " + b + " = " + a.subtract(b) + '\n' +
            a + " * " + b + " = " + a.multiply(b) + '\n' +
            a + " / " + b + " = " + a.divide(b) + '\n' +
            "--------------------------------------------------\n" +
            "Negation, Konjugation und Betrag:\n" +
            "-" + a + " = " + a.negate() + '\n' +
            "conj" + a + " = " + a.conjugate() + '\n' +
            '|' + a + "| = " + a.abs() + '\n' +
            "--------------------------------------------------\n" +
            "Gleichheit:\n" +
            a + (a.equals(b) ? " == " : " != ") + b + '\n' +
            a + (a.equals(a) ? " == " : " != ") + a
        );
    }
}
