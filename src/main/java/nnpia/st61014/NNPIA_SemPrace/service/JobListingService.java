package nnpia.st61014.NNPIA_SemPrace.service;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import nnpia.st61014.NNPIA_SemPrace.repository.AppUserRepository;
import nnpia.st61014.NNPIA_SemPrace.repository.JobListingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class JobListingService {
    private final JobListingRepository jobListingRepository;
    @Transactional
    public JobListing save(final JobListing jobListing) {
        return jobListingRepository.save(jobListing);
    }
    @Transactional(readOnly = true)
    public JobListing findById(Long id) throws ResourceNotFoundException {
        var result = jobListingRepository.findJobListingByListingIDEquals(id);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<JobListing> findAll(int page, String sortBy) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        var result = jobListingRepository.findAll(pageable).getContent();

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<JobListing> findAllNotOwnedByUser(final long userId, int page, String sortBy) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        var result = jobListingRepository.findJobListingsByListingPoster_UserIDNot(userId, pageable).getContent();

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }
    @Transactional(readOnly = true)
    public List<JobListing> findAllById(final long userId) throws ResourceNotFoundException {
        var result = jobListingRepository.findAllByListingPosterUserID(userId);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }
    @Transactional
    public Integer remove(final long listingId, final long userId) throws ResourceNotFoundException {
        var result = jobListingRepository.deleteByListingIDAndListingPosterUserID(listingId, userId);

        if (result == null) {
            throw new ResourceNotFoundException();
        }

        return result;
    }
}
