package nnpia.st61014.NNPIA_SemPrace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersInterestedInJobInputDto {

    private AppUser app_user;
    private JobListing job_listing;
    private String status;
    private LocalDateTime creation_date;

}
