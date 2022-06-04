package com.example.tfgfinal.Connection;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConSQL {
    Connection con;
    String uname,pass,ip,port,database;
    public Connection conClass() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
         ip = "192.168.1.132";
         port = "1433";
         database = "TFGAndroid";
         uname = "adri";
         pass = "1234";

        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL =
                    "jdbc:jtds:sqlserver://" +ip + ":" + port + ";" + "databasename=" +database + ";user=" +uname + ";password=" + pass +";" + "ssl=request";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (Exception e) {
            Log.e("Error: ",e.getMessage());}

        return connection;

    }
}
