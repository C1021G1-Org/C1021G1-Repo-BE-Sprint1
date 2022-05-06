package com.codegym.dto;

public interface IFlightDto {
    Long getId();
    String getCode_flight();
    String getFrom_flight();
    String getTo_flight();
    String getDate_start();
    Boolean getDel_flag_flight();
    String getDate_end();
    Long getId_airline_type();
}
