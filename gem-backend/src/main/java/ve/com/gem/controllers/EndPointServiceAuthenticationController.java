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
import org.springframework.web.bind.annotation.RestController;

import ve.com.gem.entities.EndPointServiceAuthentication;
import ve.com.gem.resources.EndPointServiceAuthenticationResource;
import ve.com.gem.resources.assembler.EndPointServiceAuthenticationResourceAssembler;
import ve.com.gem.services.IEndPointServiceAuthenticationService;

@RequestMapping(value = "/api/v1/endPointServiceAuthentication")
@RestController
public class EndPointServiceAuthenticationController {

	@Autowired
	private IEndPointServiceAuthenticationService endPointServiceAuthenticationService;

	@Autowired
	private EndPointServiceAuthenticationResourceAssembler endPointServiceAuthenticationAssembler;

	@Autowired
	private PagedResourcesAssembler<EndPointServiceAuthentication> pageAssembler;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<EndPointServiceAuthenticationResource> saveEndPointServiceAuthentication(@RequestBody EndPointServiceAuthentication endPointServiceAuthentication) {

		if (null != endPointServiceAuthentication && endPointServiceAuthenticationService.findById(endPointServiceAuthentication.getId()) == null) {

			endPointServiceAuthenticationService.save(endPointServiceAuthentication);
			return new ResponseEntity<EndPointServiceAuthenticationResource>(endPointServiceAuthenticationAssembler.toResource(endPointServiceAuthentication), HttpStatus.OK);
		}

		else {
			return new ResponseEntity<EndPointServiceAuthenticationResource>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EndPointServiceAuthenticationResource> findById(@PathVariable Long id) {

		if (null != id) {

			EndPointServiceAuthentication endPointServiceAuthentication = endPointServiceAuthenticationService.findById(id);
		
			if (null != endPointServiceAuthentication) {

				return new ResponseEntity<EndPointServiceAuthenticationResource>(endPointServiceAuthenticationAssembler.toResource(endPointServiceAuthentication), HttpStatus.OK);
			}
		
			else {

				return new ResponseEntity<EndPointServiceAuthenticationResource>(HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<EndPointServiceAuthenticationResource>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public PagedResources<EndPointServiceAuthenticationResource> loadAll(Pageable pageable) {

		Page<EndPointServiceAuthentication> endPointServiceAuthentication = endPointServiceAuthenticationService.findAll(pageable);
		return pageAssembler.toResource(endPointServiceAuthentication, endPointServiceAuthenticationAssembler);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<EndPointServiceAuthenticationResource> updateAccount(@PathVariable Long id, @RequestBody EndPointServiceAuthentication endPointServiceAuthentication) {

		EndPointServiceAuthentication endPointServiceAuthenticationSearch = endPointServiceAuthenticationService.findById(id);

		if (null == endPointServiceAuthenticationSearch) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (null != endPointServiceAuthentication) {
			
			endPointServiceAuthentication.setId(id);
			endPointServiceAuthenticationService.save(endPointServiceAuthentication);
			return new ResponseEntity<EndPointServiceAuthenticationResource>(endPointServiceAuthenticationAssembler.toResource(endPointServiceAuthentication), HttpStatus.OK);
		}

		else {
			
			return new ResponseEntity<EndPointServiceAuthenticationResource>(endPointServiceAuthenticationAssembler.toResource(endPointServiceAuthentication),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces = "application/json; charset=UTF-8")
	public ResponseEntity<EndPointServiceAuthenticationResource> delete(@PathVariable Long id){
		EndPointServiceAuthentication endPointServiceAuthenticationSearch = endPointServiceAuthenticationService.findById(id);
		System.out.println(endPointServiceAuthenticationSearch);
		if(null != endPointServiceAuthenticationSearch)
		{
			endPointServiceAuthenticationService.delete(id);
			return new ResponseEntity<EndPointServiceAuthenticationResource>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<EndPointServiceAuthenticationResource>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
