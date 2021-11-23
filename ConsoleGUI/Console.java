//Author: Dylan Johnson
package ConsoleGUI;

import java.util.concurrent.TimeUnit;

/**
 * A class that creates a console window with an input and output box.
 * Includes methods for receiving input from the input box and outputting to the output box.
 */
public class Console {
	private GUI GUI;
	private boolean waitingOnInput, cont, waitingOnPrint, setIcon, setWindowName;
	private String inputPrompt, returnStr, printString, imagePath;
	
	/**
	* Constructs a console window with an input box and output box.
	*/
	public Console() {
		this.GUI = new GUI(this);
		this.waitingOnPrint=false;
		this.GUI.start();
	}
	
	/**
	* Constructs a console window with an input box and output box.
	* @param windowName The name of the console window.
	*/
	public Console(String windowName) {
		this.GUI = new GUI(this, windowName);
		this.GUI.start();
	}
	
	/**
	* Constructs a console window with an input box and output box.
	* @param windowName The name of the console window.
	* @param fontsize The font size for the text in the window.
	*/
	public Console(String windowName, int fontsize) {
		this.GUI = new GUI(this, windowName, fontsize);
		this.GUI.start();
	}
	
	/**
	* Constructs a console window with an input box and output box.
	* @param fontsize The font size for the text in the window.
	*/
	public Console(int fontsize) {
		this.GUI = new GUI(this, fontsize);
		this.waitingOnPrint=false;
		this.GUI.start();
	}
	
	/**
	* Equivalent to System.out.println(text); for this console object.
	* @param  text  The text to be printed.
	*/
	public void print(String text) {
		if(text!=null)
			this.printString=text;
		else
			this.printString="";
		waitingOnPrint=true;
		// just giving the GUI thread a moment to print before we continue
		try {
			TimeUnit.MILLISECONDS.sleep(010);
		}	
		catch (Exception e) {
		}
		
	}
	
	/**
	* Returns the next line that the user types and enters (waits until user has entered in a response).
	* @return The next line that the user enters.
	*/
	public String input() {
		this.GUI.setAskedForInput(true);
		this.cont=false;
		while(!cont) {
			// this timer is needed, as otherwise when the other thread tries to write to cont, this one will be reading from it
			try {
				TimeUnit.MILLISECONDS.sleep(010);
			}	
			catch (Exception e) {
			}
		}
		this.GUI.setAskedForInput(false);
		return returnStr;
		
	}
	
	/**
	* Prints a prompt and returns the next line that the user types and enters (waits until user has entered in a response).
	* @param  prompt  The text used to prompt the user for input.
	* @return The next line that the user enters.
	*/
	public String input(String prompt) {
		this.GUI.setAskedForInput(true);
		this.cont=false;
		this.inputPrompt=prompt;
		this.waitingOnInput=true;
		while(!cont) {
			// this timer is needed, as otherwise when the other thread tries to write to cont, this one will be reading from it
			try {
				TimeUnit.MILLISECONDS.sleep(010);
			}	
			catch (Exception e) {
			}
		}
		this.GUI.setAskedForInput(false);
		return returnStr;
		
	}
	
	/**
	* Sets the console window icon to the .ico file at the given path.
	* @param  imagePath  The absolute path (and filename with extension) to the .ico file. On Windows you need to use two \\ instead of \, since \ is the escape character.
	*/
	public void setImage(String imagePath) {
		this.imagePath=imagePath;
		this.setIcon=true;
	}
	
	/**
	* Sets the console window's name to a given string.
	* @param  windowName  The title of the console window.
	*/
	public void setWindowName(String windowName) {
		this.GUI.setWindowName(windowName);
		this.setWindowName=true;
	}
	
	String getPrintString() {
		return this.printString;
	}
	
	void setWaitingOnPrint(boolean waitingOnPrint) {
		this.waitingOnPrint=waitingOnPrint;
	}
	
	boolean getWaitingOnPrint() {
		return this.waitingOnPrint;
	}
	
	void setReturnStr(String returnStr) {
		this.returnStr=returnStr;
	}
	
	void setWaitingOnInput(boolean waitingOnInput) {
		this.waitingOnInput=waitingOnInput;
		
	}
	
	boolean getWaitingOnInput() {
		return this.waitingOnInput;
	}
	
	String getInputPrompt() {
		return this.inputPrompt;
	}
	
	void setCont(boolean cont) {
		this.cont=cont;
	}
	
	void setSetIcon(boolean setIcon) {
		this.setIcon=setIcon;
	}
	
	boolean getSetIcon() {
		return this.setIcon;
	}
	
	String getImagePath() {
		return this.imagePath;
	}
	
	boolean getSetWindowName() {
		return this.setWindowName;
	}
	
	void setSetWindowName(boolean setWindowName) {
		this.setWindowName=setWindowName;
	}

}
