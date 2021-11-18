//Author: Dylan Johnson
package ConsoleGUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Text;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.wb.swt.SWTResourceManager;


class GUI extends Thread{
	protected Shell shell;
	private Text textboxOutput;
	private Text textboxInput;
	private String consoleString, inputtedString, windowName;
	private Console console;
	boolean setContTrue, askedForInput;
	private int fontSize;
	
	public GUI(Console console) {
		this.console=console;
		this.windowName="Console";
		this.fontSize=9;
	}
	
	public GUI(Console console, String windowName, int fontSize) {
		this.console=console;
		this.fontSize=fontSize;
		if (windowName!=null)
			this.windowName=windowName;
		else
			this.windowName="Console";
	}
	
	public GUI(Console console, int fontSize) {
		this.console=console;
		this.fontSize=fontSize;
		this.windowName="Console";
	}
	
	public GUI(Console console, String windowName) {
		this.console=console;
		this.fontSize=9;
		if (windowName!=null)
			this.windowName=windowName;
		else
			this.windowName="Console";
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public void run() {
		try {
			this.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (this.console.getWaitingOnInput()) {
				this.println(this.console.getInputPrompt());
				this.console.setWaitingOnInput(false);
			}
			if (setContTrue)
			{
				this.console.setCont(true);
			}
			if (this.console.getWaitingOnPrint()) {
				println(this.console.getPrintString());
				this.console.setWaitingOnPrint(false);
			}
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		this.shell = new Shell();
		this.shell.setSize(450, 300);
		this.shell.setText(this.windowName);
		this.shell.setLayout(new FillLayout(SWT.VERTICAL));
		this.textboxInput = new Text(shell, SWT.BORDER);
		textboxInput.setFont(SWTResourceManager.getFont("Segoe UI", this.fontSize, SWT.NORMAL));
		this.textboxInput.setText("");
		// this following runs the "runs" block when enter is pressed if the textbox is selected
		textboxInput.addListener(SWT.DefaultSelection, new Listener() {
			public void handleEvent(Event event) 
			{ // runs 
				if (!askedForInput) {
					JOptionPane.showMessageDialog(null, "The program has not asked for input.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					inputtedString=textboxInput.getText();
					console.setReturnStr(inputtedString);
					println(inputtedString);
					clearScreenInput();
					setContTrue=true;
				}

			}
		});
		this.textboxOutput = new Text(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		textboxOutput.setFont(SWTResourceManager.getFont("Segoe UI", this.fontSize, SWT.NORMAL));
		this.textboxOutput.setEditable(false);
		this.textboxOutput.setText("");
		this.consoleString="";
	}
	
	public void println(String str) {
		this.consoleString+=str+"\n";
		this.textboxOutput.setText(this.consoleString);
	}
	
	public void print(String str) {
		this.consoleString+=str;
		this.textboxOutput.setText(this.consoleString);
	}
	
	public void clearScreenOutput() {
		this.consoleString="";
		this.textboxOutput.setText(this.consoleString);

	}
	
	public void clearScreenInput() {
		this.inputtedString=null;
		this.inputtedString="";
		this.textboxInput.setText("");

	}
	
	public void setInputtedString(String inputtedString) {
		this.inputtedString=inputtedString;
	}
	
	public String getInputtedString() {
		return this.inputtedString;
	}
	
	public void setAskedForInput(boolean askedForInput) {
		this.askedForInput=askedForInput;
	}

}
