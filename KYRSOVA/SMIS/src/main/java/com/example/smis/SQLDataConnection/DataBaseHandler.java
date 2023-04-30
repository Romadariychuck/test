package com.example.smis.SQLDataConnection;
import com.example.smis.User;

import java.sql.*;


public class DataBaseHandler extends Configs {
    Connection dbConnection;
    //Функція для підключення до бази данних

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbConnection;
    }

    //Метод для контроллера Регістрації заявки користувача
    public void regUser(String Name, String Last_Name, String Auto, String Telephone, String Register_Number, String Login, String Password) {
        String insert = "INSERT INTO `database`.`intousers` (`Name`, `Last_name`, `Telephone`, `Auto`, `Register_Number`, `Login`, `Password`) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prST = null;
        try {
            prST = getDbConnection().prepareStatement(insert);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(1, Name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(2, Last_Name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(3, Login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(4, Auto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(5, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(6, Telephone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(7, Register_Number);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Метод для контроллера додавання користувача в "Адмін панелі"
    public void addUser(String Name, String Last_Name, String Auto, String Telephone, String Register_Number, String Login, String Password) {
        String insert = "INSERT INTO `database`.`intousers` (`Name`, `Last_name`, `Telephone`, `Auto`, `Register_Number`, `Login`, `Password`) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prST = null;
        try {
            prST = getDbConnection().prepareStatement(insert);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(1, Name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(2, Last_Name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(3, Login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(4, Auto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(5, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(6, Telephone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(7, Register_Number);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(User user){
        ResultSet rs = null;
        String SignUp = " SELECT * FROM " + Constant.USER_TABLE + " WHERE " + Constant.USERS_LOGIN +" =? AND "+ Constant.USERS_PASSWORD +" =?";
        PreparedStatement prST = null;
        try {
            prST = getDbConnection().prepareStatement(SignUp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(1, user.getLogin());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prST.setString(2, user.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            rs = prST.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}
