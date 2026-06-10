# Setup Instructions

## Required Java Version
- JDK 17

## Installing Java 17
1. Download JDK 17 from a trusted source such as:
   - https://adoptium.net
   - https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
2. Choose the installer for your operating system (Windows x64).
3. Run the installer and follow the prompts.
4. Note the installation directory, for example: `C:\Program Files\Java\jdk-17`.

## Configure PATH Variable
1. Open the Start menu and search for `Environment Variables`.
2. Select `Edit the system environment variables`.
3. In the System Properties window, click `Environment Variables...`.
4. Under `System variables`, select `Path` and click `Edit`.
5. Click `New` and add the `bin` folder of your JDK installation, for example:
   - `C:\Program Files\Java\jdk-17\bin`
6. Click `OK` to save and close all dialogs.
7. Open a new terminal or command prompt and verify with:

```bash
java -version
javac -version
```

## Running a "Hello World" Program
1. Create a Java source file named `HelloWorld.java`.
2. Add the following code:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

3. Compile the program using:

```bash
javac HelloWorld.java
```

4. Run the compiled class with:

```bash
java HelloWorld
```

### Expected Output
- The console should display:

```
Hello, World!
```

### Notes
- If using an IDE, create a Java project with JDK 17 configured, add the `HelloWorld` class, then use the IDE's run command to execute it.
- A screenshot of the program run would typically show the source file in the editor and the console output with `Hello, World!` displayed.