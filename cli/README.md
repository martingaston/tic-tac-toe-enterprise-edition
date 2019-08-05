# Tic Tac Toe

![tic tac toe](https://media.giphy.com/media/26tk0YANPbTyjyJ4Q/giphy.gif)

## What is this?

An 2-player implementation of Tic Tac Toe in Java, the classic 3x3 grid adventure which supposedly ends in a draw if you know what you're doing. Played on the command line, because command lines are cool.

## How do I play it?

Compiling from sources requires an installation of the JDK - my personal preference is [OpenJDK](https://openjdk.java.net/), with this code being built on JDK 12.

### Mac

#### Install Java

1.  Download OpenJDK for Mac OSX from  [http://jdk.java.net/](http://jdk.java.net/)
2.  Unarchive the OpenJDK tar (`sudo tar xzf ~/PATH_TO_JDK_FILE.tar.gz`) and place (`mv`) the extracted folder (i.e. `jdk-12.jdk`) into  `/Library/Java/JavaVirtualMachines/`  folder, which is the expected location of JDK installs.
3. A quick `java --version` should give you the confidence that you're up and running.

### Run the project with Gradle

1. `cd` to your project directory and input `./gradlew test` to make sure everything is installed OK.
2. `./gradlew --console plain run` will run the app.
