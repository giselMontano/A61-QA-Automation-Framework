import io.cucumber.java.After;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


@CucumberOptions(features ={"src/test/resources/features"})
//IMPORTANT TO COPY THE NAME OF THE CLASS AND ADD IT TO THE TEST NG FILE THAT WE HAVE BEEN USING
public class CucumberRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass (alwaysRun = true)
    public  void setUpCucumber(){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    //this is the part that will allow us to provide data into ihe framework from Feature File
    @DataProvider
    public Object [][] features(){
        return testNGCucumberRunner.provideScenarios();
    }
    @AfterClass (alwaysRun =true)
    public void teaDownClass(){
        testNGCucumberRunner.finish();
    }

}
