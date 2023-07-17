export const calcNonDrawingProbability = (odds: number, times: number) => {
  // 排出率や試行回数が0より小さい値だった場合の確率をご存じの方がいらっしゃったらissueやTwitterで教えてくださると幸いです。
  return (1 - odds) ** times;
};
