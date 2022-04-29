package com.codegym.validation;

import com.codegym.dto.EmployeeDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.regex.Pattern;
@Component
public class EmployeeCreateByRequestDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employeeDto = (EmployeeDto) target;
        Date date = new Date();
        String[] dateSplit = employeeDto.getBirthday_Employee().split("-");
        dateSplit[0] = String.valueOf(Integer.parseInt(dateSplit[0]) - 1900);
        Date dateOfBirth = new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]) - 1, Integer.parseInt(dateSplit[2]));
                if (employeeDto.getName_Employee() == null) {
                    errors.rejectValue("name_Employee", "name_Employee.null", "Tên không được để trống");
                }else if (employeeDto.getName_Employee().length() < 6) {
                     errors.rejectValue("name_Employee", "name_Employee.length", "Tên phải lơn hơn 6 ký tự");
                }else if (!Pattern.compile("^[a-zA-Zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ" +
                        "ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+(\\s[a-zA-Zàáạảãâầấậẩẫăằắặ" +
                        "ẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔ" +
                        "ỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+)*$").matcher(employeeDto.getName_Employee()).find()) {
                    errors.rejectValue("name_Employee", "name_Employee.pattern", "Tên phải đúng định dạng");
                } if (employeeDto.getBirthday_Employee() == null) {
                    errors.rejectValue("birthday_Employee", "birthday_Employee.null", "Ngày sinh không được để trống");
                }else if (((date.getTime()) - (dateOfBirth.getTime())) / 1000 / 60 / 60 / 24 < 6570 || ((date.getTime()) - (dateOfBirth.getTime())) / 1000 / 60 / 60 / 24 > 36525) {
                    errors.rejectValue("birthday_Employee", "birthday_Employee.format", "Ngày sinh phải từ 18 tuổi đến 100 tuổi");
                }if (employeeDto.getAddress_Employee().length() > 200) {
                    errors.rejectValue("address_Employee", "address_Employee.length", "Địa chỉ phải nhỏ hơn 200 ký tự");
                }if (employeeDto.getPhone_Employee() == null) {
                     errors.rejectValue("phone_Employee", "phone_Employee.null", "Số địa thoại không được để trống");
                }if (employeeDto.getCode_Employee() == null) {
                errors.rejectValue("code_Employee", "code_Employee.null", "Code EMployee không được để trống");
                }
        }
    }
