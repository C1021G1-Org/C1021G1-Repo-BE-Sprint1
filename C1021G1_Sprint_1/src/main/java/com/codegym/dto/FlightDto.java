package com.codegym.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;

public class FlightDto {

    private static final String REGEX_NAME = "^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+(\\s[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ()]+)*$";

    private Long id;

    @ValidatorFlight(message = "Mã chuyến bay đã tồn tại!")
    @NotNull(message = "Mã chuyến bay phải bắt buộc nhập!")
    @NotBlank(message = "Mã chuyến bay không được để trống!")
    @Pattern(regexp = "^([VJ|VN|BB|JT])+([0-9]{4})$", message = "Vui lòng nhập đúng định dạng. ([VJ|VN|BB|JT]XXXX) trong đó X là số từ (0-9)!")
    private String codeFlight;

    @NotNull(message = "Địa điểm phải bắt buộc nhập!")
    @NotBlank(message = "Địa điểm không được để trống!")
    @Pattern(regexp = REGEX_NAME, message = "Không được nhập ký tự đặt biệt!")
    @Size(min = 10, max = 50, message = "Địa điểm phải dài trên 10 ký tự và không được nhiều hơn 50 ký tự!")
    private String fromFlight;



    @NotNull(message = "Địa điểm phải bắt buộc nhập!")
    @NotBlank(message = "Địa điểm không được để trống!")
    @Pattern(regexp = REGEX_NAME, message = "Không được nhập ký tự đặt biệt!")
    @Size(min = 10, max = 50, message = "Địa điểm phải dài trên 10 ký tự và không được nhiều hơn 50 ký tự!")
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

