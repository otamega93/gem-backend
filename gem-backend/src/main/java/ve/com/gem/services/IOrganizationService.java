package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ve.com.gem.entities.Organization;

public interface IOrganizationService {

	public Organization save (Organization job);
	
	public Page<Organization> findAll(Pageable pageable);
	
	public Organization findById (Long id);
	
	boolean delete(Organization object);
}
