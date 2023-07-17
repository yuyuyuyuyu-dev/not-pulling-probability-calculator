import { Link as MuiLink, LinkProps as MuiLinkProps } from "@mui/material";
import NextLink, { LinkProps as NextLinkProps } from "next/link";
import React from "react";

export const NextMuiLink = (props: NextLinkProps & MuiLinkProps) => {
  // Next.jsのLink経由でMUIのLinkにhref渡すようにしたいので {...props} は使用していません
  return (
    <NextLink href={props.href} passHref legacyBehavior>
      <MuiLink
        variant={props.variant}
        color={props.color}
        underline={props.underline}
        target={props.target}
        rel={props.rel}
      >
        {props.children || props.href}
      </MuiLink>
    </NextLink>
  );
};
