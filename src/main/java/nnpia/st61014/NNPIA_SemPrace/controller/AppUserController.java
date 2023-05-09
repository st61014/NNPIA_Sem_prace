package nnpia.st61014.NNPIA_SemPrace.controller;


import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.service.AppUserService;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-user")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = appUserService.findById(id);

        return ResponseEntity.ok(result.toDto());
    }

/*
    @PostMapping("")
    public ResponseEntity<AppUserResponseDtoV1> create(@RequestBody @Validated final AppUserResponseInputDtoV1 input) {
        var result = appUserService.create(toEntity(input));

        return ResponseEntity.ok(result.toDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserResponseDtoV1> update(@PathVariable final Long id, @RequestBody final AppUserResponseInputDtoV1 input) {
        var result = appUserService.update(toEntity(id, input));

        return ResponseEntity.ok(result.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        appUserService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private static AppUser toEntity(final AppUserResponseInputDtoV1 input) {
        return new AppUser(
                input.getUsername(),
                input.getPassword(),
                input.getActive(),
                input.getCreationDate(),
                input.getUpdateDate()
        );
    }

    private static AppUser toEntity(final Long id, final AppUserResponseInputDtoV1 input) {
        return new AppUser(
                id,
                input.getUsername(),
                input.getPassword(),
                input.getActive(),
                input.getCreationDate(),
                input.getUpdateDate()
        );
    }
    */
}
