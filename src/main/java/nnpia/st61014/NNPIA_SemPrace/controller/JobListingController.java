package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import nnpia.st61014.NNPIA_SemPrace.domain.UsersInterestedInJob;
import nnpia.st61014.NNPIA_SemPrace.dto.JobListingInputDto;
import nnpia.st61014.NNPIA_SemPrace.dto.JobListingResponseDto;
import nnpia.st61014.NNPIA_SemPrace.security.JWTGenerator;
import nnpia.st61014.NNPIA_SemPrace.service.AppUserService;
import nnpia.st61014.NNPIA_SemPrace.service.JobListingService;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
import nnpia.st61014.NNPIA_SemPrace.service.UsersInterestedInJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static nnpia.st61014.NNPIA_SemPrace.domain.JobListing.toEntity;

@RestController
@RequestMapping("/job-listing")
@AllArgsConstructor

public class JobListingController {
    private final JobListingService jobListingService;
    private final AppUserService appUserService;

    private final UsersInterestedInJobService usersInterestedInJobService;
    @Autowired
    JWTGenerator idDecode;
    @PutMapping("/create")
    public ResponseEntity<JobListingResponseDto> create(@RequestHeader (name="Authorization") String token, @RequestBody @Validated final JobListingInputDto input) throws ResourceNotFoundException {
        input.setListingPoster(appUserService.findById(Long.parseLong(idDecode.getIdFromJWT(token.substring(7)))));
        var result = jobListingService.save(toEntity(input));

        return ResponseEntity.ok(result.toDto());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = jobListingService.findById(id);

        return ResponseEntity.ok(result.toDto());
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() throws ResourceNotFoundException {
        var result = jobListingService.findAll();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/owned")
    public ResponseEntity<?> findAllById(@RequestHeader (name="Authorization") String token) throws ResourceNotFoundException {
        var result = jobListingService.findAllById(Long.parseLong(idDecode.getIdFromJWT(token.substring(7))));

        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestHeader (name="Authorization") String token, @RequestBody final String jobId) throws ResourceNotFoundException {
        usersInterestedInJobService.deleteAllByJobListingListingID(Long.parseLong(jobId));
        var result = jobListingService.remove(Long.parseLong(jobId),Long.parseLong(idDecode.getIdFromJWT(token.substring(7))));

        return ResponseEntity.ok(result);
    }
}
