package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestWikipedia {
    WebDriver driver;
    WikipediaArticle article;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        article = new WikipediaArticle(driver);
    }

    @Test
    public void notAllRoadsLeadToPhilosophy() {
        article.goToURL("Testov%C3%A1n%C3%AD_softwaru");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void allRoadsLeadToRoads() {
        article.goToURL("Plat%C3%B3n");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void atLeastOneRoadLeadsToPhilosophy() {
        article.goToURL("Filosof");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
