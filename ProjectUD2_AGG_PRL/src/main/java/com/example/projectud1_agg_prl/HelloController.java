package com.example.projectud1_agg_prl;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import org.json.simple.JSONArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {


    @FXML
    private TextArea Texto;
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox Legs;
    @FXML
    private ComboBox Waist;
    @FXML
    private ComboBox Chest;
    @FXML
    private ComboBox Hands;
    @FXML
    private ComboBox Helm;




    private static ArrayList<String> shownSkills = new ArrayList<>();
    private static ArrayList<Integer> levelSkills = new ArrayList<>();

    private String[] ArmorColumns = {"id","name","fire_res","water_res","ice_res","thunder_res","dragon_res"};
    private String[] chestSkills = {"Chest","skill","skill_level"};
    private String[] headSkills = {"headgear","skill","skill_level"};
    private String[] glovesSkills = {"gloves","skill","skill_level"};
    private String[] waistSkills = {"waist","skill","skill_level"};
    private String[] legsSkills = {"legs","skill","skill_level"};
    private String[] valuesArmor = new String[7];
    private String[] valuesSkill = new String[3];
    private String[] split = new String[6];
    private String[] fireRes = new String[2];
    private String[] waterRes = new String[2];
    private String[] iceRes = new String[2];
    private String[] thunderRes = new String[2];
    private String[] dragonRes = new String[2];
    private String[] splitSkills = new String[3];
    private String[] skillLevel = new String[2];
    private String[] skillName = new String[2];

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

    }

    /**
     * Al iniciar el programa consigue los datos de la api mediante HttpUrlConnection y lo escribe con JSONParse en el archivo
     * armaduras.json despues con un newBufferedReader leemos el archivo json y obtenemos los datos del json estos los guardamos
     * en los comboBox y arrays/hasmaps
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String Camino = "armaduras.json";
        boolean exists = false ;






        try {
           // con el metodo DAO creamos una conexion y utilizando el select observo si la  tabla headgear tiene filas o no
            // si no tiene introduzco los datos del json en la base de datos si tiene salto esta parte

                DAO TheDAO = new DAO("monsterhunterworld");
                Connection connection = TheDAO.abrirConexionMySqlDB();
                Statement st = connection.createStatement();
                try {
                    ResultSet resultSet = st.executeQuery("SELECT count(*) as cnt FROM headgear");
                    if (resultSet.next()) {
                        if (resultSet.getInt("cnt") > 0) {
                            exists = true;
                        } else {

                        }
                    }
                } catch (Exception e) {


                }
                if (!exists) {
                    JSONParser parseJ = new JSONParser();
                    JSONArray dataObject = new JSONArray();
                    try (var read = Files.newBufferedReader(Path.of(Camino))) {
                        dataObject = (JSONArray) parseJ.parse(read);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }


                    /**
                     * Tengo un array de jsonObject con todos los objetos Json,
                     * de estos consigo el id , nombre de la armadura pero solo puedo conseguir un String de un array json con las skills y resistencias
                     * algo que no nos dio tiempo a implementar, para conseguir los valores de estos String utilizo arrays y el metodo split
                     */

                    for (int i = 0; i < dataObject.size(); i++) {
                        JSONObject armors = (JSONObject) dataObject.get(i);
                        if (armors.get("type").equals("head")) {

                            valuesArmor[0] = armors.get("id").toString();
                            valuesArmor[1] = armors.get("name").toString();
                            String array = armors.get("resistances").toString();
                            String skill = armors.get("skills").toString();


                            splitSkills = skill.split(":", 3);
                            skillLevel = splitSkills[2].split(",", 2);
                            skillName = splitSkills[1].split(",");

                            split = array.split(":");

                            fireRes = split[1].split(",");
                            waterRes = split[2].split(",");
                            iceRes = split[3].split(",");
                            thunderRes = split[4].split(",");
                            dragonRes = split[5].split(",");

                            String dRes = String.valueOf(dragonRes[0].charAt(0));

                            valuesArmor[2] = fireRes[0];
                            valuesArmor[3] = waterRes[0];
                            valuesArmor[4] = iceRes[0];
                            valuesArmor[5] = thunderRes[0];
                            valuesArmor[6] = dRes;


                            TheDAO.insercionDatos("headgear", ArmorColumns, valuesArmor);


                            valuesSkill[0] = armors.get("id").toString();
                            valuesSkill[1] = skillName[0].substring(1, (skillName[0].length()) - 1);
                            valuesSkill[2] = skillLevel[0];

                            TheDAO.insercionDatos("headgear_skills", headSkills, valuesSkill);


                        } else if (armors.get("type").equals("chest")) {

                            valuesArmor[0] = armors.get("id").toString();
                            valuesArmor[1] = armors.get("name").toString();
                            String array = armors.get("resistances").toString();
                            String skill = armors.get("skills").toString();


                            split = array.split(":");

                            fireRes = split[1].split(",");
                            waterRes = split[2].split(",");
                            iceRes = split[3].split(",");
                            thunderRes = split[4].split(",");
                            dragonRes = split[5].split(",");

                            String dRes = String.valueOf(dragonRes[0].charAt(0));

                            valuesArmor[2] = fireRes[0];
                            valuesArmor[3] = waterRes[0];
                            valuesArmor[4] = iceRes[0];
                            valuesArmor[5] = thunderRes[0];
                            valuesArmor[6] = dRes;


                            TheDAO.insercionDatos("chest", ArmorColumns, valuesArmor);

                            if (skill.length() <= 2) {

                            } else {
                                splitSkills = skill.split(":", 3);
                                skillLevel = splitSkills[2].split(",", 2);
                                skillName = splitSkills[1].split(",");
                                valuesSkill[0] = armors.get("id").toString();
                                valuesSkill[1] = skillName[0].substring(1, (skillName[0].length()) - 1);
                                valuesSkill[2] = skillLevel[0];
                                TheDAO.insercionDatos("chest_skills", chestSkills, valuesSkill);
                            }


                        } else if (armors.get("type").equals("gloves")) {


                            valuesArmor[0] = armors.get("id").toString();
                            valuesArmor[1] = armors.get("name").toString();
                            String array = armors.get("resistances").toString();
                            String skill = armors.get("skills").toString();


                            split = array.split(":");

                            fireRes = split[1].split(",");
                            waterRes = split[2].split(",");
                            iceRes = split[3].split(",");
                            thunderRes = split[4].split(",");
                            dragonRes = split[5].split(",");

                            String dRes = String.valueOf(dragonRes[0].charAt(0));

                            valuesArmor[2] = fireRes[0];
                            valuesArmor[3] = waterRes[0];
                            valuesArmor[4] = iceRes[0];
                            valuesArmor[5] = thunderRes[0];
                            valuesArmor[6] = dRes;


                            TheDAO.insercionDatos("gloves", ArmorColumns, valuesArmor);

                            if (skill.length() <= 2) {

                            } else {
                                splitSkills = skill.split(":", 3);
                                skillLevel = splitSkills[2].split(",", 2);
                                skillName = splitSkills[1].split(",");
                                valuesSkill[0] = armors.get("id").toString();
                                valuesSkill[1] = skillName[0].substring(1, (skillName[0].length()) - 1);
                                valuesSkill[2] = skillLevel[0];
                                TheDAO.insercionDatos("gloves_skills", glovesSkills, valuesSkill);
                            }


                        } else if (armors.get("type").equals("waist")) {


                            valuesArmor[0] = armors.get("id").toString();
                            valuesArmor[1] = armors.get("name").toString();
                            String array = armors.get("resistances").toString();
                            String skill = armors.get("skills").toString();


                            split = array.split(":");

                            fireRes = split[1].split(",");
                            waterRes = split[2].split(",");
                            iceRes = split[3].split(",");
                            thunderRes = split[4].split(",");
                            dragonRes = split[5].split(",");

                            String dRes = String.valueOf(dragonRes[0].charAt(0));

                            valuesArmor[2] = fireRes[0];
                            valuesArmor[3] = waterRes[0];
                            valuesArmor[4] = iceRes[0];
                            valuesArmor[5] = thunderRes[0];
                            valuesArmor[6] = dRes;


                            TheDAO.insercionDatos("waist", ArmorColumns, valuesArmor);


                            if (skill.length() <= 2) {

                            } else {
                                splitSkills = skill.split(":", 3);
                                skillLevel = splitSkills[2].split(",", 2);
                                skillName = splitSkills[1].split(",");
                                valuesSkill[0] = armors.get("id").toString();
                                valuesSkill[1] = skillName[0].substring(1, (skillName[0].length()) - 1);
                                valuesSkill[2] = skillLevel[0];
                                TheDAO.insercionDatos("waist_skills", waistSkills, valuesSkill);
                            }


                        } else if (armors.get("type").equals("legs")) {


                            valuesArmor[0] = armors.get("id").toString();
                            valuesArmor[1] = armors.get("name").toString();
                            String array = armors.get("resistances").toString();
                            String skill = armors.get("skills").toString();


                            split = array.split(":");

                            fireRes = split[1].split(",");
                            waterRes = split[2].split(",");
                            iceRes = split[3].split(",");
                            thunderRes = split[4].split(",");
                            dragonRes = split[5].split(",");

                            String dRes = String.valueOf(dragonRes[0].charAt(0));

                            valuesArmor[2] = fireRes[0];
                            valuesArmor[3] = waterRes[0];
                            valuesArmor[4] = iceRes[0];
                            valuesArmor[5] = thunderRes[0];
                            valuesArmor[6] = dRes;


                            TheDAO.insercionDatos("legs", ArmorColumns, valuesArmor);

                            if (skill.length() <= 2) {

                            } else {
                                splitSkills = skill.split(":", 3);
                                skillLevel = splitSkills[2].split(",", 2);
                                skillName = splitSkills[1].split(",");
                                valuesSkill[0] = armors.get("id").toString();
                                valuesSkill[1] = skillName[0].substring(1, (skillName[0].length()) - 1);
                                valuesSkill[2] = skillLevel[0];
                                TheDAO.insercionDatos("legs_skills", legsSkills, valuesSkill);
                            }


                        }


                    }

                    dataObject.clear();

                }
            /**
             * me conecto a la base de datos y introduzco todos los objetos del combo box desde esta.
             */
            Connection con = TheDAO.abrirConexionMySqlDB();
            Statement statement = con.createStatement();
            ResultSet rsHelm = statement.executeQuery("SELECT name FROM headgear order by name");
            while (rsHelm.next()) {
                Helm.getItems().add(rsHelm.getString("name"));
            }
            con.close();

            Connection con2 = TheDAO.abrirConexionMySqlDB();
            Statement statement2 = con2.createStatement();
            ResultSet rschest = statement2.executeQuery("SELECT name FROM chest order by name");
            while (rschest.next()) {
                Chest.getItems().add(rschest.getString("name"));
            }
            con2.close();

            Connection con3 = TheDAO.abrirConexionMySqlDB();
            Statement statement3 = con3.createStatement();
            ResultSet rsgloves = statement3.executeQuery("SELECT name FROM gloves order by name");
            while (rsgloves.next()) {
                Hands.getItems().add(rsgloves.getString("name"));
            }
            con3.close();

            Connection con4 = TheDAO.abrirConexionMySqlDB();
            Statement statement4 = con4.createStatement();
            ResultSet rswaist = statement4.executeQuery("SELECT name FROM waist order by name");
            while (rswaist.next()) {
                Waist.getItems().add(rswaist.getString("name"));
            }
            con4.close();

            Connection con5 = TheDAO.abrirConexionMySqlDB();
            Statement statement5 = con5.createStatement();
            ResultSet rslegs = statement5.executeQuery("SELECT name FROM legs order by name");
            while (rslegs.next()) {
                Legs.getItems().add(rslegs.getString("name"));
            }
            con5.close();






        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     *Cuando seleccionas uan opcion se conecta a la base de datos y busca con el nombre de la armadura su id,
     * con esa id se conecta con una foreign key a otra tabla donde estan las skills
     * y obtiene los valores que son mostrados por el textView, si se repiten skills estas se suman.
     */
   @FXML
   private void comboActionHelm(ActionEvent event) {
       Helm.setDisable(true);
       String valor;
       String skill,level;
       boolean repe = false;
       String info ="";
       DAO TheDAO = new DAO("monsterhunterworld");
       try {
           Connection con = TheDAO.abrirConexionMySqlDB();

           valor = Helm.getValue().toString();

           String Query = " SELECT skill,skill_level FROM headgear_skills where headgear = (select id from headgear where name =  \"" +valor+  "\")";

           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(Query);





           if(rs.next()) {
               skill = rs.getString("skill");
               level = rs.getString("skill_level");
               for (int i = 0; i<shownSkills.size();i++) {
                   if (skill.equals(shownSkills.get(i))) {
                       int newLevel = levelSkills.get(i) + Integer.parseInt(level);
                       levelSkills.set(i,newLevel);
                       repe=true;
                   }

               }
               if (!repe) {
                   shownSkills.add(skill);
                   levelSkills.add(Integer.parseInt(level));
               }


               for (int i = 0; i<shownSkills.size();i++) {
                   info +=shownSkills.get(i) + " : +" + levelSkills.get(i) + "\n";
               }

               Texto.setText(info);
           }




            con.close();


           con.close();
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }




   }

    /**
     * evento que se ejecuta al selecionar un item en la comboBox de Chest, idéntico al resto solo cambian nombres de variables
     * @param event
     */
    @FXML
    private void comboActionChest(ActionEvent event) {


        Chest.setDisable(true);
        String valor;
        String skill,level;
        boolean repe = false;
        String info ="";
        DAO TheDAO = new DAO("monsterhunterworld");
        try {
            Connection con = TheDAO.abrirConexionMySqlDB();
            valor = Chest.getValue().toString();

            String Quary = " SELECT skill,skill_level FROM chest_skills where Chest = (select id from chest where name =  \"" +valor+  "\")";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Quary);
            if (rs.next()) {
                skill = rs.getString("skill");
                level = rs.getString("skill_level");
                for (int i = 0; i<shownSkills.size();i++) {
                    if (skill.equals(shownSkills.get(i))) {
                        int newLevel = levelSkills.get(i) + Integer.parseInt(level);
                        levelSkills.set(i,newLevel);
                        repe=true;
                    }

                }
                if (!repe) {
                    shownSkills.add(skill);
                    levelSkills.add(Integer.parseInt(level));
                }


                for (int i = 0; i<shownSkills.size();i++) {
                    info +=shownSkills.get(i) + " : +" + levelSkills.get(i) + "\n";
                }

                Texto.setText(info);
            }




            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    /**
     * evento que se ejecuta al selecionar un item de la comboBox de Hands, idéntico al resto solo cambian nombres de variables
     * @param event
     */
    @FXML
    private void comboActionGloves(ActionEvent event) {
        Hands.setDisable(true);
        String valor;
        String skill,level;
        boolean repe = false;
        String info ="";
        DAO TheDAO = new DAO("monsterhunterworld");
        try {
            Connection con = TheDAO.abrirConexionMySqlDB();
            Connection con2 = TheDAO.abrirConexionMySqlDB();
            valor = Hands.getValue().toString();

            String Query = " SELECT skill,skill_level FROM gloves_skills where gloves = (select id from gloves where name =  \"" +valor+  "\")";
            Statement stCheck = con2.createStatement();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);





                    if(rs.next()) {
                        skill = rs.getString("skill");
                        level = rs.getString("skill_level");
                        for (int i = 0; i<shownSkills.size();i++) {
                            if (skill.equals(shownSkills.get(i))) {
                                int newLevel = levelSkills.get(i) + Integer.parseInt(level);
                                levelSkills.set(i,newLevel);
                                repe=true;
                            }

                        }
                        if (!repe) {
                            shownSkills.add(skill);
                            levelSkills.add(Integer.parseInt(level));
                        }


                        for (int i = 0; i<shownSkills.size();i++) {
                            info +=shownSkills.get(i) + " : +" + levelSkills.get(i) + "\n";
                        }

                        Texto.setText(info);
                    }







            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * evento que se ejecuta al selecionar un item de la comboBox de Waist, idéntico al resto solo cambian los nombre de variables
     * @param event
     */
    @FXML
     private void comboActionWaist(ActionEvent event) {
        Waist.setDisable(true);
        String valor;
        String skill,level;
        boolean repe = false;
        String info ="";
        DAO TheDAO = new DAO("monsterhunterworld");
        try {
            Connection con = TheDAO.abrirConexionMySqlDB();
            valor = Waist.getValue().toString();

            String Query = " SELECT skill,skill_level FROM waist_skills where waist = (select id from waist where name =  \"" +valor+  "\")";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);

            if (rs.next()) {
                skill = rs.getString("skill");
                level = rs.getString("skill_level");
                for (int i = 0; i<shownSkills.size();i++) {
                    if (skill.equals(shownSkills.get(i))) {
                        int newLevel = levelSkills.get(i) + Integer.parseInt(level);
                        levelSkills.set(i,newLevel);
                        repe=true;
                    }

                }
                if (!repe) {
                    shownSkills.add(skill);
                    levelSkills.add(Integer.parseInt(level));
                }


                for (int i = 0; i<shownSkills.size();i++) {
                    info +=shownSkills.get(i) + " : +" + levelSkills.get(i) + "\n";
                }

                Texto.setText(info);
            }


            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    /**
     * evento que se ejecuta al selecionar un item de la comboBox de Legs, idéntico al resto solo cambian los arrays
     * @param event
     */
    @FXML
    private void comboActionLegs(ActionEvent event) {
        Legs.setDisable(true);
        String valor;
        String skill,level;
        boolean repe = false;
        String info ="";
        DAO TheDAO = new DAO("monsterhunterworld");
        try {
            Connection con = TheDAO.abrirConexionMySqlDB();
            valor = Legs.getValue().toString();

            String Query = " SELECT skill,skill_level FROM legs_skills where legs = (select id from legs where name = \"" +valor+  "\")";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            if(rs.next()) {
                skill = rs.getString("skill");
                level = rs.getString("skill_level");
                for (int i = 0; i<shownSkills.size();i++) {
                    if (skill.equals(shownSkills.get(i))) {
                        int newLevel = levelSkills.get(i) + Integer.parseInt(level);
                        levelSkills.set(i,newLevel);
                        repe=true;
                    }

                }
                if (!repe) {
                    shownSkills.add(skill);
                    levelSkills.add(Integer.parseInt(level));
                }


                for (int i = 0; i<shownSkills.size();i++) {
                    info +=shownSkills.get(i) + " : +" + levelSkills.get(i) + "\n";
                }

                Texto.setText(info);
            }


            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * metodo para reiniciar las combo box y las skills del textView
     * @param event
     */
    @FXML
    private void ActionClear(ActionEvent event) {
        Legs.setDisable(false);
        Helm.setDisable(false);
        Chest.setDisable(false);
        Waist.setDisable(false);
        Hands.setDisable(false);
        Texto.clear();
        shownSkills.clear();
        levelSkills.clear();
    }



}