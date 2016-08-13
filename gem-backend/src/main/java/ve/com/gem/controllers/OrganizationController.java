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
import org.springframework.web.bind.annotation.CrossOrigin;
import ve.com.gem.entities.Organization;
import ve.com.gem.resources.OrganizationResource;
import ve.com.gem.resources.assembler.OrganizationResourceAssembler;
import ve.com.gem.services.IOrganizationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/organizations")
public class OrganizationController {

	@Autowired
	private IOrganizationService service;

	@Autowired
	private OrganizationResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Organization> pageAssembler;

	public OrganizationController() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<OrganizationResource> loadAll(Pageable pageable){
		
		Page<Organization> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<OrganizationResource> load(@PathVariable Long id)
	{
		Organization object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<OrganizationResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<OrganizationResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<OrganizationResource> save(@RequestBody Organization object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<OrganizationResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<OrganizationResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<OrganizationResource> update(@PathVariable Long id,@RequestBody Organization object)
	{
		Organization search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<OrganizationResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<OrganizationResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<OrganizationResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Organization> delete(@PathVariable Long id){
		Organization search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Organization>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Organization>(HttpStatus.BAD_REQUEST);
		}
	}
}
