package cz.czechitas.selenium;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WikipediaArticle {
    String URL_Base;
    public static final String PHILOSOPHY_URL_ENDING_CZECH = "/Filosofie";
    public static final String PHILOSOPHY_URL_ENDING_ENGLISH = "/Philosophy";
    public static final String FIRST_LINK_XPATH = "//div/p/a[@href]";

    WebDriver driver;

    public WikipediaArticle(WebDriver driver, String URL_Base) {
        this.driver = driver;
        this.URL_Base = URL_Base;
    }

    public void transitionToFirstLink() {
        WebElement firstLink = driver.findElement(By.xpath(FIRST_LINK_XPATH));
        firstLink.click();
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

    public void goToURL(String endOfURL) {
        driver.navigate().to(URL_Base + endOfURL);
    }

    public void assertWeAreOnPhilosophySite() {
        Assertions.assertTrue(driver.getCurrentUrl().endsWith(PHILOSOPHY_URL_ENDING_CZECH) || driver.getCurrentUrl().endsWith(PHILOSOPHY_URL_ENDING_ENGLISH));
    }

    public boolean areWeOnPhilosophySite() {
        return driver.getCurrentUrl().endsWith(PHILOSOPHY_URL_ENDING_CZECH) || driver.getCurrentUrl().endsWith(PHILOSOPHY_URL_ENDING_ENGLISH);
    }
}
