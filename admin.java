

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class admin
{
    private  Connection con;

    private String name;

    public  Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateInfosEleve(int matricule, String field, String parametre) throws SQLException {
       Statement querry = getCon().createStatement();
       ResultSet resultSet;

       switch (field){
           case "nom":
               resultSet = querry.executeQuery("SELECT E.Id_Gen from eleve as E INNER JOIN general AS G on G.ID = E.ID_Gen WHERE E.Matricule="+matricule);
               resultSet.next();
                   querry.executeUpdate("UPDATE general SET Nom='" + parametre + "' where general.ID=" + resultSet.getInt(1));
               break;

           case "prenom":
               resultSet = querry.executeQuery("SELECT E.Id_Gen from eleve as E INNER JOIN general AS G on G.ID = E.ID_Gen WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE general SET Prenom='" + parametre + "' where general.ID=" + resultSet.getInt(1));
               break;
           case "date":
               resultSet = querry.executeQuery("SELECT E.Id_Iden from eleve as E INNER JOIN identite AS I on I.ID = E.Id_Iden WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE identite SET DOB='" + parametre + "' where identite.ID=" + resultSet.getInt(1));
               break;
           case "villeNaissance":
               resultSet = querry.executeQuery("SELECT E.Id_Iden from eleve as E INNER JOIN identite AS I on I.ID = E.Id_Iden WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE identite SET VilleNaissance='" + parametre + "' where identite.ID=" + resultSet.getInt(1));
               break;
           case "pays":
               resultSet = querry.executeQuery("SELECT E.Id_Iden from eleve as E INNER JOIN identite AS I on I.ID = E.Id_Iden WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE identite SET PaysNaissance='" + parametre + "' where identite.ID=" + resultSet.getInt(1));
               break;
           case "sexe":
               resultSet = querry.executeQuery("SELECT E.Id_Iden from eleve as E INNER JOIN identite AS I on I.ID = E.Id_Iden WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE identite SET Sexe='" + parametre + "' where identite.ID=" + resultSet.getInt(1));
               break;
           case "rue":
               resultSet = querry.executeQuery("SELECT E.Id_Coor from eleve as E INNER JOIN coordonnees AS C on C.ID = E.Id_Coor WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE coordonnees SET Rue='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
               break;
           case "codePostal":
               resultSet = querry.executeQuery("SELECT E.Id_Coor from eleve as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE coordonnees SET CodePostal='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
               break;
           case "ville":
               resultSet = querry.executeQuery("SELECT E.Id_Coor from eleve as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE coordonnees SET Ville='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
               break;
           case "email":
               resultSet = querry.executeQuery("SELECT E.Id_Coor from eleve as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE coordonnees SET Email='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
               break;
           case "telephone":
               resultSet = querry.executeQuery("SELECT E.Id_Coor from eleve as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
               resultSet.next();
               querry.executeUpdate("UPDATE coordonnees SET Telephone='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
               break;
       }



   }

   public void updateInfos(int matricule) throws SQLException {
       Statement querry = getCon().createStatement();
       ResultSet resultSet;
       Scanner scanner = new Scanner(System.in);
       System.out.println("Pour changer une valeur il faut rentrer le type (eleve/responsalbe), le nom du champ et la nouvelle valeur:");
       String resp = scanner.nextLine();
       String[] resps = resp.split(" ");

        switch (resps[0]){
            case "eleve":
                updateInfosEleve(matricule,resps[1],resps[2]);
                break;
            case "responsable":
                updateInfosResp(matricule,resps[1],resps[2]);
                break;
        }

   }

    private void updateInfosResp(int matricule, String field, String parametre) throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet;

        switch (field){

            case "nom":
                resultSet = querry.executeQuery("SELECT PR.Id_Gen from eleve as E INNER Join persresponsable AS PR on E.Id_Resp = PR.ID INNER JOIN general AS G on G.ID = PR.ID_Gen WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE general SET Nom='" + parametre + "' where general.ID=" + resultSet.getInt(1));
                break;

            case "prenom":
                resultSet = querry.executeQuery("SELECT PR.Id_Gen from eleve as E INNER Join persresponsable AS PR on E.Id_Resp = PR.ID INNER JOIN general AS G on G.ID = PR.ID_Gen WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE general SET Prenom='" + parametre + "' where general.ID=" + resultSet.getInt(1));
                break;

            case "rue":
                resultSet = querry.executeQuery("SELECT PR.Id_Coor from eleve as E INNER Join persresponsable AS PR on E.Id_Resp = PR.ID INNER JOIN coordonnees AS C on C.ID = PR.ID_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Rue='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "codePostal":
                resultSet = querry.executeQuery("SELECT PR.Id_Coor from eleve as E INNER Join persresponsable AS PR on E.Id_Resp = PR.ID INNER JOIN coordonnees AS C on C.ID = PR.ID_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET CodePostal='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "ville":
                resultSet = querry.executeQuery("SELECT PR.Id_Coor from eleve as E INNER Join persresponsable AS PR on E.Id_Resp = PR.ID INNER JOIN coordonnees AS C on C.ID = PR.ID_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Ville='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "email":
                resultSet = querry.executeQuery("SELECT PR.Id_Coor from eleve as E INNER Join persresponsable AS PR on E.Id_Resp = PR.ID INNER JOIN coordonnees AS C on C.ID = PR.ID_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Email='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "telephone":
                resultSet = querry.executeQuery("SELECT PR.Id_Coor from eleve as E INNER Join persresponsable AS PR on E.Id_Resp = PR.ID INNER JOIN coordonnees AS C on C.ID = PR.ID_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Telephone='" + parametre + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
        }

    }

    public void resopInfos(int matricule) throws SQLException {
       Statement querry = getCon().createStatement();
       ResultSet resultSet;

       resultSet = querry.executeQuery("SELECT E.Matricule, G.Nom, G.Prenom from eleve as E " +
               "INNER Join persresponsable AS PR on E.Id_Resp = PR.ID " +
               "INNER JOIN general AS G on PR.Id_Gen = G.ID  where E.Matricule='"+matricule+"'" );
       while(resultSet.next())
           System.out.println("Responsable:  "+" Nom: "+resultSet.getString(2)+" Prénom(s): "+resultSet.getString(3));

       resultSet = querry.executeQuery("SELECT E.Matricule, C.Rue, C.CodePostal, C.Ville, C.Email, C.Telephone from eleve as E " +
               "INNER Join persresponsable AS PR on E.Id_Resp = PR.ID  "+
               "INNER JOIN coordonnees AS C on PR.ID_Coor = C.ID where E.matricule='"+matricule+"'");
       while(resultSet.next())
           System.out.println(" Rue: "+resultSet.getString(2)+" Code Postal: "+resultSet.getString(3) + " Ville: " + resultSet.getString(4)+" Email: " + resultSet.getString(5)+" Telephone: " + resultSet.getString(6));

   }

    public void eleveInfos(int index, int matricule) throws SQLException {

        Statement querry = getCon().createStatement();
        ResultSet resultSet;

        resultSet = querry.executeQuery("SELECT E.Matricule, G.Nom, G.Prenom from eleve as E " +
                "INNER JOIN general AS G on E.Id_Gen = G.ID where E.Matricule='"+matricule+"'" );
        while(resultSet.next())
            System.out.println("Matricule: "+resultSet.getInt(1)+" Nom: "+resultSet.getString(2)+" Prénom(s): "+resultSet.getString(3));
        resultSet = querry.executeQuery("SELECT  I.DOB, I.VilleNaissance, I.PaysNaissance, I.Sexe from eleve as E " +
                "INNER JOIN identite AS I on E.Id_Iden = I.ID where E.matricule='"+matricule+"'");
        while(resultSet.next())
            System.out.println( " Date de naissance: "+resultSet.getString(1)+" Ville de Naissance: "+resultSet.getString(2) + " Pays de Naissance: " + resultSet.getString(3)+" Sexe: " + resultSet.getString(4));

        resultSet = querry.executeQuery("SELECT E.Matricule, C.Rue, C.CodePostal, C.Ville, C.Email, C.Telephone from eleve as E " +
                "INNER JOIN coordonnees AS C on E.ID_Coor = C.ID where E.matricule='"+matricule+"'");
        while(resultSet.next())
            System.out.println(" Rue: "+resultSet.getString(2)+" Code Postal: "+resultSet.getString(3) + " Ville: " + resultSet.getString(4)+" Email: " + resultSet.getString(5)+" Telephone: " + resultSet.getString(6));

            resopInfos(matricule);
            updateInfos(matricule);
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
             resultSet = querry.executeQuery("SELECT E.Matricule, G.Nom, G.Prenom from eleve as E INNER JOIN general AS G on E.Id_Gen = G.ID");
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
        else if(forGroup == false)
            eleveInfos(choice,matricules.get(choice-1));
        else if(forGroup == true)
            return matricules.get(choice-1);

        return 0;
    }

    public void listProfs() throws SQLException{
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT P.Matricule, G.Nom, G.Prenom from professeur as P INNER JOIN general AS G on P.Id_Gen = G.ID");
        int i = 1;
        ArrayList<Integer> matricules = new ArrayList<>();
        while (resultSet.next()){
            matricules.add(resultSet.getInt(1));
            System.out.println(i+") Matricule: " + resultSet.getInt(1) + " Nom: " + resultSet.getString(2) + " Prénom(s): " + resultSet.getString(3));
            i++;
        }

        System.out.println("Selectionner un professeur en ecrivant son numéro ('1)')\n Pour revenir inserer 0.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 0)
            return;
        else
            professeurInfos(choice,matricules.get(choice-1));


    }

    private void professeurInfos(int index, int matricule) throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet;

        resultSet = querry.executeQuery("SELECT P.Matricule, G.Nom, G.Prenom from professeur as P " +
                "INNER JOIN general AS G on P.Id_Gen = G.ID where P.Matricule='"+matricule+"'" );
        while(resultSet.next())
            System.out.println("Matricule: "+resultSet.getInt(1)+" Nom: "+resultSet.getString(2)+" Prénom(s): "+resultSet.getString(3));
        resultSet = querry.executeQuery("SELECT P.Matricule, C.Rue, C.CodePostal, C.Ville, C.Email, C.Telephone from professeur as P " +
                "INNER JOIN coordonnees AS C on P.ID_Coor = C.ID where P.matricule='"+matricule+"'");
        while(resultSet.next())
            System.out.println(" Rue: "+resultSet.getString(2)+" Code Postal: "+resultSet.getString(3) + " Ville: " + resultSet.getString(4)+" Email: " + resultSet.getString(5)+" Telephone: " + resultSet.getString(6));
        updateInfosProfesseur(matricule);
    }


    public void updateInfosProfesseur(int matricule) throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pour changer une valeur il faut rentrer le nom du champ et la nouvelle valeur:");
        String resp = scanner.nextLine();
        String[] resps = resp.split(" ");

        switch (resps[0]){
            case "nom":
                resultSet = querry.executeQuery("SELECT P.Id_Gen from professeur as P INNER JOIN general AS G on G.ID = P.ID_Gen WHERE P.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE general SET Nom='" + resps[1] + "' where general.ID=" + resultSet.getInt(1));
                break;

            case "prenom":
                resultSet = querry.executeQuery("SELECT P.Id_Gen from professeur as P INNER JOIN general AS G on G.ID = P.ID_Gen WHERE P.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE general SET Prenom='" + resps[1] + "' where general.ID=" + resultSet.getInt(1));
                break;

            case "rue":
                resultSet = querry.executeQuery("SELECT E.Id_Coor from professeur as E INNER JOIN coordonnees AS C on C.ID = E.Id_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Rue='" + resps[1] + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "codePostal":
                resultSet = querry.executeQuery("SELECT E.Id_Coor from professeur as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET CodePostal='" + resps[1] + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "ville":
                resultSet = querry.executeQuery("SELECT E.Id_Coor from professeur as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Ville='" + resps[1] + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "email":
                resultSet = querry.executeQuery("SELECT E.Id_Coor from professeur as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Email='" + resps[1] + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
            case "telephone":
                resultSet = querry.executeQuery("SELECT E.Id_Coor from professeur as E INNER JOIN coordonnees AS C on C.ID = Id_Coor WHERE E.Matricule="+matricule);
                resultSet.next();
                querry.executeUpdate("UPDATE coordonnees SET Telephone='" + resps[1] + "' where coordonnees.ID=" + resultSet.getInt(1));
                break;
        }



    }

    public int listCours(boolean forGroupe) throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT * from cours");
        int i = 1;
        ArrayList<Integer> cours = new ArrayList<>();
        while (resultSet.next()){
            cours.add(resultSet.getInt(1));
            System.out.println(i+") Code: " + resultSet.getInt(1) + " Nom: " + resultSet.getString(2) + " Description: " + resultSet.getString(3)+ " Année: " + resultSet.getInt(4)+" Coefficient: " + resultSet.getString(5)+ " Pourcentage DE: " + resultSet.getString(6)+ " Pourcentage TP: " + resultSet.getString(8)+ " Pourcentage Projet: " + resultSet.getString(9));
            i++;
        }

        System.out.println("Selectionner un cours en ecrivant son numéro ('1)')\n Pour revenir inserer 0.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 0)
            return 0;
        if (forGroupe == true){
            return cours.get(choice-1);
        }
        else
            coursInfos(cours.get(choice-1));

        return 0;
    }

    private void coursInfos(int matricule) throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pour changer une valeur il faut rentrer le nom du champ et la nouvelle valeur:");
        String resp = scanner.nextLine();
        String[] resps = resp.split(" ");

        switch (resps[0]){
            case "nom":
                querry.executeUpdate("UPDATE cours SET Nom='" + resps[1] + "' where code="+matricule);
                break;
            case "description":
                querry.executeUpdate("UPDATE cours SET description='" + resps[1] + "' where code="+matricule);
                break;
            case "annee":
                querry.executeUpdate("UPDATE cours SET annee='" + resps[1] + "' where code="+matricule);
                break;
            case "coefficient":
                querry.executeUpdate("UPDATE cours SET coefficient='" + resps[1] + "' where code="+matricule);
                break;
            case "de":
                querry.executeUpdate("UPDATE cours SET Pourc_DE='" + resps[1] + "' where code="+matricule);
                break;
            case "ce":
                querry.executeUpdate("UPDATE cours SET Pourc_CE='" + resps[1] + "' where code="+matricule);
                break;
            case "prj":
                querry.executeUpdate("UPDATE cours SET Pourc_Prj='" + resps[1] + "' where code="+matricule);
                break;

        }



    }


    public int addGeneral() throws SQLException {
       int id = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nom:");
        String nom = scanner.nextLine();

        System.out.println("Prenom:");
        String prenom = scanner.nextLine();

        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT ID from general");
        while (resultSet.next()){
            id++;
        }

        System.out.println(id);
        querry.executeUpdate("INSERT INTO general(ID, Nom, Prenom) VALUES ('" + id +"','" + nom + "','" + prenom +"')");


       return id;
    }

    public int addIdenity() throws SQLException {
        int id = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Date de naissance:");
        String dob = scanner.nextLine();

        System.out.println("Ville de naissance:");
        String ville = scanner.nextLine();

        System.out.println("Pays de naissance:");
        String pays = scanner.nextLine();

        System.out.println("Sexe:");
        String sexe = scanner.nextLine();

        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT ID from identite");
        while (resultSet.next()){
            id++;
        }

        System.out.println(id);
        querry.executeUpdate("INSERT INTO identite(ID, DOB, VilleNaissance, PaysNaissance, Sexe) VALUES ('" + id +"','" + dob + "','" + ville +"','"+ pays+ "','" + sexe +"')");


        return id;
    }

    public int addCoord() throws SQLException {
        int id = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rue:");
        String rue = scanner.nextLine();

        System.out.println("Code Postal:");
        String code = scanner.nextLine();

        System.out.println("Ville:");
        String ville = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();

        System.out.println("Telephone:");
        String tel = scanner.nextLine();

        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT ID from coordonnees");
        while (resultSet.next()){
            id++;
        }

        System.out.println(id);
        querry.executeUpdate("INSERT INTO coordonnees(ID, Rue, CodePostal, Ville, Email, Telephone) VALUES ('" + id +"','" + rue + "','" + code +"','"+ ville + "','" + email + "','" + tel +"')");


        return id;
    }


    public int addResponsable() throws SQLException{
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT ID from persresponsable");
        int id = 1;
        while (resultSet.next()){
            id++;
        }
        querry.executeUpdate("INSERT INTO persresponsable(ID, Id_Gen, Id_Coor) VALUES ('" + id +"','" + addGeneral() + "','" + addCoord() +"')");
       return id;
    }

    public int addProfesseur() throws SQLException{
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT Matricule from professeur");
        int id = 1;
        while (resultSet.next()){
            id++;
        }
        querry.executeUpdate("INSERT INTO professeur(Matricule, Id_Gen, Id_Coor, Id_Iden) VALUES ('" + id +"','" + addGeneral() + "','" + addCoord() +"','1')");
        return id;
    }

    public int addGroup() throws SQLException{
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT ID from groupe");
        int id = 1;
        while (resultSet.next()){
            id++;
        }
        System.out.println("Inserer le nom du groupe:");
        String nom = new Scanner(System.in).nextLine();

        querry.executeUpdate("INSERT INTO groupe(ID, Nom) VALUES ('" + id +"','"+ nom+"')");


        return id;
    }

    public void addEleveToGroup() throws SQLException {

       int matricule = listEleves(true,false, false);

       System.out.println("Inserer le nom du groupe:");
       String nom_grp = new Scanner(System.in).nextLine();
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT ID from groupe where nom='"+ nom_grp +"'");
        int id = 0;
        while (resultSet.next()){
            id = resultSet.getInt(1);
        }

        querry.executeUpdate("UPDATE eleve SET Id_Grp="+ id + " where matricule='"+matricule+"'");


    }

    public void removeEleveFromGroup() throws SQLException {

        int matricule = listEleves(true,false, false);
        Statement querry = getCon().createStatement();

        int id = 0;


        querry.executeUpdate("UPDATE eleve SET Id_Grp="+ id + " where matricule='"+matricule+"'");


    }

    public void listGroupe() throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT * from groupe");
        int i = 1;
        ArrayList<Integer> cours = new ArrayList<>();
        while (resultSet.next()){
            cours.add(resultSet.getInt(1));
            System.out.println(i+") ID: " + resultSet.getInt(1) + " Nom: " + resultSet.getString(2));
            i++;
        }


    }

    public int addCours() throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT Code from cours");
        int id = 1;
        while (resultSet.next()){
            id++;
        }


        System.out.println("Inserer les données à rentrer (Nom, Description...");
        String line = new Scanner(System.in).nextLine();
        String data[] = line.split(" ");

        querry.executeUpdate("INSERT INTO cours(Code, Nom, Description, Annee, Coefficient, Pourc_De, Pourc_Ce, Pourc_Prj,Id_Prof) VALUES ('" + id +"','"+ data[0]+ "','" + data[1] + "','" +data[2] + "','" +data[3] + "','" +data[4] + "','" + data[5] + "','" +data[6]  + "','" +data[7] +"')");


        return id;

    }

    public void addCoursToGroupe() throws SQLException{
        Statement querry = getCon().createStatement();
        System.out.println("Rentrer le nom du groupe:");
        String  matricule = new Scanner(System.in).nextLine();
        ResultSet resultSet = querry.executeQuery("SELECT ID from groupe where nom='" + matricule+"'");

        System.out.println("Rentrer le code du cours:");
        int code = new Scanner(System.in).nextInt();
        resultSet.next();
        querry.executeUpdate("INSERT INTO groupecours(Id_Grp, Code) VALUES ('" + resultSet.getInt(1) + "','" + code +"')");

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

    public void updateNote(int matricule, int code_cours) throws SQLException{
        Statement querry = getCon().createStatement();
        System.out.println("Rentrer les notes à changer avec le type avant:");
        String line = new Scanner(System.in).nextLine();
        String data[] = line.split(" ");
       ;
        if (data[0].compareTo("DE") == 0){
            querry.executeUpdate("UPDATE notes SET Note_DE='" + data[1] +"' where Matricule="+matricule+" and code="+code_cours );
        }
        if (data[0].compareTo("TP") == 0){
            querry.executeUpdate("UPDATE notes SET Note_CE='" + data[1] +"' where Matricule='"+matricule+"' and code='"+code_cours+"'" );
        }
        if (data[0].compareTo("PRJ") == 0){
            querry.executeUpdate("UPDATE notes SET Note_PRJ='" + data[1] +"' where Matricule='"+matricule+"' and code='"+code_cours+"'" );
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

    public void addEleve() throws SQLException {
        Statement querry = getCon().createStatement();
        ResultSet resultSet = querry.executeQuery("SELECT Matricule from eleve");
        int id = 1;
        while (resultSet.next()){
            id++;
        }
        System.out.println("Inserer le code du groupe:");
        int grpCode = new Scanner(System.in).nextInt();
        System.out.println("Inserer la promotion:");
        String prom = new Scanner(System.in).nextLine();
        querry.executeUpdate("INSERT INTO eleve(Matricule, Id_Gen, Id_Coor, Id_Iden, Id_Grp, Id_Resp, mdp, Promotion) VALUES ('" + id +"','" + addGeneral() + "','" + addCoord() + "','"+ addIdenity() + "','" + grpCode + "','" + addResponsable() + "','" + null + "','"+ prom +"')");

    }



}
