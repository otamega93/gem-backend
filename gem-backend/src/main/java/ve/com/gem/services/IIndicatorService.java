package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Indicator;

public interface IIndicatorService {
	 
	 Page<Indicator> findAll(Pageable pageable);
	 Page<Indicator> findAll(Sort sort);
	 List<Indicator> search(String key);
	 void addByName(String name);
	 Indicator save (Indicator object);
	 Indicator findById(Long id);
	boolean delete(Indicator object);
	 
}
