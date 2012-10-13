JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Instruction.java \
        RInstruction.java \
		InstructionCode.java \
		DataItem.java \
		AbstractSyntax.java \
		MipsAssembler.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

