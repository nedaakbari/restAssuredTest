package ir.sample.helper.randomValues;

//import helper.ConnectionDataBase;
//import helper.PersianNames;
//import helper.RequestBody;
//import helper.requestModel.Address;
//import helper.requestModel.Service;

import ir.sample.helper.requestModel.Address;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class ShahkarHelper {

    private static Random random = new Random();

    public static final List<String> girlIranianName = List.of("ندا", "سارا", "زهرا", "مبینا");
    public static final List<String> boyIranianName = List.of("علی", "امیر", "محمد", "محمد", "نقی", "تقی", "جواد", "مجتبی", "شاهین", "سعید");
    public static final List<String> familyName = List.of("زارع", "محمدی", "جهانجانی", "سعیدی");


    //TODO IranianNationalIdGenerator
    // تابع برای تولید شناسه ملی تصادفی معتبر
    public static String generateRandomId() {
        // ابتدا یک طول تصادفی بین 8 تا 10 انتخاب می‌کنیم
        int length = 8 + (int) (Math.random() * 3); // طول بین 8 تا 10
        StringBuilder code = new StringBuilder();

        // ابتدا یک رشته عددی تصادفی می‌سازیم
        for (int i = 0; i < length - 1; i++) {
            code.append((int) (Math.random() * 10));  // اضافه کردن یک رقم تصادفی
        }

        // حالا به دنبال محاسبه عدد چک (checksum) هستیم
        int len = code.length();
        int s = 0;
        for (int i = 0; i < len - 1; i++) {
            s += Integer.parseInt(code.substring(i, i + 1)) * (len - i);
        }
        s %= 11;
        int checkDigit = (s < 2) ? s : 11 - s;

        // افزودن چک دیجیت به انتهای کد
        code.append(checkDigit);

        // برگرداندن کد کامل که معتبر است
        return code.toString();
    }

    public static boolean isValidCode(String code) {
        // ابتدا بررسی می‌کنیم که آیا کد با فرمت درست است
        if (!code.matches("\\d{8,10}") || code.matches("(0{8,10}|1{8,10}|2{8,10}|3{8,10}|4{8,10}|5{8,10}|6{8,10}|7{8,10}|8{8,10}|9{8,10})")) {
            return false;
        }

        // الگوریتم چک‌کد
        int len = code.length();
        int s = 0;
        for (int i = 0; i < len - 1; i++) {
            s += Integer.parseInt(code.substring(i, i + 1)) * (len - i);
        }
        s %= 11;
        int checkDigit = (s < 2) ? s : 11 - s;

        // بررسی عدد چک
        return Integer.parseInt(code.substring(len - 1)) == checkDigit;
    }


    /**
     * IranianDateOfBirthGenerator
     **/
    public static String generateRandomDateOfBirth() {
        return "13730203";
    }

    public static String generateRandomDateOfBirthUnderLegal() {
        return "13730203";
    }

    /**
     * requestId
     **/
    public static String generateRequestId(String providerCode) {
        Date date = new Date();
        date.setTime(date.getTime() + (3 * 3600000) + (30 * 60000)); // +3.5h (3 ساعت و 30 دقیقه)
        return providerCode + makeReqformatDate(date);
    }

    private static String makeReqformatDate(Date date) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmssSSSSSS");

        sdfDate.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdfTime.setTimeZone(TimeZone.getTimeZone("GMT"));

        String formattedDate = sdfDate.format(date);
        String formattedTime = sdfTime.format(date);

        return formattedDate + formattedTime;
    }


    /**
     * SerialNumber
     **/
    public static String generateSerialNumber() {
        return makeRandomNumber(15);
    }

    /**
     * MobileNumber
     **/
    public static String generateMobileNumber() {
        return "09" + makeRandomNumber(9);
    }

    private static String makeRandomNumber(int length) {
        String characters = "123456789";
        Random rand = new Random();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < length; i++) {
            number.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return number.toString();
    }

    // ساخت نمونه از آدرس ایرانی
    public static final Address iranianAddress = new Address("خیابان نامعلوم پلاک ۱۲ طبقه ۵", "1234567651", "۰۳۵۶۰۰۱۱۰۰۰", "فارس", "11", "شیراز", "شیراز");

    public static Service mobile() {
        return Service.builder()
                .type(2)
                .mobileNumber(generateMobileNumber())
                .serial(generateSerialNumber())
                .mobileType("1")
                .sms(1)
                .gprs(1)
                .mms(0)
                .wap(0)
                .threeG(0)
                .fourG(1)
                .videoCall(0)
                .province("021")
                .ipStatic(0)
                .ip("192.168.12.11")
                .apn(1)
                .dataSim(0).build();
    }

    public static RequestBody getIranianPerson() throws SQLException {
        String nationalId = generateRandomId();
        int type = 0;
        int id = ConnectionDataBase.insertValue(nationalId, type);
        String birthDate = ShahkarHelper.generateRandomDateOfBirth();
        String name = ShahkarHelper.generateRandomBoyName();
        String family = ShahkarHelper.generateFamily();
        String fatherName = ShahkarHelper.generateRandomBoyName();
        String number = "12255";
        int gender = 1;
        ConnectionDataBase.insertCache(number, Integer.parseInt(birthDate), fatherName, nationalId, 0, name, family, gender);
        return RequestBody.builder()
                .mobile(generateMobileNumber())
                .email("www.test@gmail.com")
                .name(name)
                .family(family)
                .fatherName(fatherName)
                .certificateNo(number)
                .birthDate(birthDate)
                .gender(gender)
                .identificationType(type)
                .identificationNo(nationalId)
                .resellerCode("526")//todo
                .iranian(1)
                .person(1)
                .address(iranianAddress)//todo
                .build();
    }

    /**
     * generateName
     **/
    public static String generateRandomBoyName() {
//        String name1 = boyIranianName.get(random.nextInt(boyIranianName.size())); // انتخاب اسم اول
//        String name2 = boyIranianName.get(random.nextInt(boyIranianName.size())); // انتخاب اسم دوم
//        return name1 + name2;

        return PersianNames.firstNameFarsi("m");
    }


    public static String generateRandomGirlName() {
//        String name1 = girlIranianName.get(random.nextInt(boyIranianName.size())); // انتخاب اسم اول
//        String name2 = girlIranianName.get(random.nextInt(boyIranianName.size())); // انتخاب اسم دوم
        return PersianNames.firstNameFarsi("f");
    }

    public static String generateFamily() {
        return PersianNames.lastNameFarsi();
//        return familyName.get(random.nextInt(familyName.size()));
    }

}
