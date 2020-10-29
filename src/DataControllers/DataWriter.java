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

    public String suDepartment() {
        int done = 0;
        String operation = "";
        try {
            if (user.getId() == 0) {
                cst = conn.prepareCall("CALL saveDepartment(?,?)");
                operation = "Save";
            } else {
                cst = conn.prepareCall("CALL updateDepartment(?,?,?)");
                cst.setInt(3, department.getId());
                operation = "Update";
            }

            cst.setString(1, department.getName());
            cst.setInt(2, 1);

            done = cst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cst == null & !cst.isClosed()) {
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

    public String deleteDepartment() {
        int done = 0;
        String operation = "";
        try {
            if (user.getId() > 0) {
                cst = conn.prepareCall("CALL deleteDepartment(?)");
                cst.setInt(1, department.getId());
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
}
