package DataControllers;

import Controllers.Alerts;
import Controllers.ObjectGenerator;
import DB_Conn.ConnectDB;
import Modules.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataWriter {
    PreparedStatement pst;
    CallableStatement cst;
    Connection conn;

    Alerts alerts;

    BackupData backupData;
    ConnectionInfo connectionInfo;
    User user;
    UserType userType;
    AD_Status ad_status;
    Department department;

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
                department = ObjectGenerator.getDepartment();
            });
            readyData.setName("Data Writer");
            readyData.start();
        } catch (Exception e) {
            e.printStackTrace();
            alerts.getErrorAlert(e);
        }
    }

    public String suUser() {
        int done = 0;
        String operation = "";
        try {
            if (user.getId() == 0) {
                cst = conn.prepareCall("CALL saveUser(?,?,?,?,?,?,?,?)");
                operation = "Save";
            } else {
                cst = conn.prepareCall("CALL updateUser(?,?,?,?,?,?,?,?,?)");
                cst.setInt(9, user.getId());
                operation = "Update";
            }

            cst.setString(1, user.getFullName());
            cst.setString(2, user.getNic());
            cst.setString(3, user.getContactNumber());
            cst.setString(4, user.getEmail());
            cst.setString(5, user.getUserName());
            cst.setString(6, user.getPassword());
            cst.setInt(7, userType.getId());
            cst.setInt(8, ad_status.getId());

            done = cst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null & !cst.isClosed()) {
                    cst.close();
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

    public String deleteUser() {
        int done = 0;
        String operation = "";
        try {
            if (user.getId() > 0) {
                cst = conn.prepareCall("CALL deleteUser(?)");
                cst.setInt(1, user.getId());
                done = cst.executeUpdate();

                operation = "Delete";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst != null & !cst.isClosed()) {
                    cst.close();
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

    public String SaveDepartment() {
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
