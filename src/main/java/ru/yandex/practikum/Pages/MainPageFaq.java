package ru.yandex.practikum.Pages;

import org.openqa.selenium.*;



public class MainPageFaq {

    private WebDriver driver;

    // локатор блока с вопросами/ответами
    private By faqListBox = By.xpath(".//div[@class= 'accordion']");

    // универсальный локатор для поля вопроса
    public String faqListQuestionElement = "accordion__heading-%s";

    // универсальный локатор для поля с ответом
    public String faqListAnswerElement = "accordion__panel-%s";



    public MainPageFaq(WebDriver driver) {
        this.driver = driver;
    }
    //метод скрола к блоку с вопросами
    public void scrollToFaqSection() {
        WebElement faqSection = driver.findElement(faqListBox);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", faqSection);
    }
    //метод клика по полю с вопросом
    public void clickOnElementQuestion(String questionListNumber) {
        driver.findElement(By.id(String.format(faqListQuestionElement, questionListNumber))).click();
        driver.findElement(By.id(String.format(faqListQuestionElement, questionListNumber))).isDisplayed();
    }
    //метод, возвращающий текст поля ответа
    public String getAnswerText(String answerText) {
        return driver.findElement(By.id(String.format(faqListAnswerElement, answerText))).getText();
    }




}
