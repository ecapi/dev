package login.backing;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import java.sql.*;
import oracle.jdbc.OracleDriver;

public class GetUser {
    private RichInputText uid;
    private RichCommandButton commandButton1;


    public void setUid(RichInputText inputText1) {
        this.uid = inputText1;
    }

    public RichInputText getUid() {
        return uid;
    }

    public void setCommandButton1(RichCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public RichCommandButton getCommandButton1() {
        return commandButton1;
    }

    public String login_action() {
        // Add event code here...
        String user = this.getUid().getValue().toString();
        Connection conn;

        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery ("SELECT 'x' FROM employees where first_name = '"+user+"'");
            if (rset.next())  {
                conn.close();
                    return "good";
            }
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return "bad";
    }

    public static Connection getConnection() throws SQLException {
        String username = "hr";
        String password = "hr";
        String thinConn = "jdbc:oracle:thin:@localhost:1521:XE";
        DriverManager.registerDriver(new OracleDriver());
        Connection conn =
            DriverManager.getConnection(thinConn, username, password);
        conn.setAutoCommit(false);
        return conn;
    }

}
