package nnpia.st61014.NNPIA_SemPrace.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Column
    private Integer listingPosterID;
    /*
    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    @ToString.Exclude
    private AppUser author;
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
