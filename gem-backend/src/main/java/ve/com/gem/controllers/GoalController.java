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

import ve.com.gem.entities.Goal;
import ve.com.gem.resources.GoalResource;
import ve.com.gem.resources.assembler.GoalResourceAssembler;
import ve.com.gem.services.IGoalService;

@RestController
@RequestMapping(value = "/api/v1/companies")
public class GoalController {

	@Autowired
	IGoalService service;
	
	@Autowired
	private GoalResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Goal> pageAssembler;
	
	public GoalController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<GoalResource> loadAll(Pageable pageable){
		
		Page<Goal> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<GoalResource> load(@PathVariable Long id)
	{
		Goal object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<GoalResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<GoalResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<GoalResource> save(@RequestBody Goal object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<GoalResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<GoalResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<GoalResource> update(@PathVariable Long id,@RequestBody Goal object)
	{
		Goal search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<GoalResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<GoalResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<GoalResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Goal> delete(@PathVariable Long id){
		Goal search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Goal>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Goal>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
