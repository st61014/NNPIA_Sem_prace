package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.UsersInterestedInJob;
import nnpia.st61014.NNPIA_SemPrace.dto.UsersInterestedInJobDto;
import nnpia.st61014.NNPIA_SemPrace.security.JWTGenerator;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
import nnpia.st61014.NNPIA_SemPrace.service.UsersInterestedInJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/job-interest")
@AllArgsConstructor
public class UsersInterestedInJobController {
    private final UsersInterestedInJobService usersInterestedInJobService;
    @Autowired
    JWTGenerator idDecode;
    @GetMapping("/user")
    public ResponseEntity<?> findByUserId(@RequestHeader (name="Authorization") String token) throws ResourceNotFoundException {
        var result = usersInterestedInJobService.findByUserId(Long.parseLong(idDecode.getIdFromJWT(token.substring(7))));
        List<UsersInterestedInJobDto> DTOs = new ArrayList<>();
        for (UsersInterestedInJob record : result) {
            DTOs.add(record.toDto());
        }
        return ResponseEntity.ok(DTOs);
    }
    @GetMapping("/job/{id}")
    public ResponseEntity<?> findByJobId(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = usersInterestedInJobService.findByJobId(id);

        return ResponseEntity.ok(result.toDto());
    }
}
