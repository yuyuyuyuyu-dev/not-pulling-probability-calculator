export const calcNonDrawingProbability = (odds: number, times: number) => {
  return (1 - odds) ** times;
};
