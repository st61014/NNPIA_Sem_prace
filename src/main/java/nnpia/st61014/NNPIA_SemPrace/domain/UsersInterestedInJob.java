package nnpia.st61014.NNPIA_SemPrace.domain;

import jakarta.persistence.*;
import lombok.*;
import nnpia.st61014.NNPIA_SemPrace.dto.JobListingInputDto;
import nnpia.st61014.NNPIA_SemPrace.dto.UsersInterestedInJobDto;
import nnpia.st61014.NNPIA_SemPrace.dto.UsersInterestedInJobInputDto;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_interested_in_job")
@IdClass(UserJobId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersInterestedInJob {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_userid", referencedColumnName = "userid")
    private AppUser appUser;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_listing_listingid", referencedColumnName = "listingid")
    private JobListing jobListing;
    @Column
    private String status;
    @Column
    private LocalDateTime creation_date;

    public static UsersInterestedInJob toEntity(final UsersInterestedInJobInputDto input) {
        return new UsersInterestedInJob(input.getApp_user(), input.getJob_listing(), input.getStatus(), input.getCreation_date());
    }
    public UsersInterestedInJobDto toDto() {
        return new UsersInterestedInJobDto(
                getAppUser().getUserID(),
                getJobListing().getListingID(),
                getStatus(),
                getCreation_date().toString()
        );
    }
}

@Embeddable
class UserJobId implements Serializable {

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private JobListing jobListing;


}
