package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.RiskType;

public interface IRiskTypeService extends IService<RiskType, Long>{
	 
	 Page<RiskType> findAll(Pageable pageable);
	 RiskType save (RiskType object);
	 RiskType findById(Long id);
	boolean delete(RiskType object);
	 
}
