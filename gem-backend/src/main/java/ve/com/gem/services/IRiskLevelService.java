package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.RiskLevel;

public interface IRiskLevelService {
	 
	 Page<RiskLevel> findAll(Pageable pageable);
	 Page<RiskLevel> findAll(Sort sort);
	 List<RiskLevel> search(String key);
	 void addByName(String name);
	 RiskLevel save (RiskLevel object);
	 RiskLevel findById(Long id);
	boolean delete(RiskLevel object);
	 
}
