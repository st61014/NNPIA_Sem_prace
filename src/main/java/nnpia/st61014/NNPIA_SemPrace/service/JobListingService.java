package nnpia.st61014.NNPIA_SemPrace.service;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import nnpia.st61014.NNPIA_SemPrace.repository.AppUserRepository;
import nnpia.st61014.NNPIA_SemPrace.repository.JobListingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class JobListingService {
    private final JobListingRepository jobListingRepository;

    @Transactional(readOnly = true)
    public JobListing findById(Long id) throws ResourceNotFoundException {
        var result = jobListingRepository.findJobListingByListingIDEquals(id);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<JobListing> findAll() throws ResourceNotFoundException {
        var result = jobListingRepository.findAll();

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }
}
