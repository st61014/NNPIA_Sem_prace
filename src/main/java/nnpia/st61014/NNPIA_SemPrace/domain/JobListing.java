package nnpia.st61014.NNPIA_SemPrace.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class JobListing {
    @Id
    private Integer listingID;
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
    /*
    public TaskDtoV1 toDto() {
        return new TaskDtoV1(
                getTaskID(),
                getTitle(),
                getDescription(),
                getCreation_date(),
                getUpdate_date()
        );
    }

     */


}
