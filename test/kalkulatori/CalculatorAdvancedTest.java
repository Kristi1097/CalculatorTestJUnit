package kalkulatori;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

class CalculatorAdvancedTest {

	/**
	 * create a new object of class CalculatorAdvanced
	 */
	private CalculatorAdvanced calculator=new CalculatorAdvanced();
	/**
	 * @param parameterCurrrentValue 
	 * @param action is it degree
	 * @param result current result in test
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("checkExp")
	public void testCheckExp(Double parameterCurrrentValue,char action,Double result) throws NotSupportedOperationException, NumberNotInAreaException
	{
		calculator.setCurrentValue(parameterCurrrentValue);
		calculator.calculateAdvanced(action);
		assertThat(calculator.getCurrentValue(),is(result));
	}
	private static Stream<Arguments> checkExp(){
		return Stream.of(
				Arguments.of(0.01,'0',1.00),
				Arguments.of(5.56,'0',1.00),
				Arguments.of(-3.48,'0',1.00),
				Arguments.of(9.00,'2',81.00),
				Arguments.of(10.01,'3',1000.00),
				Arguments.of(-1.00,'3',-1.00)	
				);
		
	}
	/**
	 * @param parameterCurrentValue
	 * @param value is it factorial
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("checkFact")
	public void testCheckFact(Double parameterCurrentValue,Double value) throws NotSupportedOperationException, NumberNotInAreaException
	{
		calculator.setCurrentValue(parameterCurrentValue);
		calculator.calculateAdvanced('!');
		assertThat(calculator.getCurrentValue(),is(value));
	}
	private static Stream<Arguments> checkFact(){
		return Stream.of(
				Arguments.of(0.00,1.00),
				Arguments.of(3.20,6.00),
				Arguments.of(5.20,120.00),
				Arguments.of(10.01,3628800.00),
				Arguments.of(2.56,2.00)
				);
	}
	/**
	 * if number is not in area
	 */
	@Test
	void testCheckNumber() {
		Exception myException1=assertThrows(NumberNotInAreaException.class, () -> {
			calculator.setCurrentValue(-1.01);
			calculator.calculateAdvanced('!');
		});
		assertThat(myException1,is(instanceOf(NumberNotInAreaException.class)));
		myException1=assertThrows(NumberNotInAreaException.class, () -> {
			calculator.setCurrentValue(11.01);
			calculator.calculateAdvanced('!');
		});
		assertThat(myException1,is(instanceOf(NumberNotInAreaException.class)));
	}
	
	/**
	 * if operation is not supported
	 */
	@Test
	void testNumberIsNotSupported() {
		Exception myException2=assertThrows(NotSupportedOperationException.class, () -> calculator.calculateAdvanced('%'));
		assertThat(myException2,is(instanceOf(NotSupportedOperationException.class)));
		myException2=assertThrows(NotSupportedOperationException.class, () -> calculator.calculateAdvanced('^'));
		assertThat(myException2,is(instanceOf(NotSupportedOperationException.class)));
	}
	/**This method check is it number Armstrong
	 * @param parameterCurrentValue
	 * @param result
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("checkIsItArmstrong")
	public void testCheckIsItArmstrong(Double parameterCurrentValue,Boolean result) throws NotSupportedOperationException, NumberNotInAreaException
	{
		calculator.setCurrentValue(parameterCurrentValue);
		calculator.hasCharacteristic('A');
		assertThat(calculator.hasCharacteristic('A'),is(result));
	}
	private static Stream<Arguments> checkIsItArmstrong(){
		return Stream.of(
				Arguments.of(153.00,true),
				Arguments.of(1634.00,true),
				Arguments.of(12.56,false)
				);
	}
	
	/**This method check is it number perfect
	 * @param parameterCurrentValue
	 * @param result
	 * @throws NotSupportedOperationException
	 * @throws NumberNotInAreaException
	 */
	@ParameterizedTest
	@MethodSource("checkIsItPerfect")
	public void testCheckIsItPerfect(Double parameterCurrentValue,Boolean result) throws NotSupportedOperationException, NumberNotInAreaException
	{
		calculator.setCurrentValue(parameterCurrentValue);
		calculator.hasCharacteristic('P');
		assertThat(calculator.hasCharacteristic('P'),is(result));
	}
	private static Stream<Arguments> checkIsItPerfect(){
		return Stream.of(
				Arguments.of(6.24,true),
				Arguments.of(28.78,true),
				Arguments.of(8.78,false)
				);
	}
	/**
	 * if number is not in area
	 */
	@Test
	void testCheckNumberCharacteristic() {
		Exception myException1=assertThrows(NumberNotInAreaException.class, () -> {
			calculator.setCurrentValue(-1.7);
			calculator.hasCharacteristic('P');
		});
		assertThat(myException1,is(instanceOf(NumberNotInAreaException.class)));
		myException1=assertThrows(NumberNotInAreaException.class, () -> {
			calculator.setCurrentValue(0.89);
			calculator.hasCharacteristic('A');
		});
		assertThat(myException1,is(instanceOf(NumberNotInAreaException.class)));
	}
	
	/**
	 * if operation is not supported
	 */
	@Test
	void testNumberIsNotSupportedCharacteristic() {
		calculator.setCurrentValue(10.06);
		Exception myException2=assertThrows(NotSupportedOperationException.class, () -> calculator.hasCharacteristic('C'));
		assertThat(myException2,is(instanceOf(NotSupportedOperationException.class)));
		myException2=assertThrows(NotSupportedOperationException.class, () -> calculator.hasCharacteristic('L'));
		assertThat(myException2,is(instanceOf(NotSupportedOperationException.class)));
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
