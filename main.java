

import java.sql.*;
import java.util.Scanner;

public class main {

    public static Connection adminConnection(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","");
            return con;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static Connection professeurConnection(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database","professeur","prof");
            return con;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static Connection eleveConnection(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","eleve","eleve");
            return con;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }



    public static Connection Login() throws SQLException {
        Scanner input = new Scanner(System.in);

        String username;
        String password;

        Connection con = adminConnection();
        if (con == null)
            return null;

        System.out.println("Username: ");
        username = input.nextLine();
        System.out.println("Password: ");
        password = input.nextLine();

        Statement querry = con.createStatement();
        ResultSet resultSet = querry.executeQuery("select username, MDP from admin where username='"+username+"' AND MDP='" + password+"'");
        while (resultSet.next()){
            if (!resultSet.getString(1).isEmpty()){
                return con;
            }

        }

        querry.clearBatch();

        resultSet = querry.executeQuery("SELECT Matricule, MDP from eleve where Matricule='"+username+"' AND MDP='"+password+"'");

        while (resultSet.next()){
            if (!resultSet.getString(1).isEmpty()){
                System.out.println("here1");
                return eleveConnection();
            }

        }

        querry.clearBatch();

        resultSet = querry.executeQuery("SELECT Matricule, MDP from professeur where Matricule='"+username+"' AND MDP='"+password+"'");

        while (resultSet.next()){
            if (!resultSet.getString(1).isEmpty()){
                System.out.println("here");
                return professeurConnection();
            }

        }


        return null;
    }










    public static void main(String[] args) throws SQLException {
        Connection  conn = Login();
        /*admin Admin = new admin();
        Admin.setCon(conn);
        Admin.searchGroupe();

        Prof pr = new Prof();
        pr.setCon(conn);
        pr.setMatricule(1);

        pr.releveDeNotes();
        */
        Eleve el = new Eleve();
        el.setCon(conn);
        el.setMatricule(20160283);
        el.releveDeNotes();
        //Admin.addProfesseur();
        //Admin.addGroup();
        //Admin.addEleveToGroup();
        //Admin.addNote();
        /*try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database","root","");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from general");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

            con.close();
        }catch(Exception e){
            System.out.println(e);
        }*/



    }
}
