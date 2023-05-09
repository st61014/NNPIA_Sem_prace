package nnpia.st61014.NNPIA_SemPrace.service;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.repository.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Transactional(readOnly = true)
    public AppUser findById(Long id) throws ResourceNotFoundException {
        var result = appUserRepository.findAppUserByUserIDEquals(id);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findAppUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("USER"));
        return new User(user.getUsername(), user.getPassword(), auths);
    }
    /*
    public AppUser findByUsernameAndPassword(String username, String password) throws ResourceNotFoundException {
        var result = appUserRepository.findAppUserByUsernameAndPassword(username, password);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result.get();
    }

     */
/*
    @Transactional
    public AppUser create(final AppUser appUser) {
       return appUserRepository.save(appUser);
    }

    @Transactional
    public AppUser update(final AppUser toEntity) {
        return appUserRepository.save(toEntity);
    }

    @Transactional
    public void delete(final Long id) {
        appUserRepository.deleteById(id);
    }

 */
}
