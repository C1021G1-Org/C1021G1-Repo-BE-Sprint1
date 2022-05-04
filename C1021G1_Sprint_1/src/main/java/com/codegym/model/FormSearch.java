package com.codegym.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class FormSearch {
    private String fromFlight;
    private String toFlight;
    private String dateStart;
    private String dateEnd;
    private String searchOption;

    public FormSearch() {
    }

    public FormSearch(String fromFlight, String toFlight, String dateStart, String dateEnd, String searchOption) {
        this.fromFlight = fromFlight;
        this.toFlight = toFlight;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.searchOption = searchOption;
    }

    public String getFromFlight() {
        return fromFlight;
    }

    public void setFromFlight(String fromFlight) {
        this.fromFlight = fromFlight;
    }

    public String getToFlight() {
        return toFlight;
    }

    public void setToFlight(String toFlight) {
        this.toFlight = toFlight;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }
}
