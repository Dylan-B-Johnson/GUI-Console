# GUI-Console
A simple package that allows for the creation of command-line Java apps that run outside a console. (By default Java hides the console when running a jar file, so normal command line programs can't be used as a runnable jar without modifying the user's Java runtime settings).

![](https://github.com/Dylan-B-Johnson/GUI-Console/blob/main/readmeAssets/example.png?raw=true)

Here is an example console window. The following code (and the user's entering of "response") produced this output:
```
Console console = new Console();
console.input("<prompt>");
console.input("<prompt2>");
```
This package uses multithreading to ensure that the GUI is responsive, while the thread that calls an input method waits for the user's input to be returned.
