package nnpia.st61014.NNPIA_SemPrace.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import nnpia.st61014.NNPIA_SemPrace.dto.AppUserResponseDto;
import nnpia.st61014.NNPIA_SemPrace.dto.AppUserResponseInputDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.*;

import java.util.Collections;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<JobListing> jobListings = Collections.emptyList();

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
    private AppUser toEntity(final AppUserResponseInputDto input) {
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
