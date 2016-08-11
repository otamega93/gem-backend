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

import ve.com.gem.entities.Employee;
import ve.com.gem.resources.EmployeeResource;
import ve.com.gem.resources.assembler.EmployeeResourceAssembler;
import ve.com.gem.services.IExpertService;

@RestController
@RequestMapping(value = "/api/v1/experts")
public class ExpertController {

	
	@Autowired
	IExpertService service;
	
	@Autowired
	private EmployeeResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Employee> pageAssembler;
	
	public ExpertController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<EmployeeResource> loadAll(Pageable pageable){
		
		Page<Employee> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<EmployeeResource> load(@PathVariable Long id)
	{
		Employee object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<EmployeeResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<EmployeeResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResource> save(@RequestBody Employee object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<EmployeeResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<EmployeeResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<EmployeeResource> update(@PathVariable Long id,@RequestBody Employee object)
	{
		Employee search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<EmployeeResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<EmployeeResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<EmployeeResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Employee> delete(@PathVariable Long id){
		Employee search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Employee>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
