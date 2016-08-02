package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.Nature;

public interface INatureService {
	
	public Nature save(Nature nature);
	
	public Page<Nature> findAll(Pageable pageable);
	
	public Page<Nature> findByNameLike(Pageable pageable, String name);
	

}
