package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Employee;

@Repository
public interface IExpertRepository extends PagingAndSortingRepository<Employee, Long> {

	Employee findByName(String name);
    
	Employee findOne(Long id);
    
    List<Employee> findByNameLike(String name, Pageable pageable);
    List<Employee> findByNameLike(String name);
    @SuppressWarnings("unchecked")
    Employee save (Employee object);
}
