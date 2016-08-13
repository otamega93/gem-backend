package ve.com.gem.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ve.com.gem.entities.Organization;

@Repository
public interface IOrganizationRepository extends PagingAndSortingRepository<Organization, Long> {

	public List<Organization> findOrganizationByCompanyId(Long id);

	Organization findByName(String name);

	Organization findOne(Long id);

	List<Organization> findByNameLike(String name, Pageable pageable);
	List<Organization> findByNameLike(String name);

	@SuppressWarnings("unchecked")
	Organization save(Organization object);

}
