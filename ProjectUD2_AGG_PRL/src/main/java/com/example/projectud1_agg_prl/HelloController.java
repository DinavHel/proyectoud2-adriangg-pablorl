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
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private JSONArray Helmets = new JSONArray();
    private JSONArray Chestplate = new JSONArray();
    private JSONArray Gauntlets = new JSONArray();
    private JSONArray Waists= new JSONArray();
    private JSONArray Leggins = new JSONArray();
    private HashMap<String,Integer> hHelmet = new HashMap<>();
    private HashMap<String,Integer> hChest = new HashMap<>();
    private HashMap<String,Integer> hGloves = new HashMap<>();
    private HashMap<String,Integer> hWaist = new HashMap<>();
    private HashMap<String,Integer> hLegs = new HashMap<>();

    private static ArrayList<Skill> shownSkills = new ArrayList<>();


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
        File Armaduras = new File(Camino);
        //variables que se utilizan para la creacion de los hasmaps
        int countw = 0;
        int countg = 0;
        int counth = 0;
        int countc = 0;
        int countl = 0;

        //busca si existe un archivo json para las armaduras//
        if (!Armaduras.exists()) {
            try {
                Armaduras.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            //creas una conexion y con el metodo setRequestMethod delimitas el tipo de consulta
            URL ur = new URL("https://mhw-db.com/armor");
            HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);

            } else {
                //creamos un scanner para leer los datos de la api y con un writer escribimos dichos datos en un archivo json//
                Scanner sc = new Scanner(ur.openStream());
                try (var file = Files.newBufferedWriter(Path.of(Camino))) {
                    while (sc.hasNext()) {
                        file.append(sc.nextLine());
                    }
                } catch (IOException e) {
                }
                //cerramos scanner y cremos un JSONParser , esto es lo que transforma la infomacion en objetos json y jsonArraylists//
                sc.close();

                JSONParser parseJ = new JSONParser();
                JSONArray dataObject = new JSONArray();
                try (var read = Files.newBufferedReader(Path.of(Camino))) {
                    dataObject = (JSONArray) parseJ.parse(read);
                } catch (IOException e) {
                    throw new RuntimeException();
                }

                /**
                 * recorro el json ,cada objeto lo guardo en un JSONObject consigue el typo con el get y lo comparo para saber a que parte del cuerpo pertenece,
                 *   guardo estas piezas de armadura en un array especifico dependiendo del tipo despues de esto utilizo
                 *   un hasmap para vincular el nombre de la pieza con la posicion en el array es decir el index
                 */

                for (int i = 0; i < dataObject.size(); i++) {
                    JSONObject armors = (JSONObject) dataObject.get(i);
                    if (armors.get("type").equals("head")) {

                        Helm.getItems().add(armors.get("name"));
                        Helmets.add(dataObject.get(i));
                        hHelmet.put((String) armors.get("name"),counth);
                        counth+=1;
                    } else if (armors.get("type").equals("chest")) {

                        Chest.getItems().add(armors.get("name"));
                        Chestplate.add(dataObject.get(i));
                        hChest.put((String) armors.get("name"),countc);
                        countc+=1;
                    } else if (armors.get("type").equals("gloves")) {

                        Hands.getItems().add(armors.get("name"));
                        Gauntlets.add(dataObject.get(i));
                        hGloves.put((String) armors.get("name"),countg);
                        countg++;
                    } else if (armors.get("type").equals("waist")) {

                        Waist.getItems().add(armors.get("name"));
                        Waists.add(dataObject.get(i));
                        hWaist.put((String) armors.get("name"),countw);
                        countw++;
                    } else if (armors.get("type").equals("legs")) {

                        Legs.getItems().add(armors.get("name"));
                        Leggins.add(dataObject.get(i));
                        hLegs.put((String) armors.get("name"),countl);
                        countl++;
                    }


                }

                dataObject.clear();


            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * cuando selecionas unha opcion en el comboBox se ejecuta uno de estos metodos dependiendo del combobox,
     * obtienen mediante el value del item del comboBox el numero del indice de un array de JSON que utiliza para consigir los datos de las skills de la armadura
     * luego a mano transformamos los datos en un objeto de java que hemos creado y
     * despues estos objetos java se guardan en un array stático que  se recorre y se  escriben sus parámetros en la textArea
     * @param event
     */
   @FXML
   private void comboActionHelm(ActionEvent event) {
       boolean repe = false;
       String info = "";

        Helm.setDisable(true);
       int index = hHelmet.get(Helm.getValue());
       JSONObject  selectedArmor = (JSONObject) Helmets.get(index);
       JSONArray sk = (JSONArray) selectedArmor.get("skills");

       if (!sk.isEmpty()) {
           for (int i = 0; i<sk.size();i++) {
               JSONObject AC = (JSONObject) sk.get(i);
               for (int l = 0;l<shownSkills.size();l++) {
                   if (AC.get("skillName").equals(shownSkills.get(l).getName())) {
                       int newlevel = (int) AC.get("level") + shownSkills.get(l).getLevel();
                       shownSkills.get(l).setLevel(newlevel);
                       repe = true;
                   }
               }
               if (!repe) {
                   Skill x = new Skill(AC.get("skillName").toString(),Integer.parseInt(AC.get("level").toString()),Integer.parseInt(AC.get("id").toString()));
                   shownSkills.add(x);
               }
               repe=false;
           }
       }

       for (int l = 0;l<shownSkills.size();l++) {
           info += shownSkills.get(l).getName() + " : + " + shownSkills.get(l).getLevel() + "\n";
       }
       Texto.setText(info );




   }

    /**
     * evento que se ejecuta al selecionar un item en la comboBox de Chest, idéntico al resto solo cambian los arrays
     * @param event
     */
    @FXML
    private void comboActionChest(ActionEvent event) {
        boolean repe = false;
        String info = "";

        Chest.setDisable(true);
        int index = hChest.get(Chest.getValue().toString());
        JSONObject selectedArmor = (JSONObject) Chestplate.get(index);
        JSONArray sk = (JSONArray) selectedArmor.get("skills");

        if (!sk.isEmpty()) {
            for (int i = 0; i<sk.size();i++) {
                JSONObject AC = (JSONObject) sk.get(i);
                for (int l = 0;l<shownSkills.size();l++) {
                    if (AC.get("skillName").equals(shownSkills.get(l).getName())) {
                        int newlevel = (int) AC.get("level") + shownSkills.get(l).getLevel();
                        shownSkills.get(l).setLevel(newlevel);
                        repe = true;
                    }
                }
                if (!repe) {
                    Skill x = new Skill(AC.get("skillName").toString(),Integer.parseInt(AC.get("level").toString()),Integer.parseInt(AC.get("id").toString()));
                    shownSkills.add(x);
                }
                repe=false;
            }
        }

        for (int l = 0;l<shownSkills.size();l++) {
            info += shownSkills.get(l).getName() + " : + " + shownSkills.get(l).getLevel() + "\n";
        }

        Texto.setText(info );




    }

    /**
     * evento que se ejecuta al selecionar un item de la comboBox de Hands, idéntico al resto solo cambian los arrays
     * @param event
     */
    @FXML
    private void comboActionGloves(ActionEvent event) {
        boolean repe = false;
        String info = "";
        Hands.setDisable(true);
        int index = hGloves.get(Hands.getValue());
        JSONObject  selectedArmor = (JSONObject) Gauntlets.get(index);
        JSONArray sk = (JSONArray) selectedArmor.get("skills");

        if (!sk.isEmpty()) {
            for (int i = 0; i<sk.size();i++) {
                JSONObject AC = (JSONObject) sk.get(i);
                for (int l = 0;l<shownSkills.size();l++) {
                    if (AC.get("skillName").equals(shownSkills.get(l).getName())) {
                        int newlevel = (int) AC.get("level") + shownSkills.get(l).getLevel();
                        shownSkills.get(l).setLevel(newlevel);
                        repe = true;
                    }
                }
                if (!repe) {
                    Skill x = new Skill(AC.get("skillName").toString(),Integer.parseInt(AC.get("level").toString()),Integer.parseInt(AC.get("id").toString()));
                    shownSkills.add(x);
                }
                repe=false;
            }
        }

        for (int l = 0;l<shownSkills.size();l++) {
            info += shownSkills.get(l).getName() + " : + " + shownSkills.get(l).getLevel() + "\n";
        }

        Texto.setText(info );



    }

    /**
     * evento que se ejecuta al selecionar un item de la comboBox de Waist, idéntico al resto solo cambian los arrays
     * @param event
     */
    @FXML
     private void comboActionWaist(ActionEvent event) {
        boolean repe = false;
        String info = "";

        Waist.setDisable(true);
        int index = hWaist.get(Waist.getValue());
        JSONObject  selectedArmor = (JSONObject) Waists.get(index);
        JSONArray sk = (JSONArray) selectedArmor.get("skills");

            if (!sk.isEmpty()) {
                for (int i = 0; i<sk.size();i++) {
                    JSONObject AC = (JSONObject) sk.get(i);
                    for (int l = 0;l<shownSkills.size();l++) {
                        if (AC.get("skillName").equals(shownSkills.get(l).getName())) {
                            int newlevel = (int) AC.get("level") + shownSkills.get(l).getLevel();
                            shownSkills.get(l).setLevel(newlevel);
                            repe = true;
                        }
                    }
                    if (!repe) {
                        Skill x = new Skill(AC.get("skillName").toString(),Integer.parseInt(AC.get("level").toString()),Integer.parseInt(AC.get("id").toString()));
                        shownSkills.add(x);
                    }
                    repe=false;
                }
            }

            for (int l = 0;l<shownSkills.size();l++) {
                info += shownSkills.get(l).getName() + " : + " + shownSkills.get(l).getLevel() + "\n";
            }

            Texto.setText(info );



    }

    /**
     * evento que se ejecuta al selecionar un item de la comboBox de Legs, idéntico al resto solo cambian los arrays
     * @param event
     */
    @FXML
    private void comboActionLegs(ActionEvent event) {
        boolean repe = false;
        String info = "";
        Legs.setDisable(true);
        int index = hLegs.get(Legs.getValue());
        JSONObject  selectedArmor = (JSONObject) Leggins.get(index);
        JSONArray sk = (JSONArray) selectedArmor.get("skills");

            if (!sk.isEmpty()) {
                for (int i = 0; i<sk.size();i++) {
                    JSONObject AC = (JSONObject) sk.get(i);
                    for (int l = 0;l<shownSkills.size();l++) {
                        if (AC.get("skillName").equals(shownSkills.get(l).getName())) {
                            int newlevel = (int) AC.get("level") + shownSkills.get(l).getLevel();
                            shownSkills.get(l).setLevel(newlevel);
                            repe = true;
                        }
                    }
                    if (!repe) {
                        Skill x = new Skill(AC.get("skillName").toString(),Integer.parseInt(AC.get("level").toString()),Integer.parseInt(AC.get("id").toString()));
                        shownSkills.add(x);
                    }
                    repe=false;
                }
            }

            for (int l = 0;l<shownSkills.size();l++) {
                info += shownSkills.get(l).getName() + " : + " + shownSkills.get(l).getLevel() + "\n";
            }

            Texto.setText(info);



    }
    @FXML
    private void ActionClear(ActionEvent event) {
        Legs.setDisable(false);
        Helm.setDisable(false);
        Chest.setDisable(false);
        Waist.setDisable(false);
        Hands.setDisable(false);
        Texto.clear();
        shownSkills.clear();
    }



}