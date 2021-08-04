package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestWikipediaCzech {
    public static final String URL_BASE_CZECH = "https://cs.wikipedia.org/wiki/";
    WebDriver driver;
    WikipediaArticle article;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        article = new WikipediaArticle(driver, URL_BASE_CZECH);
    }

    @Test
    public void notAllRoadsLeadToPhilosophy() {
        System.out.println("I'm running test with \"Testování softwaru\" beginning article.");

        article.goToURL("Testov%C3%A1n%C3%AD_softwaru");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void allRoadsLeadToRoads() {
        System.out.println("I'm running test with \"Platón\" beginning article.");

        article.goToURL("Plat%C3%B3n");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void atLeastOneRoadLeadsToPhilosophy() {
        System.out.println("I'm running test with \"Filosof\" beginning article.");

        article.goToURL("Filosof");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
