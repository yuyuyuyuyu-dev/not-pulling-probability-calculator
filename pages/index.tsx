import { OpenGraphHead } from "@/components/OpenGraphHead";
import { calcDrawingProbalility } from "@/utils/calcDrawingProbability";
import { calcNonDrawingProbability } from "@/utils/calcNonDrawingProbability";
import { Container, Stack, TextField, Typography } from "@mui/material";
import { useState } from "react";

export default function Home() {
  const [odds, setOdds] = useState<number>();
  const [times, setTimes] = useState<number>();

  return (
    <Container maxWidth="xs">
      <OpenGraphHead
        title="引けない確率の計算"
        description="1度も引けない確率を計算するWebアプリです。"
      />
      <Stack spacing={2}>
        <div />
        <TextField
          label="排出率（単位：％）"
          type="number"
          value={odds}
          onChange={(e) => {
            const n = Number(e.target.value);
            setOdds(n >= 0 ? n : 0);
          }}
        />
        <TextField
          label="試行回数（単位：回）"
          type="number"
          value={times}
          onChange={(e) => {
            const n = Number(e.target.value);
            setTimes(n >= 0 ? n : 0);
          }}
        />
        <Typography>1度も引けない確率</Typography>
        {odds && times && (
          <Typography gutterBottom>
            {"約 " +
              calcNonDrawingProbability((odds || 0) / 100, times || 0) * 100 +
              " ％"}
          </Typography>
        )}
        <Typography>少なくとも1回は引ける確率</Typography>
        {odds && times && (
          <Typography>
            {"約 " +
              calcDrawingProbalility((odds || 0) / 100, times || 0) * 100 +
              " ％"}
          </Typography>
        )}
      </Stack>
    </Container>
  );
}
