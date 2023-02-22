import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class AllTest extends BaseTest {
    private final By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By NAVIGATION_BAR = By.xpath("//nav[@id='nav-website']");
    private final By LOGO_IMAGE = By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']");
    private final By SEARCH_FIELD = By.xpath("//div//form[@role='search']//input[@placeholder='Weather in your city']");
    private final By GUID_BUTTON = By.xpath("//div//a[@href='/guide']");
    private final By API_BUTTON = By.xpath("//div//li//a[@href='/api']");
    private final By DASHBOARD_BUTTON = By.xpath("//div//ul//li//a[text()='Dashboard']");
    private final By MARKETPLACE_BUTTON = By.xpath("//div//ul//li//a[text()='Marketplace']");
    private final By PRICING_BUTTON = By.xpath("//div[@id='desktop-menu']//ul//li//a[@href='/price']");
    private final By MAPS_BUTTON = By.xpath("//div[@id='desktop-menu']//ul//li//a[@href='/weathermap']");
    private final By OUR_INITIATIVES_BUTTON = By.xpath("//div[@id='desktop-menu']//ul//li//a[@href='/our-initiatives']");
    private final By PARTNERS_BUTTON = By.xpath("//div[@id='desktop-menu']//ul//li//a[@href='/examples']");
    private final By BLOG_BUTTON = By.xpath("//div[@id='desktop-menu']//ul//li//a[@href='https://openweather.co.uk/blog/category/weather']");
    private final By FOR_BUSINESS_BUTTON = By.xpath("//div[@id='desktop-menu']//ul//li//a[@href='https://openweather.co.uk']");
    private final By SING_IN_BUTTON = By.xpath("//div[@id='desktop-menu']//ul//li//a[@href='https://openweathermap.org/home/sign_in']");
    private final By SUPPORT_BUTTON = By.xpath("//div[@id='support-dropdown']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";
        
        openBaseURL();
        
        final String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);
        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);
        
        String actualCityCountryNames = getText(H2_CITY_COUNTRY_HEADER);
        Assert.assertEquals(actualCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testOpenWeatherStartPageOpened() {
            String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";
            String expectedResultUrl = "https://openweathermap.org/";

            openBaseURL();
            
            String actualResultTitle = getTitle();
            String actualResultUrl = getCurrentURL();
            
            Assert.assertEquals(actualResultTitle, expectedResultTitle);
            Assert.assertEquals(actualResultUrl, expectedResultUrl);
        }

        @Test
        public void testVerifyAllElementsOfNavigationBarAreVisible () {

            openBaseURL();

            Assert.assertTrue(isElementExists(NAVIGATION_BAR));
            Assert.assertTrue(isElementExists(LOGO_IMAGE));
            Assert.assertTrue(isElementExists(SEARCH_FIELD));
            Assert.assertTrue(isElementExists(GUID_BUTTON));
            Assert.assertTrue(isElementExists(API_BUTTON));
            Assert.assertTrue(isElementExists(DASHBOARD_BUTTON));
            Assert.assertTrue(isElementExists(MARKETPLACE_BUTTON));
            Assert.assertTrue(isElementExists(PRICING_BUTTON));
            Assert.assertTrue(isElementExists(MAPS_BUTTON));
            Assert.assertTrue(isElementExists(OUR_INITIATIVES_BUTTON));
            Assert.assertTrue(isElementExists(PARTNERS_BUTTON));
            Assert.assertTrue(isElementExists(BLOG_BUTTON));
            Assert.assertTrue(isElementExists(FOR_BUSINESS_BUTTON));
            Assert.assertTrue(isElementExists(SING_IN_BUTTON));
            Assert.assertTrue(isElementExists(SUPPORT_BUTTON));
        }
    }
}
