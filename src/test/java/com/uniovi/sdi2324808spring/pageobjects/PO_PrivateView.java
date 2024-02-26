package com.uniovi.sdi2324808spring.pageobjects;

import com.uniovi.sdi2324808spring.util.SeleniumUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_PrivateView extends PO_NavView {
    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep)
    {
//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);
//Seleccionamos el alumnos userOrder
        new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
//Rellenemos el campo de descripción
        WebElement description = driver.findElement(By.name("description"));
        description.clear();
        description.sendKeys(descriptionp);
        WebElement score = driver.findElement(By.name("score"));
        score.click();
        score.clear();
        score.sendKeys(scorep);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
    static public void checkNumberOfMarkRows(WebDriver driver, int numberOfRows){

        List<WebElement> marksList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(numberOfRows, marksList.size());
    }
    static public void doDeleteMark(WebDriver driver, String markName){
        List<WebElement> elements = PO_View.checkElementBy(driver,
                "free",
                "//td[contains(text(), '"+markName+"')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
        elements.get(0).click();
    }
    static public void  checkMarkExists(WebDriver driver, String checkText){
        List<WebElement> elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());
    }
    public static void doAddMark(WebDriver driver, int userOrder, String checkText, String score){
        //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
        PO_NavView.goToNavPage(driver, "mark/add");
        //Ahora vamos a rellenar la nota con mas de 20 caracteres. //option[contains(@value, '4')]

        PO_PrivateView.fillFormAddMark(driver, userOrder, checkText, score);
        SeleniumUtils.goToPage(driver,4);
    }
    static public void goToDetails(WebDriver driver, String checkText){

        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
}
