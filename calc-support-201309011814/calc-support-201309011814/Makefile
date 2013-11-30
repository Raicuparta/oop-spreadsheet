JAVADIR=/usr/share/java
JARNAME=calc-support

all:
	(cd src; javac -cp $(JAVADIR)/po-uilib.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cvf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`
