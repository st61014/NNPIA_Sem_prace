package nnpia.st61014.NNPIA_SemPrace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersInterstedInJobDto {

    private Long app_user_userid;
    private Long job_listing_listingid;
    private String status;
    private LocalDateTime creation_date;

}
