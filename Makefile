JFLAGS = -g -classpath ".:java-cup-11a.jar"
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		InstructionCode.java \
		Immediate.java \
		InstructionPrettyFormatter.java \
		IFormatters.java \
		ImmProcessors.java \
        Instruction.java \
		IInstruction.java \
        RInstruction.java \
		Label.java \
		DataDecl.java \
		DataSection.java \
		TextSection.java \
		MipsAbstractSyntax.java \
		MipsParser.java \
		MipsLexer.java \
		MipsAssembler.java

default: classes

classes: MipsParser.java MipsLexer.java $(CLASSES:.java=.class)

MipsParser.java: MipsParser.cup
	java -jar java-cup-11a.jar -interface -parser MipsParser MipsParser.cup

MipsLexer.java: MipsLexer.flex
	jflex MipsLexer.flex

clean:
	$(RM) *.class
	$(RM) MipsParser.java sym.java MipsLexer.java

