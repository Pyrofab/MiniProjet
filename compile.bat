javac -cp src -d bin -Xlint:unchecked src/*.java
cd bin
jar cvmf0 ..\META-INF\MANIFEST.MF MiniProjet.jar es\esy\ladysnake\gui\*.class es\esy\ladysnake\miniprojet\main\*.class es\esy\ladysnake\miniprojet\display\*.class
cd ..
move bin\MiniProjet.jar .
java -jar MiniProjet.jar