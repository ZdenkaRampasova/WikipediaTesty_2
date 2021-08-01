package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Wikipedia {
    public static final String URL_BASE = "https://cs.wikipedia.org/wiki/";
    public static final String PHILOSOPHY_URL_ENDING = "/Filosofie";
    public static final String FIRST_LINK_XPATH = "//div/p/a[@href]";
    WebDriver driver;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void notAllRoadsLeadToPhilosophy() {
        goToURL("Testov%C3%A1n%C3%AD_softwaru");

        clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        assertWeAreOnPhilosophySite();
    }

    @Test
    public void allRoadsLeadToRoads() {
        goToURL("Plat%C3%B3n");

        clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        assertWeAreOnPhilosophySite();
    }

    @Test
    public void atLeastOneRoadLeadsToPhilosophy() {
        goToURL("Filosof");

        clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        assertWeAreOnPhilosophySite();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    public void goToURL (String endOfURL) {
        driver.navigate().to(URL_BASE + endOfURL);
    }

    public void clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions() {
        int numberOfTransitions = 0;

        while (!areWeOnPhilosophySite()) {
            transitionToFirstLink();
            numberOfTransitions = ++numberOfTransitions;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Road to philosophy is " + numberOfTransitions + " click(s) long.");
    }

    public void assertWeAreOnPhilosophySite () {
        Assertions.assertTrue(driver.getCurrentUrl().endsWith(PHILOSOPHY_URL_ENDING));
    }

    public void transitionToFirstLink() {
        WebElement firstLink = driver.findElement(By.xpath(FIRST_LINK_XPATH));
        firstLink.click();
    }

    public boolean areWeOnPhilosophySite() {
        return driver.getCurrentUrl().endsWith(PHILOSOPHY_URL_ENDING);
    }
}
