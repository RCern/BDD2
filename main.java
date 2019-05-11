

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



    public static Object Login() throws SQLException {
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
                admin Admin = new admin();
                Admin.setCon(con);
                Admin.setName(username);

                return Admin;
            }

        }

        querry.clearBatch();

        resultSet = querry.executeQuery("SELECT Matricule, MDP from eleve where Matricule='"+username+"' AND MDP='"+password+"'");

        while (resultSet.next()){
            if (!resultSet.getString(1).isEmpty()){
                Eleve eleve = new Eleve();

                eleve.setCon(con);
                eleve.setMatricule(Integer.parseInt(username));


                return eleve;
            }

        }

        querry.clearBatch();

        resultSet = querry.executeQuery("SELECT Matricule, MDP from professeur where Matricule='"+username+"' AND MDP='"+password+"'");

        while (resultSet.next()){
            if (!resultSet.getString(1).isEmpty()){
                Prof prof = new Prof();

                prof.setCon(con);
                prof.setMatricule(Integer.parseInt(username));
                return prof;
            }

        }


        return null;
    }

    static void  menu() throws SQLException {
        Object obj = Login();

        if (obj.getClass().toString().compareTo("class admin") == 0) {
            admin Admin = (admin) obj;
            boolean indef = true;
            while (indef) {
                System.out.println("Bienvenue: " + Admin.getName());
                System.out.println("Selectionner votre choix en écrivant son numéro:");
                System.out.println("1) Lister les eleves/ Mettre a jour les donnees d'un eleve.");
                System.out.println("2) Lister les professeurs: Mettre a jour les donnees d'un professeur.");
                System.out.println("3) Lister les groupes/Modifier un groupe.");
                System.out.println("4) Lister un cours/ modifier un cours.");
                System.out.println("5) Rechercher un eleve par son groupe.");
                System.out.println("6) Rechercher un eleve par sa promotion.");
                System.out.println("7) Ajouter un cours.");
                System.out.println("8) Ajouter un groupe.");
                System.out.println("9) Ajouter un eleve a un groupe.");
                System.out.println("10) Ajouter un cours a un groupe.");
                System.out.println("11) Ajouter une note.");
                System.out.println("12) Ajouter un professeur.");
                System.out.println("13) Mettre a jour une note.");
                System.out.println("14) Ajouter un eleve");
                System.out.println("15) Releve des notes d'un eleve.");

                int choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1:
                        Admin.listEleves(false, false, false);
                        break;
                    case 2:
                        Admin.listProfs();
                        break;
                    case 3:
                        Admin.listGroupe();
                        break;
                    case 4:
                        Admin.listCours(false);
                        break;
                    case 5:
                        Admin.searchGroupe();
                        break;

                    case 6:
                        Admin.searchPromotion();
                        break;
                    case 7:
                        Admin.addCours();
                        break;
                    case 8:
                        Admin.addGroup();
                        break;
                    case 9:
                        Admin.addEleveToGroup();
                        break;
                    case 10:
                        Admin.addCoursToGroupe();
                        break;
                    case 11:
                        Admin.addNote();
                        break;
                    case 12:
                        Admin.addProfesseur();
                        break;
                    case 13:
                        int mat = Admin.listEleves(true, false, false);
                        int grp = Admin.listCours(true);
                        Admin.updateNote(mat, grp);
                        break;
                    case 14:
                        Admin.addEleve();
                        break;
                    case 15:
                        Admin.releveDeNotes();
                        break;
                    default:
                        break;


                }
                System.out.println("Deconnexion? 1 pour oui.");
                int dec = new Scanner(System.in).nextInt();
                if (dec == 1) {
                    Admin.getCon().close();
                    indef = false;
                }

            }

        }
        if (obj.getClass().toString().compareTo("class Prof") == 0) {
            Prof prof = (Prof) obj;
            prof.getCodeCourss();
            boolean indef = true;
            while (indef) {
                System.out.println("Bienvenue: " + prof.getMatricule());
                System.out.println("Selectionner votre choix en écrivant son numéro:");
                System.out.println("1) Ajouter une note.");
                System.out.println("2) Mettre a jour une note.");
                System.out.println("3) Afficher les notes d'un eleve");
                System.out.println("4) Rechercher un eleve par son groupe.");
                System.out.println("5) Rechercher un eleve par sa promotion.");


                int choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1:
                        prof.addNote();
                        break;
                    case 2:
                        prof.updateNote();
                        break;
                    case 3:
                        prof.releveDeNotes();
                        break;
                    case 4:
                        prof.searchGroupe();
                        break;
                    case 5:
                        prof.searchPromotion();
                        break;
                    default:
                        break;


                }
                System.out.println("Deconnexion? 1 pour oui.");
                int dec = new Scanner(System.in).nextInt();
                if (dec == 1) {
                    prof.getCon().close();
                    indef = false;
                }

            }


        }
        if (obj.getClass().toString().compareTo("class Eleve") == 0) {
            Eleve eleve = (Eleve) obj;

            boolean indef = true;
            while (indef) {
                System.out.println("Bienvenue: " + eleve.getMatricule());
                System.out.println("Selectionner votre choix en écrivant son numéro:");
                System.out.println("1) Afficher vos notes.");
                System.out.println("2) Rechercher un eleve par son groupe.");
                System.out.println("3) Rechercher un eleve par sa promotion.");


                int choice = new Scanner(System.in).nextInt();

                switch (choice) {

                    case 1:
                        eleve.releveDeNotes();
                        break;
                    case 2:
                        eleve.searchGroupe();
                        break;
                    case 3:
                        eleve.searchPromotion();
                        break;
                    default:
                        break;


                }
                System.out.println("Deconnexion? 1 pour oui.");
                int dec = new Scanner(System.in).nextInt();
                if (dec == 1) {
                    eleve.getCon().close();
                    indef = false;
                }

            }
        }
    }










    public static void main(String[] args) throws SQLException {

      menu();



    }
}
