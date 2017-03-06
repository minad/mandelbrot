@echo off
javac -g:none -deprecation *.java
jar cvfm Mandelbrot.jar Mandelbrot.mf *.class
del *.class
start