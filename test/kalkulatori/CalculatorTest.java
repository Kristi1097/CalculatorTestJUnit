package kalkulatori;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

class CalculatorTest {

	/**
	 * create a new object of class Calculator
	 */
	private Calculator calculator=new Calculator();

	@Test 
	void checkGetForCurrentValue() {
		calculator.setCurrentValue(9.87);
		assertThat(9.87,is(calculator.getCurrentValue()));
	}
	@Test
	void checkIsObjectNull() {
		assertThat(calculator,is(notNullValue()));
	}
	@Test 
	void checkSetForCurrentValue() {
		calculator.setCurrentValue(9.87);
		assertThat(9.87,is(calculator.getCurrentValue()));
	}
	
	/**This method check is adding correct
	 * @param p1 first operand
	 * @param p2 second operand
	 * @param result
	 * @throws NotSupportedOperationException
	 * @throws DivisionByZeroException
	 */
	@ParameterizedTest
	@MethodSource("checkAdd")
	public void testCheckAdd(Double p1,Double p2,Double result) throws NotSupportedOperationException, DivisionByZeroException
	{
	calculator.setCurrentValue(p1);
	calculator.calculate(p2,'+');
	assertThat(result,is(equalTo(calculator.getCurrentValue())));	
	}
	private static Stream<Arguments> checkAdd(){
		return Stream.of(
				Arguments.of(2.35,3.25,5.6),
				Arguments.of(-2.67,-3.33,-6.00),
				Arguments.of(-2.19,3.79,1.60),
				Arguments.of(3.00,-5.00,-2.00)
				);
	}
	
	/**This method check is subtract correct
	 * @param p1
	 * @param p2
	 * @param result
	 * @throws NotSupportedOperationException
	 * @throws DivisionByZeroException
	 */
	@ParameterizedTest
	@MethodSource("checkSub")
	public void testCheckSub(Double p1,Double p2,Double result) throws NotSupportedOperationException, DivisionByZeroException
	{
	calculator.setCurrentValue(p1);
	calculator.calculate(p2,'-');
	assertThat(calculator.getCurrentValue(),is(result));	
	}
	private static Stream<Arguments> checkSub(){
		return Stream.of(
				Arguments.of(5.88,3.58,2.3),
				Arguments.of(-2.00,-3.00,1.00),
				Arguments.of(-2.33,3.66,-5.99),
				Arguments.of(3.29,-5.31,8.6)
				);
	}
	/**This method check is multiplication correct
	 * @param p1
	 * @param p2
	 * @param result
	 * @throws NotSupportedOperationException
	 * @throws DivisionByZeroException
	 */
	@ParameterizedTest
	@MethodSource("checkMul")
	public void testCheckMul(Double p1,Double p2,Double result) throws NotSupportedOperationException, DivisionByZeroException
	{
	calculator.setCurrentValue(p1);
	calculator.calculate(p2,'*');
	assertThat(calculator.getCurrentValue(),is(result));		
	}
	private static Stream<Arguments> checkMul(){
		return Stream.of(
				Arguments.of(2.5,3.3,8.25),
				Arguments.of(-2.2,-3.3,7.26),
				Arguments.of(-4.00,5.00,-20.00),
				Arguments.of(3.2,-5.3,-16.96)
				);
	}
	/**This method check is divide correct
	 * @param p1
	 * @param p2
	 * @param result
	 * @throws NotSupportedOperationException
	 * @throws DivisionByZeroException
	 */
	@ParameterizedTest
	@MethodSource("checkDiv")
	public void testCheckDiv(Double p1,Double p2,Double result) throws NotSupportedOperationException, DivisionByZeroException
	{
	calculator.setCurrentValue(p1);
	calculator.calculate(p2,'/');
	assertThat(calculator.getCurrentValue(),is(result));		
	}
	private static Stream<Arguments> checkDiv(){
		return Stream.of(
				Arguments.of(9.33,3.0,3.11),
				Arguments.of(-8.8,-4.00,2.2),
				Arguments.of(8.6,-2.00,-4.3),
				Arguments.of(-6.5,2.00,-3.25)
				);
	}
	@Test
	void checkDivisionByZero() {
		Exception myExceptionZero=assertThrows(DivisionByZeroException.class, () -> calculator.calculate(0.0, '/'));
		assertThat(myExceptionZero,is(instanceOf(DivisionByZeroException.class)));
	}
	@Test
	void checkNotSupportedOperation() {
		Exception myExceptionNotSupportedOperation=assertThrows(NotSupportedOperationException.class, () -> calculator.calculate(5.56, '%'));
		assertThat(myExceptionNotSupportedOperation,is(instanceOf(NotSupportedOperationException.class)));
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
