package pl.bartek030.foodApp.infrastructure.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FoodAppUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;
    private final UserDaoMapper userDaoMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserEntity user = userJpaRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(final Set<RoleEntity> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .distinct()
                .collect(Collectors.toList());
    }

    private UserDetails buildUserForAuthentication(final UserEntity user, final List<GrantedAuthority> authorities) {
        return new User(
                user.getUserName(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                authorities
        );
    }

    public pl.bartek030.foodApp.business.serviceModel.User createUser(
            final pl.bartek030.foodApp.business.serviceModel.User user
    ) {
        final UserEntity saved = userJpaRepository.save(userDaoMapper.mapUserToEntity(user));
        return userDaoMapper.mapUserFromEntity(saved);
    }
}
