package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Books;
import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BooksRepository;

@Service
public class BooksServices {

	@Autowired
	BooksRepository booksRepository;

	public BooksVO create(BooksVO books) {
		var entity = DozerConverter.parseObject(books, Books.class);
		var vo = DozerConverter.parseObject(booksRepository.save(entity), BooksVO.class);
		return vo;
	}

	public BooksVO update(BooksVO books) {
		var entity = booksRepository.findById(books.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity = DozerConverter.parseObject(books, Books.class);
		var vo = DozerConverter.parseObject(booksRepository.save(entity), BooksVO.class);
		return vo;
	}

	public BooksVO findById(Long id) {
		var entity = booksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BooksVO.class);
	}

	public List<BooksVO> findAll() {
		return DozerConverter.parseListObject(booksRepository.findAll(), BooksVO.class);
	}

	public void delete(Long id) {
		var entity = booksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		booksRepository.delete(entity);
	}

	public Page<BooksVO> selectAll(Pageable pageable) {
		var page = booksRepository.findAll(pageable);
		return page.map(this::convertToBooksVO);
	}

	private BooksVO convertToBooksVO(Books entity) {
		return DozerConverter.parseObject(entity, BooksVO.class);
	}

}
