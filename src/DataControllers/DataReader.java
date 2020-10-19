package DataControllers;

import Controllers.Alerts;
import Controllers.ObjectGenerator;
import Controllers.ViewUserController;
import DB_Conn.ConnConfig;
import DB_Conn.ConnectDB;
import Modules.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataReader {

    PreparedStatement pst;
    Connection conn;

    Alerts alerts;

    BackupData backupData;
    ConnectionInfo connectionInfo;
    User user;
    UserType userType;
    AD_Status ad_status;

    public DataReader() {
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
            readyData.setName("DataReader");
            readyData.start();
        } catch (Exception e) {
            e.printStackTrace();
            alerts.getErrorAlert(e);
        }
    }

    public void getSQLDumpPath() {
        ResultSet rs = null;
        try {
            pst = ConnConfig.getServerConfig().prepareStatement("SELECT * FROM tbl_exepath WHERE id = ? ");
            pst.setString(1, "1");
            rs = pst.executeQuery();
            while (rs.next()) {
                backupData.setMysqlDumpPath(rs.getString("path"));
            }
        } catch (Exception e) {
            //WebOptionPane.showMessageDialog(null, e, "Error", WebOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            alerts.getErrorAlert(e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                //WebOptionPane.showMessageDialog(null, e, "Error", WebOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                alerts.getErrorAlert(e);
            }
        }
    }

    public void getConnectionInformation() {
        ResultSet rs = null;
        try {
            pst = ConnConfig.getServerConfig().prepareStatement("SELECT * FROM tbl_connection WHERE Id = ? ");
            pst.setString(1, "1");
            rs = pst.executeQuery();
            while (rs.next()) {
                connectionInfo.setServerIP(rs.getString("server_ip"));
                connectionInfo.setPort(rs.getString("server_port"));
                connectionInfo.setDatabase(rs.getString("database"));
                connectionInfo.setUsername(rs.getString("user_name"));
                connectionInfo.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            //WebOptionPane.showMessageDialog(null, e, "Error", WebOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                //WebOptionPane.showMessageDialog(null, e, "Error", WebOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                alerts.getErrorAlert(e);
            }
        }
    }

    public void fillStatusCombo(JFXComboBox cmbStatus) {
        ResultSet rs = null;
        cmbStatus.getItems().clear();
        try {
            pst = conn.prepareStatement("SELECT status FROM ad_status");
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {

            }
            while (rs.next()) {
                cmbStatus.getItems().add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!pst.isClosed()) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void fillUserTypeCombo(JFXComboBox cmbUserType) {
        ResultSet rs = null;
        cmbUserType.getItems().clear();
        try {
            pst = conn.prepareStatement("SELECT type FROM user_type");
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {

            }
            while (rs.next()) {
                cmbUserType.getItems().add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!pst.isClosed()) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getUserTypeByType(String type) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM user_type WHERE type = ?");
            pst.setString(1, type);
            rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                userType.resetAll();
            }
            while (rs.next()) {
                userType.setId(rs.getInt(1));
                userType.setType(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            alerts.getErrorAlert(e);
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!pst.isClosed()) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                alerts.getErrorAlert(e);
            }
        }
    }

    public void getAD_StatusByStatus(String status) {
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM ad_status WHERE status = ?");
            pst.setString(1, status);
            rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                ad_status.resetAll();
            }
            while (rs.next()) {
                ad_status.setId(rs.getInt(1));
                ad_status.setStatus(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            alerts.getErrorAlert(e);
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!pst.isClosed()) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                alerts.getErrorAlert(e);
            }
        }
    }

    public void fillUserTable(TableView tblUser) {
        ResultSet rs = null;
        ObservableList<ViewUserController.UserRow> userRows = FXCollections.observableArrayList();
        try {
            pst = conn.prepareStatement("SELECT u.id,user_name,u.full_name,u.nic_number,u.contact_number,ads.status FROM user u INNER JOIN ad_status ads on u.ads_id = ads.id");
            rs = pst.executeQuery();
            /*if (!rs.isBeforeFirst()) {
                user.resetAll();
            }*/
            while (rs.next()) {
                userRows.add(
                        new ViewUserController.UserRow(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6)
                        )
                );
            }
            tblUser.setItems(userRows);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!pst.isClosed()) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
