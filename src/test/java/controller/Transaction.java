package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.TransactionModel;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import setup.Setup;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Transaction extends Setup {
    public Transaction() throws IOException {
        initConfig();
    }

    public JsonPath depositToAgent(String from_account, String to_account,int amount  ) throws ConfigurationException {

        TransactionModel deposit =new TransactionModel(from_account,to_account,amount);
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .body(deposit)
                        .when().post("/transaction/deposit");
        return res.jsonPath();

    }
    public JsonPath depositToCustomer(String from_account, String to_account,int amount  ) throws ConfigurationException {

        TransactionModel deposit =new TransactionModel(from_account,to_account,amount);
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .body(deposit)
                        .when().post("/transaction/deposit");
        return res.jsonPath();

    }
    public JsonPath checkBalanceOfCustomer(String customer_phone_number ) throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .when().get("/transaction/balance/"+ customer_phone_number);

        return res.jsonPath();

    }
    public JsonPath searchTransaction(String trnxId ) throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .when().get("/transaction/search/"+ trnxId);

        return res.jsonPath();

    }
    public JsonPath withdrawByCustomer(String from_account, String to_account,int amount  ) throws ConfigurationException {

        TransactionModel deposit =new TransactionModel(from_account,to_account,amount);
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .body(deposit)
                        .when().post("/transaction/withdraw");
        return res.jsonPath();

    }
    public JsonPath sendMoneyByCustomer(String from_account, String to_account,int amount  ) throws ConfigurationException {

        TransactionModel deposit =new TransactionModel(from_account,to_account,amount);
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .body(deposit)
                        .when().post("/transaction/sendmoney");
        return res.jsonPath();

    }
    public JsonPath checkStatementOfCustomer(String mobile_number ) throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("base_url");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                        .when().get("/transaction/statement/"+ mobile_number);

        return res.jsonPath();

    }

}
