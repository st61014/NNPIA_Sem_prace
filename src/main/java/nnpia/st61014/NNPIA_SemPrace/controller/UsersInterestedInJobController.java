package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
import nnpia.st61014.NNPIA_SemPrace.service.UsersInterestedInJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-interest")
@AllArgsConstructor
public class UsersInterestedInJobController {
    private final UsersInterestedInJobService usersInterestedInJobService;
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = usersInterestedInJobService.findByUserId(id);

        return ResponseEntity.ok(result.toDto());
    }
    @GetMapping("/job/{id}")
    public ResponseEntity<?> findByJobId(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = usersInterestedInJobService.findByJobId(id);

        return ResponseEntity.ok(result.toDto());
    }
}
