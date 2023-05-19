package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.domain.UsersInterestedInJob;
import nnpia.st61014.NNPIA_SemPrace.dto.UsersInterestedInJobDto;
import nnpia.st61014.NNPIA_SemPrace.security.JWTGenerator;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
import nnpia.st61014.NNPIA_SemPrace.service.UsersInterestedInJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/test")
    public ResponseEntity<List<Map<String, Object>>> getInterestsByUserId(
            @RequestHeader (name="Authorization") String token,
            @RequestParam("page") int page) throws ResourceNotFoundException {

        Page<Object[]> interests = usersInterestedInJobService.findInterestsByUserIdWithJobDetails(Long.parseLong(idDecode.getIdFromJWT(token.substring(7))), page);
        List<Object[]> content = interests.getContent();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] interest : interests) {
            Map<String, Object> interestMap = new LinkedHashMap<>();
            interestMap.put("job_field", interest[0]);
            interestMap.put("position", interest[1]);
            interestMap.put("pay", interest[2]);
            interestMap.put("creation_date", interest[3]);
            interestMap.put("status", interest[4]);
            response.add(interestMap);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/job/{id}")
    public ResponseEntity<?> findByJobId(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = usersInterestedInJobService.findByJobId(id);

        return ResponseEntity.ok(result.toDto());
    }
}
