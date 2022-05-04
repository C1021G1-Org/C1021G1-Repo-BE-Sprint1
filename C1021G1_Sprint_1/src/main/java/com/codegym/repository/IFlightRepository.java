package com.codegym.repository;

import com.codegym.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IFlightRepository extends JpaRepository<Flight, Long> {

//tronghd tạoc câu lệnh query thêm mới
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Flight(code_flight,from_flight,to_flight,date_start,date_end,id_airline_type,del_flag_flight)" +
            " VALUES(?1,?2,?3,?4,?5,?6, ?7) ", nativeQuery = true)
    void createFlight(String codeFlight, String fromFlight, String toFlight, String dateStart,
                      String dateEnd, Long airlineType, Boolean delFlagFlight);

    @Query(value =" SELECT id" +
            ", code_flight"+
            ", from_flight"+
            ", to_flight"+
            ", date_start"+
            ", date_end"+
            ", del_flag_flight"+
            ", id_airline_type "+
            "FROM flight WHERE id=?", nativeQuery = true)
    Flight findByIdFlight(Long id);

    //tronghd tạoc câu lệnh query chỉnh sửa chuyến bay
    @Transactional
    @Modifying
    @Query(value = "UPDATE Flight AS f SET f.code_flight = ?1 , f.from_flight = ?2, f.to_flight = ?3, f.date_start = ?4," +
            "f.date_end = ?5, f.id_airline_type = ?6, f.del_flag_flight = ?7 WHERE id = ?8", nativeQuery = true)
    void updateFlight(String codeFlight, String fromFlight, String toFlight, String dateStart,
                      String dateEnd, Long airlineType, Boolean delFlagFlight, Long id);
import com.codegym.dto.FlightDto;
import com.codegym.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "select " +
            "airline_type.image_airline, " +
            "flight.code_flight, " +
            "flight.date_start, " +
            "flight.date_end, " +
            "airline_type.price_airline " +
            "from flight " +
            "left join airline_type " +
            "on flight.id_airline_type = airline_type.id " +
            "where flight.from_flight = :from_flight " +
            "and flight.to_flight = :to_flight " +
            "and flight.date_start like concat('%',:date_start,'%') " +
            "and flight.date_end like concat('%',:date_end,'%')",
            nativeQuery = true)
    Page<FlightDto> searchFlight(@Param("from_flight") String departureDestination,
                                 @Param("to_flight") String arrivalDestination,
                                 @Param("date_start") String departureDate,
                                 @Param("date_end") String arrivalDate,
                                 Pageable pageable);

    @Query(value = "SELECT flight.id, name_airline,from_flight,to_flight,date_start,date_end,price_airline\n" +
            "FROM flight JOIN airline_type on flight.id_airline_type = airline_type.id", nativeQuery = true)
    Page<Flight> findAllFlight(Pageable pageable);

    @Query(value = "SELECT id,from_flight,to_flight,date_start,date_end,id_airline_type\n" +
            "FROM flight WHERE id = ? ", nativeQuery = true)
    Flight findByIdFlight(Long id);

    @Query(value = "UPDATE flight SET del_flag_airline = FALSE WHERE id : ?", nativeQuery = true)
    void deleteById(Long id);

}
