package code.begin.englishbackend.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResult<E> {
    private String code;
    private boolean success;
    private String message = "";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private E data;
}
