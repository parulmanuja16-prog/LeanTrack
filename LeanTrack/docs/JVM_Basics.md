# JVM Basics

## What is JDK, JRE, JVM?

- JDK stands for Java Development Kit. It is the full kit a developer uses to write and build Java programs. The JDK includes tools like the Java compiler (`javac`) and the Java runtime.
- JRE stands for Java Runtime Environment. It contains what is needed to run Java programs but does not include development tools like the compiler. If you only want to execute Java applications, you need the JRE.
- JVM stands for Java Virtual Machine. It is the engine that runs compiled Java code. The JVM reads Java bytecode and executes it on the computer, so Java programs can run on different devices.

## What is bytecode?

Bytecode is the intermediate code produced when a Java source file is compiled. Instead of turning Java code directly into machine code for one specific computer, the compiler converts it into bytecode. The JVM then reads and runs the bytecode.

## What does “write once, run anywhere” mean?

“Write once, run anywhere” means a Java program can be written and compiled on one platform, and then run on any other platform that has a compatible JVM. Because the JVM handles the differences between operating systems and hardware, the same compiled Java program can work on Windows, macOS, Linux, and other systems without changing the source code.
