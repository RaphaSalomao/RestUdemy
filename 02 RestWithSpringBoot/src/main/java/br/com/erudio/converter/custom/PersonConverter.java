package br.com.erudio.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v2.PersonVOV2;

@Service
public class PersonConverter {

	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(),
				person.getGender(), new Date());
		return vo;
	}

	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(),
				person.getGender());
		return entity;
	}

}
