package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.security.InvalidParameterException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin(origins = {"http://localhost:8080", "http://github.com"})
@Api(value = "People API v1", description = "Basic People CRUD", tags = "Person WebService")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices service;
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;

	@ApiOperation(value = "Retrieve all people")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll() {
		List<PersonVO> persons = service.findAll();
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	@ApiOperation(value = "Retrieve all people")
	@GetMapping(value = "/select", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> selectAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {

		if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc"))
			throw new InvalidParameterException("Invalid content for direction");

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> persons = service.selectAll(pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		PagedResources<?> resources = assembler.toResource(persons);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retrieve all people with the substring informed on the name")
	@GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findPersonByName(@PathVariable("firstName") String firstName,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc"))
			throw new InvalidParameterException("Invalid content for direction");
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonVO> persons = service.findPersonByName(firstName, pageable);
		persons.stream()
		.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		PagedResources<?> resources = assembler.toResource(persons);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieve the person register with the informed ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = service.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Register a new person")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = service.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Register a new person version 2")
	@PostMapping(value = "/v2", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}

	@ApiOperation(value = "Update the person register with the informed ID")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = service.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Disable a specific person with the informed ID")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = service.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Delete the register of the person with the informed ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}