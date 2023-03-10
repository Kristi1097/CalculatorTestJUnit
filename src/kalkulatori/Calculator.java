package kalkulatori;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**
 * 
 * @author Kristi
 *
 */
public class Calculator {

	/**
	 * is value in calculator
	 */
	private Double currentValue=0.00;
	
	/**
	 * @return current value
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**
	 * @param currentValue sets field with parameter double
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}

	/**This method make basic math operations like a adding,subtracting,multiplication and divide
	 * @param value is 2 parameter in operations
	 * @param operator is type of operator which makes basic math operations
	 * @throws NotSupportedOperationException if operator is not supported
	 * @throws DivisionByZeroException if value was zero
	 */
	public void calculate(Double value, char operator) throws NotSupportedOperationException, DivisionByZeroException {
		if (operator == '+')
			currentValue = currentValue + value;
		else if (operator == '-')
			currentValue = currentValue - value;
		else if (operator == '*')
			currentValue = currentValue * value;
		else if (operator == '/') {
			if (value != 0)
				currentValue = currentValue / value;
			else
				throw new DivisionByZeroException();
		} else
			throw new NotSupportedOperationException();
	}
}
