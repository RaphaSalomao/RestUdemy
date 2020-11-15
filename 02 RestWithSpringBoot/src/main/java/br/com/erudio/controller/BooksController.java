package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.services.BooksServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin(origins = {"http://localhost:8080", "http://github.com"})
@Api(value = "Books API v1", description = "Basic Books CRUD", tags = { "Books WebService" })
@RestController
@RequestMapping("/api/books/v1")
public class BooksController {

	@Autowired
	private BooksServices service;
	
	@Autowired
	private PagedResourcesAssembler<BooksVO> assembler;

	@ApiOperation(value = "Retrieve all books registers")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BooksVO> findAll() {
		var retorno = service.findAll();
		retorno.forEach(b -> b.add(linkTo(methodOn(BooksController.class).findById(b.getKey())).withSelfRel()));
		return retorno;
	}

	@ApiOperation(value = "Retrieve all books filtered by the given parameters")
	@GetMapping(value = "/select", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> selectAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {

		if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc"))
			throw new InvalidParameterException("Invalid content for direction");

		var sortDirection = "asc".equalsIgnoreCase(direction) ? Direction.ASC : Direction.DESC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		Page<BooksVO> books = service.selectAll(pageable);
		books.forEach(b -> b.add(linkTo(methodOn(BooksController.class).findById(b.getKey())).withSelfRel()));
		
		PagedResources<?> resource = assembler.toResource(books);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieve the book register with the informed ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BooksVO findById(@PathVariable("id") Long id) {
		var vo = service.findById(id);
		vo.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Register a new book")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BooksVO create(@RequestBody BooksVO booksVO) {
		var vo = service.create(booksVO);
		vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Update the book register with the informed ID")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BooksVO update(@RequestBody BooksVO booksVO) {
		var vo = service.update(booksVO);
		vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Delete the register of the book with the informed ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
