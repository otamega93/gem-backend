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

import ve.com.gem.entities.Valoration;
import ve.com.gem.resources.ValorationResource;
import ve.com.gem.resources.assembler.ValorationResourceAssembler;
import ve.com.gem.services.IValorationService;

@RestController
@RequestMapping(value = "/api/v1/valorations")
public class ValorationController {

	@Autowired
	IValorationService service;
	
	@Autowired
	private ValorationResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Valoration> pageAssembler;
	
	public ValorationController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<ValorationResource> loadAll(Pageable pageable){
		
		Page<Valoration> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<ValorationResource> load(@PathVariable Long id)
	{
		Valoration object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<ValorationResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<ValorationResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ValorationResource> save(@RequestBody Valoration object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<ValorationResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<ValorationResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ValorationResource> update(@PathVariable Long id,@RequestBody Valoration object)
	{
		Valoration search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<ValorationResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<ValorationResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<ValorationResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Valoration> delete(@PathVariable Long id){
		Valoration search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Valoration>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Valoration>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
