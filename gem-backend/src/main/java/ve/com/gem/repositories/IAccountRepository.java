package ve.com.gem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ve.com.gem.entities.Account;

@Repository
public interface IAccountRepository extends PagingAndSortingRepository<Account, Long> {

    Account findByUsername(String username);
    
    Account findOne(Long id);
    
	@Query("SELECT account FROM Account account WHERE account.username LIKE CONCAT('%',?1,'%')")
    List<Account> findByUsernameLike(String username, Pageable pageable);

    @SuppressWarnings("unchecked")
	Account save (Account account);
}
