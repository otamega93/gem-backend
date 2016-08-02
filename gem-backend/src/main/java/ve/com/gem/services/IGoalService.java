package ve.com.gem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ve.com.gem.entities.Goal;

public interface IGoalService {
	 
	 Page<Goal> findAll(Pageable pageable);
	 Page<Goal> findAll(Sort sort);
	 List<Goal> search(String key);
	 void addByName(String name);
	 Goal save (Goal gem);
	 Goal findById(Long id);
	boolean delete(Goal gemCatch);
	 
}
