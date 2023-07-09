import Head from "next/head";

type Props = {
  title: string;
  description?: string;
};

export const OpenGraphHead = ({ title, description }: Props) => {
  return (
    <Head>
      <title>{title}</title>

      <div prefix="og: https://ogp.me/ns# website: https://ogp.me/ns/website#">
        <meta property="og:title" content={title} />
        <meta
          property="og:image"
          content="https://raw.githubusercontent.com/yu-ko-ba/non-drawing-probability-calculator/main/public/icons/maskable_icon_x192.png"
        />

        <meta name="twitter:card" content="summary" />
        <meta name="twitter:site" content="@yu_ko_ba" />
        <meta name="twitter:creator" content="@yu_ko_ba" />

        {description && (
          <>
            <meta property="og:description" content={description} />
            <meta name="twitter:description" content={description} />
          </>
        )}
      </div>
    </Head>
  );
};
