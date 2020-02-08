import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

        /*
        POP için main class'in icinde main fonksiyonlar tanimlanir(click, sendtext vb.)
        ve bunlara ek olarak diger siniflardan extend ederek testscript'leri yazacagiz.
        test kisminda ise main'de yazdigimiz classlarin instancesini alarak test scope'unda calistiracagiz.
         */


public class baseTest {

    private WebDriver driver = new ChromeDriver();
    private By girisYap = By.linkText("Giriş Yap");
    private By userName = By.xpath("//input[@id='email']");
    private By password = By.xpath("//input[@id='password]");
    private By login = By.id("@loginButton");
    private By search = By.id("@searchData");
    private By quantity = By.id("@quantity");
    private By delete = By.className("removeProd svgIcon svgIcon_trash");

    @Test
    void titleControl(){
        driver.get("https://www.n11.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        System.out.println(driver.getTitle());
        driver.findElement(girisYap).click();
        driver.findElement(userName).sendKeys("kahraman_ertan@outlook.com");
        driver.findElement(password).sendKeys("ertn1643");
        driver.findElement(login).click();
        driver.findElement(search).sendKeys("bilgisayar");
        driver.findElement(By.className("searchBtn")).click();
        WebElement element = driver.findElement(By.className("pagination"));
        List<WebElement> paginationBtn = element.findElements(By.tagName("li"));
        for (int i=0; i<paginationBtn.size(); i++){
            String paginationTxt = paginationBtn.get(i).getText();
            if (paginationTxt.getText().equals("2")){
                paginationTxt.click();
            }
        }

        driver.findElement(By.className("clearfix")).findElements(By.tagName("li")).get(2);
        driver.findElement(By.className("btn btnGrey btnAddBasket")).click();
        /*
                <ins content="5399.00">5.399,00 <span content="TRY">TL</span></ins> adresinde ki fiyat
                string degiskenine atanir ve asagidaki kosullardan sepete gidilir
                sepete eklediginde bekleme yaparsa xPath: //*[@id="header"]/div/div/div[2]/div[2]/div[4]/a/i
                direkt sepete giderse zPath: //*[@id="header"]/div/div/div[2]/div[2]/div[4]/div/div/div[2]/a

                //*[@id="newCheckout"]/div/div[1]/div[2]/div[1]/section/table[2]/tbody/tr/td[3]/div[2]/div/span ' de ki
                fiyatla karsilastirilir ve ayni fiyat teyit edildiginde switch window yada backspace islemi yapilarak
                urun sayfasina geri donulur.
         */
        driver.findElement(quantity).sendKeys("2");
        /*
        daha onceden sepeteGit adinda tanimladigimiz fonksiyonu kullanarak sepete gidilir
         */
        driver.findElement(delete).click();

        driver.quit();
    }

}
