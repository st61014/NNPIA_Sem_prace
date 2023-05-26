package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.dto.UsersInterestedInJobDto;
import nnpia.st61014.NNPIA_SemPrace.dto.UsersInterestedInJobInputDto;
import nnpia.st61014.NNPIA_SemPrace.service.JWTGenerator;
import nnpia.st61014.NNPIA_SemPrace.service.AppUserService;
import nnpia.st61014.NNPIA_SemPrace.service.JobListingService;
import nnpia.st61014.NNPIA_SemPrace.service.ResourceNotFoundException;
import nnpia.st61014.NNPIA_SemPrace.service.UsersInterestedInJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static nnpia.st61014.NNPIA_SemPrace.domain.UsersInterestedInJob.toEntity;

@RestController
@RequestMapping("/job-interest")
@AllArgsConstructor
public class UsersInterestedInJobController {
    private final UsersInterestedInJobService usersInterestedInJobService;
    private final AppUserService appUserService;
    private final JobListingService jobListingService;
    @Autowired
    JWTGenerator idDecode;

    @PutMapping("/create")
    public ResponseEntity<UsersInterestedInJobDto> create(@RequestHeader(name = "Authorization") String token, @RequestBody @Validated final UsersInterestedInJobDto input) throws ResourceNotFoundException {
        UsersInterestedInJobInputDto dtoWithClasses = new UsersInterestedInJobInputDto(appUserService.findById(Long.parseLong(idDecode.getIdFromJWT(token.substring(7)))), jobListingService.findById(input.getJob_listing_listingid()), input.getStatus(), LocalDateTime.parse(input.getCreation_date(), DateTimeFormatter.ISO_DATE_TIME));
        var result = usersInterestedInJobService.save(toEntity(dtoWithClasses));

        return ResponseEntity.ok(result.toDto());
    }

    @GetMapping("/user")
    public ResponseEntity<List<Map<String, Object>>> getInterestsByUserId(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam("page") int page,@RequestParam("sort") String sortBy) throws ResourceNotFoundException {

        Page<Object[]> interests = usersInterestedInJobService.findInterestsByUserIdWithJobDetails(Long.parseLong(idDecode.getIdFromJWT(token.substring(7))), page, sortBy);
        List<Object[]> content = interests.getContent();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] interest : content) {
            Map<String, Object> interestMap = new LinkedHashMap<>();
            interestMap.put("job_listing_id", interest[0]);
            interestMap.put("job_field", interest[1]);
            interestMap.put("position", interest[2]);
            interestMap.put("pay", interest[3]);
            interestMap.put("creation_date", interest[4]);
            interestMap.put("status", interest[5]);
            response.add(interestMap);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/already-interested")
    public ResponseEntity<List<Number>> getJobIdsOfUsersInterests(
            @RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {
        List<Number> interests = usersInterestedInJobService.findAllJobListingIdsFromInterests(Long.parseLong(idDecode.getIdFromJWT(token.substring(7))));
        return ResponseEntity.ok(interests);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestHeader (name="Authorization") String token, @RequestBody final String interestId) throws ResourceNotFoundException {
        var result = usersInterestedInJobService.remove(Long.parseLong(interestId),Long.parseLong(idDecode.getIdFromJWT(token.substring(7))));

        return ResponseEntity.ok(result);
    }
}
