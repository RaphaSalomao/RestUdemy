package br.com.erudio.converter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockBooks;
import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.model.Books;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.data.vo.v1.PersonVO;


public class DozerConverterTest {

	MockPerson personInput;
	
	MockBooks booksInput;
	
	@Before
	public void setUp() {
		personInput =  new MockPerson();
		booksInput = new MockBooks();
	}
	
	@Test
	public void parseEntityPersonToVOTest() {
		PersonVO output = DozerConverter.parseObject(personInput.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("First Name Test 0", output.getFirstName());
		Assert.assertEquals("Last Name Test 0", output.getLastName());
		Assert.assertEquals("Address Test 0", output.getAddress());
		Assert.assertEquals("Male 0", output.getGender());
	}
	
	@Test
	public void parseEntityBooksToVOTest() {
		BooksVO output = DozerConverter.parseObject(booksInput.mockEntity(), BooksVO.class);
		Assert.assertEquals(0L, output.getKey());
		Assert.assertEquals("Title num 0", output.getTitle());
		Assert.assertEquals(LocalDate.of(2000, 1, 1), output.getLaunchDate());
		Assert.assertEquals(new BigDecimal(10), output.getPrice());
		Assert.assertEquals("Author num0", output.getAuthor());
	}
	
//	@Test
	public void parseEntityPersonListToVOList() {
		List<PersonVO> outputList = DozerConverter.parseListObject(personInput.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("First Name Test 0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test 0", outputZero.getLastName());
		Assert.assertEquals("Address Test 0", outputZero.getAddress());
		Assert.assertEquals("Male 0", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("First Name Test 7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test 7", outputSeven.getLastName());
		Assert.assertEquals("Address Test 7", outputSeven.getAddress());
		Assert.assertEquals("Male 7", outputSeven.getGender());
		
		PersonVO outputTwelve = outputList.get(12);
		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		Assert.assertEquals("First Name Test 12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test 12", outputTwelve.getLastName());
		Assert.assertEquals("Address Test 12", outputTwelve.getAddress());
		Assert.assertEquals("Male 12", outputTwelve.getGender());
	}

//	@Test
	public void parseVOPersonToEntityTest() {
		Person output = DozerConverter.parseObject(personInput.mockVO(), Person.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name Test 0", output.getFirstName());
		Assert.assertEquals("Last Name Test 0", output.getLastName());
		Assert.assertEquals("Address Test 0", output.getAddress());
		Assert.assertEquals("Male 0", output.getGender());
	}

	@Test
	public void parseVOBooksToEntityTest() {
		Books output = DozerConverter.parseObject(booksInput.mockVO(), Books.class);
		Assert.assertEquals(0L, output.getId());
		Assert.assertEquals("Title num 0", output.getTitle());
		Assert.assertEquals(LocalDate.of(2000, 1, 1), output.getLaunchDate());
		Assert.assertEquals(new BigDecimal(10), output.getPrice());
		Assert.assertEquals("Author num0", output.getAuthor());
	}
	
//	@Test
	public void parseVOPersonListToEntityList() {
		List<Person> outputList = DozerConverter.parseListObject(personInput.mockVOList(), Person.class);
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

	
	@Test
	public void parseEntityBooksListToVOList() {
		List<BooksVO> outputList = DozerConverter.parseListObject(booksInput.mockEntityList(), BooksVO.class);
		BooksVO outputOne = outputList.get(1);
		Assert.assertEquals(1L, outputOne.getKey());
		Assert.assertEquals("Title num 1", outputOne.getTitle());
		Assert.assertEquals(LocalDate.of(2001, 1, 1), outputOne.getLaunchDate());
		Assert.assertEquals(new BigDecimal(11), outputOne.getPrice());
		Assert.assertEquals("Author num1", outputOne.getAuthor());
		
		BooksVO outputSeven = outputList.get(7);
		Assert.assertEquals(7L, outputSeven.getKey());
		Assert.assertEquals("Title num 7", outputSeven.getTitle());
		Assert.assertEquals(LocalDate.of(2007, 1, 1), outputSeven.getLaunchDate());
		Assert.assertEquals(new BigDecimal(17), outputSeven.getPrice());
		Assert.assertEquals("Author num7", outputSeven.getAuthor());
		
		BooksVO outputTen = outputList.get(10);
		Assert.assertEquals(10L, outputTen.getKey());
		Assert.assertEquals("Title num 10", outputTen.getTitle());
		Assert.assertEquals(LocalDate.of(2010, 1, 1), outputTen.getLaunchDate());
		Assert.assertEquals(new BigDecimal(20), outputTen.getPrice());
		Assert.assertEquals("Author num10", outputTen.getAuthor());
	}

	@Test
	public void parseVOBooksListToEntityList() {
		List<Books> outputList = DozerConverter.parseListObject(booksInput.mockVOList(), Books.class);
		Books outputOne = outputList.get(1);
		Assert.assertEquals(1L, outputOne.getId());
		Assert.assertEquals("Title num 1", outputOne.getTitle());
		Assert.assertEquals(LocalDate.of(2001, 1, 1), outputOne.getLaunchDate());
		Assert.assertEquals(new BigDecimal(11), outputOne.getPrice());
		Assert.assertEquals("Author num1", outputOne.getAuthor());
		
		Books outputSeven = outputList.get(7);
		Assert.assertEquals(7L, outputSeven.getId());
		Assert.assertEquals("Title num 7", outputSeven.getTitle());
		Assert.assertEquals(LocalDate.of(2007, 1, 1), outputSeven.getLaunchDate());
		Assert.assertEquals(new BigDecimal(17), outputSeven.getPrice());
		Assert.assertEquals("Author num7", outputSeven.getAuthor());
		
		Books outputTen = outputList.get(10);
		Assert.assertEquals(10L, outputTen.getId());
		Assert.assertEquals("Title num 10", outputTen.getTitle());
		Assert.assertEquals(LocalDate.of(2010, 1, 1), outputTen.getLaunchDate());
		Assert.assertEquals(new BigDecimal(20), outputTen.getPrice());
		Assert.assertEquals("Author num10", outputTen.getAuthor());
	}

	
}

