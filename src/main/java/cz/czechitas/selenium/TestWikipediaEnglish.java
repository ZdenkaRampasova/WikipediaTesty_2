package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestWikipediaEnglish {
    public static final String URL_BASE_ENGLISH = "https://en.wikipedia.org/wiki/";
    WebDriver driver;
    WikipediaArticle article;


    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        article = new WikipediaArticle(driver, URL_BASE_ENGLISH);
    }

    @Test
    public void allRoadsLeadToPhilosophy1() {
        System.out.println("I'm running test with \"Software testing\" beginning article.");

        article.goToURL("Software_testing");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void allRoadsLeadToPhilosophy2() {
        System.out.println("I'm running test with \"Plato\" beginning article.");

        article.goToURL("Plato");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void allRoadsLeadToPhilosophy3() {
        System.out.println("I'm running test with \"Java\" beginning article.");

        article.goToURL("Java");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void allRoadsLeadToPhilosophy4() {
        System.out.println("I'm running test with \"Owl\" beginning article.");

        article.goToURL("Owl");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @Test
    public void doesRandomRoadLeadToPhilosophy() {
        System.out.println("I'm running test with random english beginning article.");

        article.goToURL("Special:Random");

        article.clickOnFirstLinkUntilPhilosophyAndPrintNumberOfTransitions();

        article.assertWeAreOnPhilosophySite();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
