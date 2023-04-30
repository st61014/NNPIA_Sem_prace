package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.service.AppUserService;
import nnpia.st61014.NNPIA_SemPrace.service.JobListingService;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/job-listing")
@AllArgsConstructor
public class JobListingController {
    private final JobListingService jobListingService;

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
