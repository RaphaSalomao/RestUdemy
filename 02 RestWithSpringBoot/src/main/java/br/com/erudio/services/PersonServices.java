package br.com.erudio.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.converter.custom.PersonConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	@Autowired
	PersonConverter converter;

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		var entity = converter.convertVOToEntity(person);
		var vo = converter.convertEntityToVO(repository.save(entity));
		return vo;
	}

	public List<PersonVO> findAll() {
		return DozerConverter.parseListObject(repository.findAll(), PersonVO.class);
	}

	public Page<PersonVO> selectAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPerson);
	}
	
	public Page<PersonVO> findPersonByName(String firstName,Pageable pageable) {
		var page = repository.findPersonByName(firstName, pageable);
		return page.map(this::convertToPerson);
	}
	
	public PersonVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	@Transactional 
	public PersonVO disablePerson(Long id) {
		repository.disablePerson(id);
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

	private PersonVO convertToPerson(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

}
