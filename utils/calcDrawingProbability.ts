import { calcNonDrawingProbability } from "./calcNonDrawingProbability";

export const calcDrawingProbalility = (odds: number, times: number) => {
  return 1 - calcNonDrawingProbability(odds, times);
};
