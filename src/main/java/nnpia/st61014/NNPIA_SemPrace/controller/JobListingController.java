package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.JobListing;
import nnpia.st61014.NNPIA_SemPrace.dto.JobListingInputDto;
import nnpia.st61014.NNPIA_SemPrace.dto.JobListingResponseDto;
import nnpia.st61014.NNPIA_SemPrace.security.JWTGenerator;
import nnpia.st61014.NNPIA_SemPrace.service.AppUserService;
import nnpia.st61014.NNPIA_SemPrace.service.JobListingService;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
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
    @Autowired
    JWTGenerator idDecode;
    @PostMapping("/create")
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
}
