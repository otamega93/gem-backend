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

import ve.com.gem.entities.Expert;
import ve.com.gem.resources.ExpertResource;
import ve.com.gem.resources.assembler.ExpertResourceAssembler;
import ve.com.gem.services.IExpertService;

@RestController
@RequestMapping(value = "/api/v1/experts")
public class ExpertController {

	
	@Autowired
	IExpertService service;
	
	@Autowired
	private ExpertResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Expert> pageAssembler;
	
	public ExpertController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<ExpertResource> loadAll(Pageable pageable){
		
		Page<Expert> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<ExpertResource> load(@PathVariable Long id)
	{
		Expert object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<ExpertResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<ExpertResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ExpertResource> save(@RequestBody Expert object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<ExpertResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<ExpertResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ExpertResource> update(@PathVariable Long id,@RequestBody Expert object)
	{
		Expert search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<ExpertResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<ExpertResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<ExpertResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Expert> delete(@PathVariable Long id){
		Expert search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Expert>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Expert>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
