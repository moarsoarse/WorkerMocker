package solvo.yard.portal.mock.dto;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;


import java.time.Instant;

import java.util.List;


@Data

@AllArgsConstructor

@NoArgsConstructor

public class TransportRequest {

    private String num;

    private Contractor customer;

    private List<TransportType> expectedVehicle;

    private List<Comment> comments;

    private Instant validSince;

    private Instant validUntill;

    private Enum status;

    private List<TransportTarget> targets;

    private List<Attachment> attachments;

    private Enum source;

    private Instant created;

    private Instant modified;

}