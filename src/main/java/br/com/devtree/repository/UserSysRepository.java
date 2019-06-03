package br.com.devtree.repository;

import br.com.devtree.model.UserSys;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserSysRepository extends PagingAndSortingRepository<UserSys, Long> {
    UserSys findByUsername(String username);
}
