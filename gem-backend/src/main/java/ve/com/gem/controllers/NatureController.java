package ve.com.gem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.web.PagedResourcesAssembler;
import ve.com.gem.services.INatureService;
import ve.com.gem.entities.Nature;
import ve.com.gem.resources.assembler.NatureResourceAssembler;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1/natures")
public class NatureController {
	
	@Autowired
	private INatureService service;
	
	@Autowired
	private NatureResourceAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<Nature> pageAssembler;

	public NatureController() {
		
	}

}
