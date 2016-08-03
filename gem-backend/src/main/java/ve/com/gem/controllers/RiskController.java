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

import ve.com.gem.entities.Risk;
import ve.com.gem.resources.RiskResource;
import ve.com.gem.resources.assembler.RiskResourceAssembler;
import ve.com.gem.services.IRiskService;

@RestController
@RequestMapping(value = "/api/v1/risks")
public class RiskController {

	@Autowired
	IRiskService service;
	
	@Autowired
	private RiskResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Risk> pageAssembler;
	
	public RiskController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<RiskResource> loadAll(Pageable pageable){
		
		Page<Risk> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<RiskResource> load(@PathVariable Long id)
	{
		Risk object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<RiskResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<RiskResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<RiskResource> save(@RequestBody Risk object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<RiskResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<RiskResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<RiskResource> update(@PathVariable Long id,@RequestBody Risk object)
	{
		Risk search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<RiskResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<RiskResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<RiskResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Risk> delete(@PathVariable Long id){
		Risk search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Risk>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Risk>(HttpStatus.BAD_REQUEST);
		}
	}
		
}
