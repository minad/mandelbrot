all: clean
	javac -O *.java
	jar -cvf Mandelbrot.jar *.class
	rm -f *.class

clean:
	rm -f *.jar *.class
