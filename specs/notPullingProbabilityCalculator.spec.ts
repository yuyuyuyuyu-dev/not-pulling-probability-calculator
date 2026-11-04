import { expect, test } from "@playwright/test";

test.use({ locale: "en-US" });

test("shows the probabilities for 0.75% odds and 300 trials", async ({ page }) => {
  // Arrange
  await page.goto("/");

  // Act
  await page.locator("#oddsTextField").click({ force: true });
  await page.keyboard.type("0.75");
  await page.locator("#trialsTextField").click({ force: true });
  await page.keyboard.type("300");

  // Assert
  await expect(page.locator("body")).toMatchAriaSnapshot(`
    - text: Probability of not pulling at all Approx. 10.45092258072379 % Probability of pulling at least once Approx. 89.54907741927622 % NOT Pulling Probability
  `);
});
