package nnpia.st61014.NNPIA_SemPrace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersInterestedInJobWithDetailsDto {

    private Long app_user_userid;
    private String jobField;
    private String position;
    private Double pay;
    private String status;
    private LocalDateTime creation_date;

}
