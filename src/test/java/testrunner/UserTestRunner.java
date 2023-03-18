package testrunner;

import controller.User;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    User user;
    @Test(priority = 1, description = "Login with invalid data")
    public void doLoginWithInvalidData() throws ConfigurationException, IOException {
        user = new User();
        JsonPath jsonResponse = user.callLoginAPI("salmankhan@roadtocareer.net", "1234");

        String token = jsonResponse.get("token");
        String message = jsonResponse.get("message");

        Utils.setEnvVariable("token", token);

        System.out.println(token);
        System.out.println(message);

        Assert.assertTrue(message.contains("User not found"));
    }


    @Test(priority = 2, description = "Login with valid data")
    public void doLogin() throws ConfigurationException, IOException {
        user = new User();
        JsonPath jsonResponse = user.callLoginAPI("salman@roadtocareer.net", "1234");

        String token = jsonResponse.get("token");
        String message = jsonResponse.get("message");

        Utils.setEnvVariable("token", token);

        System.out.println(token);
        System.out.println(message);

        Assert.assertTrue(message.contains("Login successfully"));
    }

    @Test(priority = 3, description = "Creating customer with existing data")
    public void createUserByExistingData() throws ConfigurationException, IOException {
        user = new User();

        JsonPath jsonResponse = user.createUser("Salman Rahman","salman@roadtocareer.net","1234","01686606909","1246545","Customer");
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");

        Assert.assertTrue(message.contains("User already exists"));

    }


    @Test(priority = 4, description = "Creating customer with valid data")
    public void createUser() throws ConfigurationException, IOException {
        user = new User();
        Utils utils = new Utils();
        utils.generateRandomUser();
        JsonPath jsonResponse = user.createUser(utils.getName(),utils.getEmail(),"1234",utils.generatePhoneNumber(),"1234332233","Customer");
        System.out.println(jsonResponse.get().toString());
        Utils.setEnvVariable("user_id", jsonResponse.get("user.id").toString());
        Utils.setEnvVariable("user_name", jsonResponse.get("user.name"));
        Utils.setEnvVariable("user_email", jsonResponse.get("user.email"));
        Utils.setEnvVariable("user_phone_number", jsonResponse.get("user.phone_number"));

    }

    @Test(priority = 5, description = "Creating agent with valid data")
    public void createAgent() throws ConfigurationException, IOException {
        user = new User();
        Utils utils = new Utils();
        utils.generateRandomUser();
        JsonPath jsonResponse = user.createAgent(utils.getName(),utils.getEmail(),"1234",utils.generatePhoneNumber(),"1234332233","Agent");
        System.out.println(jsonResponse.get().toString());
        Utils.setEnvVariable("agent_id", jsonResponse.get("user.id").toString());
        Utils.setEnvVariable("agent_name", jsonResponse.get("user.name"));
        Utils.setEnvVariable("agent_email", jsonResponse.get("user.email"));
        Utils.setEnvVariable("agent_phone_number", jsonResponse.get("user.phone_number"));
    }


    @Test(priority = 6, description = "Try to search user by valid phone number ")
    public void getUserByValidPhoneNumber() throws ConfigurationException, IOException {
        user = new User();

        JsonPath jsonResponse = user.getUserByPhoneNumber(prop.getProperty("user_phone_number"));
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");

        Assert.assertTrue(message.contains("User found"));
    }

    @Test(priority = 7, description = "Try to search user by valid phone number ")
    public void getUserByInvalidPhoneNumber() throws ConfigurationException, IOException {
        user = new User();

        JsonPath jsonResponse = user.getUserByPhoneNumber(prop.getProperty("01621087902"));
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");

        Assert.assertTrue(message.contains("User not found"));
    }

}
