package solvo.yard.portal.mock.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public abstract class Registry {
    private String ID;
    private String created;
    private User creator;
    private String modified;
    private User modifier;


    // getters and setters

}