package testrunner;

import controller.Transaction;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;


import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class TransactionTestRunner extends Setup {
    Transaction transaction;
    @Test(priority=1, description = "Deposit money from customer to Agent by invalid data." )
    public void depositToAgentByInValidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.depositToAgent("SYSTEM","01621805250",5000);
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Account does not exist"));

    }

   @Test(priority=2, description = "Deposit money from customer to Agent by valid data." )
    public void depositToAgentByValidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String agentPhoneNumber = prop.getProperty("agent_phone_number");
        JsonPath jsonResponse = transaction.depositToAgent("SYSTEM",agentPhoneNumber,5000);
        System.out.println(jsonResponse.get().toString());

        String trnxId = jsonResponse.get("trnxId");
        Utils.setEnvVariable("trnxId",trnxId);

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Deposit successful"));

    }
    @Test(priority=3, description = "Deposit money from agent to customer by invalid data.")
    public void depositToCustomerByInvalidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String agentPhoneNumber = prop.getProperty("agent_phone_number");

        JsonPath jsonResponse = transaction.depositToAgent(agentPhoneNumber,"01621805250",2000);
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Account does not exist"));
    }
    @Test(priority=4, description = "Deposit money from agent to customer by valid data.")
    public void depositToCustomerByValidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String agentPhoneNumber = prop.getProperty("agent_phone_number");
        String customerPhoneNumber = prop.getProperty("user_phone_number");
        JsonPath jsonResponse = transaction.depositToAgent(agentPhoneNumber,customerPhoneNumber,2000);
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Deposit successful"));
    }
    @Test(priority = 5, description = "Check the balance of customer by invalid data.")
    public void checkBalanceOfCustomerByInvalidData() throws IOException, ConfigurationException {
        transaction = new Transaction();

        JsonPath jsonResponse = transaction.checkBalanceOfCustomer("01621805250");
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("User not found"));

    }
    @Test(priority = 6, description = "Check the balance of customer by valid data.")
    public void checkBalanceOfCustomerByValidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String customerPhoneNumber = prop.getProperty("user_phone_number");
        JsonPath jsonResponse = transaction.checkBalanceOfCustomer(customerPhoneNumber);
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("User balance"));

    }
    @Test(priority = 7, description = "Search transaction by trnxId with Invalid data.")
    public void searchTransactionByInvalidTrnxId() throws IOException, ConfigurationException {
        transaction = new Transaction();

        JsonPath jsonResponse = transaction.searchTransaction("ajkfsjehf");
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Transaction not found"));

    }
    @Test(priority = 8, description = "Search transaction by trnxId with valid data.")
    public void searchTransactionByValidTrnxId() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String Transaction_id = prop.getProperty("trnxId");
        JsonPath jsonResponse = transaction.searchTransaction(Transaction_id);
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Transaction list"));

    }
    @Test(priority=9, description = "Money withdraw from customer to agent by invalid data.")
    public void withdrawByCustomerWithInvalidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String customerPhoneNumber = prop.getProperty("user_phone_number");
        JsonPath jsonResponse = transaction.withdrawByCustomer(customerPhoneNumber,"01621805250",1000);
        System.out.println(jsonResponse.get().toString());


        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Account does not exist"));
    }
    @Test(priority=10, description = "Money withdraw from customer to agent by valid data.")
    public void withdrawByCustomerWithValidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String agentPhoneNumber = prop.getProperty("agent_phone_number");
        String customerPhoneNumber = prop.getProperty("user_phone_number");
        JsonPath jsonResponse = transaction.withdrawByCustomer(customerPhoneNumber,agentPhoneNumber,1000);
        System.out.println(jsonResponse.get().toString());

        int currentBalance = jsonResponse.get("currentBalance");
        int expectedBalance = jsonResponse.get("currentBalance");
        Assert.assertEquals(expectedBalance,currentBalance);

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Withdraw successful"));
    }
    @Test(priority=11, description = "Send money form customer to another customer by invalid data")
    public void sendMoneyByCustomerWithInvalidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String customerPhoneNumber = prop.getProperty("user_phone_number");
        JsonPath jsonResponse = transaction.sendMoneyByCustomer(customerPhoneNumber,"01686690911",500);
        System.out.println(jsonResponse.get().toString());

        /* int currentBalance = jsonResponse.get("currentBalance");
        int expectedBalance = jsonResponse.get("currentBalance");
        Assert.assertEquals(expectedBalance,currentBalance);
        */
        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("From/To Account does not exist"));
    }
    @Test(priority=12, description = "Send money form customer to another customer by valid data")
    public void sendMoneyByCustomerWithValidDta() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String customerPhoneNumber = prop.getProperty("user_phone_number");
        JsonPath jsonResponse = transaction.sendMoneyByCustomer(customerPhoneNumber,"01505211199",500);
        System.out.println(jsonResponse.get().toString());

        /* int currentBalance = jsonResponse.get("currentBalance");
        int expectedBalance = jsonResponse.get("currentBalance");
        Assert.assertEquals(expectedBalance,currentBalance);
        */
        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Send money successful"));
    }
    @Test(priority = 13, description = "Check statement of customer by invalid mobile number")
    public void checkStatementOfCustomerBYInvalidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkStatementOfCustomer("01621805250");
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("User not found"));

    }
    @Test(priority = 14, description = "Check statement of customer by valid mobile number")
    public void checkStatementOfCustomerWithValidData() throws IOException, ConfigurationException {
        transaction = new Transaction();
        String customerPhoneNumber = prop.getProperty("user_phone_number");
        JsonPath jsonResponse = transaction.checkStatementOfCustomer(customerPhoneNumber);
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        Assert.assertTrue(message.contains("Transaction list"));

    }

}
