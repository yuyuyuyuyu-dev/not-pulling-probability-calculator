import { NextMuiLink } from "@/components/NextMuiLink";
import { OpenGraphHead } from "@/components/OpenGraphHead";
import { openSources } from "@/utils/openSources";
import { ExpandMore } from "@mui/icons-material";
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Box,
  Container,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import axios, { AxiosResponse } from "axios";
import { GetStaticProps } from "next";

type OSS = {
  name: string;
  // website? だと string | undefined 型になって「`undefined` cannot be serialized as JSON.」ってエラーが出る
  website: string | null;
  github: string | null;
  license: string;
};

type Props = {
  openSources: OSS[];
};

export const getStaticProps: GetStaticProps<Props> = async () => {
  const promises: Promise<OSS>[] = openSources.map(
    async (oss): Promise<OSS> => {
      return axios.get(oss.licenseUrl).then((res: AxiosResponse) => {
        return {
          name: oss.name,
          website: oss.website || null,
          github: oss.github || null,
          license: res.data,
        };
      });
    },
  );

  return {
    props: {
      openSources: await Promise.all(promises),
    },
  };
};

export default function OpenSources({ openSources }: Props) {
  return (
    <Container maxWidth="md">
      <OpenGraphHead
        title="オープンソース"
        description="本アプリで使用しているOSSに関する情報です。"
      />
      <Stack spacing={2}>
        <Box />
        {openSources
          .sort((A: OSS, B: OSS) => {
            const a = A.name.toLowerCase();
            const b = B.name.toLowerCase();
            if (a < b) {
              return -1;
            } else if (a > b) {
              return 1;
            }
            return 0;
          })
          .map((oss: OSS) => (
            <Accordion key={oss.name}>
              <AccordionSummary expandIcon={<ExpandMore />}>
                <Typography variant="h6">
                  {oss.name}
                </Typography>
              </AccordionSummary>
              <AccordionDetails>
                {oss.website && (
                  <>
                    <Typography>🌐 Website</Typography>
                    <Typography gutterBottom>
                      <NextMuiLink
                        href={oss.website}
                        target="_blank"
                        rel="noopener noreferrer"
                      >
                        {oss.website}
                      </NextMuiLink>
                    </Typography>
                  </>
                )}
                {oss.github && (
                  <>
                    <Typography>🐾 GitHub</Typography>
                    <Typography gutterBottom>
                      <NextMuiLink
                        href={oss.github}
                        target="_blank"
                        rel="noopener noreferrer"
                      >
                        {oss.github}
                      </NextMuiLink>
                    </Typography>
                  </>
                )}
                <Typography>⚖️ License</Typography>
                <TextField
                  value={oss.license}
                  multiline
                  disabled
                  fullWidth
                />
              </AccordionDetails>
            </Accordion>
          ))}
        <Box />
      </Stack>
    </Container>
  );
}
