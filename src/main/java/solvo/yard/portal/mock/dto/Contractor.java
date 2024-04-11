package solvo.yard.portal.mock.dto;
import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;


import java.time.Instant;

import java.util.List;

@Data

@AllArgsConstructor

@NoArgsConstructor

public class Contractor {

    private String ID;

    private String title;

    private String fullTitle;

    private Enum type;

    private List<Enum> role;

    private Instant created;

    private Instant modified;

}