package nnpia.st61014.NNPIA_SemPrace.repository;

import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobListingRepository extends PagingAndSortingRepository<JobListing, Long> {
        JobListing findJobListingByListingIDEquals(long jobListingID);
        List<JobListing> findAll();
        }
