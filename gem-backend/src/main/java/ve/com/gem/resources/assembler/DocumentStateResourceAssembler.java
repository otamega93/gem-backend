package ve.com.gem.resources.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.controllers.DocumentStateController;
import ve.com.gem.entities.Company;
import ve.com.gem.entities.DocumentState;
import ve.com.gem.resources.CompanyResource;
import ve.com.gem.resources.DocumentStateResource;
import ve.com.gem.services.ICompanyService;
import ve.com.gem.services.IDocumentStateService;

@RestController
@RequestMapping(value = "/api/v1/companies")
@Component
public class DocumentStateResourceAssembler extends ResourceAssemblerSupport<DocumentState, DocumentStateResource>{

	@Autowired
	IDocumentStateService service;
	
	@Autowired
	private DocumentStateResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<DocumentState> pageAssembler;
	
	public DocumentStateResourceAssembler() {
		super(DocumentStateController.class, DocumentStateResource.class);
	}
	
	@Override
	public DocumentStateResource toResource(DocumentState documentState) {
		DocumentStateResource resource = new DocumentStateResource();
		resource.setName(documentState.getName());
		resource.setDescription(documentState.getDescription());
		resource.setCreatedAt(documentState.getCreatedAt());
		resource.setUpdatedAt(documentState.getUpdatedAt());
		resource.setDeletedAt(documentState.getDeletedAt());
		resource.setIds(documentState.getId());
		resource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withSelfRel());
	    resource.add(linkTo(DocumentStateController.class).slash("").slash(documentState.getId()).withRel("documentState"));
		return resource;
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<DocumentStateResource> loadAll(Pageable pageable){
		
		Page<DocumentState> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<DocumentStateResource> load(@PathVariable Long id)
	{
		DocumentState object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<DocumentStateResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<DocumentStateResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<DocumentStateResource> save(@RequestBody DocumentState object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<DocumentStateResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<DocumentStateResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<DocumentStateResource> update(@PathVariable Long id,@RequestBody DocumentState object)
	{
		DocumentState search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<DocumentStateResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<DocumentStateResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<DocumentStateResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<DocumentState> delete(@PathVariable Long id){
		DocumentState search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<DocumentState>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<DocumentState>(HttpStatus.BAD_REQUEST);
		}
	}
	
		
	
	
}
