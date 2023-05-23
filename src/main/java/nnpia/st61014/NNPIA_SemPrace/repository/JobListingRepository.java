package nnpia.st61014.NNPIA_SemPrace.repository;

import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobListingRepository extends PagingAndSortingRepository<JobListing, Long> {
    JobListing findJobListingByListingIDEquals(long jobListingID);

    @Override
    Page<JobListing> findAll(Pageable pageable);
    Page<JobListing> findJobListingsByListingPoster_UserIDNot(long userId, Pageable pageable);
    List<JobListing> findAllByListingPosterUserID(long userId);
    JobListing save(JobListing jobListing);
    Integer deleteByListingIDAndListingPosterUserID(long listingId, long userId);
}
