/** @type {import('next').NextConfig} */
const withPWA = require("next-pwa")({
  dest: "public",
});

module.exports = withPWA({
  // Lighthouseの監査が失敗するようになるのでコメントアウト
  // reactStrictMode: true,
});
