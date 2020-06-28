package code.begin.englishbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
    public static final String DESC = "desc";
    public static final String ASC = "asc";
    private String property;
    @JsonProperty
    private boolean ascending;
}
