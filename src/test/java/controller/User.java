package controller;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@Getter
@Setter
public class User extends Setup {
    public User() throws IOException {
        initConfig();
    }
    private String message;

    public JsonPath callLoginAPI(String email, String password) throws ConfigurationException {

            UserModel loginModel=new UserModel(email, password);
            RestAssured.baseURI = prop.getProperty("base_url");
            Response res =
                    given()
                            .contentType("application/json")
                            .body(loginModel)
                            .when().post("/user/login");
            return res.jsonPath();

    }
    public String getUserList() throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .when()
                        .get("/user/list")
                        .then()
                        .assertThat().statusCode(200).extract().response();
        JsonPath response = res.jsonPath();
        //return response.get("users[1].id").toString();
        //System.out.println(response.get("users["+pos+"].id").toString());
        //int phone_number= Integer.parseInt(response.get("users["+pos+"].phone_number").toString());

        String phone_number = response.getString("users[2].mobilenumber");
        return phone_number;
    }
    public JsonPath createUser(String name,String email,String password,String phone_number, String nid,String role) throws ConfigurationException {

        UserModel regModel=new UserModel(name, email,password,phone_number,nid,role);
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .body(regModel)
                        .when()
                        .post("/user/create");
        return res.jsonPath();
    }
    public JsonPath createAgent(String name,String email,String password,String phone_number, String nid,String role) throws ConfigurationException {
        Utils utils=new Utils();
        utils.generateRandomUser();
        UserModel regModel=new UserModel(name, email,password,phone_number,nid,role);
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .body(regModel)
                        .when()
                        .post("/user/create");

        return res.jsonPath();
    }
    public JsonPath getUserByPhoneNumber(String phone_number){
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .when()
                        .get("/user/search/Phonenumber/"+ phone_number);

        return res.jsonPath();
       
    }

}
