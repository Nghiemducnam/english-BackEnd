package code.begin.englishbackend.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public abstract class BaseSearch {
    protected Long totalRecords;
    protected Integer page;
    protected Integer totalPages;
    protected Integer pageSize;
    protected List<OrderDTO> orders;
    protected List<?> data;
}
