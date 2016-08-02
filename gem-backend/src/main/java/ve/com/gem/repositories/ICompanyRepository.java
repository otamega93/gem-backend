package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Company;

@Repository
public interface ICompanyRepository extends PagingAndSortingRepository<Company, Long> {

    Company findByName(String name);
    
    Company findOne(Long id);
    
    List<Company> findByNameLike(String name, Pageable pageable);
    List<Company> findByNameLike(String name);
    
    @SuppressWarnings("unchecked")
	Company save (Company object);
}
