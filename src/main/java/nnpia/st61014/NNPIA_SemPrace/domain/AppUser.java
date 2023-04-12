package nnpia.st61014.NNPIA_SemPrace.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.*;

import java.util.Collections;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    private Integer userID;
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
    private List<JobListing> jobListings = Collections.emptyList();

    @ManyToMany
    @JoinTable(
            name = "UsersInterestedInJob",
            joinColumns = @JoinColumn(name = "interestedUserID"),
            inverseJoinColumns = @JoinColumn(name = "chosenJobListingID")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<AppUser> interestedListings = Collections.emptyList();

    public AppUser(Integer id, String username, String password, String firstName, String lastName, String currentWorkingField) {
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
/*
    public AppUserResponseDtoV1 toDto() {
        return new AppUserResponseDtoV1(
                getUserID(),
                getUsername(),
                getPassword(),
                getActive(),
                getCreation_date(),
                getUpdate_date(),
                getRoles()
        );
    }
    */

}
