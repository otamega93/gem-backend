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

import ve.com.gem.entities.Indicator;
import ve.com.gem.resources.IndicatorResource;
import ve.com.gem.resources.assembler.IndicatorResourceAssembler;
import ve.com.gem.services.IIndicatorService;

@RestController
@RequestMapping(value = "/api/v1/indicators")
public class IndicatorController {
	
	@Autowired
	IIndicatorService service;
	
	@Autowired
	private IndicatorResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Indicator> pageAssembler;

	public IndicatorController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Indicator> delete(@PathVariable Long id){
		Indicator search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Indicator>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Indicator>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<IndicatorResource> update(@PathVariable Long id,@RequestBody Indicator object)
	{
		Indicator search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<IndicatorResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<IndicatorResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<IndicatorResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<IndicatorResource> save(@RequestBody Indicator object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<IndicatorResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<IndicatorResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<IndicatorResource> load(@PathVariable Long id)
	{
		Indicator object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<IndicatorResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<IndicatorResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<IndicatorResource> loadAll(Pageable pageable){
		
		Page<Indicator> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}

}
