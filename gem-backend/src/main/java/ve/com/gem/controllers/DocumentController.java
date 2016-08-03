package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.Document;
import ve.com.gem.resources.DocumentResource;
import ve.com.gem.resources.assembler.DocumentResourceAssembler;
import ve.com.gem.services.IDocumentService;

@RestController
@RequestMapping(value = "/api/v1/documents")
public class DocumentController {
	
	@Autowired
	IDocumentService service;
	
	@Autowired
	private DocumentResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Document> pageAssembler;

	public DocumentController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Document> delete(@PathVariable Long id){
		Document search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Document>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Document>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<DocumentResource> update(@PathVariable Long id,@RequestBody Document object)
	{
		Document search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<DocumentResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<DocumentResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<DocumentResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<DocumentResource> save(@RequestBody Document object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<DocumentResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<DocumentResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<DocumentResource> load(@PathVariable Long id)
	{
		Document object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<DocumentResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<DocumentResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<DocumentResource> loadAll(Pageable pageable){
		
		Page<Document> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}

}
