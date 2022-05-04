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
}
