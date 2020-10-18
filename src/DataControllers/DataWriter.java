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

    public int saveUser() {
        int operation = 0;
        try {
            pst = conn.prepareStatement("INSERT INTO user(nic_number, contact_number,email, user_name, password, ut_id, ads_id) VALUES(?,?,?,?,?,?,?) ");
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getContactNumber());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getUserName());
            pst.setString(5, user.getPassword());
            pst.setInt(6, userType.getId());
            pst.setInt(7, ad_status.getId());

            operation = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!pst.isClosed()) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return operation;
    }

    public int updateUser() {
        int operation = 0;
        try {
            pst = conn.prepareStatement("UPDATE user SET nic_number = ?, contact_number = ?,email = ?, user_name = ?, password = ?, ut_id = ?, ads_id = ? WHERE id = ?");
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getContactNumber());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getUserName());
            pst.setString(5, user.getPassword());
            pst.setInt(6, userType.getId());
            pst.setInt(7, ad_status.getId());

            pst.setInt(8, user.getId());

            operation = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!pst.isClosed()) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return operation;
    }
}
