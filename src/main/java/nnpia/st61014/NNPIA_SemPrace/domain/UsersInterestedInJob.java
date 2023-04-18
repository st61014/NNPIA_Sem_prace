package nnpia.st61014.NNPIA_SemPrace.domain;

import jakarta.persistence.*;
import lombok.*;
import nnpia.st61014.NNPIA_SemPrace.dto.AppUserResponseDto;
import nnpia.st61014.NNPIA_SemPrace.dto.UsersInterstedInJobDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

    public UsersInterstedInJobDto toDto() {
        return new UsersInterstedInJobDto(
                getAppUser().getUserID(),
                getJobListing().getListingID(),
                getStatus(),
                getCreation_date()
        );
    }
}

@Embeddable
class UserJobId implements Serializable {

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private JobListing jobListing;

    // Constructors, getters, and setters
}
