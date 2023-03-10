package kalkulatori;

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * @author Kristi
 *
 */

public class CalculatorAdvanced extends Calculator {
	/**This method check action and execute gradation of number and factorial of number
	 * @param action can be degree of number or sign for factorial of number
	 * @throws NotSupportedOperationException if action is not correct
	 * @throws NumberNotInAreaException if number is not between 0 and 10 for calculate factorial
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		if ((action >= '0' && action <= '9')) {
			int temporaryCurrentValue = getCurrentValue().intValue(), temp = 1;
			while (action > '0') {
				temp *= temporaryCurrentValue;
				action--;
			}
			setCurrentValue(Double.valueOf(temp));
		} else if (action == '!') {
			int temporaryCurrentValue = getCurrentValue().intValue(), result = getCurrentValue().intValue();
			if (temporaryCurrentValue >= 0 && temporaryCurrentValue <= 10) {
				if (temporaryCurrentValue == 0)
					setCurrentValue(1.00);
				else {
					while (temporaryCurrentValue > 1) {
						result *= (temporaryCurrentValue - 1);
						temporaryCurrentValue--;
					}
					setCurrentValue(Double.valueOf(result));
				}

			} else
				throw new NumberNotInAreaException();
		} else
			throw new NotSupportedOperationException();
	}

	/**
	 * @param value can be A for calculate Armstrong number or P to calculate perfect number
	 * @return true if number is A or is P else return false 
	 * @throws NotSupportedOperationException if value is not correct
	 * @throws NumberNotInAreaException if number is less than zero
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {
		int temporaryCurrentValue = getCurrentValue().intValue();
		if (temporaryCurrentValue < 1)
			throw new NumberNotInAreaException();
		if (value == 'A') {
			int temp = temporaryCurrentValue, c = 0, a, numberOfNumber = 0;
			while (temporaryCurrentValue > 0) {
				numberOfNumber++;
				temporaryCurrentValue /= 10;
			}
			temporaryCurrentValue = temp;
			int tempCount = numberOfNumber;
			while (temporaryCurrentValue > 0) {
				a = temporaryCurrentValue % 10;
				temporaryCurrentValue = temporaryCurrentValue / 10;
				int tempN = 1;
				while (tempCount > 0) {
					tempN *= a;
					tempCount--;
				}
				c = c + tempN;
				tempCount = numberOfNumber;
			}
			if (temp == c)
				return true;
		} else if (value == 'P') {
			int i = 1, sum = 0;
			while (i < temporaryCurrentValue) {
				if (temporaryCurrentValue % i == 0)
					sum += i;
				i++;
			}
			if (sum == temporaryCurrentValue)
				return true;
		} else
			throw new NotSupportedOperationException();
		return false;
	}
}
