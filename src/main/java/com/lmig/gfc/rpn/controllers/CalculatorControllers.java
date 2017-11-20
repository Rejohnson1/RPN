package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;

@Controller
public class CalculatorControllers {

	private Stack<Double> stack;
	//private TwoArgumentUndoer undoer; //place to store the undoer class
	private OneArgumentUndoer undoer;
		
	public CalculatorControllers() {
			stack = new Stack<Double>();
	}
		
	@GetMapping("/")
	public ModelAndView showCalculator() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calculator");
		mv.addObject("stack", stack); //display the stack content on the main page
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2); //true when it has 2 or more numbers in the stack
		mv.addObject("hasUndoer", undoer != null);
		return mv;
	}
	
	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) { 
		//name of parameter "value" has to match input on HTML
		stack.push(value); //define the stack, and push the 'value' into the stack 
		undoer = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbersFromStack() { //numbers do not need to do anything yet
		double first = stack.pop(); //get one number from the stack
		double second = stack.pop(); //get the second number from the stack
		double result = first + second; //add them together and store a result
		stack.push(result);		//push the result back to the stack
		undoer = new TwoArgumentUndoer(first, second);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/minus")
	public ModelAndView subTwoNumbersFromStack() { //numbers do not need to do anything yet
		double first = stack.pop(); //get one number from the stack
		double second = stack.pop(); //get the second number from the stack
		double result = second - first; //subtract from each other and store a result
		stack.push(result);		//push the result back to the stack
		undoer = new TwoArgumentUndoer(first, second);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/abs")
	public ModelAndView absNumberFromStack() { //numbers do not need to do anything yet
		double first = stack.pop(); //get one number from the stack
		undoer = new OneArgumentUndoer(first);
		double result = Math.abs(first); //subtract from each other and store a result
		stack.push(result);		//push the result back to the stack
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		undoer.undo(stack); //pass 
		undoer = null; //set it back to null so it can be used again
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
}
