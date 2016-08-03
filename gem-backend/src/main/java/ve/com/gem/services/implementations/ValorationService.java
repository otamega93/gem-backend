package ve.com.gem.services.implementations;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import ve.com.gem.entities.Valoration;
import ve.com.gem.services.IValorationService;
@Service
public class ValorationService extends BasicService<Valoration, Long, PagingAndSortingRepository<Valoration, Long>> implements IValorationService{

}
