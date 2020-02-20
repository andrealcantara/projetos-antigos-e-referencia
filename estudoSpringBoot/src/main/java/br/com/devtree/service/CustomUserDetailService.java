package br.com.devtree.service;

import br.com.devtree.model.UserSys;
import br.com.devtree.repository.UserSysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserSysRepository userSysRepository;

    @Autowired
    public CustomUserDetailService(UserSysRepository userSysRepository) {
        this.userSysRepository = userSysRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSys user = Optional.ofNullable(userSysRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

        User retorno = new User(user.getUsername(), user.getPassword(),
                user.isEhAdmin() ? authorityListAdmin :authorityListUser);
        return retorno;
    }
}
