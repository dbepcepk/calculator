package acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private final String server = System.getProperty("calculator.url");
    private final RestTemplate restTemplate = new RestTemplate();
    private String a;
    private String b;
    private String result;

    @Given("^I have two numbers: (.*) and (.*)$")
    public void iHaveTwoNumbers(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @When("^the calculator sums them$")
    public void theCalculatorSumsThem() {
        String url = String.format("%s/sum?a=%s&b=%s", server, a, b);
        result = restTemplate.getForObject(url, String.class);
    }

    @Then("^I receive (.*) as a result$")
    public void iReceiveAsAResult(String expectedResult) {
        assertEquals(expectedResult, result);
    }
}
