package nnpia.st61014.NNPIA_SemPrace.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import nnpia.st61014.NNPIA_SemPrace.dto.AppUserResponseDto;
import nnpia.st61014.NNPIA_SemPrace.dto.AppUserResponseInputDto;
import org.hibernate.validator.constraints.*;

import java.util.Collections;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    private Long userID;
    @Column
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    private String username;
    @Column
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    private String password;
    @Column
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    private String firstName;
    @Column
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    private String lastName;
    @Column
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 255)
    private String currentWorkingField;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "listingPoster")
    @JsonManagedReference
    private List<JobListing> jobListings = Collections.emptyList();
/*
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "userInterestedInListing")
    private List<UsersInterestedInJob> interestedInJobs = Collections.emptyList();

 */


/*
    @ManyToMany(mappedBy = "usersInterestedInListing")
    @ToString.Exclude
    @JsonIgnore
    private List<AppUser> interestedListings = Collections.emptyList();

 */

    public AppUser(Long id, String username, String password, String firstName, String lastName, String currentWorkingField) {
        this.userID = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentWorkingField = currentWorkingField;
    }

    public AppUser(String username, String password, String firstName, String lastName, String currentWorkingField) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentWorkingField = currentWorkingField;
    }
    private static AppUser toEntity(final AppUserResponseInputDto input) {
        return new AppUser(
                input.getUsername(),
                input.getPassword(),
                "",
                "",
                ""
        );
    }
    public AppUserResponseDto toDto() {
        return new AppUserResponseDto(
                getUserID(),
                getUsername(),
                getPassword(),
                getFirstName(),
                getLastName(),
                getCurrentWorkingField(),
                getJobListings()
        );
    }


}
