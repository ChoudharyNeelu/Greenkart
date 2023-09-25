package greenkart;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class GreenKartBasic {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/user/Documents/Neelu/selenium_required_files/chromedriver.exe");
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		// products to be added in the cart
		String[] productsRequired = { "Apple", "Beans", "Cucumber" };
		productsRequired(driver, productsRequired);

		// clicking on the add to cart symbol
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		// now clicking on the Proceed to checkout cta
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		// entering the promocode
		driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
		// clicking on apply cta
		driver.findElement(By.xpath("//button[contains(text(),'Apply')]")).click();

		// now clicking on the text place order
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();

		// selecting country from dropdown

		Select dropdown = new Select(driver.findElement(By.tagName("select")));
		dropdown.selectByValue("India");

		// clicking on the checkbox to agree with conditions
		driver.findElement(By.cssSelector("input.chkAgree")).click();

		// clicking on the text proceed
		driver.findElement(By.xpath("//button[contains(text(),'Proceed')]")).click();

	}

	public static void productsRequired(WebDriver driver, String[] productsRequired) {
		// storing all elements listed on the page with class product-name into the list
		List<WebElement> allProductsListed = driver.findElements(By.cssSelector("h4.product-name"));
		int j = 0;

		// looping all elements one by one
		for (int i = 0; i < allProductsListed.size(); i++) {
			// splitting the element name into two parts based on - example Cauliflower - 1
			// Kg
			String[] productName = allProductsListed.get(i).getText().split("-");
			// removing the whitespace added to cauliflower after splitting
			String prodName = productName[0].trim();
			// converting the above required products list as an arraylist
			List productsRequiredList = Arrays.asList(productsRequired);
			// checking whether required products are present in the mentioned page products
			// list
			if (productsRequiredList.contains(prodName)) {
				j++;
				// clicking on the Add to cart text
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				// checking here that after the addition of reuired elements loop is ended
				if (j == productsRequired.length) {
					break;
				}

			}
		}
	}

}
