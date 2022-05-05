package com.codegym.dto;




import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FlightDto {





    private Long id;

    @NotNull(message = "Mã chuyến bay phải bắt buộc nhập!")
    @NotBlank(message = "Mã chuyến bay không được để trống!")
    @Pattern(regexp = "^([VJ|VN|BB|JT])+([0-9]{4})$", message = "Mã của chuyến bay phải đúng định dạng. Vd: VJ,VN,BB,JT-XXXX")
    private String codeFlight;

    @NotNull(message = "Địa điểm phải bắt buộc nhập!")
    @NotBlank(message = "Địa điểm không được để trống!")
    private String fromFlight;

    @NotNull(message = "Địa điểm phải bắt buộc nhập!")
    @NotBlank(message = "Địa điểm không được để trống!")
    private String toFlight;

    @NotNull(message = "Thời gian phải bắt buộc nhập!")
    @NotBlank(message = "Thời gian không được để trống!")
    private String dateStart;

    @NotNull(message = "Thời gian phải bắt buộc nhập!")
    @NotBlank(message = "Thời gian không được để trống!")
    private String dateEnd;

    @NotNull(message = "Hãng bay phải bắt buộc nhập!")
    private Long airlineType;

    public FlightDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeFlight() {
        return codeFlight;
    }

    public void setCodeFlight(String codeFlight) {
        this.codeFlight = codeFlight;
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

    public Long getAirlineType() {
        return airlineType;
    }

    public void setAirlineType(Long airlineType) {
        this.airlineType = airlineType;
    }
}

