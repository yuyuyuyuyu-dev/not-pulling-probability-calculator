import {
  AppBar,
  Box,
  CssBaseline,
  Divider,
  IconButton,
  Menu,
  MenuItem,
  Toolbar,
} from "@mui/material";
import { NextMuiLink } from "./NextMuiLink";
import { useState } from "react";
import { MoreVert } from "@mui/icons-material";

export const Header = () => {
  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null);
  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <AppBar component="nav">
        <Toolbar>
          <NextMuiLink href="/" variant="h6" color="inherit" underline="hover">
            引けない確率の計算
          </NextMuiLink>
          <Box sx={{ flexGrow: 1 }} />
          <IconButton
            color="inherit"
            onClick={(e) => {
              setAnchorEl(e.currentTarget);
            }}
          >
            <MoreVert />
          </IconButton>
          <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={() => {
              setAnchorEl(null);
            }}
          >
            <MenuItem
              onClick={() => {
                setAnchorEl(null);
              }}
            >
              <NextMuiLink
                href="https://github.com/yu-ko-ba/non-drawing-probability-calculator/blob/main/LICENSE"
                color="inherit"
                underline="hover"
              >
                ライセンス
              </NextMuiLink>
            </MenuItem>
            <Divider />
            <MenuItem
              onClick={() => {
                setAnchorEl(null);
              }}
            >
              <NextMuiLink
                href="/open-sources"
                color="inherit"
                underline="hover"
              >
                オープンソース
              </NextMuiLink>
            </MenuItem>
          </Menu>
        </Toolbar>
      </AppBar>
      <Toolbar />
    </Box>
  );
};
