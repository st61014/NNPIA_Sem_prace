package nnpia.st61014.NNPIA_SemPrace.repository;

import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
    AppUser findAppUserByUserIDEquals(long userId);
    Optional<AppUser> findAppUserByUsername(String username);
}

