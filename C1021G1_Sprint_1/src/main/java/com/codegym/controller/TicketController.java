package com.codegym.controller;

import com.codegym.comon.Security_Email;
import com.codegym.model.Ticket;
import com.codegym.service.ITicketService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RestController
@CrossOrigin("*")
@RequestMapping("api/ticket")
public class TicketController {
    @Autowired
    private JavaMailSender emailSender;


    @Autowired
    ITicketService ticketService;

    //    SonNh lấy danh sách ticket by customer Id
    @GetMapping("/list/{id}")
    public ResponseEntity<List<Ticket>> listAllTicketListByCustomerId(@PathVariable Long id) {
        List<Ticket> ticketList = ticketService.findAllTicketsByCustomerId(id);
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    //    SonNh lấy danh sách ticket by customer Id
    @GetMapping("/listHistory/{id}")
    public ResponseEntity<List<Ticket>> listHistoryTicketListByCustomerId(@PathVariable("id") Long id) {
        List<Ticket> ticketList = ticketService.findHistoryTicketsByCustomerId(id);
        if (ticketList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    //    SonNh lấy ticket by CodeTicket
    @GetMapping(value = "/{code_ticket}")
    public ResponseEntity<Ticket> getTicketByID(@PathVariable("code_ticket") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    //    SonNh pay ticket by ticket code
    @PatchMapping(value = "/pay/{code}")
    public ResponseEntity<Ticket> payTicketByCode(@PathVariable("code") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.payTicketByCodeTicket(codeTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    SonNh pay tickets ticket by ticket codes
    @PatchMapping(value = "/pays/{codes}")
    public ResponseEntity<Ticket> payTicketByCodes(@PathVariable("codes") List<String> codeTicketList) {
        System.out.println("Fetching Ticket with id " + codeTicketList);
        Ticket ticket;
        for (int i = 0; i < codeTicketList.size(); i++) {
            ticket = ticketService.findTicketByCodeTicket(codeTicketList.get(i));
            if (ticket == null) {
                System.out.println("Ticket with id " + codeTicketList.get(i) + " not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
//            SonNH thanh toán cho từng vé
            ticketService.payTicketByCodeTicket(codeTicketList.get(i));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    SonNh get total price ticket by ticket codes
    @GetMapping(value = "/getPrice/{codes}")
    public ResponseEntity<Double> getTotalPrice(@PathVariable("codes") List<String> codeTicketList) {
        System.out.println("Fetching Ticket with id " + codeTicketList);
        Ticket ticket;
        double totalPrice = 0;
        double finalPrice = 0;
        for (int i = 0; i < codeTicketList.size(); i++) {
            ticket = ticketService.findTicketByCodeTicket(codeTicketList.get(i));
            if (ticket == null) {
                System.out.println("Ticket with id " + codeTicketList.get(i) + " not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
//            SonNH thanh toán cho từng vé
            ticketService.payTicketByCodeTicket(codeTicketList.get(i));

//            Lấy giá tổng
            totalPrice += ticket.getPriceTicket();
        }
        finalPrice = Math.round(totalPrice / 23000);
        return new ResponseEntity<>(finalPrice, HttpStatus.OK);
    }


    @PatchMapping(value = "/abort/{code}")
    public ResponseEntity<Ticket> abortTicket(@PathVariable("code") String codeTicket) {
        System.out.println("Fetching Ticket with id " + codeTicket);
        Ticket ticket = ticketService.findTicketByCodeTicket(codeTicket);
        if (ticket == null) {
            System.out.println("Ticket with id " + codeTicket + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.abortTicketByCodeTicket(codeTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(value = "/sendmail")
    public ResponseEntity<Ticket> sendMailHtml(
            @RequestParam("finalPrice") Double finalPrice,
            @RequestParam("nameCustomer") String nameCustomer,
            @RequestParam("quantity") int quantity
    ) {
        MimeMessage message = emailSender.createMimeMessage();

        Double codePayment = Double.valueOf(10000 + Math.round((Math.random() * 100000)));
        boolean multipart = true;


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(date);
        MimeMessageHelper helper = null;
        try {

            helper = new MimeMessageHelper(message, multipart, "utf-8");
            String htmlMsg = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title></title>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                    "    <style type=\"text/css\">\n" +
                    "\n" +
                    "        body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n" +
                    "        table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n" +
                    "        img { -ms-interpolation-mode: bicubic; }\n" +
                    "\n" +
                    "        img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }\n" +
                    "        table { border-collapse: collapse !important; }\n" +
                    "        body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }\n" +
                    "\n" +
                    "\n" +
                    "        a[x-apple-data-detectors] {\n" +
                    "            color: inherit !important;\n" +
                    "            text-decoration: none !important;\n" +
                    "            font-size: inherit !important;\n" +
                    "            font-family: inherit !important;\n" +
                    "            font-weight: inherit !important;\n" +
                    "            line-height: inherit !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        @media screen and (max-width: 480px) {\n" +
                    "            .mobile-hide {\n" +
                    "                display: none !important;\n" +
                    "            }\n" +
                    "            .mobile-center {\n" +
                    "                text-align: center !important;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }\n" +
                    "    </style>\n" +
                    "<body style=\"margin: 0 !important; padding: 0 !important; background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n" +
                    "\n" +
                    "\n" +
                    "<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Open Sans, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\n" +
                    "    For what reason would it be advisable for me to think about business content? That might be little bit risky to have crew member like them.\n" +
                    "</div>\n" +
                    "\n" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                    "    <tr>\n" +
                    "        <td align=\"center\" style=\"background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n" +
                    "\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" valign=\"top\" style=\"font-size:0; padding: 35px;\" bgcolor=\"#F44336\">\n" +
                    "\n" +
                    "                        <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\">\n" +
                    "                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                <tr>\n" +
                    "                                    <td align=\"left\" valign=\"top\" style=\"font-family: Arial; font-size: 36px; font-weight: 800; line-height: 48px;\" class=\"mobile-center\">\n" +
                    "                                        <h1 style=\"font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;\">Xác nhận vé</h1>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </div>\n" +
                    "\n" +
                    "                        <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\" class=\"mobile-hide\">\n" +
                    "                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                <tr>\n" +
                    "                                    <td align=\"right\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; line-height: 48px;\">\n" +
                    "                                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"right\">\n" +
                    "                                            <tr>\n" +
                    "<!--                                                <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;\">-->\n" +
                    "<!--                                                    <p style=\"font-size: 18px; font-weight: 400; margin: 0; color: #ffffff;\"><a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\">Flight &nbsp;</a></p>-->\n" +
                    "<!--                                                </td>-->\n" +
                    "                                                <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                                    <a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\"><img src=\"https://cdn.pixabay.com/photo/2013/07/13/12/18/boeing-159589_960_720.png\" width=\"100\" height=\"100\" style=\"display: block; border: 0px;\"/></a>\n" +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </div>\n" +
                    "\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\"padding: 35px 35px 20px 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
                    "                                    <img src=\"https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\" /><br>\n" +
                    "                                    <h2 style=\"font-size: 30px; font-weight: 700; line-height: 36px; color: #333333; margin: 0;\">\n" +
                    "                                        Cám ơn quý khách đã sử dụng dịch vụ của chúng tôi!\n" +
                    "                                    </h2>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;\">\n" +
                    "                                    <p style=\"font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;\">\n" +
                    "                                        Thông tin đơn hàng được thanh toán\n" +
                    "                                    </p>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"padding-top: 20px;\">\n" +
                    "                                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\">\n" +
                    "                                                Mã đơn hàng đặt vé #\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\">\n" +
                    "                                                \n" + codePayment +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\">\n" +
                    "                                                Thông tin người đặt (1)\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\">\n" +
                    "                                                \n" + nameCustomer +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\">\n" +
                    "                                                Số vé đã đặt\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\">\n" +
                    "                                                \n" + quantity +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"padding-top: 20px;\">\n" +
                    "                                    <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                    "                                        <tr>\n" +
                    "                                            <td width=\"60%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\">\n" +
                    "                                                Tổng tiền đã thanh toán\n" +
                    "                                            </td>\n" +
                    "                                            <td width=\"40%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\">\n" +
                    "                                                $\n" + finalPrice +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" height=\"100%\" valign=\"top\" width=\"100%\" style=\"padding: 0 35px 35px 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:660px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\" valign=\"top\" style=\"font-size:0;\">\n" +
                    "                                    <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
                    "\n" +
                    "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                            <tr>\n" +
                    "                                                <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                                    <p style=\"font-weight: 800;\">Thông tin liên hệ</p>\n" +
                    "                                                    <p>295<br>Tầng 10<br>CodeGym Đà Nẵng</p>\n" +
                    "\n" +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>\n" +
                    "                                    </div>\n" +
                    "                                    <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
                    "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n" +
                    "                                            <tr>\n" +
                    "                                                <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                                    <p style=\"font-weight: 800;\">Ngày Thanh toán</p>\n" +
                    "                                                    <p></p>\n" +strDate+
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </table>\n" +
                    "                                    </div>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\" padding: 35px; background-color: #ff7361;\" bgcolor=\"#1b9ba3\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
                    "                                    <h2 style=\"font-size: 24px; font-weight: 800; line-height: 30px; color: #ffffff; margin: 0;\">\n" +
                    "                                        Chúc quý khách tận hưởng chuyến bay\n" +
                    "                                    </h2>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td align=\"center\" style=\"padding: 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n" +
                    "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"center\">\n" +
                    "                                    <img src=\"https://www.vietnamairlines.com/Themes/VNANew/Portal/images/img-logo-partner-1.png\" width=\"100\" height=\"100\" style=\"display: block; border: 0px;\"/>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "\n" +
                    "                            <tr>\n" +
                    "                                <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 24px;\">\n" +
                    "                                    <p style=\"font-size: 14px; font-weight: 400; line-height: 20px; color: #777777;\">\n" +
                    "                                        Nếu bạn không tạo tài khoản bằng địa chỉ email này, vui lòng bỏ qua email này hoặc <a href=\"#\" target=\"_blank\" style=\"color: #777777;\">hủy đăng ký.</a>.\n" +
                    "                                    </p>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";

            helper.setText("Thành công", htmlMsg);

            helper.setTo(Security_Email.MAIL_RECEIVER);

            helper.setSubject("Xác nhận thanh toán thành công vé máy bay");

            this.emailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
