package br.com.devfour.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PageableResponse<T> extends PageImpl<T> {
    public PageableResponse(@JsonProperty("content") List<T> content,
                            int size, int page, long total) {
        super(content, pageable, total);
    }
    public PageableResponse(List<T> content) {
        super(content);
    }
    public PageableResponse(){
        super(new ArrayList<>());
    }
}
