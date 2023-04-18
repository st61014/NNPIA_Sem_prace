package nnpia.st61014.NNPIA_SemPrace.repository;

import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import nnpia.st61014.NNPIA_SemPrace.domain.UsersInterestedInJob;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsersInterestedInJobRepository extends PagingAndSortingRepository<UsersInterestedInJob,Long> {
    UsersInterestedInJob findUsersInterestedInJobByAppUserUserID(long userId);
    UsersInterestedInJob findUsersInterestedInJobByJobListingListingID(long listingId);
}

