import { OpenGraphHead } from "@/components/OpenGraphHead";
import { Container, Stack } from "@mui/material";

export default function Home() {
  return (
    <Container maxWidth="md">
      <OpenGraphHead
        title="引けない確率の計算"
      />
      <Stack spacing={2}>
      </Stack>
    </Container>
  )
}
