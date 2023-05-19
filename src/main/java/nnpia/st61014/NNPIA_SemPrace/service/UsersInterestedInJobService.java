package nnpia.st61014.NNPIA_SemPrace.service;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.UsersInterestedInJob;
import nnpia.st61014.NNPIA_SemPrace.repository.AppUserRepository;
import nnpia.st61014.NNPIA_SemPrace.repository.UsersInterestedInJobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersInterestedInJobService {
    private final UsersInterestedInJobRepository usersInterestedInJobRepositoryRepository;

    @Transactional(readOnly = true)
    public List<UsersInterestedInJob> findByUserId(Long id) throws ResourceNotFoundException {
        var result = usersInterestedInJobRepositoryRepository.findUsersInterestedInJobByAppUserUserID(id);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }
    @Transactional(readOnly = true)
    public Page<Object[]> findInterestsByUserIdWithJobDetails(Long id, int page) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page, 5);
        var result = usersInterestedInJobRepositoryRepository.findInterestsByUserIdWithJobDetails(id, pageable);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }
    @Transactional(readOnly = true)
    public UsersInterestedInJob findByJobId(Long id) throws ResourceNotFoundException {
        var result = usersInterestedInJobRepositoryRepository.findUsersInterestedInJobByJobListingListingID(id);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }
}
