package com.dgliger.security;

import com.dgliger.model.User;
import com.dgliger.repository.UserRepository;
import com.dgliger.services.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSecurityService implements UserDetailsService {

    public static final String NO_SUCH_USER_EXISTS = "No Such User Exists!!!";
    private UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(NO_SUCH_USER_EXISTS));

        return UserDetailsImpl.build(user);
    }
}

