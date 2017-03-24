#!/bin/sh
javac -cp src -d bin `find -name "*.java"`
cd bin
jar cmf ../META-INF/MANIFEST.MF Miniprojet.jar `find -name "*.class"`
cd ..
mv bin/Miniprojet.jar .
java -jar Miniprojet.jar
