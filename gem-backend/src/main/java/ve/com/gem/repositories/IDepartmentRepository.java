package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Department;

@Repository
public interface IDepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    Department findByName(String name);
    
    Department findOne(Long id);
    
    List<Department> findByNameLike(String name, Pageable pageable);

    @SuppressWarnings("unchecked")
    Department save (Department object);
}
