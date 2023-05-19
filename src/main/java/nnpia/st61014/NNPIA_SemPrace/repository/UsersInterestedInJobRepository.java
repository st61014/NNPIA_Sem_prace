package nnpia.st61014.NNPIA_SemPrace.repository;

import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import nnpia.st61014.NNPIA_SemPrace.domain.UsersInterestedInJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersInterestedInJobRepository extends PagingAndSortingRepository<UsersInterestedInJob,Long> {
    List<UsersInterestedInJob> findUsersInterestedInJobByAppUserUserID(long userId);
    UsersInterestedInJob findUsersInterestedInJobByJobListingListingID(long listingId);
    @Query("SELECT j.jobField, j.position, j.pay, i.creation_date,i.status FROM UsersInterestedInJob i JOIN JobListing j ON i.jobListing.listingID = j.listingID WHERE i.appUser.userID = :userId")
    Page<Object[]> findInterestsByUserIdWithJobDetails(@Param("userId") Long userId, Pageable pageable);
}


