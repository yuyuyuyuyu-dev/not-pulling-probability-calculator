import { expect, test } from "@playwright/test";

test.use({ locale: "ja-JP" });

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
    - text: 一度も引けない確率 約 10.45092258072379 % 少なくとも1回は引ける確率 約 89.54907741927622 % 引けない確率の計算
  `);
});
