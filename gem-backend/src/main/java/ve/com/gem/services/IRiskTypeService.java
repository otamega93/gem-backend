package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.RiskType;

public interface IRiskTypeService {
	 
	 Page<RiskType> findAll(Pageable pageable);
	 Page<RiskType> findAll(Sort sort);
	 List<RiskType> search(String key);
	 void addByName(String name);
	 RiskType save (RiskType object);
	 RiskType findById(Long id);
	boolean delete(RiskType object);
	 
}
