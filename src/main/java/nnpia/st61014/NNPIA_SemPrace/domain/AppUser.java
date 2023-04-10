package nnpia.st61014.NNPIA_SemPrace.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.*;


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
    /*
    @OneToMany(mappedBy = "author")
    @EqualsAndHashCode.Exclude
    private List<JobListing> listings = Collections.emptyList();
    @ManyToMany(mappedBy = "users")
    @EqualsAndHashCode.Exclude
    private List<Role> roles = Collections.emptyList();
    */
    public AppUser(Integer id, String username, String password, String firstName,String lastName,String currentWorkingField) {
        this.userID = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentWorkingField = currentWorkingField;
    }

    public AppUser(String username, String password, String firstName,String lastName,String currentWorkingField) {
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
