package Assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FitPeo {

	public static void main(String[] args) throws InterruptedException {
		
		// Set up Chrome options for  fast loading
				ChromeOptions options = new ChromeOptions();
				
				// Disable GPU hardware acceleration for faster performance
				options.addArguments("--disable-gpu"); 
				
				// Needed for headless mode in some environments
				options.addArguments("--no-sandbox");
				
				// Load the page faster by not waiting for all resources
				options.setPageLoadStrategy(PageLoadStrategy.NONE); 

				// Set up WebDriver manager
				WebDriverManager.chromedriver().setup();

				// Launch the browser
				WebDriver driver = new ChromeDriver(options);

				JavascriptExecutor js = (JavascriptExecutor) driver;

				try {

					// Maximize the browser window based on screen size
					driver.manage().window().maximize();

					// Set page load timeout (quick timeout)
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
					
					
					
					
					
					// Task Details: Step-1
					
					// Print the Task Details of Step-1
					System.out.println("**** Output of Step-1 ****");

					/* Navigate to the FitPeo Homepage */
					driver.get("https://www.fitpeo.com/");

					/* Use WebDriverWait instead of Thread.sleep for better handling   */
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

					// Wait for a condition to make sure the page is loaded
					wait.until(ExpectedConditions.titleContains("Remote Patient Monitoring"));

					System.out.println("Page Loaded Successfully! : Navigated to the FitPeo Homepage");
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line
					
				
					
					
					// Task Details: Step-2
					
					// Print the Task Details of Step-2
					System.out.println("**** Output of Step-2 ****");

					/*  Navigate to the Revenue Calculator Page from home homepage and Wait */
					Thread.sleep(1000);
					WebElement revenueCalculator = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/revenue-calculator']")));
					
					Thread.sleep(1000);
					revenueCalculator.click();
					
					Thread.sleep(5000);
					
					System.out.println("Page Loaded Successfully! : Navigated to the Revenue Calculator Page from home homepage");
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line
					
					
					
					// Task Details: Step-3
					
					// Print the Task Details of Step-3
					System.out.println("**** Output of Step-3 ****");

					/* Wait for the slider element */
					WebElement slider = wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//input[@type='range' and @data-index='0']")));


					/* Scroll the element into view and align it to the middle of the page */
					js.executeScript("var viewPortHeight = window.innerHeight || document.documentElement.clientHeight; "
							+ "var elementTop = arguments[0].getBoundingClientRect().top; "
							+ "window.scrollBy(0, elementTop-(viewPortHeight/2));", slider);
					
					// This prints a scrolling
					System.out.println("Scrolled the element into view"); 
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line
				
					
					
					

					// Task Details: Step-4
					
					// Print the Task Details of Step-4
					System.out.println("**** Output of Step-4 ****");

					/* Initial slider setup and to move the slider to the targetValue */
					int targetValue = 820;
					
					int currentValue = Integer.parseInt(slider.getAttribute("aria-valuenow")); // Current slider value
					System.out.println("Current value before moving the slider: " + currentValue);
					

					/* Initialize Actions for slider movement */
					Actions actions = new Actions(driver);

					/* Adjust the slider dynamically while maintaining the page position */
					while (Math.abs(currentValue - targetValue) > 2) {
						/* Allow ±2 tolerance to stop adjustments and 1st while loop */

						int offset;
						
						/* Move slider faster by increasing the offset significantly */
						if (currentValue < targetValue) { // 1st if loop
							offset = Math.min(20, targetValue - currentValue); // Larger increments
						} else {
							offset = Math.max(-20, targetValue - currentValue); // Larger decrements
						}
						// Exit of 1st if loop
						//System.out.println("offset value(Exit of 1st if loop): " + offset);  //every time offset=20

						/* Move the slider to targetValue quickly */
						actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();

						/* Update the current value after moving the slider */
						currentValue = Integer.parseInt(slider.getAttribute("aria-valuenow"));
						
						// Print a gap (empty line)
						System.out.println(); // This prints a blank line
						
						System.out.println("Current value after moving the slider: " + currentValue);
						System.out.println("Moved offset value (Exit of 1st if loop) : " + offset);  //here offset=20 only
						
							
						/* Optional: Fine-tune the slider when close to the target value */
						if (Math.abs(currentValue - targetValue) <= 50) {
							/* Start fine-tuning when within 50 pixels of target and 2nd if loop */
							
							// Print a gap (empty line)
							System.out.println(); // This prints a blank line

							while (currentValue != targetValue) { //2nd while loop
								if (currentValue < targetValue) {  // 3rd if loop
									
									// Print a gap (empty line)
									System.out.println(); // This prints a blank line
									
									actions.sendKeys(Keys.ARROW_RIGHT).perform();
									System.out.println("Slider is moved slower to RIGHT to reach target (3rd if loop) : " + currentValue);
									
								} else {
									actions.sendKeys(Keys.ARROW_LEFT).perform();
									System.out.println("Slider is moved slower to LEFT to reach target (3rd if loop) : " + currentValue);
								}
								// Exit of 3rd if loop
								
								// Print a gap (empty line)
								System.out.println(); // This prints a blank line

								currentValue = Integer.parseInt(slider.getAttribute("aria-valuenow"));
								
							}
							
							System.out.println("Slider is moving slower to target value (2nd while loop) --Target is Reached-- : " + currentValue);
							break; /* Exit 2nd while loop once target is reached */
							
						}
						// Exit 2nd if loop
						
					}
					/* Exit 1st while loop once target is reached */
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line

					System.out.println("Finally adjusted the slider (Exit 1st while loop) --Target is Reached-- : " + currentValue);

					/* Scroll the element into view and align it to the middle of the page */
					js.executeScript("var viewPortHeight = window.innerHeight || document.documentElement.clientHeight; "
							+ "var elementTop = arguments[0].getBoundingClientRect().top; "
							+ "window.scrollBy(0, elementTop-(viewPortHeight/2));", slider);

					Thread.sleep(3000);
					
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line
					
					
					

					// Task Details: Step-5
					
					
					// Print the Task Details of Step-5
					System.out.println("**** Output of Step-5 ****");

					WebElement inputElement = wait.until(ExpectedConditions
							.presenceOfElementLocated(By.cssSelector("input.MuiInputBase-inputSizeSmall.css-1o6z5ng")));

					Thread.sleep(1000);
					inputElement.click();
					Thread.sleep(1000);

					/* Get the current value of the input element    */
					String digits = inputElement.getAttribute("value");

					/* Clear existing content using BACK_SPACE (from value 820)  */
					for (int i = 0; i < digits.length(); i++) {
						inputElement.sendKeys(Keys.BACK_SPACE);
						Thread.sleep(500); // Optional delay between each backspace
					}
		         
					System.out.println("Current value before enetering in Text field: " + currentValue);

					/* Entering input text field and slider also moved i.e: 560 */
					String number = "560";

					// Loop through each character in the number and enter one by one
					for (int i = 0; i < number.length(); i++) {
						// Get each digit as a character
						String digit = String.valueOf(number.charAt(i));
						Thread.sleep(500);
						// Send the digit to the input field
						inputElement.sendKeys(digit);
					}
					Thread.sleep(3000);
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line

				    /*Get the value of the input element  */
					String inputValue = inputElement.getAttribute("value");
					System.out.println("Current value after enetering in Text field: " + inputValue);
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line

					/* Get the slider value (using aria-valuenow, which holds the current value) */
					String sliderValue = slider.getAttribute("aria-valuenow");
					System.out.println("Slider also changed accordingly after entering Text field value to: " + sliderValue);
					
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line
					
					
					
					

					// Task Details: Step-6
					
					// Print the Task Details of Step-6
					System.out.println("**** Output of Step-6 ****");

					if (inputValue.equals(sliderValue) && sliderValue.equals(inputValue)) {
						System.out.println("Validation is Passed: Ensured that the value " + inputValue
								+ " is entered in the text field and the slider's position also updated to reflect the value is "
								+ sliderValue);
					} else {
						System.out.println("Validation is Failed: Ensured that the value " + inputValue
								+ " is entered in the text field and the slider's position also updated to reflect the value is "
								+ sliderValue);
					}
					Thread.sleep(2000);
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line
					
					
					

					// Task Details: Step-7
					
					// Print the Task Details of Step-7
					System.out.println("**** Output of Step-7 ****");

					// List of CPT codes
					String[] cptCodes = { "CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474" };

					// Loop through each CPT code
					for (String cptCode : cptCodes) {

						/* Locate the <p> element with the specific CPT code text    */
						WebElement text = driver.findElement(By.xpath("//p[text()='" + cptCode + "']"));

						/* Scroll the element into view and align it to the middle of the page     */
						js.executeScript("var viewPortHeight = window.innerHeight || document.documentElement.clientHeight; "
								+ "var elementTop = arguments[0].getBoundingClientRect().top; "
								+ "window.scrollBy(0, elementTop - (viewPortHeight / 2));", text);

						// Locate the checkbox that is directly associated with the CPT code
						WebElement checkbox = driver
								.findElement(By.xpath("//p[text()='" + cptCode + "']/following::input[@type='checkbox'][1]"));
						
				
						/* Click the checkbox if it's not already selected    */
						if (!checkbox.isSelected()) {
							Thread.sleep(500); /* Optional delay to ensure the checkbox is clickable   */
							checkbox.click();
							
							// Print selected the checkbox of CPT Codes
							System.out.println("Selected the checkbox of "+cptCode);
						}

						/*     Add a small delay for smoother interaction   */
						Thread.sleep(1000);
					}
					Thread.sleep(3000); /* Wait for the operations to complete   */
					
					// Print a gap (empty line)
					System.out.println(); // This prints a blank line
					
					
					
					
					

					// Task Details: Step-8 and Step-9
					
					// Print the Task Details of Step-8 and Step-9
					System.out.println("**** Output of Step-8 and Step-9 ****");
					
					
					/* Locate the element that contains the Total Recurring Reimbursement text  */
					WebElement totalElement = driver
							.findElement(By.xpath("//p[contains(text(), 'Total Recurring Reimbursement')]/p[text()='75600']"));

					/* Extract the actual text from the element    */
					String actualValue = totalElement.getText().trim();

					// Expected text to validate
					String expectedValue = "$75600";
					
					/* Compare the actual value with the expected value    */
					if (actualValue.equals(expectedValue)) {
						System.out.println(
								"Validation is Passed: Header displaying Total Recurring Reimbursement for all Patients Per Month: "
										+ expectedValue + " is displayed Correctly as: " + actualValue);
					} else {
						System.out.println(
								"Validation is Failed: Header displaying Total Recurring Reimbursement for all Patients Per Month: "
										+ expectedValue + " is displayed Incorrectly. Actual value as: " + actualValue);
					}

					
					
					
				} catch (Exception e) {
					e.printStackTrace(); /* Print the exception for debugging */
				} finally {

					Thread.sleep(120000); // Wait for 2 minutes
					driver.quit();

				}

	}

}
