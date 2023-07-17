import { calcNonDrawingProbability } from "./calcNonDrawingProbability";

test("300連して0.75%を一度も引けない確率の計算", () => {
  const result = calcNonDrawingProbability(0.0075, 300);
  const ok = 0.10450922580723788;
  console.log(`約 ${result * 100} %`);
  expect(result).toBe(ok);
});

test("排出率が0%の場合", () => {
  console.log(`排出率が0%の場合：${calcNonDrawingProbability(0, 100)}`);
});

test("試行回数が0回の場合", () => {
  console.log(`試行回数が0回の場合：${calcNonDrawingProbability(1, 0)}`);
});
