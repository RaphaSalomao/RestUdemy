package br.com.erudio.converter.mocks;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.model.Books;
import br.com.erudio.data.vo.v1.BooksVO;

public class MockBooks {

	public Books mockEntity() {
		return mockEntity(0L);
	}
	
	public BooksVO mockVO() {
		return mockVO(0L);
	}
	
	public List<Books> mockEntityList(){
		List<Books> retorno = new ArrayList<Books>();
		for (int i = 0; i < 14; i++) {
			retorno.add(mockEntity(Long.valueOf(i)));
		}
		return retorno;
	}
	
	public List<BooksVO> mockVOList(){
		List<BooksVO> retorno = new ArrayList<BooksVO>();
		for (int i = 0; i < 14; i++) {
			retorno.add(mockVO(Long.valueOf(i)));
		}
		return retorno;
	}
	
	private Books mockEntity(Long number)  {
		return new Books(number,
				"Author num" + number,
				mockData(number), 
				new BigDecimal(10 + number), 
				"Title num " + number);
	}

	private BooksVO mockVO(Long number) {
		return new BooksVO(number,
				"Author num" + number,
				mockData(number), 
				new BigDecimal(10 + number), 
				"Title num " + number);
	}
	
	private LocalDate mockData(Long number) {
		Integer numberInt = number.intValue();
		return LocalDate.of(2000 + numberInt, 1, 1);
	}
	
}
