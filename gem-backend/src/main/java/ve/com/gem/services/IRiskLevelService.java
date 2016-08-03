package ve.com.gem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ve.com.gem.entities.RiskLevel;

public interface IRiskLevelService extends IService<RiskLevel,Long> {
	 
	 Page<RiskLevel> findAll(Pageable pageable);
	 RiskLevel save (RiskLevel object);
	 RiskLevel findById(Long id);
	boolean delete(RiskLevel object);
	 
}
