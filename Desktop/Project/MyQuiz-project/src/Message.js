// Message.js

import React from "react";
import img from "./hand_one_finger.png";
import { useDispatch, useSelector } from "react-redux";
import {addRank} from "./redux/modules/rank";

const Message = (props) => {
  const dispatch = useDispatch();
  const name = useSelector((state) => state.quiz.name);
  const answers = useSelector((state) => state.quiz.answers);
  const user_name = useSelector((state)=>state.rank.user_name);

  const input_text = React.useRef(null);
  let correct = answers.filter((answer) => {
    return answer;
  });

  let score = (correct.length / answers.length) * 100;

  return (
    <div
      style={{
        display: "flex",
        height: "100vh",
        width: "100vw",
        overflow: "hidden",
        padding: "16px",
        boxSizing: "border-box",
      }}
    >
      <div
        className="outter"
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          flexDirection: "column",
          height: "96vh",
          width: "100vw",
          overflow: "hidden",
          padding: "0px 10vw",
          boxSizing: "border-box",
          maxWidth: "400px",
          margin: "0px auto",
          border: "2px solid #dadafc",
          borderRadius: "30px",
        }}
      >
        <img
          src={img}
          style={{ width: "80%", margin: "-70px 16px 48px 16px" }}
        />
        <h1 style={{ fontSize: "1.5em", margin: "0px", lineHeight: "1.4" }}>
          <span
            style={{
              backgroundColor: "#dadafc",
              padding: "5px 10px",
              borderRadius: "30px",
            }}
          >
            {name}
          </span>
          에게 한마디
        </h1>
        <input
        ref={input_text}
          type="text"
          style={{
            padding: "10px",
            margin: "24px 0px",
            border: "1px solid #dadafc",
            borderRadius: "30px",
            width: "100%",
          }}
          placeholder="한 마디 적기"
        />
        <button
          onClick={() => {
            
            let rank_info = {
                score: parseInt(score),
                name: user_name,
                message: input_text.current.value,
                current: true,
            };
            dispatch(addRank(rank_info));
            props.history.push('/ranking');

          }}
          style={{
            padding: "8px 24px",
            backgroundColor: "#dadafc",
            borderRadius: "30px",
            border: "#dadafc",
          }}
        >
          한마디하고 랭킹 보러 가기
        </button>
      </div>
    </div>
  );
};

export default Message;