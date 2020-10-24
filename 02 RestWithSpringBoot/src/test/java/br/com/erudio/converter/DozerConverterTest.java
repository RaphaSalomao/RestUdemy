package br.com.erudio.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;


public class DozerConverterTest {

	MockPerson inputObject;
	
	@Before
	public void setUp() {
		inputObject =  new MockPerson();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name Test 0", output.getFirstName());
		Assert.assertEquals("Last Name Test 0", output.getLastName());
		Assert.assertEquals("Address Test 0", output.getAddress());
		Assert.assertEquals("Male 0", output.getGender());
	}

	@Test
	public void parseEntityListToVOList() {
		List<PersonVO> outputList = DozerConverter.parseListObject(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("First Name Test 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test 0", outputZero.getLastName());
		Assert.assertEquals("Address Test 0", outputZero.getAddress());
		Assert.assertEquals("Male 0", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("First Name Test 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test 7", outputSeven.getLastName());
		Assert.assertEquals("Address Test 7", outputSeven.getAddress());
		Assert.assertEquals("Male 7", outputSeven.getGender());
		
		PersonVO outputTwelve = outputList.get(12);
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("First Name Test 12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test 12", outputTwelve.getLastName());
		Assert.assertEquals("Address Test 12", outputTwelve.getAddress());
		Assert.assertEquals("Male 12", outputTwelve.getGender());
	}

	@Test
	public void parseVOToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name Test 0", output.getFirstName());
		Assert.assertEquals("Last Name Test 0", output.getLastName());
		Assert.assertEquals("Address Test 0", output.getAddress());
		Assert.assertEquals("Male 0", output.getGender());
	}

	@Test
	public void parseVOListToEntityList() {
		List<Person> outputList = DozerConverter.parseListObject(inputObject.mockVOList(), Person.class);
		Person outputZero = outputList.get(0);
		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("First Name Test 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test 0", outputZero.getLastName());
		Assert.assertEquals("Address Test 0", outputZero.getAddress());
		Assert.assertEquals("Male 0", outputZero.getGender());
		
		Person outputSeven = outputList.get(7);
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("First Name Test 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test 7", outputSeven.getLastName());
		Assert.assertEquals("Address Test 7", outputSeven.getAddress());
		Assert.assertEquals("Male 7", outputSeven.getGender());
		
		Person outputTwelve = outputList.get(12);
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("First Name Test 12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test 12", outputTwelve.getLastName());
		Assert.assertEquals("Address Test 12", outputTwelve.getAddress());
		Assert.assertEquals("Male 12", outputTwelve.getGender());
	}

}
