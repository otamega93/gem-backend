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

import ve.com.gem.entities.Nature;
import ve.com.gem.resources.NatureResource;
import ve.com.gem.resources.assembler.NatureResourceAssembler;
import ve.com.gem.services.INatureService;

@RestController
@RequestMapping(value = "/api/v1/natures")
public class NatureController {
	
	@Autowired
	INatureService service;
	
	@Autowired
	private NatureResourceAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<Nature> pageAssembler;

	public NatureController() {
		
	}

	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<NatureResource> loadAll(Pageable pageable){
		
		Page<Nature> objects = service.findAll(pageable);
	
		return pageAssembler.toResource(objects, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<NatureResource> load(@PathVariable Long id)
	{
		Nature object = service.findById(id);
		if(null == object)
		{
			return new ResponseEntity<NatureResource>(assembler.toResource(object),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<NatureResource>(assembler.toResource(object),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<NatureResource> save(@RequestBody Nature object)
	{
		if(service.save(object)!=null)
		{
			return new ResponseEntity<NatureResource>(assembler.toResource(object),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<NatureResource>(assembler.toResource(object),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<NatureResource> update(@PathVariable Long id,@RequestBody Nature object)
	{
		Nature search = service.findById(id);
		if(null == search)
		{
			return new ResponseEntity<NatureResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
		}else
		
		if(null != object )
		{
			object.setId(id);
			service.save(object);
			return new ResponseEntity<NatureResource>(assembler.toResource(search),HttpStatus.OK);
		}
		else
			return new ResponseEntity<NatureResource>(assembler.toResource(search),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Nature> delete(@PathVariable Long id){
		Nature search = service.findById(id);
		System.out.println(search);
		if(null != search)
		{
			service.delete(search);
			return new ResponseEntity<Nature>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Nature>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
