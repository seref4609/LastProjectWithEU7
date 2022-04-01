package com.cydeo.EU7DBBootcamp;

import com.cydeo.utilities.ConfigurationReader;
import com.google.common.base.Verify;
import org.jsoup.select.Evaluator;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static java.sql.Driver.*;

public class DBConnectionWithTestNG {
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    String query="select * from spartans";

    @BeforeMethod
    public void connectToDB() throws SQLException {
        connection = DriverManager.getConnection(ConfigurationReader.get("spartan.DBUrl"), ConfigurationReader.get("spartan.DBusername"), ConfigurationReader.get("spartan.DBpassword"));
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
    }
    @AfterMethod
    public void closeDBconnection() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();

    }
    @Test
    public void Test1() throws SQLException {

        while (resultSet.next())
            System.out.println("resultSet = " + resultSet.getString(1) + " " + resultSet.getString(2));



    }

    @Test
    public void Test2() throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData(); // this object gives us column info

        int columnCount = rsmd.getColumnCount();
        Map<String,Object> row = new HashMap<>();
        while (resultSet.next()){
            String result="";
            for (int i = 1; i <= columnCount ; i++) {

                row.put(rsmd.getColumnName(i),resultSet.getObject(i));

                result+=" "+resultSet.getString(i);
            }
            System.out.println("result = " + result);
            System.out.println("row = " + row);




        }

            }
        }






