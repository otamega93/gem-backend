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

import ve.com.gem.entities.RiskType;
import ve.com.gem.resources.RiskTypeResource;
import ve.com.gem.resources.assembler.RiskTypeResourceAssembler;
import ve.com.gem.services.IRiskTypeService;

@RestController
@RequestMapping(value = "/api/v1/riskTypes")
public class RiskTypeController {

	@Autowired
	IRiskTypeService service;
	
	@Autowired
	private RiskTypeResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<RiskType> pageAssembler;
	
	public RiskTypeController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<RiskTypeResource> loadAll(Pageable pageable){
		
		Page<RiskType> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<RiskTypeResource> load(@PathVariable Long id)
	{
		RiskType object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<RiskTypeResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<RiskTypeResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<RiskTypeResource> save(@RequestBody RiskType object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<RiskTypeResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<RiskTypeResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<RiskTypeResource> update(@PathVariable Long id,@RequestBody RiskType object)
	{
		RiskType search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<RiskTypeResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<RiskTypeResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<RiskTypeResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<RiskType> delete(@PathVariable Long id){
		RiskType search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<RiskType>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<RiskType>(HttpStatus.BAD_REQUEST);
		}
	}
	
		
	
}
