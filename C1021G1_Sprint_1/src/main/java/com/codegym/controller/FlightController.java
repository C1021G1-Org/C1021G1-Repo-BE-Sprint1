package com.codegym.controller;

import com.codegym.dto.FlightDto;
import com.codegym.dto.FlightDtoCheck;
import com.codegym.dto.FlightSearchDto;
import com.codegym.dto.IFlightDto;
import com.codegym.model.Flight;
import com.codegym.model.FormSearch;
import com.codegym.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.codegym.model.AirlineType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private IFlightService flightService;
    @Autowired
    private IAirlineTypeService airlineTypeService;

    @GetMapping("/search")
    public ResponseEntity<Page<Flight>> searchFlight(@RequestParam(defaultValue = "") String fromFlight, String toFlight, String dateStart, String dateEnd,
                                                     @RequestParam(defaultValue = "0") int page) {

        Page<Flight> flightList = flightService.searchFlight(fromFlight, toFlight, dateStart, dateEnd, PageRequest.of(page, 10));

        if (flightList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Flight>> getListFlight(@RequestParam(defaultValue = "0") int page) {
        Page<Flight> flightPage = flightService.findAllFlight(PageRequest.of(page, 10));
        if (flightPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }

    @GetMapping("/list-not-pagination")
    public ResponseEntity<Page<Flight>> getListFlightNotPagination(Pageable pageable) {
        Page<Flight> flightPage = flightService.findAllFlightNotPage(pageable);
        if (flightPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Flight> findById(@PathVariable Long id) {
        IFlightDto flightDto = flightService.findById1(id);
        AirlineType airlineType = airlineTypeService.findById(flightDto.getId_airline_type());
        System.out.println(flightDto.getId());

        if (flightDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Flight flight = new Flight();
        flight.setId(id);
        flight.setCodeFlight(flightDto.getCode_flight());
        flight.setFromFlight(flightDto.getFrom_flight());
        flight.setToFlight(flightDto.getTo_flight());
        flight.setDateStart(flightDto.getDate_start());
        flight.setDateEnd(flightDto.getDate_end());
        flight.setDelFlagFlight(flightDto.getDel_flag_flight());
        flight.setAirlineType(airlineType);
        System.out.println(flight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Flight> getDeleteFlight(@PathVariable Long id) {
        System.out.println("Fetching & Deleting Flight with id " + id);

        IFlightDto flight = flightService.findById1(id);

        if (flight == null) {
            System.out.println("Unable to delete. Flight with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    Pageable pageable = PageRequest.of(0, 10);

    Map<String, Page<FlightSearchDto>> searchFlightList;

    @PostMapping("/search")
    public ResponseEntity<?> searchFlight(@RequestBody FormSearch formSearch) {
        System.out.println(formSearch);
        if (formSearch.getSearchOption().equals("oneway")) {
            if (formSearch.getFromFlight() == null
                    || formSearch.getFromFlight().equals("")
                    || formSearch.getDateStart() == null
                    || formSearch.getDateStart().equals("")
                    || formSearch.getToFlight() == null
                    || formSearch.getToFlight().equals("")
                    || formSearch.getDateEnd() == null
                    || formSearch.getDateEnd().equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            switch (formSearch.getSortOption()) {
                case "price_airline":
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "price_airline", pageable);
                    break;
                case "date_start":
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "date_start", pageable);
                    break;
                case "image_airline":
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "image_airline", pageable);
                    break;
                default:
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "from_flight", pageable);
            }
            if (searchFlightList.get("oneway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(searchFlightList, HttpStatus.OK);
            }
        } else if (formSearch.getSearchOption().equals("twoway")) {
            if (formSearch.getFromFlight() == null
                    || formSearch.getFromFlight().equals("")
                    || formSearch.getDateStart() == null
                    || formSearch.getDateStart().equals("")
                    || formSearch.getToFlight() == null
                    || formSearch.getToFlight().equals("")
                    || formSearch.getDateEnd() == null
                    || formSearch.getDateEnd().equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            switch (formSearch.getSortOption()) {
                case "price_airline":
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "price_airline", pageable);
                    break;
                case "date_start":
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "date_start", pageable);
                    break;
                case "image_airline":
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "image_airline", pageable);
                    break;
                default:
                    searchFlightList = flightService.searchFlight(formSearch.getFromFlight(), formSearch.getToFlight(), formSearch.getDateStart(),
                            formSearch.getDateEnd(), "from_flight", pageable);
            }
            if (searchFlightList.get("oneway").getSize() == 0 && searchFlightList.get("twoway").getSize() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(searchFlightList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<Flight>> searchFlightByDate(@RequestParam(required = false) String date) {

        List<Flight> flightPage = flightService.searchFlightByDate(date);
        return new ResponseEntity<>(flightPage, HttpStatus.OK);
    }

//    tronghd create chuyến bay
        @PostMapping("/create")
        public ResponseEntity<?> createFlight (@Valid @RequestBody FlightDto flightDto){
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),HttpStatus.NOT_ACCEPTABLE);
//        }
            flightService.createFlight(flightDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }

//    tronghd validate dữ liệu thêm mới
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map<String, String> handleValidationExceptions (
                MethodArgumentNotValidException ex){
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }

        @GetMapping("/{id}")
        public ResponseEntity<Flight> getId (@PathVariable Long id){
            Flight flight = flightService.findById(id);
            if (flight == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(flight, HttpStatus.OK);
            }
        }

//    tronghd update chuyến bay
        @PatchMapping("/update/{id}")
        public ResponseEntity<?> updateFlight (@Valid @RequestBody FlightDtoCheck flightDtoCheck,
                BindingResult bindingResult, @PathVariable Long id){
            FlightDto flightDto = new FlightDto();
            flightDto.setId(id);
            flightDto.setCodeFlight(flightDtoCheck.getCodeFlight());
            flightDto.setFromFlight(flightDtoCheck.getFromFlight());
            flightDto.setToFlight(flightDtoCheck.getToFlight());
            flightDto.setDateStart(flightDtoCheck.getDateStart());
            flightDto.setDateEnd(flightDtoCheck.getDateEnd());
            flightDto.setAirlineType(flightDtoCheck.getAirlineType().getId());
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
            }
            flightService.updateFlight(flightDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }

}

