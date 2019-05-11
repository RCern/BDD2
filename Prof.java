import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Prof {
    private Connection con;

    private int matricule;

    private ArrayList<Integer> codesCours = new ArrayList<>();

    public  Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }


    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public  int listEleves(boolean forGroup, boolean forSearch, boolean forSearchGroupe) throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet;


        if (forSearch == true){
            System.out.println("Rentrer le nom de la promotion:");
            String promotion = new Scanner(System.in).nextLine();
            resultSet = querry.executeQuery("SELECT E.Matricule, G.Nom, G.Prenom from eleve as E INNER JOIN general AS G on E.Id_Gen = G.ID where E.promotion='"+promotion+"'");
        }

        if (forSearchGroupe == true){
            System.out.println("Rentrer le nom du groupe:");
            String groupe = new Scanner(System.in).nextLine();
            ResultSet idGrp = querry.executeQuery("SELECT ID from groupe where Nom='"+ groupe+"'");
            idGrp.next();
            int groupeInt = idGrp.getInt(1);
            resultSet = querry.executeQuery("SELECT E.Matricule, G.Nom, G.Prenom from eleve as E INNER JOIN general AS G on E.Id_Gen = G.ID where E.ID_Grp='"+groupeInt+"'");
        }

        else{
            resultSet = querry.executeQuery("SELECT G.ID_Grp, G.Code from cours as C  INNER Join groupecours as G on C.Code = G.Code where C.Id_prof="+matricule);
            resultSet.next();
           // int Id_grp = resultSet.getInt(1);
            int gCode = resultSet.getInt(2);

            resultSet = querry.executeQuery("SELECT E.Matricule, G.Nom, G.Prenom from eleve as E INNER JOIN general AS G on E.Id_Gen = G.ID INNER JOIN groupecours as GC on E.Id_Grp = GC.Id_Grp where GC.code="+gCode);
        }
        int i = 1;
        ArrayList<Integer> matricules = new ArrayList<>();
        while (resultSet.next()){
            matricules.add(resultSet.getInt(1));
            System.out.println(i+") Matricule: " + resultSet.getInt(1) + " Nom: " + resultSet.getString(2) + " Prénom(s): " + resultSet.getString(3));
            i++;
        }

        System.out.println("Selectionner un eleve en ecrivant son numéro ('1)')\n Pour revenir inserer 0.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 0)
            return 0;
        else if(forGroup == true)
            return matricules.get(choice-1);

        return 0;
    }


    public void addNote() throws SQLException {
        Statement querry = getCon().createStatement();
        System.out.println("Rentrer la matricule de l'éleve, le code du cours et la note avec le type avant:");
        String line = new Scanner(System.in).nextLine();
        String data[] = line.split(" ");

        ResultSet resultSet = querry.executeQuery("SELECT Matricule, Code from notes where Matricule='"+data[0]+"' and Code='"+data[1]+"'");

        if (resultSet.next()) {
            if (data[2].compareTo("DE") == 0) {
                querry.executeUpdate("UPDATE notes SET Note_DE='" + data[3] + "' where Matricule=" + data[0] + " and code=" + data[1]);
            }
            if (data[2].compareTo("TP") == 0) {
                querry.executeUpdate("UPDATE notes SET Note_CE='" + data[3] + "' where Matricule='" + data[0] + "' and code='" + data[1] + "'");
            }
            if (data[2].compareTo("PRJ") == 0) {
                querry.executeUpdate("UPDATE notes SET Note_PRJ='" + data[3] + "' where Matricule='" + data[0] + "' and code='" + data[1] + "'");
            }
        }
        else {
            if (data[2].compareTo("DE") == 0) {
                querry.executeUpdate("INSERT INTO notes(Matricule, Code, Note_DE) VALUES ('"+data[0]+"','"+data[1]+"','" +data[3]+"')");
            }
            if (data[2].compareTo("TP") == 0) {
                querry.executeUpdate("INSERT INTO notes(Matricule, Code, Note_CE) VALUES ('"+data[0]+"','"+data[1]+"','" +data[3]+"')");
            }
            if (data[2].compareTo("PRJ") == 0) {
                querry.executeUpdate("INSERT INTO notes(Matricule, Code, Note_PRJ) VALUES ('"+data[0]+"','"+data[1]+"','" +data[3]+"')");
            }

        }


    }

    public void updateNote() throws SQLException{
        Statement querry = getCon().createStatement();
        System.out.println("Rentrer la matricule de l'éleve, le code du cours et la note avec le type avant:");
        String line = new Scanner(System.in).nextLine();
        String data[] = line.split(" ");

        if (data[2].compareTo("DE") == 0){
            querry.executeUpdate("UPDATE notes SET Note_DE='" + data[3] +"' where Matricule="+data[0]+" and code="+data[1] );
        }
        if (data[2].compareTo("TP") == 0){
            querry.executeUpdate("UPDATE notes SET Note_CE='" + data[3] +"' where Matricule='"+data[0]+"' and code='"+data[1]+"'" );
        }
        if (data[2].compareTo("PRJ") == 0){
            querry.executeUpdate("UPDATE notes SET Note_PRJ='" + data[3] +"' where Matricule='"+data[0]+"' and code='"+data[1]+"'" );
        }


    }

    public void releveDeNotes() throws SQLException {
        int matricules = listEleves(true,false, false);
        Statement querry = getCon().createStatement();
        Statement querry1 = getCon().createStatement();
        ArrayList<Double> grades = new ArrayList<>();
        ArrayList<Double> coeffs = new ArrayList<>();
        double moyenne;
        double temp = 0;
        double coefficintsTotal=0;

        ResultSet resultSet = querry.executeQuery("SELECT Code ,Note_DE, Note_CE, Note_PRJ from notes where Matricule="+matricules);
        ResultSet resultSet1;
        while (resultSet.next()){
            resultSet1 = querry1.executeQuery("SELECT Nom,Pourc_DE, Pourc_CE, Pourc_PRJ, Coefficient from cours where Code="+ resultSet.getInt(1));
            resultSet1.next();
            moyenne = (resultSet.getDouble(2)*resultSet1.getDouble(2) + resultSet.getDouble(3)*resultSet1.getDouble(3) + resultSet.getDouble(4)*resultSet1.getDouble(4) )/100;
            temp += moyenne * resultSet1.getDouble(5);
            coefficintsTotal += resultSet1.getDouble(5);
            System.out.println("Matiere: " + resultSet1.getString(1) + " Note DE: " + resultSet.getDouble(2) + " Note CE: " + resultSet.getDouble(3) + " Note Projet: "+ resultSet.getDouble(4) + " Moyenne:" + moyenne);

        }

        System.out.println(temp/coefficintsTotal);

    }

    public void searchPromotion() throws SQLException {
        listEleves(false,true, false);


    }

    public void searchGroupe() throws SQLException {
        listEleves(false,false,true);


    }

    public void getCodeCourss() throws SQLException {
        Statement querry = getCon().createStatement();

        ResultSet resultSet= querry.executeQuery("SELECT Code from cours where Id_Prof="+matricule);

        while (resultSet.next()){
            codesCours.add(resultSet.getInt(1));
        }

    }





}
