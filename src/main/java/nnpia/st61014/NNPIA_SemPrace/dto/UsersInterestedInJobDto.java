package nnpia.st61014.NNPIA_SemPrace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersInterestedInJobDto {

    private Long app_user_userid;
    private Long job_listing_listingid;
    private String status;
    private String creation_date;

}
