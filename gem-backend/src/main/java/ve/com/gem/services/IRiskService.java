package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ve.com.gem.entities.Risk;

public interface IRiskService extends IService<Risk,Long>{
	 
	 Page<Risk> findAll(Pageable pageable);
	 Risk save (Risk object);
	 Risk findById(Long id);
	boolean delete(Risk object);
	 
}
