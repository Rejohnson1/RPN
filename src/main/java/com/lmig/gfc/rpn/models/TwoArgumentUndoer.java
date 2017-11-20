package com.lmig.gfc.rpn.models;
import java.util.Stack;

public class TwoArgumentUndoer 
	extends OneArgumentUndoer {

	//private double first; remove from here since it is no longer needed //
	private double second;
	
	public TwoArgumentUndoer(double first, double second) { //constructor
		super(first); //we are receiving the one parameter from the OneArgumentUndoer class
		//this.first = first;// removing since it is in the other class
		this.second = second;
	}

	public void undo(Stack<Double> stack) {
		stack.pop(); // get rid of the result
		stack.push(second); // put the second number back on the stack
		//stack.push(first); // put the first number back on the stack
		super.parentUndo(stack); //pulled this from the one argument class
	}
	protected void parentUndo(Stack<Double> stack) {
		stack.push(second);
		super.parentUndo(stack); 
	}
}
