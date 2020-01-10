package com.syj.jspexample.app.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Data
public class Paging {

    private int page = 0;

    private int pageSize = 2;

    private int blockSize = 5;

    private String searchType;

    private String searchText;

    private String sortBy;

    private String sortDirection;

    private LocalDateTime startDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear());

    private LocalDateTime endDate = LocalDateTime.now();

}
