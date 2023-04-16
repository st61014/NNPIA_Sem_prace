package nnpia.st61014.NNPIA_SemPrace.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class UsersInterestedInJob {
    @Column
    private String status;
    @Column
    private LocalDateTime creation_date;
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "listingID")
    private JobListing listingUserIsInterestedIn;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "userID")
    private AppUser userInterestedInListing;

/*
    @ManyToOne
    @JoinColumn(name="listingPosterID", nullable=false)
    @ToString.Exclude
    private AppUser userInterestedInJob;

 */
}
