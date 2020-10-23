package br.com.erudio;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsupportedMathOperationException;
import br.com.erudio.util.MathUtil;

@RestController
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
		MathUtil.validMathOperation(numberOne, numberTwo);
		Double sum = MathUtil.convertToDouble(numberOne) + MathUtil.convertToDouble(numberTwo);
		return sum;
	}

	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
		MathUtil.validMathOperation(numberOne, numberTwo);
		Double subtraction = MathUtil.convertToDouble(numberOne) - MathUtil.convertToDouble(numberTwo);
		return subtraction;
	}
	 
	@RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mutiply(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
		MathUtil.validMathOperation(numberOne, numberTwo);
		Double result = MathUtil.convertToDouble(numberOne) * MathUtil.convertToDouble(numberTwo);
		return result;
	}
	
	@RequestMapping(value = "/divide/{numberOne}/{numberTwo}")
	public Double divide(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
		MathUtil.validMathOperation(numberOne, numberTwo);
		Double result = MathUtil.convertToDouble(numberOne) / MathUtil.convertToDouble(numberTwo);
		return result;
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}")
	public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws UnsupportedMathOperationException {
		MathUtil.validMathOperation(numberOne, numberTwo);
		Double result = (MathUtil.convertToDouble(numberOne) + MathUtil.convertToDouble(numberTwo))/2;
		return result;
	}
	
	@RequestMapping(value = "/squareroot/{number}")
	public Double squareRoot(@PathVariable("number")String number) {
		MathUtil.validMathOperation(number);
		Double result = Math.sqrt(MathUtil.convertToDouble(number));
		return result;
	}
}
