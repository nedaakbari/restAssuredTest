package ir.sample.helper;

import java.sql.*;

public class ConnectionDataBase {
    //    String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1"; // URL اتصال به دیتابیس
    private static final String url = "jdbc:oracle:thin:@192.168.32.52:30017/ORCLPDB1";
    private static final String username = "C##SHAHKAR";
    private static final String password = "1";

    public static int insertValue(String identificationNo, int identificationType) throws SQLException {//todo offline only
        //استفاده از RETURNING INTO در Oracle (بدون نیاز به گرفتن MAX(ID))
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO CUSTOMER (ID,IDENTIFICATION_NUMBER, CUSTOMER_TYPE) VALUES (?, ?,?) ";
//            String sql = "INSERT INTO CUSTOMER (IDENTIFICATION_NUMBER, CUSTOMER_TYPE) VALUES (?, ?) RETURNING id INTO ?";
//            try (CallableStatement callStmt = conn.prepareCall(sql)) {
//                callStmt.setString(1, identificationNo);
//                callStmt.setInt(2, identificationType);
//                callStmt.registerOutParameter(3, Types.INTEGER);
//                callStmt.executeUpdate();
//                return callStmt.getInt(3);
//            }

            String query = "SELECT id FROM CUSTOMER ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
            int lastRecordID = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery(query);
                if (rs.next()) {
                    lastRecordID = rs.getInt("id");
                }
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, lastRecordID + 1);
                pstmt.setString(2, identificationNo);
                pstmt.setInt(3, identificationType);
//                pstmt.registerOutParameter(3, java.sql.Types.INTEGER);
                return pstmt.executeUpdate();
            }
        }
    }


    public static int insertCache(String birthCert, int birthDate, String fathername, String ino,
                                  int identificationType, String name, String family,int gender) throws SQLException {
        // استفاده از اتصال به دیتابیس
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            // دریافت آخرین رکورد ID از CUSTOMER_CACHE
            String query = "SELECT id FROM CUSTOMER_CACHE ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
            int lastRecordID = 0;

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        lastRecordID = rs.getInt("id");
                    }
                }
            }

            // دستور SQL برای درج رکورد جدید
            String sql = "INSERT INTO CUSTOMER_CACHE (ID,FIRST_NAME, LAST_NAME,GENDER, IDENTIFICATION_NO, I_TYPE,IS_DEAD,BIRTH_CERTIFICATE_NO, BIRTH_DATE,FATHER_NAME, COMPANY_NAME, COMPANY_NAME_EN, COMPANY_TYPE,  NAJA_ID, RECEIPT_CODE, REGISTRATION_DATE, REGISTRATION_NO, UPDATE_TIME, NATIONALITY) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)";

//            INSERT INTO "C##SHAHKAR"."CUSTOMER_CACHE" ("ID", "BIRTH_CERTIFICATE_NO", "BIRTH_DATE", "COMPANY_NAME", "COMPANY_NAME_EN", "COMPANY_TYPE", "IS_DEAD", "FATHER_NAME", "FIRST_NAME", "GENDER", "IDENTIFICATION_NO", "I_TYPE", "LAST_NAME", "NAJA_ID", "RECEIPT_CODE", "REGISTRATION_DATE", "REGISTRATION_NO", "UPDATE_TIME", "NATIONALITY")
//            VALUES ('36', '0', '13720817', NULL, NULL, NULL, '0', 'حميد', 'پوريا', '1', '5560196576', '0', 'جهانجاني', NULL, NULL, NULL, NULL, TO_TIMESTAMP('2025-01-25 14:02:33.386000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), NULL);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // تنظیم پارامترهای PreparedStatement
                pstmt.setInt(1, lastRecordID + 1);
                pstmt.setString(2, name);
                pstmt.setString(3, family);
                pstmt.setInt(4, gender);
                pstmt.setString(5, ino);
                pstmt.setInt(6,0 );
                pstmt.setInt(7, 0);
                pstmt.setString(8, "25664");
                pstmt.setInt(9, birthDate);
                pstmt.setString(10, fathername);

                // اجرای دستور INSERT
                return pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            // مدیریت خطا
            e.printStackTrace();
            throw e;
        }
    }

}