package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer {

	private double first;
		
	public OneArgumentUndoer(double first) { //constructor
		this.first = first;
		}

	public void undo(Stack<Double> stack) {
		stack.pop(); // get rid of the result
		//stack.push(first); // put the first number back on the stack
			//removed line above since it is now redundant
		parentUndo(stack);
	}

	protected void parentUndo(Stack<Double> stack) {
		stack.push(first);
	}
}
