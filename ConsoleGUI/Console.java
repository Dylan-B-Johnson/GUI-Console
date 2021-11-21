//Author: Dylan Johnson
package ConsoleGUI;

import java.util.concurrent.TimeUnit;

public class Console {
	private GUI GUI;
	private boolean waitingOnInput, cont, waitingOnPrint;
	private String inputPrompt, returnStr, printString;
	
	/**
	* Constructs a console windows with an input box and output box.
	*/
	public Console() {
		this.GUI = new GUI(this);
		this.waitingOnPrint=false;
		this.GUI.start();
	}
	
	/**
	* Constructs a console windows with an input box and output box.
	* @param windowName The name of the console window.
	*/
	public Console(String windowName) {
		this.GUI = new GUI(this, windowName);
		this.GUI.start();
	}
	
	/**
	* Constructs a console windows with an input box and output box.
	* @param windowName The name of the console window.
	* @param fontsize The font size for the text in the window.
	*/
	public Console(String windowName, int fontsize) {
		this.GUI = new GUI(this, windowName, fontsize);
		this.GUI.start();
	}
	
	/**
	* Constructs a console windows with an input box and output box.
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
		try {
			TimeUnit.MILLISECONDS.sleep(010);
		}	
		catch (Exception e) {
		}
		
	}
	
	/**
	* Returns the next line that the user types and enters (waits until user has entered in a response).
	*/
	public String input() {
		this.GUI.setAskedForInput(true);
		this.cont=false;
		while(!cont) {
			// this timer is needed, as otherwise when the other thread tries to write to cont, this one will be reading from it
			try {
				TimeUnit.MILLISECONDS.sleep(100);
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
	*/
	public String input(String prompt) {
		this.GUI.setAskedForInput(true);
		this.cont=false;
		this.inputPrompt=prompt;
		this.waitingOnInput=true;
		while(!cont) {
			// this timer is needed, as otherwise when the other thread tries to write to cont, this one will be reading from it
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			}	
			catch (Exception e) {
			}
		}
		this.GUI.setAskedForInput(false);
		return returnStr;
		
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


}
