# Tic Tac Toe

![tic tac toe](https://media.giphy.com/media/26tk0YANPbTyjyJ4Q/giphy.gif)

## What is this?

An 2-player implementation of Tic Tac Toe in Java, the classic 3x3 grid adventure which supposedly ends in a draw if you know what you're doing. Played on the command line, because command lines are cool.

## How do I play it?

Compiling from sources requires an installation of the JDK - my personal preference is [OpenJDK](https://openjdk.java.net/), with this code being built on JDK 12.

### Mac

#### Install Java

1.  Download OpenJDK for Mac OSX from  [http://jdk.java.net/](http://jdk.java.net/)
2.  Unarchive the OpenJDK tar (`sudo tar xzf ~/PATH_TO_JDK_FILE.tar.gz`) and place (`mv`) the extracted folder (i.e.  `jdk-12.jdk`) into  `/Library/Java/JavaVirtualMachines/`  folder, which is the expected location of JDK installs.
3. A quick `java --version`

#### Compile and Run

1. From the project root, `cd` into `src/main/java`
2. run `javac *.java`
3. run `java main`

## Sprint 5 (07/05-10/05)

- Pass arguments to CLI
- Add E2E tests
- Save/Resume Functionality
- Add Minimax CPU v Minimax CPU option

## Sprint 4 (29/04-03/05)

- ✅ Fix Messages class to display proper input information on 3X3 and 4X4 grids
- ✅ Refactor functions that require specific order
- ✅ Add unbeatable computer
- ❌Pass arguments to CLI
- ❌Add E2E tests
- ❌Save/Resume Functionality

## Sprint 3 (23/04-26/04)

- ✅ Add 4X4 board
- ✅ Add menu for the size of the board
- ✅ Add Computer V Computer to the menu
- ✅ Pass arguments to CLI

## Sprint 2 (15/04-18/04)

- ✅ Refactor the board from Java primitive array to another data structure
- ✅ Add a CPU opponent
- ✅ Add a menu to select from 2P or VS CPU
- ✅ Add error handling for unexpected input cases

## Sprint 1 (08/04-12/04)

- ✅ Simple instructions for the user should be provided within the application itself
- ✅ The game is played on a standard 3 X 3 board
- ✅ Players are represented by "X" and "O" markers
- ✅ Won or tied games should be identified and game should end

