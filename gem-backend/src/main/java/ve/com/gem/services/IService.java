package ve.com.gem.services;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T, ID extends Serializable> {

	T save(T object);
	Page<T> findAll(Pageable pageable);
	T findById(ID id);
	boolean delete(T object);
}
