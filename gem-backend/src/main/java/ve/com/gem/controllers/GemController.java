package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ve.com.gem.entities.Gem;
import ve.com.gem.resources.GemResource;
import ve.com.gem.resources.assembler.GemResourceAssembler;
import ve.com.gem.services.IGemService;

@RestController
@RequestMapping(value = "/api/v1/gems")
public class GemController {
	
	@Autowired
	IGemService service;
	
	@Autowired
	private GemResourceAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Gem> pageAssembler;
	
	public GemController() {
	}
	
	/**
	 * List all gems.
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/hal+json")
	@ResponseBody
	public PagedResources<GemResource> loadAll(Pageable pageable){
		
		Page<Gem> gems = service.findAll(pageable);
		//List<Gem> gems = gemService.findAll();
		//return new ResponseEntity<PagedResources<GemResource>>(gems,HttpStatus.OK);

		return pageAssembler.toResource(gems, assembler);
	}
	
	/**
	 * Find one gem.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<GemResource> load(@PathVariable Long id)
	{
		Gem gem = service.findById(id);
		if(null == gem)
		{
			return new ResponseEntity<GemResource>(assembler.toResource(gem),HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<GemResource>(assembler.toResource(gem),HttpStatus.OK);
		}
	}
	
	
    
	@RequestMapping(value="",method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<GemResource> save(@RequestBody Gem gem)
	{
		if(service.save(gem)!=null)
		{
			return new ResponseEntity<GemResource>(assembler.toResource(gem),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<GemResource>(assembler.toResource(gem),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<GemResource> update(@PathVariable Long id,@RequestBody Gem gem)
	{
		Gem gemCatch = service.findById(id);
		if(null == gemCatch)
		{
			return new ResponseEntity<GemResource>(assembler.toResource(gemCatch),HttpStatus.NOT_FOUND);
		}else
		
		if(null != gem )
		{
			gem.setId(id);
			service.save(gem);
			return new ResponseEntity<GemResource>(assembler.toResource(gemCatch),HttpStatus.OK);
		}
		else
			return new ResponseEntity<GemResource>(assembler.toResource(gemCatch),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<Gem> delete(@PathVariable Long id){
		Gem gemCatch = service.findById(id);
		System.out.println(gemCatch);
		if(null != gemCatch)
		{
			service.delete(gemCatch);
			return new ResponseEntity<Gem>(HttpStatus.OK);
		}
		else
		{
				return new ResponseEntity<Gem>(HttpStatus.BAD_REQUEST);
		}
	}
}