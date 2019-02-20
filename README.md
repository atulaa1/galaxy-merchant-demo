# Merchant Guide To Galaxy Solution

### System Design Approach
The system is designed around the *Chain of Responsibility* design pattern.

Each input from user is treated as a *command* to the program which will be passed to a *series of* processing objects, called *processors*.

Each processor in the chain is responsible for a certain type of command.
When a command is passed to a processor, the processor will first check if the command is supported (means that the processor is responsible to process this command) or not.
If the command is supported, it will be process intermediately by this processor. Otherwise, it will be passed to the next processor in the chain, until reaching to the last processor.

There are 7 types of command (user input) given in `InputType` enum,
so there will be 7 processors which are defined in `*InputProcessor.java` files inside `com.dungpx.galaxy.merchant.processor` package.

`InputProcesorChain` initializes 7 instances of each processors and chain them together to create the processor chain, and assign it to `CHAIN_OF_PROCESSOR` constant.

`Application` use that `InputProcessorChain` to trigger the processing of each input given by user.

### Assumptions
- Each user input is terminated with a new line.
- Questions to be ended with question mark character (?)
- Keyword 'is' is used as a separator in assignment lines.
- In Roman assignment lines, Roman numbers must be written in capitalized form.
- In Credit assignment lines, the word closet before 'is' keyword is used as metal name.
- Enter a blank line to terminate the input process and show results to the console.

### Build & execute instructions
- You need Maven to build the project. Please install it first.
- Open command prompt, change the current working directory to the project directory.
- Once you are in the project directory, run this command:
```
mvn package
```
- Maven will compile and package the application into a jar file named `galaxy-merchant-guide-1.0-SNAPSHOT.jar` inside the `target` folder. To run the application invoke these commands, in order.
```
cd target
java -jar galaxy-merchant-guide-1.0-SNAPSHOT.jar
```