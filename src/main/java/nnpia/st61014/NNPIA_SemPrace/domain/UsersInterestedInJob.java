package nnpia.st61014.NNPIA_SemPrace.domain;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class UsersInterestedInJob {
    @Column
    private String status;
    @Column
    private LocalDateTime creation_date;

}
