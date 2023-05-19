package StepDefinition;

import com.google.gson.Gson;
import core.ApiCall;
import core.FileHandleHelper;
import core.HeaderFormatHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import repository.remoteRepo.requestRepo.EmpRegPostReqModel;
import repository.remoteRepo.responseRepo.EmpRegPostResponseModel;

import static core.CoreConstrainHelper.base_url;
import static core.FilePathHelper.postApiPath;

public class EmpRequestStepdefs {

    private Gson gson = new Gson();

    private String requestModel;

    Response postApiResponse;

    EmpRegPostReqModel empRegPostReqModel;

    EmpRegPostResponseModel empRegPostResponseModel;

    String url;


    @Given("employee has the api {string}")
    public void employeeHasTheApiPath(String path) {

        url = base_url + path;
        
    }

    @When("employee hit {string} and {string}")
    public void employeeHitNameAndJob(String name, String job) {
        JSONObject requestBody = new FileHandleHelper().readJsonFile(postApiPath);
        empRegPostReqModel = new Gson().fromJson(requestBody.toJSONString(),EmpRegPostReqModel.class);
        empRegPostReqModel.setJob(job);
        empRegPostReqModel.setName(name);
        requestModel = gson.toJson(empRegPostReqModel);

    }

    @And("call the api with body")
    public void callTheApiWithBody() {
        postApiResponse = ApiCall.postCall(HeaderFormatHelper.commonHeaders(),requestModel,url);
        System.out.println(postApiResponse.body().asString());
    }


    @Then("it will return created date data")
    public void itWillReturnCreatedDateData() {
        EmpRegPostResponseModel empRegPostResponseModel = gson.fromJson(postApiResponse.getBody().asString(), EmpRegPostResponseModel.class);
        System.out.println(empRegPostResponseModel.getJob());
        System.out.println(empRegPostResponseModel.getName());

    }
}
