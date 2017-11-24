package ve.com.gem.services.implementations;

import org.springframework.stereotype.Service;

import ve.com.gem.entities.Valoration;
import ve.com.gem.repositories.IValorationRepository;
import ve.com.gem.services.IValorationService;

@Service
public class ValorationService extends BasicService<Valoration, Long, IValorationRepository> implements IValorationService{

}
