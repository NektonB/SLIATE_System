package DataControllers;

import Controllers.Alerts;
import Controllers.ObjectGenerator;
import DB_Conn.ConnectDB;
import Modules.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataWriter {
    PreparedStatement pst;
    Connection conn;

    Alerts alerts;

    BackupData backupData;
    ConnectionInfo connectionInfo;
    User user;
    UserType userType;
    AD_Status ad_status;


    /**
     * Load Supporting classes by thread
     * All supporting classes load in the thread
     */
    public DataWriter() {
        try {
            Thread readyData = new Thread(() -> {
                conn = ConnectDB.getConn();
                backupData = ObjectGenerator.getBackupData();
                connectionInfo = ObjectGenerator.getConnectionInfo();
                alerts = ObjectGenerator.getAlerts();
                user = ObjectGenerator.getUser();
                userType = ObjectGenerator.getUserType();
                ad_status = ObjectGenerator.getAd_status();
            });
            readyData.setName("Data Writer");
            readyData.start();
        } catch (Exception e) {
            e.printStackTrace();
            alerts.getErrorAlert(e);
        }
    }

    public String SU_User() {
        int done = 0;
        String operation = "";
        try {
            if (user.getId() == 0) {
                pst = conn.prepareStatement("INSERT INTO user(full_name,nic_number, contact_number,email, user_name, password, ut_id, ads_id) VALUES(?,?,?,?,?,?,?,?) ");
                operation = "Save";
            } else {
                pst = conn.prepareStatement("UPDATE user SET full_name = ?, nic_number = ?, contact_number = ?,email = ?, user_name = ?, password = ?, ut_id = ?, ads_id = ? WHERE id = ?");
                pst.setInt(9, user.getId());
                operation = "Update";
            }

            pst.setString(1, user.getFullName());
            pst.setString(2, user.getNic());
            pst.setString(3, user.getContactNumber());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getUserName());
            pst.setString(6, user.getPassword());
            pst.setInt(7, userType.getId());
            pst.setInt(8, ad_status.getId());

            done = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!pst.isClosed()) {
                    pst.close();
                }
                if (done <= 0) {
                    operation = "failed";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return operation;
    }
}
