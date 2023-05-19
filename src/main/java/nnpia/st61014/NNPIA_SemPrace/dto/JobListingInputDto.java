package nnpia.st61014.NNPIA_SemPrace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListingInputDto {
    private Long listingId;
    private String jobField;

    private String position;

    private Double pay;
    private AppUser listingPoster;
}
