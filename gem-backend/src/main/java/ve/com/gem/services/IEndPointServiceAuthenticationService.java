package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.EndPointServiceAuthentication;
import ve.com.gem.resources.EndPointServiceAuthenticationResource;

public interface IEndPointServiceAuthenticationService {

	public EndPointServiceAuthentication save (EndPointServiceAuthentication endPointServiceAuthentication);

	public Page<EndPointServiceAuthentication> findAll(Pageable pageable);
	
	public EndPointServiceAuthentication findById(Long id);
	
	public boolean delete(Long id);
	
}
