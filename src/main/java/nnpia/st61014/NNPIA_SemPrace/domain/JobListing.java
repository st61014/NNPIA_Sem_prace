package nnpia.st61014.NNPIA_SemPrace.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nnpia.st61014.NNPIA_SemPrace.dto.AppUserResponseDto;
import nnpia.st61014.NNPIA_SemPrace.dto.AppUserResponseInputDto;
import nnpia.st61014.NNPIA_SemPrace.dto.JobListingInputDto;
import nnpia.st61014.NNPIA_SemPrace.dto.JobListingResponseDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listingID;
    @Column
    private String jobField;
    @Column
    private String position;
    @Column
    private Double pay;

    @ManyToOne()
    @JoinColumn(name="listingPosterID", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@ToString.Exclude
    @JsonBackReference
    private AppUser listingPoster;

    public JobListing(String jobField, String position, Double pay, AppUser listingPoster) {
        this.jobField = jobField;
        this.position = position;
        this.pay = pay;
        this.listingPoster = listingPoster;
    }

    /*
        @ManyToMany
        @JoinTable(
                name = "UsersInterestedInJob",
                joinColumns = @JoinColumn(name = "chosenJobListingID"),
                inverseJoinColumns = @JoinColumn(name = "interestedUserID")
        )
        @ToString.Exclude
        @JsonIgnore
        private List<AppUser> usersInterestedInListing = Collections.emptyList();


         */
    public static JobListing toEntity(final JobListingInputDto input) {
        return new JobListing(input.getJobField(), input.getPosition(), input.getPay(), input.getListingPoster());
    }
    public JobListingResponseDto toDto() {
        return new JobListingResponseDto(
                getListingID(),
                getJobField(),
                getPosition(),
                getPay(),
                getListingPoster().getUserID()
        );
    }

}
