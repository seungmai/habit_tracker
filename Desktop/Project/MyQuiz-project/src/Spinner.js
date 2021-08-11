// Spinner.js

import React from "react";
import styled from "styled-components";

import img from "./hand_one_finger.png";
const Spinner = (props) => {
  return (
    <Outter>
      <img src={img} />
    </Outter>
  );
};

const Outter = styled.div`
  background-color: #dadafc;
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  & img {
    width: 150px;
  }
`;
export default Spinner;