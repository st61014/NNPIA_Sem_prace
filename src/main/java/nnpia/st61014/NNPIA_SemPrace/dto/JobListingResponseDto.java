package nnpia.st61014.NNPIA_SemPrace.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListingResponseDto {

    private Long listingID;

    private String jobField;

    private String position;

    private Double pay;

    private Long listingPosterId;
}
