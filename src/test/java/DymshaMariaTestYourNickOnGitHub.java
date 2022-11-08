import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DymshaMariaTestYourNickOnGitHub {

//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
// и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testOpenAndClickToGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchButtonInMenu = driver.findElement(
                By.xpath("//li//a[text() = 'Guide']")
        );

        searchButtonInMenu.click();
        Thread.sleep(1000);

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
        driver.close();
    }


    //    TC_11_02
    //1.  Открыть базовую ссылку
    //2.  Нажать на единицы измерения Imperial: °F, mph
    //3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void testImperial() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTempSymbol = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement menuImperial = driver.findElement(
                By.xpath("//div[@class = 'option']/following-sibling::div")
        );
        menuImperial.click();
        Thread.sleep(2000);

        WebElement tempF = driver.findElement(
                By.xpath("//div[@class = 'current-temp']/span")
        );

        String tempInF = tempF.getText();
        String actualResult = tempInF.substring((tempInF.length() - 2));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
//      driver.close();

    }


//       TC_11_03
//1.     Открыть базовую ссылку
//2.     Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
// We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
// You can allow all cookies or manage them individually.”
//3.     Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testApproveTwoButtonInPanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "“We use cookies which are essential for the site to work. \n" +
                "// We also use non-essential cookies to help us improve our services. Any data collected is anonymised. \n" +
                "// You can allow all cookies or manage them individually.”";

        driver.get(url);
        Thread.sleep(3000);

        WebElement textElement = driver.findElement(
                By.className("stick-footer-panel__description")
        );
        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[text()='Allow all']")
        );
        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']")
        );

        Assert.assertEquals(buttonAllowAll.getText(), "Allow all");
        Assert.assertEquals(buttonManageCookies.getText(), "Manage cookies");
        Assert.assertEquals(textElement.getText(), expectedResult);

        driver.quit();
    }

//    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testSupportHaveThreeButton() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportDropdown = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        supportDropdown.click();
        Thread.sleep(5000);

        WebElement checkIfTextFAQIsPresent = driver.findElement(
                By.xpath("//ul//li//a[@href='/faq']")
        );
        String actualResultIfTextFAQIsPresent = checkIfTextFAQIsPresent.getText();
        System.out.println(actualResultIfTextFAQIsPresent);

        Assert.assertEquals(actualResultIfTextFAQIsPresent, expectedResultFAQ);

        WebElement checkIfTextHowToStartIsPresent = driver.findElement(
                By.xpath("//li//li//a[@href = '/appid']")
        );
        String actualResultIfTextHowToStartIsPresent = checkIfTextHowToStartIsPresent.getText();
        System.out.println(actualResultIfTextHowToStartIsPresent);

        Assert.assertEquals(actualResultIfTextHowToStartIsPresent, expectedResultHowToStart);

        WebElement checkIfTextAskAQuestionIsPresent = driver.findElement(
                By.xpath("//li//li//a[@href = 'https://home.openweathermap.org/questions']")
        );
        String actualResultIfTextAskAQuestionIsPresent = checkIfTextAskAQuestionIsPresent.getText();
        System.out.println(actualResultIfTextAskAQuestionIsPresent);

        Assert.assertEquals(actualResultIfTextAskAQuestionIsPresent, expectedResultAskAQuestion);

        driver.quit();
    }


//        TC_11_05
//        1. Открыть базовую ссылку
//        2. Нажать пункт меню Support → Ask a question
//        3. Заполнить поля Email, Subject, Message
//        4. Не подтвердив CAPTCHA, нажать кнопку Submit
//        5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testErrorPopsUP_WhenSubmitInSupportWithoutCaptcha() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String eMail = "tester@test.com";
        String message = "Test message";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement searchSupportMenu = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        Thread.sleep(2000);
        searchSupportMenu.click();

        WebElement searchAskQuestion = driver.findElement(
                By.xpath("//li//a[text()='Ask a question']")
        );
        Thread.sleep(2000);
        searchAskQuestion.click();

        String mainWindow = driver.getWindowHandle();
        for (String windowsHandle : driver.getWindowHandles()) {
            if (!mainWindow.contentEquals(windowsHandle)) {
                driver.switchTo().window(windowsHandle);
                break;
            }
        }

        WebElement searchEmailField = driver.findElement(
                By.xpath("//input[@id='question_form_email']")
        );
        Thread.sleep(2000);
        searchEmailField.click();
        searchEmailField.sendKeys(eMail);


        WebElement searchSubjectField = driver.findElement(
                By.xpath("//select[@id='question_form_subject']")
        );
        Thread.sleep(2000);
        searchSubjectField.click();

        WebElement searchSubjectFieldText = driver.findElement(
                By.xpath("//select[@id='question_form_subject']//option[7]")
        );
        Thread.sleep(2000);
        searchSubjectFieldText.click();
        Thread.sleep(2000);
        searchSubjectField.click();


        WebElement searchMessageField = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']")
        );
        Thread.sleep(2000);
        searchMessageField.click();
        searchMessageField.sendKeys(message);

        WebElement searchSubmitButton = driver.findElement(
                By.xpath("//div[@class='col-sm-8']//input[@type='submit']")
        );
        Thread.sleep(2000);
        searchSubmitButton.click();


        WebElement searchCaptchaMassage = driver.findElement(
                By.xpath("//div[@class='col-sm-8']//input[@type='submit']")
        );
        String actualResult = searchCaptchaMassage.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
//            driver.close();
    }

//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Test
    public void testConfirmErrorInEmailField() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String url = "https://openweathermap.org/";
        String subject = "Other";
        String message = "another test message";

        String expectedResult = "can't be blank";

        driver.get(url);

        Thread.sleep(2000);
        driver.manage().window().maximize();

        WebElement clickOnSupport = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        Thread.sleep(2000);
        clickOnSupport.click();


        String originalWindow = driver.getWindowHandle();
        Thread.sleep(2000);

        WebElement selectSubmenu_AskQuestion = driver.findElement(
                By.xpath("//li//a[text()='Ask a question']")
        );
        Thread.sleep(2000);
        selectSubmenu_AskQuestion.click();

        String mainWindow = driver.getWindowHandle();
        for (String windowsHandle : driver.getWindowHandles()) {
            if (!mainWindow.contentEquals(windowsHandle)) {
                driver.switchTo().window(windowsHandle);
                break;
            }
        }

        WebElement enterSubject = driver.findElement(
                By.xpath("//select[@id='question_form_subject']")
        );
        Thread.sleep(2000);
        enterSubject.click();
        enterSubject.sendKeys(subject);

        WebElement enterMessage = driver.findElement(
                By.xpath("//textarea[@id='question_form_message']")
        );
        Thread.sleep(2000);
        enterMessage.click();
        enterMessage.sendKeys(message);

        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(
                        By.xpath("//iframe[@title='reCAPTCHA']")
        ));

        WebElement enterCaptcha = driver.findElement(
                By.xpath("//div[@class='rc-anchor-center-container']")
        );
        enterCaptcha.click();
        Thread.sleep(7000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(
                By.xpath("//div[@class='col-sm-8']//input[@type='submit']")
        );
        Thread.sleep(2000);
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(
                By.xpath("//span[@class ='help-block']")
        );
        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

    @Test
    public void testChangingTemperatureValues() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://openweathermap.org/";
        String temp = "°C";

        driver.get(url);
        Thread.sleep(2000);

        WebElement clickOnImperial = driver.findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        clickOnImperial.click();
        Thread.sleep(2000);

        WebElement clickOnMetric = driver.findElement(
                By.xpath("//div[text()='Metric: °C, m/s']")
        );
        clickOnMetric.click();
        Thread.sleep(2000);

        String tempC = driver.findElement(
                By.xpath("//span[@class = 'heading']")
        ).getText();

        Boolean actualResult = tempC.contains(temp);
//        Boolean expectedResult = tempC.contains(temp);

        Assert.assertTrue(actualResult);

        driver.quit();
    }

//
//    TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void testCurrentUrl() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(2000);

        WebElement clickOnLogo = driver.findElement(
                By.xpath("//img[@src= '/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        clickOnLogo.click();
        Thread.sleep(2000);

        String expectedResult ="https://openweathermap.org/";
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

//    TC_11_09
//1.  Открыть базовую ссылку
//2.  В строке поиска в навигационной панели набрать “Rome”
//3.  Нажать клавишу Enter
//4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//5.  Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void testRome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://openweathermap.org/";
        String expectedResultCity = "Rome";
        String searchValue1 = "find";
        String searchValue2 = "Rome";

        driver.get(url);
        Thread.sleep(2000);

        WebElement clickSearchCity = driver.findElement(
                By.xpath("//input[@placeholder= 'Search city']")
        );
        clickSearchCity.click();
        clickSearchCity.sendKeys(expectedResultCity);
        clickSearchCity.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        String strUrl = driver.getCurrentUrl();

        Boolean actualResultStrUrl;
        if (strUrl.contains(searchValue1) && strUrl.contains(searchValue2)) {
            actualResultStrUrl = true;
        }else{
            actualResultStrUrl = false;
        }

        Boolean expectedResult = strUrl.contains(searchValue1) && strUrl.contains(searchValue2);
        Assert.assertEquals(actualResultStrUrl, expectedResult);

        String actualResultSearchBar = driver.findElement(
                By.xpath("//input[@class]")
        ).getAttribute("value");

        Assert.assertEquals(actualResultSearchBar,expectedResultCity);

        driver.quit();

        }

//    TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок

    @Test
    public void testMenuApi() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        Thread.sleep(2000);

        WebElement clickMenuApi = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//ul//li//a[text() = 'API']")
        );
        clickMenuApi.click();

        int actualResultOrangeButton = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        Assert.assertEquals(actualResultOrangeButton, expectedResult);

        driver.quit();
    }






    }


