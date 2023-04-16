package nnpia.st61014.NNPIA_SemPrace.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponseDto {
    private Long userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String currentWorkingField;
    private List<JobListing> jobListings = Collections.emptyList();

}
