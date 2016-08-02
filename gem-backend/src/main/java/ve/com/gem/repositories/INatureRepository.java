package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ve.com.gem.entities.Nature;

@Repository
public interface INatureRepository extends PagingAndSortingRepository<Nature, Long>{
	
	public List<Nature> findById(Long id);
	public List<Nature> findByNameLike(String name);
	
	@Modifying
	@Query("delete from Nature where id = ?1")
	void delete(Long id);

}
