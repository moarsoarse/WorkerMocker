package solvo.yard.portal.mock.dto;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;


import java.time.Instant;


@Data

@AllArgsConstructor

@NoArgsConstructor

public class TransportTarget {

    private String num;

    private String route;

    private Contractor sender;

    private Location loadLocation;

    private String loadAddress;

    private Instant loadArrival;

    private Contractor recipient;

    private Location un
}