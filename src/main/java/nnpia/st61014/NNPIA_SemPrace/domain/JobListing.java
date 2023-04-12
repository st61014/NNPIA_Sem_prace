package nnpia.st61014.NNPIA_SemPrace.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class JobListing {
    @Id
    private Long listingID;
    @Column
    private String jobField;
    @Column
    private String position;
    @Column
    private Double pay;

    @ManyToOne
    @JoinColumn(name="listingPosterID", nullable=false)
    @ToString.Exclude
    private AppUser listingPoster;

    @ManyToMany
    @JoinTable(
            name = "UsersInterestedInJob",
            joinColumns = @JoinColumn(name = "chosenJobListingID"),
            inverseJoinColumns = @JoinColumn(name = "interestedUserID")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<AppUser> usersInterestedInListing = Collections.emptyList();




}
