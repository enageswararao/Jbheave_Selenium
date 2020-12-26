package steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps extends Steps {

WebDriver driver;

    @BeforeStory
    public  void start(){

        System.out.println("start ...............  @BeforeStory................");

        WebDriverManager.chromedriver().setup();
          driver=new ChromeDriver();
          driver.manage().window().maximize();
          driver.get("https://www.spotify.com/in/");

    }
    @AfterStory
    public  void end(){
        driver.close();
        System.out.println("end ............... @AfterStory................");

    }
    private String fNameS;
    private byte[] byteARR;

    @Given("a file, $filename")
    public void setFileName(@Named("filename") String filename) {

        System.out.println("a file, $filename"+filename);
    }

    @Given("a file, a.log")
     public void givenAFileAlog() {
        System.out.println("a file");
    }

    @When("the caller loads the file as a byte array")
    public void loadFile() {
        System.out.println("the caller loads the file as a byte array");
    }

    @Then("the byte array that is returned contains the "
            + "correct number of bytes.")
    public void checkArrSize() {
        System.out.println("the byte array that is returned contains the ");
    }


}