package nnpia.st61014.NNPIA_SemPrace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponseInputDto {
    @NotNull
    @NotBlank
    @Size(max = 256, min = 1)
    private String username;

    private String password;
}
